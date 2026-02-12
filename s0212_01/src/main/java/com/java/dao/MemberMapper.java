package com.java.dao;

import org.apache.ibatis.annotations.Mapper;

import com.java.dto.MemberDto;

@Mapper
public interface MemberMapper {

	// 로그인
	MemberDto selectLogin(MemberDto mDto);
	
	// 회원등록
	int insertMember(MemberDto mDto);

}
