package com.ssafy.chuck.group.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ssafy.chuck.common.annotation.GroupMemberCheck;
import com.ssafy.chuck.common.annotation.GroupOwnerCheck;
import com.ssafy.chuck.common.annotation.GroupTokenGen;
import com.ssafy.chuck.group.dao.GroupDao;
import com.ssafy.chuck.group.dto.GroupDto;
import com.ssafy.chuck.group.dto.MemberDto;

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

	@GroupOwnerCheck
	@Override
	public void update(GroupDto group, long userId) {
		try {
			dao.update(group);
		} catch (DataAccessException e) {
			throw e;
		}
	}

	// @GroupMemberCheck
	@Override
	public GroupDto read(int id) {
		return dao.read(id);
	}

	@GroupOwnerCheck
	@Override
	public void delete(GroupDto group, long userId) {
		try {
			dao.delete(group.getId());
		} catch (DataAccessException e) {
			throw e;
		}
	}

	@Override
	public void createMember(MemberDto member) {
		try {
			dao.createMember(member);
		} catch (DataAccessException e) {
			throw e;
		}
	}

	@Override
	public List<MemberDto> readAllMember(int id) {
		return dao.readAllMember(id);
	}
}
