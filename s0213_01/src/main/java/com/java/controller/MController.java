package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.java.dto.MemberDto;
import com.java.service.MemberService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class MController {
	
	@Autowired
	MemberService memberService;
	@Autowired
	HttpSession session;
	
	
	// 회원정보 수정
	@PostMapping("/member/mupdate")
	public String mupdate(MemberDto mDto,
			String phone1,String phone2,String phone3,
			String email1, String email2,			
			Model model) {
		
		String id = (String)session.getAttribute("session_id");
		mDto.setId(id);
		
		// 패스워드 확인
		MemberDto mdto = memberService.selectOne(id);
		if(!mdto.getPw().equals(mDto.getPw())) {
			System.out.println("패스워드 불일치!");
			return "redirect:/?flag=4";
		}
		
		String phone = String.format("%s-%s-%s", phone1.trim(),phone2.trim(),phone3.trim());
		String email = String.format("%s@%s", email1.trim(),email2.trim());
		
		mDto.setPhone(phone);
		mDto.setEmail(email);
		
		//db 수정
		memberService.updateMember(mDto);
		System.out.println("id : "+id);
		return "redirect:/?flag=3";
	}
	
	// 회원정보 확인
	@GetMapping("/member/mupdate")
	public String mupdate(Model model) {
		String id = (String)session.getAttribute("session_id");
		MemberDto mdto = memberService.selectOne(id);
		model.addAttribute("member",mdto);
		System.out.println("id : "+id);
		return "member/mupdate";
	}

	@PostMapping("/member/join03")
	public String join03(MemberDto mDto,
			String phone1,String phone2,String phone3,
			String email1, String email2,
			Model model) {
		
		String phone = String.format("%s-%s-%s", phone1.trim(),phone2.trim(),phone3.trim());
		String email = String.format("%s@%s", email1.trim(),email2.trim());
		
		mDto.setPhone(phone);
		mDto.setEmail(email);
		System.out.println("phone, email : "+mDto.getPhone()+","+mDto.getEmail());
		
		memberService.insertMember(mDto);
		
		model.addAttribute("name",mDto.getName());

		return "member/join03";
	} //

	@GetMapping("/member/join02")
	public String join02() {

		return "member/join02";
	} //	
	
	
	@GetMapping("/member/join01")
	public String join01() {

		return "member/join01";
	} //	
	
	
	// 로그아웃
	@GetMapping("/member/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/";
	} //
	
	@GetMapping("/member/login")
	public String login(String flag,Model model) {
		model.addAttribute("flag",flag);
		return "member/login";
	} //
	
	@PostMapping("/member/login")
	public String login(MemberDto mDto, Model model,
			HttpServletResponse response, // 쿠기용
			String saveId) {
		
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
