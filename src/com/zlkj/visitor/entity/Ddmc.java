package com.zlkj.visitor.entity;

import java.io.Serializable;

public class Ddmc implements Serializable{
	private Integer id;
	private String address;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "Ddmc [id=" + id + ", address=" + address + "]";
	}
	
	
}
