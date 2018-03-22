package com.thenorthw.blog.common.dao.tag;

import com.thenorthw.blog.common.model.tag.RArticleTag;

import java.util.List;

/**
 * @autuor theNorthW
 * @date 20/09/2017.
 * blog: thenorthw.com
 */
public interface RArticleTagDao {
	public int addR(RArticleTag rArticleTag);

	public int deleteRByArticleId(Long id);

	public int deleteRByTagId(Long id);

	public List<RArticleTag> getRsByTagId(Long id);

	public List<RArticleTag> getRsByArticleId(Long id);
}
