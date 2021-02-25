<template>
  <div class="top">
    <div class="songSheetImage">
      <img :src="songSheet.songListImagesAddress" alt="歌单图片">
    </div>
    <div class="songSheetInfo">
      <div class="info">
        <h3>{{ songSheet.songListName }}</h3>
        <div class="author">
          <img src="~@/assets/img/info/head.svg" alt="头像">
          <a href="javascript:;">{{ songSheet.author }}</a>
        </div>
        <div class="play"><span>播放量：{{ temperatureNumber(songSheet.temperature) }}</span></div>
        <div class="tag">
          标签：
          <a href="javascript:;" v-for="type in songSheet.songSheetType"
             :key="type.songSheetTypeId">{{ type.songSheetTypeName }}</a>
        </div>
        <div class="desc">
          <span>简介：</span>
          <p :title="songSheet.songListDesc">{{ songSheet.songListDesc }}</p>
        </div>
        <div class="operation">
          <button class="btn" @click="musicPlay"><img src="~@/assets/img/info/play.svg" alt="播放">全部播放</button>
          <button class="btn" @click="addList"><i class="el-icon-circle-plus-outline"></i>加入列表</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Map from "@/plugins/map";
import {CHECKED, LENGTH, MUSIC_LENGTH, MUSIC_LIST, PLAY_INDEX, PLAY} from "@/store/constant";
import {Message} from "element-ui";
import {address} from "@/network/http";
import {errorMessage, infoMessage} from "@/plugins/message";

export default {
  name: "SongSheetInfo",
  props: {
    songSheet: {
      type: Object,
      require: true
    },
    song: {
      type: Array,
      require: true
    }
  },
  methods: {
    incomplete() {
      infoMessage("敬请期待！");
    },
    temperatureNumber(temperature) {
      if (typeof temperature !== "number") {
        return 0
      }
      if (temperature < 10000) {
        return temperature
      }
      if (temperature < 100000000) {
        return (temperature / 10000).toFixed(2) + "万"
      }
      return (temperature / 100000000).toFixed(2) + "亿"
    },
    addList() {
      let map = Map.getInstance();
      let list = map.get('music-list')
      if(list == null || list === 'string'){
        map.addString('music-list',JSON.stringify([]))
        return;
      }
      let temp = JSON.stringify(this.song)
      map.pushArray('music-list',JSON.parse(temp),'songId')
      this.$store.commit(MUSIC_LIST,map.get('music-list'))
    },
    musicPlay() {
      let map = Map.getInstance();
      let temp = JSON.stringify(this.song)
      map.pushArrayTop('music-list', JSON.parse(temp).reverse(), 'songId')

      this.$store.commit(LENGTH, 0)
      this.$store.commit(PLAY, false)
      let list = map.get('music-list')
      if(list == null || list === 'string'){

      }
      this.$store.commit(MUSIC_LIST,list)
      this.$store.commit(PLAY_INDEX, this.song[0].songId)
      map.addString('audio-id', this.song[0].songId)
    }
  }
}
</script>

<style scoped>
.top {
  width: 100%;
  display: flex;
  height: 100%;
  box-sizing: border-box;
}

.songSheetImage {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 400px;
  height: 100%;
  box-sizing: border-box;
}

.songSheetImage > img {
  width: 85%;
  height: 85%;
  border-radius: 2px;
}

.songSheetInfo {
  display: flex;
  align-items: center;
  justify-content: flex-start;
  width: 800px;
  height: 100%;
  box-sizing: border-box;
}

.info {
  width: 90%;
  height: 85%;
}

.info > h3 {
  font-size: 24px;
  margin: 25px 0 5px 0;
}

.author {
  height: 30px;
  display: flex;
  align-items: center;
}

.author > img {
  width: 18px;
  margin-right: 10px;
}

.author > a {
  text-decoration: none;
  font-size: 14px;
  color: #676464;
  cursor: pointer;
}

.author > a:hover {
  color: #0dc2e0;
}

.play {
  margin-top: 40px;
  font: 15px Microsoft YaHei, tahoma, arial, Hiragino Sans GB, \\5b8b\4f53, sans-serif;
  height: 30px;
}

.tag {
  height: 30px;
  display: flex;
  align-items: center;
  font: 15px Microsoft YaHei, tahoma, arial, Hiragino Sans GB, \\5b8b\4f53, sans-serif;
  font-size: 16px;
}

.tag > a {
  text-decoration: none;
  cursor: pointer;
  margin-right: 10px;
  color: black;
  padding: 5px 0;
  box-sizing: border-box;
}

.tag > a:hover {
  color: #0ea1ba;
}

.desc {
  display: flex;
  align-items: center;
  min-height: 30px;
  word-break: break-all;
  padding: 0 10px 0 0;
  margin-top: 10px;
  box-sizing: border-box;
  font: 15px Microsoft YaHei, tahoma, arial, Hiragino Sans GB, \\5b8b\4f53, sans-serif;
}

.desc > span {
  height: 100%;
  min-width: 50px;
}

.desc > p {
  cursor: pointer;
  max-height: 50px;
  display: inline-block;
  margin: 0;
  overflow: hidden;
  text-overflow: ellipsis;
  box-sizing: border-box;
  -o-text-overflow: ellipsis;
  white-space: nowrap;
}

.operation {
  width: 100%;
  display: flex;
  align-items: center;
  height: 50px;
  margin-top: 25px;
}

.btn {
  padding: 5px 5px 5px 0;
  border: none;
  height: 35px;
  width: 100px;
  outline: inherit;
  background-color: #ff0000;
  color: white;
  cursor: pointer;
  font: 15px Microsoft YaHei, tahoma, arial, Hiragino Sans GB, \\5b8b\4f53, sans-serif;
  margin-right: 20px;
  border-radius: 50px;
}

.btn > img {
  width: 20px;
  vertical-align: bottom;
  margin: 0 3px 0 0;
}

.btn:hover {
  background-color: #cb0707;
}

.el-icon-circle-plus-outline {
  font-size: 19px;
  margin: 0 3px;
  outline: none;
  vertical-align: bottom;
}
</style>
