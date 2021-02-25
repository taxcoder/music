<template>
  <all-song-sheet>
    <template #data>
      <h2 v-if="topIsShow()">全部歌单</h2>
      <div class="activeTag" v-else @mouseleave="leave" @mouseenter="enter">
        {{ activeTag.songSheetTypeName }}
        <img class="close" :src="img" alt="close" @click="close">
      </div>
    </template>
    <template #choose v-if="topIsShow()">
      <div class="sheetChoose">
        <span :class="{'sheetActive':isActiveRecommend()}" @click="addColor" :value="'0'">推荐</span>
        <span :class="{'sheetActive':isActiveNews()}" @click="addColor" :value="'1'">最新</span>
      </div>
    </template>
  </all-song-sheet>
</template>

<script>
import AllSongSheet from "@/components/body/songsheet/AllSongSheet";
export default {
  name: "SongSheetTop",
  data(){
    return {
      img: 'data:image/svg+xml,%3Csvg class=\'icon\'\n' +
          ' viewBox=\'0 0 1024 1024\' xmlns=\'http://www.w3.org/2000/svg\'\n' +
          ' width=\'32\' height=\'32\'%3E%3Cpath d=\'M975.07 746.176a162.756\n' +
          ' 162.756 0 0 1-230.135 230.135L511.977 743.4 279.065 976.311A162.756\n' +
          ' 162.756 0 0 1 48.931 746.176l232.912-232.934L48.93 280.33a162.688\n' +
          ' 162.688 0 0 1 0-230.112 162.711 162.711 0 0 1 230.134 0l232.912\n' +
          ' 232.89 232.935-232.89A162.711 162.711 0 1 1 975.047 280.33L742.135\n' +
          ' 513.242l232.934 232.934z\' fill=\'%232c2c2c\'/%3E%3C/svg%3E'
    }
  },
  components: {
    AllSongSheet
  },
  props: {
    activeTag: Object,
    default() {
      return {}
    }
  },
  methods: {
    topIsShow(){
      return this.$route.query.tag === undefined
    },
    isActiveRecommend(){
      if(this.$route.query.allSongSheet === undefined){
        return false;
      }
      return this.$route.query.allSongSheet === '0'
    },
    isActiveNews(){
      if(this.$route.query.allSongSheet === undefined){
        return false;
      }
      return this.$route.query.allSongSheet === '1'
    },
    enter($event){
      $event.currentTarget.setAttribute('class','activeTag tag')
      this.img = 'data:image/svg+xml,%3Csvg class=\'icon\' viewBox=\'0 0 1024 1024\'' +
          ' xmlns=\'http://www.w3.org/2000/svg\' width=\'32\' height=\'32\'%3E%3Cpath' +
          ' d=\'M975.07 746.176a162.756 162.756 0 0 1-230.135 230.135L511.977 743.4' +
          ' 279.065 976.311A162.756 162.756 0 0 1 48.931 746.176l232.912-232.934L48.93' +
          ' 280.33a162.688 162.688 0 0 1 0-230.112 162.711 162.711 0 0 1 230.134 0l232.912' +
          ' 232.89 232.935-232.89A162.711 162.711 0 1 1 975.047 280.33L742.135 513.242l232.934' +
          ' 232.934z\' fill=\'%23fff\'/%3E%3C/svg%3E'
    },
    leave($event){
      $event.currentTarget.setAttribute('class','activeTag')
      this.img = 'data:image/svg+xml,%3Csvg class=\'icon\'\n' +
          ' viewBox=\'0 0 1024 1024\' xmlns=\'http://www.w3.org/2000/svg\'\n' +
          ' width=\'32\' height=\'32\'%3E%3Cpath d=\'M975.07 746.176a162.756\n' +
          ' 162.756 0 0 1-230.135 230.135L511.977 743.4 279.065 976.311A162.756\n' +
          ' 162.756 0 0 1 48.931 746.176l232.912-232.934L48.93 280.33a162.688\n' +
          ' 162.688 0 0 1 0-230.112 162.711 162.711 0 0 1 230.134 0l232.912\n' +
          ' 232.89 232.935-232.89A162.711 162.711 0 1 1 975.047 280.33L742.135\n' +
          ' 513.242l232.934 232.934z\' fill=\'%232c2c2c\'/%3E%3C/svg%3E'
    },
    addColor($event){
      let clazz = $event.currentTarget.getAttribute('class');
      if(clazz !== null && clazz.split(' ').filter(split => split.indexOf('sheetActive') !== -1).length !== 0){
        return;
      }
      for(let node of $event.currentTarget.parentElement.childNodes){
        node.setAttribute('class','')
      }
      $event.currentTarget.setAttribute('class','sheetActive')
      this.$emit('getNewDataTop',$event.currentTarget.getAttribute('value'))
    },
    close(){
      this.$router.push('/songSheets')
      window.location.reload()
    }
  }
}
</script>

<style scoped>
h2{
  display: inline-block;
  margin: 0;
  line-height: 40px;
  font-size: 26px;
}

.sheetChoose{
  display: flex;
  float: right;
  width: 130px;
  height: 40px;
  border: 1px solid red;
  box-sizing: border-box;
  border-radius: 3px;
}

.sheetChoose > span{
  padding: 5px;
  flex: 1;
  box-sizing: border-box;
  line-height: 30px;
  text-align: center;
  cursor: pointer;
  width: 100%;
  height: 100%;
  background: none;
  display: block;
  float: left;
}

.sheetActive{
  background: red!important;
  color: white;
  font-weight: 700;
}

.activeTag{
  width: 100px;
  height: 100%;
  padding: 0 10px;
  line-height: 40px;
  cursor: pointer ;
  box-sizing: border-box;
  border: 1px solid red;
  position: relative;
}

.tag{
  background: red;
  color: white;
  font-weight: 700;
}

.close{
  position: absolute;
  right: 8px;
  width: 10px;
  top: 15px;
}
</style>
