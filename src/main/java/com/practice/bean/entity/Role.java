package com.practice.bean.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 角色
 * </p>
 *
 * @author dkx
 * @since 2019-08-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "Role", description = "角色")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(position = 0, required = true, value = "主键", dataType = "String", name = "id", example = "891fa31a63ae42f186efd8fcaad65f49")
    @TableId(type = IdType.UUID)
    private String id;

    @ApiModelProperty(position = 1, required = true, value = "角色名", dataType = "String", name = "roleName", example = "")
    private String roleName;

    @ApiModelProperty(position = 2, required = false, value = "用户id", dataType = "String", name = "userId", example = "")
    private String userId;

    @ApiModelProperty(position = 3, required = false, value = "创建者", dataType = "String", name = "createBy", example = "")
    private String createBy;

    @ApiModelProperty(position = 4, required = true, value = "创建时间", dataType = "Date", name = "createDate", example = "2018-01-02 08:00:00")
    @TableField(fill = FieldFill.INSERT)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    @ApiModelProperty(position = 5, required = false, value = "更新者", dataType = "String", name = "updateBy", example = "")
    private String updateBy;

    @ApiModelProperty(position = 6, required = true, value = "更新时间", dataType = "Date", name = "updateDate", example = "2018-01-02 08:00:00")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate;


    @ApiModelProperty(position = 7, required = false, dataType = "User", value = "用户", name = "user")
    @TableField(exist = false)
    private User user;

    @ApiModelProperty(position = 8, required = false, dataType = "List", value = "权限", name = "permissions")
    @TableField(exist = false)
    private List<Permission> permissions;

}
