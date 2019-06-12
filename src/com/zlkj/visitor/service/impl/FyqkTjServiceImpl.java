package com.zlkj.visitor.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zlkj.visitor.dao.FyqkTjDao;
import com.zlkj.visitor.dto.TjtCsDto;
import com.zlkj.visitor.service.FyqkTjService;

@Service
public class FyqkTjServiceImpl implements FyqkTjService{

	@Autowired
	private FyqkTjDao dao;

	public List<TjtCsDto> finTjt(TjtCsDto tjtCsDto) {
		return dao.finTjt(tjtCsDto);
	}
	
	
	

}
