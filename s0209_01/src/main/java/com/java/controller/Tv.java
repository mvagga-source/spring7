package com.java.controller;

import org.springframework.stereotype.Service;

@Service
public class Tv implements Product  {

	@Override
	public String getName() {
		String name = "삼성TV 번전 1";
		return name;
	}
	
}
