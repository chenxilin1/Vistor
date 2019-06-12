package com.zlkj.visitor.dao;

import java.util.List;

import com.zlkj.visitor.entity.TJgaddress;
import com.zlkj.visitor.entity.TXjpz;

public interface XjpzDao {
	/**
	 * 下拉列表显示设备
	 * @return
	 */
	public List<TXjpz> findAllSb();
	/**
	 * 查询所有的地点
	 * @return
	 */
	public List<TJgaddress> findAlldd();
	/**
	 * 根据相机名称查询详细信息
	 * @param xjmc
	 * @return
	 */
	List<TXjpz> findByXjmc(String xjmc);
	//保存
	void save(TXjpz dt);
	//查找
	TXjpz findByxjmc_delete (String xjmc);
	//删除
	int deleteXjmc(String xjmc);
	//修改
	int updateXjmc(TXjpz t);
	//根据相机IP查找
	List<TXjpz> findByXjip(String xjip);
}
