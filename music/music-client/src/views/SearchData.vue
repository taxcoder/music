<template>
  <div id="search">
    <div class="search-change-button">
      <span :class="{'checked':isActive === 'song'}" @click="searchInfo('song')">歌曲</span>
      <span :class="{'checked':isActive === 'singer'}" @click="searchInfo('singer')">歌手</span>
      <span :class="{'checked':isActive === 'album'}" @click="searchInfo('album')">专辑</span>
      <span :class="{'checked':isActive === 'songSheet'}" @click="searchInfo('songSheet')">歌单</span>
    </div>
    <div class="search-content">
      <song-list :songs="isActive === 'song' ? lists : []"
                 v-show="isActive === 'song'"
                 :hidden="lists.length !== 0"
                 :class="{'music-null':lists.length === 0}"
                 :keyword="this.$route.query.keyword"
                 :allSong="allSong"
                 :clear="false"
                 :length="length"
                 :size="currentSize()"></song-list>
      <singer-show v-if="isActive === 'singer'" :list-data="lists" type="singers"></singer-show>
      <span v-show="isActive === 'singer'" class="album">很抱歉,未找到歌手<span style="color: red">{{ $route.query.keyword }}</span></span>
      <span v-show="isActive === 'album'" class="album">很抱歉,未查询到专辑<span style="color: red"><<{{ $route.query.keyword }}>></span></span>

      <song-sheet-show :list-data="lists" type="SongSheets" v-show="isActive === 'songSheet' && lists.length !== 0" :class="{'songSheet':isActive === 'songSheet'}">
        <template #title="title">
          <span class="title"><a href="" :title="title.title">{{ title.title }}</a></span>
        </template>
        <template #heat="heat">
          <span class="heat">播放量：{{ temperature(heat.heat) }}</span>
        </template>
      </song-sheet-show>
      <span v-show="isActive === 'songSheet' && lists.length === 0" class="notSongSheet">很抱歉,未找到歌单<span style="color: red">{{ $route.query.keyword }}</span></span>
    </div>
    <data-page v-show="lists.length !== 0" :size="size" :page-index="page" @changeCurrent="changeCurrent"></data-page>
  </div>
</template>

<script>
import {search} from "@network/http";
import {errorMessage} from "@/plugins/message";
import SongList from "@components/body/comm/SongList";
import splicingPrefix from "@/plugins/splicingPrefix";
import DataPage from "@components/comm/paging/DataPage";
import SingerShow from "@components/body/index/SingerShow";
import SongSheetShow from "@components/body/index/SongSheetShow";

export default {
  name: "SearchData",
  components: {
    SongList,
    DataPage,
    SingerShow,
    SongSheetShow
  },
  mounted() {
    this.getData()
    document.body.style.backgroundColor = 'white'
  },
  data() {
    return {
      lists: [],
      size: 0,
      allSong: 0,
      length: 0
    }
  },
  methods: {
    currentSize() {
      let pattern = new RegExp("[0-9]+");
      if (pattern.test(this.$route.query.page.toString())) {
        return Number.parseInt(this.$route.query.page.toString())
      }
      return 1
    },
    searchInfo(type) {
      let search = this.$route.query
      if (type === search.type) {
        return;
      }
      this.$router.push('/search?page=' + search.page + "&type=" + type + "&keyword=" + search.keyword)
      this.getData()
    },
    getData() {
      this.lists = []
      let query = this.$route.query
      search.search(query.page, query.type, query.keyword).then(
          success => {
            let data = JSON.parse(success.data.data)
            if('singer' === query.type){
              this.lists = splicingPrefix(data.lists, 'singerHeadImage')
            }else if('songSheet' === query.type){
              this.lists = splicingPrefix(data.lists,'songListImagesAddress')
            }else{
              this.lists = data.lists
            }
            this.size = data.pagination
            this.allSong = data.total
            this.length = data.length
          },
          error => {
            errorMessage(error.message)
          }
      )
    },
    changeCurrent(value) {
      let query = this.$route.query
      search.search(value, query.type, query.keyword).then(success => {
        let data = JSON.parse(success.data.data)
        this.lists = data.lists
        this.size = data.pagination
        this.allSong = data.total
        this.length = data.length
        this.$router.push('/search?page=' + value + "&type=" + query.type + "&keyword=" + query.keyword)
      }, error => {
        errorMessage(error.message)
      })
    },
    temperature(data){
      if(data < 10000){
        return data
      }
      if(data < 100000000){
        return (data / 10000).toFixed(2) + '万'
      }
      return (data / 100000000).toFixed(2) + "亿"
    },
  },
  computed: {
    isActive() {
      return this.$route.query.type
    },
    keyword() {
      return this.$route.query.keyword
    },
    page() {
      return Number.parseInt(this.$route.query.page.toString())
    }
  },
  watch: {
    keyword() {
      this.getData()
    }
  }
}
</script>

<style scoped>
#search {
  width: 1250px;
  min-width: 1250px;
  margin: 30px auto;
  background: white;
  box-sizing: border-box;
  height: 100%;
  min-height: 275px;
  box-shadow: 0 0 5px #ccc;
}

.search-change-button {
  width: 100%;
  display: flex;
  align-items: center;
  height: 75px;
  box-sizing: border-box;
  padding: 10px;
}

.search-change-button > span {
  cursor: pointer;
  width: 75px;
  height: 100%;
  text-align: center;
  line-height: 55px;
  position: relative;
}

.checked {
  color: red;
}

.checked:after {
  content: "";
  width: 15px;
  height: 1px;
  background: red;
  position: absolute;
  bottom: 10px;
  left: 30px;
}

.search-content {
  width: 100%;
  height: 100%;
  min-height: 200px;
}

.music-null {
  height: 100%;
  text-align: center;
  padding: 100px;
  box-sizing: border-box;
}

.search-content > .songs {
  width: 95%;
  margin: 0 auto;
}
.album{
  width: 100%;
  height: 100%;
  text-align: center;
  display: block;
  padding: 100px;
  box-sizing: border-box;
}

.notSongSheet{
  font-size: 16px;
  width: 100%;
  height: 100%;
  display: block;
  text-align: center;
  padding: 100px;
  box-sizing: border-box;
}
</style>

<style>
.search-content .show .sheet{
margin-right: 8.3px;
}

.search-content .show .sheet .sheet-item > .im{

}

.search-content .show .sheet .sheet-item .img > img {
  width: 150px;
  height: 150px;
  border-radius: 50%;
}

.songSheet{
  margin: 0 auto;
}

.songSheet .sheet{
  margin: 0 20px 0 30px!important;
}

.songSheet .sheet .sheet-item .img > img{
  width: 200px!important;
  height: 200px!important;
  border-radius: 5px!important;
}

.songSheet .sheet .sheet-item .title{
  height: 20px;
  width: 100%;
  position: relative;
}

.songSheet .sheet .sheet-item .title > a{
  height: 100%;
  position: absolute;
  color: black;
  text-decoration: none;
  width: 100%;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  -webkit-line-clamp: 1;
  -webkit-box-orient: vertical;
}

.songSheet .sheet .sheet-item .heat{
  width: 100%;
  height: 20px;
  line-height: 20px;
  color: #888;
  font-size: 14px;
}
</style>