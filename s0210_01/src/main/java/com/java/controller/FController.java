package com.java.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // class의 sratic같이 메모리에 미리 등록됨
public class FController {
	
	@GetMapping("/")
	public String index(Integer flag,Model model) {
		System.out.println("flag : "+flag);
		model.addAttribute("flag",flag);
		model.addAttribute("now",new Date());
		return "index";
	}
	
}
