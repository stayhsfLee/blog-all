package com.thenorthw.blog.face.form.notification;

/**
 * @autuor theNorthW
 * @date 12/10/2017.
 * blog: theNorthW.net
 */
public class NotificationAddForm {
	String name;
	String content;
	String grammar;

	public String getContent() {
		return content;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getGrammar() {
		return grammar;
	}

	public void setGrammar(String grammar) {
		this.grammar = grammar;
	}
}
