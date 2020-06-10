package com.xzit.config.shiro;

import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.xzit.service.LoginService;

/**
 * 自定义Realm并继承AuthorizingRealm,用来实现登陆授权等业务
 * @author Charles
 *
 */
public class MyRealm extends AuthorizingRealm {
	
	private Logger log = LoggerFactory.getLogger(MyRealm.class);
	
	
	@Autowired
	private LoginService loginService;
	/**
	 * 为当前认证成功用户授权
	 */
	@Override
	@SuppressWarnings("unchecked")
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		//获取用户名
		String username = (String)principals.getPrimaryPrincipal();
		Session session = SecurityUtils.getSubject().getSession();
		//查询用户权限
		JSONObject permission = (JSONObject)session.getAttribute("permissions");
		log.info("当前用户权限值为: "+permission.get("permissionList"));
		
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		//给用户赋予相应的角色和权限
		authorizationInfo.setStringPermissions((Set<String>) permission.get("permissionList"));
		return authorizationInfo;
	}
	
	/**
	 * 验证当前登录的用户，获取认证信息
	 * 当前subject提交Token.login()时会调用此方法进行验证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//获取用户名
		String username = (String)token.getPrincipal();
		//获取密码
		String password = new String((char[])token.getCredentials());
		JSONObject user = loginService.getUser(username, password);
		if(user != null) {
			//将当前用户存到session,这里直接通过shiro获取session
			user.remove("password");
			SecurityUtils.getSubject().getSession().setAttribute("user", user);
			//传入用户名和密码进行身份验证，并返回验证信息
			AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(username, password, "MyRealm");
			return authcInfo;
		}else { //为找到账号异常
			throw new UnknownAccountException();
		}
		
	}

}
