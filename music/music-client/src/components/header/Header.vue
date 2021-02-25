<template>
  <div id="header">
    <header-bar></header-bar>
  </div>
</template>

<script>
import HeaderBar from "@/components/comm/header/HeaderBar";
export default {
  name: "Header",
  components: {
    HeaderBar
  }
}
</script>

<style scoped>
#header{
  height: 110px;
  width: 100%;
  box-sizing: border-box;
  background: #fff;
  box-shadow: 0 0 10px #ccc;
}
</style>