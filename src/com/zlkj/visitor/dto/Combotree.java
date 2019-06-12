package com.zlkj.visitor.dto;

import java.util.ArrayList;
import java.util.List;
//权限码 树
public class Combotree {
	private String id;
	private String text;
	private String iconCls;
	private String state;
	private List<Combotree>children=new ArrayList<Combotree>();
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public List<Combotree> getChildren() {
		return children;
	}
	public void setChildren(List<Combotree> children) {
		this.children = children;
	}
	
}
