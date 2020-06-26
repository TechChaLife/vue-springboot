package com.xzit.dao;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;

/**
 * 实现用户登录的Dao接口
 * @author Charles
 *
 */
public interface LoginDao {
	
	/**
	 * 根据用户名和密码查询对应数据库返回JSON
	 * @param userName
	 * @param password
	 * @return
	 */
	JSONObject getUser(@Param(value="username") String userName, @Param(value="password") String password);

}
