package com.java.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java.dto.BoardDto;
import com.java.service.BoardService;

import jakarta.servlet.http.HttpSession;

@Controller
public class BController {

	@Autowired BoardService boardService;
	@Autowired HttpSession session;

	
	// 답변 저장
	@PostMapping("/board/breply")
	public String dobreply(BoardDto bDto) {
		
		String id = (String)session.getAttribute("session_id");
		bDto.setId(id);
		boardService.insertReply(bDto);
		
		return "redirect:/board/blist";
	}
	
	// 답변
	@GetMapping("/board/breply")
	public String breply(BoardDto bDto,Model model) {
		
		BoardDto bdto = boardService.selectOne(bDto);
		model.addAttribute("board",bdto);
		
		return "board/breply";
	}	

	
	// 게시글 수정 저장
	@PostMapping("/board/bupdate")
	public String dobupdate(BoardDto bDto,Model model) {
	
		// 수정
		BoardDto bdto = boardService.updateBoard(bDto);
		model.addAttribute("board",bdto);
		String flag = "3";
		String url = String.format("redirect:/board/bview?bno=%s&flag=%s",bDto.getBno(),flag);
		return url;
	}
	
	// 게시글 수정
	@GetMapping("/board/bupdate")
	public String bupdate(
			//@RequestParam(name = "bno",defaultValue = "1") int bno,
			BoardDto bDto,
			Model model) {
		
		// 수정
		BoardDto bdto = boardService.selectOne(bDto);
		model.addAttribute("board",bdto);
		
		return "board/bupdate";
	}
	
	// 게시글 삭제
	@GetMapping("/board/bdelete")
	public String bdelete(BoardDto bDto) {
		String id = (String)session.getAttribute("session_id");
		BoardDto bdto = boardService.selectOne(bDto);
		if(!id.equals(bdto.getId())) return "redirect:/member/login";
		
		// 삭제
		boardService.deleteBoard(bDto);
		
		return "redirect:/board/blist?flag=2";
	}
	
	
	@GetMapping("/board/bview")
	public String bview(Model model,
			@RequestParam(name="flag",required = false) String flag,
			BoardDto bDto) {
		
		System.out.println("bno : "+bDto.getBno());
		BoardDto bdto = boardService.selectOne(bDto);
		model.addAttribute("board",bdto);
		model.addAttribute("flag",flag);
		return "/board/bview";
	}
	
	
	// 게시판 전체 내용
	@GetMapping("/board/blist")
	public String blist(Model model,
			@RequestParam(name="flag",required = false) String flag
			) {

		List<BoardDto> list = boardService.selectAll();
		model.addAttribute("list",list);
		model.addAttribute("flag",flag);
		return "board/blist";
	}
	
	// 게시판 글쓰기 화면 출력
	@GetMapping("/board/bwrite")
	public String bwrite() {
		return "board/bwrite";
	}
	
	// 게시판 글쓰기
	@PostMapping("/board/bwrite")
	public String bwrite(BoardDto bDto, Model model) {
		
		System.out.println("제목 : "+bDto.getBtitle());
		
		String id = (String)session.getAttribute("session_id");
		bDto.setId(id);
		
		boardService.insertBoard(bDto);
		
		return "redirect:/board/blist?flag=1";
	}	
}
