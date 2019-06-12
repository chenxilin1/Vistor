package com.zlkj.visitor.web.ljq;

import java.util.Date;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.zlkj.visitor.entity.TRzgl;
import com.zlkj.visitor.service.TRzglService;
import com.zlkj.visitor.util.TPublicUtil;

/**
 * 拦截器 日志管理 写入数据库
 * @author LYW 
 *  创建时间：2017-9-15 上午9:52:58
 *
 */
//@Component("/")
public class LogInterceptor implements HandlerInterceptor{
	@Autowired
	private TRzglService rzglService;

	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {
		
	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception {
		
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
		
		String czcd="";
		String cznr="";
		TRzgl rzgl= new TRzgl();
		System.out.println("路径是"+request.getRequestURL());
		if (request.getRequestURL().indexOf("toIndex")>-1) {
			czcd="登录";
			cznr="系统";
		}else if (request.getRequestURL().indexOf("outlog")>-1) {
			czcd="正常退出";
			cznr="登录";
		}else if (request.getRequestURL().indexOf("loginout")>-1) {
			//czcd="退出";
			//cznr="登录";
		}else if (request.getRequestURL().indexOf("updatePwd_yh")>-1) {
			czcd="修改";
			cznr="密码";
		}else {
			if (request.getRequestURL().indexOf("xtgl")>-1) {
				czcd="系统管理";
				if (request.getRequestURL().indexOf("User")>-1 || request.getRequestURL().indexOf("YHGL")>-1) {
					czcd+="-用户管理";
				}else if (request.getRequestURL().indexOf("Ppms")>-1 || request.getRequestURL().indexOf("PPMSGL")>-1) {
					czcd+="-匹配模式管理";
				}else if (request.getRequestURL().indexOf("Ddwh")>-1 || request.getRequestURL().indexOf("DDGLWH")>-1) {
					czcd+="-地点维护";
				}else if (request.getRequestURL().indexOf("Rzgl")>-1 || request.getRequestURL().indexOf("RZGL")>-1) {
					czcd+="-日志管理";
				}
			}else if (request.getRequestURL().indexOf("fkgl")>-1) {
				czcd="访客管理";
				if (request.getRequestURL().indexOf("Fkxx")>-1 || request.getRequestURL().indexOf("FKXX")>-1) {
					czcd+="-访客信息";
				}
			}else if (request.getRequestURL().indexOf("bqgl")>-1) {
				czcd="标签管理";
				if (request.getRequestURL().indexOf("Jdcbqgl")>-1 || request.getRequestURL().indexOf("JDCBQGL")>-1) {
					czcd+="-机动车管理";
				}else if (request.getRequestURL().indexOf("Jsrbqgl")>-1 || request.getRequestURL().indexOf("JSRBQGL")>-1) {
					czcd+="-驾驶人管理";
				}else if (request.getRequestURL().indexOf("SJPLGL")>-1) {
					czcd+="-数据批量管理";
					
				/*}else if (request.getRequestURL().indexOf("jdcsj")>-1 || request.getRequestURL().indexOf("Ddwh")>-1) {
					czcd+="下数据批量管理";
				}else if (request.getRequestURL().indexOf("jsrsj")>-1 || request.getRequestURL().indexOf("JSRSJ")>-1) {
					czcd+="下数据批量管理";
				}else if (request.getRequestURL().indexOf("jdcbq")>-1 || request.getRequestURL().indexOf("JDCBQ")>-1) {
					czcd+="下数据批量管理";
				}else if (request.getRequestURL().indexOf("jsrbq")>-1 || request.getRequestURL().indexOf("JSRBQ")>-1) {
					czcd+="下数据批量管理";*/
				}
			}else if (request.getRequestURL().indexOf("sjcx")>-1) {
				czcd="数据查询";
				if (request.getRequestURL().indexOf("Cltxqk")>-1 || request.getRequestURL().indexOf("CLTXQK")>-1) {
					czcd+="-处理通行情况查询";
				}
			}else if (request.getRequestURL().indexOf("sjtj")>-1) {
				czcd="数据统计";
				if (request.getRequestURL().indexOf("Cljl")>-1 || request.getRequestURL().indexOf("CLJLTJ")>-1) {
					czcd+="-车辆记录统计";
				}else if (request.getRequestURL().indexOf("Fyqk")>-1 || request.getRequestURL().indexOf("FYQKTJ")>-1) {
					czcd+="-收费情况统计";
				}
			}else if (request.getRequestURL().indexOf("sxgl")>-1) {
				czcd="摄像管理";
				if (request.getRequestURL().indexOf("CKSX")>-1) {
					czcd+="-查看摄像设备";
				}else if (request.getRequestURL().indexOf("xjxx")>-1) {
					czcd+="-相机信息";
				}else if (request.getRequestURL().indexOf("Ysd")>-1) {
					czcd+="-预置点信息";
				}
			}
			
			if (request.getRequestURL().indexOf("add")>-1) {
				cznr="-添加数据";
			}else if(request.getRequestURL().indexOf("delete")>-1){
				cznr="-删除数据";
			}else if(request.getRequestURL().indexOf("update")>-1){
				cznr="-修改数据";
			}else if(request.getRequestURL().indexOf("csh")>-1){
				cznr="-同步数据";
			}else if(request.getRequestURL().indexOf("bf")>-1){
				if(request.getRequestURL().indexOf("jdcsj")>-1 && request.getRequestURL().indexOf("Count")==-1){
					cznr+="-备份机动车数据";
				}else if (request.getRequestURL().indexOf("jsrsj")>-1 && request.getRequestURL().indexOf("Count")==-1) {
					cznr+="-备份驾驶人数据";
				}else if (request.getRequestURL().indexOf("jdcbq")>-1 && request.getRequestURL().indexOf("Count")==-1) {
					cznr+="-备份机动车标签";
				}else if (request.getRequestURL().indexOf("jsrbq")>-1 && request.getRequestURL().indexOf("Count")==-1) {
					cznr+="-备份驾驶人标签";
				}else if (request.getRequestURL().indexOf("mbjdcxx")>-1) {
					cznr+="-备份机动车采集模板";
				}else if (request.getRequestURL().indexOf("mbjsrxx")>-1) {
					cznr+="-备份驾驶人采集模板";
				}
			}else if(request.getRequestURL().indexOf("Find")>-1){
				cznr="-查询数据";
			}else if(request.getRequestURL().indexOf("TJt")>-1){
				cznr="-统计功能";
			}else if (request.getRequestURL().indexOf("Xx")>-1) {
				cznr+="-查询详细信息";
			}
		}
		
		String userName = (String) request.getSession().getAttribute("username");
		rzgl.setYhm(userName);
		rzgl.setCzcd(czcd);
		rzgl.setCznr(cznr);
		String czsj = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		rzgl.setCzsj(czsj);
		String czip=TPublicUtil.getIpAddr(request);
		rzgl.setCzip(czip);
		if (!"".equals(rzgl.getCzcd()) && !"".equals(rzgl.getCznr())) {
			rzgl.setCznr(rzgl.getCzcd()+rzgl.getCznr());
			//Resource 获取资源包文件
			ResourceBundle resource = ResourceBundle.getBundle("otherDevices");
			String rzSwitch = resource.getString("rzSwitch");
			if ("0".equals(rzSwitch)) {
				rzglService.addrz(rzgl);
			}
		}
		
		return true;
	}

}
