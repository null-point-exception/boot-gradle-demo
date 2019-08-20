package com.practice.controller;

import com.github.pagehelper.PageInfo;
import com.practice.base.Result;
import com.practice.service.impl.PermissionService;
import com.practice.bean.entity.Permission;
import com.practice.bean.query.PermissionQuery;
import java.util.List;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
* 权限信息接口.
*
* @author dkx
* @since 2019-08-15
*/
@Api(tags = "B03 权限信息接口")
@RestController
@RequestMapping("api/permission")
public class PermissionApi {

    @Resource
    private PermissionService service;

    /**
    * 新增权限
    *
    * @param entity 新增权限信息
    * @return 权限
    */
    @ApiOperation("新增权限")
    @ApiImplicitParam(name = "entity", value = "权限信息", required = true, dataType = "Permission")
    @PostMapping
    public Result addPermission(@RequestBody Permission entity) {

        Permission data = service.add(entity);
        if (null == data) {
            return Result.fail("新增权限失败");
        }
        return Result.success("新增权限成功", data);
    }

    /**
    * 修改权限
    *
    * @param id   权限id
    * @param entity 修改权限信息
    * @return 权限
    */
    @ApiOperation("修改权限")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "权限id", required = true, dataType = "String", example = "891fa31a63ae42f186efd8fcaad65f49"),
        @ApiImplicitParam(name = "entity", value = "权限信息", required = true, dataType = "Permission"),
    })
    @PutMapping("{id}")
    public Result editPermission(@PathVariable("id") String id, @RequestBody Permission entity) {
        entity.setId(id);
        Permission data = service.edit(entity);
        if (null == data) {
            return Result.fail("修改权限失败");
        }
        return Result.success("修改权限成功", data);
    }

    /**
    * 根据权限id获取权限详情.
    *
    * @param id 权限ID
    * @return 权限
    */
    @ApiOperation("根据权限id查询权限详情")
    @ApiImplicitParam(name = "id", value = "权限id", required = true, dataType = "String", example = "891fa31a63ae42f186efd8fcaad65f49")
    @GetMapping("{id}")
    public Result<Permission> getPermissionById(@PathVariable("id") String id) {
        Permission data = service.findById(id);
        return Result.success("根据权限id获取权限详情成功", data);
    }

    /**
    * 条件查询权限列表
    *
    * @return 权限列表
    */
    @ApiOperation("条件查询权限列表")
    @ApiImplicitParam(name = "query", value = "查询条件", required = true, dataType = "PermissionQuery")
    @PostMapping("query")
    public Result<List<Permission>> getPermissions(@RequestBody PermissionQuery query) {
    List<Permission> list = service.selectPermissions(query);
    return Result.success("条件查询权限列表成功", list);
    }

    /**
    * 分页查询权限列表
    *
    * @return 权限列表
    */
    @ApiOperation("分页查询权限列表")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "pageNum", value = "当前页", dataType = "int", example = "1"),
        @ApiImplicitParam(name = "pageSize", value = "页容量", dataType = "int", example = "5"),
        @ApiImplicitParam(name = "query", value = "查询条件", required = true, dataType = "PermissionQuery"),
    })
    @PostMapping("page")
    public Result<PageInfo<Permission>> getPermissionsByPage(
        @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
        @RequestParam(value = "pageSize", required = false, defaultValue = "5") int pageSize,
        @RequestBody PermissionQuery query) {
        PageInfo<Permission> page = service.selectPermissionsByPage(pageNum, pageSize, query);
        return Result.success("分页查询权限列表成功", page);
    }

    /**
    * 删除权限.
    *
    * @param id 权限id
    * @return 删除结果
    */
    @ApiOperation("删除权限")
    @ApiImplicitParam(name = "id", value = "权限id", required = true, dataType = "String", example = "891fa31a63ae42f186efd8fcaad65f49")
    @DeleteMapping("{id}")
    public Result<String> delPermissionById(@PathVariable("id") String id) {
        int n = service.delete(id);
        if (n == 0) {
            return Result.fail("删除权限失败");
        }
        return Result.success("删除权限成功");
    }

    /**
    * 批量删除权限.
    *
    * @param ids 待删除的权限id集合
    * @return 删除结果
    */
    @ApiOperation("批量删除权限")
    @ApiImplicitParam(name = "ids", value = "权限主键集合", allowMultiple = true, required = true, dataType = "String", example = "891fa31a63ae42f186efd8fcaad65f49")
    @DeleteMapping(value = "batch", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result delPermissionByIds(@RequestBody List<String> ids) {
        int n = service.deletes(ids);
        if (n < ids.size()) {
            return Result.fail("删除权限失败", n);
        }
        return Result.success("删除权限成功");
    }

}