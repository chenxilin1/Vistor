package com.zlkj.visitor.dao;

import java.util.List;

import com.zlkj.visitor.dto.PulicCxtj;
import com.zlkj.visitor.entity.TRzgl;

public interface TRzglDao {
	/**
	 * 插入日志信息
	 * @param rzgl
	 * @return
	 */
	public int addrz(TRzgl rzgl);
	/**
	 * 分页查询日志信息
	 * @param pulicCxtj
	 * @return
	 */
	public List<TRzgl> findAllFy(PulicCxtj pulicCxtj);
	/**
	 * 分页查询日志信息
	 * @param pulicCxtj
	 * @return
	 */
	public long findAllFyCount(PulicCxtj pulicCxtj);
	
	/**
	 * 查询最新的三条数据
	 * @param pulicCxtj
	 * @return
	 */
	public List<TRzgl> findMax3s();
	
	
	
	
	
	
	
	
}
