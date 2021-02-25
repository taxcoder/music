<template>
  <div class="chunk" :style="link" :class="{'place':place}">
    <slot></slot>
  </div>
</template>

<script>
export default {
  name: "HeaderChunkItem",
  props: {
    DivWidth: String,
    place: Boolean,
    right: Number
  },
  data() {
    return {
      link: {
        "width": this.DivWidth,
        'right': this.right + 'px'
      }
    }
  }
}
</script>

<style scoped>
.chunk{
  height: 100%;
  width: 100px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.message{
  padding: 10px;
  cursor: pointer;
  font-size: 17px;
  display: flex;
  width: 100%;
  align-items: center;
  justify-content: center;
}

.place{
  position: absolute;
  right: 0;
}

.hover:hover{
  color: #0ea1ba;
  position: relative;
}

.hover:hover:after{
  content: "";
  display: block;
  height: 3px;
  background: #0ea1ba;
  position: absolute;
  animation: develop 0.55s linear forwards;
  top: 50px;
}

@keyframes develop {
  0%{
    width: 0;
  }
  100%{
    width: 15px;
  }
}


</style>