<template>
<div id="body" :style="isNotLoginShow()">
  <keep-alive exclude="UserLogin,UserRegister,Singer,SongSheet,musicPlayer" include="index,MyMusic">
    <router-view name="mainArea" @isHide="isHide"></router-view>
  </keep-alive>
</div>
</template>

<script>
export default {
  name: "Body",
  methods:{
    isNotLoginShow(){
      if(this.$route.name === 'login' || this.$route.name === 'register' || this.$route.name === 'retrievePassword'){
        return {height: '870px'}
      }
      return {}
    },
    isHide(data){
      this.$emit('isHide',data)
    }
  }
}
</script>

<style scoped>
#body{
  width: 100%;
  min-width: 1250px;
  min-height: 760px;
}
</style>
