package ${package.ServiceImpl};

<#if cfg.findPage>
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
</#if>
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${superServiceImplClassPackage};
<#if cfg.findPage|| cfg.selectAll>
import com.practice.aspect.SortAop;
import ${cfg.queryPackage}.${entity + cfg.querySuffix};
import org.springframework.lang.Nullable;
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

<#if cfg.findPage || cfg.selectAll>
    /**
    * 根据查询条件查询${table.comment!} 列表
    *
    * @param query 查询条件
    * @return ${table.comment!} 列表
    */
    @SortAop(typeClass = ${entity}.class)
    public List<${entity}> select${entity}s(@Nullable ${entity + cfg.querySuffix} query){
        return mapper.select${entity}s(query);
    }
</#if>

<#if cfg.findPage>
    /**
    * 根据查询条件查询${table.comment!} 分页
    *
    * @param query 查询条件
    * @return ${table.comment!} 分页
    */
    public PageInfo<${entity}> select${entity}sByPage(int pageNum, int pageSize, @Nullable ${entity + cfg.querySuffix} query) {
        return PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> select${entity}s(query));
    }
</#if>

}