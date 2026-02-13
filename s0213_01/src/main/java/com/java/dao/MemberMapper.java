package com.java.dao;

import org.apache.ibatis.annotations.Mapper;

import com.java.dto.MemberDto;

@Mapper
public interface MemberMapper {

	MemberDto selectLogin(MemberDto mDto);

	// 성공 : 1, 실패 : 0
	void insertMember(MemberDto mDto);

	MemberDto selectOne(String id);
	
	void updateMember(MemberDto mDto);

}
