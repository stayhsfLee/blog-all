package com.thenorthw.blog.common.dao.article;

import org.springframework.stereotype.Repository;
import com.thenorthw.blog.common.model.article.Article;
import com.thenorthw.blog.common.model.article.ArticleContent;

import java.util.List;

/**
 * @autuor theNorthW
 * @date 20/09/2017.
 * blog: thenorthw.com
 */
@Repository
public interface ArticleDao {
	/**
	 * 按照最近时间顺序获取limit文章
	 * @param limit
	 * @return
	 */
	public List<Article> getRecentArticles(int limit);

	public List<ArticleContent> getContentsByIds(List<Long> ids);

	public Article getArticleById(Long id);

	public int updateArticleView(Integer integer);
	/**
	 * 上传article基础信息，和postContent需要一起使用
	 * @param article
	 * @return
	 */
	public int postArticle(Article article);



	public int updateContent(Article article);

	public int deleteArticle(Long id);

}
