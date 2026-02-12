package com.java.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dao.BoardDao;
import com.java.dto.BoardDto;

@Service // 객체선언 없이 사용가능 IOC컨테이너에 등록
public class BoardServiceImpl implements BoardService {
	
	@Autowired // <- @Mapper
	BoardDao boardDao;

	@Override
	public List<BoardDto> selectAll() {
		
		List<BoardDto> list = boardDao.selectAll();
		
//		List<BoardDto> list = new ArrayList<>();
//		list.add(new BoardDto(1, "제목1", "내용1", "aaa", 1, 0, 0, 0, null, new Timestamp(System.currentTimeMillis())));
//		list.add(new BoardDto(2, "제목2", "내용2", "bbb", 1, 0, 0, 0, null, new Timestamp(System.currentTimeMillis())));
		
		return list;
	}

	@Override
	public List<BoardDto> selectOne(Integer bno) {
		List<BoardDto> list = boardDao.selectOne(bno);
		return list;
	}

}
