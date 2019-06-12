package com.zlkj.visitor.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zlkj.visitor.dao.DdwhDao;
import com.zlkj.visitor.dto.PulicCxtj;
import com.zlkj.visitor.entity.Ddmc;
import com.zlkj.visitor.service.DdwhService;

@Service
public class DdwhServiceImpl implements DdwhService{

	@Autowired 
	private DdwhDao dao;

	public List<Ddmc> findAll() {
		return dao.findAll();
	}

	public List<Ddmc> findAllFy(PulicCxtj pulicCxtj) {
		if (null!=pulicCxtj.getDdmc()) {
			pulicCxtj.setDdmc(pulicCxtj.getDdmc()+"%");
		}
		int index = pulicCxtj.getPage();
		int size = pulicCxtj.getRows();
		int minTotal = (index - 1) * size ;
		pulicCxtj.setPage(minTotal);
		pulicCxtj.setRows(size);
		return dao.findAllFy(pulicCxtj);
	}

	public long findAllFyCount(PulicCxtj pulicCxtj) {
		return dao.findAllFyCount(pulicCxtj);
	}

	public int add(Ddmc ddmc) {
		return dao.add(ddmc);
	}

	public int delete(String ids) {
		String[] strs=ids.split(",");
		return dao.delete(strs);
	}

	public int update(Ddmc ddmc) {
		return dao.update(ddmc);
	}
	public String ddbh2ddmc(String ddbh) {
		return dao.ddbh2ddmc(ddbh);
	}
	public String ddmc2ddbh(String ddmc) {
		return dao.ddmc2ddbh(ddmc);
	}


}
