package com.practice.config;

import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * swagger配置.
 *
 * @author dkx
 */
@Getter
@Setter
@Configuration
@EnableSwagger2
@ConditionalOnProperty(name = "swagger.enable", havingValue = "true")
@ConfigurationProperties(prefix = "swagger")
public class Swagger2Config {

    private String title;
    private String description;
    private String termsOfServiceUrl;
    private String version;
    private String publishDate;
    private String active;

    @Bean
    public Docket createRestApi() {
        return docket("ALL", ".+");
    }

    @Bean
    public Docket ADocket() {
        return docket("A_登录", "^\\/(login|logout)$");
    }

    @Bean
    public Docket bDocket() {
        return docket("B_基础", "^\\/api/(user|role|permission)\\/.*");
    }

    private Docket docket(String groupName, String pathRegex) {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .directModelSubstitute(LocalDate.class, String.class).genericModelSubstitutes(ResponseEntity.class)
                .useDefaultResponseMessages(false)
                //.globalResponseMessage(RequestMethod.POST, customerResponseMessage())
                //.globalResponseMessage(RequestMethod.GET, customerResponseMessage())
                .forCodeGeneration(true)
                .groupName(groupName)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.practice.controller"))
                .paths(PathSelectors.regex(pathRegex))
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }

    private List<ApiKey> securitySchemes() {
        List<ApiKey> apiKeyList= new ArrayList<>();
        apiKeyList.add(new ApiKey("令牌", "authToken", "header"));
        return apiKeyList;
    }

    private List<SecurityContext> securityContexts() {
        List<SecurityContext> securityContexts=new ArrayList<>();
        securityContexts.add(
                SecurityContext.builder()
                        .securityReferences(defaultAuth())
                        //.forPaths(PathSelectors.any())
                        .forPaths(PathSelectors.regex("^\\/api/.*"))
                        .build());
        return securityContexts;
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        List<SecurityReference> securityReferences=new ArrayList<>();
        securityReferences.add(new SecurityReference("Authorization", authorizationScopes));
        return securityReferences;
    }

    private ApiInfo apiInfo() {
        String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        if (StringUtils.isEmpty(publishDate)) {
            publishDate = now;
        }
        if (StringUtils.isEmpty(active)) {
            active = "default";
        }
        return (new ApiInfoBuilder()).title(title).description(description + "<br/>部署时间：" + publishDate + "<br/>启动时间：" + now + "<br/>部署模式：" + active).termsOfServiceUrl(termsOfServiceUrl).version(version).build();
    }
}
