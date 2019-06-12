package com.zlkj.visitor.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zlkj.visitor.dao.TJdcbqglDao;
import com.zlkj.visitor.dto.PulicCxtj;
import com.zlkj.visitor.entity.TAllmsg;
import com.zlkj.visitor.service.DdwhService;
import com.zlkj.visitor.service.TCodeService;
import com.zlkj.visitor.service.TJdcbqglService;

@Service
public class TJdcbqglServiceImpl implements TJdcbqglService {

	@Autowired
	private TJdcbqglDao dao;
	@Autowired
	private TCodeService codeService;
	@Autowired
	private DdwhService ddwhService;
	
	public List<TAllmsg> findAllFy(PulicCxtj pulicCxtj) {
		if (null!=pulicCxtj.getBqffqk()) {
			if (!"0".equals(pulicCxtj.getBqffqk()) &&!"1".equals(pulicCxtj.getBqffqk())) {
				pulicCxtj.setBqffqk(null);
			}
		}
		String dqsj = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		pulicCxtj.setDqsj(dqsj);
		if (!"0".equals(pulicCxtj.getBh()) && !"1".equals(pulicCxtj.getBh())) {
			pulicCxtj.setBh(null);
		}
		if ("全部".equals(pulicCxtj.getHp()) || null == pulicCxtj.getHp()) {
			if ("全部".equals(pulicCxtj.getHm()) || null == pulicCxtj.getHm()) {
				if ("".equals(pulicCxtj.getZhi())) {
					pulicCxtj.setHphm(null);
				} else {
					pulicCxtj.setHphm(pulicCxtj.getZhi());
				}
			} else {
				if (null!=pulicCxtj.getZhi() & !"".equals(pulicCxtj.getZhi())) {
					pulicCxtj.setHphm(pulicCxtj.getHm() + "%"+pulicCxtj.getZhi());
				}else {
					pulicCxtj.setHphm(pulicCxtj.getHm());
				}
			}
		} else {
			if ("全部".equals(pulicCxtj.getHm()) || null == pulicCxtj.getHm()) {
				if ("".equals(pulicCxtj.getZhi())) {
					pulicCxtj.setHphm(pulicCxtj.getHp());
				}else {
					pulicCxtj.setHphm(pulicCxtj.getHp()+"%"+pulicCxtj.getZhi());
				}
			} else {
				if (null!=pulicCxtj.getZhi() & !"".equals(pulicCxtj.getZhi())) {
					pulicCxtj.setHphm(pulicCxtj.getHp() + "%"+pulicCxtj.getHm() + "%"+pulicCxtj.getZhi());
				}else {
					pulicCxtj.setHphm(pulicCxtj.getHp() + "%"+pulicCxtj.getHm());
				}
			}
		}
		if (null != pulicCxtj.getHphm()) {
			pulicCxtj.setHphm("%" + pulicCxtj.getHphm() + "%");
		}
		if (null != pulicCxtj.getHpzl() && pulicCxtj.getHpzl().length != 0) {
			String hp = "";
			for (String hp1 : pulicCxtj.getHpzl()) {
				String hpzl = codeService.cltxFindHpzl_dmz(hp1);
				hp += hpzl + ",";
			}
			String[] st = hp.split(",");
			pulicCxtj.setHpzl(st);
		} else {
			pulicCxtj.setHpzl(null);
		}
		if (null!=pulicCxtj.getSyr() & !"".equals(pulicCxtj.getSyr())) {
			pulicCxtj.setSyr("%"+pulicCxtj.getSyr()+"%");
		}
		if (null!=pulicCxtj.getLxdh() & !"".equals(pulicCxtj.getLxdh())) {
			pulicCxtj.setLxdh("%"+pulicCxtj.getLxdh()+"%");
		}
		if (null!=pulicCxtj.getLd() & !"".equals(pulicCxtj.getLd())) {
			pulicCxtj.setLd("%"+pulicCxtj.getLd()+"%");
		}
		if (null!=pulicCxtj.getSsdw() & !"".equals(pulicCxtj.getSsdw())) {
			pulicCxtj.setSsdw("%"+pulicCxtj.getSsdw()+"%");
		}
		if (null!=pulicCxtj.getDdmcsz()) {
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
		}
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

	public TAllmsg findByHphm(String hphm) {
		return dao.findByHphm(hphm);
	}

	public TAllmsg findById(String id) {
		return dao.findById(id);
	}
	
	public TAllmsg findByIdAndHphm(String id, String hphm) {
		return dao.findByIdAndHphm(id, hphm);
	}
	
	public int add(TAllmsg allmsg) {
		if (null!=allmsg.getKtxdd()) {
			String[] dd=allmsg.getKtxdd().split(",");
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
			allmsg.setKtxdd(ddmc);
		}
		allmsg.setHphm(allmsg.getHp()+allmsg.getHm()+allmsg.getZhi());
		if (null!=allmsg.getHpzl()) {
			String hpzl=codeService.cltxFindHpzl_dmz(allmsg.getHpzl());
			if (null!=hpzl) {
				allmsg.setHpzl(hpzl);
			}
		}
		if (null!=allmsg.getCllx()) {
			String cllx=codeService.cltxFindCllx_dmz(allmsg.getCllx());
			if (null!=cllx) {
				allmsg.setCllx(cllx);
			}
		}
		if (null!=allmsg.getCsys()) {
			String csys=codeService.findCsys_dmz(allmsg.getCsys());
			if (null!=csys) {
				allmsg.setCsys(csys);
			}
		}
		//Date date= new Date();
		String fqrq = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		allmsg.setFqrq(fqrq);
		if (null==allmsg.getJe()) {
			System.out.println(allmsg.getJe());
			allmsg.setJe(0);
		}
		return dao.add(allmsg);
	}
	public int delete(String ids) {
		String[] sts=ids.split(",");
		return dao.delete(sts);
	}

	public int update(TAllmsg allmsg) {
		if (null!=allmsg.getKtxdd() & !"".equals(allmsg.getKtxdd())) {
			if (",".equals(allmsg.getKtxdd().substring(0, 1))) {
				allmsg.setKtxdd(allmsg.getKtxdd().substring(1,allmsg.getKtxdd().length()));
			}
			String[] dd=allmsg.getKtxdd().split(",");
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
			allmsg.setKtxdd(ddmc);
		}
		if (null!=allmsg.getHpzl()) {
			String hpzl=codeService.cltxFindHpzl_dmz(allmsg.getHpzl());
			if (null!=hpzl) {
				allmsg.setHpzl(hpzl);
			}
		}
		if (null!=allmsg.getCllx()) {
			String cllx=codeService.cltxFindCllx_dmz(allmsg.getCllx());
			if (null!=cllx) {
				allmsg.setCllx(cllx);
			}
		}
		if (null!=allmsg.getCsys()) {
			String csys=codeService.findCsys_dmz(allmsg.getCsys());
			if (null!=csys) {
				allmsg.setCsys(csys);
			}
		}
		return dao.update(allmsg);
	}
	public int zhuxiao(String ids) {
		String zxsj = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		
		return dao.zhuxiao(ids,zxsj);
	}
	public int addZx(TAllmsg allmsg) {
		String zxsj = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		allmsg.setXqrq(zxsj);
		allmsg.setYxjzrq(zxsj);
		return dao.addZx(allmsg);
	}
	public List<Map<String, Object>> outExcel(PulicCxtj pulicCxtj) {
		if (null!=pulicCxtj.getBqffqk()) {
			if (!"0".equals(pulicCxtj.getBqffqk()) &&!"1".equals(pulicCxtj.getBqffqk())) {
				pulicCxtj.setBqffqk(null);
			}
		}
		String dqsj = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		pulicCxtj.setDqsj(dqsj);
		if (!"0".equals(pulicCxtj.getBh()) && !"1".equals(pulicCxtj.getBh())) {
			pulicCxtj.setBh(null);
		}
		if ("全部".equals(pulicCxtj.getHp()) || null == pulicCxtj.getHp()) {
			if ("全部".equals(pulicCxtj.getHm()) || null == pulicCxtj.getHm()) {
				if ("".equals(pulicCxtj.getZhi())) {
					pulicCxtj.setHphm(null);
				} else {
					pulicCxtj.setHphm(pulicCxtj.getZhi());
				}
			} else {
				if (null!=pulicCxtj.getZhi()) {
					pulicCxtj.setHphm(pulicCxtj.getHm() + "%"+pulicCxtj.getZhi());
				}else {
					pulicCxtj.setHphm(pulicCxtj.getHm());
				}
			}
		} else {
			if ("全部".equals(pulicCxtj.getHm()) || null == pulicCxtj.getHm()) {
				if ("".equals(pulicCxtj.getZhi())) {
					pulicCxtj.setHphm(pulicCxtj.getHp());
				}else {
					pulicCxtj.setHphm(pulicCxtj.getHp()+"%"+pulicCxtj.getZhi());
				}
			} else {
				if (null!=pulicCxtj.getZhi()) {
					pulicCxtj.setHphm(pulicCxtj.getHp() + "%"+pulicCxtj.getHm() + "%"+pulicCxtj.getZhi());
				}else {
					pulicCxtj.setHphm(pulicCxtj.getHp() + "%"+pulicCxtj.getHm());
				}
			}
		}
		if (null != pulicCxtj.getHphm()) {
			pulicCxtj.setHphm("%" + pulicCxtj.getHphm() + "%");
		}
		if (null != pulicCxtj.getHpzl() && pulicCxtj.getHpzl().length != 0) {
			String hp = "";
			for (String hp1 : pulicCxtj.getHpzl()) {
				String hpzl = codeService.cltxFindHpzl_dmz(hp1);
				hp += hpzl + ",";
			}
			String[] st = hp.split(",");
			pulicCxtj.setHpzl(st);
		} else {
			pulicCxtj.setHpzl(null);
		}
		if (null!=pulicCxtj.getSyr() & !"".equals(pulicCxtj.getSyr())) {
			pulicCxtj.setSyr("%"+pulicCxtj.getSyr()+"%");
		}
		if (null!=pulicCxtj.getLxdh() & !"".equals(pulicCxtj.getLxdh())) {
			pulicCxtj.setLxdh("%"+pulicCxtj.getLxdh()+"%");
		}
		if (null!=pulicCxtj.getLd() & !"".equals(pulicCxtj.getLd())) {
			pulicCxtj.setLd("%"+pulicCxtj.getLd()+"%");
		}
		if (null!=pulicCxtj.getSsdw() & !"".equals(pulicCxtj.getSsdw())) {
			pulicCxtj.setSsdw("%"+pulicCxtj.getSsdw()+"%");
		}
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		List<TAllmsg> bfjdcsj = dao.outExcel(pulicCxtj);
		TAllmsg plbq = new TAllmsg();
		Map<String, Object> mapValue1 = new HashMap<String, Object>();
		/*for (String value : keys) {
			mapValue1.put(value, "");
		}*/
		mapValue1.put("bqid", "");
		mapValue1.put("hphm", "");
		mapValue1.put("hpzl", "");
		mapValue1.put("clpp", "");
		mapValue1.put("clxh", "");
		mapValue1.put("syr", "");
		mapValue1.put("csys", "");
		mapValue1.put("fdjh", "");
		mapValue1.put("clsbdh", "");
		mapValue1.put("sfzmhm", "");
		mapValue1.put("ccdjrq", "");
		mapValue1.put("fprq", "");
		mapValue1.put("djry", "");
		mapValue1.put("lxdh", "");
		mapValue1.put("cllx", "");
		mapValue1.put("fqrq", "");
		mapValue1.put("xqrq", "");
		mapValue1.put("bqzt", "");
		mapValue1.put("id", "");
		mapValue1.put("mz", "");
		mapValue1.put("ssdw", "");
		mapValue1.put("je", "");
		mapValue1.put("nwb", "");
		mapValue1.put("ktxdd", "");
		mapValue1.put("ssks", "");
		mapValue1.put("yxjzrq", "");
		mapValue1.put("fcphm", "");
		mapValue1.put("ld", "");
		mapValue1.put("sctpdz", "");
		listmap.add(mapValue1);
		for (int i = 0; i < bfjdcsj.size(); i++) {
			Map<String, Object> mapValue = new HashMap<String, Object>();
			plbq = bfjdcsj.get(i);
			//mapValue.put("type", Integer.parseInt(plbq.getType()));
			mapValue.put("bqid", plbq.getBqid());
			mapValue.put("hphm", plbq.getHphm());
			mapValue.put("hpzl", plbq.getHpzl());
			mapValue.put("clpp", plbq.getClpp());
			mapValue.put("clxh", plbq.getClxh());
			mapValue.put("syr", plbq.getSyr());
			mapValue.put("csys", plbq.getCsys());
			mapValue.put("fdjh", plbq.getFdjh());
			mapValue.put("clsbdh", plbq.getClsbdh());
			mapValue.put("sfzmhm", plbq.getSfzmhm());
			if (null!=plbq.getCcdjrq()) {
				mapValue.put("ccdjrq", plbq.getCcdjrq());
			}else {
				mapValue.put("ccdjrq", "");
			}
			if (null!=plbq.getFprq()) {
				mapValue.put("fprq", plbq.getFprq());
			}else {
				mapValue.put("fprq", "");
			}
			mapValue.put("djry", plbq.getDjry());
			mapValue.put("lxdh", plbq.getLxdh());
			mapValue.put("cllx", plbq.getCllx());
			mapValue.put("fqrq", plbq.getFqrq());
			mapValue.put("xqrq", plbq.getXqrq());
			mapValue.put("bqzt", plbq.getBqzt());
			mapValue.put("id", plbq.getId());
			mapValue.put("mz", plbq.getMz());
			mapValue.put("ssdw", plbq.getSsdw());
			mapValue.put("je", plbq.getJe());
			mapValue.put("nwb", plbq.getNwb());
			mapValue.put("ktxdd", plbq.getKtxdd());
			mapValue.put("ssks", plbq.getSsks());
			mapValue.put("yxjzrq", plbq.getYxjzrq());
			mapValue.put("fcphm", plbq.getFcphm());
			mapValue.put("ld", plbq.getLd());
			mapValue.put("sctpdz", plbq.getSctpdz());
			listmap.add(mapValue);
		}
		return listmap;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
