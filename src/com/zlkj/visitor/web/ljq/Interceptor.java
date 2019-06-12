package com.zlkj.visitor.web.ljq;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 拦截器-只处理左侧菜单的拦截
 * @author LYW 
 *  创建时间：2017-7-27 上午9:41:57
 *
 */
@Component("/")
public class Interceptor implements HandlerInterceptor{

	
	public void afterCompletion(HttpServletRequest request,HttpServletResponse response, Object obj, Exception arg3) throws Exception {
		//System.out.println("后处理");
		/*if (request.getRequestURL().indexOf("xtgl")>-1) {
			//RzbxController.rzbx_xtwh(request);
			//request.getRequestDispatcher("/xtgl/rzbx_xtwh").forward(request, response);
			 RzglController rzglController= new RzglController();
			 rzglController.outlog(request,"系统管理");
		}*/
		//RzglController rzglController= new RzglController();
		//rzglController.outlog(request);
	}

	public void postHandle(HttpServletRequest request,HttpServletResponse response, Object obj, ModelAndView arg3) throws Exception {
		//System.out.println("返回处理");
	}
	/**
	 * preHandle方法是进行处理器拦截用的，顾名思义，该方法将在Controller处理之前进行调用，
	 * SpringMVC中的Interceptor拦截器是链式的，可以同时存在
	 * 多个Interceptor，然后SpringMVC会根据声明的前后顺序一个接一个的执行
	 * ，而且所有的Interceptor中的preHandle方法都会在 QUERY
	 * Controller方法调用之前调用。SpringMVC的这种Interceptor链式结构也是可以进行中断的
	 * ，这种中断方式是令preHandle的返 回值为false，当preHandle的返回值为false的时候整个请求就结束了。
	 */
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object obj) throws Exception {
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		String userName = (String) session.getAttribute("username");
		//System.out.println("拦截器");
		//System.out.println(userName);
		// 登录失效
		if (userName == null) {
			//request.setAttribute("errorMsg", "登录超速");
			//request.getRequestDispatcher("/outlog").forward(request, response);
            PrintWriter out = response.getWriter();
            StringBuilder builder = new StringBuilder();
            builder.append("<script type=\"text/javascript\" charset=\"UTF-8\">");
            builder.append("alert(\"页面过期,请重新登录\");");
            builder.append("window.top.location.href=\"");
            String basePath0 = String.valueOf(request.getRequestURL());
            String basePath1 = request.getRequestURI();
            String basePath2 = request.getContextPath();
            String dz = basePath0.replace(basePath1, basePath2);
            builder.append(dz+"/"+"login.jsp\";</script>");
            //System.out.println(dz+"/"+"login.jsp");
            
            out.print(builder.toString());
            out.close();
			return false;
		}else {
			//RzglController rzglController= new RzglController();
			//rzglController.outlog(request);
			
			return true;
		}
		//return true;
	}

	
}
