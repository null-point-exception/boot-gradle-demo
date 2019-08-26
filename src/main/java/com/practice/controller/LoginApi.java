package com.practice.controller;

import com.practice.base.Result;
import com.practice.bean.entity.User;
import com.practice.shiro.UserToken;
import com.practice.service.impl.LoginService;
import com.practice.util.JWTUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.Serializable;

/**
 * 登录相关接口.
 *
 * @author kexin.ding
 */
@Api(tags = "A00 登录接口")
@RestController
public class LoginApi {

    @Resource
    private LoginService service;

    @ApiOperation("登录")
    @ApiImplicitParam(name = "user", value = "用户信息", required = true, dataType = "User", example = "{name:\"root\",password:\"root\"}")
    @PostMapping("login")
    @ResponseBody
    public Result login(@RequestBody User user) throws IOException {
        //token信息
        Serializable tokenId = null;
        try {
            Subject currentUser = SecurityUtils.getSubject();
            AuthenticationToken token = new UserToken(user);
            currentUser.login(token);
            //Shiro认证通过后会将user信息放到subject内，生成token并返回
            User u = (User) currentUser.getPrincipal();
            //token信息
            tokenId = JWTUtil.sign(u.getId());
        } catch (UnknownAccountException e) {
            return Result.fail("用户不存在");
        } catch (IncorrectCredentialsException e) {
            return Result.fail("账号或密码不正确");
        } catch (LockedAccountException e) {
            return Result.fail("账号已被锁定,请联系管理员");
        } catch (AuthenticationException e) {
            return Result.fail("账户验证失败");
        }

        return Result.success("登录成功", tokenId);
    }

    /**
     * 获取当前登录用户信息.
     */
    @ApiOperation("获取当前登录用户信息")
    @GetMapping("api/user-info")
    public Result userInfo() {
        Subject currentUser = SecurityUtils.getSubject();
        User u = (User)currentUser.getPrincipal();
        u.setPassword(null);
        u.setSalt(null);
        return Result.success("获取当前登录用户信息成功", u);
    }

    /**
     * 退出
     */
    @ApiOperation("退出")
    @GetMapping("logout")
    public Result logout() {
        service.logout();
        return Result.success("注销成功");
    }
}
