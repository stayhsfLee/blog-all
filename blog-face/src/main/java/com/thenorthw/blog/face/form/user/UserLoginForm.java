package com.thenorthw.blog.face.form.user;

import javax.validation.constraints.*;

/**
 * @autuor  theNorthW
 * @date 16/08/2017.
 * blog: thenorthw.com
 */
public class UserLoginForm {
	@NotNull
	@Email
	String loginname;

	@NotNull
	String password;

	@Min(1)
	@Max(1)
	int logintype;

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

	public int getLogintype() {
		return logintype;
	}

	public void setLogintype(int logintype) {
		this.logintype = logintype;
	}
}
