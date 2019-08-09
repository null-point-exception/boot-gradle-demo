package com.practice.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.practice.aspect.SortAop;
import com.practice.bean.entity.User;
import com.practice.bean.query.UserQuery;
import com.practice.dao.UserMapper;
import com.practice.service.BaseService;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

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

    @SortAop(typeClass = User.class)
    public List<User> selectUsers(@Nullable UserQuery query) {
        return mapper.selectUsers(query);
    }

    private List<User> selectList(UserQuery query) {
        //单表部分简单查询
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "name");
        LambdaQueryWrapper<User> lambda = queryWrapper.lambda();
        if (StrUtil.isNotBlank(query.getName())) {
            lambda.and(w -> w.like(User::getName, query.getName()));
        }
        if (StrUtil.isNotBlank(query.getCreateBy())) {
            lambda.or(w -> w.eq(User::getCreateBy, query.getCreateBy()));
        }
        if (StrUtil.isNotBlank(query.getUpdateBy())) {
            lambda.or(w -> w.eq(User::getUpdateBy, query.getUpdateBy()));
        }
        return mapper.selectList(queryWrapper);
    }

    public PageInfo<User> selectUsersByPage(int pageNum, int pageSize, @Nullable UserQuery query) {
        return PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> selectUsers(query));
    }

}
