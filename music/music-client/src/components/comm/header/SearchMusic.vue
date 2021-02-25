<template>
<span class="message">
  <span class="search">
    <label for="search-music"></label>
    <input type="text" id="search-music" placeholder="搜索歌曲、歌手" maxLength="18" minLength="1" v-model="condition" @keyup.enter="searchData()"/>
    <el-button slot="append" icon="el-icon-search" class="el-button el-button--danger" @click="searchData()"></el-button>
  </span>
</span>
</template>

<script>
export default {
  name: "Search",
  data(){
    return {
      condition: ''
    }
  },
  methods: {
    searchData(){
      if(this.condition === ''){
        return;
      }

      let route = this.$route
      if(route.name === 'search' && route.path === '/search' && route.query.keyword === this.condition){
        return;
      }
      window.open('/search?page=1&type=song&keyword='+this.condition,'_blank')
    }
  }
}
</script>

<style>
#search-music{
  width: 100%;
  height: 40px;
  box-sizing: border-box;
  border: none;
  outline: 0;
  background: #eeeeee;
  padding: 15px;
  font-size: 16px;
  border-radius: 30px;
}

#search-music::placeholder{
  color: #8b8b8b;
}
#search-music::-moz-placeholder{
  color: #8b8b8b;
}
#search-music::-ms-input-placeholder{
  color: #8b8b8b;
}

.search{
  display: flex;
  width: 100%;
  border-radius: 5px;
  position: relative;
}

.el-button--danger{
  position: absolute;
  right: 0;
  display: flex!important;
  justify-content: center;
  background: none!important;
  border-radius: 30px!important;
  width: 40px!important;
  height: 40px!important;
  border: none!important;
}

.el-icon-search{
  display: flex;
  color: #888;
  font-size: 22px;
  position: relative;
  top: -2px;
  font-weight: 700;
  justify-content: center;
  align-items: center;
}

.el-icon-search:hover{
  color: red;
}
</style>
