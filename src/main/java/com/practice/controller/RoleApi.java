package com.practice.controller;

import com.github.pagehelper.PageInfo;
import com.practice.base.Result;
import com.practice.service.impl.RoleService;
import com.practice.bean.entity.Role;
import com.practice.bean.query.RoleQuery;
import java.util.List;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
* 角色信息接口.
*
* @author dkx
* @since 2019-08-15
*/
@Api(tags = "A02 角色信息接口")
@RestController
@RequestMapping("api/role")
public class RoleApi {

    @Resource
    private RoleService service;

    /**
    * 新增角色
    *
    * @param entity 新增角色信息
    * @return 角色
    */
    @ApiOperation("新增角色")
    @ApiImplicitParam(name = "entity", value = "角色信息", required = true, dataType = "Role")
    @PostMapping
    public Result addRole(@RequestBody Role entity) {

        Role data = service.add(entity);
        if (null == data) {
            return Result.fail("新增角色失败");
        }
        return Result.success("新增角色成功", data);
    }

    /**
    * 修改角色
    *
    * @param id   角色id
    * @param entity 修改角色信息
    * @return 角色
    */
    @ApiOperation("修改角色")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "角色id", required = true, dataType = "String", example = "891fa31a63ae42f186efd8fcaad65f49"),
        @ApiImplicitParam(name = "entity", value = "角色信息", required = true, dataType = "Role"),
    })
    @PutMapping("{id}")
    public Result editRole(@PathVariable("id") String id, @RequestBody Role entity) {
        entity.setId(id);
        Role data = service.edit(entity);
        if (null == data) {
            return Result.fail("修改角色失败");
        }
        return Result.success("修改角色成功", data);
    }

    /**
    * 根据角色id获取角色详情.
    *
    * @param id 角色ID
    * @return 角色
    */
    @ApiOperation("根据角色id查询角色详情")
    @ApiImplicitParam(name = "id", value = "角色id", required = true, dataType = "String", example = "891fa31a63ae42f186efd8fcaad65f49")
    @GetMapping("{id}")
    public Result<Role> getRoleById(@PathVariable("id") String id) {
        Role data = service.findById(id);
        return Result.success("根据角色id获取角色详情成功", data);
    }

    /**
    * 条件查询角色列表
    *
    * @return 角色列表
    */
    @ApiOperation("条件查询角色列表")
    @ApiImplicitParam(name = "query", value = "查询条件", required = true, dataType = "RoleQuery")
    @PostMapping("query")
    public Result<List<Role>> getRoles(@RequestBody RoleQuery query) {
    List<Role> list = service.selectRoles(query);
    return Result.success("条件查询角色列表成功", list);
    }

    /**
    * 分页查询角色列表
    *
    * @return 角色列表
    */
    @ApiOperation("分页查询角色列表")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "pageNum", value = "当前页", dataType = "int", example = "1"),
        @ApiImplicitParam(name = "pageSize", value = "页容量", dataType = "int", example = "5"),
        @ApiImplicitParam(name = "query", value = "查询条件", required = true, dataType = "RoleQuery"),
    })
    @PostMapping("page")
    public Result<PageInfo<Role>> getRolesByPage(
        @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
        @RequestParam(value = "pageSize", required = false, defaultValue = "5") int pageSize,
        @RequestBody RoleQuery query) {
        PageInfo<Role> page = service.selectRolesByPage(pageNum, pageSize, query);
        return Result.success("分页查询角色列表成功", page);
    }

    /**
    * 删除角色.
    *
    * @param id 角色id
    * @return 删除结果
    */
    @ApiOperation("删除角色")
    @ApiImplicitParam(name = "id", value = "角色id", required = true, dataType = "String", example = "891fa31a63ae42f186efd8fcaad65f49")
    @DeleteMapping("{id}")
    public Result<String> delRoleById(@PathVariable("id") String id) {
        int n = service.delete(id);
        if (n == 0) {
            return Result.fail("删除角色失败");
        }
        return Result.success("删除角色成功");
    }

    /**
    * 批量删除角色.
    *
    * @param ids 待删除的角色id集合
    * @return 删除结果
    */
    @ApiOperation("批量删除角色")
    @ApiImplicitParam(name = "ids", value = "角色主键集合", allowMultiple = true, required = true, dataType = "String", example = "891fa31a63ae42f186efd8fcaad65f49")
    @DeleteMapping(value = "batch", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result delRoleByIds(@RequestBody List<String> ids) {
        int n = service.deletes(ids);
        if (n < ids.size()) {
            return Result.fail("删除角色失败", n);
        }
        return Result.success("删除角色成功");
    }

}