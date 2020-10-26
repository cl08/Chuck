package com.ssafy.chuck.diary.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.chuck.diary.dto.DiaryDto;

@Mapper
public interface DiaryDao {

	// 1. 일기 생성
	int create(DiaryDto diary);

	// 2. 일기 상세 조회
	DiaryDto read(int id);

	// 3. 일기 수정
	int update(DiaryDto dto);

	// 4. 일기 삭제
	int delete(int id);
}
