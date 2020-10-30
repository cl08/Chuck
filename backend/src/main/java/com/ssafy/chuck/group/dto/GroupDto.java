package com.ssafy.chuck.group.dto;

import io.swagger.annotations.ApiModelProperty;

public class GroupDto {

	@ApiModelProperty(value = "그룹 아이디", example = "1")
	private int id;

	@ApiModelProperty(value = "그룹장 아이디", example = "123456789")
	private long userId;

	@ApiModelProperty(value = "그룹 이름", example = "쓰는척")
	private String name;

	@ApiModelProperty(value = "그룹 소개", example = "안녕하세요 척의 개발그룹 쓰는척입니다.")
	private String intro;

	@ApiModelProperty(value = "그룹 토큰", example = "비밀")
	private String token;

	public GroupDto() {}

	public GroupDto(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "GroupDto{" +
			"id=" + id +
			", userId=" + userId +
			", name='" + name + '\'' +
			", intro='" + intro + '\'' +
			", token='" + token + '\'' +
			'}';
	}
}
