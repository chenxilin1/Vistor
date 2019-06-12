package com.zlkj.visitor.web.bqgl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zlkj.visitor.service.PlsjglService;

/**
 * 批量标签管理 上传
 * 
 * @author LYW 创建时间：2017-6-26 下午5:21:30
 * 
 */
@Controller
@RequestMapping("/bqgl")
public class TPlbqglController {

	@Autowired
	private PlsjglService plsjglService;

	@RequestMapping(value = "/FindSJPLGL")
	public String FindSJPLGL(Model model) {
		return "/bqgl/sjplgl_list";
	}

	// 初始化机动车数据
	@RequestMapping(value = "cshjdcsj", produces = { "text/html;charset=UTF-8" })
	@ResponseBody
	public String cshjdcsj(@RequestParam("jdcsj") MultipartFile jdcsj, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String flag = "true,";
		if (jdcsj.isEmpty() && flag.equals("false,")) {
			flag = "false,";
		} else {
			Workbook workbook = null;
			try {
				workbook = create(jdcsj.getInputStream());
			} catch (Exception e1) {
				e1.printStackTrace();
				return flag = "lxyw,";
			}
			//判断列长度是否是规定长度
			if (29!=workbook.getSheetAt(0).getRow(0).getPhysicalNumberOfCells()) {
				return flag = "sizeErr,0,0,0";
			}
			Map<String, Integer> map = plsjglService.cshjdcsj(workbook);
			if (1010==map.get("ERR")) {
				return flag = "dateErr,0,0,0";
			}
			Integer[] sl = new Integer[3];
			for (String key : map.keySet()) {
				if ("YES".equals(key)) {
					sl[0]=map.get(key);
				}else if ("GX".equals(key)) {
					sl[1]=map.get(key);
				}else if ("NO".equals(key)) {
					sl[2]=map.get(key);
				}
			}
			flag += sl[0]+","+sl[1]+","+sl[2];
		}
		response.setContentType("text/json;charset=UTF-8");
		return flag;
	}

	// 初始化驾驶人数据
	@RequestMapping(value = "cshjsrsj", produces = { "text/html;charset=UTF-8" })
	@ResponseBody
	public String cshjsrsj(@RequestParam("jsrsj") MultipartFile jsrsj, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String flag = "true,";
		if (jsrsj.isEmpty() && flag.equals("false,")) {
			flag = "false,";
		} else {
			Workbook workbook = null;
			try {
				workbook = create(jsrsj.getInputStream());
			} catch (Exception e1) {
				e1.printStackTrace();
				return flag = "lxyw,";
			}
			if (21!=workbook.getSheetAt(0).getRow(0).getPhysicalNumberOfCells()) {
				return flag = "sizeErr,0,0,0";
			}
			Map<String, Integer> map = plsjglService.cshjsrsj(workbook);
			if (1010==map.get("ERR")) {
				return flag = "dateErr,0,0,0";
			}
			Integer[] sl = new Integer[3];
			for (String key : map.keySet()) {
				if ("YES".equals(key)) {
					sl[0]=map.get(key);
				}else if ("GX".equals(key)) {
					sl[1]=map.get(key);
				}else if ("NO".equals(key)) {
					sl[2]=map.get(key);
				}
			}
			flag += sl[0]+","+sl[1]+","+sl[2];
		}
		response.setContentType("text/json;charset=UTF-8");
		return flag;
	}

	// 初始化机动车标签库
	@RequestMapping(value = "jdcbqsc", produces = { "text/html;charset=UTF-8" })
	@ResponseBody
	public String jdcbqsc(@RequestParam("jdcbq") MultipartFile jdcbq, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String flag = "true,";
		Map<String, Integer> map = null;
		Integer cgsl = 0;
		Integer sbsl = 0;
		if (jdcbq.isEmpty()) {
			flag = "false,";
		}else {
			String pathName = uploadFile(jdcbq, request);
			String localFilePath = request.getSession().getServletContext().getRealPath("/upload");
			String finalName = localFilePath + "/" + pathName;
			File file = new File(finalName);
			// 使用bufferedReader 读取文本数据
			
			try {
				FileReader fr = new FileReader(file);
				BufferedReader br = new BufferedReader(fr);
				String line = br.readLine();
				while (line != null) {
					System.out.println(line);
					if (!line.equals("")) {
						map = plsjglService.cshjdcbqk(line);
					}
					for (String key : map.keySet()) {
						if ("YES".equals(key)) {
							++cgsl;
						}else if ("NO".equals(key)){
							++sbsl;
						}
					}
					line = br.readLine();
				}
			} catch (Exception e) {
				flag = "false,";
			}
		}
		
		flag = flag+cgsl+","+sbsl;
		response.setContentType("text/json;charset=UTF-8");
		return flag;
	}
	// 初始化驾驶人标签库
	@RequestMapping(value = "jsrbqsc", produces = { "text/html;charset=UTF-8" })
	@ResponseBody
	public String jsrbqsc(@RequestParam("jsrbq") MultipartFile jsrbq, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String flag = "true,";
		Map<String, Integer> map = null;
		Integer cgsl = 0;
		Integer sbsl = 0;
		if (jsrbq.isEmpty()) {
			flag = "false,";
		}else {
			String pathName = uploadFile(jsrbq, request);
			String localFilePath = request.getSession().getServletContext().getRealPath("/upload");
			String finalName = localFilePath + "/" + pathName;
			File file = new File(finalName);
			// 使用bufferedReader 读取文本数据
			try {
				FileReader fr = new FileReader(file);
				BufferedReader br = new BufferedReader(fr);
				String line = br.readLine();
				while (line != null) {
					System.out.println(line);
					if (!line.equals("")) {
						map = plsjglService.cshjsrbqk(line);
					}
					for (String key : map.keySet()) {
						if ("YES".equals(key)) {
							++cgsl;
						}else if ("NO".equals(key)){
							++sbsl;
						}
					}
					line = br.readLine();
				}
			} catch (Exception e) {
				flag = "false,";
			}
		}
		
		flag = flag+cgsl+","+sbsl;
		response.setContentType("text/json;charset=UTF-8");
		return flag;
	}
	// 机动车数据采集
	@RequestMapping(value = "cjjdcxx", produces = { "text/html;charset=UTF-8" })
	@ResponseBody
	public String cjjdcxx(@RequestParam("jdcxxcj") MultipartFile jdcxxcj, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String flag = "true,";
		if (jdcxxcj.isEmpty() && flag.equals("false,")) {
			flag = "false,";
		} else {
			Workbook workbook = null;
			try {
				workbook = create(jdcxxcj.getInputStream());
			} catch (Exception e1) {
				e1.printStackTrace();
				return flag = "lxyw,";
			}
			//判断列长度是否是规定长度
			System.out.println(workbook.getSheetAt(0).getRow(0).getPhysicalNumberOfCells());
			if (19!=workbook.getSheetAt(0).getRow(0).getPhysicalNumberOfCells()) {
				return flag = "sizeErr,0,0,0";
			}
			Map<String, Integer> map = plsjglService.cjjdcxx(workbook);
			if (1010==map.get("ERR")) {
				return flag = "dateErr,0,0,0";
			}
			Integer[] sl = new Integer[3];
			for (String key : map.keySet()) {
				if ("YES".equals(key)) {
					sl[0]=map.get(key);
				}else if ("GX".equals(key)) {
					sl[1]=map.get(key);
				}else if ("NO".equals(key)) {
					sl[2]=map.get(key);
				}
			}
			flag += sl[0]+","+sl[1]+","+sl[2];
		}
		response.setContentType("text/json;charset=UTF-8");
		return flag;
	}
	// 驾驶人数据采集
	@RequestMapping(value = "cjjsrxx", produces = { "text/html;charset=UTF-8" })
	@ResponseBody
	public String cjjsrxx(@RequestParam("jsrxxcj") MultipartFile jsrxxcj, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String flag = "true,";
		if (jsrxxcj.isEmpty() && flag.equals("false,")) {
			flag = "false,";
		} else {
			Workbook workbook = null;
			try {
				workbook = create(jsrxxcj.getInputStream());
			} catch (Exception e1) {
				e1.printStackTrace();
				return flag = "lxyw,";
			}
			if (13!=workbook.getSheetAt(0).getRow(0).getPhysicalNumberOfCells()) {
				return flag = "sizeErr,0,0,0";
			}
			Map<String, Integer> map = plsjglService.cjjsrxx(workbook);
			if (1010==map.get("ERR")) {
				return flag = "dateErr,0,0,0";
			}
			Integer[] sl = new Integer[3];
			for (String key : map.keySet()) {
				if ("YES".equals(key)) {
					sl[0]=map.get(key);
				}else if ("GX".equals(key)) {
					sl[1]=map.get(key);
				}else if ("NO".equals(key)) {
					sl[2]=map.get(key);
				}
			}
			flag += sl[0]+","+sl[1]+","+sl[2];
		}
		response.setContentType("text/json;charset=UTF-8");
		return flag;
	}
	// 文件上传
	public static String uploadFile(MultipartFile file, HttpServletRequest request) throws IOException {
		String localFilePath = request.getSession().getServletContext().getRealPath("/upload");
		String fileName = file.getOriginalFilename();
		File tempFile = new File(localFilePath, new Date().getTime() + String.valueOf("") + fileName);
		if (!tempFile.getParentFile().exists()) {
			tempFile.getParentFile().mkdir();
		}
		if (!tempFile.exists()) {
			tempFile.createNewFile();
		}
		file.transferTo(tempFile);
		return tempFile.getName();
	}  	
	//Excel
	public Workbook create(InputStream in) throws IOException, InvalidFormatException {
		if (!in.markSupported()) {
			in = new PushbackInputStream(in, 8);
		}
		if (POIFSFileSystem.hasPOIFSHeader(in)) {
			return new HSSFWorkbook(in);
		}
		if (POIXMLDocument.hasOOXMLHeader(in)) {
			return new XSSFWorkbook(OPCPackage.open(in));
		}
		throw new IllegalArgumentException("你的excel版本目前poi解析不了");
		
	}

}
