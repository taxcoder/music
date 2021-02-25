<template>
  <div id="main" @click="hide">
    <!-- 当路由为null时 -->
    <div v-if="this.$route.name === null"></div>
    <!-- 音乐播放器 -->
    <div v-else-if="isMusicPlayer()" id="music-player">
      <keep-alive include="musicPlayer">
        <router-view name="mainArea"></router-view>
      </keep-alive>
    </div>
    <!-- 对账号进行操作时 -->
    <div class="notLogin" v-else-if="isUserOperation()">
      <body-message @isHide="isHide"></body-message>
      <footer-message></footer-message>
    </div>
    <!-- 对歌曲等信息进行操作时 -->
    <div class="addBack" v-else>
      <header-message></header-message>
      <body-message @isHide="isHide"></body-message>
      <footer-message></footer-message>
      <span class="returnTop" @click="returnTop()" v-show="icon"><i class="el-icon-top icon"></i></span>
      <a class="player" @click="openPlayer()" target="_blank" :href="'/player'" v-show="icon" rel="noopener noreferrer" id="open-player"><i class="el-icon-headset icon"></i></a>
    </div>
  </div>
</template>

<script>

import MusicPlayer from "@views/MusicPlayer";
import BodyMessage from '@/components/body/Body'
import FooterMessage from '@/components/footer/Footer'
import HeaderMessage from '@/components/header/Header'
import {PLAYER} from "@/store/constant";
import {errorMessage} from "@/plugins/message";
import {getCookie} from "@/plugins/utils";

export default {
  name: "Main",
  components: {
    BodyMessage,
    HeaderMessage,
    FooterMessage,
    MusicPlayer
  },
  data() {
    return {
      hideData: false,
      show: false,
      notShow: false,
      // 获取setInterval的ID
      place: 0,
      playMusic: [],
      // 返回顶部和打开播放器的控制器
      icon: true
    }
  },
  mounted() {
    this.icon = document.body.scrollWidth >= 1530;
    window.onresize = () => {
      this.icon = document.body.scrollWidth >= 1530;
    }
  },
  methods: {
    openPlayer() {
      let open = document.getElementById('open-player')
      open.setAttribute('href', '/player')
      open.setAttribute('target', '_blank')
    },
    // 判断是否是用户账号的操作
    isUserOperation() {
      return this.$route.name === 'register' || this.$route.name === 'login' || this.$route.name === 'retrievePassword'
    },
    // 判断是否是音乐播放器页面
    isMusicPlayer() {
      return this.$route.name === 'musicPlayer'
    },
    // 返回顶部
    returnTop() {
      if (this.place !== 0) {
        return;
      }
      this.place = setInterval(() => {
        let top = document.documentElement.scrollTop
        document.documentElement.scrollTop = top - 50
        let returnTop = document.documentElement.scrollTop
        if (returnTop <= 0) {
          clearInterval(this.place)
          this.place = 0
        }
      }, 10)
    },
    isHide(data) {
      this.hideData = data
    },
    // 歌手信息点击其他地方隐藏
    hide($event) {
      let element = $event.srcElement.getAttribute('id')
      if (element === 'most' || element === 'triangle-ash' || element === 'triangle-white' || element === 'singerDesc') {
        return;
      }
      let info = document.getElementById('desc-info')
      if (info === null) {
        return;
      }
      if (info.style.display === 'block' && this.hideData === true) {
        info.style.display = 'none'
        this.hideData = false
      }
    },
    isSearch(){
      return this.$route.name === 'search'
    }
  }
}
</script>

<style scoped>
.notLogin {
  background-image: url("~@/assets/img/operation/background.jpg");
  background-repeat: no-repeat;
  background-size: cover;
}

#main {
  height: 100%;
  width: 100%;
}

#music-player {
  width: 100%;
  display: block;
  zoom: 1;
  overflow: hidden;
  height: 100%;
}

.returnTop {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 60px;
  height: 60px;
  position: fixed;
  right: 100px;
  bottom: 81px;
  cursor: pointer;
  background-color: #696969;
}

.player {
  text-decoration: none;
  list-style: none;
  display: flex;
  align-items: center;
  justify-content: center;
  width: 60px;
  height: 60px;
  position: fixed;
  right: 100px;
  bottom: 20px;
  cursor: pointer;
  background-color: #696969;
}

.icon {
  color: white;
  font-size: 25px;
}
</style>
