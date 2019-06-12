package com.zlkj.visitor.dto;

import java.util.List;

/**
 * @author zlkj
 *树参数
 */
public class MenuDto {
	private Integer menuid;
	private String menuname;
	private String icon;
	private String url;
	private List<MenuDto> menus;
	public List<MenuDto> getMenus() {
		return menus;
	}
	public void setMenus(List<MenuDto> menus) {
		this.menus = menus;
	}
	public Integer getMenuid() {
		return menuid;
	}
	public void setMenuid(Integer menuid) {
		this.menuid = menuid;
	}
	public String getMenuname() {
		return menuname;
	}
	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	public String toString() {
		return "MenuDto [menuid=" + menuid + ", menuname=" + menuname
				+ ", icon=" + icon + ", url=" + url + ", menus=" + menus + "]";
	}
	
	
}
