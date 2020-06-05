package com.xzit.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.xzit.dao.PermissionDao;
import com.xzit.service.PermissionService;

public class PermissionServiceImp implements PermissionService{
	
	@Autowired
	private PermissionDao permissionDao;
	
	@Override
	public JSONObject getPermissions(String username) {
		
		return permissionDao.getPermissions(username);
	}

}
