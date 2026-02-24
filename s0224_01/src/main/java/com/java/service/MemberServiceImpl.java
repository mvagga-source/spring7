package com.java.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java.dto.MemberDto;
import com.java.repository.MemberRepository;


@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired MemberRepository memberRepository;

	@Override // 로그인 확인
	public MemberDto findByIdAndPw(MemberDto mDto) {
		
		System.out.println("service id : "+mDto.getId());
		System.out.println("service pw : "+mDto.getPw());
		
		// find = select, by = where 
		MemberDto memberDto = memberRepository.findByIdAndPw(mDto.getId(), mDto.getPw())
//				.get() //에러처리를 하지 앖음
				//.orElseGet(()->{return new MemberDto();}) // 기본객체 넘겨줌
//				.orElse(null)
//				.orElseThrow(()->{return new IllegalArgumentException("검색데이터가 없음");}) // 함수를 매개변수로 전달 / 람다식
				.orElseThrow(
						()->{return new IllegalArgumentException("에러");});
				;

		return memberDto;
	}

	@Override // 전체회원정보
	public List<MemberDto> findAll() {
		
		// 정렬
//		Sort sort = Sort.by(
//				Sort.Order.desc("mdate"),
//				Sort.Order.asc("id")
//				);
//		List<MemberDto> list = memberRepository.findAll(sort);
		
		List<MemberDto> list = memberRepository.findAll(
				Sort.by(Sort.Order.desc("mdate"),Sort.Order.asc("id"))
				);
		
		
		return list;
	}

	@Override // 회원가입 저장
	public void save(MemberDto mDto) {
		MemberDto result = memberRepository.save(mDto);
		System.out.println("service mDto : "+result);
	}

	@Transactional
	@Override // 회원삭제
	public void deleteById(MemberDto mDto) {
		memberRepository.deleteById(mDto.getId());
		
	}
	
}
