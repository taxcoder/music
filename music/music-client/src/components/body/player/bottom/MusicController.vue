<template>
  <!-- 歌曲控制（上一首 下一首等等） -->
  <div id="play">
        <span id="left" class="controller-icon">
          <img src="~@/assets/img/overall/music-controller.svg" @click="top_music()">
        </span>
    <span id="center">
          <i class="el-icon-video-play" v-show="!isPlay()" @click="changePlay()"></i>
          <i class="el-icon-video-pause" v-show="isPlay()" @click="changePlay()"></i>
        </span>
    <span id="right" class="controller-icon">
          <img src="~@/assets/img/overall/music-controller.svg" @click="bottom_music()">
        </span>
  </div>
</template>

<script>
import {MUSIC_LIST, PAUSED_MUSIC, PLAY, PLAY_INDEX} from "@store/constant";
import {errorMessage, infoMessage} from "@/plugins/message";
import {MessageBox} from "element-ui";
import Map from "@/plugins/map";

export default {
  name: "MusicController",
  data() {
    return {
      play: false,
    }
  },
  methods: {
    changePlay() {
      let audio = document.getElementById('audio')
      if(audio.autoplay === false){
        audio.autoplay = true
      }
      this.flag = !this.flag
      if (this.flag) {
        this.musicPlay()
      } else {
        this.musicPaused()
      }
      this.$store.commit(PLAY, this.flag)
    },
    isPlay() {
      return this.$store.state.play
    },
    musicPlay() {
      let audio = document.getElementById('audio')
      audio.play().then(success => {
          },
          error => {
            errorMessage('出现未知错误，播放失败，请刷新页面重试！')
          })
    },
    musicPaused() {
      let audio = document.getElementById('audio')
      let data = audio.pause()
      if (!audio.paused) {
        this.musicPaused().then(success => {},
            error => {
              errorMessage('出现未知错误，暂停失败，请刷新页面重试！')
            })
      }
    },
    top_music(){
      this.isMusicInfo('top')
    },
    bottom_music(){
      this.isMusicInfo('bottom')
    },
    isMusicInfo(type){
      let flag = false
      let map = Map.getInstance();
      let data = this.$store.state.playIndex
      let list = this.$store.state.musicList

      if (data === undefined || typeof data !== "string" || data === '' || data.length !== 16) {
        let audio = map.get('audio')
        if (audio === null || typeof audio !== 'string' || audio === '' || audio.length !== 16) {
          flag = false
          this.data = ''
          map.addString('audio-id', '')
          this.$store.commit(PLAY_INDEX, '')
        } else {
          flag = true
          let data = audio
          this.$store.commit(PLAY_INDEX, audio)
        }
      }else{
        flag = true
      }

      if (list === undefined || typeof list !== "object" || list.length === 0) {
        let musics = map.get('music-list')
        if (musics === null || typeof musics !== 'object' || musics.length === 0) {
          map.addString('audio-id', '')
          this.$store.commit(PLAY_INDEX, '')
          this.$store.commit(MUSIC_LIST, [])
          map.addString('music-list', JSON.stringify([]))
          return;
        } else {
          list = musics
          this.$store.commit(MUSIC_LIST, musics)
          if(!flag){
            map.addString('audio-id', musics[0].songId)
            this.$store.commit(PLAY_INDEX, musics[0].songId)
          }
          this.$store.commit(PAUSED_MUSIC, false)
          return;
        }
      }else{
        if(!flag){
          map.addString('audio-id', list[0].songId)
          this.$store.commit(PLAY_INDEX, list[0].songId)
        }
      }

      if(type === 'top'){
        this.TopPlayback(list,data)
      }else if(type === 'bottom'){
        this.BottomPlayback(list,data)
      }

    },
    BottomPlayback(list,data) {
      let resource
      let map = Map.getInstance();
      let length = map.ArrayByValueReturnIndex(list, 'songId', data)
      if (length < list.length - 1) {
        // 播放下一首
        resource = list[length + 1]
      } else {
        // 播放第一首
        resource = list[0]
      }
      map.addString('audio-id',resource.songId)
      this.$store.commit(PLAY_INDEX, resource.songId)
      this.$store.commit(PLAY,false)
      clearInterval(this.clearId)
      document.getElementById('audio').currentTime = 0
      this.currentTime = -1
    },
    TopPlayback(list,data) {
      let resource
      let map = Map.getInstance();
      let length = map.ArrayByValueReturnIndex(list, 'songId', data)
      if (length > 0) {
        // 播放下一首
        resource = list[length - 1]
      } else {
        // 播放第一首
        resource = list[list.length - 1]
      }
      map.addString('audio-id',resource.songId)
      this.$store.commit(PLAY_INDEX, resource.songId)
      this.$store.commit(PLAY,false)
      clearInterval(this.clearId)
      document.getElementById('audio').currentTime = 0
      this.currentTime = -1
    },
  }
}
</script>

<style scoped>
#play {
  width: 240px;
  min-width: 240px;
  height: 100%;
  min-height: 90px;
  display: grid;
  grid-template-columns: repeat(3, 33.33%);
  align-items: center
}

#play > span {
  cursor: pointer;
  width: 100%;
  height: 100%;
}

.controller-icon > img {
  width: 35px;
}

#left, #right, #center {
  display: flex;
  align-items: center;
  justify-content: center;
}

#center > i {
  color: white;
  font-size: 55px;
}

#left > img {
  transform: rotate(180deg);
}
</style>