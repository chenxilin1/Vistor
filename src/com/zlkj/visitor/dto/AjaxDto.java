package com.zlkj.visitor.dto;

import java.io.Serializable;
public class AjaxDto implements Serializable {
private String msg;
private Object obj;
private boolean flag;

private String sy;//返回索引
public String getSy() {
	return sy;
}
public void setSy(String sy) {
	this.sy = sy;
}
public String getMsg() {
	return msg;
}
public void setMsg(String msg) {
	this.msg = msg;
}
public Object getObj() {
	return obj;
}
public void setObj(Object obj) {
	this.obj = obj;
}
public boolean isFlag() {
	return flag;
}
public void setFlag(boolean flag) {
	this.flag = flag;
}
public AjaxDto(boolean flag, String msg) {
	super();
	this.flag = flag;
	this.msg = msg;
}
public AjaxDto(boolean flag, Object obj) {
	super();
	this.flag = flag;
	this.obj = obj;
}
public AjaxDto() {
	super();
}

}
