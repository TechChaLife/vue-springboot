package com.xzit.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;

public interface UserDao {
	
	/**
	 * 查询用户数量
	 * @param jsonObject
	 * @return
	 */
	int countUser(JSONObject jsonObject);
	
	/**
	 * 查询用户列表
	 * @param jsonObject
	 * @return
	 */
	List<JSONObject> listUser(JSONObject jsonObject);
	
	/**
	 * 查询角色列表(添加用户，修改用户信息时使用)
	 * @param jsonObject
	 * @return
	 */
	List<JSONObject> getAllRoles();
	
	/**
	 * 用户名校验
	 * @param jsonObject
	 * @return
	 */
	int usernameValidate(JSONObject jsonObject);
	
	/**
	 * 新增用户
	 * @param jsonObject
	 * @return
	 */
	int addUser(JSONObject jsonObject);
	
	/**
	 * 修改用户信息
	 * @param jsonObject
	 * @return
	 */
	int updateUser(JSONObject jsonObject);
	
	/**
	 * 角色列表
	 * @param jsonObject
	 * @return
	 */
	List<JSONObject> listRole();
	
	/**
	 * 查询所有权限列表
	 * @return
	 */
	List<JSONObject> listAllPermissions();
	
	/**
	 * 插入角色
	 * @param jsonObject
	 * @return
	 */
	int insertRole(JSONObject jsonObject);
	
	/**
	 * 批量插入权限
	 * @param roleId
	 * @param permissions
	 * @return
	 */
	int insertRolePermission(@Param("roleId") String roleId,@Param("permissions") List<Integer> permissions);
	
	/**
	 * 修改曾拥有权限为不再使用
	 * @param roleId
	 * @param permissions
	 * @return
	 */
	int removePermission(@Param("roleId") String roleId,@Param("permissions") List<Integer> permissions);
	
	/**
	 * 修改角色名称
	 * @param jsonObject
	 * @return
	 */
	int updateRoleName(JSONObject jsonObject);
	
	/**
	 * 查询指定角色的全部信息
	 * @param jsonObject
	 * @return
	 */
	JSONObject getRoleAllInfo(JSONObject jsonObject);
	
	/**
	 * 移除角色
	 * @param jsonObject
	 * @return
	 */
	int removeRole(JSONObject jsonObject);
	
	/**
	 * 删除指定角色的全部权限
	 * @param jsonObject
	 * @return
	 */
	int removeRoleAllPermission(JSONObject jsonObject);
	
	
	
}
