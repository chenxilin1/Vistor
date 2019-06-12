package com.zlkj.visitor.service;


import java.util.List;

import com.zlkj.visitor.entity.TXjyzdpz;


/**
 * 相机预置点配置
 * @author LYW 
 *  创建时间：2017-5-3 下午2:50:08
 *
 */
public interface XjyzdpzService {
	//保存
	void save(TXjyzdpz dt);
	//查找
	List<TXjyzdpz> findByXjyzdmc(String xjmc);
	//根据预置点名称和相机名称查找
	List<TXjyzdpz> findByxjmcAndyzdmc(String yzdmc,String xjmc);
	
	TXjyzdpz findByxjyzdmc_delete (String xjmc);
	//删除
	int deleteXjyzdmc(String xjyzdmc);
	//修改
	int updateXjyzdmc(TXjyzdpz t);
}
