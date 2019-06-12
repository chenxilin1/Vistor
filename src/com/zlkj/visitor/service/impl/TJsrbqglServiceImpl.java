package com.zlkj.visitor.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zlkj.visitor.dao.TJsrbqglDao;
import com.zlkj.visitor.dto.PulicCxtj;
import com.zlkj.visitor.entity.TJsrk;
import com.zlkj.visitor.service.DdwhService;
import com.zlkj.visitor.service.TJsrbqglService;

@Service
public class TJsrbqglServiceImpl implements TJsrbqglService{

	@Autowired
	TJsrbqglDao dao;
	@Autowired
	private DdwhService ddwhService;
	
	public List<TJsrk> findAllFy(PulicCxtj pulicCxtj) {
		if (null!=pulicCxtj.getSfzh() && !"".equals(pulicCxtj.getSfzh())) {
			pulicCxtj.setSfzh("%"+pulicCxtj.getSfzh()+"%");
		}
		if (null!=pulicCxtj.getSyr() && !"".equals(pulicCxtj.getSyr())) {
			pulicCxtj.setSyr("%"+pulicCxtj.getSyr()+"%");
		}
		String dqsj = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		pulicCxtj.setDqsj(dqsj);
		if (!"0".equals(pulicCxtj.getBh()) && !"1".equals(pulicCxtj.getBh())) {
			pulicCxtj.setBh(null);
		}
		/*if (null!=pulicCxtj.getDdmcsz()) {
			String[] dd=pulicCxtj.getDdmcsz();
			String ddmc="";
			if (dd.length!=0) {
				for (String string : dd) {
					String dds=ddwhService.ddmc2ddbh(string);
					if (null==dds) {
						dds=string;
					}
					ddmc+="%"+dds+"%,";
				}
				ddmc=ddmc.substring(0, ddmc.length()-1);
			}
			pulicCxtj.setDdmcsz(ddmc.split(","));
		}*/
		int index = pulicCxtj.getPage();
		int size = pulicCxtj.getRows();
		int minTotal = (index - 1) * size;
		pulicCxtj.setPage(minTotal);
		pulicCxtj.setRows(size);
		return dao.findAllFy(pulicCxtj);
	}

	public long findAllFyCount(PulicCxtj pulicCxtj) {
		return dao.findAllFyCount(pulicCxtj);
	}

	public TJsrk findBySfzh(String sfzh) {
		return dao.findBySfzh(sfzh);
	}
	public TJsrk findById(String id) {
		return dao.findById(id);
	}
	
	public TJsrk findByIdAndSfzh(String id, String sfzh) {
		return dao.findByIdAndSfzh(id, sfzh);
	}
	
	public int add(TJsrk jsrk) {
		if (null!=jsrk.getSex()) {
			if ("女".equals(jsrk.getSex())) {
				jsrk.setSex("0");
			}else if ("男".equals(jsrk.getSex())) {
				jsrk.setSex("1");
			}
		}
		if (null!=jsrk.getKtxdd()) {
			String[] dd=jsrk.getKtxdd().split(",");
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
			jsrk.setKtxdd(ddmc);
		}
		String fqrq = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		jsrk.setFqsj(fqrq);
		if (null==jsrk.getJe()) {
			System.out.println(jsrk.getJe());
			jsrk.setJe(0);
		}
		
		return dao.add(jsrk);
	}

	public int delete(String ids) {
		String[] str=ids.split(",");
		return dao.delete(str);
	}



	public int update(TJsrk jsrk) {
		if (null!=jsrk.getSex()) {
			if ("女".equals(jsrk.getSex())) {
				jsrk.setSex("0");
			}else if ("男".equals(jsrk.getSex())) {
				jsrk.setSex("1");
			}
		}
		if (null!=jsrk.getKtxdd() & !"".equals(jsrk.getKtxdd())) {
			if (",".equals(jsrk.getKtxdd().substring(0, 1))) {
				jsrk.setKtxdd(jsrk.getKtxdd().substring(1,jsrk.getKtxdd().length()));
			}
			String[] dd=jsrk.getKtxdd().split(",");
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
			jsrk.setKtxdd(ddmc);
		}
		return dao.update(jsrk);
	}
	public int zhuxiao(String ids) {
		String zxsj = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		return dao.zhuxiao(ids,zxsj);
	}
	public int addZc(TJsrk jsrk) {
		String zxsj = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		jsrk.setYxjzrq(zxsj);
		jsrk.setZxsj(zxsj);
		return dao.addZc(jsrk);
	}
	
	
	

}
