<template>
<div id="retrieve">
  <!-- 输入个人信息验证 -->
  <user-info-judge v-if="isShow()"
                   :is-email="isEmail()"
                   :email="email"
                   @inputBlur="inputBlur"
                   @userEmail="userEmail"
                   @inputFocus="inputFocus"></user-info-judge>
  <!-- 密码更换 -->
  <change-password v-else
                   :is-change-password="isChangePassword"
                   @passwordChange="passwordChange"
                   :password="password"
                   :retrieve-password="retrievePassword"
                   @isPassword="isPassword()"
                   @isRetrievePassword="isRetrievePassword()"></change-password>
</div>
</template>

<script>
import ChangePassword from "@components/body/changepassowrd/ChangePassword";
import UserInfoJudge from "@components/body/changepassowrd/UserInfoJudge";
import {errorNotification} from "@/plugins/notification";
export default {
  name: "RetrievePassword",
  components: {
    UserInfoJudge,
    ChangePassword
  },
  data() {
    return {
      email: '',
      password: '',
      retrievePassword: '',
      isChangePassword: false
    }
  },
  methods: {
    // 判断查询条件
    isShow(){
      let query = this.$route.query
      return !(
          query.check !== undefined && query.email !== undefined &&
          query.check !== null && query.email !== null &&
          query.check !== '' && query.email !== ''
      );
    },
    // 判断密码是否填写错误
    isPassword(){
      if(this.password === null || this.password.length === 0){
        errorNotification("新密码不能为空！")
        return false;
      }
      let match = /^(?=.*[0-9])(?=.*[a-zA-Z])(.{8,16})$/
      if(!match.test(this.password)){
        errorNotification("密码需在8到16个字符之间,且必须包含数字、字母,区分大小写！")
        return false;
      }
      return true;
    },
    // 判断确认密码是否填写错误
    isRetrievePassword(){
      if(this.retrievePassword === null || this.retrievePassword.length === 0){
        errorNotification("确认密码不能为空！")
        return false;
      }
      if(this.retrievePassword !== this.password){
        errorNotification("确认密码需和新密码一致！")
        return false;
      }
      return true;
    },
    isEmail(){
      if (this.email === null || this.email.length === 0) {
        errorNotification("注册邮箱不能为空！")
        return false;
      }
      let match = /^\w+((-\w)|(\.\w))*@[A-Za-z0-9]+((\.\|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/
      if(!match.test(this.userEmail)){
        errorNotification("邮箱格式不正确！")
        return;
      }
      return true;
    },
    inputBlur($event, data,type) {
      $event.currentTarget.setAttribute("placeholder", data)
      $event.currentTarget.setAttribute("class", "input-error")

      if(!this.isShow()){
        return;
      }
      // 判断是信息验证还是修改密码
      if(type === 'password'){
        this.isPassword()
      }else if(type === 'retrievePassword'){
        this.isRetrievePassword();
      }
    },
    inputFocus($event,data,type) {
      $event.currentTarget.setAttribute("placeholder", data)
      $event.currentTarget.setAttribute("class", "")
    },
    userEmail(email){
      this.email = email
    },
    passwordChange(pwd,rePwd){
      this.password = pwd
      this.retrievePassword = rePwd
      this.changePassword();
    },
    changePassword(){
      this.isChangePassword = this.isPassword() && this.isRetrievePassword()
    }
  }
}
</script>

<style scoped>
#retrieve{
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
}
</style>
