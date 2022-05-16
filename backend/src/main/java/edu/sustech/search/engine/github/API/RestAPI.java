package edu.sustech.search.engine.github.API;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.deser.DeserializationProblemHandler;
import edu.sustech.search.engine.github.models.APIErrorMessage;
import org.apache.logging.log4j.*;

public class RestAPI {
    public static final Duration timeout = Duration.ofSeconds(10);
    private static final Logger logger = LogManager.getLogger(RestAPI.class);
    private static final ObjectMapper staticObjectMapper = new ObjectMapper();

    static {
        staticObjectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    /**
     * The <code>getHttpResponse</code> method uses this variable to determine whether the error message will be printed when it receives an unexpected response
     */
    private boolean suppressError = false;

    final String token;
    HttpClient client;
    final ObjectMapper objectMapper;

    public RestAPI(String OAuthToken) {
        this.token = OAuthToken;
        objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    public RestAPI() {
        this(null);
    }

    public String getHttpResponseRaw(URI uri) throws IOException, InterruptedException {
        return getHttpResponse(uri).body();
    }

    public String getHttpResponseRaw(URI uri, String acceptSchema) throws IOException, InterruptedException {
        return getHttpResponse(uri, acceptSchema).body();
    }

    public HttpResponse<String> getHttpResponse(URI uri) throws IOException, InterruptedException {
        return getHttpResponse(uri, "application/vnd.github.v3+json");
    }

    public HttpResponse<String> getHttpResponse(URI uri, String acceptSchema) throws IOException, InterruptedException {
        String tmpUri = uri.toString().replace("[", "%5b").replace("]", "%5d");
        uri = URI.create(tmpUri);

        HttpRequest.Builder builder = HttpRequest.newBuilder();
        if (token != null) {
            builder.headers("Authorization", "token " + token);
        }
        if (acceptSchema == null) {
            acceptSchema = "application/vnd.github.v3+json";
        }
        HttpRequest httpRequest = builder.headers("Accept", acceptSchema)
                .uri(uri).timeout(timeout).build();
        logger.debug("Sending request: " + httpRequest.uri());
        client = HttpClient.newHttpClient();

        HttpResponse<String> response;
        int deadLockCount = 0;
        do {
            response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                if (!suppressError) {
                    logger.error("Error upon receiving REST response from API. Check parameters, request intervals and etc. You may try again.");
                    logger.error("Request URL = " + uri.toString());
                    logger.error("Http response code: " + response.statusCode());
                }

                APIErrorMessage message = objectMapper.readValue(response.body(), APIErrorMessage.class);
                logger.warn(message.getMessage());

                if (message.getMessage().contains("secondary rate limit")) {
                    logger.error("Secondary rate limit exceeded.", new RequestRateExceededException());
                    printRateLimit(response);
                }

            }
        } while (response.statusCode() != 200 && (deadLockCount++ < 3));
        return response;
    }

    /**
     * If set to <code>true</code>, error messages for tries in the response will be hidden.
     * This is usually used in loop fetching.
     *
     * @param isErrorSuppressed
     */
    public void setSuppressError(boolean isErrorSuppressed) {
        if (isErrorSuppressed) {
            logger.warn("Error suppression on http response is " + true + ". This may cause hidden problems.");
        } else {
            logger.warn("Error suppression on http response has been recovered.");
        }
        suppressError = isErrorSuppressed;
    }

    public static <T> T convert(String jsonContent, Class<T> clazz) {
        try {
            return staticObjectMapper.readValue(jsonContent, clazz);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void printRateLimit(HttpResponse<String> response) {
        response.headers().firstValue("x-ratelimit-reset")
                .ifPresent(e -> logger.error("The rate will be reset on " + new Date(Long.parseLong(e))));
        response.headers().firstValueAsLong("x-ratelimit-limit")
                .ifPresent(e -> logger.error("The rate limit maximum is " + e));
        response.headers().firstValueAsLong("x-ratelimit-remaining")
                .ifPresent(e -> logger.error("The rate limit remaining is " + e));
    }

}
