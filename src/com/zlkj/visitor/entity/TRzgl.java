package com.zlkj.visitor.entity;

import java.io.Serializable;

public class TRzgl implements Serializable{

    private String rzid;//id
    private String yhm;//用户名
    private String czcd;//操作菜单
    private String cznr;//操作内容
	private String czsj;//操作时间
	private String czip;//操作ip
	private String czbz;//操作备注
	
	public String getYhm() {
		return yhm;
	}
	public void setYhm(String yhm) {
		this.yhm = yhm;
	}
	public String getCznr() {
		return cznr;
	}
	public void setCznr(String cznr) {
		this.cznr = cznr;
	}
	@Override
	public String toString() {
		return "TRzgl [yhm=" + yhm + ", czcd=" + czcd + ", cznr=" + cznr + ", czsj=" + czsj + ", czip=" + czip + ", czbz=" + czbz + "]";
	}
	public String getCzsj() {
		return czsj;
	}
	public void setCzsj(String czsj) {
		this.czsj = czsj;
	}
	public String getCzip() {
		return czip;
	}
	public void setCzip(String czip) {
		this.czip = czip;
	}
	public String getCzbz() {
		return czbz;
	}
	public void setCzbz(String czbz) {
		this.czbz = czbz;
	}
	public String getCzcd() {
		return czcd;
	}
	public void setCzcd(String czcd) {
		this.czcd = czcd;
	}
	public String getRzid() {
		return rzid;
	}
	public void setRzid(String rzid) {
		this.rzid = rzid;
	}
	
	
	
	
	
	
}
