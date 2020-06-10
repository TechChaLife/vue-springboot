package com.xzit.config.shiro;

import java.io.PrintWriter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import com.alibaba.fastjson.JSONObject;
import com.xzit.util.ErrorEnum;

public class AjaxPermissionAuthorizationFilter extends FormAuthenticationFilter {

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("code", ErrorEnum.E_20011.getErrorCode());
		jsonObject.put("msg", ErrorEnum.E_20011.getErrorMsg());
		PrintWriter out = null;
		HttpServletResponse res = (HttpServletResponse)response;
		try {
			res.setCharacterEncoding("utf-8");
			res.setContentType("application/json");
			out = response.getWriter();
		}catch(Exception e) {
			
		}finally {
			if(null != out) {
				out.flush();out.close();
			}
		}
		return false;
	}
	
}
