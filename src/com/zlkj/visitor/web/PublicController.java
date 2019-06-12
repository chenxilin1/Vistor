package com.zlkj.visitor.web;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zlkj.visitor.dto.DataGrid;
import com.zlkj.visitor.dto.MacOrIpDto;
import com.zlkj.visitor.entity.TAllmsg;
import com.zlkj.visitor.entity.TJsrk;
import com.zlkj.visitor.entity.TRzgl;
import com.zlkj.visitor.service.TJdcbqglService;
import com.zlkj.visitor.service.TJsrbqglService;
import com.zlkj.visitor.service.TRzglService;
import com.zlkj.visitor.util.TPublicUtil;
import com.zlkj.visitor.util.TTpscUtil;
/**
 * 公共的  访问事件
 * @author LYW 
 *  创建时间：2017-8-10 下午2:10:58
 *
 */
@Controller
@RequestMapping("/bqgl")
public class PublicController {

	@Autowired
	private TJdcbqglService jdcbqglService;
	@Autowired
	private TJsrbqglService jsrbqglService;
	@Autowired
	private TRzglService rzglService;
	
	
	//复制图片
	@ResponseBody
	@RequestMapping(value = "/tpfz", produces = { "text/html;charset=UTF-8" })
	public void tpfz(String tpdz, HttpServletRequest request,HttpServletResponse response) throws IOException {
		String dz = java.net.URLDecoder.decode(tpdz, "UTF-8");
		TTpscUtil.deleteFile(request);
		String localFilePath = request.getSession().getServletContext().getRealPath("/upload");
		String pathName=UUID.randomUUID().toString()+".jpg";
		TTpscUtil.Fztp(dz, localFilePath+"\\"+pathName);
		response.getWriter().write(pathName);
	}
	//删除图片
	@ResponseBody
	@RequestMapping(value = "/deleteTp", produces = { "text/html;charset=UTF-8" })
	public void deleteTp(String tpdz,String wysb,int i) throws IOException {
		
		String dz = java.net.URLDecoder.decode(tpdz, "UTF-8");
		int k=0;
		if (null!=wysb) {
			String wyz = java.net.URLDecoder.decode(wysb, "UTF-8");
			if (i==0) {
				TAllmsg allmsg = jdcbqglService.findByHphm(wyz);
				if (!dz.equals(allmsg.getSctpdz())) {
					k=1;
				}
			}else if (i==1) {
				TJsrk jsrk = jsrbqglService.findBySfzh(wyz);
				if (!dz.equals(jsrk.getSctpdz())) {
					k=1;
				}
			}else {
				k=1;
			}
		}else {
			k=1;
		}
		if (k==1) {
			File f = new File(dz);
		    f.delete();
		}
	}
	//验证Mac或sdk
	@RequestMapping(value = "/yzMacOrSdk")
	public void yzMacOrIp(MacOrIpDto macOrIpDto,HttpServletResponse response,HttpServletRequest request) throws Exception {
		if (null!=macOrIpDto.getMacdz()) {
			macOrIpDto.setMacdz(macOrIpDto.getMacdz().replace(",", "-"));
		}
		//本机电脑Mac
		InetAddress ia = InetAddress.getLocalHost();
		String xtMac = TPublicUtil.getLocalMac(ia);
		if ("0".equals(macOrIpDto.getGmbb())) {
			if (!macOrIpDto.getMacdz().equals(xtMac)) {
				response.getWriter().write("MACCW");
			}else {
				TPublicUtil.updateProperties("mac", macOrIpDto.getMacdz(),request);
				response.getWriter().write("YES");
			}
		}else if ("1".equals(macOrIpDto.getGmbb())) {
			if (!macOrIpDto.getSdkmc().equals("吕阳威")) {
				response.getWriter().write("SDKCW");
			}else {
				TPublicUtil.updateProperties("macSDK", macOrIpDto.getSdkmc(),request);
				
				response.getWriter().write("YES");
			}
		}else if ("2".equals(macOrIpDto.getGmbb())) {
			if (!macOrIpDto.getMacdz().equals(xtMac)) {
				response.getWriter().write("MACCW");
			}else {
				if (!macOrIpDto.getSdkmc().equals("吕阳威")) {
					response.getWriter().write("SDKCW");
				}else {
					//response.getWriter().write("YES");
				}
			}
		}else {
			response.getWriter().write("NO");
		}
	}
	//验证数据库连接正常
	@ResponseBody
	@RequestMapping(value = "/yzSjkIp")
	public void yzSjkIp(MacOrIpDto macOrIpDto,String sjkip,HttpServletResponse response,HttpServletRequest request) throws IOException {
		System.out.println(macOrIpDto.getSjkip());
		//String url="jdbc:mysql://"+"192.168.1.163"+":3306/entryinfo";
		String url="jdbc:mysql://"+macOrIpDto.getSjkip()+":3306/entryinfo";
		int index= TPublicUtil.getConnection(url);
		if (index==1) {
			TPublicUtil.updateProperties("jdbc.url", url,request);
			response.getWriter().write("YES");
		}else {
			response.getWriter().write("NO");
		}
		
		
		
		
	}
	//刷新日志详情
	@ResponseBody
	//@RequestMapping(value = "/sxrzxq")
	public DataGrid sxrzxq(HttpServletResponse response,HttpServletRequest request) throws IOException {
		DataGrid dg = new DataGrid();
		List<TRzgl> list=rzglService.findMax3s();
		long totla = 3;
		dg.setTotal(Integer.parseInt(totla + ""));
		dg.setRows(list);
		return dg;
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
