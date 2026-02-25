package com.java.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.java.dto.MemberDto;

@Controller
public class MController {
	
	@GetMapping("/member/login")
	public String login() {
		return "login";
	}
	
	@PostMapping("/member/dologin")
	public String dologin(MemberDto mDto) {
		
		System.out.println("controller id : "+mDto.getId());		
		System.out.println("controller pw : "+mDto.getPw());
		
		return "login";
		//return "redirect:/";
	}
}
