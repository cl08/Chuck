package com.ssafy.chuck.log.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.chuck.log.dto.LogDto;

@Mapper
public interface LogDao {

	// 1. 로그 생성
	int create(LogDto log);
}
