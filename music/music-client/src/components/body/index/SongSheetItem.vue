<template>
  <div class="sheet"
       @click="jump()"
       :class="{
    'sheetData':singers(),
    'sheetRight':this.$route.name === 'songSheets',
    'sheetMarginRight': allSongSheet('songSheets'),
    'sheetMarginRightFive': allSongSheet('index'),
    'allSongSheet': allSongSheet('songSheets')
  }"
       :style="right()">
    <div class="sheet-item">
      <div class="showImages" @mouseenter="enter" @mouseleave="leave" v-if="isShow()" :title="titleData">
        <img :src="start" alt="播放" class="start">
      </div>
      <div class="img" :style="styles()">
        <img v-lazy="img" :alt="name" :style="isBox()" :class="{'imagesSettings':singers()}"/>
      </div>
      <slot name="info"></slot>
    </div>
  </div>
</template>

<script>
export default {
  name: "SongSheetItem",
  data(){
    return {
      start: "https://www.tanxiangblog.com/icon/btn-play.png"
    }
  },
  props: {
    img: String,
    name: String,
    index: Number,
    marginRight: Object,
    titleData: String,
    type: {
      type: String,
      default: 'SongSheets'
    },
    id: {
      type: String,
      require: true
    }
  },
  methods: {
    jump() {
      switch (this.type) {
        case "SongSheets":
          this.$router.push('/songSheet/' + this.id + "?current=1")
          break;
        case "singers":
          this.$router.push('/singer/' + this.id + "?current=1")
          break;
      }
    },
    allSongSheet(routeData) {
      return (this.index + 1) % 5 !== 0 && this.$route.name === routeData
    },
    isShow() {
      return this.type === 'SongSheets'
    },
    enter($event) {
      let nodes = $event.currentTarget.firstElementChild;
      let next = $event.currentTarget.nextElementSibling.firstElementChild;
      next.style.transform = 'scale(1.2)'
      nodes.style.display = 'inline-block'
    },
    leave($event) {
      let nodes = $event.currentTarget.firstElementChild;
      let next = $event.currentTarget.nextElementSibling.firstElementChild;
      next.style.transform = 'scale(1)'
      nodes.style.display = 'none'
    },
    divBig() {
      return this.singers()?this.sheet:{}
    },
    right() {
      return this.singers()?this.marginRight:{}
    },
    isBox() {
      return this.singers()?"box-shadow: 0 0 10px #ccc;":""
    },
    styles(){
      return this.singers()?"padding-top:5px":{}
    },
    singers(){
      return this.$route.name === 'singers'
    }
  }
}
</script>

<style scoped>
.sheet {
  cursor: pointer;
  width: 200px;
  overflow: hidden;
  height: 250px;
}

.allSongSheet {
  margin-right: 35px !important;
  margin-left: 22px;
}

.sheetMarginRight {
  margin-right: 62px;
}

.sheetMarginRightFive {
  margin-right: 50px;
}

.sheetRight {
  margin-bottom: 30px;
}

.sheet-item {
  width: 100%;
  height: 100%;
  box-sizing: border-box;
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  position: relative;
}

.img {
  border-radius: 5px;
  overflow: hidden;
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  height: 200px;
}

.img > img {
  transition: 0.45s all;
  width: 200px;
  height: 200px;
  z-index: 1;
  border-radius: 10px;
  cursor: pointer;
}

.showImages {
  overflow: hidden;
  display: flex;
  justify-content: center;
  align-items: center;
  width: 200px;
  position: absolute;
  height: 200px;
  z-index: 3;
  cursor: pointer;
  border-radius: 5px;
}

.showImages:hover {
  background: rgba(0, 0, 0, .35);
}

.start {
  display: none;
  width: 70px;
  z-index: 21;
}

.imagesSettings {
  border-radius: 50% !important;
  width: 150px !important;
  height: 150px !important;
}

.sheetData {
  width: 200px !important;
  height: 250px !important;
}
</style>
