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

