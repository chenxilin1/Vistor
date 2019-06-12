package com.zlkj.visitor.dto;

import java.io.Serializable;
import java.util.Arrays;

public class TjtCsDto implements Serializable {
	private String kssj;// sql查询开始
	private String jssj;// sql查询结束
	private String sjxz;// 时间选择
	private String tjfs;// 统计方式
	private String sjzhou;// 时间轴
	private Integer sl;// 数量
	private Integer jksl;// 进口数量
	private Integer cksl;// 出口数量
	private Integer zlsl;// 滞留数量
	private String travelname;//旅游团名称
	private String txqk;// 通行情况
	private String bqlx;// 标签类型
	
	
	
	//-------------------------------------------待删除
	private String sj;// 统计时间
	private String hphm;// 号牌号码
	private String hp;// 号牌
	private String hm;// 号码
	private String zhi;// 号值
	private String wfxw;// 违法类型
	private String cllx;// 车辆类型
	private String hpzl;// 号牌种类
	

	private String gsd;// 归属地
	private String syxz;// 使用性质
	private String ddmc;// 地点名称
	private String[] ddms;// 地点集合
	private String djry;// 登记人

	private String txdd;// 通行地点
	private String txsj;// 通行时间
	private Integer page; // 当前页数
	private Integer rows;// 每页显示几条
	private String[] nothphm; // 存放排除的临时号牌号码 查询用
	private String[] xyr; // 嫌疑人为集合
	private String cxtj; // 车型 车种等
	private String[] cxxs; // 车型 车种等集合
	private String[] dd;// 查询地点
	private String[] lb;// 嫌疑人原因
	private String djr;// 嫌疑人原因
	private String isbd;// 是否本地
	private String isrq;// 是否人签 0：人签 1：车签

	public String getIscq() {
		return iscq;
	}

	public void setIscq(String iscq) {
		this.iscq = iscq;
	}
	public String getTravelname() {
		return travelname;
	}

	public void setTravelname(String travelname) {
		this.travelname = travelname;
	}
	public String getSjxz() {
		return sjxz;
	}

	public void setSjxz(String sjxz) {
		this.sjxz = sjxz;
	}

	public String getSjzhou() {
		return sjzhou;
	}

	public void setSjzhou(String sjzhou) {
		this.sjzhou = sjzhou;
	}

	public String getTjfs() {
		return tjfs;
	}

	public void setTjfs(String tjfs) {
		this.tjfs = tjfs;
	}

	private String iscq;// 是否人签 0：人签 1：车签

	public String getSj() {
		return sj;
	}

	public String getIsbd() {
		return isbd;
	}

	public String getTxqk() {
		return txqk;
	}

	public Integer getJksl() {
		return jksl;
	}

	public void setJksl(Integer jksl) {
		this.jksl = jksl;
	}

	public Integer getCksl() {
		return cksl;
	}

	public void setCksl(Integer cksl) {
		this.cksl = cksl;
	}

	public Integer getZlsl() {
		return zlsl;
	}

	public void setZlsl(Integer zlsl) {
		this.zlsl = zlsl;
	}

	public void setTxqk(String txqk) {
		this.txqk = txqk;
	}

	public String getBqlx() {
		return bqlx;
	}

	public void setBqlx(String bqlx) {
		this.bqlx = bqlx;
	}

	public String getIsrq() {
		return isrq;
	}

	public void setIsrq(String isrq) {
		this.isrq = isrq;
	}

	public void setIsbd(String isbd) {
		this.isbd = isbd;
	}

	public String[] getLb() {
		return lb;
	}

	public String getDjr() {
		return djr;
	}

	public void setDjr(String djr) {
		this.djr = djr;
	}

	public void setLb(String[] lb) {
		this.lb = lb;
	}

	public String getGsd() {
		return gsd;
	}

	public void setGsd(String gsd) {
		this.gsd = gsd;
	}

	public String getSyxz() {
		return syxz;
	}

	public void setSyxz(String syxz) {
		this.syxz = syxz;
	}

	public String getCxtj() {
		return cxtj;
	}

	public String[] getCxxs() {
		return cxxs;
	}

	public String getCllx() {
		return cllx;
	}

	public String getHpzl() {
		return hpzl;
	}

	public void setHpzl(String hpzl) {
		this.hpzl = hpzl;
	}

	public void setCllx(String cllx) {
		this.cllx = cllx;
	}

	public void setCxxs(String[] cxxs) {
		this.cxxs = cxxs;
	}

	public void setCxtj(String cxtj) {
		this.cxtj = cxtj;
	}

	public String[] getXyr() {
		return xyr;
	}

	public void setXyr(String[] xyr) {
		this.xyr = xyr;
	}

	public String getHm() {
		return hm;
	}

	public String[] getDdms() {
		return ddms;
	}

	public void setDdms(String[] ddms) {
		this.ddms = ddms;
	}

	public void setHm(String hm) {
		this.hm = hm;
	}

	public String getZhi() {
		return zhi;
	}

	public void setZhi(String zhi) {
		this.zhi = zhi;
	}

	@Override
	public String toString() {
		return "TjtCsDto [kssj=" + kssj + ", jssj=" + jssj + ", sjxz=" + sjxz + ", tjfs=" + tjfs + ", sl=" + sl + ", sj=" + sj + ", hphm=" + hphm
				+ ", hp=" + hp + ", hm=" + hm + ", zhi=" + zhi + ", wfxw=" + wfxw + ", cllx=" + cllx + ", hpzl=" + hpzl + ", gsd=" + gsd + ", syxz="
				+ syxz + ", ddmc=" + ddmc + ", ddms=" + Arrays.toString(ddms) + ", djry=" + djry + ", txdd=" + txdd + ", txsj=" + txsj + ", page="
				+ page + ", rows=" + rows + ", nothphm=" + Arrays.toString(nothphm) + ", xyr=" + Arrays.toString(xyr) + ", cxtj=" + cxtj + ", cxxs="
				+ Arrays.toString(cxxs) + ", dd=" + Arrays.toString(dd) + ", lb=" + Arrays.toString(lb) + ", djr=" + djr + ", isbd=" + isbd
				+ ", isrq=" + isrq + ", iscq=" + iscq + "]";
	}

	public TjtCsDto() {
		super();
	}

	public void setSj(String sj) {
		this.sj = sj;
	}

	public String getHphm() {
		return hphm;
	}

	public void setHphm(String hphm) {
		this.hphm = hphm;
	}

	public String getHp() {
		return hp;
	}

	public void setHp(String hp) {
		this.hp = hp;
	}

	public String getWfxw() {
		return wfxw;
	}

	public void setWfxw(String wfxw) {
		this.wfxw = wfxw;
	}

	public String getDdmc() {
		return ddmc;
	}

	public void setDdmc(String ddmc) {
		this.ddmc = ddmc;
	}

	public String getDjry() {
		return djry;
	}

	public void setDjry(String djry) {
		this.djry = djry;
	}

	public Integer getSl() {
		return sl;
	}

	public void setSl(Integer sl) {
		this.sl = sl;
	}

	public String getTxdd() {
		return txdd;
	}

	public void setTxdd(String txdd) {
		this.txdd = txdd;
	}

	public String getTxsj() {
		return txsj;
	}

	public void setTxsj(String txsj) {
		this.txsj = txsj;
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

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public String[] getNothphm() {
		return nothphm;
	}

	public void setNothphm(String[] nothphm) {
		this.nothphm = nothphm;
	}

	public String[] getDd() {
		return dd;
	}

	public void setDd(String[] dd) {
		this.dd = dd;
	}

}
