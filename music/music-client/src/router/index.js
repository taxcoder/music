import Vue from 'vue'
import VueRouter from 'vue-router'
import {errorMessage} from "@/plugins/message";

const Index = () => import('@/views/Index')
const MyMusic = () => import('@/views/MyMusic')
const Singer = () => import('@/views/Singer')
const SongSheet = () => import('@/views/SongSheet')
const UserLogin = () => import('@/views/UserLogin')
const UserRegister = () => import('@/views/UserRegister')
const RetrievePassword = () => import('@/views/RetrievePassword')
const SongSheetContent = () => import('@/views/SongSheetContent')
const SingerContent = () => import('@/views/SingerContent')
const MusicPlayer = () => import('@/views/MusicPlayer')
const SearchData = () => import('@/views/SearchData')

Vue.use(VueRouter)

const routes = [
    // 登录
  {name: 'login', path: '/login', meta: {title: '用户登录', 'login': false}, components: {mainArea: UserLogin}},
    //注册
  {name: 'register', path: '/register', meta: {title: '用户注册','login': false}, components: {mainArea: UserRegister}},
    // 忘记密码
  {name: 'retrievePassword', path: '/retrievePassword', meta: {title: '找回密码','login': false}, components: {mainArea: RetrievePassword}},
    // 重定向到首页
  {path: '', redirect: '/index'},
    // 歌曲播放页面
  {name: 'musicPlayer', path: '/player', meta: {title: '播放歌曲', 'login': false}, components: {mainArea: MusicPlayer}},
  {name: 'search', path: '/search', meta: {title: '查询', 'login': false}, components: {mainArea: SearchData}},
  {
    name: 'index',
    path: '/index',
    meta: {
      title: '发现音乐',
      'login': false
    },
    components: {
      mainArea: Index
    }
  },
  {
    name: 'myMusic',
    path: '/myMusic',
    meta: {
      title: '我的音乐',
      'login': true
    },
    components: {
      mainArea: MyMusic
    }
  },
  {
    name: 'singers',
    path: '/singers',
    meta: {
      title: '发现歌手',
      'login': false
    },
    query: {
      status: 0,
      letter: "流行",
      current: 1,
      "region": 1
    },
    components: {
      mainArea: Singer
    }
  },
  {
    name: 'songSheets',
    path: '/songSheets',
    meta: {
      title: '发现歌单',
      'login': false
    },
    components: {
      mainArea: SongSheet
    }
  },
  {
    name: 'songSheet',
    path: '/songSheet/:data',
    meta: {
      title: '歌单信息',
      'login': false
    },
    components: {
      mainArea: SongSheetContent
    }
  },
  {
    name: 'singer',
    path: '/singer/:data',
    meta: {
      title: '歌手信息',
      'login': false
    },
    components: {
      mainArea: SingerContent
    }
  },
]

const router = new VueRouter({
  routes,
  mode: 'history',
  linkActiveClass: 'active'
})

router.beforeEach((to,from,next)=> {
  document.title = to.meta.title
  let searchType = ['song','singer','album','songSheet']

  if(to.path === '/singers' && (to.query.current === undefined || to.query.status === undefined || to.query.region === undefined || to.query.letter === undefined)){
    next('/singers?current=1&status=0&region=1&letter=流行')
  }


  if(to.path === '/songSheets' && to.query.current === undefined){
    next('/songSheets?allSongSheet=0&current=1')
    return;
  }else if(to.path === '/songSheets' && to.query.tag === undefined && to.query.allSongSheet === undefined){
    next('/songSheets?allSongSheet=0&current=1')
    return;
  }else if(to.path === '/songSheets' && to.query.tag !== undefined && to.query.allSongSheet !== undefined){
    next('/songSheets?allSongSheet=0&current=1')
    return;
  }else if((to.path === '/songSheet/' || to.path === '/songSheet' || to.name === 'songSheet') && to.params.data === undefined){
    next('/songSheets?allSongSheet=0&current=1')
    return;
  }else if((to.path === '/songSheet/' || to.path === '/songSheet' || to.name === 'songSheet') && to.params.data !== undefined && to.query.current === undefined){
    next('/songSheet/'+(to.params.data)+'?current=1')
    return;
  }else if((to.path === '/singer/' || to.path === '/singer' || to.name === 'singer') && to.params.data === undefined){
    next('/singers?current=1&status=0&region=1&letter=流行')
    return;
  }else if((to.path === '/singer/' || to.path === '/singer' || to.name === 'singer') && to.params.data !== undefined && to.query.current === undefined){
    next('/singer/'+(to.params.data)+'?current=1')
    return;
  }else if((to.path === '/search' || to.name === 'search') && (JSON.stringify(to.query) === '{}' || to.query.keyword === undefined || to.query.keyword === '')){
    next('/index')
  }else if((to.path === '/search' || to.name === 'search') && to.query.type === undefined && to.query.page === undefined){
    next('/search?page=1&type=song&keyword='+to.query.keyword)
  }else if((to.path === '/search' || to.name === 'search') && searchType.filter(data => data === to.query.type).length === 0 && !/^[1-9]+$/.test(to.query.page.toString())){
    next('/search?page=1&type=song&keyword='+to.query.keyword)
  }else if((to.path === '/search' || to.name === 'search') && searchType.filter(data => data === to.query.type).length === 0){
    next('/search?page='+to.query.page+'&type=song&keyword='+to.query.keyword)
  }else if((to.path === '/search' || to.name === 'search') && !/^[1-9]+$/.test(to.query.page.toString())){
    next('/search?page=1&type='+to.query.type+'&keyword='+to.query.keyword)
  }

  if(to.matched.length === 0){
    next('/index')
    return;
  }

  let user = JSON.parse(sessionStorage.getItem("user"))
  if(to.matched[0].meta.login === true && (user === undefined || user === null || user.userEmail === undefined || user.userEmail === null)){
    next('/login')
    return;
  }

  if(to.path === '/login' && user !== undefined && user !== null && user.userEmail !== undefined && user.userEmail !== null){
      errorMessage("你已经登录了！")
      next("/index");
      return;
  }

  next();
})

export default router
