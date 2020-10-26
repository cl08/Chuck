package com.ssafy.chuck.group.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.chuck.common.PermissionCheck;
import com.ssafy.chuck.group.dto.GroupDto;
import com.ssafy.chuck.group.service.GroupService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin(origins = {"*"}, maxAge = 6000)
@Api(value = "그룹 관리", tags = "Group")
@RestController
@RequestMapping("/chcuk/groups")
public class GroupController {
	private static final Logger logger = LoggerFactory.getLogger(GroupController.class);

	@Autowired
	GroupService service;

	@Autowired
	PermissionCheck permissionCheck;

	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "그룹 생성", notes = "새로운 그룹을 생성한다.")
	@ApiResponses({
		@ApiResponse(code = 201, message = "그룹 생성 성공"),
		@ApiResponse(code = 400, message = "잘못된 요청입니다"),
		@ApiResponse(code = 401, message = "로그인 후 이용해 주세요"),
		@ApiResponse(code = 403, message = "권한이 없습니다"),
		@ApiResponse(code = 404, message = "그룹 생성 실패")
	})
	@ApiImplicitParams({
		@ApiImplicitParam(
			value = "생성할 그룹 정보",
			required = true,
			name = "group",
			dataTypeClass = GroupDto.class),
		@ApiImplicitParam(
			value = "사용자 정보 JWT 헤더",
			required = true,
			name = "User"
		)
	})
	private ResponseEntity<?> create(@RequestBody GroupDto group, @RequestHeader(value = "token") String token) {
		logger.debug("그룹 생성 호출");
		group.setUserId(permissionCheck.check(token).getId());
		service.create(group);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}


}
