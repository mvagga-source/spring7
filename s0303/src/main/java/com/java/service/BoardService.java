package com.java.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.java.dto.BoardDto;

public interface BoardService {

	// 게시판 리스트
//	Page<BoardDto> findAll(Pageable pageable);
//	Page<BoardDto> findAll(int page,int size);
//	List<BoardDto> findAll(Sort sort);	
	Map<String, Object> findAll(int page,int size);
	
	// 게시판 리스트 - 검색
	Map<String, Object> findContains(int page, int size, String category, String search);

	// 글쓰기 저장
	void save(BoardDto bDto);
	
	// 게시글 수정 저장
	void update(BoardDto bDto);

	// 게시글 가져오기
	Map<String, Object> findById(Integer bno);

	// 게시글 삭제
	void deleteById(Integer bno);

	// 답변달기
	void reply(BoardDto bDto);





}
