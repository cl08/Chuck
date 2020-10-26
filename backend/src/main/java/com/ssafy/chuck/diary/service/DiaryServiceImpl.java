package com.ssafy.chuck.diary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ssafy.chuck.common.annotation.GroupMemberCheck;
import com.ssafy.chuck.diary.dao.DiaryDao;
import com.ssafy.chuck.diary.dto.DiaryDto;

@Service
public class DiaryServiceImpl implements DiaryService{

	@Autowired
	DiaryDao dao;

	@GroupMemberCheck
	@Override
	public void create(long userId, int num, DiaryDto diary) {
		try {
			dao.create(diary);
		} catch (DataAccessException e) {
			throw e;
		}
	}

	@Override
	public DiaryDto read(int id) {
		return dao.read(id);
	}
}
