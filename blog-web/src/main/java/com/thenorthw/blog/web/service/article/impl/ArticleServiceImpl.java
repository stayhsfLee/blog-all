package com.thenorthw.blog.web.service.article.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.thenorthw.blog.common.dao.article.ArticleContentDao;
import com.thenorthw.blog.common.dao.article.ArticleDao;
import com.thenorthw.blog.common.dao.group.RArticleArticleGroupDao;
import com.thenorthw.blog.common.dao.tag.RArticleTagDao;
import com.thenorthw.blog.common.model.article.Article;
import com.thenorthw.blog.common.model.article.ArticleContent;
import com.thenorthw.blog.face.dto.ArticleDto;
import com.thenorthw.blog.web.service.article.ArticleService;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @autuor theNorthW
 * @date 23/09/2017.
 * blog: thenorthw.com
 */
@Service
public class ArticleServiceImpl implements ArticleService {
	@Autowired
	ArticleDao articleDao;
	@Autowired
	ArticleContentDao articleContentDao;
	@Autowired
	RArticleTagDao rArticleTagDao;
	@Autowired
	RArticleArticleGroupDao rArticleArticleGroupDao;


	/**
	 * 单纯的获取article的summary信息
	 * 浏览量+1
	 * @param id
	 * @return
	 */
	public Article getArticleInfo(Long id) {
		articleDao.updateArticleView(1);
		return articleDao.getArticleById(id);
	}

	/**
	 * 获取单个article的内容信息
	 * @param id
	 * @return
	 */
	public ArticleContent getArticleContent(Long id) {
		return articleContentDao.getArticleContent(id);
	}

	public List<ArticleDto> getRecentArticles(){
		//默认获取最近10篇
		//获取最近的article信息和article content
		List<Article> recentArticles = articleDao.getRecentArticles(18);

		List<Long> ids = new LinkedList<Long>();

		for(Article a : recentArticles){
			ids.add(a.getId());
		}

		List<ArticleContent> contents = articleDao.getContentsByIds(ids);
		//turn to map
		Map<Long,ArticleContent> contentsMap = new HashMap<Long, ArticleContent>();
		for(ArticleContent a : contents){
			contentsMap.put(a.getId(),a);
		}

		//combine
		List<ArticleDto> dtos = new LinkedList<ArticleDto>();

		for(Article a : recentArticles){
			dtos.add(new ArticleDto(a,contentsMap.get(a.getId())));
		}

		return dtos;
	}

	public List<ArticleContent> getArticleContents(List<Long> ids){
		//最多获取10篇
		if(ids.size() > 10){
			return null;
		}
		return articleContentDao.getArticleContents(ids);
	}

	public ArticleDto getArticleDto(Long id) {
		Article article = articleDao.getArticleById(id);
		ArticleContent articleContent = articleContentDao.getArticleContent(id);
		ArticleDto articleDto = new ArticleDto(article,articleContent);
		return articleDto;
	}

	/**
	 * 将article基本信息和content一起上传，group稍后上传，tag也是稍后通过调用其他api进行上传
	 * @param article
	 * @param articleContent
	 * @return 返回article id
	 */
	public Long postArticle(Article article, ArticleContent articleContent) {
		int result = articleDao.postArticle(article);

		if(result != 1){
			return -1L;
		}

		int result1 = articleContentDao.postArticleContent(articleContent);

		if(result1 != 1){
			//删除上面刚添加的article
			articleDao.deleteArticle(article.getId());
		}

		return article.getId();
	}

	/**
	 * 需要删除info和content，还要删除该article和group和tag的map
	 * 那么问题来了，中途删除失败怎么办，那就不知道让她随风吧
	 * @param id
	 * @return
	 */
	public int deleteArticle(Long id, Long accountId) {
		articleDao.deleteArticle(id);
		articleContentDao.deleteArticleContent(id);
		rArticleArticleGroupDao.deleteRByArticleId(id);
		rArticleTagDao.deleteRByArticleId(id);
		return 0;
	}

	/**
	 * 该方法只用来更新文章内容
	 * @param article
	 * @return
	 */
	public int updateArticle(Article article, ArticleContent articleContent) {
		return articleDao.updateContent(article);
	}
}
