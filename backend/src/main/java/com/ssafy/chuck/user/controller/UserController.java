package com.ssafy.chuck.user.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;


@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
@RequestMapping("/chuck/users")
@Api(value = "User Controller", tags = "User")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
}
