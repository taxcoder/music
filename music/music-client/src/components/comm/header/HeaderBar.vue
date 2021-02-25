<template>
  <div id="header-bar" >
    <header-chunk-item :DivWidth="'200px'">
      <template>
        <website-info></website-info>
      </template>
    </header-chunk-item>

    <header-chunk-item>
      <template #default>
        <router-link to="/index" class="message hover" custom v-slot="{ navigate,isActive }">
          <span @click="navigate" @keypress.enter="navigate" :class="[isActive && 'active']" role="link">发现音乐</span>
        </router-link>
      </template>
    </header-chunk-item>

    <header-chunk-item>
      <template #default>
        <router-link to="/singers" class="message hover" custom v-slot="{ navigate,isActive }">
          <span @click="navigate" @keypress.enter="navigate" :class="[isActive && 'active']" role="link">歌手</span>
        </router-link>
      </template>
    </header-chunk-item>

    <header-chunk-item>
      <template #default>
        <router-link to="/songSheets" class="message hover" custom v-slot="{ navigate,isActive }">
          <span @click="navigate" @keypress.enter="navigate" :class="[isActive && 'active']" role="link">歌单</span>
        </router-link>
      </template>
    </header-chunk-item>

    <header-chunk-item style="display: none">
      <template #default>
        <router-link to="/myMusic" class="message hover" custom v-slot="{ navigate,isActive }">
          <span @click="navigate" @keypress.enter="navigate" :class="[isActive && 'active']" role="link">我的音乐</span>
        </router-link>
      </template>
    </header-chunk-item>

    <header-chunk-item :DivWidth="'325px'" :right="200" :place="true">
      <template #default>
        <search-music></search-music>
      </template>
    </header-chunk-item>

    <header-chunk-item :place="true" :right="100" v-if="show()">
      <template #default>
        <router-link to="/login" replace class="message hover" custom v-slot="{ navigate }">
          <span @click="navigate" @keypress.enter="navigate" role="link">登录</span>
        </router-link>
      </template>
    </header-chunk-item>

    <header-chunk-item :place="true" v-if="show()">
      <template #default>
        <router-link to="/register" replace class="message hover" custom v-slot="{ navigate }">
          <span @click="navigate" @keypress.enter="navigate" role="link">注册</span>
        </router-link>
      </template>
    </header-chunk-item>

    <div id="headImage" v-else>
      <div id="roundImg" @click="logout()" :title="'点我注销'">
        <img :src="image()" alt="头像">
      </div>
    </div>

    <el-dialog
        title="提示"
        :visible.sync="centerDialogVisible"
        width="20%"
        center="center">
      <span>是否退出登录</span>
      <span slot="footer" class="dialog-footer">
    <el-button @click="centerDialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="isLogout()">确 定</el-button>
  </span>
    </el-dialog>
  </div>
</template>

<script>
import {getCookie} from "@/plugins/utils";
import WebsiteInfo from "@/components/comm/header/WebsiteInfo";
import SearchMusic from "@/components/comm/header/SearchMusic";
import HeaderChunkItem from '@/components/comm/header/HeaderChunkItem'
import {errorMessage} from "@/plugins/message";
import {user} from "@/network/http";
import {code} from "@/plugins/other";
import {LOGIN} from "@store/constant";

export default {
  name: "HeaderBar",
  components: {
    HeaderChunkItem,
    SearchMusic,
    WebsiteInfo,

  },
  data(){
    return {
      centerDialogVisible: false
    }
  },
  methods: {
    url(page){
      return page.address
    },
    show(){
      let id = getCookie('MU_ID')
      if(id === undefined){
        return true;
      }

      let cookie = code.decrypt(id)
      if(cookie.charAt(0) !== '{' && cookie.substring(cookie.length - 1,cookie.length) !== '}'){
        return true;
      }
      let cookies = JSON.parse(cookie)
      return cookies === undefined || cookies === null || cookies.userEmail === undefined || cookies.userEmail === null
    },
    image(){
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
    },
    logout(){
      this.centerDialogVisible = true
    },
    isLogout(){
      this.centerDialogVisible = false
      user.logout().then(success => {
        let expires = new Date();
        expires.setTime(expires.getTime() - 1000);     //当前时间减去一秒,相当于立即过期(可以增减)
        document.cookie = "MU_ID=;path=/;expires=" + expires.toUTCString() + "";
        this.$store.commit(LOGIN,false)
        window.location.reload()
      },error => {
        console.log(error)
      })
    }
  },
  computed: {
    isLogin(){
      return this.$store.state.login
    }
  },
  watch: {
    isLogin(value){
      if(!value){
        this.isLogout()
      }
    }
  }
}
</script>
<style>
#header-bar{
  width: 1250px;
  height: 100%;
  margin: 0 auto;
  display: flex;
  align-items: center;
  position: relative;
}

.active{
  color: red!important;
  font-weight: 700;
  position: relative;
}

.active:after{
  content: "";
  display: block;
  height: 3px;
  background: red!important;
  width: 15px;
  position: absolute;
  top: 50px;
}

#headImage{
  height: 100%;
  position: absolute;
  right: 0;
  width: 200px;
}

#roundImg{
  width: 80px;
  cursor: pointer;
  height: 80px;
  overflow: hidden;
  position: absolute;
  right: 25px;
  border-radius: 50%;
  bottom: 15px;
  box-sizing: border-box;
  box-shadow: 0 0 10px #ccc;
}

#roundImg > img{
  width: 100%;
  height: 100%;
}

.el-dialog__body{
  text-align: center!important;
}
</style>
