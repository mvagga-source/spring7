package com.java.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.java.dto.BoardDto;
import com.java.dto.SearchDto;
import com.java.service.BoardService;

@Controller
public class BController {
	
	@Autowired BoardService boardService;
	
	@GetMapping("/board/blist")
	public String blist(Model model) {
		int page = 1;
		Map<String, Object> map = boardService.selectAll(page);
		model.addAttribute("map",map);
		return "blist";
	}
	
	// 글쓰기
	@GetMapping("/board/bwrite")
	public String bwrite() {
		return "bwrite";
	}
	
	// 글쓰기 저장
	@PostMapping("/board/bwrite")
	public String bwrite(BoardDto bDto,
			@RequestPart("file") MultipartFile files,
			Model model) {
		
		if(!files.isEmpty()) {
			//원본이름
			String originFileName = files.getOriginalFilename();
			long time = System.currentTimeMillis();
			//UUID uuid = UUID.randomUUID(); // 랜덤문자를 리턴
			
			String uploadFileName = String.format("%s_%s", time, originFileName);
			System.out.println("실재파일이름 : "+uploadFileName);
			System.out.println("btitle : "+bDto.getBtitle());
			
			// 파일위치
			String fileUrl = "c:/upload/";
			File f = new File(fileUrl+uploadFileName);
			
			try {
				files.transferTo(f);
				bDto.setBfile(uploadFileName);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		boardService.insertBoard(bDto);
		
		return "redirec:/board/blist";
	}
	
	
	@GetMapping("/board/bsearch")
	public String bsearch(SearchDto searchDto, Model model) {
		
		int page = 1;
		
		List<BoardDto> list = boardService.selectSearch(searchDto, page);
		model.addAttribute("list",list);
		
		return "blist";
	}
	
	
	@GetMapping("/board/bview")
	public String bview(BoardDto bDto, Model model) {
		BoardDto bdto = boardService.selectOne(bDto);
		model.addAttribute("board",bdto);
		return "bview";
	}
	
	
}
