package com.practice.service;

import com.practice.entity.User;

/**
 * 用户信息业务层
 *
 * @author kexin.ding
 */
public interface UserService {

    /**
     * 根据id查询用户详情.
     *
     * @param id 用户id
     * @return 用户详情
     */
    User findById(String id);

}
