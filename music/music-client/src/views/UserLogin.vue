<template>
  <div id="login">
    <div class="loginInfo">
      <!-- 顶层 -->
      <login-and-register>
        <template #form-input>
          <!-- form -->
          <form-input ref="form" @give="give">
            <template #input-data>
              <!-- 验证码 -->
              <input-print input_type="text" input_id="code" input_placeholder="请输入4位验证码" input_maxLength="6" input_error-message="请输入验证码" type="code" @give="give">
                <template #other>
                  <div class="identifying" :key="img">
                    <img :src="isShow()" alt="验证码" id="imgCode" @click="newCode()">
                  </div>
                </template>
              </input-print>
            </template>
            <!-- 按钮 -->
            <template #input-click>
              <div id="btn">
                <el-button class="el-button el-button--primary" @click="login()">登录</el-button>
              </div>
            </template>
          </form-input>

        </template>
      </login-and-register>
    </div>
  </div>
</template>

<script>
import InputPrint from "@/components/comm/user/InputPrint";
import FormInput from "@/components/comm/user/FormInput";
import LoginAndRegister from "@/components/comm/user/LoginAndRegister";
import {errorMessage} from "@/plugins/message";
import {user} from "@/network/http";
import {code} from "@/plugins/other";
import {getCookie} from "@/plugins/utils";

export default {
  name: "UserLogin",
  data(){
    return {
      code: null,
      username: null,
      password: null,
      img: null,
      replaceImg: require('@/assets/img/data-error.png')
    }
  },
  components: {
    LoginAndRegister,
    InputPrint,
    FormInput
  },
  mounted() {
    let id = getCookie('MU_ID')
    if(id !== undefined){
      let cookie = code.decrypt(id)
      if(cookie.charAt(0) === '{' && cookie.substring(cookie.length - 1,cookie.length) === '}'){
        window.location.replace('/index')
      }
    }

    document.getElementById('user-login').setAttribute("class","operation click")
    user.code(new Date()).then(success => {
      return 'data:image/png;base64,' + btoa(
          new Uint8Array(success.data).reduce((data, byte) => data + String.fromCharCode(byte), ''))
    },error => {}).then(data => this.img = data)
  },
  methods: {
    isShow(){
      if(this.img === null || this.img === ""){
        return this.replaceImg
      }
      return this.img
    },
    give(data){
      if(data.type === 'username'){
        this.username = data.code
      }else if(data.type === 'password'){
        this.password = data.code
      }else{
        this.code = data.code
      }
    },
    isUsername(){
      if(this.username === null || this.username === '' || this.username === undefined){
        errorMessage("请输入用户名！")
        return false;
      }
      return true
    },
    isPassword(){
      if(this.password === null || this.password === '' || this.password === undefined){
        errorMessage("请输入密码！")
        return false;
      }
      if(this.password.length < 8 || this.password.length > 16){
        errorMessage("密码的长度应在8至16位之间！")
        return false;
      }
      return true
    },
    isCode(){
      if(this.code === null || this.code === '' || this.code === undefined){
        errorMessage("请输入验证码！")
        return false;
      }
      if(this.code.length !== 4){
        errorMessage("验证码长度为4！")
        return false;
      }
      return true
    },
    login(){
      if(this.isUsername() && this.isPassword() && this.isCode()){
        user.login(this.username,this.password,this.code).then(success => {
          if(success.data.code === 0){
            return new Promise((resolve, reject) => {
              reject(success.data.message)
            })
          }else{
            let Json = JSON.parse(success.data.data)
            let userArray = JSON.stringify({"userId": Json.userId,"userEmail": Json.userEmail,"userName": Json.userName,"userHeadAddress": Json.userHeadAddress})
            document.cookie = 'MU_ID='+ code.encryption(userArray) +";path=/;"
            window.location.replace('/index')
          }
        },error => {errorMessage(error)})
        .catch(error => {errorMessage(error)})
      }
    },
    newCode(){
      user.code(new Date()).then(success => {
        return 'data:image/png;base64,' + btoa(new Uint8Array(success.data).reduce((data, byte) => data + String.fromCharCode(byte), ''))
      },error => {}).then(data => this.img = data)
    }
  }
}
</script>

<style scoped>
#login{
  width: 100%;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
}

.loginInfo{
  width: 400px;
  height: 610px;
  margin-top: 40px;
}

.identifying{
  cursor: pointer;
  position: absolute;
  width: 130px;
  height: 50px;
  right: 10px;
}

#imgCode{
  box-sizing: border-box;
  width: 100%;
  height: 100%;
}
</style>
