<template>
  <div id="other">
    <!-- 下载 收藏 循环播放 -->
    <div id="operation">
          <span>
              <svg aria-hidden="true" class="refresh" v-show="loop" @click="changeLoop()"><use
                  xlink:href="#icon-xunhuanbofang"></use></svg>
              <svg aria-hidden="true" class="refresh" v-show="!loop" @click="changeLoop()"><use
                  xlink:href="#icon-danquxunhuan"></use></svg>
          </span>
      <span id="heart">
              <svg aria-hidden="true" class="aixin" v-show="isMusicLike" @click="removeLikeDialogVisible = true"><use
                  xlink:href="#icon-aixin_shixin"></use></svg>
              <svg aria-hidden="true" class="aixin-white" v-show="!isMusicLike" @click="addLikeDialogVisible = true"><use
                  xlink:href="#icon-shoucangaixinxin-white"></use></svg>
          </span>
      <span><i class="el-icon-download download" @click="down()"></i></span>
    </div>
    <!-- 音量控制 -->
    <div id="volume">
          <span>
            <i class="sound el-icon-bell" v-show="isVolumeShow" @click="changeVolume()"></i>
            <i class="sound el-icon-close-notification" v-show="!isVolumeShow" @click="changeVolume()"></i>
          </span>
      <span>
               <el-slider v-model="volume" class="volumeSetting" :show-tooltip="false"></el-slider>
          </span>
    </div>

    <el-dialog
        title="提示"
        :visible.sync="addLikeDialogVisible"
        width="20%"
        :modal="false"
        center="center">
      <span>是否将此歌曲加入我喜欢？</span>
      <span slot="footer" class="dialog-footer">
    <el-button @click="addLikeDialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="changeLike(true)">确 定</el-button>
  </span>
    </el-dialog>

    <el-dialog
        title="提示"
        :visible.sync="removeLikeDialogVisible"
        width="20%"
        :modal="false"
        center="center">
      <span>是否将此歌曲移出我喜欢？</span>
      <span slot="footer" class="dialog-footer">
    <el-button @click="removeLikeDialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="changeLike(false)">确 定</el-button>
  </span>
    </el-dialog>
  </div>
</template>

<script>
import {LOGIN, LOOP_MUSIC, VOLUME} from "@store/constant";
import Map from "@/plugins/map";
import {address, operationMusic} from "@network/http";
import {getCookie} from "@/plugins/utils";
import {code} from "@/plugins/other";
import {errorMessage, successMessage} from "@/plugins/message";

export default {
  name: "MusicOther",
  data() {
    return {
      isVolume: true,
      isLike: false,
      loop: true,
      lastTimeVolume: 0,
      volume: 40,
      addLikeDialogVisible: false,
      removeLikeDialogVisible: false
    }
  },
  mounted() {
    let map = Map.getInstance()
    let loop = map.get('loop')
    if (loop !== 'true' && loop !== 'false') {
      map.addString('loop', 'true')
      loop = 'true'
    } else if (loop === 'false') {
      let audio = document.getElementById('audio')
      audio.setAttribute('loop', 'loop')
    }

    this.loop = loop === 'true'
    this.$store.commit(LOOP_MUSIC, this.loop)
  },
  methods: {
    changeLoop() {
      let map = Map.getInstance()
      this.loop = !this.loop
      map.addString('loop', this.loop)
      let audio = document.getElementById('audio')
      if(this.loop){
        audio.removeAttribute('loop')
      }else{
        audio.setAttribute('loop','loop')
      }

    },
    changeLike(data) {
      let cookie = this.isUserLogin()
      if(cookie === undefined){
        return;
      }

      let result = JSON.parse(cookie)
      if (data) {
        this.addLikeDialogVisible = false
        operationMusic.addCollectionSong(this.$store.state.playIndex, result.userEmail).then(success => {
          successMessage(success.data.data)
          this.isLike = true
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
        this.removeLikeDialogVisible = false
        operationMusic.deleteCollectionSong(this.$store.state.playIndex, result.userEmail).then(success => {
          successMessage(success.data.data)
          this.isLike = false
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
    changeVolume() {
      let map = Map.getInstance()
      this.isVolume = !this.isVolume
      if (!this.isVolume) {
        this.lastTimeVolume = this.volume
        this.volume = 0
      } else {
        let temp = this.volume
        this.volume = this.lastTimeVolume
        this.lastTimeVolume = temp
      }
      this.$store.commit(VOLUME, this.volume)
      map.addString('volume', this.volume)
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
    down() {
      if (!this.$store.state.login) {
        errorMessage("请登录！")
        return;
      }
``
      let map = Map.getInstance()

      address.address(this.$store.state.playIndex).then(success => {
        let result = success.data
        let music = this.$store.state.musicList[map.ArrayByValueReturnIndex(this.$store.state.musicList,'songId',this.$store.state.playIndex)]
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
  },
  computed: {
    isVolumeShow() {
      this.isVolume = (Number.parseInt(this.$store.state.volume.toString()) !== 0)
      this.volume = this.$store.state.volume
      return this.isVolume
    },
    isMusicLike() {
      if (!this.$store.state.login) {
        return false
      }

      let data = this.isUserLogin()
      if (data === undefined) {
        return false
      }

      operationMusic.searchMusicIsLike(this.$store.state.playIndex,JSON.parse(data).userEmail).then(
          success => {
            this.isLike = success.data.data
          }, error => {
            errorMessage(error.message)
            if (error.data !== null) {
              let expires = new Date();
              expires.setTime(expires.getTime() - 1000);     //当前时间减去一秒,相当于立即过期(可以增减)
              document.cookie = "MU_ID=;path=/;expires=" + expires.toUTCString() + "";
              this.$store.commit(LOGIN, false)
            }
            this.isLike = false
          })
      return this.isLike
    }
  },
  watch: {
    volume(length) {
      let map = Map.getInstance()
      map.addString('volume', length)
      this.$store.commit(VOLUME, length)
    }
  }
}
</script>

<style scoped>
#other {
  display: flex;
  width: 350px;
  min-width: 350px;
  height: 100%;
  min-height: 90px;
}

#operation {
  height: 100%;
  width: 40%;
  display: grid;
  grid-template-columns: repeat(3, 33.33%);
}

#operation > span {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}

.refresh {
  width: 25px;
  height: 25px;
  cursor: pointer;
}

.aixin {
  width: 29px;
  height: 29px;
  cursor: pointer;
}


.aixin-white {
  width: 25px;
  height: 25px;
  cursor: pointer;
}

.download {
  color: white;
  font-size: 28px;
  cursor: pointer;
}

#volume {
  width: 60%;
  height: 100%;
  display: flex;
  align-items: center;
  padding: 10px;
  box-sizing: border-box;
}

#volume > span:first-child {
  height: 100%;
  width: 20%;
  display: flex;
  align-items: center;
}

#volume > span:last-child {
  height: 100%;
  width: 80%;
  display: grid;
  align-items: center;
  padding: 5px;
  box-sizing: border-box;
}

.sound {
  font-size: 25px;
  cursor: pointer;
  color: white;
  margin-bottom: 2px;
}
</style>
<style>
.volumeSetting div div > div:first-child {
  width: 10px !important;
  height: 10px !important;
}
</style>