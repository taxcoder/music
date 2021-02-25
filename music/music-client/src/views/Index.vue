<template>
  <div id="index">
    <div id="roll">
      <rotation-chart></rotation-chart>
    </div>
    <song-sheet-item-component :List="songSheet" Name="歌单推荐" type="SongSheets"></song-sheet-item-component>
    <news-song :songs="songs" :songRegion="songRegion" @getData="newsData"></news-song>
    <song-sheet-item-component :List="singer" Name="歌手推荐" type="singers"></song-sheet-item-component>
  </div>
</template>

<script>

import splicingPrefix from "@/plugins/splicingPrefix";
import {errorMessage} from "@/plugins/message";
import {index} from '@/network/http'
import NewsSong from "@/components/body/index/NewsSong";
import RotationChart from "@/components/comm/rotation/RotationChart";
import SongSheetItemComponent from "@/components/body/index/SongSheetItemComponent";

export default {
  name: "index",
  components: {
    RotationChart,
    SongSheetItemComponent,
    NewsSong
  },
  data() {
    return {
      // 歌单信息
      songSheet: [],
      // 歌手信息
      singer: [],
      // 歌曲信息
      songs:[],
      // 歌曲地域
      songRegion: []
    }
  },
  mounted() {
    document.body.style.backgroundColor = 'white'
    // splicingPrefix 给指定的数据加上前缀
    index.indexAll().then(success => {
      this.singer = splicingPrefix(success.singer.data.data, 'singerHeadImage')
      this.songSheet = splicingPrefix(success.songSheet.data.data, 'songListImagesAddress')
      this.songs = splicingPrefix(success.song.data.data, 'songImages')
      this.songRegion = success.region.data.data
    }, error => {errorMessage(error.message)})
  },
  methods: {
    newsData(value){
      index.regionSong(value).then(
          success => {this.songs = splicingPrefix(success.data.data, 'songImages')},
          error => {errorMessage(error.message)})
    }
  }
}
</script>

<style scoped>
#index{
  width: 1200px;
  height: 100%;
  margin: 0 auto 50px auto;
}

#roll{
  width: 100%;
  margin: 30px auto 85px auto;
  height: 400px;
  border-radius: 5px;
}
</style>
