package com.zlkj.visitor.web.sjtj;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zlkj.visitor.dto.AjaxTjtDto;
import com.zlkj.visitor.dto.TjtCsDto;
import com.zlkj.visitor.service.FyqkTjService;
/**
 * 收费情况统计
 * @author LYW 
 *  创建时间：2017-6-21 下午6:13:44
 *
 */
@Controller
@RequestMapping("/sjtj")
public class SfqkTjController {

	@Autowired
	private FyqkTjService fyqkTjService;
	
	//显示统计页面
	@RequestMapping(value = "/FindSFQKTJ")
	public String FindSFQKTJ() {
		return "/sjtj/sfqkTj";
	}
	// 初始化统计图显示
	@ResponseBody
	@RequestMapping(value = "/CxTJt_Fyqk")
	public AjaxTjtDto initcltx_tjt(TjtCsDto tjtCsDto) {
		AjaxTjtDto ajaxTjtDto = new AjaxTjtDto();//存放返回结果
		try {
			String kssj="";
			String jssj="";
			if ("1".equals(tjtCsDto.getTjfs())) {
				kssj=tjtCsDto.getKssj();
				jssj=tjtCsDto.getJssj().substring(0,4)+"-12-31 59:59:59";
			}else if ("2".equals(tjtCsDto.getTjfs())) {
				kssj=tjtCsDto.getKssj();
				jssj=tjtCsDto.getJssj().substring(0,7)+"-31 59:59:59";
			}else if ("3".equals(tjtCsDto.getTjfs())) {
				kssj=tjtCsDto.getKssj();
				jssj=tjtCsDto.getJssj().substring(0,10)+" 59:59:59";
			}else if ("4".equals(tjtCsDto.getTjfs())) {
				kssj=tjtCsDto.getKssj();
				jssj=tjtCsDto.getJssj().substring(0,13)+":59:59";
			}
			tjtCsDto.setKssj(kssj);
			tjtCsDto.setJssj(jssj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<TjtCsDto> list=fyqkTjService.finTjt(tjtCsDto);
		List<String> listNum = new ArrayList<String>();
		List<String> lukou1 = new ArrayList<String>();
		List<String> shijianzhou = new ArrayList<String>();
		if (list.size()!=0) {
			for (TjtCsDto tjtCsDto2 : list) {
				shijianzhou.add(tjtCsDto2.getTxsj());
				listNum.add(tjtCsDto2.getSl()+"");
			}
		}else {
			shijianzhou.add("此时间段无数据");
			listNum.add("0");
		}
		lukou1.add("收费情况金额(元)");
		ajaxTjtDto.setTitlew("收费情况-");
		ajaxTjtDto.setLegendw("收费情况金额(元)");
		ajaxTjtDto.setLegends(lukou1);
		ajaxTjtDto.setShuliang(listNum);
		ajaxTjtDto.setBeiyong(shijianzhou);
		return ajaxTjtDto;
	}	
	
	
	
	
	
	
}
