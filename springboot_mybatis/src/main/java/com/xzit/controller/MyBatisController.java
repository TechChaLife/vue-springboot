package com.xzit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xzit.domain.User;
import com.xzit.mapper.UserMapper;

@Controller
public class MyBatisController {
	
	@Autowired
	private UserMapper userMapper;
	
	@RequestMapping(value="/queryUser")
	@ResponseBody
	public List<User> queryUserList(){
		
		List<User> list= userMapper.queryUserList();
		
		return list;
	}

}
