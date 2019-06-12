package com.zlkj.visitor.dao;

import java.util.List;

import com.zlkj.visitor.dto.PulicCxtj;
import com.zlkj.visitor.entity.TJgaddress;

public interface PpmsDao {
	//查询数据
	public List<TJgaddress> finAll(PulicCxtj pulicCxtj);
	//查询总条数
	public long findAllCount(PulicCxtj pulicCxtj);
	//添加
	public int add(TJgaddress jgaddress);
	//删除
	public int delete(String[] strs);
	//修改
	public int update(TJgaddress jgaddress);
	//查询匹配模式里的地点是否已经配置
	public TJgaddress findByDdmc(String ddmc);
	//验证匹配模式的IP是否已被征用
	public TJgaddress findByPpmsIp(String ip);
}
