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
	
	@Autowired // <-- @Service   IOC 컨테이너에서 자동으로 주입
	BoardService boardService;
	
	@GetMapping("/board/blist")
	public String blist(Model model) {
		
		List<BoardDto> list = boardService.selectAll();
		model.addAttribute("list",list);
		System.out.println("list개수 : "+list.size());
		
		return "blist";
	}
	
	@GetMapping("/board/bview")
	public String bview(Integer bno, Model model) {
		
		List<BoardDto> list = boardService.selectOne(bno);
		model.addAttribute("list",list);
		System.out.println("list개수 : "+list.size());		
		
		return "bview";
	}
	
	
	
}
