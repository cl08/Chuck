package com.ssafy.chuck.common.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ssafy.chuck.error.exception.AccessDeniedException;
import com.ssafy.chuck.group.dto.GroupDto;
import com.ssafy.chuck.group.service.GroupService;

@Component
@Aspect
public class GroupAspect {

	private static final Logger logger = LoggerFactory.getLogger(GroupAspect.class);

	@Autowired
	GroupService service;

	@After("@annotation(com.ssafy.chuck.common.annotation.GroupTokenGen)")
	private void genToken(JoinPoint point) {
		logger.debug("카카오톡 초대를 위한 그룹 토큰 생성");
		Object[] parameterValues = point.getArgs();
		GroupDto dto = (GroupDto)parameterValues[0];
		String token = JWT.create().withIssuer("Chucks")
			.withSubject("Invite group")
			.withClaim("id", dto.getId())
			.sign(Algorithm.HMAC256("chuck_project"));
		service.updateToken(dto.getId(), token);
	}

	@Before("@annotation(com.ssafy.chuck.common.annotation.GroupOwnerCheck)")
	private void checkOwner(JoinPoint point) {
		logger.debug("그룹장 체크");
		Object[] parameterValues = point.getArgs();
		GroupDto dto = (GroupDto)parameterValues[0];
		long userId = (long)parameterValues[1];
		long originUserId = service.read(dto.getId()).getUserId();
		if(userId != originUserId) throw new AccessDeniedException("그룹장 확인 필요");
	}

	@Before("@annotation(com.ssafy.chuck.common.annotation.GroupMemberCheck)")
	private void checkUser(JoinPoint point) {
		logger.debug("그룹 접근 권한 체크");
		Object[] parameterValues = point.getArgs();
		if((int)parameterValues[1] == 1) {
			// 그룹 조회
		} else if((int)parameterValues[1] == 2){
			// 다이어리 관련
		} else {
			// 댓글 관련
		}
	}
}
