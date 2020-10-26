package com.ssafy.chuck.group.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ssafy.chuck.common.annotation.GroupTokenGen;
import com.ssafy.chuck.group.dao.GroupDao;
import com.ssafy.chuck.group.dto.GroupDto;

@Service
public class GroupServiceImpl implements GroupService {

	@Autowired
	GroupDao dao;

	@GroupTokenGen
	@Override
	public void create(GroupDto group) {
		try {
			dao.create(group);
		} catch (DataAccessException e) {
			throw e;
		}
	}

	@Override
	public void updateToken(int id, String token) {
		try {
			dao.updateToken(id, token);
		} catch (DataAccessException e) {
			throw e;
		}
	}
}
