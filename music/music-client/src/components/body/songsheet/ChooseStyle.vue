<template>
  <div id="choose">
    <div id="type">
      <div class="mod_playlist_tag">
        <div class="js_normal">
          <SongSheetType v-for="(type,key,index) in types"
                         :dataKey="key"
                         :key="type.songSheetTypeId"
                         :data="type"
                         @getNewsSongSheet="getNewsSongSheet"></SongSheetType>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import SongSheetType from "@/components/body/songsheet/SongSheetType";
export default {
  name: "ChooseStyle",
  components: {
    SongSheetType
  },
  methods: {
    getNewsSongSheet(data){
      this.$emit('getNewsSongSheet',data)
    }
  },
  props: {
    types: {
      type: Object,
      required: true
    }
  }
}
</script>

<style scoped>
#choose{
  height: 250px;
  background: #f2f2f2;
  margin: 3px auto 0 auto;
  box-sizing: border-box;
}

#type{
  width: 1250px;
  min-width: 1250px;
  height: 100%;
  margin: 0 auto;
}

.mod_playlist_tag{
  position: relative;
  height: 100%;
  width: 100%;
}

.js_normal{
  display: flex;
}
</style>
