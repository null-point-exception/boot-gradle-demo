package com.practice.controller;

import com.practice.entity.User;
import com.practice.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户接口
 *
 * @author kexin.ding
 */
@RestController
@RequestMapping("api/user")
public class UserApi {

    @Resource
    private UserService service;

    private static List<User> users = new ArrayList<>();

    @PostConstruct
    private void init() {
        User user1 = new User();
        user1.setId("1");
        user1.setName("admin");
        user1.setPassword("admin");
        users.add(user1);

        User user2 = new User();
        user2.setId("2");
        user2.setName("root");
        user2.setPassword("root");
        users.add(user2);
    }

    /**
     * 新增用户
     *
     * @param user 新增用户信息
     * @return 用户
     */
    @PostMapping
    public User addUser(@RequestBody User user) {
        users.add(user);
        return user;
    }

    /**
     * 新增用户
     *
     * @param id   用户id
     * @param user 新增用户信息
     * @return 用户
     */
    @PutMapping("{id}")
    public User editUser(@PathVariable("id") String id, @RequestBody User user) {
        User u = this.getUserById(id);
        if (u == null) {
            return u;
        }
        if (null != user.getName()) {
            u.setName(user.getName());
        }
        if (null != user.getPassword()) {
            u.setPassword(user.getPassword());
        }
        return u;
    }

    /**
     * 根据id获取用户详情
     *
     * @param id 用户ID
     * @return 用户
     */
    @GetMapping("{id}")
    public User getUserById(@PathVariable("id") String id) {
        return service.findById(id);
    }

    /**
     * 获取用户列表
     *
     * @return 用户
     */
    @GetMapping
    public List<User> getUsers() {
        return users;
    }

    /**
     * 删除用户
     *
     * @param id 用户id
     * @return 用户
     */
    @DeleteMapping("{id}")
    public User delUserById(@PathVariable("id") String id) {
        User user = this.getUserById(id);
        if (null != user) {
            users.remove(user);
        }
        return user;
    }

}
