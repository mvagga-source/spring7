package com.java.controller;

import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.java.dto.MemberDto;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MController {

	@GetMapping("/login")
	public String login(Integer flag,Model model) {
		model.addAttribute("flag",flag);
		return "login";
	}
	
	@PostMapping("/login")
	public String login(HttpServletRequest request, Model model) {
		System.out.println("id : "+request.getParameter("id"));
		System.out.println("pw : "+request.getParameter("pw"));
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		if(id.equals("aaa") && pw.equals("1111") ) {
			return "redirect:/?flag=1";
		}else {
			return "redirect:/login?flag=2";
		}
	
//		model.addAttribute("id",id);
//		model.addAttribute("pw",pw);
//		return "dologin";
		
	}//login
	
	@GetMapping("/join")
	public String join() {
		return "join";
	}//join
	
	@GetMapping("/mUpdate")
	public String mUpdate(Model model) {
		
		MemberDto mdto = new MemberDto("aaa", "1111", "홍길동", "010-1111-1111", "aaa@naver.com", "남자", "골프,수영");
		model.addAttribute("member",mdto);
		return "mUpdate";
	}//join
	
	@PostMapping("/dojoin")	
	public String dojoin(HttpServletRequest request, Model model) {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String gender = request.getParameter("gender");
		
		String[] hobbys = request.getParameterValues("hobby");
		String hobby = Arrays.toString(hobbys);
		
		model.addAttribute("id",id);
		model.addAttribute("pw",pw);		
		model.addAttribute("name",name);		
		model.addAttribute("phone",phone);		
		model.addAttribute("email",email);		
		model.addAttribute("gender",gender);		
		model.addAttribute("hobby",hobby);	
		
		return "dojoin";
	}
	
}
