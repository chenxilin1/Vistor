package com.zlkj.visitor.web.xtgl;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zlkj.visitor.entity.TRzgl;
import com.zlkj.visitor.service.TRzglService;

/**
 * 拦截器编写日志
 * @author LYW 
 *  创建时间：2017-7-31 下午1:32:15
 *
 */
@Controller
@RequestMapping("/rzgl")
public class RzbxController {
	@Autowired
	private TRzglService rzglService;
	
	@RequestMapping(value = "/rzbx_xtwh")
	public void rzbx_xtwh(HttpServletRequest request) throws UnknownHostException{
		String fwdd=String.valueOf(request.getRequestURL());//访问地点
		if (fwdd.indexOf("xtgl/YHGL")>-1) {//用户管理
			TRzgl rzgl = new TRzgl();
			InetAddress ia=null;
			try {
				ia=ia.getLocalHost();
				//String localname=ia.getHostName();
				String localip=ia.getHostAddress();
				//System.out.println("本机名称是："+ localname);
				//System.out.println("本机的ip是 ："+localip);
				rzgl.setCzip(localip);
			} catch (Exception e) {
				e.printStackTrace();
			}
			rzgl.setCznr("查看系统管理/用户管理的信息");
			HttpSession session = request.getSession();
			String userName = (String) session.getAttribute("username");
			rzgl.setYhm(userName);
			String xtsj = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			rzgl.setCzsj(xtsj);
			rzgl.setCzbz(null);
			System.out.println(rzgl);
			rzglService.addrz(rzgl);
		}
		
		
		
		
	}
}
