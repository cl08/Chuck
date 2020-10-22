package com.ssafy.chuck.user.service;

import com.ssafy.chuck.user.dto.UserDto;

public interface UserService {

	// 회원 정보 추가 (회원 가입)
	void create(UserDto user);

}
