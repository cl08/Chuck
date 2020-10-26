package com.ssafy.chuck.group.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ssafy.chuck.group.dto.GroupDto;
import com.ssafy.chuck.group.dto.MemberDto;

@Mapper
public interface GroupDao {

	// 1. 그룹 생성
	int create(GroupDto group);

	// 2. 업데이트 토큰
	int updateToken(@Param(value = "id") int id, @Param(value = "token") String token);

	// 3. 그룹 수정
	int update(GroupDto group);

	// 4. 그룹 상세 조회
	GroupDto read(int id);

	// 5. 그룹 삭제
	int delete(int id);

	// 6. 그룹에 멤버 추가
	int createMember(MemberDto member);

	// 7. 그룹내 멤버 전체 조회
	List<MemberDto> readAllMember(int id);
}
