package com.practice.entity;

import lombok.Data;

import java.util.Date;

/**
 * 用户
 *
 * @author kexin.ding
 */
@Data
public class User {

    /**
     * 用户ID
     */
    private String id;

    /**
     * 用户名
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private Date updateDate;

    /**
     * 删除标志
     */
    private String delFlag;

}
