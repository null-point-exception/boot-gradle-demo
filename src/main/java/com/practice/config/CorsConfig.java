package com.practice.config;

import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 跨域配置.
 *
 * @author kexin.ding
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Value("${originUrls}")
    private String originUrls;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        if (StrUtil.isBlank(originUrls)) {
            return;
        }
        registry.addMapping("/**")
                .allowedOrigins(originUrls.split(","))
                .allowCredentials(true)
                .allowedMethods("OPTIONS", "GET", "POST", "DELETE", "PUT", "PATCH")
                .allowedHeaders("*")
                .maxAge(3600);
    }

    /**
     * 此方法把该拦截器实例化成一个bean,否则在拦截器里无法注入其它bean
     */
    /*@Bean
    SessionInterceptor sessionInterceptor() {
        return new SessionInterceptor();
    }
    *//**
     * 配置拦截器
     *//*
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sessionInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/login","/permission/userInsert",
                        "/error","/tUser/insert","/gif/getGifCode");
    }*/


}
