package com.thenorthw.blog.web.controller.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.thenorthw.blog.common.ResponseCode;
import com.thenorthw.blog.common.ResponseModel;
import com.thenorthw.blog.common.model.group.ArticleGroup;
import com.thenorthw.blog.face.form.group.ArticleGroupPostForm;
import com.thenorthw.blog.face.form.group.ArticleGroupUpdateForm;
import com.thenorthw.blog.web.service.group.ArticleGroupService;

import java.util.List;

/**
 * @autuor theNorthW
 * @date 19/09/2017.
 * blog: thenorthw.com
 */
@Controller
@RequestMapping(value = "/web/v1")
public class ArticleGroupController {

	@Autowired
	ArticleGroupService articleGroupService;

	/**
	 * 这个方法是直接获取所有的article group（二维数组形式返回给前端），所以不需要参数
	 * service此处还会做一个缓存操作，因为article group不会有太大变动，这样就可以减轻数据库压力
	 * @return
	 */
	@RequestMapping(value = "/group/article", method = RequestMethod.GET)
	@ResponseBody
	public ResponseModel getAllArticleGroups(){
		ResponseModel responseModel = new ResponseModel();

		List<ArticleGroup> articleGroupList = articleGroupService.getAllArticleGroups();

		//是否以树形结构输出，感觉不需要呀 - -

		if(articleGroupList == null){
			responseModel.setResponseCode(ResponseCode.INTERNAL_SERVER_ERROR.getCode());
			responseModel.setMessage("Add failed, unknown error.");
		}else{
			responseModel.setData(articleGroupList);
		}

		return responseModel;
	}

	@RequestMapping(value = "/group/article", method = RequestMethod.POST)
	@ResponseBody
	public ResponseModel addArticleGroups(ArticleGroupPostForm articleGroupPostForm){
		ResponseModel responseModel = new ResponseModel();

		ArticleGroup articleGroup = new ArticleGroup();
		articleGroup.setName(articleGroupPostForm.getName());
		articleGroup.setParentGroupId(articleGroupPostForm.getParentGroupId());

		int res = articleGroupService.addArticleGroup(articleGroup);

		if(res == -1){
			responseModel.setResponseCode(ResponseCode.INTERNAL_SERVER_ERROR.getCode());
			responseModel.setMessage(ResponseCode.INTERNAL_SERVER_ERROR.getMessage());
		}else if(res == -2){
			responseModel.setResponseCode(ResponseCode.NO_SUCH_PARENT_ARTICLE_GROUP.getCode());
			responseModel.setMessage(ResponseCode.NO_SUCH_PARENT_ARTICLE_GROUP.getMessage());
		}else if (res == -3){
			responseModel.setResponseCode(ResponseCode.PARENT_LEVEL_ILLEGAL.getCode());
			responseModel.setMessage(ResponseCode.PARENT_LEVEL_ILLEGAL.getMessage());
		}

		responseModel.setData(articleGroupService.getAllArticleGroups());

		return responseModel;
	}

	@RequestMapping(value = "/group/article/info", method = RequestMethod.POST)
	@ResponseBody
	public ResponseModel updateArticleGroup(ArticleGroupUpdateForm articleGroupUpdateForm){
		ResponseModel responseModel = new ResponseModel();

		ArticleGroup articleGroup = new ArticleGroup();
		articleGroup.setId(articleGroupUpdateForm.getId());
		articleGroup.setName(articleGroupUpdateForm.getName());
		articleGroup.setLevel(articleGroupUpdateForm.getLevel());
		articleGroup.setParentGroupId(articleGroupUpdateForm.getParentGroupId());

		int res = articleGroupService.updateArticleGroup(articleGroup);

		if(res == -1){
			responseModel.setResponseCode(ResponseCode.INTERNAL_SERVER_ERROR.getCode());
			responseModel.setMessage(ResponseCode.INTERNAL_SERVER_ERROR.getMessage());
		}else if(res == -4){
			responseModel.setResponseCode(ResponseCode.NO_SUCH_ARTICLE_GROUP.getCode());
			responseModel.setMessage(ResponseCode.NO_SUCH_ARTICLE_GROUP.getMessage());
		}else if(res == -2){
			responseModel.setResponseCode(ResponseCode.NO_SUCH_PARENT_ARTICLE_GROUP.getCode());
			responseModel.setMessage(ResponseCode.NO_SUCH_PARENT_ARTICLE_GROUP.getMessage());
		}else if(res == -3){
			responseModel.setResponseCode(ResponseCode.PARENT_LEVEL_ILLEGAL.getCode());
			responseModel.setMessage(ResponseCode.PARENT_LEVEL_ILLEGAL.getMessage());
		}


		return responseModel;
	}


	@RequestMapping(value = "/group/article", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseModel deleteArticleGroups(@RequestParam(value = "id")String id){
		ResponseModel responseModel = new ResponseModel();

		Long idL = Long.parseLong(id);
		int res = articleGroupService.deleteArticleGroup(idL);

		if(res == -1){
			responseModel.setResponseCode(ResponseCode.GROUP_DELETE_ERROR.getCode());
			responseModel.setMessage(ResponseCode.GROUP_DELETE_ERROR.getMessage());
			return responseModel;
		}

		//需要删除和这个分类相关的文章


		return responseModel;
	}
}