package com.practice.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import com.practice.bean.entity.Role;
import com.practice.dao.RoleMapper;
import com.practice.service.BaseService;
import com.practice.aspect.SortAop;
import com.practice.bean.query.RoleQuery;
import org.springframework.lang.Nullable;
import java.util.List;
import javax.annotation.Resource;

/**
 * 角色 业务实现类.
 *
 * @author dkx
 * @since 2019-08-15
 */
@Service
public class RoleService implements BaseService<RoleMapper, Role> {

    @Resource
    private RoleMapper mapper;
    @Override
    public RoleMapper getMapper() {
        return mapper;
    }

    /**
    * 根据查询条件查询角色 列表
    *
    * @param query 查询条件
    * @return 角色 列表
    */
    @SortAop(typeClass = Role.class)
    public List<Role> selectRoles(@Nullable RoleQuery query){
        return mapper.selectRoles(query);
    }

    /**
    * 根据查询条件查询角色 分页
    *
    * @param query 查询条件
    * @return 角色 分页
    */
    public PageInfo<Role> selectRolesByPage(int pageNum, int pageSize, @Nullable RoleQuery query) {
        return PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> selectRoles(query));
    }

}