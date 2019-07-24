package com.practice.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
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

    /**
     * 查询用户列表.
     *
     * @return 用户列表
     */
    IPage<User> selectUserPage(IPage<User> page);

}
