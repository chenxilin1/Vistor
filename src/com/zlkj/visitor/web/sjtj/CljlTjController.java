package com.zlkj.visitor.web.sjtj;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zlkj.visitor.dto.AjaxTjtDto;
import com.zlkj.visitor.dto.DataGrid;
import com.zlkj.visitor.dto.TjtCsDto;
import com.zlkj.visitor.entity.Ddmc;
import com.zlkj.visitor.entity.TCltxkb;
import com.zlkj.visitor.service.CljlTjService;
import com.zlkj.visitor.service.DdwhService;
import com.zlkj.visitor.service.TCodeService;
import com.zlkj.visitor.util.TPublicUtil;

/**
 * 车辆记录统计
 * @author LYW 
 *  创建时间：2017-4-18 下午4:09:19
 *
 */
@Controller
@RequestMapping("/sjtj")
public class CljlTjController {
	@Autowired
	private CljlTjService cljlTjService;
	@Autowired
	private TCodeService codeService;
	@Autowired
	private DdwhService ddwhService;
	
	//显示统计页面
	@RequestMapping(value = "/FindCLJLTJ")
	public String showCljlTj(Model model) {
		List<Ddmc> ktxdd = ddwhService.findAll();
		model.addAttribute("ktxdd_cljltjtj", ktxdd);
		
		String[] listhp = "京,津,冀,晋,蒙,辽,吉,黑,沪,苏,浙,皖,闽,赣,鲁,豫,鄂,湘,粤,桂,琼,渝,川,贵,云,藏,陕,甘,青,宁,港,澳,台,使".split(",");
		model.addAttribute("cltj_hp", listhp);
		// 获取字母
		List<String> list1 = new ArrayList<String>();
		for (int i = (int) 'A'; i < 'A' + 26; i++) {
			if ('A' != i) {
				list1.add("" + (char) i);
			}
		}
		model.addAttribute("cltj_hm", list1);
		return "/sjtj/cljlTj";
	}
	
	// 初始化统计图显示
	@ResponseBody
	@RequestMapping(value = "/CxTJt_Cljl")
	public AjaxTjtDto initcltx_tjt(TjtCsDto tjtCsDto) throws ParseException {
		AjaxTjtDto ajaxTjtDto = new AjaxTjtDto();//存放返回结果
		tjtCsDto.setKssj(TPublicUtil.DataTjFat(tjtCsDto.getKssj(), tjtCsDto.getJssj(), tjtCsDto.getTjfs(), "开始时间"));
		tjtCsDto.setJssj(TPublicUtil.DataTjFat(tjtCsDto.getKssj(), tjtCsDto.getJssj(), tjtCsDto.getTjfs(), "结束时间"));
		List<TjtCsDto> list=cljlTjService.finTjt(tjtCsDto);
		//List<Object> list=cljlTjService.finTjt(mapCxtj);
		List<String> listNum = new ArrayList<String>();
		List<String> lukou1 = new ArrayList<String>();
		List<String> shijianzhou = new ArrayList<String>();
		List<Map<String,Object>> sas = new ArrayList<Map<String,Object>>();
		if (null!=list && list.size()!=0) {
			for (TjtCsDto tjtCsDto2 : list) {
				shijianzhou.add(tjtCsDto2.getTxsj());
				listNum.add(tjtCsDto2.getSl()+"");
			}
			try {
				AjaxTjtDto tb=clickcjjlTb(tjtCsDto, shijianzhou.get(0), tjtCsDto.getTxqk());
				ajaxTjtDto.setCp(tb.getCp());
				ajaxTjtDto.setObject(tb.getObject());
				
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			//根据地点查询相应的数据
			String dd=tjtCsDto.getTxdd();
			if (dd==null) {
				/*String dds="";
				List<Ddmc> ktxdd = ddwhService.findAll();
				for (Ddmc ddmc : ktxdd) {
					dds+=ddmc.getAddress()+",";
				}
				dd=dds.substring(0,dds.length()-1);*/
				lukou1.add("车辆记录数量(辆)");
				Map<String,Object> map=new HashMap<String,Object>();//存放总map
				map.put("name", "车辆记录数量(辆)");
				map.put("type", "bar");
				//map.put("smooth", false);
				map.put("data", listNum);
				sas.add(map);
			}else {
				for (String dds : dd.split(",")) {
					String sls="";
					for (String str : shijianzhou) {
						tjtCsDto.setTxdd(dds);
						str = TPublicUtil.FatData(str);
						tjtCsDto.setKssj(TPublicUtil.DataTjFat(str, str, tjtCsDto.getTjfs(), "开始时间"));
						tjtCsDto.setJssj(TPublicUtil.DataTjFat(str, str, tjtCsDto.getTjfs(), "结束时间"));
						long sl=cljlTjService.findByDd(tjtCsDto);
						sls+=sl+",";
					}
					lukou1.add(dds);
					Map<String,Object> map=new HashMap<String,Object>();//存放总map
					map.put("name", dds);
					map.put("type", "bar");
					map.put("stack", "总数");
					map.put("smooth", true);
					map.put("data", sls.split(","));
					sas.add(map);
				}
			}
		}else {
			lukou1.add("此时间段无数据");
			//shijianzhou.add("此时间段无数据");
			Map<String,Object> map=new HashMap<String,Object>();//存放总map
			map.put("name", "此时间段无数据");
			map.put("type", "bar");
			map.put("stack", "总数");
			//map.put("smooth", false);
			map.put("data", "0".split(","));
			sas.add(map);
			shijianzhou.add("此时间段无数据");
			//listNum.add("0");
		}
		
		
		//lukou1.add("车辆记录数量(辆)");
		ajaxTjtDto.setTitlew("车辆记录-");
		//ajaxTjtDto.setLegendw("车辆记录数量(辆)");
		ajaxTjtDto.setLegendw(shijianzhou.get(0));
		
		ajaxTjtDto.setLegends(lukou1);
		//ajaxTjtDto.setShuliang(sas);
		ajaxTjtDto.setMap(sas);
		//ajaxTjtDto.setShuliang(listNum);
		ajaxTjtDto.setBeiyong(shijianzhou);
		return ajaxTjtDto;
	}
	//单击统计图后
	@RequestMapping("/clickcjjlTb")
	@ResponseBody	
	public AjaxTjtDto clickcjjlTb(TjtCsDto tjtCsDto,String shijian,String djry) throws UnsupportedEncodingException{
		//Map<String, Object> mapCxtj = new HashMap<String, Object>();//存放查询条件
		AjaxTjtDto ajaxTjtDto= new AjaxTjtDto();
		String txqk = java.net.URLDecoder.decode(djry, "UTF-8");
		//String zjzhou="进口车辆,出口车辆,滞留车辆";
		String zjzhou="进口车辆,出口车辆,滞留车辆";
		String sj = java.net.URLDecoder.decode(shijian, "UTF-8");
		sj = sj.replace("年", "-");
		sj = sj.replace("月", "-");
		sj = sj.replace("日", "");
		sj = sj.replace("时", ":");
		tjtCsDto.setKssj(TPublicUtil.DataTjFat(sj, sj, tjtCsDto.getTjfs(), "开始时间"));
		tjtCsDto.setJssj(TPublicUtil.DataTjFat(sj, sj, tjtCsDto.getTjfs(), "结束时间"));
		tjtCsDto.setTxqk(txqk);
		tjtCsDto.setTjfs(tjtCsDto.getTjfs());
		String[] sts=zjzhou.split(",");
		Map<String,List<Object>>map=new HashMap<String,List<Object>>();//存放总map
		List<Object> listName= new ArrayList<Object>();//存放name
		List<Object> ww= new ArrayList<Object>();//存放name对应的值
		for (String string : sts) {
			tjtCsDto.setSjzhou(string);
			List<TjtCsDto> sl= cljlTjService.djtjt(tjtCsDto);
			
			if (null==sl) {
				listName.add("无数据");
				ww.add(0);
			}else {
				for (TjtCsDto tjtCsDto2 : sl) {
					listName.add(string);
					ww.add(tjtCsDto2.getSl());
				}
			}
		}
		map.put("name", listName);
		map.put("value", ww);
		ajaxTjtDto.setObject(map);
		ajaxTjtDto.setCp(listName);
		return ajaxTjtDto;
	}	
	//单击圆形统计图后  获取数据
	// 单击饼图
	@RequestMapping("/clickcljlBt")
	@ResponseBody
	public DataGrid clickcljlBt(TjtCsDto tjtCsDto,HttpServletResponse response,String tbmc, String shijian, String tjfs, String txzt,Integer page,Integer rows,String txdd,String hp,String hm,String zhi) throws UnsupportedEncodingException {
		DataGrid dg = new DataGrid();
		tjtCsDto.setHp((hp!=null && !"".equals(hp)) ? java.net.URLDecoder.decode(hp, "UTF-8"):null);
		tjtCsDto.setHm((hm!=null && !"".equals(hm)) ? java.net.URLDecoder.decode(hm, "UTF-8"):null);
		tjtCsDto.setZhi((zhi!=null && !"".equals(zhi)) ? java.net.URLDecoder.decode(zhi, "UTF-8"):null);
		tjtCsDto.setTxdd((txdd!=null && !"".equals(txdd)) ? java.net.URLDecoder.decode(txdd, "UTF-8"):null);
		String mc = java.net.URLDecoder.decode(tbmc, "UTF-8");
		String sj = java.net.URLDecoder.decode(shijian, "UTF-8");
		sj = sj.replace("年", "-");
		sj = sj.replace("月", "-");
		sj = sj.replace("日", " ");
		sj = sj.replace("时", ":");
		tjtCsDto.setKssj(TPublicUtil.DataTjFat(sj, sj, tjtCsDto.getTjfs(), "开始时间"));
		tjtCsDto.setJssj(TPublicUtil.DataTjFat(sj, sj, tjtCsDto.getTjfs(), "结束时间"));
		tjtCsDto.setSjzhou(mc);
		List<TCltxkb> cltxkbs = cljlTjService.djbtsj(tjtCsDto);
		for (TCltxkb cltxkb : cltxkbs) {
			if ("滞留车辆".equals(tjtCsDto.getSjzhou())) {
				cltxkb.setFx("进口-滞留");
			}
			if (null != cltxkb.getFx()) {
				if (cltxkb.getFx().equals("0")) {
					cltxkb.setFx("进口");
				} else if (cltxkb.getFx().equals("1")){
					cltxkb.setFx("出口");
				}
			}
			if (null != cltxkb.getTxzt()) {
				if (cltxkb.getTxzt().equals("0")) {
					cltxkb.setTxzt("禁止通行");
				} else if (cltxkb.getTxzt().equals("1")){
					cltxkb.setTxzt("正常通行");
				}else {
					cltxkb.setTxzt("异常通行");
				}
			}
			if (null != cltxkb.getHpzl()) {
				String hpzl=codeService.cltxFindHpzl_dmmc(cltxkb.getHpzl());
				cltxkb.setHpzl(hpzl);
			}
			if (null != cltxkb.getCllx()) {
				String cllx=codeService.cltxFindCllx_dmmc(cltxkb.getCllx());
				cltxkb.setCllx(cllx);
			}
		}
		long totla = cljlTjService.djbtsjCount(tjtCsDto);
		dg.setTotal(Integer.parseInt(totla + ""));
		dg.setRows(cltxkbs);
		return dg;
	}	
	
	
	
	
	
	
	
	
	
	
}
