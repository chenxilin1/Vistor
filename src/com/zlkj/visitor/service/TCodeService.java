package com.zlkj.visitor.service;

import java.util.List;

import com.zlkj.visitor.entity.TCode;

public interface TCodeService {

	//车辆通行-车辆类型 根据代码值查询代码名称
	public String cltxFindCllx_dmmc(String dmz);
	//车辆通行-号牌种类 根据代码值查询代码名称
	public String cltxFindHpzl_dmmc(String dmz);
	//车辆通行-车辆类型 根据代码名称查询代码值
	public String cltxFindCllx_dmz(String dmmc);
	//车辆通行-号牌种类 根据代码名称查询代码值
	public String cltxFindHpzl_dmz(String dmmc);
	//查询所得号牌种类 返回集合
	public List<TCode> finListhpzl();
	//查询所得车辆类型 返回集合
	public List<TCode> finListcllx();
	//查询所有的车身颜色 返回集合
	public List<TCode> finListCsys();
	//根据车身颜色名称查询代码值
	public String findCsys_dmz(String dmmc);
	//根据车身颜色值查询代码名称
	public String findCsys_dmmc(String dmz);
	
}
