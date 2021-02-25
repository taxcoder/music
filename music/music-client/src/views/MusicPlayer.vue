<template>
  <div id="player">
    <div id="page-black">
      <div id="background"></div>
      <div id="back-color"></div>
      <!-- 头部 -->
      <player-top></player-top>
      <!-- 中间部分 -->
     <player-middle :images="images" :widthSize="widthSize" :songs="dataChange"></player-middle>
      <!-- 底部部分，歌曲进度等一些信息 -->
      <player-bottom></player-bottom>
    </div>
  </div>
</template>

<script>
import Map from "@/plugins/map";
import {activeMusicIdentification, code} from '@/plugins/other'
import PlayerTop from "@components/body/player/PlayerTop";
import PlayerMiddle from "@components/body/player/PlayerMiddle";
import PlayerBottom from "@components/body/player/PlayerBottom";
import {getCookie} from "@/plugins/utils";
import {LOOP_MUSIC, MUSIC_LIST, PLAY_INDEX, VOLUME, PLAY, LENGTH, LOGIN} from "@store/constant";
import store from '@/store/index'
import {errorMessage} from "@/plugins/message";

export default {
  name: "MusicPlayer",
  components: {
    PlayerTop,
    PlayerMiddle,
    PlayerBottom
  },
  data() {
    return {
      songs: [],
      images: activeMusicIdentification,
      widthSize: 0
    }
  },
  mounted() {
    this.init()
    this.lists()
    this.backgroundPage()
    this.resize()
    this.isLogin()
    this.isOpen()
  },
  methods: {
    isOpen(){
      let audio = document.getElementById('audio')
      audio.autoplay = false
    },
    init(){
      let map = Map.getInstance()
      // 音量
      let volume = map.get('volume')
      let audioId = map.get('audio-id')
      let loop = map.get('loop')
      let list = map.get('music-list')
      // 音量
      if(volume == null || !/^[1-9]\d*|0$/.test(volume)){
        map.addString('volume',40)
      }
      // 是否循环
      if(loop == null || (loop !== 'true' && loop !== 'false')){
        map.addString('loop',true)
      }
      // 歌曲集合
      if(list == null || typeof list !== 'object'){
        map.addString('music-list',JSON.stringify([]))
      }else{
        this.songs = list
      }
      // 播放歌曲ID
      if(audioId === null || audioId.length !== 16){
        map.addString('audio-id','')
      }

      this.$store.commit(VOLUME,Number.parseInt((map.get('volume')).toString()))
      this.$store.commit(PLAY_INDEX,map.get('audio-id'))
      this.$store.commit(LOOP_MUSIC,map.get('loop'))
      this.$store.commit(MUSIC_LIST,map.get('music-list'))
    },
    backgroundPage(){
      let map = Map.getInstance();
      let data = this.getMusic()
      if(data === undefined){
        return;
      }
      let background = document.getElementById('background')
      document.body.style.backgroundColor = '#292a2b'
      document.body.style.minWidth = '1000px'
      document.body.style.overflowX = 'hidden'
      background.style.backgroundImage = 'url(' + data.songImages + ')'
    },
    getMusic(){
      let data = this.$store.state.musicList

      if(data.length === 0){
        return undefined
      }
      for (let i of data){
        if(i['songId'] === this.$store.state.playIndex){
          return i;
        }
      }
    },
    resize(){
      this.widthSize = document.body.clientWidth
      window.onresize = () => {
        this.widthSize = document.body.clientWidth
      }
    },
    lists(){
      const that = this;
      //监听缓存中指定key的值变化
      window.addEventListener('storage', function (e) {
        if(e.key && e.key === 'music-list' && e.newValue){
          //即可获取到【页面A】最新的socketQuery
          let map = Map.getInstance()
          let list = map.get('music-list')
          if(list == null || typeof list !== 'object'){
            map.addString('music-list',JSON.stringify([]))
            return;
          }
          this.songs = JSON.parse(e.newValue)
          store.dispatch(MUSIC_LIST,this.songs)
        }
      })
      window.addEventListener('storage', function (e) {
        if(e.key && e.key === 'audio-id' && e.newValue){
          let map = Map.getInstance()
          let audio = map.get('audio-id')
          if(audio === null || typeof audio !== 'string' || audio.length !== 16){
            map.addString('audio-id','')
            return;
          }

          //即可获取到【页面A】最新的socketQuery
          store.dispatch(PLAY_INDEX,e.newValue)
          store.dispatch(LENGTH,0)
          store.dispatch(PLAY,false)
        }
      })
    },
    isLogin(){
      let id = getCookie('MU_ID')
      if(id === undefined){
        this.$store.commit(LOGIN,false)
        return;
      }
      let cookie = code.decrypt(id)
      if(cookie.charAt(0) !== '{' || cookie.substring(cookie.length - 1,cookie.length) !== '}'){
        let expires = new Date();
        expires.setTime(expires.getTime() - 1000);     //当前时间减去一秒,相当于立即过期(可以增减)
        document.cookie = "MU_ID=;path=/;expires=" + expires.toUTCString() + "";
        this.$store.commit(LOGIN,false)
        return;
      }

      this.$store.commit(LOGIN,true)
    }
  },
  computed: {
    dataChange(){
      this.songs = this.$store.state.musicList
      this.backgroundPage()
      return this.songs
    }
  }
}
</script>

<style>
#player {
  width: 100%;
  height: 100%;
}

#page-black {
  min-width: 1000px;
  overflow: hidden;
  width: 100%;
  height: 100%;
  display: flex;
  flex: 1;
  flex-direction: column;
}

#background {
  position: fixed;
  -webkit-filter: blur(30px);
  filter: blur(30px);
  z-index: -1;
  width: 100%;
  height: 100%;
  background-repeat: no-repeat;
  background-size: cover;
}

#back-color {
  background-color: #1b1b1b;
  opacity: 0.85;
  z-index: 10;
  width: 100%;
  height: 100%;
  position: fixed;
}

.el-link--inner {
  font-size: 16px;
  display: flex;
  align-items: center;
}
</style>
