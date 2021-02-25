<template>
  <div id="singerInfo">
    <singer-show :list-data="singers" type="singers" :marginRight="marginRight"></singer-show>
  </div>
</template>

<script>
import SingerShow from "@/components/body/index/SingerShow";
export default {
  name: "SingerInfo",
  props: {
    singers: {
      type: Array,
      default(){
        return []
      }
    }
  },
  data(){
    return {
      marginRight: {
        'margin-right': '46px'
      }
    }
  },
  components: {
    SingerShow
  }
}
</script>

<style scoped>
#singerInfo{
  width: 100%;
}

.register{
  width: 200px;
  height: 200px;
  background: #8b8b8b;
  padding: 30px;
}
</style>