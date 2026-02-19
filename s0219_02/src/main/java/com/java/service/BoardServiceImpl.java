package com.java.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dao.BoardMapper;
import com.java.dto.BoardDto;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired BoardMapper boardMapper;
	
	// 게시글 전체 가져오기
	@Override
	public List<BoardDto> selectAll() {
		
		List<BoardDto> list = boardMapper.selectAll();
		
		return list;
	}
	
	// 게시글 한개 가져오기
	@Override
	public BoardDto selectOne(BoardDto bDto) {
		
		BoardDto bdto = boardMapper.selectOne(bDto);
		
		return bdto;
	}	
	
	
	// 게시글쓰기
	@Override
	public void insertBoard(BoardDto bDto) {
		boardMapper.insertBoard(bDto);
	}

	// 게시글 삭제
	@Override
	public void deleteBoard(BoardDto bDto) {
		boardMapper.deleteBoard(bDto);
		
	}

	// 게시글 수정
	@Override
	public BoardDto updateBoard(BoardDto bDto) {
		boardMapper.updateBoard(bDto);
		
		BoardDto bdto = boardMapper.selectOne(bDto);
		return bdto;
	}

	// 답변달기 저장
	@Override
	public void insertReply(BoardDto bDto) {
		// 같은 bgroup에 있는 현재 게시글보다 더 높은 bstep을 1씩 증가
		boardMapper.updateReply(bDto);
		
		// 답변달기 저장
		boardMapper.insertReply(bDto);
	}

	


}
