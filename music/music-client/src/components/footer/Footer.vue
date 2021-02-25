<template>
<div :class="{'footer':!isRouter(),'footer-user':isRouter()}">
  <div class="board">
    <p>Copyright ©2020 tanxiang. All Rights Reserved.</p>
    <p>版权所有本人所有</p>
  </div>
</div>
</template>

<script>
export default {
  name: "Footer",
  methods: {
    isRouter(){
      return this.$route.name === 'register' || this.$route.name === 'login' || this.$route.name === 'retrievePassword' || this.$route.name ===null
    }
  }
}
</script>

<style scoped>
.footer{
  width: 100%;
  min-width: 1250px;
  height: 80px;
  background: #333;
  display: flex;
  align-items: center;
  justify-content: center;
}

.board{
  width: 50%;
  color: #999;
  height: 50px;
  display: flex;
  margin: 0 auto;
  flex-direction: column;
  justify-content: flex-end;
  align-items: center;
}

.board > p{
  font-family: poppin,Tahoma,Arial,\5FAE\8F6F\96C5\9ED1,sans-serif;
  margin: 0 auto;
  text-align: center;
}

.footer-user{
  width: 100%;
  min-width: 1250px;
  height: 125px;
  display: flex;
  align-items: center;
  justify-content: center;
}
</style>
