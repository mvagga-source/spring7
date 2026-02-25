package com.java.controller;

import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java.dto.MemberDto;
import com.java.service.MemberService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class MController {
	
	@Autowired MemberService memberService;
	@Autowired HttpSession session;
//	@Autowired Cookie cookie;

	
	@GetMapping("/member/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/";
	}	
	
	@GetMapping("/member/login")
	public String login() {
		return "login";
	}
	
	@PostMapping("/member/login")
	public String dlogin(MemberDto mDto, 
			@RequestParam(name="rememberId", required = false) String rememberId,
			HttpServletResponse response) {
		
		System.out.println("dlogin id : "+mDto.getId());
		System.out.println("dlogin pw : "+mDto.getPw());
		System.out.println("rememberId : "+rememberId);
		
		Cookie cookie = new Cookie("cookie_id", mDto.getId());

		if(rememberId != null) {
			cookie.setMaxAge(60*60*24*30);
		}else {
			cookie.setMaxAge(0);
		}
		response.addCookie(cookie);
		
		MemberDto memberDto = memberService.findByIdAndPw(mDto);
		
		if(memberDto != null) {
			
			System.out.println("memberDto.getName() : "+memberDto.getName());
			
			session.setAttribute("session_id", memberDto.getId());
			session.setAttribute("session_name", memberDto.getName());
			
			return "redirect:/";
		}else {
			System.out.println("아이디 또는 비밀번호 불일치!");
			return "login";
		}
		
	}
}
