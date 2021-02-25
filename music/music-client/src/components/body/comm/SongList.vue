<template>
  <div class="songs">
    <div class="only" v-if=" hidden===false ?false: clear!==false">
      <h2>热门单曲</h2>
      <span>共 {{ allSong }} 首</span>
    </div>
    <ul v-if="lists.length !== 0">
      <li>
        <span style="max-width: 130px" :style="!show(1530)?{'width':'20%'}:''" class="song" :class="player">
          <input type="checkbox" class="input" @click="allChecked('input')"/>
        </span>
        <span style="max-width: 360px" class="song" :class="player" :style="!show(1530)?{'width':'50%'}:''">歌名</span>
        <span style="max-width: 240px" class="song" :class="player">歌手</span>
        <span style="max-width: 240px" class="song" :class="player">专辑</span>
        <span style="max-width: 240px" class="song" :class="player" v-show="show(1100) ">时长</span>
      </li>
    </ul>
    <ul id="musics" v-if="lists.length !== 0">
      <li class="checked"
          @mouseleave="leave"
          @mouseenter="enter"
          v-for="(music,index) in lists"
          :key="music.songId"
          :index="index"
          :class="{'playerActive':active(music.songId)}"
          @dblclick="playMusic(music)">
        <span style="max-width: 130px" :style="!show(1530)?{'width':'20%'}:''">
          <input type="checkbox" class="input check" :value="music" v-model="checked" @change="top()"/>
        </span>
        <span class="index" :class="player" v-show="show(1530)" v-if="active(music.songId)">
          <i class="el-icon-video-play" v-show="isPlay()"></i>
          <i class="el-icon-video-pause" v-show="!isPlay()"></i>
        </span>
        <span class="index" :class="player" v-show="show(1530)" v-else>
          {{ number(length, index + 1) }}
        </span>
        <span style="max-width: 360px" class="song" :class="player"
              :style="!show(1530)?{'width':'50%'}:''">{{ music.songName }}</span>
        <span style="max-width: 240px" class="song" :class="player">{{ singerName(music.author) }}</span>
        <span style="max-width: 240px" class="song" :class="player">{{ albumNameData(music.album) }}</span>
        <span style="max-width: 240px" class="song" :class="player" v-show="show(1100)">{{
            musicLengthData(music.songLength)
          }}</span>
        <div class="ico" style="max-width: 225px" v-if="isShow">
          <a href="javascript:;" @click="down(music)"><i class="el-icon-download"></i></a>
          <a href="javascript:;" @click="incomplete()"><i class="el-icon-circle-plus-outline"></i></a>
          <svg aria-hidden="true" class="aixin" v-show="!isLikeShow(music.songId)" @click="like(false,music.songId)">
            <use
                xlink:href="#icon-aixin_shixin"></use>
          </svg>
          <svg aria-hidden="true" class="aixin" v-show="isLikeShow(music.songId)" @click="like(true,music.songId)">
            <use
                xlink:href="#icon-shoucangaixinxin-494949"></use>
          </svg>
        </div>
        <div class="ico" style="max-width: 225px" v-else>
          <a href="javascript:;" @click="incomplete()"><i class="el-icon-circle-plus-outline" :style="color"
                                                        style="margin-left: 15px"></i></a>
          <a href="javascript:;" @click="removeMusic(music)"><i class="el-icon-delete" :style="color"></i></a>
        </div>
      </li>
    </ul>
    <ul v-else style="font-size: 16px">抱歉,未查询到歌曲<span class="keyword">{{ keyword }}</span></ul>
    <div class="operation" v-if="hidden">
      <span @click="allChecked(songs,'click')">全选</span>
      <span><button class="play" @click="play()" :class="{'notPlay':this.checked.length === 0}"><img
          src="~@/assets/img/info/play.svg" alt="播放">播放选中按钮</button></span>
    </div>
  </div>
</template>

<script>
import {Message} from 'element-ui'
import {LENGTH, LOGIN, MUSIC_LENGTH, MUSIC_LIST, PLAY, PLAY_INDEX} from "@/store/constant";
import Map from "@/plugins/map";
import {errorMessage, infoMessage, successMessage, warningMessage} from "@/plugins/message";
import {address, operationMusic, download} from "@network/http";
import {getCookie} from "@/plugins/utils";
import {code} from "@/plugins/other";

export default {
  name: "SongList",
  props: {
    keyword: {
      type: String,
      default: ''
    },
    length: {
      type: Number,
      default: 0
    },
    allSong: {
      type: Number,
      default: 0
    },
    size: {
      type: Number,
      default: 1
    },
    hidden: {
      type: Boolean,
      default: true
    },
    clear: {
      type: Boolean,
      default: true
    },
    player: String,
    hoverColor: String,
    isShow: {
      type: Boolean,
      default: true
    },
    musics: {
      type: Object,
      require: true
    },
    songs: {
      type: Array,
      require: true
    },
    widthSize: {
      type: Number,
      default: 0
    },
    iconColor: {
      type: String,
      default: ''
    }
  },
  data() {
    return {
      checked: [],
      datas: [],
      isLike: false,
      isCheck: false,
      likeLists: [],
      login: this.$store.state.login,
      addLikeDialogVisible: false,
      removeLikeDialogVisible: false,
      color: {color: this.iconColor, "font-size": "18px"},
    }
  },
  mounted() {
    let map = Map.getInstance()
    let list = map.get('music-list')
    if(list === null || list === 'string'){
      map.addString('music-list',JSON.stringify([]))
    }
  },
  methods: {
    incomplete() {
      infoMessage("敬请期待！");
    },
    isPlay() {
      return !this.$store.state.play
    },
    active(songId) {
      return this.$route.name === 'musicPlayer' && songId === this.$store.state.playIndex;
    },
    removeMusic(music) {
      let map = Map.getInstance();
      if (!map.removeArrayData('music-list', 'songId', music.songId)) {
        errorMessage('删除失败！')
        return;
      }
      let data = map.get('music-list')
      if (data.length === 0) {
        map.addString('audio-id', '')
        location.reload()
      } else {
        this.$store.commit(PLAY_INDEX, data[0].songId)
        map.addString('audio-id', data[0].songId)
      }
      successMessage("删除成功！")
      this.$store.commit(MUSIC_LIST, data)
    },
    top() {
      this.$emit('checkboxResult', this.checked)
    },
    musicLengthData(length) {
      let hour;
      let minute = Number.parseInt((length / 60).toString())
      let second = length - (minute * 60)
      if (minute >= 60) {
        hour = Number.parseInt((minute / 60).toString())
        minute = hour * 60 - minute
        return (hour < 10 ? "0" + hour : hour) + ":" + (minute < 10 ? "0" + minute : minute) + ":" + (second < 10 ? "0" + second : second)
      }
      return (minute < 10 ? "0" + minute : minute) + ":" + (second < 10 ? "0" + second : second)
    },
    singerName(author) {
      if (author.length === 0) {
        return "未知歌手"
      }
      if (author.length === 1) {
        return author[0].singerName
      }
      let str = ''
      for (let i = 0; i < author.length; ++i) {
        if (i === author.length - 1) {
          str = str + author[i].singerName
        } else {
          str = str + author[i].singerName + ' / '
        }
      }
      return str
    },
    albumNameData(album) {
      return album.albumName === '暂无专辑' ? '暂无专辑' : "<<" + album.albumName + ">>"
    },
    // 点击了全选按钮，将所有input赋予点击状态或未点击状态
    allChecked(state) {
      let checks;
      if (state === 'click') {
        checks = document.getElementsByClassName('input')
      } else {
        checks = document.getElementsByClassName('check')
      }

      if (this.isCheck === false) {
        for (let check of checks) {
          check.checked = true
        }
        let data = JSON.stringify(this.songs)
        this.checked = JSON.parse(data)
        this.isCheck = true
      } else {
        for (let check of checks) {
          check.checked = false
        }
        this.checked = []
        this.isCheck = false
      }
    },
    // 增加数据(播放选中按钮)
    play() {
      if (this.checked.length === 0) {
        warningMessage("您没有选择任何歌曲！")
        return;
      }
      let data = this.checked
      let map = Map.getInstance();
      if (data.length === 1) {
        if (map.ArrayDataByValue('music-list', 'songId', data[0].songId) !== undefined) {
          errorMessage("音乐已存在！")
          return;
        }
        map.pushArray('music-list', [data[0]], 'songId')
      } else {
        map.pushArray('music-list', data, 'songId')
      }
      this.$store.commit(MUSIC_LIST, map.get('music-list'))
      successMessage('成功加入播放器！')
    },
    down(music) {
      if (!this.$store.state.login) {
        errorMessage("请登录！")
      }
      address.address(music.songId).then(success => {
        let result = success.data

        fetch(result).then(res => res.blob()).then(blob => {
          const a = document.createElement('a')
          document.body.appendChild(a)
          a.style.display = 'none'
          // 使用获取到的blob对象创建的url
          const url = window.URL.createObjectURL(blob)
          a.href = url
          // 指定下载的文件名
          let name = '';
          if(music.author.length === 0){
            name = "未知歌手"
          }else if(music.author.length === 1){
            name =  music.author[0].singerName
          }else{
            for(let data=0;data<music.author.length;++data){
              if(music.author.length -1 === data){
                name = name + data.singerName
              }else{
                name = name + data.singerName + '、'
              }
            }
          }

          a.download = music.songName + '-' +name + '.mp3'
          a.click()
          document.body.removeChild(a)
          // 移除blob对象的url
          window.URL.revokeObjectURL(url)
        }, error => errorMessage("出现异常，下载失败！"))
      })
    },
    // 单条数据增加
    add(music) {
      let map = Map.getInstance();
      let flag = map.pushOnly(music)
      if (flag === false) {
        setTimeout(() => {
          Message.error("音乐已存在！")
        }, 0)
        return;
      }
      this.$store.commit(MUSIC_LIST, map.getAll())
      setTimeout(() => {
        Message.success("已增加音乐播放器！")
      }, 0)
      this.$store.commit(PLAY_INDEX, music.songId)
      this.$store.commit(LENGTH, 0)
      this.$store.commit(LEFT, "-100%")
      this.$store.commit(MUSIC_LENGTH, music.songLength)
      this.$store.commit(PLAY, true)
      this.$store.commit(IS_SHOW, false)
    },
    playMusic(music) {
      let map = Map.getInstance();
      let data = map.get('music-list')
      map.pushArrayTop('music-list', [music], 'songId')
      map.addString('audio-id', music.songId)

      if (this.$route.name === 'musicPlayer') {
        this.$store.commit(LENGTH, 0)
        this.$store.commit(PLAY, false)
        this.$store.commit(PLAY_INDEX, music.songId)
      }
    },
    leave($event) {
      document.getElementsByClassName('ico')[$event.currentTarget.getAttribute('index')].style.display = 'none'
      $event.currentTarget.style.backgroundColor = ''
    },
    enter($event) {
      document.getElementsByClassName('ico')[$event.currentTarget.getAttribute('index')].style.display = 'flex'
      $event.currentTarget.style.backgroundColor = this.hoverColor
    },
    // 序号装换
    number(length, index) {
      length = (this.size - 1) * 20 + length
      index = (this.size - 1) * 20 + index
      if (length < 10) {
        return "0" + index
      }
      if (length < 100) {
        return index < 10 ? "00" + index : "0" + index
      }
      if (length < 1000) {
        return index < 10 ? "000" + index : (index < 100 ? "00" + index : "0" + index)
      }
      if (length < 10000) {
        return index < 10 ? "0000" + index : (index < 100 ? "000" + index : (index < 1000 ? "00" + index : "0" + index))
      }
      if (length < 100000) {
        return index < 10 ? "00000" + index : (index < 100 ? "0000" + index : (index < 1000 ? "000" + index : (index < 10000 ? "00" + index : "0" + index)))
      }
    },
    show(length) {
      return this.widthSize === 0 || this.widthSize > length
    },
    like(data, songId) {
      if (!this.$store.state.login) {
        errorMessage('请登录!')
        return;
      }

      let user = this.isUserLogin()
      if (user === undefined) {
        return;
      }

      if (data) {
        operationMusic.addCollectionSong(songId, JSON.parse(user).userEmail).then(success => {
          successMessage(success.data.data)
          this.likeLists.push(songId)
        }, error => {
          errorMessage(error.message)
          if (error.data !== null) {
            let expires = new Date();
            expires.setTime(expires.getTime() - 1000);     //当前时间减去一秒,相当于立即过期(可以增减)
            document.cookie = "MU_ID=;path=/;expires=" + expires.toUTCString() + "";
            this.$store.commit(LOGIN, false)
          }
        })
      } else {
        operationMusic.deleteCollectionSong(songId, JSON.parse(user).userEmail).then(success => {
          successMessage(success.data.data)
          this.likeLists = this.likeLists.filter(data => data !== songId)
        }, error => {
          errorMessage(error.message)
          if (error.data !== null) {
            let expires = new Date();
            expires.setTime(expires.getTime() - 1000);     //当前时间减去一秒,相当于立即过期(可以增减)
            document.cookie = "MU_ID=;path=/;expires=" + expires.toUTCString() + "";
            this.$store.commit(LOGIN, false)
          }
        })
      }
    },
    isUserLogin() {
      let id = getCookie('MU_ID')
      if (id === undefined) {
        let expires = new Date();
        expires.setTime(expires.getTime() - 1000);     //当前时间减去一秒,相当于立即过期(可以增减)
        document.cookie = "MU_ID=;path=/;expires=" + expires.toUTCString() + "";
        this.$store.commit(LOGIN, false)
        return undefined;
      }

      let cookie = code.decrypt(id)
      if (cookie.charAt(0) !== '{' && cookie.substring(cookie.length - 1, cookie.length) !== '}') {
        let expires = new Date();
        expires.setTime(expires.getTime() - 1000);     //当前时间减去一秒,相当于立即过期(可以增减)
        document.cookie = "MU_ID=;path=/;expires=" + expires.toUTCString() + "";
        this.$store.commit(LOGIN, false)
        return undefined;
      }
      return cookie
    },
    isLikeShow(songId) {
      if (!this.$store.state.login) {
        // 返回true，表示不喜欢成立
        return true;
      }

      return this.likeMusic(songId)
    },
    likeMusic(songId) {
      if (this.likeLists.length === 0) {
        return true;
      }

      let data = this.likeLists.filter(data => data === songId)
      return data.length === 0;

    }
  },
  computed: {
    lists() {
      return this.songs
    },
    isLogin() {
      if (this.$store.state.login) {
        let data = this.isUserLogin();
        if (data === undefined) {
          this.likeLists = []
          return false;
        }

        operationMusic.likeMusic(JSON.parse(data).userEmail).then(success => {
              this.likeLists = success.data.data.map(data => data.songId)
            }, error => {
              errorMessage(error.message)
              if (error.data !== null) {
                let expires = new Date();
                expires.setTime(expires.getTime() - 1000);     //当前时间减去一秒,相当于立即过期(可以增减)
                document.cookie = "MU_ID=;path=/;expires=" + expires.toUTCString() + "";
                this.$store.commit(LOGIN, false)
              }
            }
        )
      } else {
        this.likeLists = []
      }

      return this.$store.state.login
    }
  },
  watch: {
    immediate: true,
    songs(value) {
      this.datas = value
    },
    isLogin(value) {
    }
  }
}
</script>

<style scoped>
.songs {
  width: 100%;
  margin-top: 10px;
}

ul {
  width: 100%;
  box-sizing: border-box;
  margin: 0;
  padding: 0;
}

li {
  position: relative;
  display: flex;
  align-items: center;
  list-style: none;
  height: 60px;
  width: 100%;
}

.input {
  cursor: pointer;
  display: block;
  position: relative;
  width: 16px;
  height: 16px;
  font-size: 14px;
  outline: none;
  border: none;
}

.input::after {
  position: absolute;
  top: 0;
  background-color: #c7c7c7 !important;
  color: #888;
  width: 16px;
  height: 16px;
  display: inline-block;
  visibility: visible;
  text-align: center;
  content: ' ';
}

.input:checked::after {
  width: 16px;
  height: 16px;
  background-color: #c7c7c7;
  content: "✓";
  font-size: 12px;
  font-weight: 900;
  text-align: center;
  color: black;
}

li > span:first-child {
  padding-left: 5px;
}

.song {
  color: #888;
  font: 14px Microsoft YaHei, tahoma, arial, Hiragino Sans GB, \\5b8b\4f53, sans-serif;
}

.ico {
  display: none;
  align-items: center;
  justify-content: flex-end;
  right: 0;
  width: 20%;
  position: absolute;
  padding: 0 20px 0 0;
}

.index {
  position: absolute;
  left: 65px;
  width: 50px;
  color: #757272;
  font-size: 14px;
}

.ico > a:first-child img {
  z-index: 30;
  vertical-align: bottom;
  margin-right: 15px;
}

.ico > a:nth-child(2) img {
  margin-right: 25px;
  z-index: 30;
  width: 22px;
}

.ico > a:last-child img {
  z-index: 30;
  width: 24px;
}

.checked {
  transition: 0.5s all;
}

.checked > span {
  color: #494949;
}

.checked:hover {
  cursor: pointer;
  background-color: #ccc;
}

.operation {
  margin-top: 15px;
  width: 100%;
  height: 50px;
}

.operation > span {
  color: #888;
  font-size: 14px;
  margin: 0 15px;
  cursor: pointer;
}

.play {
  background-color: red;
  border: none;
  outline: inherit;
  height: 40px;
  border-radius: 50px;
  color: white;
  padding: 10px;
  cursor: pointer;
  font-weight: 700 !important;
  font: 14px Microsoft YaHei, tahoma, arial, Hiragino Sans GB, \\5b8b\4f53, sans-serif;
}

.play:hover {
  background-color: #cb0707;
}

.play > img {
  width: 18px;
  vertical-align: bottom;
  margin: 0 5px;
}

.notPlay {
  background-color: #9e9e9e;
}

.notPlay:hover {
  background-color: #9e9e9e;
}

.only {
  height: 60px;
  box-sizing: border-box;
}

.only > h2 {
  display: inline-block;
  font-size: 20px;
}

.only > span {
  transition: 1s all;
  cursor: pointer;
  position: relative;
  color: #888;
  float: right;
  font: 14px Microsoft YaHei, tahoma, arial, Hiragino Sans GB, \\5b8b\4f53, sans-serif;
  line-height: 60px;
}

.player {
  color: #c7c7c7 !important;
}

.el-icon-circle-plus-outline, .el-icon-download, .el-icon-delete {
  font-size: 25px;
  color: #494949;
}

.el-icon-circle-plus-outline {
  margin: 0 15px;
}

::-webkit-scrollbar {
  width: 6px;
  height: 6px;
}

::-webkit-scrollbar-track {
  background: 0 0;
}

/* 滚动条滑块 */
::-webkit-scrollbar-thumb {
  border-radius: 5px;
  background: hsla(0, 0%, 100%, .2);
}

ul li > span {
  width: 100%;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  -webkit-line-clamp: 1;
  -webkit-box-orient: vertical;
}

#musics {
  overflow: auto;
  max-height: 93.5%;
  min-height: 60px;
}

.playerActive > span {
  color: #ff6a6a !important;
}

.el-icon-video-pause, .el-icon-video-play {
  font-size: 18px;
}

.keyword {
  color: red;
}

.aixin {
  width: 23px;
  height: 23px;
  padding-bottom: 1px;
}
</style>
