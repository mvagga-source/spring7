package com.java.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dao.MemberDao;
import com.java.dto.MemberDto;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberDao memnerDao;
	
	@Override
	public List<MemberDto> selectAll() {
		
		List<MemberDto> list = memnerDao.selectAll();
		return list;
	}

}
