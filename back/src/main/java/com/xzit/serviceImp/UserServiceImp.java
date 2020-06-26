package com.xzit.serviceImp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.xzit.dao.UserDao;
import com.xzit.service.UserService;
import com.xzit.util.CommonUtil;
import com.xzit.util.ErrorEnum;

public class UserServiceImp implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public JSONObject listUser(JSONObject jsonObject) {
		CommonUtil.fillPageParam(jsonObject);
		int count = userDao.countUser(jsonObject);
		List<JSONObject> list = userDao.listUser(jsonObject);
		
		return CommonUtil.successPage(jsonObject, list, count);
	}

	@Override
	public JSONObject getAllRoles() {
		List<JSONObject> roles = userDao.getAllRoles();
		return CommonUtil.successPage(roles);
	}

	@Override
	public JSONObject addUser(JSONObject jsonObject) {
		//插入之前查看数据库中是否有已存在的用户名
		int exist = userDao.usernameValidate(jsonObject);
		if(exist>0) {//数据库中已存在相同的用户名的用户信息
			return CommonUtil.errorJson(ErrorEnum.E_10009);
		}
		userDao.addUser(jsonObject);
		return CommonUtil.successJson();//成功则返回空信息的Json对象
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject updateUser(JSONObject jsonObject) {
		userDao.updateUser(jsonObject);
		return CommonUtil.successJson();
	}

	@Override
	public JSONObject listRole() {
		List<JSONObject> roles = userDao.listRole();
		return CommonUtil.successPage(roles);
	}

	@Override
	public JSONObject listAllPermission() {
		List<JSONObject> permissions = userDao.listAllPermissions();
		return CommonUtil.successPage(permissions);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject addRole(JSONObject jsonObject) {
		userDao.insertRole(jsonObject);
		userDao.insertRolePermission(jsonObject.getString("roleId"),(List<Integer>)jsonObject.get("permissions") );
		return CommonUtil.successJson();
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public JSONObject updateRole(JSONObject jsonObject) {
		String roleId = jsonObject.getString("roleId");
		List<Integer> newPerms = (List<Integer>)jsonObject.get("permissions");
		Set<Integer> oldPerms = (Set<Integer>)jsonObject.get("permissionId");
		JSONObject roleInfo = userDao.getRoleAllInfo(jsonObject);//获取所有角色信息
		//修改角色名称
		modifyRoleName(jsonObject,roleInfo);
		//添加新权限
		addNewPermisson(roleId,newPerms,oldPerms);
		//移除旧权限
		removeOldPermission(roleId,newPerms,oldPerms);
		
		return CommonUtil.successJson();
	}
	//修改角色名方法
	private void modifyRoleName(JSONObject jsonParam, JSONObject roleObject) {
		String roleName = jsonParam.getString("roleName");
		if(!roleName.equals(roleObject.get("roleName"))) {
			userDao.updateRoleName(jsonParam);
		}
	}
	//添加新权限方法
	private void addNewPermisson(String roleId,Collection<Integer> newPerms, Collection<Integer> oldPerms) {
		List<Integer> insert = new ArrayList<>();
		for(Integer newPerm :newPerms) {
			if(!oldPerms.contains(newPerm)) {
				insert.add(newPerm);
			}
		}
		if(insert.size()>0) {
			userDao.insertRolePermission(roleId, insert);
		}
	}
	//移除旧权限方法
	private void removeOldPermission(String roleId,Collection<Integer> newPerms, Collection<Integer> oldPerms) {
		List<Integer> remove = new ArrayList<>();
		for(Integer oldPerm :oldPerms) {
			if(!newPerms.contains(oldPerm)) {
				remove.add(oldPerm);
			}
		}
		if(remove.size()>0) {
			userDao.removePermission(roleId, remove);
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject removeRole(JSONObject jsonObject) {
		JSONObject roleInfo = userDao.getRoleAllInfo(jsonObject);//获取所有角色信息
		List<JSONObject> users = (List<JSONObject>) roleInfo.get("users");
		if(users != null && users.size()>0) { //有用户使用此角色
			return CommonUtil.errorJson(ErrorEnum.E_10008);
		}
		userDao.removeRole(jsonObject);
		userDao.removeRoleAllPermission(jsonObject);
		return CommonUtil.successJson();
	}

}
