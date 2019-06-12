package com.zlkj.visitor.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zlkj.visitor.dao.XjyzdpzDao;
import com.zlkj.visitor.entity.TXjyzdpz;
import com.zlkj.visitor.service.XjyzdpzService;

@Service
public class XjyzdpzServiceImpl implements XjyzdpzService{
	@Autowired
	private XjyzdpzDao dao;

	public void save(TXjyzdpz dt) {
		dao.save(dt);
	}

	public List<TXjyzdpz> findByXjyzdmc(String xjmc) {
		return dao.findByXjyzdmc(xjmc);
	}

	public List<TXjyzdpz> findByxjmcAndyzdmc(String yzdmc, String xjmc) {
		return dao.findByxjmcAndyzdmc(yzdmc, xjmc);
	}

	public TXjyzdpz findByxjyzdmc_delete(String xjmc) {
		return null;
	}

	public int deleteXjyzdmc(String xjyzdmc) {
		return dao.deleteXjyzdmc(xjyzdmc);
	}

	public int updateXjyzdmc(TXjyzdpz t) {
		return dao.updateXjyzdmc(t);
	}

}
