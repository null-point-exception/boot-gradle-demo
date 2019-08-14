# frontend

> A frontend project based on vue.js

## Build Setup

``` bash
# install dependencies
npm install

# serve with hot reload at localhost:8080
npm run dev

# build for production with minification
npm run build

# build for production and view the bundle analyzer report
npm run build --report
```

For a detailed explanation on how things work, check out the [guide](http://vuejs-templates.github.io/webpack/) and [docs for vue-loader](http://vuejs.github.io/vue-loader).


### 目录结构
```
├── build（改名build-scripts） 项目构建(webpack)相关代码
├── config (Webpack) 配置目录，包括端口号等
├── node_modules	npm加载的项目依赖模块
├── src 这里是我们要开发的目录，基本上要做的事情都在这个目录里
│     ├── assets 放置一些图片，如logo等
│     ├── api
│     ├── components 目录里面放了一个组件文件，可以不用
│     ├── mixins
│     ├── views (or views)
│     ├── router 路由
│     ├── util
│     ├── theme
│     │      └── default.styl
│     └── App.vue 项目入口文件，我们也可以直接将组件写这里，而不使用 components 目录
│     └── event.js
│     └── main.js 项目的核心文件
├── dist
├── release
├── static (or asset) 静态资源目录，如图片、字体等
├── mock (or script to build mock data)
├── test 初始测试目录，可删除
├── README.md 项目的说明文档，markdown 格式
├── package.json 项目配置文件
├── index.html 首页入口文件，你可以添加一些 meta 信息或统计代码啥的
└── .xxxx 这些是一些配置文件，包括语法配置，git配置等
```
