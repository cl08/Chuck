package com.ssafy.chuck.reply.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.chuck.reply.dto.ReplyDto;

@Mapper
public interface ReplyDao {
	public int insertComment(Long writer, String comment, int diary_id, Date date);
	public List<ReplyDto> selectCommentByDiaryId(int diary_id);
	public List<ReplyDto> selectCommentByWriter(Long writer);
}