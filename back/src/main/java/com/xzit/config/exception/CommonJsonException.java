package com.xzit.config.exception;

import com.alibaba.fastjson.JSONObject;
import com.xzit.util.CommonUtil;
import com.xzit.util.ErrorEnum;

public class CommonJsonException extends RuntimeException {
	
	private JSONObject resultJson;
	
	public CommonJsonException(ErrorEnum errorEnum) {
		this.resultJson = CommonUtil.errorJson(errorEnum);
	}
	
	public CommonJsonException(JSONObject resultJson) {
		this.resultJson = resultJson;
	}
	
	public JSONObject getResultJson() {
		return resultJson;
	}
}
