package com.ssafy.chuck.user.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.chuck.user.dto.UserDto;

@Mapper
public interface UserDao {

	// 회원 정보 추가
	int create(UserDto user);
}
