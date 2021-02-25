<template>
  <!-- 歌曲播放进度条 -->
  <div id="progress">
    <div id="roll">
      <div id="music-info">
        <span id="music-name">{{ names }}</span>
        <span id="music-length">{{ currentLength }} / {{ allLength() }}</span>
      </div>
      <div id="music-roll">
        <audio-data @changeLength="changeLength"></audio-data>
        <el-slider v-model="musicLength" class="setting" :show-tooltip="false" :max="maxMusicLength" @change="lengthChange"></el-slider>
      </div>
    </div>
  </div>
</template>


<script>
import Map from "@/plugins/map";
import AudioData from "@components/AudioData";
import {LENGTH} from "@store/constant";

export default {
  name: "MusicProgress",
  components: {AudioData},
  data() {
    return {
      musicLength: 0,
      maxMusicLength: 100
    }
  },
  methods: {
    allLength() {
      let map = Map.getInstance()
      let index = this.$store.state.playIndex
      if (index === '') {
        return '00:00'
      }
      let data = map.ArrayDataByValue('music-list', 'songId', index)
      this.maxMusicLength = data.songLength
      return this.format(data.songLength)
    },
    format(length) {
      let minute = Number.parseInt((length / 60).toString())
      let second = length - minute * 60
      let hour;
      if (minute >= 60) {
        hour = Number.parseInt((minute / 60).toString())
        minute = hour - hour * 60
      } else {
        hour = 0
      }
      return (hour > 0 ? (hour >= 10 ? hour : "0" + hour + ':') : '') + (minute >= 10 ? minute : '0' + minute) + ':' + (second >= 10 ? second : '0' + second)
    },

    lengthChange(value){
      this.$store.commit(LENGTH,value)
    },
    changeLength(value){
      this.musicLength = value
    }
  },
  computed: {
    names() {
      let map = Map.getInstance()
      let index = this.$store.state.playIndex
      if (index === '') {
        return '暂无'
      }
      let data = map.ArrayDataByValue('music-list', 'songId', index)

      let author = '';
      if (data.author.length === 1) {
        author = data.author[0].singerName
      } else {
        for (let result = 0; result < data.author.length; ++result) {
          if (result === 0) {
            author = data.author[result].singerName
          } else {
            author = author + "/" + data.author[result].singerName
          }

        }
      }
      return data['songName'] + "-" + author
    },
    currentLength() {
      if(this.musicLength < 0){
        return '00:00'
      }
      if (this.maxMusicLength > 3600 && this.musicLength === 0) {
        return '00:00:00'
      }
      if (this.maxMusicLength < 3600 && this.musicLength === 0) {
        return '00:00'
      }
      return this.format(this.musicLength)
    }
  }
}
</script>

<style scoped>
#progress {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 100%;
  width: 100%;
  min-height: 90px;
}

#roll {
  width: 100%;
  height: 90%;
  display: flex;
  margin: 0 20px;
  flex-direction: column;
}

#music-info {
  width: 100%;
  height: 40%;
  display: flex;
  position: relative;
  align-items: center;
}

#music-roll {
  width: 100%;
  height: 60%;
}

#music-name, #music-length {
  padding: 5px;
  color: #e0e0e0;
}

#music-name {
  width: 50%;
  min-width: 110px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  -webkit-line-clamp: 1;
  -webkit-box-orient: vertical;
}

#music-length {
  position: absolute;
  right: 0;
}
</style>

<style>
.el-slider__runway {
  background: #615f5f !important;
  height: 3px !important;
}

.el-slider__button-wrapper {
  top: -16.5px !important;
}

.el-slider__bar {
  height: 3px !important;
  background: white !important;
}

.el-slider__button {
  width: 14px !important;
  height: 14px !important;
  border: none !important;
}

.el-slider__button.hover {
  transform: scale(1) !important;
  cursor: pointer !important;
}

.el-slider__button-wrapper:hover {
  cursor: pointer !important;
}
</style>