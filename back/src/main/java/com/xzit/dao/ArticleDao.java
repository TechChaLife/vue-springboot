package com.xzit.dao;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

public interface ArticleDao {
	
	/**
	 * 新增文章
	 * @param json
	 * @return
	 */
	int addArticle(JSONObject json);
	
	/**
	 * 统计文章总数
	 * @param json
	 * @return
	 */
	int countArticle(JSONObject json);
	
	/**
	 * 显示文章列表
	 * @param json
	 * @return
	 */
	List<JSONObject> listArticle(JSONObject json);
	
	/**
	 * 更新文章
	 * @param json
	 * @return
	 */
	int updateArticle(JSONObject json);
	
	
}
