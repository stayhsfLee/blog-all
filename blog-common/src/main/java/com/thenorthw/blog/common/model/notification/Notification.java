package com.thenorthw.blog.common.model.notification;

import java.util.Date;

/**
 * @autuor theNorthW
 * @date 12/10/2017.
 * blog: theNorthW.net
 */
public class Notification {
	Long id;
	Long creator;
	String name;
	String content;
	Integer grammer;
	Date gmtCreate;
	Date gmtModified;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCreator() {
		return creator;
	}

	public void setCreator(Long creator) {
		this.creator = creator;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getGrammer() {
		return grammer;
	}

	public void setGrammer(Integer grammer) {
		this.grammer = grammer;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Date getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}
}
