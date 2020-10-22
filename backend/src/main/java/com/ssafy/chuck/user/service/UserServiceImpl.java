package com.ssafy.chuck.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.chuck.user.dao.UserDao;
import com.ssafy.chuck.user.dto.UserDto;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao dao;

	@Override
	public void create(UserDto user) {
		dao.create(user);
	}
}
