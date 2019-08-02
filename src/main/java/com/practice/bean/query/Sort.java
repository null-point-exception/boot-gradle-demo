package com.practice.bean.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 排序查询模型
 *
 * @author kexin.ding
 */
@ApiModel(value = "Sort", description = "排序查询模型")
@Data
public class Sort {

    /**
     * 排序类型
     */
    @ApiModelProperty(required = false, dataType = "TypeEnum", value = "排序方式 ASC-升序，DESC-降序", name = "type", allowableValues = "ASC,DESC", example = "ASC")
    private TypeEnum type;

    /**
     * 排序字段
     */
    @ApiModelProperty(required = true, dataType = "String", value = "排序字段", name = "filed", example = "''")
    private String field;

}
