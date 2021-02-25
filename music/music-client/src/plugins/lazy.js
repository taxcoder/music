import Vue from 'vue'
import VueLazyLoad from "vue-lazyload";

export default() => {
	Vue.use(VueLazyLoad, {
		preLoad: 1,
		loading: require('@/assets/img/lazy/load.gif'),
		error: require('@/assets/img/lazy/error.png'),
		attempt: 2
	})
}
