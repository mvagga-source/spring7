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

import jakarta.servlet.http.HttpSession;



@Controller // JSP페이지 리턴
//@RestController // 문자 리턴
public class MController {
	
	@Autowired MemberService memberService;
	@Autowired HttpSession session;
	
	// 회원삭제
	@GetMapping("/member/delete")
	public String delete(MemberDto mDto) {
		System.out.println("controller id : "+mDto.getId());
		memberService.deleteById(mDto);
		return "redirect:/";
	}
	
	@GetMapping("/member/memberShip")
	public String memberShip(Model model) {
		return "memberShip";
	}
	
	@PostMapping("/member/memberShip")
	public String memberShip(
			@RequestParam(name="phone1", required = false) String phone1,
			@RequestParam(name="phone2", required = false) String phone2,
			@RequestParam(name="phone3", required = false) String phone3,
			MemberDto mDto) {
		System.out.println("controller mdto : "+mDto.getId());
		String phone = String.format("%s-%s-%s", phone1, phone2, phone3);
		mDto.setPhone(phone);
		System.out.println("controller phone : "+mDto.getPhone());
		
		// db저장
		memberService.save(mDto);
		
		return "memberShip";
	}
	
	@GetMapping("/member/mlist")
	public String mlist(Model model) {
		List<MemberDto> list = memberService.findAll();
		System.out.println("controller mlist : "+list.size());
		model.addAttribute("list",list);
		return "mlist";
	}
	
	@GetMapping("/member/login")
	public String login() {
		return "login";
		//return "문자를 리턴";
	}
	
	@PostMapping("/member/login")
	public String dologin(MemberDto mDto) {
		
		System.out.println("id : "+mDto.getId());
		System.out.println("pw : "+mDto.getPw());
		
		MemberDto memberDto = memberService.findByIdAndPw(mDto);
	
		if(memberDto != null) {
			System.out.println("로그인이 되었습니다.");
			// 세션을 저장해서 메인으로 리턴
			session.setAttribute("session_id", memberDto.getId());
			session.setAttribute("session_name", memberDto.getName());
			
			return "redirect:/";
			
		}else {
			System.out.println("로그인 또는 비밀번호 불일치!");
			// 로그인페이지로 리턴
			return "login";	
		}
	}
	
	@GetMapping("/member/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/";
	}
	
}
