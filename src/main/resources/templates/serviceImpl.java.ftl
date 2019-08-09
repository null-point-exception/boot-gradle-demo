package ${package.ServiceImpl};

<#if cfg.findPage == true>
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
</#if>
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${superServiceImplClassPackage};
<#if cfg.findPage == true || cfg.selectAll == true>
import com.practice.util.QueryUtils;
import ${cfg.queryPackage}.${entity + cfg.querySuffix};
import java.util.List;
</#if>
import javax.annotation.Resource;

/**
 * ${table.comment!} 业务实现类.
 *
 * @author ${author}
 * @since ${date}
 */
@Service
public class ${table.serviceImplName} implements ${superServiceImplClass}<${table.mapperName}, ${entity}> {

    @Resource
    private ${table.mapperName} mapper;
    @Override
    public ${table.mapperName} getMapper() {
        return mapper;
    }

<#if cfg.findPage == true || cfg.selectAll == true>
    /**
    * 根据查询条件查询${table.comment!} 列表
    *
    * @param query 查询条件
    * @return ${table.comment!} 列表
    */
    public List<${entity}> select${entity}s(@Param("query") ${entity + cfg.querySuffix} query){
        QueryUtils.setField(query.getSort(), ${entity}.class);
        return mapper.select${entity}s(query);
    }
</#if>

<#if cfg.findPage == true>
    /**
    * 根据查询条件查询${table.comment!} 分页
    *
    * @param query 查询条件
    * @return ${table.comment!} 分页
    */
    public PageInfo<${entity}> select${entity}sByPage(int pageNum, int pageSize, ${entity + cfg.querySuffix} query) {
        return PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> select${entity}s(query));
    }
</#if>

}