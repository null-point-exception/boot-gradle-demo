package com.practice.shiro;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.practice.base.Result;
import com.practice.util.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * jwt权限过滤器.
 *
 * @author kexin.ding
 */
@Slf4j
public class JwtAuthFilter extends AuthenticatingFilter {

    /**
     * 父类会在请求进入拦截器后调用该方法，返回true则继续，返回false则会调用onAccessDenied()。这里在不通过时，还调用了isPermissive()方法，我们后面解释。
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (this.isLoginRequest(request, response)) {
            return true;
        }
        boolean allowed = false;
        try {
            allowed = executeLogin(request, response);
        } catch (IllegalStateException e) {
            log.error("Not found any token");
        } catch (Exception e) {
            log.error("Error occurs when login", e);
        }
        return allowed || super.isPermissive(mappedValue);
    }

    /**
     * 这里重写了父类的方法，使用我们自己定义的Token类，提交给shiro。这个方法返回null的话会直接抛出异常，进入isAccessAllowed（）的异常处理逻辑。
     */
    @Override
    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) {
        String jwt = JWTUtil.getAuthzHeader(servletRequest);
        if (StrUtil.isNotBlank(jwt) && !JWTUtil.isTokenExpired(jwt)) {
            return new JsonWebToken(jwt);
        }
        return null;
    }

    /**
     * 如果这个Filter在之前isAccessAllowed（）方法中返回false,则会进入这个方法。我们这里直接返回错误的response
     */
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) {
        Result<String> result = Result.fail("登录已过期,请重新登陆");
        String jsonStr = JSON.toJSONString(result);
        PrintWriter out = null;
        try {
            HttpServletResponse httpResponse = WebUtils.toHttp(servletResponse);
            httpResponse.setHeader("Access-Control-Allow-Origin", "*");
            httpResponse.setCharacterEncoding("UTF-8");
            httpResponse.setContentType("application/json;charset=UTF-8");
            httpResponse.setStatus(HttpServletResponse.SC_NON_AUTHORITATIVE_INFORMATION);
            out = httpResponse.getWriter();
            out.write(jsonStr);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != out) {
                out.flush();
                out.close();
            }
        }
        return false;
    }

    /**
     * 如果Shiro Login认证成功，会进入该方法，等同于用户名密码登录成功
     */
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) {
        return true;
    }

    /**
     * 如果调用shiro的login认证失败，会回调这个方法，这里我们什么都不做，因为逻辑放到了onAccessDenied（）中。
     */
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        log.error("Validate token fail, token:{}, error:{}", token.toString(), e.getMessage());
        return false;
    }
}
