package com.practice.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.practice.bean.entity.Role;
import com.practice.bean.query.RoleQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色映射mapper.
 *
 * @author dkx
 * @since 2019-08-15
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 根据 ID 查询
     *
     * @param id 主键ID
     */
    Role selectById(@Param("id") String id);

    /**
     * 根据查询条件查询角色列表
     *
     * @param query 查询条件
     * @return 角色列表
     */
    List<Role> selectRoles(@Param("query") RoleQuery query);

}