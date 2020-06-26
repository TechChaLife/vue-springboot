package com.xzit.service;

import com.alibaba.fastjson.JSONObject;

public interface ArticleService {
	
	/**
	 * 新增文章
	 * @param json
	 * @return
	 */
	JSONObject addArticle(JSONObject json);
	/**
	 * 显示文章列表
	 * @param json
	 * @return
	 */
	JSONObject listArticle(JSONObject json);
	
	/**
	 * 更新文章
	 * @param json
	 * @return
	 */
	JSONObject updateArticle(JSONObject json);
}
