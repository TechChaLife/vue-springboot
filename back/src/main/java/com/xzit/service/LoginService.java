package com.xzit.service;

import com.alibaba.fastjson.JSONObject;

/**
 * 登陆业务
 * @author Charles
 *
 */
public interface LoginService {
	
	/**
	 * 前端提交登陆信息
	 * @param json
	 * @return
	 */
	JSONObject login(JSONObject json);
	/**
	 * 从用户表中查询对于的用户信息
	 * @param username
	 * @param password
	 * @return
	 */
	JSONObject getUser(String username, String password);
	
	/**
	 * 获取用户信息
	 * @return
	 */
	JSONObject getInfo();
	
	JSONObject logout();
}
