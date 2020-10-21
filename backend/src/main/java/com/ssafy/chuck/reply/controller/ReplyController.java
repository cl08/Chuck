package com.ssafy.chuck.reply.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@CrossOrigin(origins = {"*"}, maxAge = 6000)
@Api(value = "댓글 관리", tags = "Reply")
@RestController
@RequestMapping("/chcuk/replies")
public class ReplyController {
	static final Logger logger = LoggerFactory.getLogger(ReplyController.class);
}
