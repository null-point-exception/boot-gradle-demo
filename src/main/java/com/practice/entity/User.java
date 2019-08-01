package com.practice.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 用户.
 *
 * @author kexin.ding
 */
@ApiModel(value = "User", description = "路口信息实体模型")
@Data
public class User {

    /**
     * 用户ID
     */
    @ApiModelProperty(position = 1, required = false, dataType = "String", value = "用户ID（主键，新增可不传）", name = "id", example = "891fa31a63ae42f186efd8fcaad65f49")
    private String id;

    /**
     * 用户名
     */
    @ApiModelProperty(position = 2, required = false, dataType = "String", value = "用户名", name = "name", example = "admin")
    private String name;

    /**
     * 密码
     */
    @ApiModelProperty(position = 3, required = false, dataType = "String", value = "密码", name = "password", example = "123456")
    private String password;

    /**
     * 创建者
     */
    @ApiModelProperty(position = 4, required = false, dataType = "String", value = "创建者", name = "createBy", example = "1")
    private String createBy;

    /**
     * 创建时间
     */
    @ApiModelProperty(position = 5, required = false, dataType = "Date", value = "创建时间", name = "createDate", example = "2018-10-23 12:00:00")
    // 页面写入数据库时格式化
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    /**
     * 更新者
     */
    @ApiModelProperty(position = 6, required = false, dataType = "String", value = "更新者", name = "updateBy", example = "1")
    private String updateBy;

    /**
     * 更新时间
     */
    @ApiModelProperty(position = 7, required = false, dataType = "Date", value = "更新时间", name = "updateDate", example = "2018-10-23 12:00:00")
    // 页面写入数据库时格式化
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateDate;

    /**
     * 删除标志
     */
    @ApiModelProperty(position = 8, required = false, dataType = "String", value = "删除标志", name = "delFlag", example = "0")
    private String delFlag;

}
