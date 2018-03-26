package com.thenorthw.blog.face.form.user;

import com.thenorthw.blog.common.utils.ShortUUIDUtil;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 *
 * @author theNorthW
 * @date 19/07/2017
 * blog: thenorthw.com
 *
 */
public class UserRegisterForm {
    //account
    @NotEmpty
    @Email  //使用邮箱进行注册并且激活
    String loginname;
    @NotEmpty
    String password;

    //user_profile
    String nickname = "Aron" + ShortUUIDUtil.randomUUID();
    String sex = "3";

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

}
