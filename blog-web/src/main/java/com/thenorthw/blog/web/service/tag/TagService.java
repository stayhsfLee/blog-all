package com.thenorthw.blog.web.service.tag;

import com.thenorthw.blog.common.model.tag.Tag;

import java.util.List;

/**
 * @autuor theNorthW
 * @date 20/09/2017.
 * blog: thenorthw.com
 */
public interface TagService {

	public List<Tag> getAllTags();


	public int addTag(Tag tag);


	public int deleteTag(Long id);
}
