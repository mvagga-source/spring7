package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java.dto.MemberDto;
import com.java.service.MemberService;

import jakarta.servlet.http.HttpSession;

@Controller
public class MController {
	
	@Autowired MemberService memberService;
	@Autowired HttpSession session;	

	@GetMapping("/member/login")
	public String login() {
		return "member/login";
	}
	
	@PostMapping("/member/dologin")
	public String dologin(MemberDto mDto, Model model) {
		
		//System.out.println("id : "+mDto.getId());
		MemberDto memberDto = memberService.findByIdAndPw(mDto);
		
		if(memberDto != null) {
			session.setAttribute("session_id", memberDto.getId());
			session.setAttribute("session_pw", memberDto.getPw());
			
			model.addAttribute("flag",1);
		}else {
			model.addAttribute("flag",-1);
		}

		return "member/login";
	}
}
