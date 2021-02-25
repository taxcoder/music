<template>
  <div class="form">
    <div class="login_register">
      <span class="operation" @click="skipLogin()" id="user-login">登录</span>
      <span class="operation" @click="skipRegister()" id="user-register">注册</span>
    </div>

    <!-- 用户邮箱 -->
    <input-print input_placeholder="邮箱" input_id="username" input_type="text" input_max-length="40" input_error-message="请输入邮箱号" type="username" @give="give">
    </input-print>

    <!-- 用户密码 -->
    <input-print input_type="password" input_id="password" input_placeholder="密码" input_max-length="30" input_error-message="请输入密码" type="password" @give="give">
      <template #other>
        <eyes-lock></eyes-lock>
      </template>
    </input-print>

    <slot name="input-data"></slot>

    <div id="operation-other">
        <span class="spanSettings index" @click="skip('index')">返回首页！</span>
        <span class="spanSettings" @click="skip('retrievePassword')">忘记密码？</span>
    </div>

    <slot name="input-click"></slot>

    <div class="third-party">
      <span class="site user_login">社交账号登录</span>
      <div class="site wechat" @click="incomplete()">
        <img src="data:image/svg+xml;base64,PHN2ZyBjbGFzcz0iaWNvbiIgdmlld0JveD0iMCAwIDEwMjQgMTAy
      NCIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIiB3aWR0aD0iMjAwIiBoZWlnaHQ9IjIwMCI+PHBh
      dGggZD0iTTEwMjQgNjE5LjUyYzAtMTQzLjM2LTEzOC4yNC0yNTYtMzA3LjItMjU2cy0zMDcuMiAxMTIuNjQtMzA3
      LjIgMjU2IDEzOC4yNCAyNTYgMzA3LjIgMjU2YzMwLjcyIDAgNjEuNDQtNS4xMiA5Mi4xNi0xMC4yNGw5Ny4yOCA1
      MS4yLTI1LjYtNzYuOGM4Ny4wNC01MS4yIDE0My4zNi0xMjggMTQzLjM2LTIyMC4xNnptLTQxNC43Mi00MC45NmMt
      MzAuNzIgMC01MS4yLTIwLjQ4LTUxLjItNTEuMnMyMC40OC01MS4yIDUxLjItNTEuMiA1MS4yIDIwLjQ4IDUxLjIg
      NTEuMmMwIDI1LjYtMjUuNiA1MS4yLTUxLjIgNTEuMnptMjA5LjkyIDBjLTMwLjcyIDAtNTEuMi0yMC40OC01MS4y
      LTUxLjJzMjAuNDgtNTEuMiA1MS4yLTUxLjIgNTEuMiAyMC40OCA1MS4yIDUxLjJjMCAyNS42LTI1LjYgNTEuMi01
      MS4yIDUxLjJ6IiBmaWxsPSIjNENCRjAwIi8+PHBhdGggZD0iTTM1OC40IDYwOS4yOGMwLTE1OC43MiAxNTMuNi0y
      ODYuNzIgMzQ4LjE2LTI4Ni43MmgxNS4zNkM2ODAuOTYgMTg5LjQ0IDU0Mi43MiA4Ny4wNCAzNjguNjQgODcuMDQg
      MTYzLjg0IDg3LjA0IDAgMjI1LjI4IDAgMzk0LjI0YzAgMTA3LjUyIDY2LjU2IDIwNC44IDE2OC45NiAyNTZsLTMw
      LjcyIDkyLjE2TDI1NiA2ODYuMDhjMzUuODQgMTAuMjQgNzEuNjggMTUuMzYgMTEyLjY0IDE1LjM2aDEwLjI0Yy0x
      NS4zNi0zMC43Mi0yMC40OC02MS40NC0yMC40OC05Mi4xNnptMTM4LjI0LTQxNC43MmMzNS44NCAwIDY2LjU2IDMwL
      jcyIDY2LjU2IDY2LjU2cy0zMC43MiA2Ni41Ni02Ni41NiA2Ni41NmMtMzUuODQtNS4xMi02Ni41Ni0zNS44NC02N
      i41Ni03MS42OHMzMC43Mi02MS40NCA2Ni41Ni02MS40NHptLTI1MC44OCAxMjhjLTM1Ljg0IDAtNjEuNDQtMzAuN
      zItNjEuNDQtNjYuNTZzMzAuNzItNjYuNTYgNjYuNTYtNjYuNTYgNjEuNDQgMzAuNzIgNjEuNDQgNjYuNTYtMzAuN
      zIgNjYuNTYtNjYuNTYgNjYuNTZ6IiBmaWxsPSIjNENCRjAwIi8+PC9zdmc+" alt="weChat" class="iwechat">
        <span>微信</span>
      </div>

      <div class="site qq"  @click="incomplete()">
      <img src="data:image/svg+xml;base64,PHN2ZyBjbGFzcz0iaWNvbiIgdmlld0JveD0iMCAwIDEwMjQgMTAyN
      CIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIiB3aWR0aD0iMjAwIiBoZWlnaHQ9IjIwMCI+PHBhdG
      ggZD0iTTkzMS4zOTYgNTk3Ljg5NmMtMTguNDEyLTQ5LjYxMi05MC41MjgtMTYxLjYyMi05MS41NS0yMTcuODgyaC0
      uNTEyYzAtMy4wNjkuNTExLTYuMTM4LjUxMS05LjIwNkM4MzkuODQ1IDE2Ni4yMjQgNjkzLjA1NiAwIDUxMiAwIDMz
      MC45NDQuNTExIDE4NC4xNTUgMTY2LjIyNCAxODQuMTU1IDM3MS4zMmMwIDMuMDY4LjUxMSA2LjEzNy41MTEgOS4yM
      DVoLS41MTFjLTEuMDIzIDU2LjI2LTcyLjYyNyAxNjguMjctOTEuNTUxIDIxNy44ODJDNzMuNjggNjQ4LjAxOCA2OS
      4wNzcgNjk3LjYzIDc3Ljc3IDczNy41MjRjOC42OTUgMzkuMzgyIDE1Ljg1NiA2My45MzIgMjQuMDM5IDcxLjYwNHM
      xNS44NTUgMTYuMzY3IDQwLjQwNS0xLjUzNGMxOS45NDctMTYuMzY3IDQxLjQyOC00OS42MTIgNDEuNDI4LTQ5LjYx
      MnMxNy45MDEgNTEuNjU4IDU3Ljc5NSA5MS41NTFjLTMxLjE5OSAxNC44MzMtNzYuNzE5IDQ3LjU2Ni03OS43ODcgN
      jkuNTU5LTMuMDcgMjEuNDgxIDQ3LjU2NSAxMDIuODAzIDE2Ni43MzUgMTA0Ljg0OSAxMTkuMTcgMS41MzQgMTU4Lj
      U1My00OS42MTIgMTYyLjY0NC01My43MDMgMTEuMjUyLTExLjc2NCAyMC40NTktMTguOTI0IDIwLjQ1OS0xOC45MjR
      zOS43MTcgNy4xNiAyMC40NTggMTguOTI0YzQuMDkyIDQuMDkxIDQzLjQ3NCA1NS43NDkgMTYyLjY0NCA1My43MDMg
      MTE5LjE3LTIuMDQ2IDE2OS44MDQtODMuMzY4IDE2Ni43MzUtMTA0Ljg1LTMuMDY4LTIxLjQ4LTQ4LjU4OC01NC43M
      jUtNzkuNzg3LTY5LjU1OCAzOS44OTQtMzkuMzgyIDU3Ljc5NS05MS41NSA1Ny43OTUtOTEuNTVzMjEuNDgxIDMzLj
      I0NCA0MS40MjggNDkuNjFjMjQuNTUgMTcuMzkgMzIuMjIyIDkuMjA3IDQwLjQwNSAxLjUzNSA4LjE4NC03LjY3MiA
      xNS4zNDQtMzIuMjIyIDI0LjAzOS03MS42MDQgOS43MTctMzkuODk0IDQuNjAzLTkwLjAxNy0xMy44MS0xMzkuNjI4
      eiIgZmlsbD0iIzM2OUJDRSIvPjwvc3ZnPg==" alt="qq" class="iqq">
      <span>QQ</span>
      </div>
    </div>

  </div>
</template>

<script>
import EyesLock from "@/components/comm/user/Eyes";
import {infoMessage} from '@/plugins/message'
import InputPrint from "@/components/comm/user/InputPrint";
export default {
  name: "FormInput",
  components: {
    InputPrint,
    EyesLock
  },
  data(){
    return {
      isShow: false,
    }
  },
  methods: {
    incomplete(){
      infoMessage("敬请期待！");
    },
    skipLogin(){
      if(this.$route.name === 'register'){
        this.$router.replace("/login");
      }
    },
    skipRegister(){
      if(this.$route.name === 'login'){
        this.$router.replace("/register");
      }
    },
    skip(page){
      this.$router.replace('/'+page)
    },
    give(data){
      this.$emit('give',data)
    }
  }
}
</script>

<style scoped>
.form{
  width: 100%;
  height: 400px;
  background: white;
  border-radius: 3px;
  box-sizing: border-box;
  box-shadow: 0 0 10px #ccc;
}

.login_register{
  padding: 15px;
  margin-top: 10px;
}

.el-button{
  width: 92%;
  font-size: 16px;
  right: 15px;
  font-weight: 700;
  position: absolute;
}

.click{
  font-weight: 700;
  color: #8a8aff;
  position: relative;
}

.click:after{
  content: "";
  position: absolute;
  width: 35px;
  height: 3px;
  background: #8a8aff;
  bottom: -4px;
  left: 15px;
}

.operation{
  font-size: 18px;
  margin-top: 10px;
  padding: 5px 15px;
  cursor: pointer;
}

#operation-other{
  width: 100%;
  height: 30px;
  margin-top: 10px;
  position: relative;
}

.spanSettings{
  color: #ee3838;
  font-size: 14px;
  position: absolute;
  cursor: pointer;
  right: 0;
  bottom: 0;
  padding: 5px;
}

.index{
  left: 15px;
  right: auto;
}

#btn{
  width: 100%;
  height: 50px;
  margin-top: 10px;
  display: flex;
  justify-content: center;
  align-items: center;
  position: relative;
}

.third-party{
  margin-top: 10px;
  width: 100%;
  height: 50px;
  position: relative;
}

.site{
  position: absolute;
  font-size: 15px;
  color: #888;
  line-height: 50px;
}

.user_login{
  left: 17px;
}

.iwechat{
  width: 22px;
  right: 150px;
  top: 14px;
  vertical-align: text-top;
  margin: -2px 5px 0 0;
  padding: 0;
}

.iqq{
  width: 20px;
  right: 90px;
  top: 14px;
  vertical-align: text-top;
  margin: -2px 5px 0 0;
  padding: 0;
}

.qq{
  right: 20px;
  cursor: pointer;
}

.wechat{
  right: 85px;
  cursor: pointer;
}
</style>
