package com.ssafy.chuck.picture.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.chuck.picture.dao.PictureDao;

@Service
public class PictureServiceImpl implements PictureService{
	@Autowired
	private PictureDao pictureDao;
	
	@Override
	public int insertPicture(int diary_id, String path) {
		return pictureDao.insertPicture(diary_id, path);
	}

	@Override
	public int deletePictureByPath(String path) {
		return pictureDao.deletePictureByPath(path);
	}
}
