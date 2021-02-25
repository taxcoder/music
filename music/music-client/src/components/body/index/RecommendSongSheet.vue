<template>
  <div id="recommend">
    <h4>{{ name }}</h4>
    <div id="songSheet">
      <slot name="SongSheet"></slot>
    </div>
  </div>
</template>

<script>
export default {
  name: "RecommendSongSheet",
  props: {
    name: {
      type: String,
      required: true
    }
  }
}
</script>

<style scoped>
#recommend{
  width: 100%;
  margin-top: 55px;
}

h4{
  text-align: center;
  font-size: 25px;
  margin: 45px 0;
  position: relative;
}

#songSheet{
  width: 100%;
  height: 500px;
}
</style>