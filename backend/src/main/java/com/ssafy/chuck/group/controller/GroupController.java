package com.ssafy.chuck.group.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;


@CrossOrigin(origins = {"*"}, maxAge = 6000)
@Api(value = "그룹 관리", tags = "Group")
@RestController
@RequestMapping("/chcuk/groups")
public class GroupController {
	private static final Logger logger = LoggerFactory.getLogger(GroupController.class);
}
