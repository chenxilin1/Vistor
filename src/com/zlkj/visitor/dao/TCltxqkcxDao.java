package com.zlkj.visitor.dao;

import java.util.List;

import com.zlkj.visitor.dto.PulicCxtj;
import com.zlkj.visitor.entity.TCltxkb;

public interface TCltxqkcxDao {
	//查询 分页
	public List<TCltxkb> findAllFy(PulicCxtj pulicCxtj);
	//查询 分页 总条数
	public long findAllFyCount(PulicCxtj pulicCxtj);
	//根据id查询
	public TCltxkb findById(String id);
}
