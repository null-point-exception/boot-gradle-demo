package com.practice.dao;

import com.practice.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    User findById(String id);

}
