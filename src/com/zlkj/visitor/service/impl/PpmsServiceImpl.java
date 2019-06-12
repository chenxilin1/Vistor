package com.zlkj.visitor.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zlkj.visitor.dao.PpmsDao;
import com.zlkj.visitor.dto.PulicCxtj;
import com.zlkj.visitor.entity.TJgaddress;
import com.zlkj.visitor.service.DdwhService;
import com.zlkj.visitor.service.PpmsService;

@Service
public class PpmsServiceImpl implements PpmsService{
	
	@Autowired
	private PpmsDao dao;
	@Autowired
	private DdwhService ddwhService;

	public List<TJgaddress> finAll(PulicCxtj pulicCxtj) {
		
		if (null!=pulicCxtj.getXjsbbh()) {
			if (!"1".equals(pulicCxtj.getXjsbbh()) && !"0".equals(pulicCxtj.getXjsbbh())) {
				pulicCxtj.setXjsbbh(null);
			}
		}
		if (null!=pulicCxtj.getCkip()) {
			if (!"1".equals(pulicCxtj.getCkip()) && !"0".equals(pulicCxtj.getCkip())) {
				pulicCxtj.setCkip(null);
			}
		}
		/*if (null!=pulicCxtj.getPpms()) {
			String str="";
			String[] sts=pulicCxtj.getPpms();
			for (String string : sts) {
				str+="%"+string+"%,";
			}
			str= str.substring(0,str.length()-1);
			pulicCxtj.setPpms(str.split(","));
		}*/
		
		if (null!=pulicCxtj.getNote()) {
			if (!"1".equals(pulicCxtj.getNote()) && !"0".equals(pulicCxtj.getNote())) {
				pulicCxtj.setNote(null);
			}
		}
		if (!"0".equals(pulicCxtj.getRfidms()) && !"1".equals(pulicCxtj.getRfidms())) {
			pulicCxtj.setRfidms(null);
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
					ddmc+=dds+",";
				}
				ddmc=ddmc.substring(0, ddmc.length()-1);
			}
			pulicCxtj.setDdmcsz(ddmc.split(","));
		}*/
		int index = pulicCxtj.getPage();
		int size = pulicCxtj.getRows();
		int minTotal = (index - 1) * size ;
		pulicCxtj.setPage(minTotal);
		pulicCxtj.setRows(size);
		return dao.finAll(pulicCxtj);
	}

	public long findAllCount(PulicCxtj pulicCxtj) {
		return dao.findAllCount(pulicCxtj);
	}

	public int add(TJgaddress jgaddress) {
		if (null != jgaddress.getCkip()) {
			if (jgaddress.getCkip().equals("出口")) {
				jgaddress.setCkip("1");
			} else if (jgaddress.getCkip().equals("进口")){
				jgaddress.setCkip("0");
			}
		}
		if (null != jgaddress.getXjsbbh()) {
			if (jgaddress.getXjsbbh().equals("启用")) {
				jgaddress.setXjsbbh("1");
			} else if (jgaddress.getXjsbbh().equals("未启用")){
				jgaddress.setXjsbbh("0");
			}
		}
		if (null!=jgaddress.getCkclfs()) {
			if ("处理数据发命令".equals(jgaddress.getCkclfs())) {
				jgaddress.setCkclfs("1");
			}else if ("处理数据不发命令".equals(jgaddress.getCkclfs())) {
				jgaddress.setCkclfs("2");
			}else if ("走匹配模式".equals(jgaddress.getCkclfs())) {
				jgaddress.setCkclfs("3");
			}
		}
		if (null!=jgaddress.getPpms()) {
			String ppms=jgaddress.getPpms();
			ppms = ppms.replace("车签", "1");
			ppms = ppms.replace("人签", "2");
			ppms = ppms.replace("车牌", "3");
			ppms = ppms.replace("且", ",");
			ppms = ppms.replace("或", "-");
			jgaddress.setPpms(ppms);
		}
		/*if (null!=jgaddress.getPpmsgx()) {
			if ("1".equals(jgaddress.getPpmsgx())) {
				jgaddress.setPpms(jgaddress.getPpms().replace(",", "-"));
			}else if ("0".equals(jgaddress.getPpmsgx())) {
				jgaddress.setPpms(jgaddress.getPpms().replace("-", ","));
			}
		}*/
		if (null != jgaddress.getXjfx()) {
			if (jgaddress.getXjfx().equals("内部")) {
				jgaddress.setXjfx("0");
			} else if (jgaddress.getXjfx().equals("外部")){
				jgaddress.setXjfx("1");
			}
		}
		return dao.add(jgaddress);
	}

	public int delete(String ids) {
		String[] strs=ids.split(",");
		return dao.delete(strs);
	}

	public int update(TJgaddress jgaddress) {
		if (null != jgaddress.getCkip()) {
			if (jgaddress.getCkip().equals("出口")) {
				jgaddress.setCkip("1");
			} else if (jgaddress.getCkip().equals("进口")){
				jgaddress.setCkip("0");
			}
		}
		if (null != jgaddress.getXjsbbh()) {
			if (jgaddress.getXjsbbh().equals("启用")) {
				jgaddress.setXjsbbh("1");
			} else if (jgaddress.getXjsbbh().equals("未启用")){
				jgaddress.setXjsbbh("0");
			}
		}
		if (null!=jgaddress.getCkclfs()) {
			if ("处理数据发命令".equals(jgaddress.getCkclfs())) {
				jgaddress.setCkclfs("1");
			}else if ("处理数据不发命令".equals(jgaddress.getCkclfs())) {
				jgaddress.setCkclfs("2");
			}else if ("走匹配模式".equals(jgaddress.getCkclfs())) {
				jgaddress.setCkclfs("3");
			}
		}
		if (null!=jgaddress.getPpms()) {
			String ppms=jgaddress.getPpms();
			ppms = ppms.replace("车签", "1");
			ppms = ppms.replace("人签", "2");
			ppms = ppms.replace("车牌", "3");
			ppms = ppms.replace("且", ",");
			ppms = ppms.replace("或", "-");
			jgaddress.setPpms(ppms);
		}
		/*if (null!=jgaddress.getPpmsgx()) {
			if ("1".equals(jgaddress.getPpmsgx())) {
				jgaddress.setPpms(jgaddress.getPpms().replace(",", "-"));
			}else if ("0".equals(jgaddress.getPpmsgx())) {
				jgaddress.setPpms(jgaddress.getPpms().replace("-", ","));
			}
		}*/
		if (null != jgaddress.getXjfx()) {
			if (jgaddress.getXjfx().equals("内部")) {
				jgaddress.setXjfx("0");
			} else if (jgaddress.getXjfx().equals("外部")){
				jgaddress.setXjfx("1");
			}
		}
		return dao.update(jgaddress);
	}

	public TJgaddress findByDdmc(String ddmc) {
		return dao.findByDdmc(ddmc);
	}
	public TJgaddress findByPpmsIp(String ip) {
		return dao.findByPpmsIp(ip);
	}
	
}
