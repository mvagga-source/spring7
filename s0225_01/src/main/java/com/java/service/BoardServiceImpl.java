package com.java.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java.dto.BoardDto;
import com.java.repository.BoardRepository;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired BoardRepository boardRepository;
	
	// 게시판
	@Override
	public List<BoardDto> findAll(Sort sort) {
		
		List<BoardDto> list = boardRepository.findAll(sort);

		return list;
	}

	// 글쓰기 저장
	@Transactional // 메소드 완료시 기존의 연속성 context가 수정되면 db에 자동반영
	@Override 
	public void save(BoardDto bDto) {
		
		//Repository저장 시 객체를 리턴해줌
		BoardDto boardDto = boardRepository.save(bDto);
		boardDto.setBgroup(boardDto.getBno());
		//boardRepository.save(boardDto);  // @Transactional 

	}

	// 게시글 1개 가져오기
	@Transactional(readOnly = true) // 정합성을 유지
	@Override
	public Map<String, Object> findById(Integer bno) {
		
		Map<String, Object> map = new HashMap<>();
		
		// 이전글
		BoardDto preDto = boardRepository.findByPre(bno).orElse(null);
		// 다음글
		BoardDto nextDto = boardRepository.findByNext(bno).orElse(null);
		// 해당글
		BoardDto boardDto = boardRepository.findById(bno).orElse(null);
		
		map.put("preDto", preDto);
		map.put("nextDto", nextDto);
		map.put("boardDto", boardDto);
		
		// 조회수 증복방지 cookie, session, db 등록
		boardDto.setBhit(boardDto.getBhit()+1);
		
		return map;
	}

	// 게시글 삭제
	@Override
	public void deleteById(Integer bno) {
		boardRepository.deleteById(bno);
	}

	// 게시글 수정 저장
	@Transactional // 메소드 완료시 기존의 연속성 context가 수정되면 db에 자동반영	
	@Override
	public void update(BoardDto bDto) {
		BoardDto boardDto = boardRepository.findById(bDto.getBno()).get();
		boardDto.setBtitle(bDto.getBtitle());
		boardDto.setBcontent(bDto.getBcontent());
		boardDto.setBfile(bDto.getBfile());
		boardDto.setBdate(new Timestamp(System.currentTimeMillis()));
		
//		boardRepository.save(boardDto); // @Transactional 자동으로 처리됨
	}

	// 답변달기 저장
	@Transactional // 메소드 완료시 기존의 연속성 context가 수정되면 db에 자동반영	
	@Override
	public void reply(BoardDto bDto) {
		// 1.부모의 bgroup에서 부모보다 큰 bstep에 1을 증가
		boardRepository.replyBstepUp(bDto.getBgroup(),bDto.getBstep());

		// 부모의 bgroup에서 부모보다 큰 bstep에 1을 증가
		bDto.setBgroup(bDto.getBgroup());
		bDto.setBstep(bDto.getBstep()+1);
		bDto.setBindent(bDto.getBindent()+1);		
		
		// 2. 
		boardRepository.save(bDto);
	}

}
