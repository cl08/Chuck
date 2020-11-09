package com.ssafy.chuck.reply.service;

import java.util.List;

import com.ssafy.chuck.reply.dto.ReplyDto;

public interface ReplyService {
	int insertComment(Long writer, int num, String comment, int diary_id);
	List<ReplyDto> selectCommentByDiaryId(int diary_id);
	List<ReplyDto> selectCommentByWriter(Long writer);
}
