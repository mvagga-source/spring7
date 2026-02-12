package com.java.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dto.MemberDto;
import com.java.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {

	//내부객체 :  @Component @Controller @Service @Repository @Configuration @Bean
	//@Mapper : Mybatis 설치하면 생김
	@Autowired
	MemberMapper memberMapper;
	
	@Override
	public MemberDto selectIdAndPw(MemberDto member) {
		String id = member.getId();
		String pw = member.getPw();
		
		MemberDto m = memberMapper.selectIdAndPw(id,pw);
		return m;
	}
}
