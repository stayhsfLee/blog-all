package com.thenorthw.blog.web.service.group;

import com.thenorthw.blog.common.model.group.ArticleGroup;

import java.util.List;

/**
 * @autuor theNorthW
 * @date 19/09/2017.
 * blog: thenorthw.com
 */
public interface ArticleGroupService {

	/**
	 * 获取所有的article分类
	 * 在实现中会有 缓存 实例
	 * @return
	 */
	public List<ArticleGroup> getAllArticleGroups();


	/**
	 * 根据id获取相应article group
	 * @param id
	 * @return
	 */
	public ArticleGroup getArticleGroupById(Long id);

	/**
	 * 增加新的article group
	 * @param articleGroup
	 * @return
	 */
	public int addArticleGroup(ArticleGroup articleGroup);

	/**
	 * 更新article group
	 * @param articleGroup
	 * @return
	 */
	public int updateArticleGroup(ArticleGroup articleGroup);

	/**
	 * 首先删除article 和 该group的map映射
	 * 再删除这个group
	 * @param id
	 * @return
	 */
	public int deleteArticleGroup(Long id);
}
