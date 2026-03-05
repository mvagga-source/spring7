package com.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.dto.CommentDto;

public interface CommentRepository extends JpaRepository<CommentDto, Integer> {

}
