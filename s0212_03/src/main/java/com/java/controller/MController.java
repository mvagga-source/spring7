package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.java.dto.MemberDto;
import com.java.service.MemberService;

import jakarta.servlet.http.HttpSession;

@Controller
public class MController {
	
	@Autowired
	MemberService memberService;
	@Autowired
	HttpSession session;
	
	@GetMapping("/member/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/?flag=0";
	}
	
	@GetMapping("/member/login")
	public String login(String flag, Model model) {
		model.addAttribute("flag",flag);
		return "login";
	}
	
	@PostMapping("/member/login")
	public String login(MemberDto mDto) {
		//System.out.println("id : "+mDto.getId());
		MemberDto memberDto = memberService.selectLogin(mDto);
		
		if(memberDto != null) {
			
			System.out.println("아이디 및 비번 일치 ");
			session.setAttribute("session_id", memberDto.getId());
			session.setAttribute("session_name", memberDto.getName());
			return "redirect:/?flag=1";
			
		}else {
			System.out.println("아이디 및 비번 불일치");
			return "redirect:/member/login?flag=2";
		}
	}
}
