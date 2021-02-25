<template>
  <div id="singer">
    <query-singer :types="types" @getNewsSongSheet="getNewsSongSheet" :singerData="singerData"></query-singer>
    <singer-info :singers="singers"></singer-info>
    <data-page :size="size" @getNewsSongSheet="getNewsSongSheet" :singerData="singerData"></data-page>
  </div>
</template>

<script>
import {singers} from "@/network/http";
import DataPage from "@/components/comm/paging/DataPage";
import SingerInfo from "@/components/body/singer/SingerInfo";
import QuerySinger from "@/components/body/singer/QuerySinger";
import {errorMessage} from "@/plugins/message";
import splicingPrefix from "@/plugins/splicingPrefix";
export default {
  name: "Singer",
  components: {
    QuerySinger,
    SingerInfo,
    DataPage
  },
  data() {
    return {
      current: 0,
      types: [],
      singers: [],
      singerData: {
          'region': 0,
          'status': 0,
          'letter': '流行',
          'current': 1
      },
      size: 1 // 从后台获取的数据总数
    }
  },
  created() {
    this.give();
    singers.regionSinger.then(success => {
      this.types = JSON.parse(success.data.data)
      singers.allSingers(this.singerData['region'],this.singerData['status'],this.singerData['letter'],this.singerData['current']).then(success => {
        this.size = success.data.pagination
        this.singers = splicingPrefix(JSON.parse(success.data.data).list,'singerHeadImage')
      });
    },error => {errorMessage("地域数据获取失败！");})
  },
  methods: {
    give(){
      this.singerData['region'] = this.$route.query.region
      this.singerData['status'] = this.$route.query.status
      this.singerData['letter'] = this.$route.query.letter
      this.singerData['current'] = this.$route.query.current
    },
    getNewsSongSheet(data){
      this.singerData[Object.keys(data)[0]] = data[Object.keys(data)[0]]
      this.newSinger(this.singerData);
    },
    newSinger(singer){
      singers.allSingers(singer['region'],singer['status'],singer['letter'],singer['current']).then(success => {
        let Json = JSON.parse(success.data.data);
        this.size = Json.pagination
        this.singers = splicingPrefix(Json.list,'singerHeadImage')
      },error => {errorMessage("歌手获取失败！");})
    }
  },
}
</script>

<style scoped>
#singer{
  width: 1200px;
  min-width: 1200px;
  height: 100%;
  margin: 50px auto 0 auto;
}
</style>
