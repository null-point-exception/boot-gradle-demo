package com.practice.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import com.practice.handler.SolarMetaObjectHandler;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

/**
 * mybatis-plus配置.
 *
 * @author kexin.ding
 */
@EnableTransactionManagement
@Configuration
@MapperScan("com.practice.dao*")
public class MybatisPlusConfig {

    /**
     * mybatis-plus SQL执行效率插件【生产环境可以关闭】
     * 打印 sql
     */
    @Bean
    public PerformanceInterceptor performanceInterceptor() {
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        //格式化sql语句
        Properties properties = new Properties();
        properties.setProperty("format", "true");
        performanceInterceptor.setProperties(properties);

        return performanceInterceptor;
    }

    @Bean
    public MetaObjectHandler metaObjectHandler() {
        return new SolarMetaObjectHandler();
    }

}