package com.zlkj.visitor.web.sxgl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zlkj.visitor.dto.AjaxDto;
import com.zlkj.visitor.entity.Ddmc;
import com.zlkj.visitor.entity.TXjpz;
import com.zlkj.visitor.entity.TXjyzdpz;
import com.zlkj.visitor.service.DdwhService;
import com.zlkj.visitor.service.XjpzService;
import com.zlkj.visitor.service.XjyzdpzService;


@Controller
@RequestMapping("/sxgl")
public class SxglController {
	
	@Autowired
	private XjpzService xjpzService;
    @Autowired
  	private DdwhService ddwhService;
    @Autowired
    private XjyzdpzService xjyzdpzService;
	
	//显示
	@RequestMapping(value = "/FindCKSX")
	public String FindCKSX(Model model) {
		List<TXjpz> list = xjpzService.findAllSb();
		//System.out.println("001"+list);
		model.addAttribute("Allsb", list);
		List<Ddmc> dd =ddwhService.findAll();
		model.addAttribute("ddmcs", dd);
		return "/sxgl/cksxsb";
	}
	//查询下拉框
	//@RequestMapping(value = "/findAllxjmc")
	public @ResponseBody List<TXjpz> findAllxjmc() {
		List<TXjpz> list = xjpzService.findAllSb();
		return list;
	}
	//根据单击的下拉框的值查询详细信息
	@RequestMapping(value = "/ajax_cxxjxx")
	@ResponseBody 
	public AjaxDto ajax_cxxjxx(String jgmc) {
		System.out.println("jgmc="+jgmc);
		AjaxDto aj=new AjaxDto(); 
		List<TXjpz> list = xjpzService.findByXjmc(jgmc);
		if (null==list) {
			List<TXjpz> list2= new ArrayList<TXjpz>();
			TXjpz xjpz= new TXjpz();
			xjpz.setDdmc("空");
			xjpz.setXjip("空");
			xjpz.setXjmc("空");
			xjpz.setXjmm("空");
			xjpz.setXjtd("空");
			xjpz.setXjzh("空");
			list2.add(xjpz);
			aj.setObj(list2);
			return aj;
		}else {
			aj.setObj(list);
			return aj;
		}
	}
	
	//添加
	@RequestMapping(value = "/ajax_xjxx_add")
	@ResponseBody 
	public AjaxDto ajax_xjxx_add(TXjpz jgaddress) {
		AjaxDto aj=new AjaxDto(); 
		
		aj.setMsg("NO");
		String xjmciscf=jgaddress.getXjmc();
		List<TXjpz> xjpzs=xjpzService.findByXjmc(xjmciscf);
		if (null!=xjpzs && xjpzs.size()!=0) {
			aj.setMsg("XJMCCF");
		}else {
			if (null==jgaddress.getDdmc()) {
				aj.setMsg("WDDMC");
			}else if (null==jgaddress.getXjip()) {
				aj.setMsg("WXJIP");
			}else if (null==jgaddress.getXjmc()) {
				aj.setMsg("WXJMC");
			}else if (null==jgaddress.getXjmm()) {
				aj.setMsg("WXJMM");
			}else if (null==jgaddress.getXjtd()) {
				aj.setMsg("WXJTD");
			}if (jgaddress.getXjtd().length()!=1) {
				aj.setMsg("XJTDCD");
			}else if (null==jgaddress.getXjzh()) {
				aj.setMsg("WXJZH");
			}else {
				List<TXjpz> xjpz=xjpzService.findByXjip(jgaddress.getXjip());
				if (null!=xjpz && xjpz.size()!=0) {
					aj.setMsg("XJIPCZ");
				}else {
					xjpzService.save(jgaddress);
					List<TXjpz> list = xjpzService.findAllSb();
					String xjmc="";
					if (null!=list) {
						for (TXjpz tJgaddress : list) {
							//${sbxj.xjip},${sbxj.xjtd},${sbxj.xjzh},${sbxj.xjmm},${sbxj.xjmc}
							xjmc+=tJgaddress.getXjip()+","+tJgaddress.getXjtd()+","+tJgaddress.getXjzh()+","+tJgaddress.getXjmm()+","+tJgaddress.getXjmc()+";";
						}
					}
					String[] st=xjmc.split(";");
					aj.setObj(st);
					aj.setMsg("OK");
				}
			}
		}
		return aj;
	}
	//删除
	@RequestMapping(value = "/ajax_xjxx_delete")
	@ResponseBody 
	public AjaxDto ajax_xjxx_delete(String jgmc) {
		System.out.println(jgmc);
		AjaxDto aj=new AjaxDto(); 
		//TXjpz t=xjpzService.findByxjmc_delete(jgmc);
		
		int i=xjpzService.deleteXjmc(jgmc);
		System.out.println(i);
		List<TXjpz> list = xjpzService.findAllSb();
		String xjmc="";
		if (null!=list) {
			for (TXjpz tJgaddress : list) {
				xjmc+=tJgaddress.getXjip()+","+tJgaddress.getXjtd()+","+tJgaddress.getXjzh()+","+tJgaddress.getXjmm()+","+tJgaddress.getXjmc()+";";
			}
		}
		System.out.println(list);
		String[] st=xjmc.split(";");
		aj.setObj(st);
		return aj;
	}
	//修改
	@RequestMapping(value = "/ajax_xjxx_update")
	@ResponseBody 
	public AjaxDto ajax_xjxx_update(String jgmc,TXjpz xjpz) throws UnsupportedEncodingException {
		AjaxDto aj=new AjaxDto(); 
		String jgmcs = java.net.URLDecoder.decode(jgmc, "UTF-8");
		xjpz.setXjmc(jgmcs);
		//TXjpz t=xjpzService.findByxjmc_delete(jgmc);
		int i=xjpzService.updateXjmc(xjpz);
		if (i!=1) {
			aj.setMsg("NO");
		}else {
			aj.setMsg("YES");
			List<TXjpz> list = xjpzService.findAllSb();
			String xjmc="";
			if (null!=list) {
				for (TXjpz tJgaddress : list) {
					xjmc+=tJgaddress.getXjip()+","+tJgaddress.getXjtd()+","+tJgaddress.getXjzh()+","+tJgaddress.getXjmm()+","+tJgaddress.getXjmc()+";";
				}
			}
			String[] st=xjmc.split(";");
			aj.setObj(st);
		}
		return aj;
	}
	//预置点的-添加
	@RequestMapping(value = "/ajax_addYsd")
	@ResponseBody 
	public AjaxDto ajax_addYsd(TXjyzdpz xjyzdpz,String xjip) {
		AjaxDto aj=new AjaxDto(); 
		List<TXjpz> xjpz=xjpzService.findByXjip(xjip);
		String mc="";
		if (null!=xjpz) {
			for (TXjpz tXjpz : xjpz) {
				mc=tXjpz.getXjmc();
				xjyzdpz.setXjmc(mc);
			}
		}
		//String xjmc=xjyzdpz.getXjmc();
		//xJmcAndYzdmc.setXjmc(xjyzdpz.getXjmc());
		//xJmcAndYzdmc.setXjyzdmc(xjyzdpz.getXjyzdmc());
		List<TXjyzdpz> xjyzd=xjyzdpzService.findByxjmcAndyzdmc(xjyzdpz.getXjyzdmc(),mc);
		if (null!=xjyzd && xjyzd.size()!=0) {
			aj.setMsg("NO");
		}else {
			List<TXjyzdpz> list=xjyzdpzService.findByXjyzdmc(mc);
			List<String> str= new ArrayList<String>();
			if (null==list) {
				xjyzdpz.setXjyzdbh("1");
			}else {
				for (TXjyzdpz tXjyzdpz : list) {
					str.add(tXjyzdpz.getXjyzdbh());
				}
				for (int i = 1; i < 33; i++) {
					String ii=i+"";
					boolean bo=str.contains(ii);
					if (bo) {
						continue;
					}else {
						xjyzdpz.setXjyzdbh(i+"");
						break;
					}
				}
			}
			String id=UUID.randomUUID().toString();
			xjyzdpz.setTid(id);
			xjyzdpzService.save(xjyzdpz);
			List<TXjyzdpz> yzdmc2bh=xjyzdpzService.findByXjyzdmc(mc);
			String sss="";
			if (null==yzdmc2bh) {
				aj.setObj(null);
			}else {
				for (TXjyzdpz tXjyzdpz : yzdmc2bh) {
					sss+=tXjyzdpz.getXjyzdmc()+","+tXjyzdpz.getXjyzdbh()+";";
				}
				String[] st=sss.split(";");
				aj.setObj(st);
			}
			aj.setMsg(xjyzdpz.getXjyzdbh());
		}
		return aj;
	}
	//获取该相机名称下的所有预置点
	@RequestMapping(value = "/findAllXjmc2yzd")
	@ResponseBody 
	public AjaxDto findAllXjmc2yzd(TXjyzdpz xjyzdpz,String xjip) {
		System.out.println(xjip);
		if (null!=xjip) {
			List<TXjpz> xjpz=xjpzService.findByXjip(xjip);
			String mc="";
			if (null!=xjpz) {
				for (TXjpz tXjpz : xjpz) {
					mc=tXjpz.getXjmc();
					xjyzdpz.setXjmc(mc);
				}
			}
		}
		String xjmc=xjyzdpz.getXjmc();
		AjaxDto aj=new AjaxDto();
		List<TXjyzdpz> yzdmc2bh=xjyzdpzService.findByXjyzdmc(xjmc);
		String sss="";
		if (null==yzdmc2bh) {
			aj.setObj(null);
		}else {
			for (TXjyzdpz tXjyzdpz : yzdmc2bh) {
				sss+=tXjyzdpz.getXjyzdmc()+","+tXjyzdpz.getXjyzdbh()+";";
			}
			String[] st=sss.split(";");
			aj.setObj(st);
		}
		return aj;
	}
	
	//删除预置点
	@RequestMapping(value = "/ajax_delYsd")
	@ResponseBody 
	public AjaxDto ajax_delYsd(TXjyzdpz xjyzdpz,String xjip) {
		AjaxDto aj=new AjaxDto();
		List<TXjpz> xjpz=xjpzService.findByXjip(xjip);
		String mc="";
		for (TXjpz tXjpz : xjpz) {
			mc=tXjpz.getXjmc();
			xjyzdpz.setXjmc(mc);
		}
		
		
		aj.setMsg("NO");
		List<TXjyzdpz> xjyzd=xjyzdpzService.findByxjmcAndyzdmc(xjyzdpz.getXjyzdmc(),xjyzdpz.getXjmc());
		if (null==xjyzd) {
			aj.setMsg("BCZ");
		}else {
			int index=0;
			for (TXjyzdpz tXjyzdpz : xjyzd) {
				index=xjyzdpzService.deleteXjyzdmc(tXjyzdpz.getXjyzdmc());
			}
			if (index>0) {
				List<TXjyzdpz> yzdmc2bh=xjyzdpzService.findByXjyzdmc(xjyzdpz.getXjmc());
				String sss="";
				if (null==yzdmc2bh) {
					aj.setObj(null);
				}else {
					for (TXjyzdpz tXjyzdpz : yzdmc2bh) {
						sss+=tXjyzdpz.getXjyzdmc()+","+tXjyzdpz.getXjyzdbh()+";";
					}
					String[] st=sss.split(";");
					aj.setObj(st);
				}
				aj.setMsg("YES");
			}
		}
		return aj;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
