package com.xzit;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.xzit.domain.User;
import com.xzit.mapper.UserMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=SpringbootMybatisApplication.class)
public class MybatisTest {
	
	@Autowired
	private UserMapper userMapper;
	
	@Test
	public void test() {
		List<User> users = userMapper.queryUserList();
		System.out.println(users);
	}
	
	

}
