<template>
  <div>
<!--    <h1>{{message}}</h1>-->
<!--    <element-view></element-view>-->
<!--    <emp-view></emp-view>-->
    <router-view></router-view>
  </div>
</template>

<script>
// import ElementView from "@/views/element/ElementView.vue";
// import EmpView from "@/views/tlias/EmpView.vue";

export default {
  components: {
/*    EmpView*/
    /*ElementView*/},
  data() {
    return {
      message: "Hello Vue"
    }
  },
  methods: {

  }
}
</script>

<style>

</style>
