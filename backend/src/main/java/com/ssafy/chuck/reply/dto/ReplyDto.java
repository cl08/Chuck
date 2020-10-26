package com.ssafy.chuck.reply.dto;

import lombok.Data;

@Data
public class ReplyDto {
	private Long writer; 
	private String comment;
	private int diary_id;
	private String date;
}