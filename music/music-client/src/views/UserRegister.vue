<template>
  <div id="register">
    <div class="registerInfo">
      <!-- 顶层 -->
      <login-and-register>
        <template #form-input>
          <!-- form -->
          <form-input @give="give">
            <template #input-data>
              <!-- 验证码 -->
              <input-print input_type="text" input_id="code" input_placeholder="请输入6位验证码" input_maxLength="6" input_error-message="请输入验证码" type="code" @give="give">
                <template #other>
                  <button class="getCode" @click="getCode()">获取验证码</button>
                </template>
              </input-print>
            </template>
            <!-- 按钮 -->
            <template #input-click>
              <div id="btn">
                <el-button class="el-button el-button--success" @click="registerUser()">注册</el-button>
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
import {register} from "@/network/http";
import {errorMessage, successMessage} from "@/plugins/message";
import {getCookie} from "@/plugins/utils";
import {code} from "@/plugins/other";
export default {
  name: "UserRegister",
  components: {
    LoginAndRegister,
    FormInput,
    InputPrint
  },
  data(){
    return {
      code: null,
      username: null,
      password: null,
    }
  },
  mounted() {
    let id = getCookie('MU_ID')
    let cookie = code.decrypt(id)
    if(id !== undefined && cookie.charAt(0) === '{' && cookie.substring(cookie.length - 1,cookie.length) === '}'){
      window.location.replace('/index')
    }

     document.getElementById('user-register').setAttribute("class","operation click")
  },
  methods: {
    give(data){
      if(data.type === 'username'){
        this.username = data.code

        if(this.username !== null && this.username !== ''){
          register.username(this.username).then(success => {},error =>{
            errorMessage(error.message)
          })
        }
      }
      if(data.type === 'password'){
        this.password = data.code
      }
      if(data.type === 'code'){
        this.code = data.code
      }
    },
    getCode(){
      if(this.isUsername() && this.isUsernameRegex()){
        register.code(this.username).then(success => {
          successMessage(success.data.message)
        },error => {errorMessage(error)})
      }
    },
    registerUser(){
      if(this.isUsername() && this.isUsernameRegex() && this.isPassword() && this.isCode()){
        register.register(this.username,this.password,this.code).then(success => {
          successMessage(success.data.message)
        },error => {
          errorMessage(error.message)
        })
      }
    },
    isUsername(){
      if(this.username === null || this.username === ''){
        errorMessage("邮箱不能为空！");
        return false;
      }
      return true;
    },
    isUsernameRegex(){
      let match = /^\w+((-\w)|(\.\w))*@[A-Za-z0-9]+((\.\|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/
      if(!match.test(this.username)){
        errorMessage("邮箱格式错误！");
        return false;
      }
      return true;
    },
    isPassword(){
      if(this.password === null || this.password === ''){
        errorMessage("密码不能为空！");
        return false;
      }
      return true;
    },
    isCode(){
      if(this.code.length !== 6){
        errorMessage("验证码长度为6位！");
        return false;
      }
      return true
    }
  }
}
</script>

<style scoped>
#register{
  width: 100%;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
}

.registerInfo{
  width: 400px;
  height: 610px;
  margin-top: 40px;
}

.getCode{
  border: none;
  background: none;
  position: absolute;
  right: 12px;
  cursor: pointer;
  outline: inherit;
  color: #175199;
  font-size: 14px;
  margin: 0;
  padding: 5px 0;
  bottom: 8px;
}
</style>
