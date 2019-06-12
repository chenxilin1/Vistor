package com.zlkj.visitor.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zlkj.visitor.dao.CljiTjDao;
import com.zlkj.visitor.dto.TjtCsDto;
import com.zlkj.visitor.entity.TCltxkb;
import com.zlkj.visitor.service.CljlTjService;
@Service
public class CljlTjServiceImpl implements CljlTjService{

	@Autowired
	private CljiTjDao dao;
	
	public List<TjtCsDto> finTjt(TjtCsDto tjtCsDto) {
		if ("全部".equals(tjtCsDto.getHp()) || null == tjtCsDto.getHp()) {
			if ("全部".equals(tjtCsDto.getHm()) || null == tjtCsDto.getHm()) {
				if ("".equals(tjtCsDto.getZhi())) {
					tjtCsDto.setHphm(null);
				} else {
					tjtCsDto.setHphm(tjtCsDto.getZhi());
				}
			} else {
				if (null!=tjtCsDto.getZhi()) {
					tjtCsDto.setHphm(tjtCsDto.getHm() + tjtCsDto.getZhi());
				}else {
					tjtCsDto.setHphm(tjtCsDto.getHm());
				}
			}
		} else {
			if ("全部".equals(tjtCsDto.getHm()) || null == tjtCsDto.getHm()) {
				tjtCsDto.setHphm(tjtCsDto.getHp());
			} else {
				if (null!=tjtCsDto.getZhi()) {
					tjtCsDto.setHphm(tjtCsDto.getHp() + tjtCsDto.getHm() + tjtCsDto.getZhi());
				}else {
					tjtCsDto.setHphm(tjtCsDto.getHp() + tjtCsDto.getHm());
				}
			}
		}
		if (null != tjtCsDto.getHphm()) {
			tjtCsDto.setHphm("%" + tjtCsDto.getHphm() + "%");
		}
		System.out.println("号牌号码="+tjtCsDto.getHphm());
		return dao.finTjt(tjtCsDto);
	}

	public List<TjtCsDto> djtjt(TjtCsDto tjtCsDto) {
		if ("全部".equals(tjtCsDto.getHp()) || null == tjtCsDto.getHp()) {
			if ("全部".equals(tjtCsDto.getHm()) || null == tjtCsDto.getHm()) {
				if ("".equals(tjtCsDto.getZhi())) {
					tjtCsDto.setHphm(null);
				} else {
					tjtCsDto.setHphm(tjtCsDto.getZhi());
				}
			} else {
				if (null!=tjtCsDto.getZhi()) {
					tjtCsDto.setHphm(tjtCsDto.getHm() + tjtCsDto.getZhi());
				}else {
					tjtCsDto.setHphm(tjtCsDto.getHm());
				}
			}
		} else {
			if ("全部".equals(tjtCsDto.getHm()) || null == tjtCsDto.getHm()) {
				tjtCsDto.setHphm(tjtCsDto.getHp());
			} else {
				if (null!=tjtCsDto.getZhi()) {
					tjtCsDto.setHphm(tjtCsDto.getHp() + tjtCsDto.getHm() + tjtCsDto.getZhi());
				}else {
					tjtCsDto.setHphm(tjtCsDto.getHp() + tjtCsDto.getHm());
				}
			}
		}
		if (null != tjtCsDto.getHphm()) {
			tjtCsDto.setHphm("%" + tjtCsDto.getHphm() + "%");
		}
		if (!"".equals(tjtCsDto.getTxdd()) && null!=tjtCsDto.getTxdd()) {
			tjtCsDto.setDd(tjtCsDto.getTxdd().split(","));
		}else {
			tjtCsDto.setDd(null);
		}
		return dao.djtjt(tjtCsDto);
		//return null;
	}

	public List<TCltxkb> djbtsj(TjtCsDto tjtCsDto) {
		if ("全部".equals(tjtCsDto.getHp()) || null == tjtCsDto.getHp()) {
			if ("全部".equals(tjtCsDto.getHm()) || null == tjtCsDto.getHm()) {
				if ("".equals(tjtCsDto.getZhi())) {
					tjtCsDto.setHphm(null);
				} else {
					tjtCsDto.setHphm(tjtCsDto.getZhi());
				}
			} else {
				if (null!=tjtCsDto.getZhi()) {
					tjtCsDto.setHphm(tjtCsDto.getHm() + tjtCsDto.getZhi());
				}else {
					tjtCsDto.setHphm(tjtCsDto.getHm());
				}
			}
		} else {
			if ("全部".equals(tjtCsDto.getHm()) || null == tjtCsDto.getHm()) {
				tjtCsDto.setHphm(tjtCsDto.getHp());
			} else {
				if (null!=tjtCsDto.getZhi()) {
					tjtCsDto.setHphm(tjtCsDto.getHp() + tjtCsDto.getHm() + tjtCsDto.getZhi());
				}else {
					tjtCsDto.setHphm(tjtCsDto.getHp() + tjtCsDto.getHm());
				}
			}
		}
		if (null != tjtCsDto.getHphm()) {
			tjtCsDto.setHphm("%" + tjtCsDto.getHphm() + "%");
		}
		if (!"".equals(tjtCsDto.getTxdd()) && null!=tjtCsDto.getTxdd()) {
			tjtCsDto.setDd(tjtCsDto.getTxdd().split(","));
		}else {
			tjtCsDto.setDd(null);
		}
		int index = tjtCsDto.getPage();
		int size = tjtCsDto.getRows();
		int minTotal = (index - 1) * size ;
		tjtCsDto.setPage(minTotal);
		tjtCsDto.setRows(size);
		
		return dao.djbtsj(tjtCsDto);
	}

	public long djbtsjCount(TjtCsDto tjtCsDto) {
		
		return dao.djbtsjCount(tjtCsDto);
	}
	public long findByDd(TjtCsDto tjtCsDto) {
		System.out.println(tjtCsDto);
		return dao.findByDd(tjtCsDto);
	}
}
