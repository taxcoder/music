<template>
  <div id="singerContent">
    <data-info-top>
      <template #default>
        <singer-info :singerData="singerData" @isHide="isHide"></singer-info>
      </template>
    </data-info-top>
    <song-list :musics="singerData" :songs="songs" :all-song="allSong" :length="length"></song-list>
    <data-page :size="size()" @changeCurrent="changeCurrent"></data-page>
    <user-comment></user-comment>
  </div>
</template>

<script>
import SingerInfo from "@/components/body/singercontent/SingerInfo";
import UserComment from "@/components/comm/usercomment/UserComment";
import DataPage from "@/components/comm/paging/DataPage";
import SongSheetInfo from "@/components/body/songsheetcontent/SongSheetInfo";
import DataInfoTop from "@/components/body/comm/DataInfoTop";
import SongList from "@/components/body/comm/SongList";
import {singer} from "@/network/http";
import {errorMessage} from "@/plugins/message";
import {splicing} from "@/plugins/splicingPrefix";
import splicingPrefix from "@/plugins/splicingPrefix";

export default {
  name: "SingerContent",
  components: {
    DataInfoTop,
    SongSheetInfo,
    SongList,
    DataPage,
    UserComment,
    SingerInfo
  },
  data() {
    return {
      singerData: {},
      songs: [],
      allSong: 0,
      length: 0
    }
  },
  created() {
    singer.singerInfo(this.$route.params.data, this.$route.query.current).then(success => {
      this.singerData = splicing(success.info.data.data, 'singerHeadImage');
      this.songs = splicingPrefix(success.songs.data.data.list, 'songImages')
      this.allSong = success.songs.data.data.total
    }, error => {
      errorMessage(error.message)
    })
  },
  methods: {
    changeCurrent(index) {
      let query = this.$route.query
      singer.songs(this.$route.params.data, index).then(success => {
        this.songs = success.data.data.list
        document.documentElement.scroll(0, 0)
        this.allSong = success.data.data.total
        this.length = success.data.data.length
        this.$router.push('/singers?' + "current=" + query.current + "&status=" + query.status + "&region=" + query.region + "&letter=" + query.letter)
      }, error => {
        errorMessage(error.message)
      })
    },
    isHide(data) {
      this.$emit('isHide', data)
    },
    size() {
      return this.songs.length === 0 ? 1 : Math.ceil(this.songs.length / 20)
    }
  }
}
</script>

<style scoped>
#singerContent {
  width: 1200px;
  min-width: 1200px;
  margin: 20px auto 50px auto;
  box-sizing: border-box;
  border-radius: 2px;
}
</style>
