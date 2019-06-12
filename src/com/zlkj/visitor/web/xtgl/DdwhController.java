package com.zlkj.visitor.web.xtgl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
import com.zlkj.visitor.entity.TUser;
import com.zlkj.visitor.service.DdwhService;
import com.zlkj.visitor.service.TUserService;

/**
 * 地点管理维护
 * @author LYW 
 *  创建时间：2017-6-22 下午3:51:44
 *
 */
@Controller
@RequestMapping("/xtgl")
public class DdwhController {
	@Autowired
	private	DdwhService ddwhService ;
	@Autowired
	private TUserService userService;
	
	@RequestMapping(value = "/FindDDGLWH")
	public String FindDDGLWH(Model model) {
		return "/xtgl/ddwh_list";
	}
	//显示
	@RequestMapping(value = "findDdwh")
	@ResponseBody
	public DataGrid findDdwh(PulicCxtj pulicCxtj,HttpSession session) {
/*		if (null==pulicCxtj.getDdmcsz()) {
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
		System.out.println(pulicCxtj);
		DataGrid dg = new DataGrid();
		List<Ddmc> list = ddwhService.findAllFy(pulicCxtj);// 查询 分页
		long totla = ddwhService.findAllFyCount(pulicCxtj);// 查询 分页 总条数
		dg.setTotal(Integer.parseInt(totla + ""));
		dg.setRows(list);
		return dg;
	}
	// 添加
	@ResponseBody
	@RequestMapping(value = "/addDdwh", produces = { "text/html;charset=UTF-8" })
	public void addDdwh(Ddmc ddmc, HttpServletResponse response, String code, String ids) throws IOException {
		String msg = "NO";
		int index = ddwhService.add(ddmc);
		if (index > 0) {
			msg = "YES";
			response.getWriter().write(msg);
		} else {
			msg = "NO";
			response.getWriter().write(msg);
		}
	}

	// 删除人员
	@RequestMapping(value = "deleteDdwh")
	@ResponseBody
	public boolean deleteDdwh(String ids) {
		boolean flag = false;
		int index = ddwhService.delete(ids);
		if (index > 0) {
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}

	// 修改
	@RequestMapping(value = "updateDdwh", produces = { "text/html;charset=UTF-8" })
	@ResponseBody
	public String updateDdwh(String code, Integer yhid, Ddmc ddmc) throws UnsupportedEncodingException {
		ddmc.setId(yhid);
		String flag = "false";
		int index = ddwhService.update(ddmc);
		if (index > 0) {
			flag = "true";
		} else {
			flag = "false";
		}
		return flag;
	}	
	
	
	
	
	
	
}
