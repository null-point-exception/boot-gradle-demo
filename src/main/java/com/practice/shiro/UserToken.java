package com.practice.shiro;

import com.practice.bean.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * 自定义用户token.
 *
 * @author kexin.ding
 */
@EqualsAndHashCode(callSuper=false)
@Data
public class UserToken extends UsernamePasswordToken {

    private User user;

    public UserToken(User user) {
        super(user.getName(), user.getPassword(), user.isRememberMe());
        this.user = user;
    }

}
