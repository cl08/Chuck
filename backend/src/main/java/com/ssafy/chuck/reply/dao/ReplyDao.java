package com.ssafy.chuck.reply.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.chuck.reply.dto.ReplyDto;

@Mapper
public interface ReplyDao {
	int insertComment(Long writer, String comment, int diary_id, Date date);
	List<ReplyDto> selectCommentByDiaryId(int diary_id);
	List<ReplyDto> selectCommentByWriter(Long writer);
}
