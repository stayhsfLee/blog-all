package com.thenorthw.blog.face.form.user;

import javax.validation.constraints.NegativeOrZero;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

/**
 * @autuor theNorthW
 * @date 18/09/2017.
 * blog: thenorthw.com
 */
public class UserInfoFetchForm {
	@Positive
	String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
