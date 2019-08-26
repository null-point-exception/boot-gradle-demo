package com.practice.shiro;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * jwt属性.
 *
 * @author kexin.ding
 */
@Component
@Data
@ConfigurationProperties(prefix = "jwt")
public class JwtProperty {

    /**
     * 加密秘钥
     */
    private String secret;

    /**
     * token有效时长，单位秒
     */
    private long expire;

    /**
     * header字段
     */
    private String header;

}
