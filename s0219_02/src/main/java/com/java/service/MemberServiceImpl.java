package com.java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dao.MemberMapper;
import com.java.dto.MemberDto;


@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired MemberMapper memberMapper;

	@Override
	public MemberDto selectLogin(MemberDto mDto) {
		MemberDto mdto = memberMapper.selectLogin(mDto);

		// 부분생성자 : builder()
		//MemberDto mdto = new MemberDto().builder().id("aaa").pw("1111").build();
		return mdto;
	}
//
//	@Override
//	public void insertMember(MemberDto mDto) {
//		memberMapper.insertMember(mDto);
//		
//	}
//
//	@Override
//	public MemberDto selectOne(String id) {
//		MemberDto mdto = memberMapper.selectOne(id);
//		return mdto;
//	}
//
//	@Override //회원정보 수정
//	public void updateMember(MemberDto mDto) {
//		memberMapper.updateMember(mDto);
//	}

}
