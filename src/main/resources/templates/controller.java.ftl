package ${package.Controller};

<#if cfg.findPage>
import com.github.pagehelper.PageInfo;
</#if>
import com.practice.base.Result;
import ${package.ServiceImpl}.${table.serviceImplName};
import ${package.Entity}.${entity};
<#if cfg.findPage || cfg.selectAll>
import ${cfg.queryPackage}.${entity + cfg.querySuffix};
import java.util.List;
</#if>
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
* ${table.comment!}信息接口.
*
* @author ${author}
* @since ${date}
*/
@Api(tags = "${table.comment!}信息接口")
@RestController
@RequestMapping("<#if cfg.urlPrefix??>${cfg.urlPrefix}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table
.entityPath}</#if>")
public class ${table.controllerName} {

    @Resource
    private ${table.serviceImplName} service;

    /**
    * 新增${table.comment!}
    *
    * @param entity 新增${table.comment!}信息
    * @return ${table.comment!}
    */
    @ApiOperation("新增${table.comment!}")
    @ApiImplicitParam(name = "entity", value = "${table.comment!}信息", required = true, dataType = "${entity}")
    @PostMapping
    public Result add${entity}(@RequestBody ${entity} entity) {

        ${entity} data = service.add(entity);
        if (null == data) {
            return Result.fail("新增${table.comment!}失败");
        }
        return Result.success("新增${table.comment!}成功", data);
    }

    /**
    * 修改${table.comment!}
    *
    * @param id   ${table.comment!}id
    * @param entity 修改${table.comment!}信息
    * @return ${table.comment!}
    */
    @ApiOperation("修改${table.comment!}")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "${table.comment!}id", required = true, dataType = "String", example = "891fa31a63ae42f186efd8fcaad65f49"),
        @ApiImplicitParam(name = "entity", value = "${table.comment!}信息", required = true, dataType = "${entity}"),
    })
    @PutMapping("{id}")
    public Result edit${entity}(@PathVariable("id") String id, @RequestBody ${entity} entity) {
        entity.setId(id);
        ${entity} data = service.edit(entity);
        if (null == data) {
            return Result.fail("修改${table.comment!}失败");
        }
        return Result.success("修改${table.comment!}成功", data);
    }

    /**
    * 根据${table.comment!}id获取${table.comment!}详情.
    *
    * @param id ${table.comment!}ID
    * @return ${table.comment!}
    */
    @ApiOperation("根据${table.comment!}id查询${table.comment!}详情")
    @ApiImplicitParam(name = "id", value = "${table.comment!}id", required = true, dataType = "String", example = "891fa31a63ae42f186efd8fcaad65f49")
    @GetMapping("{id}")
    public Result<${entity}> get${entity}ById(@PathVariable("id") String id) {
        ${entity} data = service.findById(id);
        return Result.success("根据${table.comment!}id获取${table.comment!}详情成功", data);
    }

<#if cfg.selectAll>
    /**
    * 条件查询${table.comment!}列表
    *
    * @return ${table.comment!}列表
    */
    @ApiOperation("条件查询${table.comment!}列表")
    @ApiImplicitParam(name = "query", value = "查询条件", required = true, dataType = "${entity + cfg.querySuffix}")
    @PostMapping("query")
    public Result<List<${entity}>> get${entity}s(@RequestBody ${entity + cfg.querySuffix} query) {
    List<${entity}> list = service.select${entity}s(query);
    return Result.success("条件查询${table.comment!}列表成功", list);
    }
</#if>

<#if cfg.findPage>
    /**
    * 分页查询${table.comment!}列表
    *
    * @return ${table.comment!}列表
    */
    @ApiOperation("分页查询${table.comment!}列表")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "pageNum", value = "当前页", dataType = "int", example = "1"),
        @ApiImplicitParam(name = "pageSize", value = "页容量", dataType = "int", example = "5"),
        @ApiImplicitParam(name = "query", value = "查询条件", required = true, dataType = "${entity + cfg.querySuffix}"),
    })
    @PostMapping("page")
    public Result<PageInfo<${entity}>> get${entity}sByPage(
        @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
        @RequestParam(value = "pageSize", required = false, defaultValue = "5") int pageSize,
        @RequestBody ${entity + cfg.querySuffix} query) {
        PageInfo<${entity}> page = service.select${entity}sByPage(pageNum, pageSize, query);
        return Result.success("分页查询${table.comment!}列表成功", page);
    }
</#if>

    /**
    * 删除${table.comment!}.
    *
    * @param id ${table.comment!}id
    * @return 删除结果
    */
    @ApiOperation("删除${table.comment!}")
    @ApiImplicitParam(name = "id", value = "${table.comment!}id", required = true, dataType = "String", example = "891fa31a63ae42f186efd8fcaad65f49")
    @DeleteMapping("{id}")
    public Result<String> del${entity}ById(@PathVariable("id") String id) {
        int n = service.delete(id);
        if (n == 0) {
            return Result.fail("删除${table.comment!}失败");
        }
        return Result.success("删除${table.comment!}成功");
    }

    /**
    * 批量删除${table.comment!}.
    *
    * @param ids 待删除的${table.comment!}id集合
    * @return 删除结果
    */
    @ApiOperation("批量删除${table.comment!}")
    @ApiImplicitParam(name = "ids", value = "${table.comment!}主键集合", allowMultiple = true, required = true, dataType = "String", example = "891fa31a63ae42f186efd8fcaad65f49")
    @DeleteMapping(value = "batch", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result del${entity}ByIds(@RequestBody List<String> ids) {
        int n = service.deletes(ids);
        if (n < ids.size()) {
            return Result.fail("删除${table.comment!}失败", n);
        }
        return Result.success("删除${table.comment!}成功");
    }

}