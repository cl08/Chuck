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

	// 8. 유저의 모든 그룹리스트 조회
	List<GroupDto> readAllGroup(long id);

	// 9. 그룹장 조회
	long readOwner(int id);

	// 10. 그룹장 변경
	int change(@Param(value = "id")int id, @Param(value = "userId")long userId);

	int changeMemberTrue(@Param(value = "userId")long userId);

	int changeMemberFalse(@Param(value = "userId")long userId);
}
