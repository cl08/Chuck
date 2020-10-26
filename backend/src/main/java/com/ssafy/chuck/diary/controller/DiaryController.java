package com.ssafy.chuck.diary.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.chuck.common.PermissionCheck;
import com.ssafy.chuck.diary.dao.DiaryDao;
import com.ssafy.chuck.diary.dto.DiaryDto;
import com.ssafy.chuck.diary.service.DiaryService;
import com.ssafy.chuck.diary.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@CrossOrigin(origins = {"*"}, maxAge = 6000)
@Api(value = "다이어리 관리", tags = "Diary")
@RestController
@RequestMapping("/chcuk/diaries")
public class DiaryController {
	static final Logger logger = LoggerFactory.getLogger(DiaryController.class);

	@Autowired
	DiaryService service;

	@Autowired
	PermissionCheck permissionCheck;

	@Autowired
	FileService fileService;

	@RequestMapping(method = RequestMethod.POST, value = "/images", produces = "application/json")
	@ApiOperation(value = "이미지 업로드")
	@ApiResponses({
		@ApiResponse(code = 200, message = "이미지 업로드 성공"),
		@ApiResponse(code = 400, message = "잘못된 요청입니다"),
		@ApiResponse(code = 401, message = "로그인 후 이용해 주세요"),
		@ApiResponse(code = 403, message = "권한이 없습니다"),
		@ApiResponse(code = 404, message = "이미지 업로드 실패")
	})
	private ResponseEntity<?> create(@RequestParam(value = "file") MultipartFile image) {
		logger.debug("이미지 업로드 호출");
		return new ResponseEntity<>(fileService.store(image), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	@ApiOperation(value = "다이어리 생성", notes = "그룹의 추억 쌓기", response = DiaryDto.class)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "회원 토큰"),
		@ApiImplicitParam(name = "review", value = "일기 내용", dataTypeClass = DiaryDto.class)
	})
	@ApiResponses({
		@ApiResponse(code = 201, message = "다이어리 생성 성공"),
		@ApiResponse(code = 400, message = "잘못된 요청입니다"),
		@ApiResponse(code = 401, message = "로그인 후 이용해 주세요"),
		@ApiResponse(code = 403, message = "권한이 없습니다"),
		@ApiResponse(code = 404, message = "다이어리 생성 실패")
	})
	private ResponseEntity<?> create(@RequestBody DiaryDto diary, @RequestHeader(value = "token") String token) {
		logger.debug("다이어리 생성 호출");
		// long userId = permissionCheck.check(token).getId();
		service.create(0, 1, diary);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}", produces = "application/json")
	@ApiOperation(value = "다이어리 상세 조회", notes = "그룹의 추억 읽기", response = DiaryDto.class)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "회원 토큰"),
	})
	@ApiResponses({
		@ApiResponse(code = 201, message = "다이어리 상세 조회 성공"),
		@ApiResponse(code = 400, message = "잘못된 요청입니다"),
		@ApiResponse(code = 401, message = "로그인 후 이용해 주세요"),
		@ApiResponse(code = 403, message = "권한이 없습니다"),
		@ApiResponse(code = 404, message = "다이어리 상세 조회 실패")
	})
	private ResponseEntity<?> read(@PathVariable int id, @RequestHeader(value = "token") String token) {
		logger.debug("다이어리 상세 조회 호출");
		// long userId = permissionCheck.check(token).getId();
		DiaryDto diary = service.read(id);
		return new ResponseEntity<>(diary, HttpStatus.OK);
	}

}
