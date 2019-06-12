package com.zlkj.visitor.web.xtgl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
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
import com.zlkj.visitor.util.Encrypt;

/**
 * 用户管理
 * 
 * @author LYW 创建时间：2017-6-19 下午4:20:27
 * 
 */
@Controller
@RequestMapping("/xtgl")
public class UserController {
	@Autowired
	private TUserService userService;
	@Autowired
	private DdwhService ddwhService;
	
	
	@RequestMapping("/FindYHGL")
	public String Findyhgl(Model model, HttpServletRequest request,HttpSession session) {
		String yhm=(String) session.getAttribute("username");
		List<Ddmc> ktxdd= new ArrayList<Ddmc>();
		if (null!=yhm) {
			//如果用户名为空
			if(yhm.equals("zlkj"))
			{
				ktxdd = ddwhService.findAll();
			}else{
				TUser user = userService.findByyhm(yhm);
				if (null!=user.getKtxdd()) {
					//获取t_user 表的ktxdd属性值
					String[] yhdd= user.getKtxdd().split(",");
					//遍历,然后去ddmc表查找并获取
					for (String dd : yhdd) {
						Ddmc ddmc = new Ddmc();
						String dds=ddwhService.ddbh2ddmc(dd);
						ddmc.setAddress(dds);
						ktxdd.add(ddmc);
					}
				}
			}
		}
		model.addAttribute("user_ktxdd", ktxdd);
		List<Ddmc> list = ddwhService.findAll();
		model.addAttribute("user_ktxdd", list);
		return "xtgl/user_list";
	}

	// 分页显示
	@RequestMapping(value = "/findUser")
	@ResponseBody
	public DataGrid findUser(PulicCxtj pulicCxtj, HttpServletRequest request, HttpSession session) {
		if (null==pulicCxtj.getDdmcsz()) {
			String yhm=(String) session.getAttribute("username");
			String ddmc="";
			if (null!=yhm) {
				if(yhm.equals("zlkj")){
					pulicCxtj.setDdmcsz(null);
				}else{
					TUser user = userService.findByyhm(yhm);
					if (null!=user.getKtxdd()) {
						String[] dd=user.getKtxdd().split(",");
						for (String string : dd) {
							ddmc+="%"+string+"%,";
						}
					}
					pulicCxtj.setDdmcsz(ddmc.split(","));
				}
			}
		}
		DataGrid dg = new DataGrid();
		// String qx = (String) session.getAttribute("isAdmin");
		// String name = (String) session.getAttribute("username");
		// TUser user=userService.findByyhm(name);
		/*
		 * String[] yhqx=user.getYhqx().split(","); List<String> ss= new
		 * ArrayList<String>(); for (String string : yhqx) {
		 * ss.add(string.substring(0,1)); } System.out.println(ss);
		 */
		/*
		 * if ("1".equals(qx)) { pulicCxtj.setQxjb(null); }else if
		 * ("2".equals(qx)) { pulicCxtj.setQxjb("1".split(",")); }else {
		 * pulicCxtj.setQxjb("1,2".split(",")); }
		 */
		List<TUser> list = userService.finAllFy(pulicCxtj);
		for (TUser tUser : list) {
			//可通行地点
			if (null!=tUser.getKtxdd() & !"".equals(tUser.getKtxdd())) {
				String[] yhdd= tUser.getKtxdd().split(",");
				String ddmc ="";
				for (String dd : yhdd) {
					String dds=ddwhService.ddbh2ddmc(dd);
					ddmc+=dds+",";
				}
				if (!"".equals(ddmc)) {
					String zhyw=ddmc.substring(ddmc.length()-1,ddmc.length());
					if (",".equals(zhyw)) {
						ddmc=ddmc.substring(0,ddmc.length()-1);
					}
					tUser.setKtxdd(ddmc);
				}
			}else {
				tUser.setKtxdd("用户可通行所有地点");
			}
			if (null != tUser.getSex()) {
				if (tUser.getSex().equals("0")) {
					tUser.setSex("女");
				} else {
					tUser.setSex("男");
				}
			}
			if (null != tUser.getIsAdmin()) {
				if ("1".equals(tUser.getIsAdmin())) {
					tUser.setIsAdmin("超级管理员");
				} else if ("2".equals(tUser.getIsAdmin())) {
					tUser.setIsAdmin("高级管理员");
				} else if ("3".equals(tUser.getIsAdmin())) {
					tUser.setIsAdmin("普通管理员");
				}
			}
		}
		long totla = userService.findAllFyCount(pulicCxtj);
		dg.setTotal(Integer.parseInt(totla + ""));
		dg.setRows(list);
		return dg;
	}

	// 添加
	@ResponseBody
	@RequestMapping(value = "/addUser", produces = { "text/html;charset=UTF-8" })
	public void addUser(TUser user, HttpServletResponse response, String code, String ids) throws IOException {
		String uuid = UUID.randomUUID().toString();
		user.setYhid(uuid);
		user.setYhqx("1,2,3,4,5,6,7,8,9,100,101,102,200,300,301,302,400,401,500,5010,600");
		user.setYhmm(Encrypt.e(user.getYhmm()));
		String msg = "NO";
		int index = userService.addUser(user);
		if (index > 0) {
			msg = "YES";
			response.getWriter().write(msg);
		} else {
			msg = "NO";
			response.getWriter().write(msg);
		}
	}

	// 删除人员
	@RequestMapping(value = "deleteUser")
	@ResponseBody
	public boolean deleteUser(String ids) {
		boolean flag = false;
		int index = userService.deleteUser(ids);
		if (index > 0) {
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}

	// 修改
	@RequestMapping(value = "updateUser", produces = { "text/html;charset=UTF-8" })
	@ResponseBody
	public String updUserAccosionCode(String code, String yhid, TUser user) throws UnsupportedEncodingException {
		if (null!=yhid) {
			String id = java.net.URLDecoder.decode(yhid, "UTF-8");
			user.setYhid(id);
		}
		String flag = "false";
		user.setYhmm(Encrypt.e(user.getYhmm()));
		int index = userService.updateUser(user);
		if (index > 0) {
			flag = "true";
		} else {
			flag = "false";
		}
		return flag;
	}

}
