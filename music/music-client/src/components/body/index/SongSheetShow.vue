<template>
  <div class="show">
    <song-sheet-item v-for="(song,index) in ListData"
                     :img="song.songListImagesAddress"
                     :name="song.songListName"
                     :key="song.songListId"
                     :type="type"
                     :id="song.songListId"
                     :song-list-id="song.songListId"
                     :index='index'
                     :titleData="song.songListName">

      <template #info>
        <slot name="title" :title="song.songListName">
          <span class="span-settings" v-if="$route.name === 'index'">{{ sum(song.songSheetTypeList[0].songSheetTypeName,song.songListName) }}</span>
          <span class="span-settings" v-else>{{ sum(song.songListName) }}</span>
        </slot>

        <slot name="heat" :heat="song.temperature">
          <span class="temperature">
          <img src="data:image/svg+xml,%3Csvg class='icon' viewBox='0 0 1024 1024'
                    xmlns='http://www.w3.org/2000/svg' width='64' height='64'%3E%3Cpath
                    d='M875.008 295.424a34.133 34.133 0 1 0-58.197 35.67c35.328 57.514
                    53.93 123.562 53.93 191.487 0 201.899-164.352 366.251-366.25
                    366.251S138.24 724.48 138.24 522.582 302.592 156.33 504.49
                    156.33c18.774 0 34.134-15.36 34.134-34.134s-15.36-34.133-34.133-34.133c-239.616
                    0-434.518 194.901-434.518 434.517S264.875 957.1 504.491 957.1 939.008 762.197
                    939.008 522.58c.17-80.384-22.016-159.061-64-227.157z' fill='%23fff'/%3E%3Cpath
                    d='M501.248 389.973c-77.653 0-140.8 63.147-140.8 140.8s63.147 140.8 140.8 140.8
                    140.8-63.146 140.8-140.8V224.256c0-19.456 15.872-35.328 35.328-35.328 19.456 0
                    35.328 15.872 35.328 35.328 0 18.773 15.36 34.133 34.133 34.133s34.134-15.36
                    34.134-34.133c0-57.173-46.422-103.595-103.595-103.595s-103.595 46.422-103.595
                    103.595v186.027a140.527 140.527 0 0 0-72.533-20.31zm0 213.334a72.704 72.704 0 0
                    1-72.533-72.534 72.704 72.704 0 0 1 72.533-72.533 72.704 72.704 0 0 1 72.533 72.533
                    72.704 72.704 0 0 1-72.533 72.534z' fill='%23fff'/%3E%3C/svg%3E"
               alt="音乐" class="ico">
          {{ temperature(song.temperature) }}
          </span>
        </slot>

      </template>
    </song-sheet-item>
  </div>
</template>

<script>
import SongSheetItem from "@/components/body/index/SongSheetItem";
export default {
  name: "SongSheetShow",
  components: {
    SongSheetItem
  },
  props: {
      ListData: Array,
      type: String
  },
  methods: {
    sum(typeName,songListName){
      return '['+typeName+']'+songListName
    },
    temperature(data){
      if(data < 10000){
        return data
      }
      if(data < 100000000){
        return (data / 10000).toFixed(2) + '万'
      }
      return (data / 100000000).toFixed(2) + "亿"
    }
  }
}
</script>

<style scoped>
.show{
  width: 100%;
  display: flex;
  height: 100%;
  box-sizing: border-box;
  flex-wrap: wrap;
}
.span-settings{
  width: 200px;
  display: block;
  padding: 5px 0;
  line-height: 18px;
  color: #666;
  box-sizing: border-box;
  overflow: hidden;
  text-overflow: ellipsis;
  -o-text-overflow: ellipsis;
  white-space:nowrap;
  height: 30px;
  word-break: break-all;
}

.temperature{
  position: absolute;
  right: 0;
  top: 0;
  z-index: 2;
  color: white;
  background: rgba(136,136,136,.55);
  font-weight: 700;
  padding: 5px;
  border-radius: 7px;
}

.ico{
  width: 20px;
  vertical-align: bottom;
}

</style>
