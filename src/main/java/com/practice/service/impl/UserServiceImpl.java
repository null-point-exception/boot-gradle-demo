package com.practice.service.impl;

import com.practice.dao.UserMapper;
import com.practice.entity.User;
import com.practice.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户信息业务实现类
 *
 * @author kexin.ding
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper mapper;

    @Override
    public User findById(String id) {
        return mapper.selectById(id);
    }
}
