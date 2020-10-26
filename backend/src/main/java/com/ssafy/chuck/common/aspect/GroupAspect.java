package com.ssafy.chuck.common.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
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

}
