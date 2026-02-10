package com.java.controller;

import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.java.dto.BoardDto;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class BController {
	
	// @PathVariable	
	@GetMapping("/boardView/{bno}")
	//public ModelAndView boardView(@PathVariable(value="bno",required=false) int bno) {
	public ModelAndView boardView(@PathVariable int bno) {
		
		// 부분 생성자
		BoardDto b = BoardDto.builder().bno(1).btitle("제목 입니다.").bcontent("내용입니다.").build();
		
		System.out.println("@PathVariable bno : "+bno);
		ModelAndView mv = new ModelAndView();
		mv.addObject("bno",bno);
		mv.setViewName("boardView");
		return mv;
	}
	
	// ModelAndView
//	@GetMapping("/boardView")
//	public ModelAndView boardView(Integer bno,Model model) {
//		System.out.println("bno : "+bno);
//		ModelAndView mv = new ModelAndView();
//		mv.addObject("bno",bno);
//		mv.setViewName("boardView");
//		return mv;
//	}
	
	@GetMapping("/board")
	public String board() {
		return "board";
	}
	
	// 객체타입
	@PostMapping("/board")
	public String board(
			BoardDto bdto, 
			Model model) {
				
//		model.addAttribute("bno",bdto.getBno());
//		model.addAttribute("btitle",bdto.getBtitle());
//		model.addAttribute("bcontent",bdto.getBcontent());
//		model.addAttribute("id",bdto.getId());
		
		model.addAttribute("board",bdto);
		
		return "doboard";
	}		
	
	// 1. HttpServletRequest 방식 --
	
//	@PostMapping("/board")
//	public String board(HttpServletRequest request, Model model) {
//		
//		int bno = Integer.parseInt(request.getParameter("bno"));
//		String btitle = request.getParameter("btitle");
//		String bcontent = request.getParameter("bcontent");
//		String id = request.getParameter("id");
//		
//		model.addAttribute("bno",bno);
//		model.addAttribute("btitle",btitle);
//		model.addAttribute("bcontent",bcontent);
//		model.addAttribute("id",id);
//		
//		return "doboard";
//	}
	
	// 2. @RequestParam 방식
//	@PostMapping("/board")
//	public String board(
//			@RequestParam("bno") String bno, 
//			@RequestParam("btitle") String btitle, 
//			@RequestParam("bcontent") String bcontent, 
//			@RequestParam("id") String id, 
//			Model model) {
//				
//		model.addAttribute("bno",bno);
//		model.addAttribute("btitle",btitle);
//		model.addAttribute("bcontent",bcontent);
//		model.addAttribute("id",id);		
//		
//		return "doboard";
//	}
	
	// 3. 축약 @RequestParam 방식 - 변수랑 이름이 같으면 생략 가능
//	@PostMapping("/board")
//	public String board(
//			@RequestParam int bno, 
//			@RequestParam String btitle, 
//			@RequestParam String bcontent, 
//			@RequestParam String id, 
//			Model model) {
//				
//		model.addAttribute("bno",bno);
//		model.addAttribute("btitle",btitle);
//		model.addAttribute("bcontent",bcontent);
//		model.addAttribute("id",id);		
//		
//		return "doboard";
//	}
	
	// 4. 객체타입
//	@PostMapping("/board")
//	public String board(
//			@RequestParam(name="bno",defaultValue="0")
//			int bno,  // 자동형변환 시 값이 없을 경우 null값일때 에러 발생
//			String btitle, 
//			String bcontent, 
//			String id, 
//			Model model) {
//				
//		model.addAttribute("bno",bno);
//		model.addAttribute("btitle",btitle);
//		model.addAttribute("bcontent",bcontent);
//		model.addAttribute("id",id);		
//		
//		return "doboard";
//	}		
}
