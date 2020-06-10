package com.xzit.util;

import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.xzit.config.exception.CommonJsonException;

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
	
	/**
	 * 返回错误信息的JSONObject
	 * @param errorEnum
	 * @return
	 */
	public static JSONObject errorJson(ErrorEnum errorEnum) {
		
		JSONObject resultJson = new JSONObject();
		resultJson.put("code", errorEnum.getErrorCode());
		resultJson.put("msg", errorEnum.getErrorMsg());
		resultJson.put("info", new JSONObject());
		return resultJson;
	}
	
	/**
	 * 分页查询之前，为查询条件里面添加分页参数
	 * @param param 查询条件参数
	 * @param defaultPageRow 默认的每页显示条数
	 */
	private static void fillPageParam(final JSONObject param, int defaultPageRow) {
		
		int pageNum = param.getIntValue("pageNum");
		pageNum = pageNum==0 ? 1:pageNum;
		int pageRow = param.getIntValue("pageRow");
		pageRow = pageRow ==0 ? defaultPageRow : pageRow;
		param.put("offset", (pageNum-1)*pageRow);
		param.put("pageRow", pageRow);
		param.put("pageNum", pageNum);
		
	}
	
	public static void fillPageParam(final JSONObject param) {
		fillPageParam(param, 10);
	}
	/**
	 * 查询分页结果的封装
	 * @param request 请求json
	 * @param list 查询分页对象list
	 * @param totalCount 查询出的总记录数
	 * @return
	 */
	public static JSONObject successPage(final JSONObject request, List<JSONObject> list, int totalCount) {
		int pageRow = request.getIntValue("pageRow");
		int totalPage = getPageCount(pageRow, totalCount);
		JSONObject result = successJson();
		JSONObject info = new JSONObject();
		info.put("list", list);
		info.put("totalCount", totalCount);
		info.put("totalPage", totalPage);
		result.put("info", info);
		
		return result;		
	}
	
	public static JSONObject successPage(List<JSONObject> list) {
		JSONObject result = successJson();
		JSONObject info = new JSONObject();
		info.put("list", list);
		result.put("info", info);
		return result;
	}
	
	/**
	 * 获取记录总页数
	 * @param pageRow 每页行数
	 * @param itemCount 结果总记录数
	 * @return
	 */
	private static int getPageCount(int pageRow, int itemCount) {
		if(itemCount ==0)
			return 1;
		return itemCount % pageRow >0 ?itemCount/pageRow +1 : itemCount/pageRow;
	}
	
	/**
	 * 判断传入参数是否符合要求
	 * @param jsonObject
	 * @param requiredParams
	 */
	public static void hasAllRequired(final JSONObject jsonObject,String requiredParams) {
		if(!StringTools.isNullOrEmpty(requiredParams)) {
			String[] params = requiredParams.split(",");
			String missCol = "";//缺失的参数
			for(String param :params) {
				Object val = jsonObject.get(param.trim());
				if(StringTools.isNullOrEmpty(val)) {
					missCol += param +" ";
				}
			}
			if(!StringTools.isNullOrEmpty(missCol)) {//对应的缺失参数
				jsonObject.clear();
				jsonObject.put("code", ErrorEnum.E_90003.getErrorCode());
				jsonObject.put("msg", "缺少必填的参数"+missCol.trim());
				jsonObject.put("info", new JSONObject());
				throw new CommonJsonException(jsonObject);
			}
		}
	}
	
	/**
	 * 将request参数转为json
	 * @param request
	 * @return
	 */
	public static JSONObject requestToJson(HttpServletRequest request) {
		JSONObject requestJson = new JSONObject();
		Enumeration paramNames = request.getParameterNames();
		while(paramNames.hasMoreElements()) {
			String paramName = (String)paramNames.nextElement();
			String[] pv = request.getParameterValues(paramName);
			StringBuilder builder = new StringBuilder();
			for(int i=0;i<pv.length;i++) {
				if(pv[i].length() >0) {
					if(i>0) {
						builder.append(",");
					}
					builder.append(pv[i]);
				}
			}
			requestJson.put(paramName, builder).toString();
		}
		return requestJson;
	}
}
