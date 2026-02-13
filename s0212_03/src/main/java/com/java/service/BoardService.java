package com.java.service;

import java.util.List;

import com.java.dto.BoardDto;

public interface BoardService {

	// 게시판
	List<BoardDto> selectAll();

}
