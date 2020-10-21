package com.ssafy.chuck.diary.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@CrossOrigin(origins = {"*"}, maxAge = 6000)
@Api(value = "다이어리 관리", tags = "Diary")
@RestController
@RequestMapping("/chcuk/diaries")
public class DiaryController {
	static final Logger logger = LoggerFactory.getLogger(DiaryController.class);
}
