# boot-gradle-demo
![springboot-2.1](https://img.shields.io/badge/springboot-2.1-brightgreen.svg?style=plastic)
![gradle-5.5](https://img.shields.io/badge/gradle-5.5-brightgreen.svg?style=plastic)
![mysql-5.7](https://img.shields.io/badge/mysql-5.7-brightgreen.svg?style=plastic)
![mybatisplus-3.1](https://img.shields.io/badge/mybatisplus-3.1-brightgreen.svg?style=plastic)
![swagger-2.9](https://img.shields.io/badge/swagger-2.9-brightgreen.svg?style=plastic)
![pagehepler-1.2](https://img.shields.io/badge/pagehepler-1.2-brightgreen.svg?style=plastic)

### gradle注意事项
- settings.gradle是全局配置
- build.gradle中字符串中如果引用变量时，使用双引号（"）和${}，如：
`testCompile "junit:junit:${junitVersion}"`

### vue-cli安装步骤
- node安装，验证 `node -v`
- 淘宝npm镜像 `npm install -g cnpm --registry=https://registry.npm.taobao.org`
- 全局vue-cli脚手架 `cnpm install --global vue-cli`,验证 `vue`出现vue信息
- 初始化项目 `vue init webpack frontend`(frontend是要创建的前端项目文件夹)
![控制台](https://user-images.githubusercontent.com/33916350/62928030-4dfe0200-bdea-11e9-902c-c82cfeccd4df.png)

### frontend前端模块
 - 重命名vue默认模板中构建脚本`build`文件夹为`build-scripts`，主要是因为gradle的默认构建输出目录是build为了减少配置而修改了vue
 构建脚本的目录，我感觉这是最简单方便的，用idea重命名后会自动修改受影响的地方，需要注意package.json中路径问题：
 ![package.json](https://user-images.githubusercontent.com/33916350/62998526-67f91c80-be9e-11e9-82c2-ee00a70ed95e.png)
 
### 前后端融合
 - 全局settings.gradle中添加前后端模块
 ```gradle
 include 'src'
 include 'frontend'
 ```
 
 - 在前端(front)根目录创建build.gradle用于打包。修改config/index.js路径配置：
 ```js
 var assetsRoot = path.resolve(__dirname, '../build/resources/main/frontend')
 ```
 dev开发模式添加后端端口代理：
 ```
  proxyTable: {
       '/api/**': 'http://localhost:8080'
     },
 ```
 build部署模式更改打包位置：
 ```
    // Template for index.html
    index: path.resolve(assetsRoot, 'index.html'),

    // Paths
    assetsRoot: assetsRoot,
```

 - 在后端(src)的配置文件application.yml中配置静态访问资源的位置：
 ```yml
 spring.resources.static-locations: classpath:/frontend/
 ```
 
 - 执行gradle的build命令打包即可
