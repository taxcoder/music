<template>
  <div id="all">
    <slot name="data"></slot>
    <slot name="choose"></slot>
  </div>
</template>

<script>
export default {
name: "AllSongSheet"
}
</script>

<style scoped>
#all{
  width: 100%;
  height: 40px;
  margin-bottom: 30px;
  box-sizing: border-box;
  padding: 0 25px;
}
</style>