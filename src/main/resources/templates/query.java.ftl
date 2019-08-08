package ${cfg.queryPackage};

<#list table.importPackages as pkg>
import ${pkg};
</#list>
import com.practice.bean.query.Sort;
<#if swagger2>
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
</#if>
<#if entityLombokModel>
import lombok.Data;
</#if>

/**
* <p>
* ${table.comment!}查询对象.
* </p>
*
* @author ${author}
* @since ${date}
*/
<#if entityLombokModel>
@Data
</#if>
<#if swagger2>
@ApiModel(value="${entity + cfg.querySuffix}", description="${table.comment!}查询对象")
</#if>
public class ${entity + cfg.querySuffix} implements Serializable {

<#if entitySerialVersionUID>
	private static final long serialVersionUID = 1L;
</#if>

<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.fields as field>
	<#if field.comment!?length gt 0>
		<#if swagger2>
	@ApiModelProperty(position = ${field_index}, required = false, value = "${field.comment}", dataType = "${field.propertyType}", name = "${field.propertyName}", example = "")
		<#else>
	/**
	* ${field.comment}
	*/
		</#if>
	</#if>
	private ${field.propertyType} ${field.propertyName};

</#list>

<#if swagger2>
	@ApiModelProperty(position = 99, dataType = "Sort", value = "排序", name = "sort")
<#else>
	/**
	* 排序
	*/
</#if>
	private Sort sort;

<#------------  END 字段循环遍历  ---------->
}