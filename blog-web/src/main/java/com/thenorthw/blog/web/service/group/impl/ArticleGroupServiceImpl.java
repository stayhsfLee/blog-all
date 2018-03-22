package com.thenorthw.blog.web.service.group.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.thenorthw.blog.common.dao.group.ArticleGroupDao;
import com.thenorthw.blog.common.dao.group.RArticleArticleGroupDao;
import com.thenorthw.blog.common.model.group.ArticleGroup;
import com.thenorthw.blog.web.service.group.ArticleGroupService;

import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @autuor theNorthW
 * @date 19/09/2017.
 * blog: thenorthw.com
 */
@Service
public class ArticleGroupServiceImpl implements ArticleGroupService {
	private static Logger logger = LoggerFactory.getLogger(ArticleGroupServiceImpl.class);


	@Autowired
	ArticleGroupDao articleGroupDao;
	@Autowired
	RArticleArticleGroupDao articleArticleGroupDao;

	//group cache
	public static List<ArticleGroup> articleGroups = null;


	public List<ArticleGroup> getAllArticleGroups() {
		//查询是幂等的
		if(articleGroups == null) {
			synchronized (articleGroups) {
				if(articleGroups == null) {
					List<ArticleGroup> temp = articleGroupDao.getAllArticleGroup();
					if (temp == null || temp.size() == 0) {
						articleGroups = Collections.synchronizedList(
							Collections.synchronizedList(Collections.<ArticleGroup>emptyList()));
					} else {
						articleGroups = Collections.synchronizedList(temp);
					}
				}
			}
		}
		return articleGroups;
	}

	public ArticleGroup getArticleGroupById(Long id) {
		if(articleGroups == null){
			getAllArticleGroups();
		}

		Iterator<ArticleGroup> its = articleGroups.iterator();

		while(its.hasNext()){
			ArticleGroup a = its.next();

			if(a.getId().equals(id)){
				return a;
			}
		}

		return null;
	}

	/**
	 *
	 * @param articleGroup
	 * @return -1 db error -2 no parent group -3 out of level limit
	 */
	public int addArticleGroup(ArticleGroup articleGroup) {
		//首先判断level是否已经超过2
		//查询父group的level
		Long parentId = articleGroup.getParentGroupId();
		Date now = new Date();

		if(articleGroups == null){
			getAllArticleGroups();
		}

		//添加的是一级目录，直接添加
		if(parentId == 0){
			articleGroup.setLevel(1);
			articleGroup.setGmtCreate(now);
			articleGroup.setGmtModified(now);
			int result = articleGroupDao.addArticleGroup(articleGroup);

			if(result == 1){
				articleGroup.setGmtCreate(null);
				articleGroup.setGmtModified(null);
				articleGroups.add(articleGroup);
			}else {
				return -1;
			}
		}else{
			ArticleGroup parentGroup = getArticleGroupById(parentId);

			if(parentGroup == null){
				return -2;
			}

			//判断父group level是否为1，应为level不能超过二
			if(parentGroup.getLevel() != 1){
				return -3;
			}

			articleGroup.setLevel(parentGroup.getLevel() + 1);
			articleGroup.setGmtCreate(now);
			articleGroup.setGmtModified(now);
			int result = articleGroupDao.addArticleGroup(articleGroup);

			if(result == 1){
				articleGroup.setGmtCreate(null);
				articleGroup.setGmtModified(null);
				articleGroups.add(articleGroup);
			}else {
				return -1;
			}
		}

		return 0;
	}


	public int updateArticleGroup(ArticleGroup articleGroup) {
		if(articleGroups == null){
			getAllArticleGroups();
		}

		if(articleGroup.getLevel() >= 2){
			return -3;
		}

		if(getArticleGroupById(articleGroup.getParentGroupId()) == null){
			return -2;
		}

		//判断是否存在这个group
		ArticleGroup temp = getArticleGroupById(articleGroup.getId());

		if(temp == null){
			logger.warn("Someone want to update group which not exists.");
			return -4;
		}else {
			articleGroup.setGmtCreate(temp.getGmtCreate());
			articleGroup.setGmtModified(new Date());
			int result = articleGroupDao.updateArticleGroup(articleGroup);

			if(result == 1){
				articleGroup.setGmtCreate(null);
				articleGroup.setGmtModified(null);
				temp = articleGroup;
			}else {
				return -1;
			}
		}

		return 0;
	}

	/**
	 * 删除某个article group，并且在缓存中删除相应article group
	 * @param id
	 * @return null - 删除失败
	 */
	public int deleteArticleGroup(Long id) {
		if(articleGroups == null){
			getAllArticleGroups();
		}

		int result = articleGroupDao.deleteArticleGroup(id);

		if(result == 1){
			Iterator<ArticleGroup> its = articleGroups.iterator();

			while(its.hasNext()){
				ArticleGroup a = its.next();

				if(a.getId() == id){
					its.remove();

					//删除相关article和group的map
					int result1 = articleArticleGroupDao.deleteRByGroupId(id);
					break;
				}
			}
		}else {
			return -1;
		}

		return 0;
	}
}
