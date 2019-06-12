package com.zlkj.visitor.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zlkj.visitor.dao.TCodeDao;
import com.zlkj.visitor.entity.TCode;
import com.zlkj.visitor.service.TCodeService;

@Service
public class TCodeServiceImpl implements TCodeService{

	@Autowired
	private TCodeDao dao;
	public String cltxFindCllx_dmmc(String dmz) {
		String dmlb="4";
		return dao.dmzBydmmc(dmz, dmlb);
	}

	public String cltxFindHpzl_dmmc(String dmz) {
		String dmlb="7";
		return dao.dmzBydmmc(dmz, dmlb);
	}

	public List<TCode> finListhpzl() {
		String dmz="7";
		return dao.finListDmmc(dmz);
	}

	public List<TCode> finListcllx() {
		String dmz="4";
		return dao.finListDmmc(dmz);
	}

	public String cltxFindCllx_dmz(String dmmc) {
		String dmlb="4";
		return dao.dmmcBydmz(dmmc, dmlb);
	}

	public String cltxFindHpzl_dmz(String dmmc) {
		String dmlb="7";
		return dao.dmmcBydmz(dmmc, dmlb);
	}
	public String findCsys_dmmc(String dmz) {
		String dmlb="8";
		return dao.dmzBydmmc(dmz, dmlb);
	}
	public String findCsys_dmz(String dmmc) {
		String dmlb="8";
		return dao.dmmcBydmz(dmmc, dmlb);
	}
	public List<TCode> finListCsys() {
		String dmz="8";
		return dao.finListDmmc(dmz);
	}
}
