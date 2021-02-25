<template>
<div>
  <h4>新歌速递</h4>
  <div class="choose">
    <span class="music active" @click="active" :value="'new'">最新</span>
    <span class="music" @click="active" v-for="region in songRegion" :value="region.regionId">{{ region.regionName }}</span>
  </div>
  <div class="musics" @mouseenter="enter" @mouseleave="leave" :key="flush">
    <div class="show leftShow" @click="leftSlip" v-show="show()">
      <i class="el-icon-arrow-left leftSlip"></i>
    </div>
    <div class="music-list" v-for="(songIndex,index) in songs.length === 0?songs.length:Math.ceil(songs.length / 9)"
         :class="{'activeNews':songIndex - 1 === 0}"
         :style="site(index)">

      <span v-for="(song,index) in songs.slice((songIndex - 1) * 9,songIndex * 9)"
            class="songs"
            :class="{'bottomAfter': (index + 1) % 3 !== 0}"
            :key="song.songId">
        <span class="shadow"
              @mouseenter="enterImg($event,'shadow')"
              @mouseleave="leaveImg($event,'shadow')"></span>
        <span id="images">
          <img v-lazy="song.songImages"
               :alt="song.songName"
               class="song-img">
        </span>
        <img src="~@/assets/img/songSheet/btn-play.png"
             alt="播放"
             class="start"
             @dblclick="music_play(song)"
             @mouseenter="enterImg($event,'start')"
             @mouseleave="leaveImg($event,'start')">
          <span class="song-info">
            <span class="song-name" :title="song.songName">{{ song.songName }}</span>
            <span class="song-author" :title="name(song.author)"><a href="javascript:;" v-for="(singer,index) in song.author" class="name-a" @click="change(singer.singerId)">{{ songSingerName(singer.singerName,index,song.author) }}</a></span>
            <span class="song-length">{{ duration(song.songLength) }}</span>
          </span>
      </span>
    </div>

    <div class="show rightShow" @click="rightSlip" v-if="Math.ceil(songs.length / 9)>1">
      <i class="el-icon-arrow-right rightSlip"></i>
    </div>
    <div class="btn">
      <span  v-for="(song,index) in songs.slice(0,Math.ceil(songs.length / 9))"
             :key="song.songId"
             class="btn-round"
             :class="{'addColor':index === 0}"
             @click="transformation($event,index)"></span>
    </div>
  </div>
</div>
</template>

<script>
import Map from '@/plugins/map'
import {IS_SHOW, LEFT, LENGTH, MUSIC_LENGTH, MUSIC_LIST, PLAY_INDEX, PLAY_MUSIC, PLAYER} from "@store/constant";
import {getCookie} from "@/plugins/utils";
import {url} from "@/plugins/other"
import {errorMessage} from "@/plugins/message";

export default {
  name: "NewsSong",
  props: {
    songs: {
      type: Array,
      default(){
        return []
      }
    },
    songRegion: {
      type: Array,
      default(){
        return []
      }
    }
  },
  data(){
    return {
      indexData: 90,
      flush: true
    }
  },
  methods: {
    music_play(song){
      let map = Map.getInstance()
      let music = map.get("music-list")
      if(music == null){
        map.addString("music-list",JSON.stringify([]))
      }
      map.pushArrayTop('music-list',[song],'songId')
      map.addString('audio-id',song.songId)
    },
    change(singerId){
      this.$router.push('/singer/'+singerId+"?current=1")
    },
    songSingerName(singerName,index,song){
      if(song.length === 1){
        return singerName
      }
      if(index === song.length - 1){
        return singerName
      }
      return singerName + ' / '
    },
    show(){
      return false;
    },
    site(index){
      return {'left':(index * 1100) + 90 + 'px'}
    },
    active($event){
      if($event.currentTarget.getAttribute("class").split(" ").filter(data => data.indexOf("active") !== -1).length !== 0){
        return;
      }
      let nodes = $event.currentTarget.parentElement.childNodes;
      for(let node of nodes){
        node.setAttribute("class","music")
      }
      $event.currentTarget.setAttribute("class",'music active');
      this.flush = ! this.flush
      this.$emit('getData',$event.currentTarget.getAttribute('value'))
    },
    enter(){
      if(Math.ceil(this.songs.length / 9) > 1){
        let leftShow = document.getElementsByClassName('leftShow')[0];
        let rightShow = document.getElementsByClassName('rightShow')[0];
        leftShow.style.left = 0
        rightShow.style.right = 0
      }
    },
    leave(){
      if(Math.ceil(this.songs.length / 9) > 1) {
        let leftShow = document.getElementsByClassName('leftShow')[0];
        let rightShow = document.getElementsByClassName('rightShow')[0];
        leftShow.style.left = -90 + 'px'
        rightShow.style.right = -90 + 'px'
      }
    },
    enterImg($event,data){
      if(data === 'shadow'){
        let pre = $event.currentTarget;
        pre.style.opacity = '0.65'
        let songImg = $event.currentTarget.nextElementSibling.firstElementChild
        let next = $event.currentTarget.nextElementSibling.nextElementSibling;
        songImg.style.transform = 'scale(1.2)'
        next.style.display = 'inline-block'
      }

      if(data === 'start'){
        let pre = $event.currentTarget;
        let songImg = $event.currentTarget.previousElementSibling.firstElementChild
        let next = $event.currentTarget.previousElementSibling.previousElementSibling;
        pre.style.display = 'inline-block'
        songImg.style.transform = 'scale(1.2)'
        next.style.opacity = '0.65'
      }

    },
    leaveImg($event,data){
      if(data === 'shadow'){
        let pre = $event.currentTarget;
        let songImg = $event.currentTarget.nextElementSibling.firstElementChild
        let next = $event.currentTarget.nextElementSibling.nextElementSibling;
        pre.style.opacity = '0'
        songImg.style.transform = 'scale(1)'
        next.style.display = 'none'
      }

      if(data === 'start'){
        let pre = $event.currentTarget;
        let songImg = $event.currentTarget.previousElementSibling.firstElementChild
        let next = $event.currentTarget.previousElementSibling.previousElementSibling;
        pre.style.display = 'none'
        next.style.opacity = '0'
        songImg.style.transform = 'scale(1)'
      }
    },
    duration(length){
      if(typeof length === 'undefined'){
        return 0
      }
      if(typeof length === 'string'){
        length = parseInt(length)
      }
      let date = Math.floor(length / 60)
      let time =  length - date * 60
      return date < 10 ?"0"+date+":"+(time<10?"0"+time:time):date+":"+(time<10?"0"+time:time)
    },

    transformation($event,index){
      let splits = $event.currentTarget.getAttribute("class").split(" ");

      if(splits.filter(split => split.indexOf('addColor') !== -1).length !== 0){
        return;
      }

      if(index === 0){
        document.getElementsByClassName('leftShow')[0].style.display = 'none';
      }else if(index > 0 && index < Math.ceil(this.songs.length / 9)){
        document.getElementsByClassName('leftShow')[0].style.display = 'flex';
      }

      if(index === Math.ceil(this.songs.length / 9) - 1 || index < 0 || index >= Math.ceil(this.songs.length / 9)){
        document.getElementsByClassName('rightShow')[0].style.display = 'none';
      }else if(index < Math.ceil(this.songs.length / 9) - 1){
        document.getElementsByClassName('rightShow')[0].style.display = 'flex';
      }


      let size = 0;
      let nodes = $event.currentTarget.parentElement.childNodes
      for(let node of nodes){
        for(let length of node.getAttribute("class").split(" ")){
          if(length === 'addColor'){
            node.setAttribute("class",'btn-round')
            $event.currentTarget.setAttribute("class","btn-round addColor")
            this.addActive(index)
            this.update(index,size)
            return;
          }
        }
        size++;
      }
    },
    addActive(index){
      let musics = document.getElementsByClassName('music-list');
      for(let music of musics){
        music.setAttribute('class','music-list')
      }
      document.getElementsByClassName('music-list')[index].setAttribute('class','music-list activeNews')
    },
    update(index,size){
      let music = document.getElementsByClassName('music-list')
      if(index > size){
        for(let i=0;i< index - size;++i){
          for(let y=0;y<music.length;++y){
            music[y].style.left = ( parseInt(music[y].style.left.replaceAll("px","")) - 1100) + 'px'
          }
        }
      }else{
        for(let i=0;i< size - index;++i){
          for(let y=0;y<music.length;++y){
            music[y].style.left = ( parseInt(music[y].style.left.replaceAll("px","")) + 1100) + 'px'
          }
        }
      }
    },
    leftSlip(){
      let nodes = document.getElementsByClassName('music-list')
      let index = 0;

      for(let node of nodes){
        if(node.getAttribute('class').split(' ').filter(split => split.indexOf('activeNews') !== -1).length !== 0){
          break;
        }else{
          index++;
        }
      }

      if(index === 1){
        document.getElementsByClassName('leftShow')[0].style.display = 'none';
      }

      if(index < Math.ceil(this.songs.length / 9)){
        document.getElementsByClassName('rightShow')[0].style.display = 'flex';
      }

      let btn = document.getElementsByClassName('btn-round');
      for(let b of btn){
        b.setAttribute('class','btn-round')
      }

      if(index !== 0){
        this.addActive(index - 1);
        btn[index - 1].setAttribute('class','btn-round addColor')
      }else{
        this.addActive((this.songs.length / 9) - 1);
        btn[Math.ceil(this.songs.length / 9) - 1].setAttribute('class','btn-round addColor')
      }

      this.update(index,index+1)
    },
    rightSlip(){
      let nodes = document.getElementsByClassName('music-list')
      let index = 0;

      for(let node of nodes){
        if(node.getAttribute('class').split(' ').filter(split => split.indexOf('activeNews') !== -1).length !== 0){
          break;
        }else{
          index++;
        }
      }
      if(index === Math.ceil(this.songs.length / 9) - 2){
        document.getElementsByClassName('rightShow')[0].style.display = 'none';
      }
      if(index > -1){
        document.getElementsByClassName('leftShow')[0].style.display = 'flex';
      }
      let btn = document.getElementsByClassName('btn-round');
      for(let b of btn){
        b.setAttribute('class','btn-round')
      }
      if(index + 1 > Math.ceil(this.songs.length / 9) - 1){
        this.addActive(0);
        btn[0].setAttribute('class','btn-round addColor')
      }else{
        this.addActive(index + 1);
        btn[index + 1].setAttribute('class','btn-round addColor')
      }


      this.update(index+1,index)
    },
    name(songName){
      if(songName.length === 0){
        return "未知歌手"
      }
      if(songName.length === 1){
        return songName[0].singerName
      }

      let showName = '';
      for(let i=0;i<songName.length;++i){
        if(i === songName.length - 1){
          showName = showName+songName[i].singerName
        }else{
          showName = showName+songName[i].singerName +" / "
        }
      }
      return showName
    }
  }
}
</script>

<style scoped>
h4{
  text-align: center;
  font-size: 25px;
  margin: 45px 0 15px 0;
  position: relative;
}

.choose{
  display: flex;
  align-items: center;
  justify-content: center;
  width: 100%;
  height: 30px;
}

.music{
  display: inline-block;
  position: relative;
  font-size: 15px;
  margin: 0 24px;
  color: #333;
  cursor: pointer;
}

.music:hover{
  color: #0dc60d;
}

.active{
  color: #0dc60d;
}

.active::after{
  content: "";
  display: block;
  height: 3px;
  background: red!important;
  width: 15px;
  position: absolute;
  top: 25px;
  left: 7px;
}

.musics{
  width: 1200px;
  margin:  30px auto 0 auto;
  height: 430px;
  border-radius: 5px;
  background: #f3f3f3;
  overflow: hidden;
  display: flex;
  cursor: pointer;
  position: relative;
  align-items: center;
  justify-content: center;
}

.music-list{
  left: 90px;
  top: 10px;
  position: absolute;
  transition: 1s all;
  display: flex;
  flex-wrap: wrap;
  flex-direction: column;
  width: 85%;
  height: 98%;
  margin: 0;
  padding: 0;
}

.leftSlip{
  font-size: 60px;
  position: absolute;
  left: 10px;
}

.leftShow{
  transition: 0.75s all;
  left: -90px;
}

.show{
  position: absolute;
  background: rgba(255,255,255,0.3);
  filter: blur(0.8px);
  width: 90px;
  z-index: 19;
  display: flex;
  height: 90px;
  align-items: center;
  justify-content: center;
  border-radius: 5px;
}

.rightSlip{
  right: 10px;
  font-size: 60px;
  position: absolute;
}

.rightShow{
  transition: 0.75s all;
  right: -90px;
}

.songs{
  width: 33%;
  height: 130px;
  padding: 10px;
  box-sizing: border-box;
  display: flex;
  position: relative;
  align-items: center;
}

.bottomAfter:after{
  position: absolute;
  content: "";
  width: 95%;
  height: 1px;
  bottom: 0;
  background: #ccc;
}

.song-img{
  transition: 0.75s all;
  width: 100%;
  height: 100%;
}

.song-info{
  width: 73%;
  height: 86px;
  position: relative;
}

.song-length{
  position: absolute;
  top: 30px;
  right: 0;
  font-size: 17px;
  color: #888;
}

.song-author{
  color: #535353;
  position: absolute;
  bottom: 17px;
  left: 10px;
  width: 220px;
  font-size: 14px;
  box-sizing: border-box;
  overflow: hidden;
  text-overflow: ellipsis;
  -o-text-overflow: ellipsis;
  white-space: nowrap;
  word-break: break-all;
}

.song-name{
  position: absolute;
  top: 10px;
  width: 220px;
  left: 10px;
  font-size: 16px;
  box-sizing: border-box;
  overflow: hidden;
  text-overflow: ellipsis;
  -o-text-overflow: ellipsis;
  white-space: nowrap;
  word-break: break-all;
}

.btn{
  width: 100%;
  height: 40px;
  position: absolute;
  bottom: 0;
  display: flex;
  align-items: center;
  justify-content: center;
}

.btn-round{
  width: 10px;
  height: 10px;
  border-radius: 50%;
  margin: 0 5px;
  background: #b4b4b4;
}

.addColor{
  background: #565656;
}

.start{
  display: none;
  z-index: 21;
  position: absolute;
  width: 45px;
  top: 33%;
  left: 9%;
}

.shadow{
  background: rgba(0,0,0,0.65);
  z-index: 20;
  width: 86px;
  opacity: 0;
  height: 86px;
  position: absolute;
  box-sizing: border-box;
}

#images{
  width: 86px;
  height: 86px;
  overflow: hidden;
  transform: scale(1);
}

.name-a{
  text-decoration: none;
  color: #535353;
}

.name-a:hover{
  color: #929292;
}
</style>
