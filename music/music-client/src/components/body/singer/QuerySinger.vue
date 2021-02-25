<template>
<div class="query">
  <div class="type">
    <span v-if="types.length > 0" v-for="(type,index) in types"
          :key="type.regionId"
          :value="type.regionId"
          :class="{'active':isActive(index,'region')}"
          @click="addColor($event,type.regionName,'type')"
          class="success">{{ type.regionName }}</span>
  </div>
  <div class="type">
    <span v-for="(state,index) in status"
          :key="state.stateId"
          :value="state.stateId"
          :class="{'active':isActive(index,'status')}"
          @click="addColor($event,state,'state')"
          class="success">{{ state.stateName }}</span>
  </div>
  <div class="type">
    <span v-for="(letter,index) in letters"
          class="letter"
          :class="{'active':isActive(letter,'letter')}"
          :style="isRight(index)"
          :value="letter"
          @click="addColorLetters">{{ letter }}</span>
  </div>
</div>
</template>

<script>
export default {
  name: "query",
  data(){
    return {
      letters: ['流行','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'],
      status: [
        {
          'stateId': 0,
          'stateName': '男'
        },
        {
          'stateId': 1,
          'stateName': '女'
        },
        {
          'stateId': 2,
          'stateName': '组合'
        },
      ],
    }
  },
  props: {
    types: {
      type: Array,
      default: []
    },
    singerData: {
      type: Object,
      require: true
    }
  },
  methods:{
    isRight(index){
      return index === 0?'margin-right:20px!important;':''
    },
    isActive(index,data){
      if(data === 'region') {
        return index === (parseInt(this.singerData[data]) - 1)
      }
      return data === 'status'?index === parseInt(this.singerData[data]):index === this.singerData[data]

    },
    addColor(event,name,type) {
      this.isClear(event,"",'active');

      if(type === 'type'){
        this.isEmit(event,'region');
      }else if(type === 'state'){
        this.isEmit(event,'status');
      }

      this.isPush();
    },
    addColorLetters(event){
      this.isClear(event,"site",'site active');
      this.isEmit(event,'letter');
      this.isPush();
    },
    isPush(){
      this.$router.push('/singers?'+"current="+this.singerData.current+"&status="+this.singerData.status+"&region="+this.singerData.region+"&letter="+this.singerData.letter)
    },
    isEmit(event,data){
      let param = {[data]: event.currentTarget.getAttribute('value')}
      this.$emit('getNewDataChoose',param)
    },
    isClear(event,clear,add){
      if(event.currentTarget.getAttribute('class').split(" ").filter(split => split.indexOf('active') !== -1).length !== 0){
        return;
      }

      let nodes = event.currentTarget.parentElement.childNodes
      for(let node of nodes){
        node.setAttribute("class",clear)
      }

      event.currentTarget.setAttribute('class',add)
    }
  }
}
</script>

<style scoped>
.type{
  width: 100%;
  height: 60px;
  display: flex;
  align-items: center;
}

.site{
  margin: 0!important;
  padding: 0;
  width: 45px!important;
  font: 15px/1.5 Microsoft YaHei,tahoma,arial,Hiragino Sans GB,\\5b8b\4f53,sans-serif;
}

.type > span{
  cursor: pointer;
  margin-right: 20px;
  height: 30px;
  width: 45px;
  text-align: center;
  line-height: 33px;
  color: black;
  font-size: 14px;

}

.type > span:hover{
  font-weight: 700;
  color: white!important;
  border-radius: 50px;
  box-sizing: border-box;
  background: red;
  box-shadow: 0 0 10px #ccc;
}

.active{
  font-weight: 700!important;
  color: white!important;
  border-radius: 50px;
  box-sizing: border-box;
  background: red;
  box-shadow: 0 0 10px #ccc;
}

.active:after{
  background: none!important;
}

.letter{
  margin: 0!important;
  font: 15px/1.5 Microsoft YaHei,tahoma,arial,Hiragino Sans GB,\\5b8b\4f53,sans-serif;
}

.query{
  margin-bottom: 30px;
}
</style>
