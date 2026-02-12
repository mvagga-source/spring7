package com.java.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.java.dto.MemberDto;
import com.java.service.MemberService;

@Controller
public class MController {
	
	@Autowired
	MemberService memberService;
	
	@GetMapping("/member/mlist")
	public String mlist(Model model) {
		
		List<MemberDto> list = memberService.selectAll();
		model.addAttribute("list",list);
		
		return "mlist";
	}
}
