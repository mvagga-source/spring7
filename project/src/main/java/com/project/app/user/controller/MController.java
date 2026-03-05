package com.project.app.user.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.app.user.dto.LoginRequest;
import com.project.app.user.dto.MemberDto;
import com.project.app.user.service.MemberService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000") // React 포트 허용
public class MController {
	
	// java -jar build/libs/project-0.0.1-SNAPSHOT.jar
	
	@Autowired MemberService memberService;
	@Autowired HttpSession session;
	

	@GetMapping("/member/mlist")
	public List<MemberDto> mlist() {
		
		System.out.println("test");
		
		List<MemberDto> list = memberService.findAll();
		return list;
	}
	
	@PostMapping("/member/mlogin")
	public ResponseEntity<?> mlogin(@RequestBody MemberDto mDto){
		
		System.out.println("login : "+mDto.getId());
		
		MemberDto memberDto = memberService.findByIdAndPw(mDto);

//	    // React로 보낼 데이터
	    Map<String, Object> response = new HashMap<>();		
		
	    if (memberDto == null) {
	        //return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "아이디 또는 비밀번호가 틀렸습니다."));
		    response.put("message", "login error");
		    session.invalidate();
	    }else {

		    // 세션 저장
		    session.setAttribute("session_id", memberDto.getId());
		    session.setAttribute("session_name", memberDto.getName());
	
		    response.put("username", memberDto.getName());
		    response.put("message", "login success");
	    }
		
		return ResponseEntity.ok(response);
	}
	
	
    @GetMapping("/api/check-session")
    public ResponseEntity<String> checkSession(HttpSession session) {
        // 세션에 "user"가 존재하면 로그인된 상태
        if (session.getAttribute("user") != null) {
            return ResponseEntity.ok("Session is active");
        } else {
            return ResponseEntity.status(401).body("No active session");
        }
    }
	
	
}
