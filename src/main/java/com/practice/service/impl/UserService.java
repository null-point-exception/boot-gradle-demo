package com.practice.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.practice.bean.entity.User;
import com.practice.bean.query.UserQuery;
import com.practice.dao.UserMapper;
import com.practice.service.BaseService;
import com.practice.util.QueryUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户信息业务实现类
 *
 * @author kexin.ding
 */
@Service
public class UserService extends BaseService<UserMapper, User> {

    public List<User> selectUsers(UserQuery query) {
        /*QueryWrapper<User> queryWrapper = new QueryWrapper<>();
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
        List<User> list = mapper.selectList(queryWrapper);*/
        QueryUtils.setField(query.getSort(), User.class);
        return mapper.selectUsers(query);
    }

    public PageInfo<User> selectUsersByPage(int pageNum, int pageSize, UserQuery query) {
        return PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> selectUsers(query));
    }

}
