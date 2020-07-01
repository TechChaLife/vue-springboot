package com.xzit.dao;

import java.util.Set;

import com.alibaba.fastjson.JSONObject;

public interface PermissionDao {
	
	/**
	 * 查询指定用户的角色 权限
	 * @param username
	 * @return
	 */
	JSONObject getPermissions(String username);
	
	/**
	 * 查询所有菜单
	 * @return
	 */
	Set<String> getAllMenu();
	/**
	 * 查询所有权限
	 * @return
	 */
	Set<String> getAllPermission();
}
