package com.java.service;

import java.util.List;

import com.java.dto.BoardDto;

public interface BoardService {

	// 게시글 저체 조회
	List<BoardDto> selectAll();

}
