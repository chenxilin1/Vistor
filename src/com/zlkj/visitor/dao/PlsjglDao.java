package com.zlkj.visitor.dao;

import java.util.List;

import com.zlkj.visitor.entity.TAllmsg;
import com.zlkj.visitor.entity.TJsrk;
import com.zlkj.visitor.entity.TPlbqk;

public interface PlsjglDao {
	
	//初始化机动车数据
	int cshjdcsj(TAllmsg allmsg);
	//更新
	int updetejdcsj(TAllmsg allmsg);
	//查询车牌是否存在
	TAllmsg findByhphm(String hphm);
	//判断id是否存在
	TAllmsg findByJdcId(Integer id);

	//初始驾驶人数据
	int cshjsrsj(TJsrk jsrk);
	//更新
	int updetejsrsj(TJsrk jsrk);
	//查询身份证号码是否存在
	TJsrk findBysfzh(String sfzh);
	//判断id是否存在
	TJsrk findByJsrId(Integer id);
	
	//添加机动车标签库
	int cshjdcbqk(TPlbqk plbqk);
	//查询机动车标签是否存在
	TPlbqk finByjdcbqid(String bqid);
	
	//添加驾驶人库
	int cshjsrbqk(TPlbqk plbqk);
	//查询驾驶人标签是否存在
	TPlbqk finByjsrbqid(String bqid);
	
	
	//备份机动车数据
	List<TAllmsg> bfjdcsj();
	//备份机动车数据总数
	long bfjdcsjCount();
	//备份驾驶人数据
	List<TJsrk> bfjsrsj();
	//备份驾驶人数据总数
	long bfjsrsjCount();
	
	//备份机动车标签
	List<TPlbqk> bfjdcbq();
	//备份驾驶人标签
	List<TPlbqk> bfjsrbq();
	
	//根据标签id 查询 机动车数据是否存在
	List<TAllmsg> findByJdcSj(String bqid);
	//根据标签id 查询 驾驶人数据是否存在
	List<TJsrk> findByJsrSj(String bqid);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
