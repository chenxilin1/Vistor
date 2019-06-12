package com.zlkj.visitor.service;

import java.util.List;

import com.zlkj.visitor.dto.PulicCxtj;
import com.zlkj.visitor.entity.TJsrk;

public interface TJsrbqglService {
	//查询 分页
	public List<TJsrk> findAllFy(PulicCxtj pulicCxtj);
	//查询 分页 总条数
	public long findAllFyCount(PulicCxtj pulicCxtj);
	//验证身份证的唯一
	TJsrk findBySfzh(String sfzh);
	//根据id查询数据
	TJsrk findById(String id);
	/**
	 * 根据id和身份证查询
	 * @param id
	 * @param sfzh
	 * @return
	 */
	TJsrk findByIdAndSfzh(String id,String sfzh);
	//添加
	public int add(TJsrk jsrk);
	//删除
	public int delete(String ids);
	//注销
	public int zhuxiao(String ids);
	//修改
	public int update(TJsrk jsrk);
	/**
	 * 转存添加
	 * @param jsrk
	 * @return
	 */
	public int addZc(TJsrk jsrk);
}
