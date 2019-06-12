package com.zlkj.visitor.web.sjcx;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zlkj.visitor.dto.DataGrid;
import com.zlkj.visitor.dto.FtpBean;
import com.zlkj.visitor.dto.PulicCxtj;
import com.zlkj.visitor.entity.Ddmc;
import com.zlkj.visitor.entity.TAllmsg;
import com.zlkj.visitor.entity.TCltxkb;
import com.zlkj.visitor.entity.TCode;
import com.zlkj.visitor.entity.TJsrk;
import com.zlkj.visitor.entity.TUser;
import com.zlkj.visitor.service.DdwhService;
import com.zlkj.visitor.service.TCltxqkcxService;
import com.zlkj.visitor.service.TCodeService;
import com.zlkj.visitor.service.TJdcbqglService;
import com.zlkj.visitor.service.TJsrbqglService;
import com.zlkj.visitor.service.TUserService;
import com.zlkj.visitor.util.FtpUtil;
import com.zlkj.visitor.util.TTpscUtil;

/**
 * 通行车辆查询
 * @author LYW 
 *  创建时间：2017-6-22 下午4:49:22
 *
 */
@Controller
@RequestMapping("/sjcx")
public class TCltxqkcxController {
	@Autowired
	private	TCltxqkcxService cltxqkcxService;
	@Autowired
	private TCodeService codeService;
	@Autowired
	private DdwhService ddwhService;
	@Autowired
	private TJdcbqglService jdcbqglService;
	@Autowired
	private TJsrbqglService jsrbqglService;
	@Autowired
	private TUserService userService;
	
	@RequestMapping(value = "/FindCLTXQK")
	public String FindDDGLWH(Model model,HttpSession session) {
		List<TCode> list=codeService.finListhpzl();
		model.addAttribute("txcl_hpzl", list);
		
		String[] listhp = "京,津,冀,晋,蒙,辽,吉,黑,沪,苏,浙,皖,闽,赣,鲁,豫,鄂,湘,粤,桂,琼,渝,川,贵,云,藏,陕,甘,青,宁,港,澳,台,使".split(",");
		model.addAttribute("txcl_hp", listhp);
		// 获取字母
		List<String> list1 = new ArrayList<String>();
		for (int i = (int) 'A'; i < 'A' + 26; i++) {
			if ('A' != i) {
				list1.add("" + (char) i);
			}
		}
		model.addAttribute("txcl_hm", list1);
		//List<Ddmc> ktxdd = ddwhService.findAll();
		//当前用户的地点
		String yhm=(String) session.getAttribute("username");
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
				}else {
					ktxdd = ddwhService.findAll();
				}
			}
		}
		model.addAttribute("ktxdd_txcltj", ktxdd);
		return "/sjcx/cltxqk_list";
	}
	//显示
	@RequestMapping(value = "findCltxqk")
	@ResponseBody
	public DataGrid findCltxqk(PulicCxtj pulicCxtj,HttpSession session) {
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
							ddmc+=string+",";
						}
						pulicCxtj.setDdmcsz(ddmc.split(","));
					}else {
						pulicCxtj.setDdmcsz(null);
					}
				}
			}
		}
		DataGrid dg = new DataGrid();
		List<TCltxkb> list = cltxqkcxService.findAllFy(pulicCxtj);
		for (TCltxkb cltxkb : list) {
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
			if (null != cltxkb.getCllx() & !"".equals(cltxkb.getCllx())) {
				String cllx=codeService.cltxFindCllx_dmmc(cltxkb.getCllx());
				cltxkb.setCllx(cllx);
			}
		}
		long totla = cltxqkcxService.findAllFyCount(pulicCxtj);
		dg.setTotal(Integer.parseInt(totla + ""));
		dg.setRows(list);
		return dg;
	}
	@RequestMapping("/findCltxqkXx")
	public String findCltxqkXx(@RequestParam("id") String id,String hphm,String sfzh, Model model,HttpServletRequest request) throws UnsupportedEncodingException {
		String hp = java.net.URLDecoder.decode(hphm, "UTF-8");
		//通行数据
		TCltxkb cltxkb=cltxqkcxService.findById(id);
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
		cltxkb.setTp1(cltxkb.getTplj() + cltxkb.getTp1());
		cltxkb.setTp2(cltxkb.getTp2() + cltxkb.getTp3());
		model.addAttribute("tCltxkb", cltxkb);
		//机动车数据
		TAllmsg allmsg = jdcbqglService.findByHphm(hp);
		if (null!=allmsg) {
			if (null!=allmsg.getKtxdd()) {
				String[] dd=allmsg.getKtxdd().split(",");
				String ddmc="";
				if (dd.length!=0) {
					for (String string : dd) {
						String dds=ddwhService.ddbh2ddmc(string);
						if (null==dds) {
							dds=string;
						}
						ddmc+=dds+",";
					}
					ddmc=ddmc.substring(0, ddmc.length()-1);
				}
				allmsg.setKtxdd(ddmc);
			}else {
				allmsg.setKtxdd("");
			}
			if (null != allmsg.getCsys()) {
				String csys=codeService.findCsys_dmmc(allmsg.getCsys());
				allmsg.setCsys(csys);
			}
			if (null != allmsg.getHpzl()) {
				String hpzl = codeService.cltxFindHpzl_dmmc(allmsg.getHpzl());
				allmsg.setHpzl(hpzl);
			}
			if (null != allmsg.getCllx()) {
				String cllx = codeService.cltxFindCllx_dmmc(allmsg.getCllx());
				allmsg.setCllx(cllx);
			}
			//复制图片
			TTpscUtil.deleteFile(request);
			String localFilePath = request.getSession().getServletContext().getRealPath("/upload");
			String pathName=UUID.randomUUID().toString()+".jpg";
			if (null!=allmsg.getSctpdz()) {
				TTpscUtil.Fztp(allmsg.getSctpdz(), localFilePath+"\\"+pathName);
			}
			allmsg.setSctpdz(pathName);
		}
		model.addAttribute("jdcbq_txcl", allmsg);
		//驾驶人数据
		TJsrk jsrk =jsrbqglService.findBySfzh(sfzh);
		if (null!=jsrk) {
			if (null!=jsrk.getSex()) {
				if ("0".equals(jsrk.getSex())) {
					jsrk.setSex("女");
				}else if ("1".equals(jsrk.getSex())) {
					jsrk.setSex("男");
				}
			}
			if (null!=jsrk.getKtxdd()) {
				String[] dd=jsrk.getKtxdd().split(",");
				String ddmc="";
				if (dd.length!=0) {
					for (String string : dd) {
						String dds=ddwhService.ddbh2ddmc(string);
						if (null==dds) {
							dds=string;
						}
						ddmc+=dds+",";
					}
					ddmc=ddmc.substring(0, ddmc.length()-1);
				}
				jsrk.setKtxdd(ddmc);
			}else {
				jsrk.setKtxdd("");
			}
			if (null != jsrk.getZt()) {
				if (jsrk.getZt().equals("0")) {
					jsrk.setZt("注销");
				} else if (jsrk.getZt().equals("1")) {
					jsrk.setZt("已启用");
				} else if (jsrk.getZt().equals("2")) {
					jsrk.setZt("未启用");
				}
			}
			String localFilePath = request.getSession().getServletContext().getRealPath("/upload");
			String pathName=UUID.randomUUID().toString()+".jpg";
			if (null!=allmsg.getSctpdz()) {
				TTpscUtil.Fztp(jsrk.getSctpdz(), localFilePath+"\\"+pathName);
			}
			jsrk.setSctpdz(pathName);
		}
		model.addAttribute("jsrbq_txcl", jsrk);

		return "/sjcx/cltxqk_show";

	}	
	/**
	 * 显示图片
	 * 
	 * @param imgSrc
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/showImage")
	@ResponseBody
	public byte[] showImage(String imgSrc) throws Exception {
		String strName = java.net.URLDecoder.decode(imgSrc, "UTF-8");
		System.out.println("000000="+strName);

		byte[] buffer = new byte[5 * 1024];
		try {
			//String strName = new String(img.getBytes("ISO-8859-1"), "utf-8");
			//System.out.println(strName);
			// String
			// strName="ftp://zlkj:zlkj@192.168.1.221/轮胎厂卡口/已识别/20130522/15/20130522154350230-02-辽EF8079.jpg";
			int index = strName.indexOf("/", 6);
			int lastindex = strName.lastIndexOf("/");
			String strHtp = strName.substring(0, index);
			int index1 = strHtp.indexOf("//");
			int index2 = strHtp.indexOf("@");
			String strIp = strHtp.substring(index2 + 1, strHtp.length());

			String stru = strHtp.substring(index1 + 2, index2);
			String[] strs = stru.split(":");

			String username = strs[0];
			String password = strs[1];

			String strimg = strName.substring(lastindex + 1, strName.length());
			String strPath = strName.substring(index, lastindex + 1);

			FtpBean bean = new FtpBean();
			bean.setIp(strIp);
			// bean.setIp("192.168.1.157");
			bean.setPort(21);
			bean.setUsername(username);
			bean.setPassword(password);
			bean.setFtpPath(strPath);
			bean.setFileName(strimg);
			// String fileName="c:\\新建.txt";
			// FtpUtil.upLoad(bean, fileName);
			//bean.setLocalPath("E:");
			buffer = FtpUtil.downloads(bean);

		} catch (Exception e1) {
			e1.printStackTrace();
		}

		return buffer;

	}
	//转到图片对比页面
	@RequestMapping(value = "/tpdbt")
	public String tpdbt(Model model,String clsctp,String cltxtp,HttpServletRequest request) throws UnsupportedEncodingException {
		String sctp = java.net.URLDecoder.decode(clsctp, "UTF-8");
		String txtp = java.net.URLDecoder.decode(cltxtp, "UTF-8");
		//String localFilePath = request.getSession().getServletContext().getRealPath("/");
		//model.addAttribute("clsctp", localFilePath+clsctp);
		model.addAttribute("clsctp", sctp);
		model.addAttribute("cltxtp", txtp);
		return "/sjcx/tpdbt";
	}
	
	
	
	
	
	
	
	
	
	
}
