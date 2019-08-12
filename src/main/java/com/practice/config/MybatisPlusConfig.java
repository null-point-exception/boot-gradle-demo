package com.practice.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import com.practice.handler.SolarMetaObjectHandler;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
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

    @Value("${mybatis-plus.configuration.sql-format}")
    private boolean sqlFormat;

    /**
     * mybatis-plus SQL执行效率插件【生产环境可以关闭】
     * 打印 sql
     */
    @ConditionalOnProperty(name = "mybatis-plus.configuration.sql-format", havingValue = "true")
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