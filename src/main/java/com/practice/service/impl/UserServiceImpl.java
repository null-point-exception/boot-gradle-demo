package com.practice.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.practice.dao.UserMapper;
import com.practice.entity.User;
import com.practice.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
    public User addUser(User user) {
        return mapper.insert(user) > 0 ? user : null;
    }

    @Override
    public User editUser(User user) {
        return mapper.updateById(user) > 0 ? user : null;
    }

    @Override
    public int delUser(String id) {
        return mapper.deleteById(id);
    }

    @Override
    public int delUsers(List<String> ids) {
        return mapper.deleteBatchIds(ids);
    }

    @Override
    public User findById(String id) {
        return mapper.selectById(id);
    }

    @Override
    public List<User> selectUsers(String delFlag) {
        return mapper.selectUsers(delFlag);
    }

    @Override
    public PageInfo<User> selectUsersByPage(int pageNum, int pageSize, String delFlag) {
        return PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> selectUsers(delFlag));
    }

}
