'use strict'
// Template version: 1.3.1
// see http://vuejs-templates.github.io/webpack for documentation.

const path = require('path')

//assetsRoot 将webpack构建的资源输出至build/resources/main/frontend目录，gradle打包会将frontend加入至classpath中，spring查找静态资源有frontend目录区分比较方便。
var assetsRoot = path.resolve(__dirname, '../build/resources/main/frontend')
module.exports = {
  dev: {
    // Paths
    assetsSubDirectory: 'assets',
    assetsPublicPath: '/',
    // 代理配置将/api/** URL下的所有请求转发至服务后端即springboot启动的服务
    proxyTable: {
      '/api/**': 'http://localhost:8080'
    },

    // Various Dev Server settings
    host: 'localhost', // can be overwritten by process.env.HOST
    port: 9981, // can be overwritten by process.env.PORT, if port is in use, a free one will be determined
    autoOpenBrowser: false,
    errorOverlay: true,
    notifyOnErrors: true,
    poll: false, // https://webpack.js.org/configuration/dev-server/#devserver-watchoptions-

    
    /**
     * Source Maps
     */

    // https://webpack.js.org/configuration/devtool/#development
    devtool: 'cheap-module-eval-source-map',

    // If you have problems debugging vue-files in devtools,
    // set this to false - it *may* help
    // https://vue-loader.vuejs.org/en/options.html#cachebusting
    cacheBusting: true,

    cssSourceMap: false
  },

  build: {
    // Template for index.html
    index: path.resolve(assetsRoot, 'index.html'),

    // Paths
    assetsRoot: assetsRoot,
    assetsSubDirectory: 'assets',
    assetsPublicPath: '/',

    /**
     * Source Maps
     */

    productionSourceMap: true,
    // https://webpack.js.org/configuration/devtool/#production
    devtool: '#source-map',

    // Gzip off by default as many popular frontend hosts such as
    // Surge or Netlify already gzip all frontend assets for you.
    // Before setting to `true`, make sure to:
    // npm install --save-dev compression-webpack-plugin
    productionGzip: false,
    productionGzipExtensions: ['js', 'css'],

    // Run the build command with an extra argument to
    // View the bundle analyzer report after build finishes:
    // `npm run build --report`
    // Set to `true` or `false` to always turn it on or off
    bundleAnalyzerReport: process.env.npm_config_report
  }
}
