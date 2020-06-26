package com.xzit.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.xzit.service.UserService;
import com.xzit.util.CommonUtil;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	/**
	 * 获取文章列表
	 * @param request
	 * @return
	 */
	@RequiresPermissions("user:list")
	@PostMapping("/listUser")
	public JSONObject listUser(HttpServletRequest request) {
		return userService.listUser(CommonUtil.requestToJson(request));
	}
	
	/**
	 * 添加用户
	 * @param jsonObject
	 * @return
	 */
	@RequiresPermissions("user:add")
	@PostMapping("/addUser")
	public JSONObject addUser(@RequestBody JSONObject jsonObject) { //直接使用json对象接收前端传递的json对象参数
		
		CommonUtil.hasAllRequired(jsonObject, "content");
		return userService.addRole(jsonObject);
	}
	
	/**
	 * 更新用户信息
	 * @param jsonObject
	 * @return
	 */
	@RequiresPermissions("user:update")
	@PostMapping("/updateUser")
	public JSONObject updateUser(@RequestBody JSONObject jsonObject) {
		CommonUtil.hasAllRequired(jsonObject, "password,roleId,nickname");
		return userService.updateUser(jsonObject);
	}
	
	/**
	 * 显示所有角色列表
	 * @return
	 */
	@RequiresPermissions("role:list")
	@RequestMapping("/listRole")
	public JSONObject listRole() {
		return userService.listRole();	
	}
	
	/**
	 * 添加角色
	 * @param jsonObject
	 * @return
	 */
	@RequiresPermissions("role:add")
	@PostMapping("/addRole")
	public JSONObject addRole(@RequestBody JSONObject jsonObject) {
		CommonUtil.hasAllRequired(jsonObject, "roleName,roleId,permissionId");
		return userService.addRole(jsonObject);
	}
	
	/**
	 * 更新角色信息
	 * @param jsonObject
	 * @return
	 */
	@RequiresPermissions("role:update")
	@PostMapping("/updateRole")
	public JSONObject updateRole(@RequestBody JSONObject jsonObject) {
		CommonUtil.hasAllRequired(jsonObject, "roleId,permissions,permissionId");
		return userService.updateRole(jsonObject);
	}
	
	/**
	 * 删除角色信息
	 * @param jsonObject
	 * @return
	 */
	@RequiresPermissions("role:delete")
	@PostMapping("/removeRole")
	public JSONObject removeRole(@RequestBody JSONObject jsonObject) {
		return userService.removeRole(jsonObject);
	}
	
	
	
	

}
