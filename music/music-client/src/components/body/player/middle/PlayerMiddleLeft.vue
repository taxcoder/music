<template>
  <!-- 信息选择(歌曲) -->
  <div class="player-info site">
    <div class="info-choose">
      <div class="opera" @click="choice">
        <p class="player-active-icon-settings">
          <img :src="images" alt="选中标识" class="active-img">
          <span class="active-info">播放列表</span>
        </p>
      </div>
      <div class="opera" @click="choice">
        <p class="player-active-icon-settings">
          <img :src="images" alt="选中标识" class="active-img icon-hidden" id="sheet">
          <span>我的歌单</span>
        </p>
      </div>
      <div class="music-sheet">
        <ul>
          <li class="li-active">我喜欢</li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
import {infoMessage} from "@/plugins/message";

export default {
  name: "PlayerMiddleLeft",
  props: {
    images: {
      type: String,
      require: true
    },
  },
  methods: {
    choice($event) {
      infoMessage("敬请期待！")
      // let settings = document.getElementsByClassName('player-active-icon-settings');
      // for (const setting of settings) {
      //   setting.firstElementChild.setAttribute("class", "active-img icon-hidden")
      //   setting.firstElementChild.nextElementSibling.setAttribute("class", "")
      // }
      // let child = $event.currentTarget.firstElementChild;
      // child.firstElementChild.setAttribute("class", "active-img")
      // child.firstElementChild.nextElementSibling.setAttribute("class", "active-info");
      // this.isShow();
    },
    isShow(){
      let sheet = document.getElementById('sheet');
      if(sheet.getAttribute("class").indexOf("icon-hidden") === -1){
        document.getElementsByClassName('music-sheet')[0].style.display = "inline-block"
        return;
      }
      document.getElementsByClassName('music-sheet')[0].style.display = "none"
    }
  }
}
</script>

<style scoped>
#player-middle .player-info {
  width: 13%;
  height: 100%;
  min-width: 267px;
}

#player-middle .player-info .info-choose {
  width: 100%;
  height: 85%;
  margin-top: 80px;
}

#player-middle .player-info .info-choose .opera {
  width: 55%;
  margin: auto;
}

#player-middle .player-info .info-choose .opera > p {
  display: flex;
  align-items: center;
  justify-content: flex-end;
  width: 100%;
  height: 50px;
  text-align: center;
  margin: 15px 0;
  color: #e0e0e0;
  cursor: pointer;
  font: 19px/1.5 Microsoft YaHei, taho8a, arial, Hiragino Sans GB, \\5b8b\4f53, sans-serif;
}

#player-middle .player-info .info-choose .opera p > span {
  margin-right: 20px;
}

.active-img {
  width: 30px;
  height: 60px;
}

.active-info {
  color: #ff2a72;
  font-weight: 700;
}

.li-active{
  color: red!important;
}
.site{
  display: inline-block;
}

.icon-hidden {
  display: none;
}

.music-sheet{
  display: none;
  width: 103px;
  height: 100px;
  border-top: 1px solid red;
  margin-left: 84px;
  margin-top: -20px;
}

.music-sheet ul{
  padding-left: 28px;
  width: 100%;
}

.music-sheet ul > li{
  color: white;
  list-style: none;
  margin: 10px 0;
  cursor: pointer;
  box-sizing: border-box;
  white-space: nowrap;
  word-break: break-all;
  overflow: hidden;
  text-overflow: ellipsis;
  -o-text-overflow: ellipsis;
}
</style>