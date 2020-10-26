package com.ssafy.chuck.reply.service;

import java.util.List;

import com.ssafy.chuck.reply.dto.ReplyDto;

public interface ReplyService {
	public int insertComment(Long writer, String comment, int diary_id);
	public List<ReplyDto> selectCommentByDiaryId(int diary_id);
	public List<ReplyDto> selectCommentByWriter(Long writer);
}