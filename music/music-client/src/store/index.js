import Vue from 'vue'
import Vuex from 'vuex'
import state from './state'
import actions from './actions'
import songSheet from './modules/songsheet'
import mutations from './mutations'
import getters from './getters'

Vue.use(Vuex)

export default new Vuex.Store({
  state: state,
  mutations: mutations,
  actions: actions,
  getters: getters,
  modules: {
    songSheet
  }
})