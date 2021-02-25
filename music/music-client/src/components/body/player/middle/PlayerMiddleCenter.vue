<template>
  <div id="music-play" class="site">
    <div class="music-click-btn">
      <click-button icon="el-icon-delete" text="删除歌曲" :checked="checked"></click-button>
    </div>
    <div id="music-info">
      <song-list
          :is-show="false"
          :musics="songSheetData"
          :songs="songs"
          :hidden="false"
          :widthSize="widthSize"
          player="player"
          icon-color="#c7c7c7"
          @checkboxResult="checkboxResult"
          hover-color="#333">
      </song-list>
    </div>
  </div>
</template>

<script>

import ClickButton from "@components/body/button/ClickButton";
import SongList from "@components/body/comm/SongList";
import {songSheet} from "@network/http";
import {splicing} from "@/plugins/splicingPrefix";
import {errorMessage} from "@/plugins/message";

export default {
  name: "PlayerMiddleCenter",
  components:{
    ClickButton,
    SongList
  },
  methods: {
    checkboxResult(data){
      this.checked = data
    },
  },
  data(){
    return {
      songSheetData: {},
      size: 1,
      checked: []
    }
  },
  props: {
    songs: {
      type: Array,
      default(){
        return []
      }
    },
    widthSize: {
      type: Number,
      default: 0
    }
  }
}
</script>

<style scoped>
#music-play{
  width: 62%;
  height: 100%;
}

.music-click-btn{
  width: 100%;
  height: 60px;
  display: flex;
  align-items: center;
}

#music-info{
  height: 89.2%;
}

.site{
  display: inline-block;
}

.songs {
  height: 100%;
}
</style>