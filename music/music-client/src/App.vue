<template>
  <div id="app">
    <main-page></main-page>
  </div>
</template>

<script>
import MainPage from '@/components/Main'
export default {
  name: 'app',
  components: {
    MainPage
  }
}
</script>

<style>
@import "assets/css/overall.css";

#app{
  width: 100%;
  height: 100%;
}
</style>
