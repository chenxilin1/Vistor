package com.zlkj.visitor.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFPictureData;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

public class TestRead {
	
	@Test
	public void main() {
		TestRead tr = new TestRead();
		// tr.readFile();
		tr.writeFile();
	}
	public void writeFile() {
		XSSFWorkbook wb = null;
		InputStream ifs = null;
		OutputStream ofs = null;
		String filePath = "D:/机动车.xls";
		try {
			// 设置要读取的文件路径
			ifs = new FileInputStream(filePath);
			// HSSFWorkbook相当于一个excel文件，HSSFWorkbook是解析excel2007之前的版本（xls）
			// 之后版本使用XSSFWorkbook（xlsx）
			wb = new XSSFWorkbook(ifs);

			// 获得sheet工作簿HSSFSheet
			XSSFSheet sheet = wb.getSheetAt(0);
			// 获得行HSSFRow
			XSSFRow row = sheet.getRow(7);
			// 获得行中的列，即单元格HSSFCell
			XSSFCell cell = row.getCell(0);
			// 获得单元格中的值
			String msg = cell.getStringCellValue();
			System.out.println(msg);
			
			List<XSSFPictureData> pd = wb.getAllPictures();
			System.out.println(pd.size());
			
			row = sheet.getRow(1);
			if (row == null) {
				row = sheet.createRow(1);
			}

			cell = row.getCell(1);
			if (cell == null) {
				cell = row.createCell(1);
			}
			cell.setCellValue("This is test");
			ofs = new FileOutputStream(filePath);
			wb.write(ofs);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



}