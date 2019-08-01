package com.practice.service;

import com.github.pagehelper.PageInfo;
import com.practice.entity.User;

import java.util.List;
import java.util.Map;

/**
 * 用户信息业务层
 *
 * @author kexin.ding
 */
public interface UserService {

    /**
     * 新增用户.
     *
     * @param user 待新增的用户
     * @return 新增的用户
     */
    User addUser(User user);

    /**
     * 修改用户.
     *
     * @param user 待修改的用户
     * @return 修改的用户
     */
    User editUser(User user);

    /**
     * 删除用户.
     *
     * @param id 待删除的用户id
     * @return 删除结果
     */
    int delUser(String id);

    /**
     * 批量删除用户.
     *
     * @param ids 待删除的用户id集合
     * @return 删除结果
     */
    int delUsers(List<String> ids);

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
    List<User> selectUsers(Map<String,Object> query);

    /**
     * 分页查询用户列表.
     *
     * @return 分页对象
     */
    PageInfo<User> selectUsersByPage(int pageNum, int pageSize, Map<String,Object> query);

}
