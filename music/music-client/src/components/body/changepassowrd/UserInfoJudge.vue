<template>
  <div id="send">
    <h2>找回密码</h2>
    <p class="tips">验证码将发送至您的注册邮箱之中</p>
    <label for="RetrievePassword"></label>
    <!-- 注册邮箱信息 -->
    <input type="text"
           id="RetrievePassword"
           placeholder="注册邮箱"
           @blur="inputBlur($event,'请输入您的注册邮箱','return')"
           @focus="inputFocus($event,'注册邮箱','return')"
           v-model.lazy.trim="userEmail"/>
    <button @click="send()">发送邮件</button>
    <p class="returnIndex" @click="returnIndex()">返回首页</p>
  </div>
</template>

<script>
import {retrieve} from "@network/http";
import {errorMessage, successMessage} from "@/plugins/message";

export default {
  name: "UserInfoJudge",
  props: {
    isEmail: Boolean,
    email: String
  },
  data(){
    return {
      userEmail: this.email
    }
  },
  methods: {
    returnIndex() {
      this.$router.replace("/index")
    },
    // 发送邮件
    send() {
      if(!this.email){
        return;
      }
      retrieve.sendEmail(this.userEmail).then(success => {
        console.log(111)
        if(success.code === 0){
          return new Promise((resolve, reject) => {
            reject(success.message);
          })
        }
        successMessage(success.data.message)
      },error => {errorMessage(error.message)})
      .catch(error => {errorMessage(error)})
    },
    inputBlur($event,data,type){
      this.$emit('userEmail',this.userEmail)
        this.$emit('inputBlur',$event,data,type)
    },
    inputFocus($event,data,type){
      this.$emit('inputFocus',$event,data,type)
    }
  }
}
</script>

<style scoped>
#send{
  width: 350px;
  height: 500px;
  margin-top: 80px;
}

.tips{
  color: #888;
  font-size: 15px;
}

#send > input{
  width: 100%;
  height: 40px;
  margin-top: 30px;
  border: none;
  outline: inherit;
  box-sizing: border-box;
  border-bottom: 1px solid #ccc;
  padding: 5px;
  font-size: 16px;
  background: none;
}

#send > button{
  width: 100%;
  margin-top: 35px;
  height: 40px;
  border: none;
  outline: inherit;
  border-radius: 3px;
  background: #0084ff;
  color: white;
  cursor: pointer;
  font-weight: 700;
  font-size: 14px;
}

#send > button:hover{
  background: #0471d7;
}

.returnIndex{
  width: 100%;
  padding: 5px;
  height: 30px;
  box-sizing: border-box;
  text-align: center;
  color: cadetblue;
  cursor: pointer;
  font-size: 15px;
}

.returnIndex:hover{
  color: #538284;
}

.input-error::placeholder{
  color: red;
}
.input-error::-moz-placeholder{
  color: red;
}
.input-error:-moz-placeholder{
  color: red;
}
.input-error::-ms-input-placeholder{
  color: red;
}
</style>
