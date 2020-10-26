package com.ssafy.chuck.diary.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.chuck.diary.service.DiaryService;
import com.ssafy.chuck.diary.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin(origins = {"*"}, maxAge = 6000)
@Api(value = "다이어리 관리", tags = "Diary")
@RestController
@RequestMapping("/chcuk/diaries")
public class DiaryController {
	static final Logger logger = LoggerFactory.getLogger(DiaryController.class);

	@Autowired
	DiaryService service;

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

}
