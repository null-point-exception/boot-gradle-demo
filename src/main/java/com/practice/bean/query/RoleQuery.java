package com.practice.bean.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 角色查询对象.
 * </p>
 *
 * @author dkx
 * @since 2019-08-15
 */
@Data
@ApiModel(value = "RoleQuery", description = "角色查询对象")
public class RoleQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(position = 1, required = false, value = "角色名", dataType = "String", name = "roleName", example = "")
    private String roleName;

    @ApiModelProperty(position = 2, required = false, value = "用户id", dataType = "String", name = "userId", example = "")
    private String userId;

    @ApiModelProperty(position = 3, required = false, value = "创建者", dataType = "String", name = "createBy", example = "")
    private String createBy;

    @ApiModelProperty(position = 4, required = false, value = "创建时间", dataType = "Date", name = "createDate", example = "2018-01-02 08:00:00")
    private Date createDate;

    @ApiModelProperty(position = 5, required = false, value = "更新者", dataType = "String", name = "updateBy", example = "")
    private String updateBy;

    @ApiModelProperty(position = 6, required = false, value = "更新时间", dataType = "Date", name = "updateDate", example = "2018-01-02 08:00:00")
    private Date updateDate;


    @ApiModelProperty(position = 99, dataType = "Sort", value = "排序", name = "sort")
    private Sort sort;

}