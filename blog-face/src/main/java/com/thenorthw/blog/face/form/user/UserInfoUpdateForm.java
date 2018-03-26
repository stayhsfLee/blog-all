package com.thenorthw.blog.face.form.user;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @autuor theNorthW
 * @date 18/09/2017.
 * blog: thenorthw.com
 */
public class UserInfoUpdateForm {
	@Size(max = 18)
	String nick;

	@Pattern(regexp = "1|2|3")
	String sex;

	@Size(max = 218)
	String introduction;

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
}
