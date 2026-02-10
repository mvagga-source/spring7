package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class MController {
	
	@Autowired // IoC컨테이너에서 시작할때 객체를 주입(의존성 주입)
	HttpSession session;

	@GetMapping("/member/login")
	public String login(Integer flag, Model model) {
		model.addAttribute("flag",flag);
		return "login";
	}
	
	@GetMapping("/member/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/";
	}
	
	@PostMapping("/member/login")
	//public String login(HttpServletRequest request, Model model) {
	public String login(String id, String pw) {
		
		if(id.equals("aaa") && pw.equals("1111")) {
			session.setAttribute("session_id", id);
			return "redirect:/?flag=1";
		} else {
			return "redirect:/member/login?flag=2";			
		}
		
	}
	
	
}
