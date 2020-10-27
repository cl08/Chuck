package com.ssafy.chuck.reply.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ReplyDto {
	private int id;
	private Long writer; 
	private String comment;
	private int diary_id;
	private Date date;
}