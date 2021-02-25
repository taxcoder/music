<template>
  <div>
    <audio id="audio" @ended="audioEnd()" autoplay="autoplay" :src="address"></audio>
  </div>
</template>

<script>
import Map from "@/plugins/map";
import {MUSIC_LIST, PAUSED_MUSIC, PLAY, PLAY_INDEX} from "@store/constant";
import {address} from "@network/http";
import {errorMessage,warningMessage} from "@/plugins/message";

export default {
  name: "AudioData",
  data() {
    return {
      address: '',
      currentTime: 0.1,
      clearId: 0
    }
  },
  mounted() {
    let audio = document.getElementById('audio')
    if(!audio.paused){
      this.$store.commit(PLAY, true)
    }
  },
  methods: {
    audioEnd() {
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

      let resource;
      let loop = this.$store.state.loop

      if (loop) {
        // 循环播放
        this.loopPlayback(list,data)
      }

    },
    music(value) {
      address.address(value).then(success => {
        this.address = success.data
      }, error => {
        errorMessage("地址获取失败！")
      })
    },
    music_play() {
      let audio = document.getElementById('audio')
      if (audio.paused) {
        audio.play().then(() => {
          this.$store.commit(PLAY, true)
        }, () => {
          warningMessage('浏览器不支持自动播放！')
        })
      } else {
        this.$store.commit(PLAY, true)
      }
    },
    isAudioCurrentTime(audio) {
      this.currentTime = Math.ceil(audio.currentTime)
      this.clearId = setInterval(() => {
        this.currentTime = Math.ceil(audio.currentTime)
      }, 1000)
    },
    loopPlayback(list,data) {
      // 循环播放音乐
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
    }
  },
  computed: {
    setVolume() {
      return this.$store.state.volume / 100
    },
    isAudioPaused() {
      return this.$store.state.play
    },
    musicInfo() {
      return this.$store.state.playIndex
    },
    length() {
      return this.$store.state.length
    }
  },
  watch: {
    setVolume(value) {
      let audio = document.getElementById('audio')
      audio.volume = value
    },
    isAudioPaused(value) {
      if (value) {
        this.isAudioCurrentTime(document.getElementById('audio'))
      } else {
        clearInterval(this.clearId)
      }
    },
    musicInfo(value) {
      this.music(value)
      let audio = document.getElementById('audio')
      if (audio === null) {
        return;
      }
      clearInterval(this.clearId)
      audio.currentTime = 0
    },
    length(value) {
      clearInterval(this.clearId)
      document.getElementById('audio').currentTime = value
      this.isAudioCurrentTime(document.getElementById('audio'))
      this.$store.commit(PLAY,true)
    },
    address() {
      this.music_play()
    },
    currentTime(value) {
      this.$emit('changeLength', value)
    }
  }
}
</script>

<style scoped>

</style>
