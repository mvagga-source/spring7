package com.java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dao.MemberMapper;
import com.java.dto.MemberDto;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberMapper memberMapper;
	
	@Override // 로그인
	public MemberDto selectLogin(MemberDto mDto) {
		MemberDto memberDto = memberMapper.selectLogin(mDto);
		return memberDto;
	}

	@Override // 회원등록
	public void insertMember(MemberDto mDto) {
		int result = memberMapper.insertMember(mDto);
		System.out.println("result : "+result);
	}
	
}
