package com.java.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.java.dto.BoardDto;
import com.java.dto.SearchDto;

@Mapper
public interface BoardMapper {

	// 게시글 전체 가져오기
	int selectAllCount();
	List<BoardDto> selectAll(@Param("startRow") int startRow, @Param("endRow")int endRow);
	
	// 검색
	List<BoardDto> selectSearch(SearchDto searchDto);	

	// 게시글 한개 가져오기
	BoardDto selectOne(BoardDto bDto);
	
	// 게시글 쓰기
	void insertBoard(BoardDto bDto);

	// 게시글 삭제
	void deleteBoard(BoardDto bDto);

	// 게시글 수정
	void updateBoard(BoardDto bDto);

	// 게시글 답변 bstep1증가
	void updateReply(BoardDto bDto);

	// 게시글 답변 저장
	void insertReply(BoardDto bDto);


	

	
	
}
