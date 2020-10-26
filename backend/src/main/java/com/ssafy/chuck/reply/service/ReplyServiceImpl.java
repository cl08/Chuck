package com.ssafy.chuck.reply.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.chuck.reply.dao.ReplyDao;
import com.ssafy.chuck.reply.dto.ReplyDto;

@Service
public class ReplyServiceImpl implements ReplyService{

	@Autowired
	private ReplyDao replyDao;
	
	@Override
	public int insertComment(Long writer, String comment, int diary_id) {
		return replyDao.insertComment(writer, comment, diary_id);
	}

	@Override
	public List<ReplyDto> selectCommentByDiaryId(int diary_id) {
		return replyDao.selectCommentByDiaryId(diary_id);
	}

	@Override
	public List<ReplyDto> selectCommentByWriter(Long writer) {
		return replyDao.selectCommentByWriter(writer);
	}
}
