import Vue from 'vue'
import App from './App.vue'
import ElementUI from 'element-ui'
import VueRouter from 'vue-router'
import router from './router'
import * as echarts from 'echarts';
import axios from 'axios'
import JsonExcel from 'vue-json-excel'
import VueWechatTitle from 'vue-wechat-title'

Vue.use(VueWechatTitle)


Vue.component('downloadExcel', JsonExcel)

Vue.prototype.$http = axios
Vue.prototype.$echarts = echarts

axios.defaults.baseURL = 'http://localhost:8070/'
axios.defaults.timeout = 5000 

Vue.config.productionTip = false

Vue.use(VueRouter)
Vue.use(ElementUI)

new Vue({
  render: h => h(App),
  router: router,
}).$mount('#app')
