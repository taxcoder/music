<template>
  <div id="player-bottom">
    <div id="music-controller">
      <music-controller></music-controller>
      <music-progress></music-progress>
      <music-other></music-other>
    </div>
  </div>
</template>

<script>
import MusicProgress from "@components/body/player/bottom/MusicProgress";
import MusicController from "@components/body/player/bottom/MusicController";
import MusicOther from "@components/body/player/bottom/MusicOther";

export default {
  name: "PlayerBottom",
  components: {
    MusicProgress,
    MusicController,
    MusicOther
  }
}
</script>

<style scoped>
#player-bottom {
  box-sizing: border-box;
  height: 10%;
  width: 100%;
  min-height: 100px;
  position: fixed;
  bottom: 0;
  display: flex;
  min-width: 850px;
  padding: 0 100px 0 80px;
  z-index: 13;
}

#music-controller {
  min-width: 850px;
  display: flex;
  width: 100%;
  height: 90%;
  padding: 5px;
}
</style>

