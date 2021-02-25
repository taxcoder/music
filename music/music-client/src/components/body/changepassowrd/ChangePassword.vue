<template>
  <div id="updatePassword">
    <h2>更换密码</h2>
    <p class="preservation">请保存好您的新密码</p>
    <label for="newPassword"></label>
    <input type="password"
           id="newPassword"
           placeholder="新密码"
           v-model="pwd"
           @blur="inputBlur($event,'请输入您的新密码','password')"
           @focus="inputFocus($event,'新密码','retrievePassword')"/>
    <eyes-lock styles="top:116px;right:-10px" target="newPassword"></eyes-lock>
    <eyes-lock styles="top:178px;right:-10px" target="confirm"></eyes-lock>
    <label for="confirm"></label>
    <input type="password"
           id="confirm"
           placeholder="确认密码"
           v-model="rePwd"
           @blur="inputBlur($event,'请确认您的密码','retrievePassword')"
           @focus="inputFocus($event,'确认密码')"/>
    <button @click="change()">修改</button>
  </div>
</template>

<script>
import EyesLock from "@/components/comm/user/Eyes";
import {retrieve} from "@network/http";
import {errorMessage, successMessage} from "@/plugins/message";
export default {
  name: "ChangePassword",
  components:{
    EyesLock
  },
  props: {
    isPassword: Boolean,
    isRetrievePassword: Boolean,
    retrievePassword: String,
    password: String,
    isChangePassword: Boolean
  },
  data(){
    return {
      pwd: this.password,
      rePwd: this.retrievePassword
    }
  },
  methods: {
    inputBlur($event,data,type){
      this.$emit('inputBlur',$event,data,type);
    },
    inputFocus($event,data,type){
      this.$emit('inputFocus',$event,data,type);
    },
    // 修改密码
    change(){
      this.$emit('passwordChange',this.pwd,this.rePwd)
      setTimeout(()=>{
        if(this.isChangePassword){
          retrieve.changePassword(this.$route.query.email,this.$route.query.check,this.pwd)
          .then(success => {
            successMessage(success.data.message)
            this.$router.replace('/login')
          },error => {errorMessage(error.message)})
        }
      },1000)
    }
  }
}
</script>

<style scoped>
#updatePassword{
  position: relative;
  width: 350px;
  height: 500px;
  margin-top: 80px;
}

#updatePassword > h2{
  margin: 20px 0 0 0;
}

#updatePassword > input{
  width: 100%;
  height: 40px;
  margin-top: 20px;
  border: none;
  outline: inherit;
  box-sizing: border-box;
  border-bottom: 1px solid #ccc;
  padding: 5px;
  font-size: 16px;
  background: none;
}

#updatePassword > button{
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

.preservation{
  margin: 5px 0 10px 0;
  color: #888;
  font-size: 14px;
  width: 100%;
  box-sizing: border-box;
}

.eyes{
  top: -353px!important;
  right: 0;
  display: none;
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
