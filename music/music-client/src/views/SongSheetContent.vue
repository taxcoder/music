<template>
  <div id="content">
    <data-info-top>
      <template #default>
        <song-sheet-info :song-sheet="songSheetData" :song="song"></song-sheet-info>
      </template>
    </data-info-top>
    <song-list :musics="songSheetData" :songs="song" :size="currentSize()" :all-song="allSong" :length="length"></song-list>
    <data-page :size="size" @changeCurrent="changeCurrent"></data-page>
    <user-comment></user-comment>
  </div>
</template>

<script>
import {songSheet} from "@/network/http";
import {errorMessage} from "@/plugins/message";
import {splicing} from "@/plugins/splicingPrefix";
import splicingPrefix from "@/plugins/splicingPrefix";
import SongList from "@/components/body/comm/SongList";
import DataPage from "@/components/comm/paging/DataPage";
import DataInfoTop from "@/components/body/comm/DataInfoTop";
import SingerInfo from "@/components/body/singercontent/SingerInfo";
import UserComment from "@/components/comm/usercomment/UserComment";
import SongSheetInfo from "@/components/body/songsheetcontent/SongSheetInfo";

export default {
  name: "SongSheetContent",
  components: {
    DataInfoTop,
    SongSheetInfo,
    SongList,
    DataPage,
    UserComment,
    SingerInfo
  },
  data(){
    return {
      songSheetData: {},
      song:  [],
      size: 1,
      current: 1,
      allSong: 0,
      length: 0
    }
  },
  mounted() {
    songSheet.songSheetInfo(this.$route.params.data,this.$route.query.current).then(success => {
      this.songSheetData = splicing(success.info.data.data[0],'songListImagesAddress')
      this.song = splicingPrefix(success.songs.data.data.list,'songImages');
      this.size = success.songs.data.data.pagination
      this.allSong = success.songs.data.data.total
      this.length = success.songs.data.data.length
    },error => {errorMessage(error.message)})
  },
  methods: {
    changeCurrent(index){
      songSheet.newSong(this.$route.params.data,index).then(success => {
        this.song = splicingPrefix(success.data.data.list,'songImages');
        document.documentElement.scroll(0,0)
        this.$router.push('/songSheet/'+this.$route.params.data+'?current='+index)
      },error => {errorMessage(error.message)})
    },
    singerShow(){
      return this.$route.name === 'singer'
    },
    currentSize(){
      let pattern = new RegExp("[0-9]+");
      if(pattern.test(this.$route.query.current)){
        return Number.parseInt(this.$route.query.current)
      }
      return 1
    }
  }
}
</script>

<style scoped>
#content{
  width: 1200px;
  min-width: 1200px;
  margin: 20px auto 50px auto;
  box-sizing: border-box;
  border-radius: 2px;
}
</style>
