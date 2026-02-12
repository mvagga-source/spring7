package com.java.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.java.dto.MemberDto;
import com.java.mapper.MemberMapper;

public class MemberDaoImpl implements MemberDao {

	@Autowired
	MemberMapper memberMapper;
	
	@Override
	public List<MemberDto> selectAll() {
		List<MemberDto> list = memberMapper.selectAll();
		return list;
	}

}
