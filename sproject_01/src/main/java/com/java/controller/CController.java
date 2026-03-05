package com.java.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java.dto.BoardDto;
import com.java.service.CustomerService;

@Controller
public class CController {
	
	@Autowired CustomerService customerService;

	@GetMapping("/customer/customer")
	public String customer(
			@RequestParam(name="page",defaultValue = "1") int page,
			@RequestParam(name="category", required = false) String category,
			@RequestParam(name="search", required = false) String search,
			Model model) {
		
//		System.out.println("category : "+category);
//		System.out.println("search : "+search);
		
		Map<String, Object> map = customerService.findAll(page, category, search);
		
		model.addAttribute("map",map);
		
		return "customer/customer";
	}
	
	@GetMapping("/customer/customerWrite")
	public String customerWrite() {
			
		return "customer/customerWrite";
	}
}
