package com.ssafy.chuck.music.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@CrossOrigin(origins = {"*"}, maxAge = 6000)
@Api(value = "음악 관리", tags = "Music")
@RestController
@RequestMapping("/chcuk/music")
public class MusicController {
	static final Logger logger = LoggerFactory.getLogger(MusicController.class);
}
