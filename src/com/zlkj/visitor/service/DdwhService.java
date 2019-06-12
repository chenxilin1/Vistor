/**
 * 
 */
package com.zlkj.visitor.service;

import java.util.List;

import com.zlkj.visitor.dto.PulicCxtj;
import com.zlkj.visitor.entity.Ddmc;

/**
 * 地点维护
 * @author LYW 
 *  创建时间：2017-6-22 下午3:59:57
 *
 */
public interface DdwhService {

	//查询所有的不分页
	List<Ddmc> findAll();
	//查询 分页
	public List<Ddmc> findAllFy(PulicCxtj pulicCxtj);
	//查询 分页 总条数
	public long findAllFyCount(PulicCxtj pulicCxtj);
	//添加
	public int add(Ddmc ddmc);
	//删除
	public int delete(String ids);
	//修改
	public int update(Ddmc ddmc);
	//地点编号返回地点名称
	public String ddbh2ddmc(String ddbh);
	//地点名称返回地点编号
	public String ddmc2ddbh(String ddmc);
	
	
	
	
}
