<template>
  <RecommendSongSheet :name="Name">
    <template #SongSheet>
      <song-sheet-show :list-data="List" v-if="type === 'SongSheets'" :type="type"></song-sheet-show>
      <singer-show :list-data="List" v-if="type === 'singers'" :type="type"></singer-show>
    </template>
  </RecommendSongSheet>
</template>

<script>
import SingerShow from "@/components/body/index/SingerShow";
import SongSheetShow from "@/components/body/index/SongSheetShow";
import RecommendSongSheet from "@/components/body/index/RecommendSongSheet";
export default {
  name: "SongSheetItemComponent",
  components: {
    RecommendSongSheet,
    SongSheetShow,
    SingerShow
  },
  props: {
    List: {
      type: Array,
      required: true
    },
    Name: String,
    type: String
  },
  data() {
    return {
      list: this.List
    }
  }
}
</script>

<style scoped>

</style>