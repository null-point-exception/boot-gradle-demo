package com.practice.service.impl;

import com.practice.dao.UserMapper;
import com.practice.entity.User;
import com.practice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper mapper;

    @Override
    public User findById(String id) {
        return mapper.findById(id);
    }
}
