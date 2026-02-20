package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	
	// 로그아웃
	@GetMapping("/member/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/";
	} //
	
	@GetMapping("/member/login")
	public String login(Model model,
			@RequestParam(name="flag",required = false) String flag) {
		model.addAttribute("flag",flag);
		return "member/login";
	} //
	
	@PostMapping("/member/login")
	public String login(MemberDto mDto, Model model,
			HttpServletResponse response, // 쿠기용
			@RequestParam(name="saveId",required = false) String saveId // required : 필수사항, defaultValue : 없을 경우 해당값으로 대체
			) {
		
		System.out.println("id,pw : "+mDto.getId()+","+mDto.getPw());
		
		// 쿠기설정
		Cookie cookie = new Cookie("cookie_id",mDto.getId());
		if(saveId != null) {
			cookie.setMaxAge(60*60*24*30); // 30일 저장
		}else {
			cookie.setMaxAge(0);
		}
		response.addCookie(cookie);
		
		// 세션 설정
		MemberDto mdto = memberService.selectLogin(mDto);
		if(mdto != null) {
			System.out.println("mdto name : "+mdto.getName());
			session.setAttribute("session_id", mdto.getId());
			session.setAttribute("session_name", mdto.getName());
			return "redirect:/?flag=1";
		}else {
			System.out.println("id 또는 비빌번호 존재하지 않음");
			return "redirect:/member/login?flag=2";
		}
	} // 
}
