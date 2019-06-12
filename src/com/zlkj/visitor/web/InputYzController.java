package com.zlkj.visitor.web;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zlkj.visitor.dto.AjaxDto;
import com.zlkj.visitor.entity.TAllmsg;
import com.zlkj.visitor.entity.TJgaddress;
import com.zlkj.visitor.entity.TJsrk;
import com.zlkj.visitor.entity.TUser;
import com.zlkj.visitor.service.PpmsService;
import com.zlkj.visitor.service.TJdcbqglService;
import com.zlkj.visitor.service.TJsrbqglService;
import com.zlkj.visitor.service.TUserService;
import com.zlkj.visitor.util.Encrypt;
/**
 * 验证各种表单数据
 * @author LYW 
 *  创建时间：2017-6-20 上午11:31:06
 *
 */
@Controller
@RequestMapping("/xtgl")
public class InputYzController {
	@Autowired
	private TUserService userService;
	@Autowired
	private TJdcbqglService jdcbqglService;
	@Autowired
	private TJsrbqglService jsrbqglService;
	@Autowired
	private PpmsService ppmsService;
	
	
	/**
	 * 验证拦截站编号的唯一性
	 * @param bh
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/yzyhmwy")
	@ResponseBody
	public AjaxDto yzyhmwy(String bh) throws IOException  {
		System.out.println("698");
		AjaxDto aj = new AjaxDto();
		aj.setFlag(true);
		TUser user=userService.findByyhm(java.net.URLDecoder.decode(bh, "UTF-8"));
		if (null!=user) {
			aj.setFlag(false);
		}
		return aj;
	}
	//解密md5
	@RequestMapping("/jmmd5")
	@ResponseBody
	public AjaxDto jmmd5(String yhmm) throws IOException  {
		String mm=java.net.URLDecoder.decode(yhmm, "UTF-8");
		System.out.println(Encrypt.JM(Encrypt.KL(mm)));
		
		AjaxDto aj = new AjaxDto();
		aj.setMsg(Encrypt.JM(Encrypt.KL(mm)));
		return aj;
	}
	//验证号牌号码的唯一性
	@RequestMapping("/yzhphmwy")
	@ResponseBody
	public AjaxDto yzhphmwy(String hphm,String id) throws IOException  {
		String hm = java.net.URLDecoder.decode(java.net.URLDecoder.decode(hphm, "UTF-8"), "UTF-8");
		System.out.println(hm);
		AjaxDto aj = new AjaxDto();
		TAllmsg allmsgs= jdcbqglService.findByHphm(hm);
		aj.setFlag(false);
		if (null!=allmsgs) {
			if (!id.equals(allmsgs.getId()+"")) {
				aj.setFlag(true);
			}
		}
		return aj;
	}
	//验证身份证号码的唯一性
	@RequestMapping("/yzsfzhwy")
	@ResponseBody
	public AjaxDto yzsfzhwy(String sfzh,String id) throws IOException  {
		String sfz = java.net.URLDecoder.decode(java.net.URLDecoder.decode(sfzh, "UTF-8"), "UTF-8");
		AjaxDto aj = new AjaxDto();
		System.out.println(sfz);
		TJsrk jsrk = jsrbqglService.findBySfzh(sfz);
		aj.setFlag(false);
		if (null!=jsrk) {
			if (!id.equals(jsrk.getId()+"")) {
				aj.setFlag(true);
			}
		}
		return aj;
	}
	//验证匹配模式里的地点是否配置
	@RequestMapping("/yzddIspz")
	@ResponseBody
	public AjaxDto yzddIspz(String ddmc,String id) throws IOException  {
		String dd = java.net.URLDecoder.decode(java.net.URLDecoder.decode(ddmc, "UTF-8"), "UTF-8");
		AjaxDto aj = new AjaxDto();
		aj.setFlag(false);
		TJgaddress ppms= ppmsService.findByDdmc(dd);
		if (null!=ppms) {
			if (!id.equals(ppms.getId()+"")) {
				aj.setFlag(true);
			}
		}
		return aj;
	}
	//验证匹配模式里的IP是否已被征用
	@RequestMapping("/yzIpIsCz")
	@ResponseBody
	public AjaxDto yzIpIsCz(String ip,String id) throws IOException  {
		System.out.println("id是什么="+id);
		String ppmsip = java.net.URLDecoder.decode(java.net.URLDecoder.decode(ip, "UTF-8"), "UTF-8");
		AjaxDto aj = new AjaxDto();
		aj.setFlag(false);
		TJgaddress ppms= ppmsService.findByPpmsIp(ppmsip);
		if (null!=ppms) {
			if (!id.equals(ppms.getId()+"")) {
				aj.setFlag(true);
			}
		}
		return aj;
	}
	//验证匹配模式里的地点是否已被配置
	@RequestMapping("/yzddmc_wy_ppms")
	@ResponseBody
	public AjaxDto yzddmc_wy_ppms(String ppms,String id) throws IOException  {
		String ppmsdd = java.net.URLDecoder.decode(ppms, "UTF-8");
		AjaxDto aj = new AjaxDto();
		aj.setFlag(false);
		TJgaddress ppmsdds= ppmsService.findByDdmc(ppmsdd);
		if (null!=ppmsdds) {
			if (!id.equals(ppmsdds.getId()+"")) {
				aj.setFlag(true);
			}
		}
		return aj;
	}
}
