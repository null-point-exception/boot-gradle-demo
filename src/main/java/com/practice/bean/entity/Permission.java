package com.practice.bean.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.apache.ibatis.annotations.Many;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 权限
 * </p>
 *
 * @author dkx
 * @since 2019-08-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "Permission", description = "权限")
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(position = 0, required = true, value = "主键", dataType = "String", name = "id", example = "891fa31a63ae42f186efd8fcaad65f49")
    @TableId(type = IdType.UUID)
    private String id;

    @ApiModelProperty(position = 1, required = false, value = "权限名", dataType = "String", name = "permission", example = "")
    private String permission;

    @ApiModelProperty(position = 2, required = false, value = "角色id", dataType = "String", name = "roleId", example = "")
    private String roleId;

    @ApiModelProperty(position = 3, required = false, value = "创建者", dataType = "String", name = "createBy", example = "")
    private String createBy;

    @ApiModelProperty(position = 4, required = false, value = "创建时间", dataType = "Date", name = "createDate", example = "2018-01-02 08:00:00")
    @TableField(fill = FieldFill.INSERT)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    @ApiModelProperty(position = 5, required = false, value = "更新者", dataType = "String", name = "updateBy", example = "")
    private String updateBy;

    @ApiModelProperty(position = 6, required = false, value = "更新时间", dataType = "Date", name = "updateDate", example = "2018-01-02 08:00:00")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate;


    @ApiModelProperty(position = 7, required = false, dataType = "Role", value = "角色", name = "role")
    @TableField(exist = false)
    private Role role;

}
