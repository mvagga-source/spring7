package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.dto.CommentDto;
import com.java.service.CommentService;

import jakarta.servlet.http.HttpSession;

@Controller
public class CommentController {
	
	@Autowired HttpSession session;
	@Autowired CommentService commentService;

	@ResponseBody
	@PostMapping("/comment/save")
	public CommentDto commentSave(CommentDto cDto,
			@RequestParam(name="bno", defaultValue = "1") int bno
			) {
		
		// cno : 자동부여, ccontent, bno, id, cdate : 자동부여
//		System.out.println("내용 : "+cDto.getCcontent());
//		System.out.println("게시글번호 : "+bno);
//		System.out.println("ID : "+session.getAttribute("session_id"));
		
		CommentDto commentDto = commentService.save(cDto, bno);
		//System.out.println("aa : "+commentDto.getCcontent());
		return commentDto;
	}
	
	@ResponseBody
	@DeleteMapping("/comment/delete")
	public String commentDelete(CommentDto cDto) {
		
		int cno = cDto.getCno();
		System.out.println("cno : "+cno);
		
		commentService.deleteById(cDto);
		
		return "success";
	}
	
	@ResponseBody
	@PutMapping("/comment/update")
	public CommentDto commentUpdate(CommentDto cDto) {
		
		System.out.println("cno : "+cDto.getCno());
		System.out.println("cno : "+cDto.getCcontent());
		
		CommentDto commentDto = commentService.save(cDto);
		//CommentDto commentDto = new CommentDto();
		
		return commentDto;
	}
	
	
	
}
