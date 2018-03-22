package com.thenorthw.blog.common.dao.group;

import com.thenorthw.blog.common.model.group.RArticleArticleGroup;

import java.util.List;

/**
 * @autuor theNorthW
 * @date 20/09/2017.
 * blog: thenorthw.com
 */
public interface RArticleArticleGroupDao {

	public int addR(RArticleArticleGroup rArticleArticleGroup);

	public int deleteRByArticleId(Long id);

	public int deleteRByGroupId(Long id);

	public List<RArticleArticleGroup> getRsByGroupId(Long id);

	public List<RArticleArticleGroup> getRsByArticleId(Long id);
}
