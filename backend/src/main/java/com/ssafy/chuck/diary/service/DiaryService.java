package com.ssafy.chuck.diary.service;

import com.ssafy.chuck.diary.dto.DiaryDto;

public interface DiaryService {

	// 다이어리 생성
	void create(long userId, int num, DiaryDto diary);

	// 다이어리 상세 조회
	DiaryDto read(int id);
}
