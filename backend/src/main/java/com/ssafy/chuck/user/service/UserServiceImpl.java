package com.ssafy.chuck.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ssafy.chuck.common.annotation.LoginCheck;
import com.ssafy.chuck.common.annotation.PermissionChecking;
import com.ssafy.chuck.common.annotation.SignOut;
import com.ssafy.chuck.error.exception.DuplicateKeyException;
import com.ssafy.chuck.error.exception.EntityNotFoundException;
import com.ssafy.chuck.error.exception.IncorrectFormatException;
import com.ssafy.chuck.user.dao.UserDao;
import com.ssafy.chuck.user.dto.UserDto;

@Service
public class UserServiceImpl implements UserService {

	static final String duplicateKey = "duplicate";
	static final String invalid = "can't find";

	@Autowired
	UserDao dao;


	@LoginCheck
	@Override
	public void create(String accessToken, long userId, String name, boolean signUp) {
		if(signUp) {
			try {
				dao.create(new UserDto(userId, name));
			} catch (DataAccessException e) {
				if (e.getMessage().contains(duplicateKey)) {
					throw new DuplicateKeyException(String.valueOf(userId));
				} else {
					throw new IncorrectFormatException("IncorrectFormatError");
				}
			}
		} else {
			dao.updateTime(userId);
		}
	}

	@Override
	public void updateTime(long userId) {
		try {
			dao.updateTime(userId);
		} catch (DataAccessException e) {
			if(e.getMessage().contains(invalid)) {
				throw new EntityNotFoundException(String.valueOf(userId));
			} else {
				throw new IncorrectFormatException("IncorrectFormatError");
			}
		}
	}

	@Override
	public UserDto read(long userId) {
		UserDto user = new UserDto();
		try {
			user = dao.read(userId);
		} catch (DataAccessException e) {
			if(e.getMessage().contains(invalid)) {
				throw new EntityNotFoundException(String.valueOf(userId));
			} else {
				throw e;
			}
		}
		return user;
	}

	@Override
	public void update(UserDto user) {
		try {
			dao.update(user);
		} catch (DataAccessException e) {
			if(e.getMessage().contains(invalid)) {
				throw new EntityNotFoundException(String.valueOf(user.getId()));
			} else {
				throw e;
			}
		}
	}

	@SignOut
	@PermissionChecking
	@Override
	public void delete(String token, long userId, String refreshToken) {
		try {
			dao.delete(userId);
		} catch (DataAccessException e) {
			if(e.getMessage().contains(invalid)) {
				throw new EntityNotFoundException(String.valueOf(userId));
			} else {
				throw e;
			}
		}
	}
}
