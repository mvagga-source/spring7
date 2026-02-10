package com.java.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java.dto.BoardDto;
import com.java.dto.MemberDto;

@Controller
public class FController {

	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	
	@GetMapping("/join")
	public String join() {
		return "join";
	}
	
	@PostMapping("/dojoin")
	public String dojoin(MemberDto mdto, Model model) {
		model.addAttribute("member",mdto);
		return "dojoin";
	}
	
	@GetMapping("/board")
	public String board() {
		return "board";
	}	

	@PostMapping("/doboard")
	public String doboard(BoardDto bdto, Model model) {
//		@RequestParam(name="bdto.GetBno()",defaultValue="0");
		model.addAttribute("board",bdto);
		return "doboard";
	}	
}
