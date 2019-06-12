package com.zlkj.visitor.dao;

import java.util.List;

import com.zlkj.visitor.dto.TjtCsDto;
import com.zlkj.visitor.entity.TCltxkb;

public interface CljiTjDao {
	List<TjtCsDto> finTjt(TjtCsDto tjtCsDto);
	
	//点击统计图
	List<TjtCsDto> djtjt(TjtCsDto tjtCsDto);
	//点击饼图数据
	List<TCltxkb> djbtsj(TjtCsDto tjtCsDto);
	//单击饼图总数
	long djbtsjCount(TjtCsDto tjtCsDto);
	//获取该地点的数据
	long findByDd(TjtCsDto tjtCsDto);
}
