package com.thenorthw.blog.common.dao.tag;

import com.thenorthw.blog.common.model.tag.Tag;

import java.util.List;

/**
 * @autuor theNorthW
 * @date 20/09/2017.
 * blog: thenorthw.com
 */
public interface TagDao {
	public abstract List<Tag> getAllTags();

	public abstract int addTag(Tag tag);

	public abstract int deleteTag(Long id);
}
