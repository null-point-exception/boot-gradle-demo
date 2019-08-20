package com.practice.bean.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * 用户.
 *
 * @author kexin.ding
 */
@ApiModel(value = "User", description = "用户信息实体模型")
@Data
public class User {

    @ApiModelProperty(position = 0, required = true, dataType = "String", value = "用户ID（主键，新增可不传）", name = "id", example = "891fa31a63ae42f186efd8fcaad65f49")
    @TableId(type = IdType.UUID)
    private String id;

    @ApiModelProperty(position = 1, required = true, dataType = "String", value = "用户名", name = "name", example = "admin")
    private String name;

    @ApiModelProperty(position = 2, required = true, dataType = "String", value = "密码", name = "password", example = "123456")
    private String password;

    @ApiModelProperty(position = 3, required = true, dataType = "String", value = "盐值", name = "salt", example = "123456")
    private String salt;

    @ApiModelProperty(position = 4, required = false, dataType = "String", value = "创建者", name = "createBy", example = "1")
    private String createBy;

    @ApiModelProperty(position = 5, required = true, dataType = "Date", value = "创建时间", name = "createDate", example = "2018-10-23 12:00:00")
    // 页面写入数据库时格式化
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private Date createDate;

    @ApiModelProperty(position = 6, required = false, dataType = "String", value = "更新者", name = "updateBy", example = "1")
    private String updateBy;

    @ApiModelProperty(position = 7, required = true, dataType = "Date", value = "更新时间", name = "updateDate", example = "2018-10-23 12:00:00")
    // 页面写入数据库时格式化
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateDate;

    @ApiModelProperty(position = 8, required = false, dataType = "String", value = "删除标志", name = "delFlag", example = "0")
    private String delFlag;


    @ApiModelProperty(position = 9, required = false, dataType = "List", value = "角色", name = "roles")
    @TableField(exist = false)
    private List<Role> roles;

    @ApiModelProperty(position = 10, required = false, dataType = "boolean", value = "记住我", name = "rememberMe")
    @TableField(exist = false)
    private boolean rememberMe = false;

}
