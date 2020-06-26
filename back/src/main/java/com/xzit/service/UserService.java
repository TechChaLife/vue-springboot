package com.xzit.service;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

@Service
public interface UserService {
	
	/**
	 * 用户列表
	 * @param jsonObject
	 * @return
	 */
	JSONObject listUser(JSONObject jsonObject);
	
	/**
	 * 查询所有角色
	 * @return
	 */
	JSONObject getAllRoles();
	
	/**
	 * 添加用户
	 * @param jsonObject
	 * @return
	 */
	JSONObject addUser(JSONObject jsonObject);
	
	/**
	 * 修改用户信息
	 * @param jsonObject
	 * @return
	 */
	JSONObject updateUser(JSONObject jsonObject);
	
	/**
	 * 获取角色列表(添加用户，修改用户信息时使用)
	 * @return
	 */
	JSONObject listRole();
	
	/**
	 * 查询所有权限(添加/修改权限时使用)
	 * @return
	 */
	JSONObject listAllPermission();
	
	/**
	 * 添加角色
	 * @param jsonObject
	 * @return
	 */
	JSONObject addRole(JSONObject jsonObject);
	
	/**
	 * 修改角色信息
	 * @param jsonObject
	 * @return
	 */
	JSONObject updateRole(JSONObject jsonObject);
	
	/**
	 * 删除角色
	 * @param jsonObject
	 * @return
	 */
	JSONObject removeRole(JSONObject jsonObject);
	
	
}
