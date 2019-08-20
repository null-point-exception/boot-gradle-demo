package com.practice.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.practice.bean.entity.Permission;
import com.practice.bean.query.PermissionQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 权限映射mapper.
 *
 * @author dkx
 * @since 2019-08-15
 */
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {

    /**
     * 根据 ID 查询
     *
     * @param id 主键ID
     */
    Permission selectById(@Param("id") String id);

    /**
     * 根据查询条件查询权限列表
     *
     * @param query 查询条件
     * @return 权限列表
     */
    List<Permission> selectPermissions(@Param("query") PermissionQuery query);

}