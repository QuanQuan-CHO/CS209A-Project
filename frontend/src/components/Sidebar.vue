<template>
  <!-- sidebar -->
  <nav class="sidebar bg-white dark:bg-gray-800">
    <!-- sidebar head -->
    <div class="sidebar-head p-4">

      <router-link to="/" exact>
        <h2
            class="text-2xl font-normal text-gray-800 dark:text-gray-500"
            translate="no"
        >
          <span class="text-primary">CS209A</span> Test Page: Data Visualization
          <span
              class="bg-gray-700 absolute mt-2 dark:block hidden rounded-md py-1 px-2 text-xs text-gray-200"
          >Dark mode</span
          >
        </h2>
      </router-link>


      <button class="lg:hidden block float-right -mt-7" @click="sidebarToggle">
        <svg
            xmlns="http://www.w3.org/2000/svg"
            aria-hidden="true"
            role="img"
            width="25px"
            height="25px"
            preserveAspectRatio="xMidYMid meet"
            viewBox="0 0 32 32"
        >
          <path
              fill="currentColor"
              d="M7.219 5.781L5.78 7.22L14.563 16L5.78 24.781l1.44 1.439L16 17.437l8.781 8.782l1.438-1.438L17.437 16l8.782-8.781L24.78 5.78L16 14.563z"
          />
        </svg>
      </button>


    </div>


    <!-- sidebar list -->
    <div class="sidebar-list p-4 mt-4">
      <p class="font-medium text-gray-400 dark:text-gray-600">Menu</p>
      <div class="wrap-item mt-4 dark:text-gray-500">


        <div class="item">
          <router-link
              to="/"
              exact
              class="w-full flex text-left rounded-md box-border p-3 hover:bg-gray-100 dark:hover:bg-gray-700"
          >
            <span class="mr-3 text-xl"><Icon icon="bxs:dashboard"/></span>
            <span class="w-full"> Dashboard </span>
          </router-link>
        </div>

        <div class="item mt-3">
          <menu-accordion>
            <template v-slot:icon>
              <Icon icon="gg:components"/>
            </template>
            <template v-slot:title>Dependency Maps</template>
            <template v-slot:content>
              <router-link
                  to="/maps/DependencyMap"
                  exact
                  class="w-full text-left block rounded-md p-3 hover:bg-gray-100 dark:hover:bg-gray-700">
                  <span class="w-full"> Map Test</span>
                  <span class="box-border mt-1 text-gray-500"> </span>
              </router-link>

              <router-link
                  to="/maps/SpringMap"
                  exact
                  class="w-full text-left block rounded-md p-3 hover:bg-gray-100 dark:hover:bg-gray-700">
                  <span class="w-full"> Spring Map </span>
                  <span class="box-border mt-1 text-gray-500"> </span>
              </router-link>

              <router-link
                  to="/maps/Log4jMap"
                  exact
                  class="w-full text-left block rounded-md p-3 hover:bg-gray-100 dark:hover:bg-gray-700">
                  <span class="w-full"> Log4j Map </span>
                  <span class="box-border mt-1 text-gray-500"> </span>
              </router-link>

              <router-link
                  to="/maps/LombokMap"
                  exact
                  class="w-full text-left block rounded-md p-3 hover:bg-gray-100 dark:hover:bg-gray-700">
                  <span class="w-full"> Lombok Map </span>
                  <span class="box-border mt-1 text-gray-500"> </span>
              </router-link>

              <router-link
                  to="/maps/MySQLMap"
                  exact
                  class="w-full text-left block rounded-md p-3 hover:bg-gray-100 dark:hover:bg-gray-700">
                  <span class="w-full"> MySQL Map </span>
                  <span class="box-border mt-1 text-gray-500"> </span>
              </router-link>


            </template>
          </menu-accordion>
        </div>

        <div class="item">
          <router-link
              to="/TestPage"
              exact
              class="w-full flex text-left rounded-md box-border p-3 hover:bg-gray-100 dark:hover:bg-gray-700">
            <span class="mr-3 text-xl"><Icon icon="carbon:data-table"/></span>
            <span class="w-full"> Test Page </span>
          </router-link>
        </div>


<!--        <div class="item mt-3">-->
<!--          <router-link to="/">-->
<!--            <button-->
<!--                class="text-gray-800 dark:text-gray-500 bg-transparent hover:bg-gray-100 dark:hover:bg-gray-700 w-full flex text-left rounded-md box-border p-3"-->
<!--            >-->
<!--              <span class="mr-3 text-xl"><Icon icon="bi:table"/></span>-->
<!--              <span class="w-full"> Table </span>-->
<!--              <span class="box-border mt-1 text-gray-500"> </span>-->
<!--            </button>-->
<!--          </router-link>-->
<!--        </div>-->


<!--        <div class="item mt-3">-->
<!--          <router-link to="">-->
<!--            <button-->
<!--                class="text-gray-800 dark:text-gray-500 bg-transparent hover:bg-gray-100 dark:hover:bg-gray-700 w-full flex text-left rounded-md box-border p-3"-->
<!--            >-->
<!--              <span class="mr-3 text-xl"><Icon icon="carbon:data-table"/></span>-->
<!--              <span class="w-full"> DataTables </span>-->
<!--              <span class="box-border mt-1 text-gray-500"> </span>-->
<!--            </button>-->
<!--          </router-link>-->
<!--        </div>-->


        <div class="item mt-3">
          <menu-accordion>
            <template v-slot:icon>
              <Icon icon="gg:components"/>
            </template>
            <template v-slot:title>Test Components</template>
            <template v-slot:content>


              <router-link
                  to="/component/alert"
                  class="w-full text-left block rounded-md p-3 hover:bg-gray-100 dark:hover:bg-gray-700"
              >
                Alert
              </router-link>


              <router-link
                  to="/component/accordion"
                  class="w-full text-left block rounded-md p-3 hover:bg-gray-100 dark:hover:bg-gray-700"
              >
                Accordion
              </router-link>


              <router-link
                  to="/component/badge"
                  class="w-full text-left block rounded-md p-3 hover:bg-gray-100 dark:hover:bg-gray-700"
              >
                Badge
              </router-link>


              <router-link
                  to="/component/breadcumb"
                  class="w-full text-left block rounded-md p-3 hover:bg-gray-100 dark:hover:bg-gray-700"
              >
                Breadcumb
              </router-link>


              <router-link
                  to="/component/button"
                  class="w-full text-left block rounded-md p-3 hover:bg-gray-100 dark:hover:bg-gray-700"
              >
                Button
              </router-link>


              <router-link
                  to="/component/card"
                  class="w-full text-left block rounded-md p-3 hover:bg-gray-100 dark:hover:bg-gray-700"
              >
                Card
              </router-link>


            </template>
          </menu-accordion>
        </div>
      </div>


    </div>
  </nav>
</template>
<style>
.active {
}
</style>
<script>
import {Icon} from "@iconify/vue";
import MenuAccordion from "./MenuAccordion.vue";

export default {
  components: {
    Icon,
    MenuAccordion,
  },
  methods: {
    sidebarToggle: function () {
      document.querySelector(".flex-sidebar").classList.add("hidden");
    },
  },
  mounted() {
  },
};
</script>
