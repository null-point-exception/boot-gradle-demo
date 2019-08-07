package ${servicePackage};

<#if findPage == true>
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
</#if>
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import ${entityPackage}.${model + entitySuffix};
<#if findPage == true || selectAll == true>
import com.practice.util.QueryUtils;
import ${queryPackage}.${model + querySuffix};
</#if>
import ${mapperPackage}.${model + mapperSuffix};
import com.practice.service.BaseService;

import javax.annotation.Resource;
import java.util.List;

/**
 * ${desc}信息业务实现类
 *
 * @author ${author}
 * @since ${date}
 */
@Service
public class ${model + serviceSuffix} extends BaseService<${model + mapperSuffix}, ${model + entitySuffix}> {

    @Resource
    private ${model + mapperSuffix} mapper;

<#if findPage == true || selectAll == true>
    /**
    * 根据查询条件查询${desc}列表
    *
    * @param query 查询条件
    * @return ${desc}列表
    */
    public List<${model + entitySuffix}> select${model}s(@Param("query") ${model + querySuffix} query){
        QueryUtils.setField(query.getSort(), ${model + entitySuffix}.class);
        return mapper.select${model}s(query);
    }
</#if>

<#if findPage == true>
    public PageInfo<${model + entitySuffix}> select${model}sByPage(int pageNum, int pageSize, ${model + querySuffix} query) {
        return PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> select${model}s(query));
    }
</#if>

}