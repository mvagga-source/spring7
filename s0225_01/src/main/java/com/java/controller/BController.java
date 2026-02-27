package com.java.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.java.dto.BoardDto;
import com.java.dto.MemberDto;
import com.java.service.BoardService;
import com.java.service.MemberService;

import jakarta.servlet.http.HttpSession;



@Controller
//@RestController // 데이터로 전달
public class BController {
	
	@Autowired BoardService boardService;
	@Autowired MemberService memberService;
	@Autowired HttpSession session;

	
	// 답변 수정 저장
	@PostMapping("/board/breply")
	public String breply(BoardDto bDto, Model model,
			@RequestPart(name="file") MultipartFile files) {
		
		System.out.println("breply bgroup : "+bDto.getBgroup());
		System.out.println("breply bstep : "+bDto.getBstep());
		
		// 파일 이름 저장
		if(!files.isEmpty()) {
			String fName = files.getOriginalFilename();
			long time = System.currentTimeMillis();
			String refName = String.format("%s_%s", time, fName);
			System.out.println("파일이름 : "+fName );
			System.out.println("파일변경 이름 : "+refName );
			
			String fileUploadUrl = "c:/upload/";
			
			File f = new File(fileUploadUrl+refName);
			try {
				files.transferTo(f);
				bDto.setBfile(refName);  //변경된 파일이름을 저장
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		String id = (String)session.getAttribute("session_id");
		
		// 작성자의 ID정보들로 변경필요
		MemberDto memberDto = memberService.findById(id);
		bDto.setMemberDto(memberDto);
		
		// 글수정
		boardService.reply(bDto);
				
		return "redirect:/board/blist";
	}
	
	// 답변 수정
	@GetMapping("/board/breply")
	public String breply(Model model,
			@RequestParam(name="bno", required = false) Integer bno) {
		System.out.println("breply bno : "+bno);
		
		Map<String, Object> map = boardService.findById(bno);
		model.addAttribute("board",map.get("boardDto"));
		
		return "breply";
	}	
	
	
	// 게시글 수정저장
	@PostMapping("/board/bupdate")
	public String bupdate(BoardDto bDto, Model model,
			@RequestPart(name="file") MultipartFile files) {
		
		System.out.println("bupdate bno : "+bDto.getBno());
		System.out.println("bupdate bfile : "+bDto.getBfile());
		
		// 파일 이름 저장
		if(!files.isEmpty()) {
			String fName = files.getOriginalFilename();
			long time = System.currentTimeMillis();
			String refName = String.format("%s_%s", time, fName);
			System.out.println("파일이름 : "+fName );
			System.out.println("파일변경 이름 : "+refName );
			
			String fileUploadUrl = "c:/upload/";
			
			File f = new File(fileUploadUrl+refName);
			try {
				files.transferTo(f);
				bDto.setBfile(refName);  //변경된 파일이름을 저장
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// 글수정
		boardService.update(bDto);
				
		return "redirect:/board/blist";
	}
	
	// 게시글 수정
	@GetMapping("/board/bupdate")
	public String bupdate(Model model,
			@RequestParam(name="bno", required = false) Integer bno) {
		System.out.println("bupdate bno : "+bno);
		
		Map<String, Object> map = boardService.findById(bno);
		model.addAttribute("board",map.get("boardDto"));
		//model.addAttribute("board",boardDto);
		
		return "bupdate";
	}	

	
	@ResponseBody // 데이터 전달
	@DeleteMapping("/board/bdelete")
	public String bdelete(Model model,
			@RequestParam(name="bno", required = false) Integer bno
			) {
		System.out.println("bdelete bno : "+bno);
		boardService.deleteById(bno);
		return "성공";
	}
	
	// 상세보기 페이지
	@GetMapping("/board/bview/{bno}")
	public String bview(Model model,
			@PathVariable(name="bno", required = false) Integer bno
			) {
		
		System.out.println("bview bno : "+bno);
		Map<String, Object> map = boardService.findById(bno);
		model.addAttribute("board",map.get("boardDto"));
		model.addAttribute("preBoard",map.get("preDto"));
		model.addAttribute("nextBoard",map.get("nextDto"));
		//model.addAttribute("board",boardDto);
		
		return "bview";
	}
	
	// 글쓰기
	@GetMapping("/board/bwrite")
	public String bwrite(Model model) {
		return "bwrite";
	}
	
	// 글쓰기 저장
	@PostMapping("/board/bwrite")
	public String bwrite(BoardDto bDto, Model model,
			@RequestPart(name="file") MultipartFile files) {
		
		String id = (String)session.getAttribute("session_id");
		System.out.println("bwrite title : "+bDto.getBtitle());
		
		// 파일 이름 저장
		if(!files.isEmpty()) {
			String fName = files.getOriginalFilename();
			long time = System.currentTimeMillis();
			String refName = String.format("%s_%s", time, fName);
			System.out.println("파일이름 : "+fName );
			System.out.println("파일변경 이름 : "+refName );
			
			String fileUploadUrl = "c:/upload/";
			
			File f = new File(fileUploadUrl+refName);
			try {
				files.transferTo(f);
				bDto.setBfile(refName);  //변경된 파일이름을 저장
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//MemberDto객체를 검색해서 저장
		MemberDto memberDto = memberService.findById(id);
		bDto.setMemberDto(memberDto);
		
		// 글쓰기 저장
		boardService.save(bDto);
		
		return "redirect:/board/blist";
	}
	
	// 게시판
	@GetMapping("/board/blist")
	public String blist(Model model) {
		
		//정렬
		Sort sort = Sort.by(
				Sort.Order.desc("bgroup"),Sort.Order.asc("bstep")
//				Sort.Order.desc("btitle")
				);
		
		List<BoardDto> list = boardService.findAll(sort);
		
		System.out.println("list size : "+list.size());
		
		model.addAttribute("list",list);
		return "blist";
	}
}
