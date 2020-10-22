package com.ssafy.chuck.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ssafy.chuck.error.exception.DuplicateKeyException;
import com.ssafy.chuck.error.exception.IncorrectFormatException;
import com.ssafy.chuck.error.exception.InvalidValueException;
import com.ssafy.chuck.user.dao.UserDao;
import com.ssafy.chuck.user.dto.UserDto;

@Service
public class UserServiceImpl implements UserService {

	static final String duplicateKey = "duplicate";
	static final String invalid = "can't find";

	@Autowired
	UserDao dao;

	@Override
	public void create(UserDto user) {
		try {
			dao.create(user);
		} catch (DataAccessException e) {
			if(e.getMessage().contains(duplicateKey)) {
				throw new DuplicateKeyException(String.valueOf(user.getUserId()));
			} else {
				throw new IncorrectFormatException("IncorrectFormatError");
			}
		}
	}

	@Override
	public void updateTime(long userId) {
		try {
			dao.updateTime(userId);
		} catch (DataAccessException e) {
			if(e.getMessage().contains(invalid)) {
				throw new InvalidValueException(String.valueOf(userId));
			} else {
				throw new IncorrectFormatException("IncorrectFormatError");
			}
		}
	}
}
