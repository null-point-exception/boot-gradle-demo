<template>
  <el-form>
    <h1>你好，{{currentUser.name}}，欢迎登录本系统！<el-button type="danger" @click="logout()" plain>退出</el-button></h1>
    <el-link type="primary" href="#/users">用户列表</el-link>
  </el-form>
</template>

<script>
export default {
  name: 'Index',
  data () {
    return {
      currentUser:{},
    }
  },
  methods: {
    getCurrentUser () {
      let self = this
      // 请求后台
      this.$axios({
        method: 'get',
        url: '/api/user-info',
      }).then((result) => {
        self.currentUser = result.data;
      })
    },
    logout() {
      //window.localStorage.removeItem('authToken');
      let self = this
      // 请求后台
      this.$axios({
        method: 'get',
        url: 'http://localhost:8080/logout',
      }).then((result) => {
          window.localStorage.removeItem('authToken');
          window.location.href = '#/';
      })
    },
  },mounted() {
    this.getCurrentUser();
  }
}
</script>
