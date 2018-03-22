package com.thenorthw.blog.face.dto;

import com.thenorthw.blog.common.model.article.Article;
import com.thenorthw.blog.common.model.article.ArticleContent;

import java.util.Date;

/**
 * @autuor theNorthW
 * @date 05/10/2017.
 * blog: theNorthW.net
 */
public class ArticleDto {
	Long id;
	String name;
	Long userId;
	Integer view;
	Integer grammar;
	Date gmtCreate;
	Date gmtModified;

	Integer length;
	String content;

	public ArticleDto(Article a1, ArticleContent a2){
		this.id = a1.getId();
		this.name = a1.getName();
		this.content = a2.getContent();
		this.userId = a1.getUserId();
		this.view = a1.getView();
		this.grammar = a1.getGrammar();
		this.gmtCreate = a1.getGmtCreate();
		this.gmtModified = a1.getGmtModified();
		this.length = a2.getLength();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getView() {
		return view;
	}

	public void setView(Integer view) {
		this.view = view;
	}

	public Integer getGrammar() {
		return grammar;
	}

	public void setGrammar(Integer grammar) {
		this.grammar = grammar;
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

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
