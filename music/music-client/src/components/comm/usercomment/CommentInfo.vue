<template>
  <div class="comments" :style="styles">
    <div id="marvellous" v-if="marvellous.length > 0">
      <h3><slot name="info"></slot></h3>
      <div class="marvellous-discuss" v-for="discuss in resource" :key="discuss.commentId">
        <span class="head"><img :src="discuss.user.userHeadAddress" alt="头像"></span>
        <div id="info">
          <p class="user-info"><a href="javascript:;">{{ discuss.user.userName }}</a></p>
          <p class="discuss-content">{{ discuss.commentInfo }}</p>
          <p class="time-content">
            <span class="date">{{ discuss.commentDate }}</span>
            <span class="good" @click="clickDiscuss(isShow(discuss.commentId))">
              <img src="~@/assets/img/info/good.svg" alt="未点赞" v-if="isShow(discuss.commentId)" class="discuss-not_good">
              <img src="~@/assets/img/info/good-red.svg" alt="已点赞" v-else class="discuss-good">
              {{ discuss.commentGoods }}
            </span>
          </p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {Message} from 'element-ui'
export default {
  name: "CommentInfo",
  props: {
    marvellous: {
      type: Array,
      require: true
    },
    styles: {
      type: String,
      default: ''
    },
    discussGoods: {
      type: Array,
      default(){
        return []
      }
    }
  },
  methods: {
    isShow(discuss){
      return this.discussGoods.filter(good => good.commentId === discuss).length === 0
    },
    clickDiscuss(flag){
      if(!flag){
        setTimeout(()=>{Message.error('您已经点过赞了！')})
        return;
      }
      // 传递歌单ID和评论ID和用户ID
      setTimeout(()=>{Message.success('点赞成功！')})
    }
  },
  computed: {
    resource(){
      return this.marvellous
    }
  }
}
</script>

<style scoped>
.comments{
  margin: 70px auto 0 auto;
  width: 95%;
}

#marvellous > h3{
  font: 18px Microsoft YaHei,tahoma,arial,Hiragino Sans GB,\\5b8b\4f53,sans-serif;
}

.marvellous-discuss{
  display: flex;
  width: 100%;
  min-height: 120px;
  border-bottom: 1px solid #efefef;
}

#info{
  height: 100%;
  width: 100%;
  box-sizing: border-box;
}

.head{
  width: 70px;
  height: 100%;
  position: relative;
}

.head > img{
  width: 60px;
  position: absolute;
  left: 5px;
  top: 7px;
  border-radius: 50%;
}

.user-info{
  box-sizing: border-box;
  padding: 5px;
  margin: 0 0 0 10px;
  width: 100%;
  height: 25%;
  font-size: 15px;

}

.user-info > a{
  text-decoration: none;
  color: #888;
}

.user-info > a:hover{
  color: #5a5a5a;
}

.discuss-content{
  box-sizing: border-box;
  margin: 0;
  width: 100%;
  overflow: hidden;
  min-height: 55px;
  padding: 5px 15px;
  word-break: break-all;
}

.time-content{
  color: #888;
  font: 16px Microsoft YaHei,tahoma,arial,Hiragino Sans GB,\\5b8b\4f53,sans-serif;
  box-sizing: border-box;
  padding: 5px;
  margin: 0;
  width: 100%;
  height: 30%;
}

.date{
  padding: 0 0 0 10px;
}

.good{
  cursor: pointer;
  float: right;
}

.discuss-not_good{
  width: 16px;
}

.discuss-good{
  width: 20px;
}
</style>
