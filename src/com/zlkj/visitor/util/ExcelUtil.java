package com.zlkj.visitor.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;

//导出Excel文档工具类
public class ExcelUtil {
	//poi方式导出Excel
	public static Workbook createWorkBook(List<Map<String, Object>> list, String[] keys, String columnNames[],String whichXls) {
		// 创建excel工作薄
		Workbook wb = new XSSFWorkbook();	
		// 创建第一个 sheet（页），并命名
		//Sheet sheet = wb.createSheet(list.get(0).get("sheetName").toString());
		Sheet sheet = wb.createSheet("sheet1");
		// 手动设置列宽。第一个参数表示要为第几列设；，第二个参数表示列的宽度，n为列高的像素数。
		for (int i = 0; i < keys.length; i++) {
			sheet.setColumnWidth((short) i, (short) (55.7 * 130));
		}
		// 创建 第一行
		Row row = sheet.createRow((short) 0);
		CellStyle cs = wb.createCellStyle();
		CellStyle cs2 = wb.createCellStyle();
		// 创建两种字体
		Font f = wb.createFont();
		Font f2 = wb.createFont();
		// 创建第一种字体样式（用于列名）
		f.setFontHeightInPoints((short) 10);
		f.setColor(IndexedColors.BLACK.getIndex());
		f.setBoldweight(Font.BOLDWEIGHT_BOLD);
		// 创建第二中字体样式（用于值）
		f2.setFontHeightInPoints((short) 10);
		f2.setColor(IndexedColors.BLACK.getIndex());
		// 设置第一种单元格的样式（用于列名）
		cs.setFont(f);
		cs.setBorderLeft(CellStyle.BORDER_THIN);
		cs.setBorderRight(CellStyle.BORDER_THIN);
		cs.setBorderTop(CellStyle.BORDER_THIN);
		cs.setBorderBottom(CellStyle.BORDER_THIN);
		cs.setAlignment(CellStyle.ALIGN_CENTER);
		// 设置第二种单元格的样式（用于值）
		cs2.setFont(f2);
		cs2.setBorderLeft(CellStyle.BORDER_THIN);
		cs2.setBorderRight(CellStyle.BORDER_THIN);
		cs2.setBorderTop(CellStyle.BORDER_THIN);
		cs2.setBorderBottom(CellStyle.BORDER_THIN);
		cs2.setAlignment(CellStyle.ALIGN_CENTER);
		// 设置列名
		for (short i = 0; i < list.size(); i++) {
			// Row 行,Cell 方格 , Row 和 Cell 都是从0开始计数的
			// 创建一行，在页sheet上
			if (i == 0) {
				Row row2 = sheet.createRow(i);
				long cds=columnNames.length;
				if (cds!=0) {
					for (int j = 0; j < cds; j++) {
						Cell cell = row2.createCell(j);
						cell.setCellValue(columnNames[j].toString());
						cell.setCellStyle(cs);
					}
				}
			} else {
				Row row1 = sheet.createRow(i);
				// 在row行上创建一个方格
				for (short j = 0; j < keys.length; j++) {
					Cell cell = row1.createCell(j);
					if ("机动车数据备份".equals(whichXls)) {
						if (j != 19 || j!=22) {
							cell.setCellValue((list.get(i).get(keys[j]) == null ? "" : list.get(i).get(keys[j])).toString());
						} else {
							cell.setCellValue((Integer) (list.get(i).get(keys[j]) == null ? 0 : list.get(i).get(keys[j])));
						}
					}else if ("驾驶人数据备份".equals(whichXls)) {
						if (j != 8 || j!=15) {
							cell.setCellValue((list.get(i).get(keys[j]) == null ? "" : list.get(i).get(keys[j])).toString());
						} else {
							cell.setCellValue((Integer) (list.get(i).get(keys[j]) == null ? 0 : list.get(i).get(keys[j])));
						}
					}
					cell.setCellStyle(cs2);
				}
			}
			//System.gc();
		}
		return wb;
	}
	//formwork模板方式导出Excel
	public void ExcelFormwork(HttpServletResponse response,HttpServletRequest request,String excelName,Map<String, Object> map,String formworkName) {
		//模板的文件夹路径
		String ziplj = request.getSession().getServletContext().getRealPath("/formwork");
		String mbFilePath = ziplj;
		try {
			Configuration configuration = new Configuration();
			configuration.setDefaultEncoding("utf-8");
			configuration.setDirectoryForTemplateLoading(new File(mbFilePath));// 指定ftl所在目录,根据自己的改
			response.reset(); //此句为防止导出的表格多出其他东西 比如回车等
			response.setHeader("Content-Type", "application/x-xls");
			response.setHeader("Content-Disposition", "attachment;filename=" + new String((excelName + ".xls").getBytes(), "iso-8859-1"));
			response.setCharacterEncoding("utf-8");// 此句非常关键,不然excel文档全是乱码
			PrintWriter out = response.getWriter();
			freemarker.template.Template t = configuration.getTemplate(formworkName+".ftl", "utf-8");// 以utf-8的编码读取ftl文件
			t.process(map, out);
			out.flush();
			out.close();
		} catch (TemplateException e) {
			System.out.println("获取excel模板失败");
		} catch (IOException e) {
			System.out.println("IO错误");
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
