package com.zlkj.visitor.dao;

import java.util.List;

import com.zlkj.visitor.entity.TCode;

public interface TCodeDao {

	//根据代码值查询代码名称
	public String dmzBydmmc(String dmz,String dmlb);
	//根据代码名称查询代码值
	public String dmmcBydmz(String dmmc,String dmlb);
	//查询所有的代码名称 返回集合
	public List<TCode> finListDmmc(String dmz);

	
	
	
	
	
}
