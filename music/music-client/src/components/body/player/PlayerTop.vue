<template>
  <div id="player-top">
    <div id="top">
      <div id="website-logo">
        <img src="https://www.tanxiangblog.com/icon/logo.png" alt="logo" @click="hrefUrl()">
        <span class="web-name" @click="hrefUrl()">Music WebSite</span>
      </div>
      <div class="music-search">
        <search-music></search-music>
      </div>
      <div class="user-isLogin">
        <el-link class="login" :underline="false" href="/login" v-if="!this.$store.state.login">
          <i class="el-icon-user-solid" style="font-size: 23px;margin-right: 1px"></i>
          登录
        </el-link>
        <el-link class="login" :underline="false" v-else>
          <div class="round" @click="isLogout()">
            <img :src="userImages" alt="用户头像">
          </div>
        </el-link>
      </div>
    </div>
  </div>
</template>

<script>
import SearchMusic from "@components/comm/header/SearchMusic";
import {getCookie} from "@/plugins/utils";
import {code} from "@/plugins/other";
import {errorMessage, successMessage} from "@/plugins/message";
import {LOGIN} from "@store/constant";
import {user} from "@network/http";

export default {
  name: "PlayerTop",
  components: {
    SearchMusic
  },
  methods: {
    hrefUrl() {
      window.open('/index','_blank')
    },
    isLogout(){
      user.logout().then(success => {
        let expires = new Date();
        expires.setTime(expires.getTime() - 1000);     //当前时间减去一秒,相当于立即过期(可以增减)
        document.cookie = "MU_ID=;path=/;expires=" + expires.toUTCString() + "";
        this.$store.commit(LOGIN,false)
        successMessage("已退出！")
        setTimeout(()=>{
          window.location.reload()
        },1000)
      },error => {
        console.log(error)
      })
    }
  },
  computed: {
    userImages(){
      let id = getCookie('MU_ID')
      if(id === undefined){
        return true;
      }

      let cookie = code.decrypt(id)
      if(cookie.charAt(0) !== '{' || cookie.substring(cookie.length - 1,cookie.length) !== '}'){
        errorMessage('用户信息读取失败，请重新登录！')
        let expires = new Date();
        expires.setTime(expires.getTime() - 1000);     //当前时间减去一秒,相当于立即过期(可以增减)
        document.cookie = "MU_ID=;path=/;expires=" + expires.toUTCString() + "";
        this.$store.commit(LOGIN,false)
        return;
      }

      this.$store.commit(LOGIN,true)
      return process.env['VUE_APP_URL'] + JSON.parse(cookie).userHeadAddress
    }
  }
}
</script>

<style scoped>
#player-top {
  z-index: 11;
  width: 100%;
  min-height: 100px;
  height: 10%;
}

#top{
  position: relative;
  min-width: 1250px;
  display: flex;
  align-items: center;
  height: 100%
}

#website-logo {
  display: flex;
  align-items: center;
  width: 55px;
  height: 55px;
}

#website-logo > img {
  left: 75px;
  top: 10px;
  cursor: pointer;
  position: relative;
  width: 100%;
  height: 100%;
}

.web-name {
  cursor: pointer;
  text-align: center;
  font-size: 16px;
  font-weight: 700;
  position: relative;
  color: red;
  left: 80px;
  top: 10px;
}

.music-search {
  position: absolute;
  right: 150px;
  max-width: 275px;
  min-width: 250px;
  width: 15%;
}

.login {
  background: none;
  outline: 0;
  border: none;
  font-size: 18px;
  color: #ccc !important;
}

.login:hover {
  background: none;
  outline: 0;
  border: none;
  color: #F56C6C !important;
}

.user-isLogin {
  position: absolute;
  right: 50px;
  display: flex;
  height: 40px;
}

.round{
  width: 70px;
  height: 70px;
  background: #e0e0e0;
  border-radius: 50%;
  overflow: hidden;
  box-sizing: border-box;
  box-shadow: 0 0 10px white;
}

.round > img{
  width: 100%;
  height: 100%;
}
</style>

<style>
.el-dialog--center{
  z-index: 20;
}
</style>