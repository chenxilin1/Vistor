package com.zlkj.visitor.web.bqgl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zlkj.visitor.dto.DataGrid;
import com.zlkj.visitor.dto.PulicCxtj;
import com.zlkj.visitor.entity.Ddmc;
import com.zlkj.visitor.entity.TAllmsg;
import com.zlkj.visitor.entity.TCode;
import com.zlkj.visitor.service.DdwhService;
import com.zlkj.visitor.service.TCodeService;
import com.zlkj.visitor.service.TJdcbqglService;
import com.zlkj.visitor.service.TUserService;
import com.zlkj.visitor.util.ExcelUtil;
import com.zlkj.visitor.util.TTpscUtil;

/**
 * 机动车标签管理
 * 
 * @author LYW 创建时间：2017-6-23 上午10:39:30
 * 
 */
@Controller
@RequestMapping("/bqgl")
public class TJdcbqglController {

	@Autowired
	private TJdcbqglService jdcbqglService;
	@Autowired
	private TCodeService codeService;
	@Autowired
	private DdwhService ddwhService;
	@Autowired
	private TUserService userService;

	
	@RequestMapping(value = "/FindJDCBQGL")
	public String FindJDCBQGL(Model model,HttpSession session) {
		List<TCode> csys=codeService.finListCsys();
		model.addAttribute("jdcbq_csys", csys);
		List<TCode> list = codeService.finListhpzl();
		model.addAttribute("jdcbq_hpzl", list);
		List<TCode> cllx= codeService.finListcllx();
		model.addAttribute("jdcbq_cllx", cllx);
		String[] listhp = "京,津,冀,晋,蒙,辽,吉,黑,沪,苏,浙,皖,闽,赣,鲁,豫,鄂,湘,粤,桂,琼,渝,川,贵,云,藏,陕,甘,青,宁,港,澳,台,使".split(",");
		model.addAttribute("jdcbq_hp", listhp);
		// 获取字母
		List<String> list1 = new ArrayList<String>();
		for (int i = (int) 'A'; i < 'A' + 26; i++) {
			if ('A' != i) {
				list1.add("" + (char) i);
			}
		}
		// 存字母
		model.addAttribute("jdcbq_hm", list1);
		List<Ddmc> ktxdd = ddwhService.findAll();
		model.addAttribute("ktxdd_jdcbq", ktxdd);
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
		model.addAttribute("ktxdd_jdcbq", ktxdd);*/
		return "/bqgl/jdcbqgl_list";
	}

	// 条件 显示
	@RequestMapping(value = "findJdcbqgl")
	@ResponseBody
	public DataGrid findJdcbq(Model model,PulicCxtj pulicCxtj,HttpSession session) {
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
		List<TAllmsg> list = jdcbqglService.findAllFy(pulicCxtj);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (TAllmsg tAllmsg : list) {
			if (null!=tAllmsg.getKtxdd() & !"".equals(tAllmsg.getKtxdd())) {
				String[] dd=tAllmsg.getKtxdd().split(",");
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
				tAllmsg.setKtxdd(ddmc);
			}else {
				tAllmsg.setKtxdd("");
			}
			
			/*if (null!=tAllmsg.getHphm() && !"".equals(tAllmsg.getHphm())) {
				if (tAllmsg.getHphm().length()==7) {
					try {
						String hphm = tAllmsg.getHphm();
						tAllmsg.setHp(hphm.substring(0, 1));
						tAllmsg.setHm(hphm.substring(1, 2));
						tAllmsg.setZhi(hphm.substring(2, 7));
					} catch (Exception e) {
						System.out.println(tAllmsg.getHphm());
					}
				}else {
					tAllmsg.setHp(null);
					tAllmsg.setHm(null);
					tAllmsg.setZhi(null);
				}
			}*/
			if (null != tAllmsg.getCsys() & !"".equals(tAllmsg.getCsys())) {
				String csys=codeService.findCsys_dmmc(tAllmsg.getCsys());
				tAllmsg.setCsys(csys);
			}
			if (null != tAllmsg.getHpzl() & !"".equals(tAllmsg.getHpzl())) {
				String hpzl = codeService.cltxFindHpzl_dmmc(tAllmsg.getHpzl());
				tAllmsg.setHpzl(hpzl);
			}
			if (null != tAllmsg.getCllx() & !"".equals(tAllmsg.getCllx())) {
				String cllx = codeService.cltxFindCllx_dmmc(tAllmsg.getCllx());
				tAllmsg.setCllx(cllx);
			}
			if (null!=tAllmsg.getYxjzrq() & !"".equals(tAllmsg.getYxjzrq())) {
				try {
				long dqsj = new Date().getTime();
				long yxsj = sdf.parse(tAllmsg.getYxjzrq()).getTime();
				if (dqsj >= yxsj) {
					tAllmsg.setBqzt("失效");
				}else if (dqsj < yxsj){
					tAllmsg.setBqzt("正常");
				}else {
					tAllmsg.setBqzt("有效期异常");
				}
				
				} catch (ParseException e) {
					e.printStackTrace();
					tAllmsg.setBqzt("有效期异常");
				}
			}else {
				tAllmsg.setBqzt("有效期异常");
			}
			if (null!=tAllmsg.getXqrq()) {
				tAllmsg.setBqzt("注销");
			}
		}
		long totla = jdcbqglService.findAllFyCount(pulicCxtj);
		dg.setTotal(Integer.parseInt(totla + ""));
		dg.setRows(list);
		model.addAttribute("Lodop_jdcsj_totla", totla);
		model.addAttribute("Lodop_jdcsj_list", list);
		return dg;
	}
	// 添加
	@ResponseBody
	@RequestMapping(value = "/addJdcbqgl", produces = { "text/html;charset=UTF-8" })
	public void addJdcbq(TAllmsg allmsg, HttpServletResponse response) throws IOException {
		String msg = "NO";
		int index = jdcbqglService.add(allmsg);
		if (index > 0) {
			msg = "YES";
			response.getWriter().write(msg);
		} else {
			msg = "NO";
			response.getWriter().write(msg);
		}
	}
	//图片提交-机动车近照
	@RequestMapping(value ="addJdcjz", produces = {"text/html;charset=UTF-8"}) 
	@ResponseBody	
	public void addJdcjz(@RequestParam("jdcjztjdz") MultipartFile jdcjztjdz ,HttpServletRequest request, HttpServletResponse response) throws IOException{
		//清空upload文件夹下的所有文件
		TTpscUtil.deleteFile(request);
		String localFilePath = request.getSession().getServletContext().getRealPath("/upload");
		//获取配置文件
		ResourceBundle resource = ResourceBundle.getBundle("jdbc");
		String path = resource.getString("bqtpdz");
		//图片存指定位置
		String pathName=TTpscUtil.uploadFile(jdcjztjdz, request,path);
		String ytpdd=path+"\\"+pathName;
		String xztpname=UUID.randomUUID().toString();
		String xztpdd =localFilePath+"\\"+xztpname;
		TTpscUtil.Fztp(ytpdd, xztpdd);
		pathName = java.net.URLEncoder.encode(ytpdd+","+xztpname, "UTF-8");
		response.getWriter().write(pathName);
	}	
	// 删除
	@RequestMapping(value = "deleteJdcbqgl")
	@ResponseBody
	public boolean deleteJdcbq(String ids) {
		boolean flag = false;
		int index = jdcbqglService.delete(ids);
		if (index>0) {
			flag = true;
		}
		return flag;
	}	
	// 注销
	@RequestMapping(value = "zhuxiaoJdcbqgl")
	@ResponseBody
	public boolean zhuxiaoJdcbq(String ids,String hphm) {
		boolean flag = false;
		TAllmsg allmsg = jdcbqglService.findById(ids);
		int index = jdcbqglService.addZx(allmsg);
		if (index>0) {
			System.out.println("idas=="+ids);
			int in= jdcbqglService.delete(ids);
			if (in>0) {
				flag = true;
			}
		}
		return flag;
	}	
	// 修改
	@RequestMapping(value = "updateJdcbqgl", produces = { "text/html;charset=UTF-8" })
	@ResponseBody
	public String updateJdcbq(TAllmsg allmsg,String yhphm,String xhphm,String ytpdz) throws UnsupportedEncodingException {
		String dz = java.net.URLDecoder.decode(ytpdz, "UTF-8");
		if (!dz.equals(allmsg.getSctpdz())) {
			 File f = new File(dz);
		     f.delete();
		}
		String yhp = java.net.URLDecoder.decode(yhphm, "UTF-8");
		allmsg.setHphm(yhp);
		String xhp = java.net.URLDecoder.decode(xhphm, "UTF-8");
		allmsg.setZhi(xhp);
		String flag = "false";
		int index = jdcbqglService.update(allmsg);
		if (index > 0) {
			flag = "true";
		} else {
			flag = "false";
		}
		return flag;
	}
	//点击查看详细信息
	@RequestMapping("/findJdcbqglXx")
	public String findJdcbqXx(@RequestParam("id") String id ,String hphm, Model model,HttpServletRequest request) throws UnsupportedEncodingException {
		String hp = java.net.URLDecoder.decode(hphm, "UTF-8");
		TAllmsg allmsg = jdcbqglService.findByIdAndHphm(id, hp);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (null!=allmsg) {
			if (null!=allmsg.getSctpdz()) {
				//复制图片到服务器
				//清空upload文件夹下的所有文件
				TTpscUtil.deleteFile(request);
				String localFilePath = request.getSession().getServletContext().getRealPath("/upload");
				String pathName=UUID.randomUUID().toString()+".jpg";
				//System.out.println("原图片="+jsrk.getSctpdz());
				//System.out.println("现图片="+localFilePath+"\\"+pathName);
				TTpscUtil.Fztp(allmsg.getSctpdz(), localFilePath+"\\"+pathName);
				allmsg.setSctpdz(pathName);
			}
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
			if (null!=allmsg.getYxjzrq()) {
				try {
					long dqsj = new Date().getTime();
					long yxsj = sdf.parse(allmsg.getYxjzrq()).getTime();
					if (dqsj >= yxsj) {
						allmsg.setBqzt("失效");
					}else if (dqsj < yxsj){
						allmsg.setBqzt("正常");
					}else {
						allmsg.setBqzt("有效期异常");
					}
				} catch (ParseException e) {
					e.printStackTrace();
					allmsg.setBqzt("有效期异常");
				}
			}else {
				allmsg.setBqzt("有效期异常");
			}
		}
		model.addAttribute("Allmsg", allmsg);
		return "/bqgl/jdcbqgl_show";
	}		
	
	// 导出数据到Excel
	@ResponseBody
	@RequestMapping(value = "/outExcel", produces = { "text/html;charset=UTF-8" })
	public void outExcel(PulicCxtj pulicCxtj, HttpServletResponse response) throws IOException {
		String fileName = "机动车数据备份";
		String[] columNames = { "标签id", "号牌号码", "号牌种类", "车辆品牌", "车辆型号", "所有人", "车身颜色", "发动机号", "车辆识别代码", 
				"身份证号", "初次登记日期", "发牌日期", "登记人员", "联系电话","车辆类型", "发签日期", "销签日期", "标签状态", "ID", "民族", 
				"所属单位", "金额", "内外部", "可通行地点", "所属科室" ,"有效截止日期","副车牌号码","领导","上传图片名称"};
		String[] keys = { "bqid", "hphm", "hpzl", "clpp", "clxh", "syr", "csys", "fdjh", "clsbdh", "sfzmhm", "ccdjrq", "fprq", "djry", "lxdh",
				"cllx", "fqrq", "xqrq", "bqzt", "id", "mz", "ssdw", "je", "nwb", "ktxdd", "ssks" ,"yxjzrq","fcphm","ld","sctpdz"};
		List<Map<String, Object>> list = jdcbqglService.outExcel(pulicCxtj);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			ExcelUtil.createWorkBook(list, keys, columNames,"机动车数据备份").write(os);
			byte[] content = os.toByteArray();
			InputStream is = new ByteArrayInputStream(content);
			// 设置response参数，可以打开下载页面
			response.reset();
			response.setContentType("application/vnd.ms-excel;charset=utf-8");
			response.setHeader("Content-Disposition", "attachment;filename=" + new String((fileName + ".xls").getBytes(), "iso-8859-1"));
			ServletOutputStream out = response.getOutputStream();
			bis = new BufferedInputStream(is);
			bos = new BufferedOutputStream(out);
			byte[] buff = new byte[1024];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (final IOException e) {
			e.printStackTrace();
		} finally {
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}	
	
	// 导出数据到Excel
	//@ResponseBody
	@RequestMapping(value = "/outLodop", produces = { "text/html;charset=UTF-8" })
	public String outLodop(Model model,PulicCxtj pulicCxtj, HttpServletResponse response) throws IOException {
		System.out.println("00000000000000000000000");
		//List<Map<String, Object>> list = jdcbqglService.outExcel(pulicCxtj);
		pulicCxtj.setPage(1);
		pulicCxtj.setRows(10);
		List<TAllmsg> list = jdcbqglService.findAllFy(pulicCxtj);
		model.addAttribute("Lodop_jdcsj", list);
		return "/bqgl/Lodop_jdcsj_htm";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
