package com.java.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java.dto.MemberDto;
import com.java.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class MController {
	
	@Autowired
	HttpSession session;
	@Autowired
	MemberService memberService;

	@GetMapping("/member/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/?flag=0";
	}
	
	@GetMapping("/member/login")
	public String login(String flag,Model model) {
		model.addAttribute("flag",flag);
		return "login";
	}
	
	// 1. HttpServletRequest
	// 2. @RequestParam
	// 3. 객체 (form의 name값이 자동으로 Dto의 필드로 인식됨 MemberDto memberDto
	
	@PostMapping("/member/login")
	public String login(MemberDto member, Model model) {
		System.out.println("id : "+member.getId());
		MemberDto m = memberService.selectIdAndPw(member);
		if(m != null ) {
			System.out.println(m.getId());
			session.setAttribute("session_id", m.getId());
			session.setAttribute("session_name", m.getName());
			return "redirect:/?flag=1";
		}else {
			System.out.println("일치하지 않음");
		}
	
		return "redirect:/member/login?flag=2";
	}
	
}
