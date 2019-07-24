package com.practice.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.practice.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户映射mapper.
 *
 * @author kexin.ding
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
