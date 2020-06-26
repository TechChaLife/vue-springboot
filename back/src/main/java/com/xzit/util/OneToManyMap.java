package com.xzit.util;

import java.util.List;
import java.util.Set;

import com.alibaba.fastjson.JSONObject;

/**
 * 一对查询结果封装
 * @author Charles
 *
 */
public class OneToManyMap extends JSONObject{
	
	private Set<String> roleList;
	private Set<String> menuList;
	private Set<String> permissionList;
	private Set<Integer> permissionId;
	private List<JSONObject> picList;
	private List<JSONObject> menus;
	private List<JSONObject> users;
	private List<Integer> permissions;
	
}
