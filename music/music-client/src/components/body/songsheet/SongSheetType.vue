<template>
<div class="sheetType">
  <div class="playlist_tag__list">
    <h3>{{ dataKey }}</h3>
    <ul class="playlist_tag__tags">
      <li class="playlist_tag__itemBox"
          v-for="type in firstType"
          :key="type.songSheetTypeId"
          :value="type.songSheetTypeId">
        <span><a href="javascript:;" class="playlist_tag__item" @click="addColor($event)" v-html="type.songSheetTypeName" :class="{'active':isActive(type.songSheetTypeId)}"></a></span>
      </li>
      <li class="playlist_tag__itemBox" v-if="this.data.length > 9">
        <span>
          <a href="javascript:;" class="playlist_tag__item js_more_tag" @click="addColor($event,'most')" @mouseenter="enter" @mouseleave="leave">更多
            <i class="playlist_tag__arrow sprite"></i>
          </a>
        </span>
      </li>
    </ul>
    <i class="triangle"></i>
  </div>

  <div class="popup_tag tag-hide">
    <ul class="playlist_tag__tags">
      <li class="playlist_tag__itemBox" v-for="last in lastType" :key="last.songSheetTypeId" @click="addShow" :value="last.songSheetTypeId">
        <span><a href="javascript:;" class="playlist_tag__item" :class="{'active':isActive(last.songSheetTypeId)}">{{ last.songSheetTypeName }}</a></span>
      </li>
    </ul>
  </div>
</div>
</template>

<script>

export default {
  name: "SongSheetType",
  methods: {
    isActive(tagId){
      let tag = this.$route.query.tag
      return tag !== undefined && typeof tag === 'string'?parseInt(tag) === tagId : tag === tagId
    },
    enter($event){
      if($event.currentTarget.getAttribute('class').split(" ").filter(split => split.indexOf("active") !== -1).length === 0){
        $event.currentTarget.firstElementChild.style.backgroundImage = 'url("data:image/svg+xml,%3Csvg class=\'icon\' ' +
            'viewBox=\'0 0 1024 1024\' xmlns=\'http://www.w3.org/2000/svg\' ' +
            'width=\'32\' height=\'32\'%3E%3Cpath d=\'M538.112 639.488L918.016 ' +
            '273.92c13.824-13.824 36.352-13.824 50.176 0 13.824 13.824 13.824 35.84 ' +
            '0 49.664L563.2 724.48c-13.824 13.824-36.352 13.824-50.176 0L108.032 323.584c-13.824-13.824-13.824-35.84' +
            ' 0-49.664s36.352-13.824 50.176 0l379.904 365.568z\' fill=\'%23d81e06\'/%3E%3C/svg%3E")'
      }

    },
    leave($event){
      if($event.currentTarget.getAttribute('class').split(" ").filter(split => split.indexOf("active") !== -1).length === 0) {
        $event.currentTarget.firstElementChild.style.backgroundImage = 'url("data:image/svg+xml,%3Csvg class=\'icon\' ' +
            'viewBox=\'0 0 1024 1024\' xmlns=\'http://www.w3.org/2000/svg\' width=\'32\' height=\'32\'%3E%3Cpath d=\'M538.112' +
            ' 639.488L918.016 273.92c13.824-13.824 36.352-13.824 50.176 0 13.824 13.824 13.824 35.84 0 49.664L563.2 724.48c-13.824' +
            ' 13.824-36.352 13.824-50.176 0L108.032 323.584c-13.824-13.824-13.824-35.84 0-49.664s36.352-13.824 50.176 0l379.904' +
            ' 365.568z\'/%3E%3C/svg%3E")'
      }
    },
    addColor($event,data){

      if($event.currentTarget.getAttribute('class').split(" ").filter(split => split.indexOf("active") !== -1).length !== 0 &&
          $event.currentTarget.getAttribute('class').split(" ").filter(split => split.indexOf("js_more_tag") !== -1).length !== 0){

        // 当点击的是已经激活的 并且是'更多'按钮时，可以关闭
        $event.currentTarget.setAttribute('class','playlist_tag__item js_more_tag')
        $event.currentTarget.firstElementChild.setAttribute('class','playlist_tag__arrow sprite')
        $event.currentTarget.parentElement.parentElement.parentElement.nextElementSibling.style.display = 'none'
        $event.currentTarget.parentElement.parentElement.parentElement.parentElement.nextElementSibling.style.display = 'none'
        return;
      }else if($event.currentTarget.getAttribute('class').split(" ").filter(split => split.indexOf("active") !== -1).length !== 0){

        // 当点击的是已经激活的，非‘更多’按钮时，直接退出
        return;
      }

      this.close()

      if(data === 'most'){
        // 如果点击的是更多按钮时，打开div
        $event.currentTarget.parentElement.parentElement.parentElement.nextElementSibling.style.display = 'block'
        $event.currentTarget.parentElement.parentElement.parentElement.parentElement.nextElementSibling.style.display = 'block'
        $event.currentTarget.firstElementChild.setAttribute('class','playlist_tag__arrow sprite most')
        $event.currentTarget.setAttribute('class','playlist_tag__item js_more_tag active')
      }else{
        let playlist_tag__item = document.getElementsByClassName('playlist_tag__item');
        for (let i = 0; i < playlist_tag__item.length; i++) {
          playlist_tag__item[i].setAttribute('class','playlist_tag__item')
        }

        // 非更多按钮
        $event.currentTarget.setAttribute('class','playlist_tag__item active')
        this.$emit('getNewsSongSheet',{
          'songSheetTypeId': $event.currentTarget.parentElement.parentElement.getAttribute('value'),
          'songSheetTypeName': $event.currentTarget.innerHTML
        })
      }

    },
    close(){
      let js_more_tag = document.getElementsByClassName('js_more_tag');
      let tagHide = document.getElementsByClassName('tag-hide');
      let triangle = document.getElementsByClassName('triangle');
      let sprite = document.getElementsByClassName('sprite');

      for (let i = 0; i < js_more_tag.length; i++) {
        js_more_tag[i].setAttribute('class','playlist_tag__item js_more_tag')
      }

      for (let i = 0; i < tagHide.length; i++) {
        tagHide[i].style.display = 'none'
      }

      for (let i = 0; i < triangle.length; i++) {
        triangle[i].style.display = 'none'
      }

      for (let i = 0; i < sprite.length; i++) {
        sprite[i].setAttribute('class','playlist_tag__arrow sprite')
      }
    },
    addShow($event){
      this.$emit('getNewsSongSheet',{
        'songSheetTypeId': $event.currentTarget.getAttribute('value'),
        'songSheetTypeName': $event.currentTarget.firstElementChild.firstElementChild.innerHTML
      })
    }
  },
  props: {
    dataKey: String,
    data: {
      type: Array,
      default(){
        return {}
      }
    }
  },
  data(){
    return {
      firstType: this.data.length <= 9 ?this.data: this.data.slice(0,8),
      lastType: this.data.length <= 9 ? {}:this.data.slice(9,this.data.length)
    }
  },
  mounted() {
    console.log()
  }
}
</script>

<style scoped>
.playlist_tag__list{
  display: inline-block;
  position: relative;
  height: 100%;
  flex: 1;
  box-sizing: border-box;
  margin: 5px;
  padding: 5px;
}

h3{
  text-align: center;
  font-size: 17px;
  color: #939191;
  font-weight: 500;
}

a{
  text-decoration: none;
}

.playlist_tag__item{
  line-height: 40px;
  color: black;
  position: relative;
  text-align: center;
  font-size: 15px;
  width: 65px;
  height: 35px
}

.playlist_tag__item:hover{
  color: red;
}

.playlist_tag__itemBox > span{
  width: 100%;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
}

ul{
  margin: 0;
  padding: 0;
}

li{
  list-style: none;
  display: inline-block;
  width: 65px;
  margin: 5px;
  height: 40px;
}

.tag-hide{
  position: absolute;
  top: 220px;
  width: 97%;
  left: 18px;
  height: 50px;
  display: none;
  background: white;
  box-sizing: border-box;
  padding: 0;
  box-shadow: 0 0 10px #ccc;
  margin: 0;
  border-radius: 5px;
}

.playlist_tag__arrow{
  position: absolute;
  background-image: url("https://www.tanxiangblog.com/icon/bottom.svg");
  right: 4px;
  top: 13px;
  width: 13px;
  background-repeat: no-repeat;
  z-index: 30;
  background-size: 13px 13px;
  height: 13px;
}

.active{
  background: red;
  color: white!important;
  font-weight: 700;
  border-radius: 50px;
  box-sizing: border-box;
}

.most{
  transition: 0.75s all;
  top: 10px;
  transform: rotate(180deg);
  background-image: url("data:image/svg+xml;base64,PHN2ZyBjbGFzcz0iaWNvbiIgdmlld0JveD0iMCAwIDEwMjQgMTAyNCIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIiB3aWR0aD0iMzIiIGhlaWdodD0iMzIiPjxwYXRoIGQ9Ik01MzguMTEyIDYzOS40ODhMOTE4LjAxNiAyNzMuOTJjMTMuODI0LTEzLjgyNCAzNi4zNTItMTMuODI0IDUwLjE3NiAwIDEzLjgyNCAxMy44MjQgMTMuODI0IDM1Ljg0IDAgNDkuNjY0TDU2My4yIDcyNC40OGMtMTMuODI0IDEzLjgyNC0zNi4zNTIgMTMuODI0LTUwLjE3NiAwTDEwOC4wMzIgMzIzLjU4NGMtMTMuODI0LTEzLjgyNC0xMy44MjQtMzUuODQgMC00OS42NjRzMzYuMzUyLTEzLjgyNCA1MC4xNzYgMGwzNzkuOTA0IDM2NS41Njh6IiBmaWxsPSIjZmZmIi8+PC9zdmc+")!important;
}

.triangle{
  position: absolute;
  background-image: url("data:image/svg+xml,%3Csvg class='icon' viewBox='0 0 1024 1024' xmlns='http://www.w3.org/2000/svg' width='32' height='32'%3E%3Cpath d='M512 6.4C505.6 0 492.8 0 480 0s-25.6 0-32 6.4c-12.8 6.4-19.2 19.2-25.6 25.6L6.4 761.6c-12.8 19.2-12.8 44.8 0 64 6.4 12.8 12.8 12.8 25.6 19.2s19.2 6.4 32 6.4h819.2c12.8 0 25.6 0 32-6.4 12.8-6.4 19.2-12.8 25.6-19.2 12.8-19.2 12.8-44.8 0-64L537.6 32c-6.4-6.4-12.8-19.2-25.6-25.6z' fill='%23fff'/%3E%3C/svg%3E");
  box-sizing: border-box;
  background-repeat: no-repeat;
  background-size: 20px 20px;
  width: 20px;
  height: 20px;
  right: 35px;
  z-index: 20;
  bottom: 3px;
  display: none;
}

.sheetType{
  flex: 1 ;
}

.active:after{
  background: none!important;

}
</style>
