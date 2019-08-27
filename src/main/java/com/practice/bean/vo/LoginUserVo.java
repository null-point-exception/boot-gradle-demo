package com.practice.bean.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 登录用户vo.
 *
 * @author kexin.ding
 */
@ApiModel(value = "LoginUserVo", description = "登录用户信息展示模型")
@Data
public class LoginUserVo {

    @ApiModelProperty(position = 1, required = true, dataType = "String", value = "用户名", name = "name", example = "root")
    private String name;

    @ApiModelProperty(position = 2, required = true, dataType = "String", value = "密码", name = "password", example = "root")
    private String password;

}
