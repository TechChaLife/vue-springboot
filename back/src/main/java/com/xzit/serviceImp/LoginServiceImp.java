package com.xzit.serviceImp;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.xzit.dao.LoginDao;
import com.xzit.service.LoginService;
import com.xzit.service.PermissionService;
import com.xzit.util.CommonUtil;

@Service
public class LoginServiceImp implements LoginService {
	
	
	@Autowired
	private LoginDao loginDao;
	
	@Autowired
	private PermissionService permissionService;
	/**
	 * 前端表单提交并认证
	 */
	@Override
	public JSONObject login(JSONObject json) {
		//通过json键获取值
		String username= json.getString("username");
		String password = json.getString("password");
		JSONObject loginInfo = new JSONObject();
		Subject currentUser = SecurityUtils.getSubject();//获取当前认证主体对象(web程序中也就是当前用户)
		UsernamePasswordToken token = new UsernamePasswordToken(username,password);//创建用于封装登陆信息的令牌
		try {
			currentUser.login(token);
			loginInfo.put("result", "success");
			
		}catch(AuthenticationException e) {
			loginInfo.put("result", "fail");
		}
		
		return CommonUtil.successJson(loginInfo);
	}
	
	/**
	 * 从数据库查询指定用户
	 */
	@Override
	public JSONObject getUser(String username, String password) {
		
		return loginDao.getUser(username, password);
	}
	
	/**
	 * 查询当前用户权限信息(用户已经登陆前提下)
	 */
	@Override
	public JSONObject getInfo() {
		
		//从当前session获取用户信息
		Session session = SecurityUtils.getSubject().getSession();
		JSONObject userInfo = (JSONObject)session.getAttribute("user");
		String username = userInfo.getString("username");
		JSONObject info = new JSONObject();
		JSONObject userPermission = permissionService.getPermissions(username);
		session.setAttribute("permissions", userPermission);
		info.put("userPermission", userPermission);
		
		return CommonUtil.successJson(info);
	}

	@Override
	public JSONObject logout() {
		try {
			Subject currUser = SecurityUtils.getSubject();
			currUser.logout();
			return null;
			
		}catch(Exception e) {
			
		}
		return CommonUtil.successJson();
	}

}
