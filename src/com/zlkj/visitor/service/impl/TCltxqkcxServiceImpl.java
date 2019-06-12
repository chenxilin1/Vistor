package com.zlkj.visitor.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zlkj.visitor.dao.TCltxqkcxDao;
import com.zlkj.visitor.dto.PulicCxtj;
import com.zlkj.visitor.entity.TCltxkb;
import com.zlkj.visitor.service.DdwhService;
import com.zlkj.visitor.service.TCltxqkcxService;
import com.zlkj.visitor.service.TCodeService;

@Service
public class TCltxqkcxServiceImpl implements TCltxqkcxService {

	@Autowired
	private TCltxqkcxDao dao;
	@Autowired
	private TCodeService codeService;
	@Autowired
	private DdwhService ddwhService;

	public List<TCltxkb> findAllFy(PulicCxtj pulicCxtj) {
		if (null != pulicCxtj.getXjfx()) {
			if ("0".equals(pulicCxtj.getXjfx())) {
				pulicCxtj.setXjfx("进口");
			} else if ("1".equals(pulicCxtj.getXjfx())) {
				pulicCxtj.setXjfx("出口");
			} else {
				pulicCxtj.setXjfx(null);
			}
		}
		if (null != pulicCxtj.getTxzt()) {
			if (!"0".equals(pulicCxtj.getTxzt()) && !"1".equals(pulicCxtj.getTxzt())) {
				pulicCxtj.setTxzt(null);
			}
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
		if (null!=pulicCxtj.getDdmcsz()) {
			String[] dd=pulicCxtj.getDdmcsz();
			String ddmc="";
			if (dd.length!=0) {
				for (String string : dd) {
					String dds=ddwhService.ddbh2ddmc(string);
					if (null==dds) {
						dds=string;
					}
					ddmc+=dds+",";
				}
				ddmc=ddmc.substring(0, ddmc.length()-1);
			}
			pulicCxtj.setDdmcsz(ddmc.split(","));
		}
		if (null != pulicCxtj.getSsdw() & !"".equals(pulicCxtj.getSsdw())) {
			pulicCxtj.setSsdw("%" + pulicCxtj.getSsdw() + "%");
		}
		if (null != pulicCxtj.getSfzh() & !"".equals(pulicCxtj.getSfzh())) {
			pulicCxtj.setSfzh("%" + pulicCxtj.getSfzh() + "%");
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

	public TCltxkb findById(String id) {
		return dao.findById(id);
	}
}
