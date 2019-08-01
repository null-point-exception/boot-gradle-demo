package com.practice.controller;

import com.github.pagehelper.PageInfo;
import com.practice.entity.User;
import com.practice.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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
    @ApiOperation(value = "新增用户", notes = "新增用户")
    @ApiImplicitParam(name = "user", value = "用户信息", required = true, dataType = "User")
    @PostMapping
    public User addUser(@RequestBody User user) {
        return service.addUser(user);
    }

    /**
     * 修改用户
     *
     * @param id   用户id
     * @param user 修改用户信息
     * @return 用户
     */
    @ApiOperation(value = "修改用户", notes = "修改用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "String", example = "891fa31a63ae42f186efd8fcaad65f49"),
            @ApiImplicitParam(name = "user", value = "用户信息", required = true, dataType = "User"),
    })
    @PutMapping("{id}")
    public User editUser(@PathVariable("id") String id, @RequestBody User user) {
        user.setId(id);
        return service.editUser(user);
    }

    /**
     * 根据用户id获取用户详情.
     *
     * @param id 用户ID
     * @return 用户
     */
    @ApiOperation(value = "根据用户id查询用户详情", notes = "根据用户id获取用户详情")
    @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "String", example = "891fa31a63ae42f186efd8fcaad65f49")
    @GetMapping("{id}")
    public User getUserById(@PathVariable("id") String id) {
        return service.findById(id);
    }

    /**
     * 获取用户列表
     *
     * @return 用户列表
     */
    @ApiOperation(value = "获取用户列表", notes = "获取用户列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页", required = true, dataType = "Integer", example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页容量", required = true, dataType = "Integer", example = "5"),
            @ApiImplicitParam(name = "query", value = "查询条件", required = true, dataType = "Map"),
    })
    @GetMapping
    public PageInfo<User> getUsersByPage(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                         @RequestParam(value = "pageSize", defaultValue = "5") int pageSize,
                                         @RequestBody Map<String,Object> query) {

        return service.selectUsersByPage(pageNum, pageSize, query);
    }

    /**
     * 删除用户
     *
     * @param id 用户id
     * @return 删除结果
     */
    @ApiOperation(value = "删除用户", notes = "删除用户")
    @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "String", example = "891fa31a63ae42f186efd8fcaad65f49")
    @DeleteMapping("{id}")
    public int delUserById(@PathVariable("id") String id) {
        return service.delUser(id);
    }

    /**
     * 批量删除用户.
     *
     * @param ids 待删除的用户id集合
     * @return 删除结果
     */
    @ApiOperation(value = "批量删除用户", notes = "批量删除用户")
    @ApiImplicitParam(name = "ids", value = "用户主键集合", allowMultiple = true, required = true, dataType = "String", example = "891fa31a63ae42f186efd8fcaad65f49")
    @DeleteMapping("batch")
    public int delUserByIds(@RequestBody List<String> ids) {
        return service.delUsers(ids);
    }

}
