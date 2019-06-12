package com.zlkj.visitor.web.xtgl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zlkj.visitor.dto.DataGrid;
import com.zlkj.visitor.dto.PulicCxtj;
import com.zlkj.visitor.entity.Ddmc;
import com.zlkj.visitor.entity.TCltxkb;
import com.zlkj.visitor.entity.TCode;
import com.zlkj.visitor.entity.TRzgl;
import com.zlkj.visitor.entity.TUser;
import com.zlkj.visitor.service.TRzglService;
import com.zlkj.visitor.service.TUserService;
import com.zlkj.visitor.util.TPublicUtil;

/**
 * 日志管理
 * @author LYW 
 *  创建时间：2017-7-27 下午6:25:09
 *
 */
@Controller
@RequestMapping("/xtgl")
public class RzglController {

	@Autowired
	private TRzglService rzglService;
	@Autowired
	private TUserService userService;
	
	
	@RequestMapping(value = "/FindRZGL")
	public String FindDDGLWH(Model model,HttpSession session) {
		List<TUser> list=userService.findAllUser();
		model.addAttribute("rzgl_czyh", list);
		return "/xtgl/rzgl_list";
	}	
	
	
	//显示
	@RequestMapping(value = "findRzgl")
	@ResponseBody
	public DataGrid findRzgl(PulicCxtj pulicCxtj,HttpSession session) {
		DataGrid dg = new DataGrid();
		List<TRzgl> list = rzglService.findAllFy(pulicCxtj);
		long totla = rzglService.findAllFyCount(pulicCxtj);
		dg.setTotal(Integer.parseInt(totla + ""));
		dg.setRows(list);
		return dg;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
