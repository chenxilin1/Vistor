package com.zlkj.visitor.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zlkj.visitor.dao.XjpzDao;
import com.zlkj.visitor.entity.TJgaddress;
import com.zlkj.visitor.entity.TXjpz;
import com.zlkj.visitor.service.XjpzService;

@Service
public class XjpzServiceImpl implements XjpzService{

	@Autowired
	private XjpzDao dao;
	
	
	public List<TXjpz> findAllSb() {
		return dao.findAllSb();
	}

	public List<TJgaddress> findAlldd() {
		return null;
	}

	public List<TXjpz> findByXjmc(String xjmc) {
		return dao.findByXjmc(xjmc);
	}

	public void save(TXjpz dt) {
		dao.save(dt);
	}

	public TXjpz findByxjmc_delete(String xjmc) {
		return null;
	}

	public int deleteXjmc(String xjmc) {
		return dao.deleteXjmc(xjmc);
	}

	public int updateXjmc(TXjpz t) {
		return dao.updateXjmc(t);
	}

	public List<TXjpz> findByXjip(String xjip) {
		return dao.findByXjip(xjip);
	}

}
