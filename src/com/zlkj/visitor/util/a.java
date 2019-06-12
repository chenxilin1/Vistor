package com.zlkj.visitor.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class a {
public static void main(String[] args) {
	SimpleDateFormat sdf =new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
	Date date=new Date();
	System.out.println(sdf.format(date));
}
}
