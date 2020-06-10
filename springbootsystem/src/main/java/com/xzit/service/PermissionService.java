package com.xzit.service;

import com.alibaba.fastjson.JSONObject;

public interface PermissionService {
	
	JSONObject getPermissions(String username);

}
