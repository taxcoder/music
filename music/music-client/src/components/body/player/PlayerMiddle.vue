<template>
  <div id="player-middle">
    <player-middle-left :images="images"></player-middle-left>
    <!-- 播放歌曲信息 -->
    <player-middle-center :width-size="widthSize" :songs="songs"></player-middle-center>
    <!-- 专辑图片 歌词 -->
    <player-middle-right :current-play="playMusic()"></player-middle-right>
  </div>
</template>

<script>

import PlayerMiddleLeft from "@components/body/player/middle/PlayerMiddleLeft";
import PlayerMiddleCenter from "@components/body/player/middle/PlayerMiddleCenter";
import PlayerMiddleRight from "@components/body/player/middle/PlayerMiddleRight";

export default {
  name: "PlayerMiddle",
  components: {
    PlayerMiddleLeft,
    PlayerMiddleCenter,
    PlayerMiddleRight
  },
  props: {
    images: {
      type: String,
      require: true
    },
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
  },
  methods: {
    playMusic(){

      if(this.songs === null || this.songs.length <= 0){
        return;
      }
      for(let song of this.songs){
        if(this.$store.state.playIndex === song.songId){
          return song
        }
      }
    }
  }
}
</script>

<style scoped>
#player-middle {
  z-index: 11;
  width: 100%;
  height: 80%;
  display: flex;
  flex-flow: nowrap;
}

</style>