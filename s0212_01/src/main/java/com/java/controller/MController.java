package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.java.dto.MemberDto;
import com.java.service.MemberService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class MController {
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	HttpSession session;
	
	// 03.회원가입
	@PostMapping("/member/join03")
	public String join03(MemberDto mDto,
			String phone1,String phone2,String phone3,
			String email1,String email2 ) {
		
//		System.out.println("id : "+mDto.getId());
//		System.out.println("phone : "+phone1+"-"+phone2+"-"+phone3);
//		System.out.println("email : "+email1+"@"+email2);
		
		String phone = String.format("%s-%s-%s", phone1,phone2,phone3);
		String email = String.format("%s@%s", email1,email2);
		mDto.setPhone(phone);
		mDto.setEmail(email);
		
		memberService.insertMember(mDto);
		
		return "join03";
	}			
	
	// 02.회원가입
	@GetMapping("/member/join02")
	public String join02(Model model) {
		return "join02";
	}		
	
	// 01.회원가입
	@GetMapping("/member/join01")
	public String join01(Model model) {
		return "join01";
	}	
	
	
	// 로그아웃
	@GetMapping("/member/logout")
	public String logout(String flag,Model model) {
		session.invalidate();
		model.addAttribute("flag",flag);
		return "redirect:/?flag=0";
	}
	
	// 로그인
	@GetMapping("/member/login")
	public String login(String flag,Model model) {
		model.addAttribute("flag",flag);
		return "login";
	}
	
//	Cookie 설정
	@PostMapping("/member/login")
	public String login(MemberDto mDto, String saveId, HttpServletResponse response) {
		
		System.out.println("id,pw,saveId :"+mDto.getId()+","+mDto.getPw()+","+saveId);
		
		// 아이디 저장
		Cookie cookie = new Cookie("cook_id", mDto.getId());
		if(saveId != null) {
			cookie.setMaxAge(60*60*24*30); //60초*60분*24시간*30일
		}else {
			cookie.setMaxAge(0);
		}
		response.addCookie(cookie);

		//
		MemberDto memberDto = memberService.selectLogin(mDto);
		if(memberDto != null) {

			System.out.println("id,pw 일치");
			session.setAttribute("session_id", memberDto.getId());
			session.setAttribute("session_name", memberDto.getName());
			return "redirect:/?flag=1";
		}else{
			System.out.println("id,pw 불일치");
			return "redirect:/member/login?flag=2";
		}

	}
}
