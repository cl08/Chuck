package com.ssafy.chuck.picture.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PictureDao {
	public int insertPicture(int diary_id, String path);
}
