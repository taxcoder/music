<template>
  <div id="music-lyric">
    <div id="lyric-info">
      <div id="image">
        <img v-lazy="printData" alt="" :key="change">
      </div>
      <div id="music-info" v-show="this.$store.state.playIndex !== ''">
        <span>歌曲名：{{ this.currentPlay.songName }}</span>
        <span>作者：{{ names() }}</span>
        <span>专辑：{{ albumNames()}}</span>
      </div>
      <div id="lyric" v-show="this.$store.state.playIndex !== ''">
        <div id="lyric-roll">
          <div id="roll">
            {{ getLyric( this.currentPlay.lyric) }}
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "PlayerMiddleRight",
  props: {
      currentPlay: {
      type: Object,
      default(){
        return {}
      }
    }
  },
  methods: {
    names(){
      if(this.currentPlay.author === undefined){
        return;
      }

      let author = this.currentPlay.author
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
    albumNames(){
      if(this.currentPlay.author === undefined){
        return;
      }

      return this.currentPlay.album.albumName === '暂无专辑' ? '暂无专辑' : '<<' +this.currentPlay.album.albumName+ '>>'
    },
    getLyric(lyric){
      if(lyric === ''){
        return '暂无歌词'
      }
      return '暂无歌词'
    }
  },
  computed: {
    change(){
      return this.$store.state.playIndex
    },
    printData(){
      if(this.currentPlay.songImages !== undefined){
        return this.currentPlay.songImages
      }
    }
  }
}
</script>

<style scoped>
.site{
  display: inline-block;
}

#music-lyric{
  width: 25%;
  margin: 0 10px;
  height: 100%;
  min-width: 325px;
  display: flex;
  align-items: center;
  justify-content: center;
}

#lyric-info{
  width: 90%;
  height: 95%;
}

#image{
  width: 250px;
  height: 250px;
  margin: 12px auto 0 auto;
}

#image > img{
  width: 200px;
  height: 200px;
  padding: 25px;
}

#music-info{
  display: grid;
  width: 250px;
  height: 90px;
  margin: 0 auto;
}

#music-info > span{
  color: #999;
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 14px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  -webkit-line-clamp: 1;
  -webkit-box-orient: vertical;
}

#lyric{
  margin: 0 auto;
  color: #999;
  padding: 10px 0;
  width: 250px;
  height: 380px;
}

#lyric-roll{
  overflow: hidden;
  position: relative;
  width: 100%;
  height: 95%;
}

#roll{
  position: absolute;
  width: 100%;
  top: 25px;
  height: 100%;
  display: flex;
  justify-content: center;
}
</style>