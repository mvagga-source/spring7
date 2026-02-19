package com.java.service;

import java.util.List;

import com.java.dto.BoardDto;

public interface BoardService {

	// 게시글 전체
	List<BoardDto> selectAll();


	// 게시글 한개
	BoardDto selectOne(BoardDto bDto);

	// 게시글 쓰기
	void insertBoard(BoardDto bDto);
	
	// 게시글 삭제
	void deleteBoard(BoardDto bDto);

	// 게시글 수정
	BoardDto updateBoard(BoardDto bDto);

	// 답변달기 저장
	void insertReply(BoardDto bDto);

}
