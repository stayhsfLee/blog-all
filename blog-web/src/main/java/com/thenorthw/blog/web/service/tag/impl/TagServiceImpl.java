package com.thenorthw.blog.web.service.tag.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.thenorthw.blog.common.dao.tag.RArticleTagDao;
import com.thenorthw.blog.common.dao.tag.TagDao;
import com.thenorthw.blog.common.model.tag.Tag;
import com.thenorthw.blog.web.service.tag.TagService;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * @autuor theNorthW
 * @date 20/09/2017.
 * blog: thenorthw.com
 */
@Service
public class TagServiceImpl implements TagService {
	@Autowired
	TagDao tagDao;
	@Autowired
	RArticleTagDao rArticleTagDao;

	public static List<Tag> tags = null;

	public List<Tag> getAllTags() {
		if(tags == null){
			synchronized (tags){
				if(tags == null){
					List<Tag> list = tagDao.getAllTags();
					if(list == null || list.size() == 0){
						tags = Collections.synchronizedList(Collections.<Tag>emptyList());
					}else{
						tags = Collections.synchronizedList(tags);
					}
				}
			}
		}

		return tags;
	}

	public int addTag(Tag tag) {
		if(tags == null){
			getAllTags();
		}

		int res = tagDao.addTag(tag);

		if(res == 1){
			tag.setGmtModified(null);
			tag.setGmtCreate(null);
			tag.setCreator(null);
			tags.add(tag);
		}else{
			return -1;
		}

		return 0;
	}

	/**
	 * 需要删除掉相关article和tag的map
	 * @param id
	 * @return
	 */
	public int deleteTag(Long id) {
		if(tags == null){
			getAllTags();
		}

		int res = tagDao.deleteTag(id);

		if(res == 1){
			Iterator<Tag> iterator = tags.iterator();

			while(iterator.hasNext()){
				Tag temp = iterator.next();
				if(temp.getId().equals(id)){
					iterator.remove();

					//删除相关map
					rArticleTagDao.deleteRByTagId(id);
					return 0;
				}
			}

			return -2;
		}else {
			return -1;
		}
	}
}
