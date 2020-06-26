package com.xzit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.xzit.service.LoginService;
import com.xzit.util.CommonUtil;

@RestController
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	/**
	 * 登陆控制器
	 * @param requestJson
	 * @return
	 */
	@RequestMapping(value= "/auth",method = RequestMethod.POST)
	public JSONObject authLogin(@RequestBody JSONObject requestJson) {
		CommonUtil.hasAllRequired(requestJson, "username.password");
		return loginService.login(requestJson);
	}
	
	/**
	 * 查询当前登录用户信息
	 * @return
	 */
	@RequestMapping(value= "/getInfo",method = RequestMethod.POST)
	public JSONObject getInfo() {
		return loginService.getInfo();
	}
	
	/**
	 * logout控制器
	 * @return
	 */
	@RequestMapping(value= "/logout",method = RequestMethod.POST)
	public JSONObject logout() {
		return loginService.logout();
	}

}
