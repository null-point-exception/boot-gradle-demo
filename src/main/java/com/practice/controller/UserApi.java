package com.practice.controller;

import com.github.pagehelper.PageInfo;
import com.practice.base.Result;
import com.practice.bean.entity.User;
import com.practice.bean.query.UserQuery;
import com.practice.service.impl.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户接口.
 *
 * @author kexin.ding
 */
@Api(tags = "A01 用户信息接口")
@RestController
@RequestMapping("api/user")
public class UserApi {

    @Resource
    private UserService service;

    /**
     * 新增用户
     *
     * @param user 新增用户信息
     * @return 用户
     */
    @ApiOperation("新增用户")
    @ApiImplicitParam(name = "user", value = "用户信息", required = true, dataType = "User")
    @PostMapping
    public Result addUser(@RequestBody User user) {

        User data = service.add(user);
        if (null == data) {
            return Result.fail("新增用户失败");
        }
        return Result.success("新增用户成功", data);
    }

    /**
     * 修改用户
     *
     * @param id   用户id
     * @param user 修改用户信息
     * @return 用户
     */
    @ApiOperation("修改用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "String", example = "891fa31a63ae42f186efd8fcaad65f49"),
            @ApiImplicitParam(name = "user", value = "用户信息", required = true, dataType = "User"),
    })
    @PutMapping("{id}")
    public Result editUser(@PathVariable("id") String id, @RequestBody User user) {
        user.setId(id);
        User data = service.edit(user);
        if (null == data) {
            return Result.fail("修改用户失败");
        }
        return Result.success("修改用户成功", data);
    }

    /**
     * 根据用户id获取用户详情.
     *
     * @param id 用户ID
     * @return 用户
     */
    @ApiOperation("根据用户id查询用户详情")
    @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "String", example = "891fa31a63ae42f186efd8fcaad65f49")
    @GetMapping("{id}")
    public Result<User> getUserById(@PathVariable("id") String id) {
        User data = service.findById(id);
        return Result.success("根据用户id获取用户详情成功", data);
    }

    /**
     * 分页查询用户列表
     *
     * @return 用户列表
     */
    @ApiOperation("分页查询用户列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页", dataType = "int", example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页容量", dataType = "int", example = "5"),
            @ApiImplicitParam(name = "query", value = "查询条件", required = true, dataType = "UserQuery"),
    })
    @PostMapping("page")
    public Result<PageInfo<User>> getUsersByPage(
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "5") int pageSize,
            @RequestBody UserQuery query) {
        PageInfo<User> page = service.selectUsersByPage(pageNum, pageSize, query);
        return Result.success("分页查询用户列表成功", page);
    }

    /**
     * 条件查询用户列表
     *
     * @return 用户列表
     */
    @ApiOperation("条件查询用户列表")
    @ApiImplicitParam(name = "query", value = "查询条件", required = true, dataType = "UserQuery")
    @PostMapping("query")
    public Result<List<User>> getUsers(@RequestBody UserQuery query) {
        List<User> list = service.selectUsers(query);
        return Result.success("条件查询用户列表成功", list);
    }

    /**
     * 删除用户.
     *
     * @param id 用户id
     * @return 删除结果
     */
    @ApiOperation("删除用户")
    @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "String", example = "891fa31a63ae42f186efd8fcaad65f49")
    @DeleteMapping("{id}")
    public Result<String> delUserById(@PathVariable("id") String id) {
        int n = service.delete(id);
        if (n == 0) {
            return Result.fail("删除用户失败");
        }
        return Result.success("删除用户成功");
    }

    /**
     * 批量删除用户.
     *
     * @param ids 待删除的用户id集合
     * @return 删除结果
     */
    @ApiOperation("批量删除用户")
    @ApiImplicitParam(name = "ids", value = "用户主键集合", allowMultiple = true, required = true, dataType = "String", example = "891fa31a63ae42f186efd8fcaad65f49")
    @DeleteMapping(value = "batch", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result delUserByIds(@RequestBody List<String> ids) {
        int n = service.deletes(ids);
        if (n < ids.size()) {
            return Result.fail("删除用户失败", n);
        }
        return Result.success("删除用户成功");
    }

}
