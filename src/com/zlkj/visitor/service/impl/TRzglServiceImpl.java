package com.zlkj.visitor.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zlkj.visitor.dao.TRzglDao;
import com.zlkj.visitor.dto.PulicCxtj;
import com.zlkj.visitor.entity.TRzgl;
import com.zlkj.visitor.service.TRzglService;

@Service
public class TRzglServiceImpl implements TRzglService{

	@Autowired 
	private TRzglDao dao;
	public int addrz(TRzgl rzgl) {
		return dao.addrz(rzgl);
	}

	public List<TRzgl> findAllFy(PulicCxtj pulicCxtj) {
		String[] syr=pulicCxtj.getPpms();
		if (null!=syr) {
			String yhm="%";
			for (String string : syr) {
				yhm+=string+"%,";
			}
			syr=yhm.split(",");
			pulicCxtj.setPpms(syr);
		}
		if (null!=pulicCxtj.getBh()) {
			pulicCxtj.setBh("%"+pulicCxtj.getBh()+"%");
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
	
	public List<TRzgl> findMax3s() {
		return dao.findMax3s();
	}
	
	
	
}
