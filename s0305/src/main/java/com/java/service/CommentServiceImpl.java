package com.java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java.dto.BoardDto;
import com.java.dto.CommentDto;
import com.java.dto.MemberDto;
import com.java.repository.BoardRepository;
import com.java.repository.CommentRepository;
import com.java.repository.MemberRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired CommentRepository commentRepository;
	@Autowired MemberRepository memberRepository;
	@Autowired BoardRepository boardRepository;
	@Autowired HttpSession session;
	
	
	@Override
	public CommentDto save(CommentDto cDto, int bno) {
		String id = (String) session.getAttribute("session_id");
		MemberDto memberDto = memberRepository.findById(id).get();
		cDto.setMemberDto(memberDto);
		
		BoardDto boardDto = boardRepository.findById(bno).get();
		cDto.setBoardDto(boardDto);
		
		CommentDto commentDto = commentRepository.save(cDto);  
		
		return commentDto;
	}
	
	@Override
	public void deleteById(CommentDto cDto) {
		commentRepository.deleteById(cDto.getCno());
	}

	// 하단댓글 수정저장
	@Transactional
	@Override
	public CommentDto save(CommentDto cDto) {
		
		// save() -> cno가 있으면 update, con가 없으면 insert
		// 없는정보 수집필요
		CommentDto commentDto = commentRepository.findById(cDto.getCno()).get();
		commentDto.setCcontent(cDto.getCcontent());
		
		// save 없음 @Transactional이 알아서 처리함

		return commentDto;
	}
	
}
