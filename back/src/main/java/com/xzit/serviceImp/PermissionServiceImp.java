package com.xzit.serviceImp;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.xzit.dao.PermissionDao;
import com.xzit.service.PermissionService;

public class PermissionServiceImp implements PermissionService{
	
	@Autowired
	private PermissionDao permissionDao;
	
	@Override
	public JSONObject getPermissions(String username) {
		
		JSONObject userPermission = permissionDao.getPermissions(username);
		//如果是管理员
		int adminRoleId =1;
		if(adminRoleId == userPermission.getIntValue("roleId")) {
			//查询所有权限
			Set<String> permissionList = permissionDao.getAllPermission();
			//查询所有菜单
			Set<String> menuList = permissionDao.getAllMenu();
			userPermission.put("menuList", menuList);
			userPermission.put("permissionList", permissionList);			
		}
		return userPermission;
	}

}
