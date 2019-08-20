package com.practice.bean.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 权限查询对象.
 * </p>
 *
 * @author dkx
 * @since 2019-08-15
 */
@Data
@ApiModel(value = "PermissionQuery", description = "权限查询对象")
public class PermissionQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    @ApiModelProperty(position = 1, required = false, value = "权限名", dataType = "String", name = "permission", example = "")
    private String permission;

    @ApiModelProperty(position = 2, required = false, value = "角色id", dataType = "String", name = "roleId", example = "")
    private String roleId;

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