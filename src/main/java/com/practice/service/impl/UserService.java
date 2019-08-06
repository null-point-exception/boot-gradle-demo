package com.practice.service.impl;

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
        QueryUtils.setField(query.getSort(), User.class);
        return mapper.selectUsers(query);
    }

    public PageInfo<User> selectUsersByPage(int pageNum, int pageSize, UserQuery query) {
        return PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> selectUsers(query));
    }

}
