package com.practice.util;

import cn.hutool.core.util.ObjectUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.practice.shiro.JwtProperty;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.ServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Date;

@Component
public class JWTUtil {

    @Autowired
    private JwtProperty configPropertiesAutowired;
    private static JwtProperty property;

    @PostConstruct
    public void init() {
        property = this.configPropertiesAutowired;
    }

    /**
     * 校验token是否正确
     * @param token 密钥
     * @return 是否正确
     */
    public static boolean verify(String token, String userId) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(property.getSecret());
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("userId", userId)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    public static Date getExpiresAt(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getExpiresAt();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 验证token是否过期
     * @param token
     * @return
     */
    public static boolean isTokenExpired(String token) {
        Date expiresAt = getExpiresAt(token);
        return ObjectUtil.isNotNull(expiresAt) && expiresAt.before(new Date());
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     * @return token中包含的用户名
     */
    public static String getUserId(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("userId").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    public static String getAuthzHeader(ServletRequest request) {
        //获取请求头中的 AUTH_TOKEN 的值，如果请求头中有 AUTH_TOKEN 则其值为sessionId。shiro就是通过sessionId 来控制的
        return WebUtils.toHttp(request).getHeader(property.getHeader());
    }

    /**
     * 生成签名,5min后过期
     * @param userId 用户id
     * @return 加密的token
     */
    public static String sign(String userId) {
        try {
            Date date = new Date(System.currentTimeMillis() + property.getExpire());
            System.out.println(date);
            Algorithm algorithm = Algorithm.HMAC256(property.getSecret());
            // 附带username信息
            return JWT.create()
                    .withClaim("userId", userId)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    public static void main(String[] args) throws Exception {

        String sign = sign("root");
        System.out.println(sign);
        Thread.sleep(property.getExpire());
        System.out.println(isTokenExpired(sign));


    }
}
