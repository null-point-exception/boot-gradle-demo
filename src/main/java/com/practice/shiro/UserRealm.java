package com.practice.shiro;

import com.practice.bean.entity.Permission;
import com.practice.bean.entity.Role;
import com.practice.bean.entity.User;
import com.practice.service.impl.LoginService;
import com.practice.util.PasswordHelper;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;

/**
 * 实现AuthorizingRealm接口用户用户认证
 *
 * @author kexin.ding
 */
public class UserRealm extends AuthorizingRealm {

    @Resource
    private LoginService service;

    public UserRealm() {
        //凭证匹配器（由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了）
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        //散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        //散列的次数，比如散列两次，相当于 md5(md5(""));
        hashedCredentialsMatcher.setHashIterations(2);
        this.setCredentialsMatcher(hashedCredentialsMatcher);
    }

    /**
     *  找它的原因是这个方法返回true
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UserToken;
    }

    /**
     * 角色权限和对应权限添加
     *
     * 在需要权限认证时才会进去，比如前面配置类中配置了 filterChainDefinitionMap.put("/admin/**", "roles[admin]"); 的管理员角色
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取登录用户名
        User loginUser = (User) principalCollection.getPrimaryPrincipal();
        //查询用户名称
        User user = service.findByName(loginUser.getName());
        //添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        for (Role role : user.getRoles()) {
            //添加角色
            simpleAuthorizationInfo.addRole(role.getRoleName());
            for (Permission permission : role.getPermissions()) {
                //添加权限
                simpleAuthorizationInfo.addStringPermission(permission.getPermission());
            }
        }

        return simpleAuthorizationInfo;
    }

    /**
     * 用户认证.
     *
     * 身份认证时（比如前面的 Subject.login() 方法）才会进入
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        User loginUser = ((UserToken) token).getUser();
        // 获取用户的输入的账号.
        String username = (String) token.getPrincipal();
        // 通过username从数据库中查找 User对象，如果找到，没找到.
        // 实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        User user = service.findByName(username);
        // 没找到帐号
        if (user == null) {
            throw new UnknownAccountException();
        }

        /*if(Boolean.TRUE.equals(user.getLocked())) {
            throw new LockedAccountException(); //帐号锁定
        }*/
        // 这里盐值可以自定义
        ByteSource credentialsSalt = PasswordHelper.getCredentialsSalt(loginUser.getPassword(), user.getSalt());
        return new SimpleAuthenticationInfo(
                //用户名
                user,
                //密码
                user.getPassword(),
                credentialsSalt,
                //realm name
                getName()
        );
    }

    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }

}