package com.java.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.java.dto.BoardDto;
import com.java.service.BoardService;

@Controller
public class BController {
	
	@Autowired // DI주입
	BoardService boardService;
	
	@GetMapping("/board/boardList")
	public String boardList(Model model) {
		
		List<BoardDto> list = boardService.selectAll();
		
		return "boardList";
	}
}
