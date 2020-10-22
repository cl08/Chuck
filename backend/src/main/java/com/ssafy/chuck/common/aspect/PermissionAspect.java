package com.ssafy.chuck.common.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.ssafy.chuck.common.PermissionCheck;
import com.ssafy.chuck.error.exception.AccessDeniedException;

@Aspect
@Component
public class PermissionAspect {
	private static final Logger logger = LoggerFactory.getLogger(PermissionCheck.class);

	@Autowired
	PermissionCheck permissionCheck;

	@Before("@annotation(com.ssafy.chuck.common.annotation.PermissionChecking)")
	private void UserPermissionCheck(JoinPoint point) {
		logger.debug("사용자 권한 체크");
		Object[] parameterValues = point.getArgs();
		long userId = (Long)parameterValues[0];
		String token = String.valueOf(parameterValues[1]);

		if(userId != permissionCheck.check(token).getUserId()) {
			throw new AccessDeniedException("사용자 정보 불일치");
		}
	}
}
