package com.java.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.dto.BoardDto;
import com.java.repository.BoardRepository;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired BoardRepository boardRepository;
	
	// 게시판
	@ResponseBody
	@Override
	public Map<String, Object> findAll(int page, int size, String category, String search) {
		
		//정렬
		Sort sort = Sort.by(
				Sort.Order.desc("bgroup"),Sort.Order.asc("bstep"));
		
		Pageable pageable = PageRequest.of(page-1, size, sort);
		
		Page<BoardDto> pageList = null;
		
		if(category == null || category == "") {
			pageList = boardRepository.findAll(pageable);
		}else if(category.equals("all")) {
			pageList = boardRepository.findByBtitleContainingOrBcontentContaining(search, search, pageable);
		}else if(category.equals("btitle")) {
			
			System.out.println("search : "+search);
			
			pageList = boardRepository.findByBtitleContaining(search, pageable);
		}else if(category.equals("bcontent")) {
			pageList = boardRepository.findByBcontentContaining(search, pageable);
		}

		
		//System.out.println("pageList : "+pageList);
		List<BoardDto> list = pageList.getContent();
		
		System.out.println("size : "+pageList.getSize()); 
		
		int maxPage = pageList.getTotalPages();
		int startPage = ((page-1)/10)*10+1;
		int endPage = Math.min(startPage + 10-1,maxPage);
		Map<String, Object> map = new HashMap<>();
		
		map.put("list", list);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		map.put("maxPage", maxPage);
		map.put("page", page);
		map.put("category", category);
		map.put("search", search);

		return map;
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
		
		map.put("commentList", boardDto.getCommentList());
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
