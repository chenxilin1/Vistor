package com.zlkj.visitor.entity;

/**
 * @author zlkj
 *
 */
public class Tmenus {
private String url;
private String image;
private String pid;
private String text;
private String tid;
public String getUrl() {
	return url;
}
public void setUrl(String url) {
	this.url = url;
}
public String getImage() {
	return image;
}
public void setImage(String image) {
	this.image = image;
}
public String getPid() {
	return pid;
}
public void setPid(String pid) {
	this.pid = pid;
}
public String getTid() {
	return tid;
}
public void setTid(String tid) {
	this.tid = tid;
}
public String getText() {
	return text;
}
public void setText(String text) {
	this.text = text;
}
@Override
public String toString() {
	return "Tmenus [url=" + url + ", image=" + image + ", pid=" + pid
			+ ", text=" + text + ", tid=" + tid + "]";
}

}
