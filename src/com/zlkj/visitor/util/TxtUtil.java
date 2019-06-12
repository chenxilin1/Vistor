package com.zlkj.visitor.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

//import org.junit.Test;


public class TxtUtil {
	
	public static String AddFileText(List<String> plbq){
		File file= null;
		String pathname="C:/Users/zlkj/Documents/批量标签/";
		String fileName = "批量备份标签";
		String suffix=".txt";
		
		for (int i = 1; i >0; i--) {
			file= new File(pathname+fileName+suffix);
			try {
				if (!file.exists()) {
					file.createNewFile();
				}else {
					fileName+="1";
					i++;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		BufferedWriter output=null;
        try {
			output = new BufferedWriter(new FileWriter(pathname + fileName + suffix));
			String ss = "";
			for (int i = 0; i < plbq.size(); i++) {
				ss+=plbq.get(i)+"\r\n";
						
			}
			output.write(ss);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				
				output.close();
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
		}
		
        return pathname+fileName+suffix;
	}

}
