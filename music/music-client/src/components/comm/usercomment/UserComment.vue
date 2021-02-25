<template>
  <div id="comment">
    <div id="discuss">
      <p id="title">
        <span class="dis">评论</span>
<!--        <span class="all">共{{total}}条评论</span>-->
      </p>
      <div class="content">
        <textarea id="text"  placeholder="期待您的神评论..." @blur="dataBlur" @focus="dataFocus" v-model="input" maxLength="300"></textarea>
        <span class="font">{{ dataLength(input.length) }}</span>
        <button class="discuss">发表评论</button>
      </div>
      <h2 class="comm">敬请期待！</h2>
<!--      <comment-info :marvellous="marvellous">-->
<!--        <template #info>精彩评论({{marvellous.length}})</template>-->
<!--      </comment-info>-->
<!--      <comment-info :marvellous="userDiscuss" :styles="styles" :discussGoods="discussGoods">-->
<!--        <template #info>用户评论({{userDiscuss.length}})</template>-->
<!--      </comment-info>-->
<!--      <div id="loadData" v-if="isLoad">-->
<!--        <span @click="update">加载更多评论<img src="~@/assets/img/indexIco/right.svg" alt="right"></span>-->
<!--      </div>-->
    </div>
    <div>
    </div>
  </div>
</template>

<script>
import CommentInfo from "@/components/comm/usercomment/CommentInfo";
export default {
  name: "UserComment",
  components: {
    CommentInfo
  },
  data(){
    return {
      current: 1,
      input: '',
      styles: 'margin-top:35px;margin-bottom:20px',
      marvellous: [
        {
          'commentId': "zzzzzzzzzzzzzzzz",
          "user":{
            "userId": "sbsbsbsbsbsbsbsb",
            "userName": "谭翔一号",
            "userHeadAddress": require('@/assets/img/operation/background.jpg')
          },
          "commentInfo": "谭翔666",
          "commentDate": "2020年1月16日 21:15",
          "commentGoods": "8",
          "discussTotal": 3
        },
        {
          'commentId': "xxxxxxxxxxxxxxxx",
          "user":{
            "userId": "aaaaaaaaaaaaaaaa",
            "userName": "谭翔二号",
            "userHeadAddress": require('@/assets/img/operation/background.jpg')
          },
          "commentInfo": "谭翔牛逼",
          "commentDate": "2020年10月26日 21:15",
          "commentGoods": "7",
          "discussTotal": 3
        },
        {
          'commentId': "pppppppppppppppp",
          "user":{
            "userId": "cccccccccccccccc",
            "userName": "谭翔三号",
            "userHeadAddress": require('@/assets/img/operation/background.jpg')
          },
          "commentInfo": "谭翔最帅",
          "commentDate": "2020年10月16日 21:15",
          "commentGoods": "5",
          "discussTotal": 3
        }
      ],
      userDiscuss: [
        {
          'commentId': "zzzzzzzzzzzzzzzz",
          "user":{
            "userId": "sbsbsbsbsbsbsbsb",
            "userName": "谭翔一号",
            "userHeadAddress": require('@/assets/img/operation/background.jpg')
          },
          "commentInfo": "谭翔666",
          "commentDate": "2020年1月16日 21:15",
          "commentGoods": "8",
          "discussTotal": 6
        },
        {
          'commentId': "zzzzzzzzzxzzzzzz",
          "user":{
            "userId": "sbsbsbsbsbsbsbsb",
            "userName": "谭翔四号",
            "userHeadAddress": require('@/assets/img/operation/background.jpg')
          },
          "commentInfo": "谭翔666",
          "commentDate": "2020年1月16日 21:15",
          "commentGoods": "8",
          "discussTotal": 6
        },
        {
          'commentId': "zzzzczzzzzzzzzzz",
          "user":{
            "userId": "sbsbsbsbsbsbsbsb",
            "userName": "谭翔五号",
            "userHeadAddress": require('@/assets/img/operation/background.jpg')
          },
          "commentInfo": "谭翔666",
          "commentDate": "2020年1月16日 21:15",
          "commentGoods": "8",
          "discussTotal": 6
        },
        {
          'commentId': "xxxxxxxxxxxxxxxx",
          "user":{
            "userId": "aaaaaaaaaaaaaaaa",
            "userName": "谭翔二号",
            "userHeadAddress": require('@/assets/img/operation/background.jpg')
          },
          "commentInfo": "谭翔牛逼",
          "commentDate": "2020年10月26日 21:15",
          "commentGoods": "7",
          "discussTotal": 6
        },
        {
          'commentId': "pppppppppppppppp",
          "user":{
            "userId": "cccccccccccccccc",
            "userName": "谭翔三号",
            "userHeadAddress": require('@/assets/img/operation/background.jpg')
          },
          "commentInfo": "谭翔最帅",
          "commentDate": "2020年10月16日 21:15",
          "commentGoods": "5",
          "discussTotal": 6
        }
      ],
      discussGoods: [
        {'commentId': 'pppppppppppppppp'}
      ]
    }
  },
  methods: {
    dataBlur($event){
      $event.currentTarget.placeholder = '期待您的神评论...'
    },
    dataFocus($event){
      $event.currentTarget.placeholder = ''
    },
    dataLength(length){
      if(length <= 300){
        return "共"+(300 -length)+"个字"
      }
    },
    update(){
      // 获取新的数据
      this.current = this.current + 1
      // 请求API
      let s = [
        {
          'commentId': "ppppppxxpppppppp",
          "user":{
            "userId": "cccccccccccccccc",
            "userName": "谭翔三号",
            "userHeadAddress": require('@/assets/img/operation/background.jpg')
          },
          "commentInfo": "谭翔777",
          "commentDate": "2020年10月16日 21:15",
          "commentGoods": "5",
          "discussTotal": 6
        },
        {
          'commentId': "ppppppxxppppppzz",
          "user":{
            "userId": "cccccccccccccccc",
            "userName": "谭翔十号",
            "userHeadAddress": require('@/assets/img/operation/background.jpg')
          },
          "commentInfo": "谭翔777",
          "commentDate": "2020年10月16日 21:15",
          "commentGoods": "5",
          "discussTotal": 6
        }
      ]
      this.add(s);
    },
    add(datas){
      if(datas.length === 1){
        this.userDiscuss.push(datas[0])
        console.log(this.userDiscuss)
        return;
      }
      for(let data of datas){
        this.userDiscuss.push(data)
      }
    }
  },
  computed: {
    isLoad(){
      if(this.userDiscuss.length === 0){
        return false
      }
      return this.userDiscuss.length < this.userDiscuss[0].discussTotal
    },
    total(){
      if(this.userDiscuss.length === 0){
        return 0;
      }
      return this.userDiscuss[0].discussTotal
    }
  }
}
</script>

<style scoped>
.comm{
  margin-top: 80px;
  text-align: center;
  font-weight: 500;
  font-size: 18px;
}
#comment{
  width: 100%;
  margin-top: 30px;
}

#discuss{
  width: 100%;
  height: 100%;
}

#title{
  display: flex;
  align-items: center;
  width: 100%;
  height: 40px;
}

#title > .dis{
  font-weight: 700;
  font-size: 24px;
  line-height: 40px;
}

#title > .all{
  font: 15px Microsoft YaHei,tahoma,arial,Hiragino Sans GB,\\5b8b\4f53,sans-serif;
  color: #7f7f7f;
  margin-left: 10px;
  line-height: 40px;
}

#text{
  box-sizing: border-box;
  font-size: 14px;
  padding: 10px;
  border-radius: 3px;
  outline: inherit;
  width: 100%;
  height: 100%;
  position: relative;
  overflow: hidden;
  border: solid 1px #ececec;
  background-color: #f5f5f5;
}

.content{
  position: relative;
  width: 100%;
  height: 100px;
}

.font{
  cursor: alias;
  color: #868686;
  position: absolute;
  right: 5px;
  bottom: 5px;
}

.discuss{
  position: absolute;
  right: 0;
  bottom: -50px;
  margin-top: 5px;
  color: white;
  font-weight: 700;
  border-radius: 5px;
  border: none;
  background-color: #00a800;
  width: 75px;
  outline: inherit;
  cursor: pointer;
  height: 35px;
}

#loadData{
  cursor: pointer;
  display: flex;
  width: 95%;
  border-radius: 50px;
  background-color: #f3f3f3;
  height: 60px;
  justify-content: center;
  margin: 0 auto;
  align-items: center;
}

#loadData > span{
  width: 100px;
  position: relative;
  font-size: 14px;
  color: #888;
}

#loadData span > img{
  width: 16px;
  vertical-align: text-top;
}

#loadData > span:hover:after{
  content: "";
  position: absolute;
  width: 96px;
  top: 22px;
  left: 0;
  height: 1px;
  box-sizing: border-box;
  background: #888;
}
</style>
