package com.zlkj.visitor.dao;

import java.util.List;

import com.zlkj.visitor.dto.PulicCxtj;
import com.zlkj.visitor.entity.TAllmsg;

public interface TJdcbqglDao {
	//查询 分页
	public List<TAllmsg> findAllFy(PulicCxtj pulicCxtj);
	//查询 分页 总条数
	public long findAllFyCount(PulicCxtj pulicCxtj);
	//验证号牌号码的唯一
	TAllmsg findByHphm(String hphm);
	//根据id查询
	TAllmsg findById(String id);
	//根据id和号牌号码查询
	TAllmsg findByIdAndHphm(String id,String hphm);
	//添加
	public int add(TAllmsg allmsg);
	//删除
	public int delete(String[] sts);	
	//注销
	public int zhuxiao(String ids,String zxsj);
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
	public List<TAllmsg> outExcel(PulicCxtj pulicCxtj);
	
	
	
	
}
