package com.ssafy.chuck.log.dto;

import io.swagger.annotations.ApiModelProperty;

public class LogDto {

	@ApiModelProperty(value = "로그 아이디", example = "1")
	private int id;

	@ApiModelProperty(value = "그룹 아이디", example = "1")
	private int groupId;

	@ApiModelProperty(value = "로그 내용", example = "OOO님이 OO그룹에 가입하셨습니다.")
	private String content;

	@ApiModelProperty(value = "해당 이벤트 유저 아이디", example = "12468731")
	private long userId;

	@ApiModelProperty(value = "해당 이벤트 다이어리 또는 댓글 아이디", example = "1")
	private int writeId;

	@ApiModelProperty(value = "이벤트 상태", example = "Chuck")
	private String state;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public int getWriteId() {
		return writeId;
	}

	public void setWriteId(int writeId) {
		this.writeId = writeId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "LogDto{" +
			"id=" + id +
			", groupId=" + groupId +
			", content='" + content + '\'' +
			", userId=" + userId +
			", writeId=" + writeId +
			", state='" + state + '\'' +
			'}';
	}
}
