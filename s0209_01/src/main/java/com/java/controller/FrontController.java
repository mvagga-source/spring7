package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//@ 어노테이션   @Component  =  @Repository, @Controller, @Service
@Controller // 모든 url주소는 controller 주소로 들어오게 됨
public class FrontController {

	@Autowired
	Product product;
	
	@GetMapping("/index") // http://localhost:8181/index
	public String index() {
		System.out.println("스프링 자동 di : "+product.getName());
		
		Tv2 tv2 = new Tv2();
		System.out.println("객체선언 : "+tv2.getName());
		
		Member member = new Member();
		member.setId("aaa");
		System.out.println(member.getId());
		
		
		return "index";
	}
	
	@GetMapping("/member")
	public String member() {
		System.out.println("test");
		return "member";
	}
	
}
