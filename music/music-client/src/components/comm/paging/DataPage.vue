<template>
  <div id="page">
  <span class="paging"
        @mouseenter="enterLeft()"
        @mouseleave="leaveLeft()"
        v-if="leftIsShow"
        @click="leftSub">
    <svg aria-hidden="true" class="images leftImages" v-if="left"><use xlink:href="#icon-direction"> </use></svg>
    <svg aria-hidden="true" class="images leftImages" v-else><use xlink:href="#icon-direction-white"></use></svg>
  </span>

    <span class="paging list" v-for="(key,index) in page" :class="{'activePage':currentActive === (index + 1)}"
          @click="getData($event,index)">{{ key }}</span>

    <span class="paging"
          @mouseenter="enterRight()"
          @mouseleave="leaveRight()"
          v-if="rightIsShow"
          @click="rightAdd">
    <svg aria-hidden="true" class="images rightImages" v-if="right"><use xlink:href="#icon-direction"> </use></svg>
    <svg aria-hidden="true" class="images rightImages" v-else><use xlink:href="#icon-direction-white"></use></svg>
  </span>
  </div>
</template>

<script>
export default {
  name: "DataPage",
  props: {
    size: {
      type: Number,
      default: 1
    },
    pageIndex: {
      type: Number,
      default: 1
    },
    singerData: {
      type: Object,
      require: true
    }
  },
  data() {
    return {
      currentActive: this.pageIndex,
      isActive: false,
      array: [1],
      ceil: Math.ceil(this.size / 5),
      current: 1,
      left: true,
      right: true
    }
  },
  methods: {
    getData(event, index) {
      this.currentActive = index + 1
      this.$emit('changeCurrent',this.currentActive)
    },
    enterLeft() {
      this.left = false
    },
    enterRight(){
      this.right = false
    },
    leaveLeft() {
      this.left = true
    },
    leaveRight(){
      this.right = true
    },
    leftSub() {
      if (this.current <= 1) {
        return;
      }else{
        console.log('1')
        if (this.current - 1< this.ceil) {
           this.right = true
        }
      }

      this.change('left')
      this.current--;
    },
    rightAdd() {
      if (this.current >= this.ceil) {
        return;
      }else{
        if (this.current + 1 > 1) {
          this.left = true
        }
      }

      this.change('right')
      this.current++;
    },
    change(type){
      let active = document.getElementsByClassName('activePage')
      if(active.length !== 0 && active[0].innerText !== this.currentActive){
        for(let li of document.getElementsByClassName('list')){
          li.setAttribute('class','paging list')
        }
      }
      if(type === 'left' && this.current !== 1 && active.length === 0){
        this.recovery()
      }
    },
    recovery(){
      let list = document.getElementsByClassName('list')
      let text = [];
      for(let li of list){
        text.push(li.innerText)
      }
      let index = text.map(value=>value - 5).filter(data=>data=== this.currentActive)
      if(index.length === 1){
          for(let li of list){
            if((li.innerText - 5) === index[0]){
              li.setAttribute('class','paging list activePage')
            }
          }
      }
    }
  },
  computed: {
    page() {
      if (this.ceil <= 1) {
        return this.size
      }

      if (this.ceil > 1 && this.current === 1) {
        this.array = [1, 2, 3, 4, 5]
        return this.array
      }
      this.array = []
      let data = (this.current >= this.ceil ? this.ceil : this.current) * 5;
      for (let i = (this.current - 1) * 5 + 1; i <= (data <= this.size ? data : this.size); ++i) {
        this.array.push(i)
      }

      return this.array
    },
    leftIsShow(){
      return this.ceil > 1 && this.current > 1
    },
    rightIsShow(){
      return this.ceil > 1 && this.current !== this.ceil
    }
  }
}
</script>

<style scoped>
#page {
  width: 100%;
  height: 100px;
  margin-bottom: 30px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.paging {
  cursor: pointer;
  width: 35px;
  height: 35px;
  margin-right: 20px;
  border-radius: 50%;
  text-align: center;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 18px;
}

.paging:hover {
  box-sizing: border-box;
  box-shadow: 0 0 10px #ccc;
  background: red;
  color: white;
  font-weight: 700;
}

.activePage {
  box-sizing: border-box;
  box-shadow: 0 0 10px #ccc;
  background: red;
  color: white;
  font-weight: 700;
}

.images {
  width: 25px;
  height: 25px;
  box-sizing: border-box;
  padding: 2px;
}

.leftImages {
  transform: rotate(180deg);
  margin-right: 2px;
}

.rightImages {
  margin-left: 2px;
}
</style>
