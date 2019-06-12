package com.zlkj.visitor.service;

import java.util.List;
import java.util.Map;

import com.zlkj.visitor.dto.PulicCxtj;
import com.zlkj.visitor.entity.TAllmsg;
import com.zlkj.visitor.entity.TJgaddress;

public interface TJdcbqglService {
	//查询 分页
	public List<TAllmsg> findAllFy(PulicCxtj pulicCxtj);
	//查询 分页 总条数
	public long findAllFyCount(PulicCxtj pulicCxtj);
	//验证号牌号码的唯一
	TAllmsg findByHphm(String hphm);
	//根据id查询
	TAllmsg findById(String id);
	/**
	 * 根据id和号牌号码查询
	 * @param id
	 * @param hphm
	 * @return
	 */
	TAllmsg findByIdAndHphm(String id,String hphm);
	//添加
	public int add(TAllmsg allmsg);
	/**
	 * 删除
	 * @param ids
	 * @return
	 */
	public int delete(String ids);
	//注销
	public int zhuxiao(String ids);
	//修改
	public int update(TAllmsg allmsg);
	/**
	 * 注销转存
	 * @param allmsg
	 * @return
	 */
	public int addZx(TAllmsg allmsg);
	/**
	 * 导出数据到 Excel
	 * @param pulicCxtj
	 * @return
	 */
	public List<Map<String, Object>> outExcel(PulicCxtj pulicCxtj);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
