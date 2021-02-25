<template>
<div id="btn">
  <div @mouseenter="enter()" @mouseleave="leave()">
    <i id="el-icon" :class="icon"></i>
    <button id="click-btn" @click="deleteMusic()">{{ text }}</button>
  </div>
</div>
</template>

<script>
import Map from "@/plugins/map";
import {errorMessage,successMessage} from "@/plugins/message";
import {MUSIC_LIST, PLAY_INDEX} from "@store/constant";

export default {
  name: "ClickButton",
  props: {
    icon: {
      type: String,
      default: ''
    },
    checked: {
      type: Array,
      default(){
        return []
      }
    },
    text: {
      type: String,
      require: true
    }
  },
  methods: {
    enter(){
      let btn = document.getElementById("btn");
      let elIcon = document.getElementById("el-icon");
      let clickBtn = document.getElementById("click-btn");
      btn.style.border = "1px solid red";
      elIcon.style.color = "red";
      clickBtn.style.color = "red";

    },
    leave(){
      let btn = document.getElementById("btn");
      let elIcon = document.getElementById("el-icon");
      let clickBtn = document.getElementById("click-btn");
      btn.style.border = "1px solid white";
      elIcon.style.color = "white";
      clickBtn.style.color = "white";
    },
    deleteMusic(){
      let map = Map.getInstance();
      if(!map.removeArrayDataDouble('music-list', 'songId', this.checked)){
        errorMessage('删除失败！')
        return;
      }
      let data = map.get('music-list')
      if(data.length === 0){
        map.addString('audio-id','')
        location.reload()
      }
      successMessage("删除成功！")
    }
  }
}
</script>

<style scoped>
#btn{
  border: 1px solid white;
  border-radius: 50px;
  height: 30px;
}

#btn > div{
  margin: 4px 10px;
}

#click-btn{
  cursor: pointer;
  outline: none;
  background: none;
  border: none;
  color: white;
}

#el-icon{
  color: white;
}
</style>