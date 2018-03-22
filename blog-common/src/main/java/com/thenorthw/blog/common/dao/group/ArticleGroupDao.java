package com.thenorthw.blog.common.dao.group;

import com.thenorthw.blog.common.model.group.ArticleGroup;

import java.util.List;

/**
 * @autuor theNorthW
 * @date 19/09/2017.
 * blog: thenorthw.com
 */
public interface ArticleGroupDao {
	/**
	 * 获取所有article group
	 * @return
	 */
	public List<ArticleGroup> getAllArticleGroup();

	/**
	 * 该方法可用于增加article group
	 * @param articleGroup
	 * @return
	 */
	public int addArticleGroup(ArticleGroup articleGroup);

	/**
	 * 该方法可用于更新某个article group
	 * @param articleGroup
	 * @return
	 */
	public int updateArticleGroup(ArticleGroup articleGroup);

	/**
	 * 删除某个article group
	 * @param id
	 * @return
	 */
	public int deleteArticleGroup(Long id);
}
