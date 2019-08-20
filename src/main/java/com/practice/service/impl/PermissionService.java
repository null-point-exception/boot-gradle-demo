package com.practice.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import com.practice.bean.entity.Permission;
import com.practice.dao.PermissionMapper;
import com.practice.service.BaseService;
import com.practice.aspect.SortAop;
import com.practice.bean.query.PermissionQuery;
import org.springframework.lang.Nullable;
import java.util.List;
import javax.annotation.Resource;

/**
 * 权限 业务实现类.
 *
 * @author dkx
 * @since 2019-08-15
 */
@Service
public class PermissionService implements BaseService<PermissionMapper, Permission> {

    @Resource
    private PermissionMapper mapper;
    @Override
    public PermissionMapper getMapper() {
        return mapper;
    }

    /**
    * 根据查询条件查询权限 列表
    *
    * @param query 查询条件
    * @return 权限 列表
    */
    @SortAop(typeClass = Permission.class)
    public List<Permission> selectPermissions(@Nullable PermissionQuery query){
        return mapper.selectPermissions(query);
    }

    /**
    * 根据查询条件查询权限 分页
    *
    * @param query 查询条件
    * @return 权限 分页
    */
    public PageInfo<Permission> selectPermissionsByPage(int pageNum, int pageSize, @Nullable PermissionQuery query) {
        return PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> selectPermissions(query));
    }

}