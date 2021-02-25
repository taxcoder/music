<template>
  <div class="eye" :style="styles">
    <img src="https://www.tanxiangblog.com/icon/lock.png" alt="lock" v-show="isShow" class="eyes" @click="changeEyes">
    <img src="https://www.tanxiangblog.com/icon/not_lock.png" alt="not_lock" v-show="!isShow" class="eyes" @click="changeEyes">
  </div>
</template>

<script>
export default {
  name: "Eyes",
  data(){
    return {
      isShow: false
    }
  },
  props: {
    styles: String,
    target: String
  },
  methods: {
    changeEyes($event){
      this.isShow = !this.isShow
      let input;
      if(this.$route.name === 'login' || this.$route.name === 'register'){
        input = $event.currentTarget.parentElement.parentElement.getElementsByTagName('label')[0].childNodes[0];
      }
      if(this.$route.name === 'retrievePassword'){
        input = document.getElementById(this.target);
      }
      if(this.isShow === true){
        input.setAttribute("type","text");
      }else{
        input.setAttribute("type","password");
      }
    },
  }
}
</script>

<style scoped>
.eye{
  position: absolute;
  right: 0;
  bottom: 35px;
}

.eyes{
  position: absolute;
  width: 25px;
  right: 12px;
  cursor: pointer;
}
</style>