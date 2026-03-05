package com.java.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.java.dto.BoardDto;
import com.java.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired CustomerRepository customerRepository;
	
	@Override
	public Map<String, Object> findAll(int page, String category, String search) {

		System.out.println("S Impl category : "+category);		
		
		//정렬
		Sort sort = Sort.by(
				Sort.Order.desc("bgroup"),Sort.Order.asc("bstep"));		
		
		int size = 10;
		Page<BoardDto> pageList = null;
		
		Pageable pageable = PageRequest.of(page-1,size,sort);
		
		if(category == null || category == "") {
			
			pageList = customerRepository.findAll(pageable);
		}else if(category.equals("all")) {
		
			pageList = customerRepository.findByBtitleContainingOrBcontentContaining(search, search, pageable);
		}else if(category.equals("btitle")) {
		
			pageList = customerRepository.findByBtitleContaining(search, pageable);
		}else if(category.equals("bcontent")) {
			
			pageList = customerRepository.findByBcontentContaining(search, pageable);
		}
		
		Map<String, Object> map = pageMethod(pageList,page);
		
		map.put("category", category);
		map.put("search", search);
		
		return map;
	}

	
	public Map<String, Object> pageMethod(Page<BoardDto> pageList, int page){
		
		Map<String, Object> map = new HashMap<>();
		
		List<BoardDto> list = pageList.getContent();
		int maxPage = pageList.getTotalPages();
		int startPage = ((page-1)/10)*10+1;
		int endPage = Math.min(startPage+10-1,maxPage);  // if(endPage > maxPage) endPage = maxPage;
		
		map.put("list", list);
		map.put("maxPage", maxPage);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		map.put("page", page);		
		
		return map;
	}
	
	
}
