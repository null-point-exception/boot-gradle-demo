package ${mapperPackage};

<#if selectAll == true || findPage == true>
import ${queryPackage}.${model + querySuffix};
</#if>
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ${entityPackage}.${model + entitySuffix};
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* ${desc}映射mapper.
*
* @author ${author}
* @since ${date}
*/
@Mapper
public interface ${model + mapperSuffix} extends BaseMapper<${model + entitySuffix}> {

<#if selectAll == true || findPage == true>
    /**
    * 根据查询条件查询${desc}列表
    *
    * @param query 查询条件
    * @return ${desc}列表
    */
    List<${model + entitySuffix}> select${model}s(@Param("query") ${model + querySuffix} query);
</#if>

}