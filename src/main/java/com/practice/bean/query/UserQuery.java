package com.practice.bean.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 用户查询模型
 *
 * @author kexin.ding
 */
@ApiModel(value = "UserQuery", description = "用户查询模型")
@Data
public class UserQuery {

    /**
     * 用户名
     */
    @ApiModelProperty(dataType = "String", value = "用户名(支持模糊查询)", name = "name", example = "admin")
    private String name;

    /**
     * 密码
     */
    @ApiModelProperty(dataType = "String", value = "密码", name = "password", example = "123456")
    private String password;

    /**
     * 创建者
     */
    @ApiModelProperty(dataType = "String", value = "创建者", name = "createBy", example = "1")
    private String createBy;

    /**
     * 开始创建时间
     */
    @ApiModelProperty(dataType = "Date", value = "开始创建时间", name = "startCreateDate", example = "2018-10-23 10:00:00")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startCreateDate;

    /**
     * 结束创建时间
     */
    @ApiModelProperty(dataType = "Date", value = "结束创建时间", name = "endCreateDate", example = "2018-10-23 12:00:00")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endCreateDate;

    /**
     * 更新者
     */
    @ApiModelProperty(dataType = "String", value = "更新者", name = "updateBy", example = "1")
    private String updateBy;

    /**
     * 开始更新时间
     */
    @ApiModelProperty(dataType = "Date", value = "开始更新时间", name = "startUpdateDate", example = "2018-10-23 10:00:00")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startUpdateDate;

    /**
     * 结束更新时间
     */
    @ApiModelProperty(dataType = "Date", value = "结束更新时间", name = "endUpdateDate", example = "2018-10-23 12:00:00")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endUpdateDate;

    /**
     * 删除标志
     */
    @ApiModelProperty(dataType = "String", value = "删除标志", name = "delFlag", example = "0")
    private String delFlag;

    /**
     * 排序
     */
    @ApiModelProperty(position = 99, dataType = "Sort", value = "排序", name = "sort")
    private Sort sort;

}
