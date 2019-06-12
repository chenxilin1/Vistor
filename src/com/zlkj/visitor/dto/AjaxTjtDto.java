package com.zlkj.visitor.dto;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class AjaxTjtDto implements Serializable{

	
	private String msg;				//状态
	private List<Object> cp;		//车牌栏
	private Object Object;		//饼图数据
	private String titlew;		//统计图标题名
	private String legendw;		//统计图组件名
	private List<String> legends;		//统计图组件名
	private List<String> beiyong;	//备用集合
	private List<String> shuliang;	//数量集合
	private String nhphm;	//存放排除的临时号牌号码
	private String[] nothphm;	//存放排除的临时号牌号码 查询用
	private String djry;	//存放临时登记人 查询用
	private String xyry;	//存放临时嫌疑人 查询用
	private String isrc;	//判断是人是车 0:人 1:车
	private List<Map<String,Object>> map;	//判断是人是车 0:人 1:车
	
	public String getIsrc() {
		return isrc;
	}
	public void setIsrc(String isrc) {
		this.isrc = isrc;
	}
	public AjaxTjtDto() {
		super();
	}
	public List<Map<String, Object>> getMap() {
		return map;
	}
	public void setMap(List<Map<String, Object>> map) {
		this.map = map;
	}
	public String getXyry() {
		return xyry;
	}
	public void setXyry(String xyry) {
		this.xyry = xyry;
	}
	public String getDjry() {
		return djry;
	}
	public void setDjry(String djry) {
		this.djry = djry;
	}
	public String getNhphm() {
		return nhphm;
	}
	public String[] getNothphm() {
		return nothphm;
	}
	public void setNothphm(String[] nothphm) {
		this.nothphm = nothphm;
	}
	public void setNhphm(String nhphm) {
		this.nhphm = nhphm;
	}
	public List<String> getShuliang() {
		return shuliang;
	}
	public List<String> getLegends() {
		return legends;
	}

	public void setLegends(List<String> legends) {
		this.legends = legends;
	}
	public AjaxTjtDto(String msg, List<java.lang.Object> cp,
			java.lang.Object object, String titlew, String legendw,
			List<String> beiyong, List<String> shuliang) {
		super();
		this.msg = msg;
		this.cp = cp;
		Object = object;
		this.titlew = titlew;
		this.legendw = legendw;
		this.beiyong = beiyong;
		this.shuliang = shuliang;
	}

	public void setShuliang(List<String> shuliang) {
		this.shuliang = shuliang;
	}

	public AjaxTjtDto(String msg, List<java.lang.Object> cp,
			java.lang.Object object, String titlew, String legendw,
			List<String> beiyong) {
		super();
		this.msg = msg;
		this.cp = cp;
		Object = object;
		this.titlew = titlew;
		this.legendw = legendw;
		this.beiyong = beiyong;
	}

	public String getTitlew() {
		return titlew;
	}

	public void setTitlew(String titlew) {
		this.titlew = titlew;
	}

	public String getLegendw() {
		return legendw;
	}

	public void setLegendw(String legendw) {
		this.legendw = legendw;
	}

	public List<String> getBeiyong() {
		return beiyong;
	}

	public void setBeiyong(List<String> beiyong) {
		this.beiyong = beiyong;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<Object> getCp() {
		return cp;
	}

	public void setCp(List<Object> cp) {
		this.cp = cp;
	}

	public Object getObject() {
		return Object;
	}

	public void setObject(Object object) {
		Object = object;
	}

	@Override
	public String toString() {
		return "AjaxTjtDto [msg=" + msg + ", cp=" + cp + ", Object=" + Object
				+ ", titlew=" + titlew + ", legendw=" + legendw + ", legends="
				+ legends + ", beiyong=" + beiyong + ", shuliang=" + shuliang
				+ ", nhphm=" + nhphm + ", nothphm=" + Arrays.toString(nothphm)
				+ ", djry=" + djry + "]";
	}

	public AjaxTjtDto(String msg, List<java.lang.Object> cp,
			java.lang.Object object) {
		super();
		this.msg = msg;
		this.cp = cp;
		Object = object;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
