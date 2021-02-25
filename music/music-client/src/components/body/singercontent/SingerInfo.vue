<template>
  <div class="info">
    <div class="head">
      <img :src="singerData.singerHeadImage" alt="头像">
    </div>
    <div class="desc">
      <p class="name">{{singerData.singerName}}</p>
      <p class="singerDesc">{{ desc(singerData.singerDesc) }}</p>
      <button @click="show" id="most">查看更多</button>
      <div class="desc-info" id="desc-info">
        <div class="triangle" id="triangle-ash"></div>
        <div class="triangle-white" id="triangle-white"></div>
        <div class="info" id="singerDesc" v-html="singerData.singerDesc"></div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "SingerInfo",
  props: {
    singerData: {
      type: Object,
      require: true
    }
  },
  methods: {
    desc(singerDesc){
      if(typeof singerDesc === 'string'){
        return singerDesc.toString().replaceAll("<br/>","\n").replaceAll("&emsp;","\t")
      }
      return '暂无简介'
    },
    show(){
      document.getElementById('desc-info').style.display = 'block'
      this.$emit('isHide',true)
    }
  }
}
</script>

<style scoped>
.info{
  width: 100%;
  height: 100%;
}

.head{
  width: 100%;
  height: 60%;
  display: flex;
  align-items: flex-end;
  justify-content: center;
}

.head > img{
  width: 200px;
  height: 200px;
  border-radius: 50%;
  box-sizing: border-box;
  box-shadow: 0 0 10px #ccc;
}

.desc{
  width: 100%;
  position: relative;
  height: 40%;
}

.desc > p{
  margin: 0;
  width: 100%;

}

.name{
  margin: 20px 0!important;
  text-align: center;
  font-size: 28px;
  font-weight: 700;
}

.singerDesc{
  font: 16px Microsoft YaHei,tahoma,arial,Hiragino Sans GB,\\5b8b\4f53,sans-serif;
  margin: 0;
  width: 100%;
  cursor: pointer;
  box-sizing: border-box;
  overflow: hidden;
  text-overflow: ellipsis;
  -o-text-overflow: ellipsis;
  white-space: nowrap;
  word-break: break-all;
}

.desc > button{
  position: absolute;
  right: 0;
  border: none;
  background: #21b34a;
  color: white;
  outline: inherit;
  width: 75px;
  height: 30px;
  font-weight: 700;
  margin-top: 10px;
  border-radius: 3px;
  cursor: pointer;
}

.desc-info{
  display: none;
  position: absolute;
  width: 500px;
  height: 400px;
  background-color: white;
  top: 145px;
  z-index: 29;
  box-shadow: 0 0 5px #888;
  box-sizing: border-box;
  right: 0;
}

.triangle{
  width: 0;
  position: absolute;
  transform: rotate(180deg);
  right: 20px;
  border: 12px solid transparent;
  border-top-color: #e5e5e5;
  top: -25px;
}

.triangle-white{
  width: 0;
  position: absolute;
  transform: rotate(180deg);
  right: 20px;
  border: 12px solid transparent;
  border-top-color: white;
  top: -22px;
}

.desc-info > .info{
  font: 15px Microsoft YaHei,tahoma,arial,Hiragino Sans GB,\\5b8b\4f53,sans-serif;
  width: 100%;
  height: 100%;
  padding: 20px;
  box-sizing: border-box;
}
</style>
