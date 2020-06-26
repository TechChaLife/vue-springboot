package com.xzit.serviceImp;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.xzit.dao.ArticleDao;
import com.xzit.service.ArticleService;
import com.xzit.util.CommonUtil;

@Service
public class ArticleServiceImp implements ArticleService {
	
	private ArticleDao articleDao;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject addArticle(JSONObject json) {
		
		articleDao.addArticle(json);
		return CommonUtil.successJson();
	}

	@Override
	public JSONObject listArticle(JSONObject json) {
		CommonUtil.fillPageParam(json);
		int count = articleDao.countArticle(json);
		List<JSONObject> list = articleDao.listArticle(json);
		
		return CommonUtil.successPage(json, list, count);
	}

	@Override
	@Transactional(rollbackFor= Exception.class)
	public JSONObject updateArticle(JSONObject json) {
		articleDao.updateArticle(json);
		return CommonUtil.successJson();
	}

}
