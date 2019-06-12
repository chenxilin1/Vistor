package com.zlkj.visitor.dto;

import java.io.Serializable;

public class UserAndPwd implements Serializable{
	private String yhm;
	private String yhmm;
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
	
}
