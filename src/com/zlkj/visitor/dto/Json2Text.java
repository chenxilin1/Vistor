package com.zlkj.visitor.dto;

public class Json2Text {

	private String text;
	private String value;
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "Json2Text [text=" + text + ", value=" + value + "]";
	}
	
}
