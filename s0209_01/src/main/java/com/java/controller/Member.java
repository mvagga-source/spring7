package com.java.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor // 전체생성자
@NoArgsConstructor // 기본생성자
@Data // @Setter + @Getter
public class Member {

	private String id;
	private String pw;
	private String name;
	
	
	
}
