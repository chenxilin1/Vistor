package com.zlkj.visitor.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.zlkj.visitor.dao.TFkxxDao;
import com.zlkj.visitor.dto.PulicCxtj;
import com.zlkj.visitor.entity.TFkxx;
import com.zlkj.visitor.service.DdwhService;
import com.zlkj.visitor.service.TFkxxService;

@Service
public class TFkxxServiceImpl implements TFkxxService {
	
	@Autowired
	private TFkxxDao dao;
	@Autowired
	private DdwhService ddwhService;

	public List<TFkxx> findAllFy(PulicCxtj pulicCxtj) {
		if (null!=pulicCxtj.getUserName()) {
			pulicCxtj.setUserName(pulicCxtj.getUserName()+"%");
		}
		if (null!=pulicCxtj.getEmail()) {
			pulicCxtj.setEmail(pulicCxtj.getEmail()+"%");
		}
		//当前时间
		String dqsj = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		pulicCxtj.setDqsj(dqsj);
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

	public TFkxx findByHphm(String hphm) {
		return null;
	}

	public int add(TFkxx fkxx) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dqsj=sdf.format(new Date());
		fkxx.setCreateTime(dqsj);
		fkxx.setHphm(fkxx.getHp()+fkxx.getHm()+fkxx.getZhi());
		if (null!=fkxx.getUserpath()) {
			String[] dd=fkxx.getUserpath().split(",");
			String ddmc="";
			if (dd.length!=0) {
				for (String string : dd) {
					String dds=ddwhService.ddmc2ddbh(string);
					if (null==dds) {
						dds=string;
					}
					ddmc+=dds+",";
				}
				ddmc=ddmc.substring(0, ddmc.length()-1);
			}
			fkxx.setUserpath(ddmc);
		}
		return dao.add(fkxx);
	}

	public int delete(String ids) {
		return 0;
	}

	public int zhuxiao(String ids) {
		return dao.zhuxiao(ids);
	}

	public int update(TFkxx fkxx) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dqsj=sdf.format(new Date());
		fkxx.setCreateTime(dqsj);
		fkxx.setHphm(fkxx.getHp()+fkxx.getHm()+fkxx.getZhi());
		if (null!=fkxx.getUserpath()) {
			String[] dd=fkxx.getUserpath().split(",");
			String ddmc="";
			if (dd.length!=0) {
				for (String string : dd) {
					String dds=ddwhService.ddmc2ddbh(string);
					if (null==dds) {
						dds=string;
					}
					ddmc+=dds+",";
				}
				ddmc=ddmc.substring(0, ddmc.length()-1);
			}
			fkxx.setUserpath(ddmc);
		}
		
		
		return dao.update(fkxx);
	}

}
