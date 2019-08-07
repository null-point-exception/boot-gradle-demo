package ${controllerPackage};

import com.github.pagehelper.PageInfo;
import com.practice.base.Result;
import ${servicePackage}.${model + serviceSuffix};
import ${entityPackage}.${model + entitySuffix};
<#if findPage == true || selectAll == true>
import ${queryPackage}.${model + querySuffix};
</#if>
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* ${desc}信息接口.
*
* @author ${author}
* @since ${date}
*/
@Api(tags = "${desc}信息接口")
@RestController
@RequestMapping("${url}")
public class ${model + controllerSuffix} {

    @Resource
    private ${model + serviceSuffix} service;

    /**
    * 新增${desc}
    *
    * @param entity 新增${desc}信息
    * @return ${desc}
    */
    @ApiOperation("新增${desc}")
    @ApiImplicitParam(name = "entity", value = "${desc}信息", required = true, dataType = "${model + entitySuffix}")
    @PostMapping
    public Result add${model}(@RequestBody ${model + entitySuffix} entity) {

        ${model + entitySuffix} data = service.add(entity);
        if (null == data) {
            return Result.fail("新增${desc}失败");
        }
        return Result.success("新增${desc}成功", data);
    }

    /**
    * 修改${desc}
    *
    * @param id   ${desc}id
    * @param entity 修改${desc}信息
    * @return ${desc}
    */
    @ApiOperation("修改${desc}")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "${desc}id", required = true, dataType = "String", example = "891fa31a63ae42f186efd8fcaad65f49"),
        @ApiImplicitParam(name = "entity", value = "${desc}信息", required = true, dataType = "${model + entitySuffix}"),
    })
    @PutMapping("{id}")
    public Result edit${model}(@PathVariable("id") String id, @RequestBody ${model + entitySuffix} entity) {
        entity.setId(id);
        ${model + entitySuffix} data = service.edit(entity);
        if (null == data) {
            return Result.fail("修改${desc}失败");
        }
        return Result.success("修改${desc}成功", data);
    }

    /**
    * 根据${desc}id获取${desc}详情.
    *
    * @param id ${desc}ID
    * @return ${desc}
    */
    @ApiOperation("根据${desc}id查询${desc}详情")
    @ApiImplicitParam(name = "id", value = "${desc}id", required = true, dataType = "String", example = "891fa31a63ae42f186efd8fcaad65f49")
    @GetMapping("{id}")
    public Result<${model + entitySuffix}> get${model}ById(@PathVariable("id") String id) {
        ${model + entitySuffix} data = service.findById(id);
        return Result.success("根据${desc}id获取${desc}详情成功", data);
    }

<#if selectAll == true>
    /**
    * 条件查询${desc}列表
    *
    * @return ${desc}列表
    */
    @ApiOperation("条件查询${desc}列表")
    @ApiImplicitParams({
    @ApiImplicitParam(name = "query", value = "查询条件", required = true, dataType = "${model + querySuffix}"),
    })
    @PostMapping("query")
    public Result<List<${model + entitySuffix}>> get${model}sByPage(@RequestBody ${model + querySuffix} query) {
    List<${model + entitySuffix}> list = service.select${model}s(query);
    return Result.success("条件查询${desc}列表成功", list);
    }
</#if>

<#if findPage == true>
    /**
    * 分页查询${desc}列表
    *
    * @return ${desc}列表
    */
    @ApiOperation("分页查询${desc}列表")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "pageNum", value = "当前页", dataType = "int", example = "1"),
        @ApiImplicitParam(name = "pageSize", value = "页容量", dataType = "int", example = "5"),
        @ApiImplicitParam(name = "query", value = "查询条件", required = true, dataType = "${model + querySuffix}"),
    })
    @PostMapping("page")
    public Result<PageInfo<${model + entitySuffix}>> get${model}sByPage(
        @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
        @RequestParam(value = "pageSize", required = false, defaultValue = "5") int pageSize,
        @RequestBody ${model + querySuffix} query) {
        PageInfo<${model + entitySuffix}> page = service.select${model}sByPage(pageNum, pageSize, query);
        return Result.success("分页查询${desc}列表成功", page);
    }
</#if>

    /**
    * 删除${desc}.
    *
    * @param id ${desc}id
    * @return 删除结果
    */
    @ApiOperation("删除${desc}")
    @ApiImplicitParam(name = "id", value = "${desc}id", required = true, dataType = "String", example = "891fa31a63ae42f186efd8fcaad65f49")
    @DeleteMapping("{id}")
    public Result<String> del${model}ById(@PathVariable("id") String id) {
        int n = service.delete(id);
        if (n == 0) {
            return Result.fail("删除${desc}失败");
        }
        return Result.success("删除${desc}成功");
    }

    /**
    * 批量删除${desc}.
    *
    * @param ids 待删除的${desc}id集合
    * @return 删除结果
    */
    @ApiOperation("批量删除${desc}")
    @ApiImplicitParam(name = "ids", value = "${desc}主键集合", allowMultiple = true, required = true, dataType = "String", example = "891fa31a63ae42f186efd8fcaad65f49")
    @DeleteMapping(value = "batch", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result del${model}ByIds(@RequestBody List<String> ids) {
        int n = service.deletes(ids);
        if (n < ids.size()) {
            return Result.fail("删除${desc}失败", n);
        }
        return Result.success("删除${desc}成功");
    }

}