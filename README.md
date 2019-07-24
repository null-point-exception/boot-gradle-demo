# boot-gradle-demo
springboot2.1+gradle5.5+mysql5.7+mybatisplus3.1

### gradle注意事项
- settings.gradle是全局配置
- build.gradle中字符串中如果引用变量时，使用双引号（"）和${}，如：
`testCompile "junit:junit:${junitVersion}"`
