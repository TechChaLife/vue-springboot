package com.xzit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.xzit.domain.User;

@Mapper
public interface UserMapper {
	
	public List<User> queryUserList();

}
