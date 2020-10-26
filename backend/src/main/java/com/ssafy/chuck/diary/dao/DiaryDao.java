package com.ssafy.chuck.diary.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.chuck.diary.dto.DiaryDto;

@Mapper
public interface DiaryDao {

	// 1. 일기 생성
	int create(DiaryDto diary);

}
