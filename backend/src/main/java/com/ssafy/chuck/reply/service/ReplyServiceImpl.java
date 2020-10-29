package com.ssafy.chuck.reply.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.chuck.common.annotation.ReplyLog;
import com.ssafy.chuck.reply.dao.ReplyDao;
import com.ssafy.chuck.reply.dto.ReplyDto;

@Service
public class ReplyServiceImpl implements ReplyService{

	@Autowired
	private ReplyDao replyDao;

	@ReplyLog
	@Override
	public int insertComment(Long writer, String comment, int diary_id) {
		Date date = new Date(System.currentTimeMillis());
		return replyDao.insertComment(writer, comment, diary_id, date);
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
