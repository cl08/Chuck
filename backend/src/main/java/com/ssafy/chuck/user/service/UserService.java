package com.ssafy.chuck.user.service;

import com.ssafy.chuck.user.dto.UserDto;

public interface UserService {

	// 회원 정보 추가 (회원 가입)
	void create(UserDto user);

	// 최근 접속 업데이트
	void updateTime(long userId);

	// 회원 정보 조회
	UserDto read(long userId);

	// 회원 정보 수정
	void update(UserDto user);
}
