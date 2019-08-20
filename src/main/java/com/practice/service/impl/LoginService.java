package com.practice.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.practice.bean.entity.Permission;
import com.practice.bean.entity.Role;
import com.practice.bean.entity.User;
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

    //查询用户通过用户名
    public User findByName(String name) {
        return userService.findByName(name);
    }
}
