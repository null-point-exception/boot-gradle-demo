package com.practice.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.practice.entity.User;
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
     * 查询 : 根据delFlag删除标志查询用户列表
     *
     * @param delFlag 删除标志
     * @return 用户列表
     */
    List<User> selectUsers(@Param("delFlag") String delFlag);

}
