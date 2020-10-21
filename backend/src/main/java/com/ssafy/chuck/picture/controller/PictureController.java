package com.ssafy.chuck.picture.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@CrossOrigin(origins = {"*"}, maxAge = 6000)
@Api(value = "사진 관리", tags = "Picture")
@RestController
@RequestMapping("/chcuk/pictures")
public class PictureController {
	static final Logger logger = LoggerFactory.getLogger(PictureController.class);
}
