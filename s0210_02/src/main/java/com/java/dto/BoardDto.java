package com.java.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BoardDto {
	private Integer bno; // 객체타입은 null
	private String btitle;
	private String bcontent;
	private String id;	
	private int bgroup;
	private int bindent;
	private int bhit;
	private int bstep;
	private Timestamp bdate;
}
