package com.java.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.java.dto.BoardDto;

public interface CustomerRepository extends JpaRepository<BoardDto, Integer> {

	Page<BoardDto> findByBtitleContaining(String btitle, Pageable pageable);

	Page<BoardDto> findByBcontentContaining(String bcontent, Pageable pageable);

	Page<BoardDto> findByBtitleContainingOrBcontentContaining(String btitle, String bcontent, Pageable pageable);

}
