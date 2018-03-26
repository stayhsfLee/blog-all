package com.thenorthw.blog.common.dao.article;

import com.thenorthw.blog.common.model.article.ArticleContent;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @autuor theNorthW
 * @date 23/09/2017.
 * blog: thenorthw.com
 */
public interface ArticleContentDao {
	public ArticleContent getArticleContent(Long id);
	public List<ArticleContent> getArticleContents(List<Long> id);

	public int postArticleContent(ArticleContent articleContent);

	public int deleteArticleContent(Long id);
}
