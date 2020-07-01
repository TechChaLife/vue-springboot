import Vue from 'vue'
import 'normalize.css/normalize.css'// css样式的初始化
import ElementUI from 'element-ui' //配置element UI
import 'element-ui/lib/theme-chalk/index.css'
import locale from 'element-ui/lib/locale/lang/zh-CN' //引入elementUI语言包
import App from './App'
import router from './router'
import store from './store'
import '@/icons' // icon
import '@/permission' // 权限
import {default as api} from './utils/api'
import {hasPermission} from "./utils/hasPermission";
Vue.use(ElementUI, {locale})
Vue.prototype.api = api
//全局的常量
Vue.prototype.hasPerm = hasPermission
//生产环境时自动设置为 false 以阻止 vue 在启动时生成生产提示。
Vue.config.productionTip = (process.env.NODE_ENV != 'production')
new Vue({
  el: '#app',
  router,
  store,
  template: '<App/>',
  components: {App}
})
