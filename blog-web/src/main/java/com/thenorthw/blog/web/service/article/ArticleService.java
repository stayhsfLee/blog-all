package com.thenorthw.blog.web.service.article;

import com.thenorthw.blog.common.model.article.Article;
import com.thenorthw.blog.common.model.article.ArticleContent;
import com.thenorthw.blog.face.dto.ArticleDto;

import java.util.List;

/**
 * @autuor theNorthW
 * @date 17/09/2017.
 * blog: thenorthw.com
 */
public interface ArticleService {
	public Article getArticleInfo(Long id);

	public ArticleContent getArticleContent(Long id);

	public List<ArticleDto> getRecentArticles();

	public List<ArticleContent> getArticleContents(List<Long> ids);

	public ArticleDto getArticleDto(Long id);

	/**
	 * @param article
	 * @param articleContent
	 * @return 返回刚插入的article id
	 */
	public Long postArticle(Article article, ArticleContent articleContent);

	public int deleteArticle(Long id, Long accountId);

	public int updateArticle(Article article, ArticleContent articleContent);
}
