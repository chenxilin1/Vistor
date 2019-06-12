package com.zlkj.visitor.web.bqgl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zlkj.visitor.entity.TAllmsg;
import com.zlkj.visitor.entity.TJsrk;
import com.zlkj.visitor.service.PlsjglService;
import com.zlkj.visitor.util.ExcelUtil;
import com.zlkj.visitor.util.TxtUtil;

/**
 * 从数据库读取文件写入Excel
 * 
 * @author LYW 创建时间：2017-7-10 下午5:27:30
 * 
 */
@Controller
@RequestMapping("/bqgl")
public class TPlbqbfController {
	
	@Autowired
	private PlsjglService plsjglService;

	//机动车数据备份
	@RequestMapping(value = "/bfjdcsj", produces = { "text/html;charset=UTF-8" })
	@ResponseBody
	public String bfjdcsj(Model model, HttpServletRequest request, HttpServletResponse response) {
		List<TAllmsg> list=plsjglService.bfjdcsj();
		Map<String,Object> map=new HashMap<String,Object>();
		String excelName="机动车数据备份";
		String formworkName="jdcsjbfFormwork";
		map.put("jdcsj", list);
		new ExcelUtil().ExcelFormwork(response, request, excelName, map, formworkName);
		return null;
	}
	//备份机动车总数
	@RequestMapping(value = "/bfjdcsjCount", produces = { "text/html;charset=UTF-8" })
	@ResponseBody
	public String bfjdcsjCount(Model model, HttpServletRequest request, HttpServletResponse response) {
		String flag = "true,";
		long count=plsjglService.bfjdcsjCount();
		flag = flag+count;
		
		return flag;
	}
	//驾驶人数据备份
	@RequestMapping(value = "/bfjsrsj", produces = { "text/html;charset=UTF-8" })
	@ResponseBody
	public String bfjsrsj(HttpServletResponse response,HttpServletRequest request) {
		//List<Map<String, Object>> list = plsjglService.bfjsrsj();
		List<TJsrk> list=plsjglService.bfjsrsj();
		Map<String,Object> map=new HashMap<String,Object>();
		String excelName="驾驶人数据备份";
		String formworkName="jsrsjbfFormwork";
		map.put("jsrsj", list);
		//new ExcelUtil().ExcelFormwork(response, request, excelName, map, formworkName);
		new ExcelUtil().ExcelFormwork(response, request, excelName, map, formworkName);
		return null;
	}
	//备份驾驶人数据总数
	@RequestMapping(value = "/bfjsrsjCount", produces = { "text/html;charset=UTF-8" })
	@ResponseBody
	public String bfjsrsjCount(Model model, HttpServletRequest request, HttpServletResponse response) {
		String flag = "true,";
		long count=plsjglService.bfjsrsjCount();
		flag = flag+count;
		
		return flag;
	}
	//机动车标签备份
	@RequestMapping(value = "/bfjdcbq", produces = { "text/html;charset=UTF-8" })
	@ResponseBody
	public void bfjdcbq(HttpServletResponse response) throws IOException {
		//public static void exportFile(HttpServletResponse response, String csvFilePath, String fileName)throws IOException {
		//FileManageUtils.exportFile(response, filePath + fileName, fileName);
		List<String> plbq=plsjglService.bfjdcbq();
		//System.out.println("保存机动车标签");
		String flag = "false,";
		String fileName = "备份机动车标签";
		String path = TxtUtil.AddFileText(plbq);
		File file = new File(path);
		response.setContentType("application/csv;charset=UTF-8");
        response.setHeader("Content-Disposition","attachment;  filename=" + new String((fileName + ".txt").getBytes(), "ISO8859-1"));
        //URLEncoder.encode(fileName, "GBK")
        InputStream in = null;
        try {
            in = new FileInputStream(file);
            int len = 0;
            byte[] buffer = new byte[1024];
            response.setCharacterEncoding("UTF-8");
            OutputStream out = response.getOutputStream();
            while ((len = in.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
        } catch (FileNotFoundException e) {
        	e.printStackTrace();
            System.out.println("00=="+e);
		} finally {
            if (in != null) {
                try {
                    in.close();
                    //TxtUtil.delFileText(path);
                    file.delete();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
		//return flag;
	}
	//驾驶人标签备份
	@RequestMapping(value = "/bfjsrbq", produces = { "text/html;charset=UTF-8" })
	@ResponseBody
	public void bfjsrbq(HttpServletResponse response) throws IOException {
		String flag = "false,";
		List<String> plbq=plsjglService.bfjsrbq();
		String fileName = "备份驾驶人标签";//初始名称
		String path = TxtUtil.AddFileText(plbq);
		System.out.println(path);
		File file = new File(path);
		response.setContentType("application/csv;charset=UTF-8");
		response.setHeader("Content-Disposition","attachment;  filename=" + new String((fileName + ".txt").getBytes(), "ISO8859-1"));
        InputStream in = null;
        try {
            in = new FileInputStream(file);
            
            int len = 0;
            byte[] buffer = new byte[1024];
            response.setCharacterEncoding("UTF-8");
            OutputStream out = response.getOutputStream();
            while ((len = in.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
        } catch (FileNotFoundException e) {
        	e.printStackTrace();
            //System.out.println("00=="+e);
		} finally {
            if (in != null) {
                try {
                    in.close();
                    //TxtUtil.delFileText(path);
                    file.delete();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
		//return flag;
	}	
	//=========================================================================================
	//机动车数据采集模板备份
	@RequestMapping(value = "/bfmbjdcxx", produces = { "text/html;charset=UTF-8" })
	@ResponseBody
	public String mbjdcxx(Model model, HttpServletRequest request, HttpServletResponse response) {
		Map<String,Object> map=new HashMap<String,Object>();
		String excelName="机动车数据采集模板";
		String formworkName="jdcxxcjFormwork";
		//map.put("obj", "");
		new ExcelUtil().ExcelFormwork(response, request, excelName, map, formworkName);
		return null;
	}
	//驾驶人数据备份
	@RequestMapping(value = "/bfmbjsrxx", produces = { "text/html;charset=UTF-8" })
	@ResponseBody
	public String mbjsrxx(HttpServletResponse response,HttpServletRequest request) {
		Map<String,Object> map=new HashMap<String,Object>();
		String excelName="驾驶人数据采集模板";
		String formworkName="jsrxxcjFormwork";
		//map.put("obj", "");
		new ExcelUtil().ExcelFormwork(response, request, excelName, map, formworkName);
		return null;
	}
	//原始备份机动车数据-方法  暂时废弃
	/*public String bfjdcsj(Model model, HttpServletRequest request, HttpServletResponse response) {
		String flag = "false,";
		String fileName = "机动车数据备份";
		//String[] columNames = {"标签id","号牌号码","号牌种类","车辆型号","所有人","车身颜色","发动机号","车辆识别代码","车辆类型","所属单位","内外部","金额"};
		String[] columNames = { "标签id", "号牌号码", "号牌种类", "车辆品牌", "车辆型号", "所有人", "车身颜色", "发动机号", "车辆识别代码", 
				"身份证号", "初次登记日期", "发牌日期", "登记人员", "联系电话","车辆类型", "发签日期", "销签日期", "标签状态", "ID", "民族", 
				"所属单位", "金额", "内外部", "可通行地点", "所属科室" ,"有效截止日期","副车牌号码","领导","上传图片名称"};
		//String[] keys = {"bqid", "hphm", "hpzl", "clxh", "syr" ,"csys","fdjh","clsbdh","cllx","ssdw","nwb","je"};
		String[] keys = { "bqid", "hphm", "hpzl", "clpp", "clxh", "syr", "csys", "fdjh", "clsbdh", "sfzmhm", "ccdjrq", "fprq", "djry", "lxdh",
				"cllx", "fqrq", "xqrq", "bqzt", "id", "mz", "ssdw", "je", "nwb", "ktxdd", "ssks" ,"yxjzrq","fcphm","ld","sctpdz"};
		List<Map<String, Object>> list = plsjglService.bfjdcsj(keys);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			ExcelUtil.createWorkBook(list, keys, columNames,"机动车数据备份").write(os);
			byte[] content = os.toByteArray();
			InputStream is = new ByteArrayInputStream(content);
			// 设置response参数，可以打开下载页面
			response.reset();
			//response.setContentType("application/vnd.ms-excel;charset=utf-8");
			response.setContentType("application/octet-stream;charset=utf-8");
			response.setHeader("Content-Disposition", "attachment;filename=" + new String((fileName + ".xls").getBytes(), "iso-8859-1"));
			ServletOutputStream out = response.getOutputStream();
			bis = new BufferedInputStream(is);
			bos = new BufferedOutputStream(out);
			byte[] buff = new byte[1024];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
			flag="true,";
		} catch (final IOException e) {
			e.printStackTrace();
		} finally {
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		flag = flag+list.size();
		//flag="true,110";
		//response.setContentType("text/json;charset=UTF-8");
		System.out.println("flag="+flag);
		return flag;
	}*/
	
	
	
	
	
	
	
}
