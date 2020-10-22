package com.ssafy.chuck.user.controller;


import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.ssafy.chuck.common.ChuckJWT;
import com.ssafy.chuck.common.annotation.LoginCheck;
import com.ssafy.chuck.common.annotation.PermissionChecking;
import com.ssafy.chuck.user.dto.Response;
import com.ssafy.chuck.user.dto.UserDto;
import com.ssafy.chuck.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
@RequestMapping("/chuck/users")
@Api(value = "User Controller", tags = "User")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserService service;

	@LoginCheck
	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	@ApiOperation(value = "로그인 또는 회원가입", notes = "카카오 API 통해 받은 토큰을 기반으로 신규회원이라면 DB에 저장하는 메서드",
		response = UserDto.class)
	@ApiImplicitParam(name = "accessToken", value = "Kakao API를 통해서 받는 임의의 토큰 값", required = true)
	@ApiResponses({
		@ApiResponse(code = 200, message = "로그인 또는 회원가입 성공"),
		@ApiResponse(code = 400, message = "잘못된 요청입니다"),
		@ApiResponse(code = 401, message = "로그인 후 이용해 주세요"),
		@ApiResponse(code = 404, message = "로그인 또는 회원가입 실패")
	})
	private ResponseEntity<?> create(@RequestBody String accessToken, long userId, String name, boolean signUp) {
		logger.debug("로그인 또는 회원가입 호출");

		// DB 조회 후 없으면 추가 후 개인 그룹 생성 있으면 최근 접속 일자 업데이트
		if (signUp) {
			service.create(new UserDto(userId, name));
		} else {
			service.updateTime(userId);
		}

		String token = ChuckJWT.getToken(userId, name);
		return new ResponseEntity<>(token, HttpStatus.OK);
	}

	// @PermissionChecking
	@RequestMapping(method = RequestMethod.GET, value = "/{userId}", produces = "application/json")
	@ApiOperation(value = "회원 정보 조회", notes = "회원 아이디를 기반으로 회원 정보 조회", response = UserDto.class)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "userId", value = "Kakao API에서 제공한 회원 아이디", required = true,
			example = "1412733569"),
		@ApiImplicitParam(name = "token", value = "회원 토큰", required = true)
	})
	@ApiResponses({
		@ApiResponse(code = 200, message = "회원정보 조회 성공"),
		@ApiResponse(code = 400, message = "잘못된 요청입니다"),
		@ApiResponse(code = 401, message = "로그인 후 이용해 주세요"),
		@ApiResponse(code = 403, message = "권한이 없습니다"),
		@ApiResponse(code = 404, message = "회원정보 조회 실패")
	})
	private ResponseEntity<?> read(@PathVariable("userId") Long userId, @RequestHeader(value = "token") String token) {
		logger.debug("회원정보 조회 호출");

		UserDto user = service.read(userId);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
}
