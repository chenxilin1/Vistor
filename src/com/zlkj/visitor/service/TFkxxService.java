package com.zlkj.visitor.service;

import java.util.List;

import com.zlkj.visitor.dto.PulicCxtj;
import com.zlkj.visitor.entity.TFkxx;

public interface TFkxxService {
	//查询 分页
	public List<TFkxx> findAllFy(PulicCxtj pulicCxtj);
	//查询 分页 总条数
	public long findAllFyCount(PulicCxtj pulicCxtj);
	//验证号牌号码的唯一
	TFkxx findByHphm(String hphm);
	//添加
	public int add(TFkxx fkxx);
	//删除
	public int delete(String ids);
	//注销
	public int zhuxiao(String ids);
	//修改
	public int update(TFkxx fkxx);
}
