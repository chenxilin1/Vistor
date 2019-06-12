package com.zlkj.visitor.entity;

import java.io.Serializable;

public class TUser implements Serializable{
	private String yhm;//用户名
	private String yhmm;//用户密码
	private String zsxm;//真实姓名
	private String sex;//性别
	private String yhyx;//用户邮箱
	private String yhsj;//用户手机
	private String yhqx;//用户权限
	private String yhid;//用户id
	private String isAdmin;//是否是管理员
	private String ktxdd;//该用户的可通行地点
	public String getYhm() {
		return yhm;
	}
	public void setYhm(String yhm) {
		this.yhm = yhm;
	}
	public String getYhmm() {
		return yhmm;
	}
	public void setYhmm(String yhmm) {
		this.yhmm = yhmm;
	}
	public String getKtxdd() {
		return ktxdd;
	}
	public void setKtxdd(String ktxdd) {
		this.ktxdd = ktxdd;
	}
	public String getZsxm() {
		return zsxm;
	}
	public void setZsxm(String zsxm) {
		this.zsxm = zsxm;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getYhyx() {
		return yhyx;
	}
	public void setYhyx(String yhyx) {
		this.yhyx = yhyx;
	}
	public String getYhsj() {
		return yhsj;
	}
	public void setYhsj(String yhsj) {
		this.yhsj = yhsj;
	}
	public String getYhqx() {
		return yhqx;
	}
	public void setYhqx(String yhqx) {
		this.yhqx = yhqx;
	}
	public String getYhid() {
		return yhid;
	}
	public void setYhid(String yhid) {
		this.yhid = yhid;
	}
	public String getIsAdmin() {
		return isAdmin;
	}
	@Override
	public String toString() {
		return "TUser [yhm=" + yhm + ", yhmm=" + yhmm + ", zsxm=" + zsxm + ", sex=" + sex + ", yhyx=" + yhyx + ", yhsj=" + yhsj + ", yhqx=" + yhqx
				+ ", yhid=" + yhid + ", isAdmin=" + isAdmin + "]";
	}
	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	
	
	
}
