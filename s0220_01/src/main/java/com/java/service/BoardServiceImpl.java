package com.java.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dao.BoardMapper;
import com.java.dto.BoardDto;
import com.java.dto.SearchDto;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired BoardMapper boardMapper;
	
	// 게시글 전체 가져오기
	@Override
	public Map<String, Object> selectAll(int page) {
		
		// 총게시글 수, 현재 페이지, 최대페이지, 첫페이지, 마지막 페이지
		// page : 현재페이지
		int countAll = boardMapper.selectAllCount();
		double rowPerPage = 10; // 한페이지 갯수
		
		int maxPage= (int)Math.ceil(countAll/rowPerPage);
		int startPage = ((page-1)/10)*10+1;
		int endPage = (startPage+10)-1;
		
		// 2.해당 페이지의 게시긓 가져오기
		int startRow = (page-1)*10+1;
		int endRow = startRow + (int)rowPerPage - 1;
		
		System.out.println("startrow : "+startRow);
		System.out.println("endRow : "+endRow);
		
		List<BoardDto> list = boardMapper.selectAll(startRow,endRow);
		
		//리턴해야 할것 - list,총게시글수,현재페이지,최대페이지,첫페이지,마지막페이지
		Map<String, Object> map = new HashMap<>();
		map.put("countAll", countAll);
		map.put("page", page);
		map.put("maxPage", maxPage);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		map.put("list", list);
		
		return map;
	}
	
	// 검색
	@Override
	public List<BoardDto> selectSearch(SearchDto searchDto) {

		List<BoardDto> list = boardMapper.selectSearch(searchDto);
		
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
