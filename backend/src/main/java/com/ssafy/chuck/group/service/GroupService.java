package com.ssafy.chuck.group.service;

import com.ssafy.chuck.group.dto.GroupDto;

public interface GroupService {

	// 그룹 생성
	void create (GroupDto group);

	// 그룹 토큰 업데이트
	void updateToken(int id, String token);
}
