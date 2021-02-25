<template>
<div id="songSheet">
  <choose-style :types="types" @getNewsSongSheet="getNewsSongSheet"></choose-style>
  <div id="sheet">
    <song-sheet-top @getNewDataTop="getNewDataTop" :activeTag="activeTag"></song-sheet-top>
    <song-sheet-show :list-data="songSheet" type="SongSheets">
      <template #title="title">
        <span class="title"><a href="" :title="title.title">{{ title.title }}</a></span>
      </template>

      <template #heat="heat">
        <span class="heat">播放量：{{ temperature(heat.heat) }}</span>
      </template>
    </song-sheet-show>
    <data-page @changeCurrent="changeCurrent" :size="size"></data-page>
  </div>
</div>
</template>

<script>
import {songSheets} from "@/network/http";
import SongSheetShow from "@/components/body/index/SongSheetShow";
import SongSheetTop from "@/components/body/songsheet/SongSheetTop";
import DataPage from "@/components/comm/paging/DataPage";
import ChooseStyle from "@/components/body/songsheet/ChooseStyle";
import {errorMessage} from "@/plugins/message";
import splicingPrefix from "@/plugins/splicingPrefix";
export default {
  name: "SongSheet",
  components: {
    ChooseStyle,
    DataPage,
    SongSheetTop,
    SongSheetShow
  },
  data(){
    return {
      // 类别
      types: {},
      // 歌单数据
      songSheet: [],
      // 当前的页数
      current: 1,
      choose: 0,
      // 判断是否有类别处于激活状态
      activeTag: {
        'songSheetTypeId': '0',
        'songSheetTypeName': '暂无'
      },
      size: 1
    }
  },
  created: function () {
    if(this.$route.query.allSongSheet !== undefined){
      this.allSongSheet();
    }else{
      this.tagSongSheet();
    }
  },
  methods: {
    allSongSheet(){
      songSheets.recommendAll(this.$route.query.allSongSheet,this.$route.query.current).then(success => {
        this.types = JSON.parse(success.types.data.data)
        let songSheet =JSON.parse(success.songSheet.data.data);
        this.songSheet = splicingPrefix(songSheet.list,'songListImagesAddress')
        this.size = songSheet.pagination;
        this.choose = this.$route.query.allSongSheet
        this.current = this.$route.query.current
      }, error => {errorMessage("数据获取失败，请重试！")})
    },
    tagSongSheet(){
      songSheets.tagAll(this.$route.query.tag,this.$route.query.current).then(success => {
        this.types = JSON.parse(success.types.data.data)
        let songSheet =JSON.parse(success.songSheet.data.data);
        this.size = songSheet.pagination;
        this.activeTag.songSheetTypeId = songSheet.list[0].songSheetTypeId
        this.activeTag.songSheetTypeName = songSheet.list[0].songSheetTypeName
        this.songSheet = splicingPrefix(songSheet.list[0].songList,"songListImagesAddress")
      }, error => {errorMessage("数据获取失败，请重试！")})
    },
    temperature(data){
      if(data < 10000){
        return data
      }
      if(data < 100000000){
        return (data / 10000).toFixed(2) + '万'
      }
      return (data / 100000000).toFixed(2) + "亿"
    },
    getNewDataTop(data){
      this.choose = data
      this.$router.push('/songSheets?allSongSheet='+data+"&current="+this.current)
      this.requestData(data,this.current).then(success => {
        let songSheet =JSON.parse(success.data.data);
        this.songSheet = splicingPrefix(songSheet.list,'songListImagesAddress')
        this.size = songSheet.pagination;
      },error => {errorMessage("数据获取失败，请重试！")})

      this.activeTag = {
        'songSheetTypeId': '0',
        'songSheetTypeName': '暂无'
      }
    },
    requestData(data,current){
      if(data === '1'){
        return songSheets.news(current)
      }else{
        return songSheets.recommend(current)
      }
    },
    changeCurrent(data){
      this.current = data.current
      this.$router.push('/songSheets?allSongSheet='+this.choose+"&current="+data.current)
      if(this.activeTag.songSheetTypeId === '0'){
        this.requestData(this.choose,this.current).then(success => {
        },error => {errorMessage("数据获取失败，请重试！")});
      }
    },
    getNewsSongSheet(data){
      this.activeTag.songSheetTypeId = data.songSheetTypeId
      this.activeTag.songSheetTypeName = data.songSheetTypeName
      this.$router.push("/songSheets?tag="+data.songSheetTypeId+"&current="+this.current);
      songSheets.tags(data.songSheetTypeId,this.current).then(success => {
        let songSheet =JSON.parse(success.data.data);
        this.size = songSheet.pagination;
        this.activeTag.songSheetTypeId = songSheet.list[0].songSheetTypeId
        this.activeTag.songSheetTypeName = songSheet.list[0].songSheetTypeName
        this.songSheet = splicingPrefix(songSheet.list[0].songList,"songListImagesAddress")
      },error => {errorMessage("数据获取失败，请重试！")});
    }
  }
}
</script>

<style scoped>
#songSheet{
  width: 100%;
  height: 100%;
}

#sheet{
  width: 1250px;
  min-width: 1250px;
  height: 100%;
  margin: 20px auto 0 auto;
}

.title{
  width: 100%;
  position: absolute;
  box-sizing: border-box;
  overflow: hidden;
  text-overflow: ellipsis;
  -o-text-overflow: ellipsis;
  white-space: nowrap;
  word-break: break-all;
  left: 0;
  bottom: 23px;
}

.heat{
  width: 100%;
  position: absolute;
  left: 0;
  font-size: 14px;
  color: #888;
  bottom: 0;
}

a{
  text-decoration: none;
  font-size: 16px;
  color: black;
  width: 100%;
}

a:hover{
  color: red!important;
}


</style>
