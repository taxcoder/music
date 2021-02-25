import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import plugins from './plugins/element'
import lazy from './plugins/lazy'
import {storage} from './plugins/utils'

Vue.config.productionTip = false

plugins()
lazy()
storage.dispatchEventStorage()

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
