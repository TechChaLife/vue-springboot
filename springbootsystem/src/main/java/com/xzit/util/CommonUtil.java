package com.xzit.util;

import com.alibaba.fastjson.JSONObject;

/**
 * json工具类
 * @author Charles
 *
 */
public class CommonUtil {
	
	/**
	 * 返回空json对象的请求成功的json
	 * @return
	 */
	public static JSONObject successJson() {
		return successJson(new JSONObject());
	}
	
	/**
	 * 返回特定json对象的请求成功的json
	 * @param info
	 * @return
	 */
	public static JSONObject successJson(Object info) {
		JSONObject resultJson = new JSONObject();
		resultJson.put("code", 100);
		resultJson.put("msg", "请求成功");
		resultJson.put("info", info);
		return resultJson;
	}
	

}
