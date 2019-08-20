package com.practice.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.practice.bean.entity.User;
import com.practice.bean.query.UserQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户映射mapper.
 *
 * @author kexin.ding
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据 ID 查询
     *
     * @param id 主键ID
     */
    User selectById(@Param("id") String id);

    /**
     * 根据 用户名 查询
     *
     * @param name 用户名
     */
    User findByName(@Param("name") String name);

    /**
     * 根据查询条件查询用户列表
     *
     * @param query 查询条件
     * @return 用户列表
     */
    List<User> selectUsers(@Param("query") UserQuery query);

}
