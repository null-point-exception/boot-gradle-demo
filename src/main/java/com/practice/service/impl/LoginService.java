package com.practice.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.practice.bean.entity.Permission;
import com.practice.bean.entity.Role;
import com.practice.bean.entity.User;
import com.practice.util.RedisUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class LoginService {

    @Resource
    private UserService userService;
    @Resource
    private RoleService roleService;
    @Resource
    private RedisUtil util;

    //添加用户
    public User addUser(User user) {
        user.setId(IdWorker.get32UUID());
        userService.add(user);
        return user;
    }

    //添加角色
    public Role addRole(Role role) {
        User user = userService.findById(role.getUserId());
        role.setId(IdWorker.get32UUID());
        role.setUser(user);
        Permission permission1 = new Permission();
        permission1.setPermission("create");
        permission1.setRole(role);
        Permission permission2 = new Permission();
        permission2.setPermission("update");
        permission2.setRole(role);
        List<Permission> permissions = new ArrayList<>();
        permissions.add(permission1);
        permissions.add(permission2);
        role.setPermissions(permissions);
        roleService.add(role);
        return role;
    }

    /**
     * 通过用户名查询用户
     */
    public User findByName(String name) {
        return userService.findByName(name);
    }

    /**
     * 通过用户id查询用户
     */
    public User findById(String id) {
        User user = (User)util.get(id);
        if (null == user) {
            user = userService.findById(id);
            util.set(id, user);
        }
        return user;
    }

    /**
     * 注销
     */
    public void logout() {
        User currentUser = (User)SecurityUtils.getSubject().getPrincipal();
        if (currentUser != null) {
            util.del(currentUser.getId());
        }
        SecurityUtils.getSubject().logout();
    }

}
