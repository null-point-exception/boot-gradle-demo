package com.practice.shiro;

import cn.hutool.core.util.StrUtil;
import com.practice.bean.entity.User;
import com.practice.service.impl.LoginService;
import com.practice.util.JWTUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;

/**
 * jwt shiro认证.
 *
 * @author kexin.ding
 */
public class JwtShiroRealm extends AuthorizingRealm {

    @Resource
    private LoginService service;

    public JwtShiroRealm() {
        //这里使用我们自定义的Matcher
        this.setCredentialsMatcher((auth, info) -> {
            String token = (String) auth.getCredentials();
            User user = (User) info.getPrincipals().getPrimaryPrincipal();
            return JWTUtil.verify(token, user.getId());
        });
    }

    /**
     * 限定这个Realm只支持我们自定义的JWT Token
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JsonWebToken;
    }

    /**
     * 更controller登录一样，也是获取用户的salt值，给到shiro，由shiro来调用matcher来做认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        JsonWebToken jwt = (JsonWebToken) authcToken;
        String token = jwt.getToken();

        String userId = JWTUtil.getUserId(token);
        if (StrUtil.isBlank(userId)) {
            throw new AuthenticationException("token验证失败，请重新登录");
        }
        User user = service.findById(userId);
        if (user == null) {
            throw new AuthenticationException("token过期，请重新登录");
        }

        return new SimpleAuthenticationInfo(user, user.getSalt(), getName());
    }

    /**
     * 没有存储角色信息，所以直接返回空
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return new SimpleAuthorizationInfo();
    }
}
