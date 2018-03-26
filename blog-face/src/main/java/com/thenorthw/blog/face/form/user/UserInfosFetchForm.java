package com.thenorthw.blog.face.form.user;

import javax.validation.constraints.Pattern;

/**
 * @autuor theNorthW
 * @date 18/09/2017.
 * blog: thenorthw.com
 */
public class UserInfosFetchForm {
	@Pattern(regexp = "^([1-9]\\d*)(,([1-9]\\d*))*")
	String userIds;


	public String getUserIds() {
		return userIds;
	}

	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}
}
