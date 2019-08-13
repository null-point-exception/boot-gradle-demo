package com.practice.config;

import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

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
                .allowedMethods("GET", "POST", "DELETE", "PUT","PATCH")
                .allowedHeaders("*")
                .maxAge(3600);
    }

}
