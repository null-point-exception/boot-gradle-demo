package com.practice.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.practice.aspect.SortAop;
import com.practice.bean.entity.User;
import com.practice.bean.query.UserQuery;
import com.practice.dao.UserMapper;
import com.practice.service.BaseService;
import com.practice.util.NumberUtils;
import com.practice.util.PasswordHelper;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户信息业务实现类
 *
 * @author kexin.ding
 */
@Service
public class UserService implements BaseService<UserMapper, User> {

    @Resource
    private UserMapper mapper;

    @Override
    public UserMapper getMapper() {
        return mapper;
    }

    @Override
    public User add(User user) {
        user.setId(NumberUtils.genUUID());
        user.setSalt(NumberUtils.genUUID(5));
        user.setPassword(PasswordHelper.encryptPassword(user.getPassword(), user.getSalt()));
        mapper.insert(user);
        // todo: 级联添加role和permission
        return user;
    }

    @SortAop(typeClass = User.class)
    public List<User> selectUsers(@Nullable UserQuery query) {
        return mapper.selectUsers(query);
    }

    public User findByName(String name) {
        Assert.notNull(name);
        return mapper.findByName(name);
    }

    public PageInfo<User> selectUsersByPage(int pageNum, int pageSize, @Nullable UserQuery query) {
        return PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> selectUsers(query));
    }

}
