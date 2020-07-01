package com.xzit.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.xzit.service.ArticleService;
import com.xzit.util.CommonUtil;

/**
 * 文章相关控制器
 * @author Charles
 *
 */
@RestController
@RequestMapping("/article")
public class ArticleController {
	
	@Autowired
	private ArticleService articleService;
	
	/**
	 * 新增文章
	 * @param jsonObject
	 * @return
	 */
	@RequiresPermissions("article:add")
	@RequestMapping(value="/addArticle",method = RequestMethod.POST)
	public JSONObject addArticle(@RequestBody JSONObject jsonObject) {
		
		CommonUtil.hasAllRequired(jsonObject, "content");
		return articleService.addArticle(jsonObject);
	}
	
	/**
	 * 查询文章列表
	 * @param request
	 * @return
	 */
	@RequiresPermissions("article:list")
	@GetMapping("/listArticle")
	public JSONObject listArticle(@RequestBody HttpServletRequest request) {
		return articleService.listArticle(CommonUtil.requestToJson(request));
	}
	
	/**
	 * 修改文章
	 * @param requestJson
	 * @return
	 */
	@RequiresPermissions("article:update")
	@PostMapping("/updateArticle")
	public JSONObject updateArticle(@RequestBody JSONObject requestJson) {
		CommonUtil.hasAllRequired(requestJson, "id,content");
		return articleService.updateArticle(requestJson);
		
	}
	
	

}
