package com.xzit.dao;

import com.alibaba.fastjson.JSONObject;

public interface PermissionDao {
	
	JSONObject getPermissions(String username);

}
