package com.zlkj.visitor.dto;

import java.io.Serializable;
import java.util.Arrays;

public class PulicCxtj implements Serializable{
	private Integer page;	//当前页数
	private Integer rows;//每页显示几条
	private String status;//条件状态
	private String bh;//条件编号
	private String mz;//条件民族
	private String[] qxjb;//权限级别
	private String hcyy;//核查原因
	private String note;//核查原因
	
	
	private String xjfx;//进出口
	private String txzt;//通行状态
	private String hp;//号牌
	private String hm;//号码
	private String hphm;//号牌号码
	private String zhi;//值
	private String[] hpzl;//号牌种类
	private String kssj;//开始时间
	private String jssj;//结束时间
	private String dqsj;//当前时间
	private String[] bqzt;//标签状态
	private String sfzh;//身份证
	private String userName;//拜访人姓名
	private String email;//受访人姓名
	
	private String xjsbbh;//相机状态
	private String ckip;//进出口
	private String ppms[];//匹配模式
	private String ddmc;//地点名称
	private String ddmcsz[];//地点名称数组条件
	private String bqffqk;//标签发放情况
	private String rfidms;//是否rfidms优先
	private String syr;//车主姓名
	private String lxdh;//车主类型电话
	private String ld;//车主领导
	private String ssdw;//所属单位
	
	
	
	
	public String getBqffqk() {
		return bqffqk;
	}
	public String getSsdw() {
		return ssdw;
	}
	public void setSsdw(String ssdw) {
		this.ssdw = ssdw;
	}
	public String getSyr() {
		return syr;
	}
	public void setSyr(String syr) {
		this.syr = syr;
	}
	public String getLxdh() {
		return lxdh;
	}
	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}
	@Override
	public String toString() {
		return "PulicCxtj [page=" + page + ", rows=" + rows + ", status=" + status + ", bh=" + bh + ", mz=" + mz + ", qxjb=" + Arrays.toString(qxjb)
				+ ", hcyy=" + hcyy + ", note=" + note + ", xjfx=" + xjfx + ", txzt=" + txzt + ", hp=" + hp + ", hm=" + hm + ", hphm=" + hphm
				+ ", zhi=" + zhi + ", hpzl=" + Arrays.toString(hpzl) + ", kssj=" + kssj + ", jssj=" + jssj + ", dqsj=" + dqsj + ", bqzt="
				+ Arrays.toString(bqzt) + ", sfzh=" + sfzh + ", userName=" + userName + ", email=" + email + ", xjsbbh=" + xjsbbh + ", ckip=" + ckip
				+ ", ppms=" + Arrays.toString(ppms) + ", ddmc=" + ddmc + ", ddmcsz=" + Arrays.toString(ddmcsz) + ", bqffqk=" + bqffqk + ", rfidms="
				+ rfidms + ", syr=" + syr + ", lxdh=" + lxdh + ", ld=" + ld + ", ssdw=" + ssdw + "]";
	}
	public String getLd() {
		return ld;
	}
	public void setLd(String ld) {
		this.ld = ld;
	}
	public void setBqffqk(String bqffqk) {
		this.bqffqk = bqffqk;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public String[] getDdmcsz() {
		return ddmcsz;
	}
	public void setDdmcsz(String[] ddmcsz) {
		this.ddmcsz = ddmcsz;
	}
	public String getDqsj() {
		return dqsj;
	}
	public void setDqsj(String dqsj) {
		this.dqsj = dqsj;
	}
	public String getRfidms() {
		return rfidms;
	}
	public void setRfidms(String rfidms) {
		this.rfidms = rfidms;
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public String getXjsbbh() {
		return xjsbbh;
	}
	public void setXjsbbh(String xjsbbh) {
		this.xjsbbh = xjsbbh;
	}
	public String getCkip() {
		return ckip;
	}
	public void setCkip(String ckip) {
		this.ckip = ckip;
	}
	public String[] getPpms() {
		return ppms;
	}
	public void setPpms(String[] ppms) {
		this.ppms = ppms;
	}
	public String getDdmc() {
		return ddmc;
	}
	public void setDdmc(String ddmc) {
		this.ddmc = ddmc;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBh() {
		return bh;
	}
	public void setBh(String bh) {
		this.bh = bh;
	}
	public String[] getBqzt() {
		return bqzt;
	}
	public void setBqzt(String[] bqzt) {
		this.bqzt = bqzt;
	}
	
	public String getSfzh() {
		return sfzh;
	}
	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}
	public String getMz() {
		return mz;
	}
	public String getXjfx() {
		return xjfx;
	}
	public void setXjfx(String xjfx) {
		this.xjfx = xjfx;
	}
	public String getTxzt() {
		return txzt;
	}
	public void setTxzt(String txzt) {
		this.txzt = txzt;
	}
	public String getHp() {
		return hp;
	}
	public void setHp(String hp) {
		this.hp = hp;
	}
	public String getHm() {
		return hm;
	}
	public void setHm(String hm) {
		this.hm = hm;
	}
	public String getHphm() {
		return hphm;
	}
	public void setHphm(String hphm) {
		this.hphm = hphm;
	}
	public String getZhi() {
		return zhi;
	}
	public void setZhi(String zhi) {
		this.zhi = zhi;
	}
	
	public String[] getHpzl() {
		return hpzl;
	}
	public void setHpzl(String[] hpzl) {
		this.hpzl = hpzl;
	}
	public String getKssj() {
		return kssj;
	}
	public void setKssj(String kssj) {
		this.kssj = kssj;
	}
	public String getJssj() {
		return jssj;
	}
	public void setJssj(String jssj) {
		this.jssj = jssj;
	}
	public void setMz(String mz) {
		this.mz = mz;
	}
	public String[] getQxjb() {
		return qxjb;
	}
	public void setQxjb(String[] qxjb) {
		this.qxjb = qxjb;
	}
	public String getHcyy() {
		return hcyy;
	}
	public void setHcyy(String hcyy) {
		this.hcyy = hcyy;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
