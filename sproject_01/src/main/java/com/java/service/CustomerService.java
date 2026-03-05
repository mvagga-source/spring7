package com.java.service;

import java.util.List;
import java.util.Map;

import com.java.dto.BoardDto;

public interface CustomerService {

	Map<String, Object> findAll(int page, String category, String search);
}
