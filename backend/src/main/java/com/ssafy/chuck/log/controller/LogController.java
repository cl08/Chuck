package com.ssafy.chuck.log.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@CrossOrigin(origins = {"*"}, maxAge = 6000)
@Api(value = "활동로그 관리", tags = "Log")
@RestController
@RequestMapping("/chcuk/logs")
public class LogController {
	static final Logger logger = LoggerFactory.getLogger(LogController.class);
}
