package com.java.service;

import com.java.dto.CommentDto;

public interface CommentService {

	CommentDto save(CommentDto cDto, int bno);

	void deleteById(CommentDto cDto);

	CommentDto save(CommentDto cDto);

}
