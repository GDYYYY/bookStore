import Vue from 'vue'
import App from './App.vue'
import axios from 'axios'
import VueAxios from 'vue-axios'

Vue.prototype.axios = axios;
Vue.use(VueAxios, axios);
// 引入路由
import router from "./router.js"    // import router 的router 一定要小写， 不要写成Router, 否则报 can't match的报错
new Vue({
  el: '#app',
  router,  // 注入到根实例中
  render: h => h(App)
})