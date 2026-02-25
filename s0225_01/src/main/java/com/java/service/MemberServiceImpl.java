package com.java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dto.MemberDto;
import com.java.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired MemberRepository memberRepository;
	
	@Override
	public MemberDto findByIdAndPw(MemberDto mDto) {
		
		MemberDto memberDto = memberRepository.findByIdAndPw(mDto.getId(), mDto.getPw()).orElse(null);
		
		return memberDto;
	}

}
