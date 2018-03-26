package com.thenorthw.blog.web.controller.article;

import com.thenorthw.blog.common.ResponseCode;
import com.thenorthw.blog.common.ResponseModel;
import com.thenorthw.blog.common.model.article.Article;
import com.thenorthw.blog.common.model.article.ArticleContent;
import com.thenorthw.blog.face.dto.ArticleDto;
import com.thenorthw.blog.face.form.article.ArticlePostForm;
import com.thenorthw.blog.face.form.article.ArticleUpdateForm;
import com.thenorthw.blog.web.service.article.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @autuor theNorthW
 * @date 17/09/2017.
 * blog: thenorthw.com
 */
@RequestMapping(value = "/web/v1")
@Controller
public class ArticleController {
	@Autowired
	HttpServletRequest httpServletRequest;
	@Autowired
	ArticleService articleService;

	@RequestMapping(value = "/article/recent", method = RequestMethod.GET)
	@ResponseBody
	public ResponseModel getRecentArticles(){
		ResponseModel responseModel = new ResponseModel();

		List<ArticleDto> dtos = articleService.getRecentArticles();

		responseModel.setData(dtos);

		return responseModel;
	}

	public ResponseModel getArticleById(Long id){
		ResponseModel responseModel = new ResponseModel();

		ArticleDto dto = articleService.getArticleDto(id);

		responseModel.setData(dto);

		return responseModel;
	}

	@RequestMapping(value = "/article",method = RequestMethod.POST)
	@ResponseBody
	public ResponseModel postNewArticle(ArticlePostForm articlePostForm){
		ResponseModel responseModel = new ResponseModel();

		Article article = new Article();
		article.setName(articlePostForm.getName());
		article.setView(0);
		Date now = new Date();
		article.setGmtCreate(now);
		article.setGmtModified(now);
		article.setGrammar(Integer.parseInt(articlePostForm.getGrammar()));
		article.setUserId((Long)httpServletRequest.getSession().getAttribute("user_id"));

		ArticleContent content = new ArticleContent();
		content.setContent(articlePostForm.getContent());
		content.setLength(articlePostForm.getContent().length());
		content.setGmtCreate(now);
		content.setGmtModified(now);

		Long result = articleService.postArticle(article,content);

		if(result.equals(-1L)){
			responseModel.setResponseCode(ResponseCode.INTERNAL_SERVER_ERROR.getCode());
			responseModel.setMessage(ResponseCode.INTERNAL_SERVER_ERROR.getMessage());
		}

		return responseModel;
	}

	@RequestMapping(value = "/article/update",method = RequestMethod.POST)
	@ResponseBody
	public ResponseModel updateArticle(ArticleUpdateForm articleUpdateForm){
		ResponseModel responseModel = new ResponseModel();

		Article article = new Article();
		article.setName(articleUpdateForm.getName());
		Date now = new Date();
		article.setGmtModified(now);
		article.setGrammar(Integer.parseInt(articleUpdateForm.getGrammar()));

		ArticleContent content = new ArticleContent();
		content.setContent(articleUpdateForm.getContent());
		content.setLength(articleUpdateForm.getContent().length());
		content.setGmtModified(now);

		Integer result = articleService.updateArticle(article,content);

		if(result.equals(-1)){
			responseModel.setResponseCode(ResponseCode.INTERNAL_SERVER_ERROR.getCode());
			responseModel.setMessage(ResponseCode.INTERNAL_SERVER_ERROR.getMessage());
		}

		return responseModel;
	}

	/**
	 * 需要给service传入accountId，
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/article", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseModel deleteArticle(Long id){
		ResponseModel responseModel = new ResponseModel();
		Long accountId = (Long)httpServletRequest.getSession().getAttribute("user_id");

		articleService.deleteArticle(id,accountId);

		return responseModel;
	}
}
