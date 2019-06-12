package com.zlkj.visitor.service;

import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Workbook;

import com.zlkj.visitor.entity.TAllmsg;
import com.zlkj.visitor.entity.TJsrk;

public interface PlsjglService {

	//初始化机动车数据
	Map<String, Integer> cshjdcsj(Workbook workbook);
	
	//初始驾驶人数据
	Map<String, Integer> cshjsrsj(Workbook workbook);
	
	//初始化机动车标签库
	Map<String, Integer> cshjdcbqk(String sj);
	
	//初始化机动车标签库
	Map<String, Integer> cshjsrbqk(String sj);
	
	//采集机动车数据
	Map<String, Integer> cjjdcxx(Workbook workbook);
	
	//采集驾驶人数据
	Map<String, Integer> cjjsrxx(Workbook workbook);
		
	/**
	 * 备份机动车数据
	 * @return
	 */
	//List<Map<String,Object>> bfjdcsj(String[] keys);
	List<TAllmsg> bfjdcsj();

	//备份机动车数据总数
	long bfjdcsjCount();
	
	
	/**
	 * 备份机动车采集信息模板
	 * @return
	 */
	List<Map<String,Object>> bfjdccjxxmb(String[] keys);
	/**
	 * 备份驾驶人数据
	 * @return
	 */
	//List<Map<String,Object>> bfjsrsj();
	List<TJsrk> bfjsrsj();
	//备份驾驶人数据总数
	long bfjsrsjCount();
	
	/**
	 * 备份驾驶人采集信息模板
	 * @return
	 */
	List<Map<String,Object>> bfjsrcjxxmb();
	/**
	 * 备份机动车标签
	 * @return
	 */
	List<String> bfjdcbq();
	/**
	 * 备份驾驶人标签
	 * @return
	 */
	List<String> bfjsrbq();
	
	
	
	
	
	
	
	
	
	
	
	
}
