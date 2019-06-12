package com.zlkj.visitor.web.xtgl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zlkj.visitor.dto.DataGrid;
import com.zlkj.visitor.dto.PulicCxtj;
import com.zlkj.visitor.entity.Ddmc;
import com.zlkj.visitor.entity.TJgaddress;
import com.zlkj.visitor.entity.TUser;
import com.zlkj.visitor.service.DdwhService;
import com.zlkj.visitor.service.PpmsService;
import com.zlkj.visitor.service.TUserService;

/**
 * 匹配模式 - 原来的设备管理
 * @author LYW 
 *  创建时间：2017-6-21 下午7:12:24
 *
 */

@Controller
@RequestMapping("/xtgl")
public class PpmsController {

	@Autowired
	private PpmsService ppmsService;
	@Autowired
	private DdwhService ddwhService;	
	@Autowired
	private TUserService userService;
	
	@RequestMapping(value = "/FindPPMSGL")
	public String FindPPMSGL(Model model,HttpSession session) {
		//所有的地点
		List<Ddmc> list = ddwhService.findAll();
		model.addAttribute("ppms_ddmc", list);
		//当前用户的地点
		/*String yhm=(String) session.getAttribute("username");
		List<Ddmc> ktxdd= new ArrayList<Ddmc>();
		if (null!=yhm) {
			if(yhm.equals("zlkj")){
				ktxdd = ddwhService.findAll();
			}else{
				TUser user = userService.findByyhm(yhm);
				if (null!=user.getKtxdd()) {
					String[] yhdd= user.getKtxdd().split(",");
					for (String dd : yhdd) {
						Ddmc ddmc = new Ddmc();
						String dds=ddwhService.ddbh2ddmc(dd);
						ddmc.setAddress(dds);
						ktxdd.add(ddmc);
					}
				}
			}
		}
		model.addAttribute("ppms_ddmc", ktxdd);*/
		
		
		return "/xtgl/ppmsgl_list";
	}
	
	@RequestMapping(value = "findPpms")
	@ResponseBody
	public DataGrid findPpms(PulicCxtj pulicCxtj,HttpSession session) {
		/*if (null==pulicCxtj.getDdmcsz()) {
			String yhm=(String) session.getAttribute("username");
			String ddmc="";
			if (null!=yhm) {
				if(yhm.equals("zlkj")){
					List<Ddmc> ktxdd = ddwhService.findAll();
					for (Ddmc ddmc2 : ktxdd) {
						ddmc+=ddmc2.getAddress()+",";
					}
				}else{
					TUser user = userService.findByyhm(yhm);
					if (null!=user.getKtxdd()) {
						String[] yhdd= user.getKtxdd().split(",");
						for (String dd : yhdd) {
							String dds=ddwhService.ddbh2ddmc(dd);
							ddmc+=dds+",";
						}
					}
				}
				pulicCxtj.setDdmcsz(ddmc.split(","));
			}
		}*/
		DataGrid dg = new DataGrid();
		List<TJgaddress> list = ppmsService.finAll(pulicCxtj);
		for (TJgaddress tJgaddress : list) {
			if (null != tJgaddress.getCkip()) {
				if (tJgaddress.getCkip().equals("1")) {
					tJgaddress.setCkip("出口");
				} else if (tJgaddress.getCkip().equals("0")){
					tJgaddress.setCkip("进口");
				}
			}
			if (null != tJgaddress.getXjsbbh()) {
				if (tJgaddress.getXjsbbh().equals("1")) {
					tJgaddress.setXjsbbh("启用");
				} else if (tJgaddress.getXjsbbh().equals("0")){
					tJgaddress.setXjsbbh("未启用");
				}
			}
			if (null!=tJgaddress.getPpms()) {
				String ppms=tJgaddress.getPpms();
				ppms = ppms.replace("1", "车签");
				ppms = ppms.replace("2", "人签");
				ppms = ppms.replace("3", "车牌");
				ppms = ppms.replace(",", "且");
				ppms = ppms.replace("-", "或");
				tJgaddress.setPpms(ppms);
			}
			/*if (tJgaddress.getPpms().indexOf(",")>-1) {
				tJgaddress.setPpmsgx("并且");
			}else if (tJgaddress.getPpms().indexOf("-")>-1) {
				tJgaddress.setPpmsgx("或者");
			}else {
				tJgaddress.setPpmsgx("并且");
			}*/
			if (null != tJgaddress.getXjfx()) {
				if (tJgaddress.getXjfx().equals("0")) {
					tJgaddress.setXjfx("内部");
				} else if (tJgaddress.getXjfx().equals("1")){
					tJgaddress.setXjfx("外部");
				}
			}
			if (null!=tJgaddress.getCkclfs()) {
				if ("1".equals(tJgaddress.getCkclfs())) {
					tJgaddress.setCkclfs("处理数据发命令");
				}else if ("2".equals(tJgaddress.getCkclfs())) {
					tJgaddress.setCkclfs("处理数据不发命令");
				}else if ("3".equals(tJgaddress.getCkclfs())) {
					tJgaddress.setCkclfs("走匹配模式");
				}
			}
			if (null!=tJgaddress.getRfidms()) {
				if ("0".equals(tJgaddress.getRfidms())) {
					tJgaddress.setRfidms("否");
				}else if ("1".equals(tJgaddress.getRfidms())) {
					tJgaddress.setRfidms("是");
				}
			}
		}
		long totla = ppmsService.findAllCount(pulicCxtj);
		dg.setTotal(Integer.parseInt(totla + ""));
		dg.setRows(list);
		return dg;
	}
	// 添加
	@ResponseBody
	@RequestMapping(value = "/addPpms", produces = { "text/html;charset=UTF-8" })
	public void addPpms(TJgaddress jgaddress, HttpServletResponse response, String code, String ids) throws IOException {
		String msg = "NO";
		int index = ppmsService.add(jgaddress);
		if (index > 0) {
			msg = "YES";
			response.getWriter().write(msg);
		} else {
			msg = "NO";
			response.getWriter().write(msg);
		}
	}

	// 删除人员
	@RequestMapping(value = "deletePpms")
	@ResponseBody
	public boolean deletePpms(String ids) {
		boolean flag = false;
		int index = ppmsService.delete(ids);
		if (index > 0) {
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}

	// 修改
	@RequestMapping(value = "updatePpms", produces = { "text/html;charset=UTF-8" })
	@ResponseBody
	public String updatePpms(String code, Integer yhid, TJgaddress jgaddress) throws UnsupportedEncodingException {
		jgaddress.setId(yhid);
		String flag = "false";
		int index = ppmsService.update(jgaddress);
		if (index > 0) {
			flag = "true";
		} else {
			flag = "false";
		}
		return flag;
	}	
	
	
	
	
	
	
	
	
	
	
	
	
}
