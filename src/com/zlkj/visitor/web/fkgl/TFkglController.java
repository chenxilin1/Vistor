package com.zlkj.visitor.web.fkgl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.zlkj.visitor.entity.TFkxx;
import com.zlkj.visitor.entity.TJgaddress;
import com.zlkj.visitor.entity.TUser;
import com.zlkj.visitor.service.DdwhService;
import com.zlkj.visitor.service.TFkxxService;
import com.zlkj.visitor.service.TUserService;

/**
 * 访客管理
 * @author LYW 
 *  创建时间：2017-6-23 下午8:58:36
 *
 */
@Controller
@RequestMapping("/fkgl")
public class TFkglController {
	
	@Autowired
	private TFkxxService fkxxService;
	@Autowired
	private DdwhService ddwhService;
	@Autowired
	private TUserService userService;

	@RequestMapping(value = "/FindFKXX")
	public String FindFKXX(Model model,HttpSession session) {
		String mz="汉族,蒙古族,回族,藏族,维吾尔族,苗族,彝族,壮族,布依族,朝鲜族,满族,侗族,瑶族,白族,土家族,哈尼族,哈萨克族,傣族,黎族,僳僳族,佤族," +
				"畲族,高山族,拉祜族,水族,东乡族,纳西族,景颇族,柯尔克孜族,土族,达斡尔族,仫佬族,羌族,布朗族,撒拉族,毛南族,仡佬族,锡伯族,阿昌族,普米族," +
				"塔吉克族,怒族,乌孜别克族,俄罗斯族,鄂温克族,德昂族,保安族,裕固族,京族,塔塔尔族,独龙族,鄂伦春族,赫哲族,门巴族,珞巴族,基诺族";
		String[] str=mz.split(",");
		model.addAttribute("fkxx_mz", str);
		
		String[] listhp = "京,津,冀,晋,蒙,辽,吉,黑,沪,苏,浙,皖,闽,赣,鲁,豫,鄂,湘,粤,桂,琼,渝,川,贵,云,藏,陕,甘,青,宁,港,澳,台,使".split(",");
		model.addAttribute("fkxx_add_hp", listhp);
		// 获取字母
		List<String> list1 = new ArrayList<String>();
		for (int i = (int) 'A'; i < 'A' + 26; i++) {
			if ('J' != i) {
				list1.add("" + (char) i);
			}
		}
		model.addAttribute("fkxx_add_hm", list1);
		List<Ddmc> list = ddwhService.findAll();
		model.addAttribute("fkxx_add_jgdd", list);
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
		model.addAttribute("fkxx_add_jgdd", ktxdd);*/
		return "/fkgl/fkxx_list";
	}
	// 条件 显示
	@RequestMapping(value = "findFkxx")
	@ResponseBody
	public DataGrid findFkxx(PulicCxtj pulicCxtj,HttpSession session) {
		/*if (null==pulicCxtj.getDdmcsz()) {
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
		}*/
		DataGrid dg = new DataGrid();
		List<TFkxx> list = fkxxService.findAllFy(pulicCxtj);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (TFkxx fkxx : list) {
			if (1==fkxx.getStatar()) {
				try {
					long kssj = sdf.parse(fkxx.getVisitStartTime()).getTime();
					long jssj = sdf.parse(fkxx.getVisitEndTime()).getTime();
					long dqsj = new Date().getTime();
					if (dqsj < kssj && kssj <= jssj) {
						fkxx.setStatar(2);//预约中
					}else if (dqsj<jssj && dqsj>=kssj && kssj <= jssj) {
						fkxx.setStatar(3);//拜访中
					}else if (dqsj > jssj && dqsj > kssj && kssj <= jssj) {
						fkxx.setStatar(4);//超时
					}else {
						fkxx.setStatar(14);//数据异常
					}
				} catch (Exception e) {
					fkxx.setStatar(14);//数据异常
					e.printStackTrace();
				}
				
			}else if (null!=fkxx.getStatar() && 1!=fkxx.getStatar() && 0!=fkxx.getStatar()) {
				fkxx.setStatar(15);//拜访状态异常
			}
			if (null!=fkxx.getUserpath()) {
				String[] dd=fkxx.getUserpath().split(",");
				String ddmc="";
				if (dd.length!=0) {
					for (String string : dd) {
						String dds=ddwhService.ddbh2ddmc(string);
						if (null==dds) {
							//dds=string;
							dds="地点已失效";
						}
						ddmc+=dds+",";
					}
					ddmc=ddmc.substring(0, ddmc.length()-1);
				}
				fkxx.setUserpath(ddmc);
			}
			try {
				//System.out.println(fkxx.getHphm());
				if (null != fkxx.getHphm() & !"".equals(fkxx.getHphm())) {
					fkxx.setHp(fkxx.getHphm().substring(0, 1));
					fkxx.setHm(fkxx.getHphm().substring(1, 2));
					fkxx.setZhi(fkxx.getHphm().substring(2, fkxx.getHphm().length()));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		long totla = fkxxService.findAllFyCount(pulicCxtj);
		dg.setTotal(Integer.parseInt(totla + ""));
		dg.setRows(list);
		return dg;
	}		
	// 添加
	@ResponseBody
	@RequestMapping(value = "/addFkxx", produces = { "text/html;charset=UTF-8" })
	public void addFkxx(TFkxx fkxx, HttpServletResponse response, String code, String ids,HttpSession session) throws IOException {
		String lrr = (String) session.getAttribute("username");
		String msg = "NO";
		if (null==lrr) {
			msg = "KLRR";
			response.getWriter().write(msg);
		}else {
			fkxx.setWriter(lrr);
			int index = fkxxService.add(fkxx);
			if (index > 0) {
				msg = "YES";
				response.getWriter().write(msg);
			} else {
				msg = "NO";
				response.getWriter().write(msg);
			}
		}
	}	
	// 注销-取消预约
	@RequestMapping(value = "zhuxiaoFkxx")
	@ResponseBody
	public boolean zhuxiaoFkxx(String ids) {
		boolean flag = false;
		int index = fkxxService.zhuxiao(ids);
		if (index > 0) {
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}

	// 修改
	@RequestMapping(value = "updateFkxx", produces = { "text/html;charset=UTF-8" })
	@ResponseBody
	public String updateFkxx(String code, Integer yhid, TFkxx fkxx,HttpSession session) throws UnsupportedEncodingException {
		fkxx.setId(yhid);
		
		String flag = "false";
		String lrr = (String) session.getAttribute("username");
		if (null!=lrr) {
			fkxx.setWriter(lrr);
		}
		
		
		int index = fkxxService.update(fkxx);
		if (index > 0) {
			flag = "true";
		} else {
			flag = "false";
		}
		return flag;
	}	
	
	
	
	
	
	
	
	
	
	
	
}
