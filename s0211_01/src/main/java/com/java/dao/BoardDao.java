package com.java.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.java.dto.BoardDto;

// IOC컨테이너  Component, Controller, Service, Repository, Configuation, Bean

@Mapper
public interface BoardDao {
	List<BoardDto> selectAll();

	List<BoardDto> selectOne(Integer bno);
}
