package com.java.service;

import com.java.dto.MemberDto;

public interface MemberService {

	MemberDto selectLogin(MemberDto mDto);
	
	// 회원가입
	void insertMember(MemberDto mDto);

	// 회원검색
	MemberDto selectOne(String id);

	// 회원수정
	void updateMember(MemberDto mDto);

}
