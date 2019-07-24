package com.practice.entity;

import lombok.Data;

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
}
