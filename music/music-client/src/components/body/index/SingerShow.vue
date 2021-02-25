<template>
  <div class="show">
    <song-sheet-item v-for="(singer,index) in ListData"
                     :img="singer.singerHeadImage"
                     :name="singer.singerName"
                     :type="type"
                     :index="index"
                     :id="returnId(singer.singerId)"
                     :marginRight="(index + 1) % 5 !== 0?marginRight:{}"
                     :key="singer.singerId">
      <template #info>
        <span class="span-settings">{{ singer.singerName }}</span>
      </template>
    </song-sheet-item>
  </div>
</template>

<script>
import SongSheetItem from "@/components/body/index/SongSheetItem";
export default {
  name: "SingerShow",
  components: {
    SongSheetItem
  },
  props: {
    ListData: Array,
    type: String,
    size: String,
    sheet: {
      type: Object,
      default(){
        return {}
      }
    },
    marginRight: {
      type: Object,
      default(){
        return {}
      }
    }
  },
  methods: {
    returnId(singerId){
      return singerId + ''
    }
  }
}
</script>

<style scoped>
.show{
  width: 100%;
  display: flex;
  height: 100%;
  position: relative;
  box-sizing: border-box;
  flex-wrap: wrap;
}

.span-settings{
  text-align: center;
  width: 200px;
  display: block;
  padding: 5px 0;
  line-height: 18px;
  font-size: 19px;
  color: #545454;
  box-sizing: border-box;
  overflow: hidden;
  text-overflow: ellipsis;
  -o-text-overflow: ellipsis;
  white-space: nowrap;
  height: 30px;
  word-break: break-all;
}
</style>
