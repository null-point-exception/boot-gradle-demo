// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue';
import App from './App';
import router from './router';
import axios from 'axios';
import ElementUI from 'element-ui';
import {Message} from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

// http request 拦截器
axios.interceptors.request.use(
  config => {
    var token = window.localStorage.getItem('authorization');//我的token 存储在 localStorage中
    // 如果 xtoken 存在 就设置请求头
    if (token) {
      //xtoken = 'Bearer' + xtoken
      console.log(token)
      config.headers['authorization'] = token//配置请求头中  Authorization 字段的值为拿到的token
    }
    return config;
  },
  err => {
    Message.error({message: '请求超时!'});
    return Promise.reject(err);
  }
  );

axios.interceptors.response.use(data=> {
  if (data.data.code != 0) {
    Message.error({message: data.data.data || data.data.message});
    window.location.href = '#/';
    return ;
  }
  return data.data;
}, err=> {
  if (err.response.status == 504||err.response.status == 404) {
    Message.error({message: '服务器被吃了⊙﹏⊙∥'});
  } else if (err.response.status == 403) {
    Message.error({message: '权限不足,请联系管理员!'});
  }else {
    Message.error({message: '未知错误!'});
  }
  return Promise.resolve(err);
});
Vue.use(ElementUI);
Vue.prototype.$axios = axios
Vue.config.productionTip = false


/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  // components: { App },
  // template: '<App/>',
  render: h => h(App),
})
