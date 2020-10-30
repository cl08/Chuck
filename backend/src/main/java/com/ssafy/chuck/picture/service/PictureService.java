package com.ssafy.chuck.picture.service;

public interface PictureService {
	public int insertPicture(int diary_id, String path);
	public int deletePictureByPath(String path);
	public int deletePictureById(int id);
}
