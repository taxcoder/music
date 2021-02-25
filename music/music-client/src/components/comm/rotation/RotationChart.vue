<template>
<div id="chart">
  <ul id="images-ul">
    <li v-for="(image,index) in images" :key="image.id" :class="{
      'three':index === three && count !== 0,
      'two':index === two && count !== 0,
      'twoBefore':index === two && count === 0,
      'first':index === first && count !== 0
      ,'page':index === first && count === 0
    }">
      <a @click="incomplete()" :title="image.desc" target="_blank">
        <img :src="image.imgAddress" :alt="image.desc">
      </a>
    </li>
  </ul>
  <ul id="round">
    <li v-for="(image,index) in images"
        :key="image.id"
        class="styles"
        :class="{'active':current === index}"
        @click="change(index)"></li>
  </ul>
</div>
</template>
<script>
import {infoMessage} from "@/plugins/message";

export default {
  name: "RotationChart",
  data() {
    return {
      images: [
        {
          'hrefAddress':'#',
          'imgAddress': 'https://www.tanxiangblog.com/rotation/img0044.jpg',
          'desc': '图片1号',
          'id': 1
        },
        {
          'hrefAddress':'https://www.baidu.com',
          'imgAddress': 'https://www.tanxiangblog.com/rotation/img0014.jpg',
          'desc': '图片2号',
          'id': 2
        },
        {
          'hrefAddress':'https://www.baidu.com',
          'imgAddress': 'https://www.tanxiangblog.com/rotation/img0104.jpg',
          'desc': '图片3号',
          'id': 3
        },
        {
          'hrefAddress':'https://www.baidu.com',
          'imgAddress': 'https://www.tanxiangblog.com/rotation/img0114.jpg',
          'desc': '图片4号',
          'id': 4
        },
        {
          'hrefAddress':'https://www.baidu.com',
          'imgAddress': 'https://www.tanxiangblog.com/rotation/img0411.jpg',
          'desc': '图片5号',
          'id': 5
        },
        {
          'hrefAddress':'https://www.baidu.com',
          'imgAddress': 'https://www.tanxiangblog.com/rotation/img0024.jpg',
          'desc': '图片6号',
          'id': 6
        },
        {
          'hrefAddress':'https://www.baidu.com',
          'imgAddress': 'https://www.tanxiangblog.com/rotation/img0041.jpg',
          'desc': '图片7号',
          'id': 7
        }
      ],
      current: 0,
      time: 0,
      count: 0,
      heightStyle: 50,
    }
  },
  mounted() {
    this.time = setInterval(() => {
      if(this.current === this.images.length - 1){
        this.current = 0
      }else{
        this.current ++;
      }

      if(this.count === 0){
        this.count = 1
      }
    },5000)
  },
  methods: {
    incomplete(){
      infoMessage("敬请期待！");
    },
    leftMoves(index){
      clearInterval(this.time);
      this.time = setInterval(() => {
        this.current++;
        if(this.current >= index){
          this.imageLoad();
        }
      },85)
    },
    rightMoves(index){
      clearInterval(this.time);
      this.time = setInterval(() => {
        if(this.current >= this.images.length - 1){
          this.current = 0
          this.leftMoves(index);
        }else{
          this.current++;
        }
      },85)
    },
    change(index){
      if(this.current < index){
        this.leftMoves(index);
      }else{
        if(index === 0){
          this.current = index
          return;
        }

        if(this.current > index){
          this.rightMoves(index);
          return;
        }
        this.imageLoad()
      }
      if(this.count === 0){
        this.count = 1
      }
    },
    imageLoad(){
      clearInterval(this.time);
      this.time = setInterval(() => {
        if(this.current === this.images.length - 1){
          this.current = 0
        }else{
          this.current ++;
        }
      },5000)
    }
  },
  computed: {
    first(){
      return this.current
    },
    two(){
      if(this.current === this.images.length - 1){
        return 0
      }else{
        return this.current + 1
      }
    },
    three(){
      if(this.current === 0){
        return this.images.length - 1;
      }else{
        return this.current - 1
      }
    },
  }

}
</script>

<style scoped>
#chart{
  width: 1200px;
  height: 400px;
  border-radius: 5px;
  position: relative;
}

#chart > ul{
  width: 100%;
  height: 100%;
  box-sizing: border-box;
  border-radius: 5px;
  position: relative;
  margin: 0;
  padding: 0;
}

#chart ul > li{
  top: 11%;
  text-decoration: none;
  list-style: none;
  overflow: hidden;
  border-radius: 5px;
  box-shadow: 0 0 10px #888;
  box-sizing: border-box;
  position: absolute;
  width: 550px;
  height: 310px;
}

a{
  cursor: pointer;
  z-index: 11;
  height: 100%;
  width: 100%;
  padding: 0;
}

img{
  z-index: 10;
  width: inherit;
  height: auto;
  border-radius: 5px;
  box-sizing: border-box;
}

.three{
  z-index: 15;
  animation: small 0.75s linear forwards!important;
}

@keyframes small {
  0%{
    top: 0;
    width: 1050px;
    height: 400px;
  }
  100%{
    top: 11%;
    width: 550px;
    height: 310px;
  }
}

.two{
  animation: forwards update 0.15s linear;
}

.twoBefore{
  right: 0;
}

@keyframes update {
  0%{
    right: 56%;
  }
  100%{
    right: 0;
  }
}

.first{
  animation: big 0.75s linear forwards!important;
}

.page{
  top: 0!important;
  right: 8%;
  width: 1050px!important;
  height: 400px!important;
  z-index: 20;
}

@keyframes big {
  0%{
    z-index: 15;
    top: 11%;
    right: 0;
    width: 550px;
    height: 310px;
  }
  100%{
    z-index: 20;
    top: 0;
    right: 8%;
    width: 1050px;
    height: 400px;
  }
}

#round{
  width: 100%!important;
  height: 30px!important;
  display: flex;
  align-items: center;
  justify-content: center;
  top: 25px;
}

#round > li{
  margin: 0 8px;
  position: inherit!important;
}

.styles{
  cursor: pointer;
  width: 18px!important;
  height: 6px!important;
  box-sizing: border-box;
  background: #ccc;
  box-shadow: none!important;
}

.active{
  background: deeppink;
  border: none;
}
</style>
