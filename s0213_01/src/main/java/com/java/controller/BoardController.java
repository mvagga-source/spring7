package com.java.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.java.dto.BoardDto;
import com.java.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	// 게시글 쓰기
	@GetMapping("/board/bwrite")
	public String bwrite() {
		return "board/bwrite";
	}
	
	// 게시글 쓰기 저장
	@PostMapping("/board/bwrite")
	public String bwrite(BoardDto bDto,Model model) {
		System.out.println("bwrite btitle : "+bDto.getBtitle());
		return "redirect:/board/blist?flag=1";
	}	
	
	
	@GetMapping("/board/blist")
	public String blist(Model model) {
		List<BoardDto> list = boardService.selectAll();
		
		System.out.println("list 갯수 : "+list.size());
		
		model.addAttribute("list",list);
		return "board/blist";
	}
}
