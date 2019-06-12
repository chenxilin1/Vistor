package com.zlkj.visitor.service;

import java.util.List;

import com.zlkj.visitor.dto.TjtCsDto;
import com.zlkj.visitor.entity.TCltxkb;


public interface CljlTjService {
	//初始化统计图
	List<TjtCsDto> finTjt (TjtCsDto tjtCsDto);
	
	//点击统计图
	List<TjtCsDto> djtjt(TjtCsDto tjtCsDto);
	//点击饼图数据
	List<TCltxkb> djbtsj(TjtCsDto tjtCsDto);
	//单击饼图总数
	long djbtsjCount(TjtCsDto tjtCsDto);
	/**
	 * 获取该地点的数据
	 * @param tjtCsDto
	 * @return
	 */
	long findByDd(TjtCsDto tjtCsDto);
	
}
