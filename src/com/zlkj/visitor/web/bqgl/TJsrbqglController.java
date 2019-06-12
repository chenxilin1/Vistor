package com.zlkj.visitor.web.bqgl;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;

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
import com.zlkj.visitor.entity.TJsrk;
import com.zlkj.visitor.service.DdwhService;
import com.zlkj.visitor.service.TJsrbqglService;
import com.zlkj.visitor.service.TUserService;
import com.zlkj.visitor.util.TTpscUtil;

/**
 * 驾驶人标签查询-管理
 * @author LYW 
 *  创建时间：2017-6-23 下午6:57:30
 *
 */

@Controller
@RequestMapping("/bqgl")
public class TJsrbqglController {

	@Autowired
	private TJsrbqglService jsrbqglService;
	@Autowired
	private DdwhService ddwhService;
	@Autowired
	private TUserService userService;
	
	@RequestMapping(value = "/FindJSRBQGL")
	public String FindJSRBQGL(Model model,HttpSession session) {
		List<Ddmc> ktxdd = ddwhService.findAll();
		model.addAttribute("ktxdd_jsrbq", ktxdd);
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
		model.addAttribute("ktxdd_jsrbq", ktxdd);*/
		return "/bqgl/jsrbqgl_list";
	}
	// 条件 显示
	@RequestMapping(value = "findJsrbqgl")
	@ResponseBody
	public DataGrid findJsrbq(PulicCxtj pulicCxtj,HttpSession session) {
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
		List<TJsrk> list = jsrbqglService.findAllFy(pulicCxtj);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (TJsrk tJsrk : list) {
			if (null!=tJsrk.getKtxdd()) {
				String[] dd=tJsrk.getKtxdd().split(",");
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
				tJsrk.setKtxdd(ddmc);
			}else {
				tJsrk.setKtxdd("");
			}
			if (null!=tJsrk.getSex()) {
				if ("0".equals(tJsrk.getSex())) {
					tJsrk.setSex("女");
				}else if ("1".equals(tJsrk.getSex())) {
					tJsrk.setSex("男");
				}
			}
			if (null!=tJsrk.getYxjzrq()) {
				try {
				long dqsj = new Date().getTime();
				long yxsj = sdf.parse(tJsrk.getYxjzrq()).getTime();
				if (dqsj >= yxsj) {
					tJsrk.setZt("失效");
				}else if (dqsj < yxsj){
					tJsrk.setZt("正常");
				}else {
					tJsrk.setZt("有效期异常");
				}
				
				} catch (ParseException e) {
					e.printStackTrace();
					tJsrk.setZt("有效期异常");
				}
			}else {
				tJsrk.setZt("有效期异常");
			}
			if (null!=tJsrk.getZxsj()) {
				tJsrk.setZt("注销");
			}
			if (null!=tJsrk.getDqsj()) {
				//tJsrk.setDqsj(sdf.parse(tJsrk.getYxjzrq()));
			}
			//System.out.println("到期时间="+tJsrk.getDqsj());
			//System.out.println("有效时间="+tJsrk.getYxjzrq());
		}
		long totla = jsrbqglService.findAllFyCount(pulicCxtj);
		dg.setTotal(Integer.parseInt(totla + ""));
		dg.setRows(list);
		return dg;
	}	
	// 注销
	@RequestMapping(value = "deleteJsrbqgl")
	@ResponseBody
	public boolean deleteJsrbq(String ids) {
		boolean flag = false;
		int index= jsrbqglService.delete(ids);
		if (index>0) {
			flag = true;
		}
		return flag;
	}	
	// 注销
	@RequestMapping(value = "zhuxiaoJsrbqgl")
	@ResponseBody
	public boolean zhuxiaoJdcbq(String ids) {
		boolean flag = false;
		TJsrk jsrk = jsrbqglService.findById(ids);
		int index= jsrbqglService.addZc(jsrk);
		if (index>0) {
			int in= jsrbqglService.delete(ids);
			if (in>0) {
				flag = true;
			}
		}
		return flag;
	}	
	//图片提交-人员近照
	@RequestMapping(value ="addJsrjz", produces = {"text/html;charset=UTF-8"}) 
	@ResponseBody	
	public void addJsrjz(@RequestParam("jsrjztjdz") MultipartFile jsrjztjdz ,HttpServletRequest request, HttpServletResponse response) throws IOException{
		//清空upload文件夹下的所有文件
		TTpscUtil.deleteFile(request);
		String localFilePath = request.getSession().getServletContext().getRealPath("/upload");
		//获取配置文件
		ResourceBundle resource = ResourceBundle.getBundle("jdbc");
		String path = resource.getString("bqtpdz");
		//图片存指定位置
		String pathName=TTpscUtil.uploadFile(jsrjztjdz, request,path);
		String ytpdd=path+"\\"+pathName;
		String xztpname=UUID.randomUUID().toString();
		String xztpdd =localFilePath+"\\"+xztpname;
		TTpscUtil.Fztp(ytpdd, xztpdd);
		
		pathName = java.net.URLEncoder.encode(ytpdd+","+xztpname, "UTF-8");
		response.getWriter().write(pathName);
	}
	// 添加
	@ResponseBody
	@RequestMapping(value = "/addJsrbqgl", produces = { "text/html;charset=UTF-8" })
	public void addJsrbq(TJsrk jsrk, HttpServletResponse response) throws IOException {
		String msg = "NO";
		int index = jsrbqglService.add(jsrk);
		if (index > 0) {
			msg = "YES";
			response.getWriter().write(msg);
		} else {
			msg = "NO";
			response.getWriter().write(msg);
		}
	}
	// 修改
	@RequestMapping(value = "updateJsrbqgl", produces = { "text/html;charset=UTF-8" })
	@ResponseBody
	public String updateJsrbq(TJsrk jsrk,Integer id,String ytpdz,HttpServletRequest request) throws UnsupportedEncodingException {
		String dz = java.net.URLDecoder.decode(ytpdz, "UTF-8");
		if (!dz.equals(jsrk.getSctpdz())) {
			 File f = new File(dz);
		     f.delete();
		}
		jsrk.setId(id);
		String flag = "false";
		int index = jsrbqglService.update(jsrk);
		if (index > 0) {
			flag = "true";
		} else {
			flag = "false";
		}
		return flag;
	}
	
	//点击查看详细信息
	@RequestMapping("/findJsrbqglXx")
	public String findJsrbqXx(@RequestParam("id") String id,String sfzh, Model model,HttpServletRequest request) throws UnsupportedEncodingException {
		String sfzhm = java.net.URLDecoder.decode(sfzh, "UTF-8");
		TJsrk jsrk = jsrbqglService.findByIdAndSfzh(id, sfzhm);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (null!=jsrk) {
			if (null!=jsrk.getSctpdz()) {
				//复制图片到服务器
				//清空upload文件夹下的所有文件
				TTpscUtil.deleteFile(request);
				String localFilePath = request.getSession().getServletContext().getRealPath("/upload");
				String pathName=UUID.randomUUID().toString()+".jpg";
				//System.out.println("原图片="+jsrk.getSctpdz());
				//System.out.println("现图片="+localFilePath+"\\"+pathName);
				TTpscUtil.Fztp(jsrk.getSctpdz(), localFilePath+"\\"+pathName);
				jsrk.setSctpdz(pathName);
			}
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
			if (null!=jsrk.getYxjzrq()) {
				try {
				long dqsj = new Date().getTime();
				long yxsj = sdf.parse(jsrk.getYxjzrq()).getTime();
				if (dqsj >= yxsj) {
					jsrk.setZt("失效");
				}else if (dqsj < yxsj){
					jsrk.setZt("正常");
				}else {
					jsrk.setZt("有效期异常");
				}
				
				} catch (ParseException e) {
					e.printStackTrace();
					jsrk.setZt("有效期异常");
				}
			}else {
				jsrk.setZt("有效期异常");
			}
		}
		model.addAttribute("Jsrk", jsrk);
		return "/bqgl/jsrbqgl_show";
	}	

	
	
	
	
	
	
	
}
