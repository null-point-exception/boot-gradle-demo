package ${package.Mapper};

<#if cfg.selectAll || cfg.findPage>
import ${cfg.queryPackage}.${entity + cfg.querySuffix};
import java.util.List;
import org.apache.ibatis.annotations.Param;
</#if>
import ${package.Entity}.${entity};
import ${superMapperClassPackage};
import org.apache.ibatis.annotations.Mapper;

/**
* ${table.comment!}映射mapper.
*
* @author ${author}
* @since ${date}
*/
@Mapper
public interface ${table.mapperName} extends ${superMapperClass}<${entity}> {

<#if cfg.selectAll || cfg.findPage>
    /**
    * 根据查询条件查询${table.comment!}列表
    *
    * @param query 查询条件
    * @return ${table.comment!}列表
    */
    List<${entity}> select${entity}s(@Param("query")${entity + cfg.querySuffix} query);
</#if>

}