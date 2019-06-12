package com.zlkj.visitor.entity;

import java.io.Serializable;
/**
 * 此实体类为匹配模式 原来的所谓-设备管理
 * @author LYW 
 *  创建时间：2017-6-22 上午9:25:29
 *
 */
public class TJgaddress implements Serializable{
	private Integer id;
	private String jgmc;
	private String jkip;
	private String xjsbbh;
	private String xjfx;
	private String ppms;
	private String ckip;
	private String dk;
	private String dzip;//道闸IP
	private String dkqip;//读卡器IP
	private String ppmsgx;//匹配模式关系
	private String rfidms;//RFID优先 0不是 1是'
	private String ckclfs;//出口处理方式 1 处理数据发命令 2 处理数据不发命令  3 走匹配模式'
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getJgmc() {
		return jgmc;
	}
	public void setJgmc(String jgmc) {
		this.jgmc = jgmc;
	}
	public String getJkip() {
		return jkip;
	}
	public String getPpmsgx() {
		return ppmsgx;
	}
	public void setPpmsgx(String ppmsgx) {
		this.ppmsgx = ppmsgx;
	}
	public String getDkqip() {
		return dkqip;
	}
	public void setDkqip(String dkqip) {
		this.dkqip = dkqip;
	}
	public String getRfidms() {
		return rfidms;
	}
	public void setRfidms(String rfidms) {
		this.rfidms = rfidms;
	}
	public String getCkclfs() {
		return ckclfs;
	}
	public void setCkclfs(String ckclfs) {
		this.ckclfs = ckclfs;
	}
	public void setJkip(String jkip) {
		this.jkip = jkip;
	}
	public String getXjsbbh() {
		return xjsbbh;
	}
	public void setXjsbbh(String xjsbbh) {
		this.xjsbbh = xjsbbh;
	}
	public String getXjfx() {
		return xjfx;
	}
	public void setXjfx(String xjfx) {
		this.xjfx = xjfx;
	}
	public String getPpms() {
		return ppms;
	}
	public void setPpms(String ppms) {
		this.ppms = ppms;
	}
	public String getCkip() {
		return ckip;
	}
	public void setCkip(String ckip) {
		this.ckip = ckip;
	}
	public String getDk() {
		return dk;
	}
	public void setDk(String dk) {
		this.dk = dk;
	}
	public String getDzip() {
		return dzip;
	}
	public void setDzip(String dzip) {
		this.dzip = dzip;
	}
	
	
}
