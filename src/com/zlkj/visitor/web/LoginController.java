package com.zlkj.visitor.web;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zlkj.visitor.dto.MacOrIpDto;
import com.zlkj.visitor.dto.MenuDto;
import com.zlkj.visitor.dto.UserAndPwd;
import com.zlkj.visitor.entity.TUser;
import com.zlkj.visitor.service.TUserService;
import com.zlkj.visitor.util.Encrypt;
import com.zlkj.visitor.util.TPublicUtil;

/**
 * 登陆界面
 * @author LYW 
 *  创建时间：2017-4-10 上午10:31:23
 *
 */
@Controller
public class LoginController {
	private String yzm;
	@Autowired
	private TUserService tuserService;
	// 用户登陆
	@RequestMapping(value = "yzdl")
	public void login(HttpSession session, UserAndPwd userAndPwd, HttpServletResponse response) throws IOException {
		ResourceBundle resource = ResourceBundle.getBundle("jdbc");
		//配置文件Mac
		String pzmac = resource.getString("mac");
		//配置文件Mac的skd
		String macSDK = resource.getString("macSDK");
		System.out.println(macSDK);
		InetAddress ia = InetAddress.getLocalHost();
		//本机电脑Mac
		String xtMac = TPublicUtil.getLocalMac(ia);
		//---------------------------------------------
		//SDK正确 就不验证系统Mac地址
		if ("吕阳威".equals(macSDK)) {
			xtMac=pzmac;
		}
		//---------------------------------------------
		String msg = null;
		// 响应的格式
		response.setContentType("text/html;charset=utf-8");
		String un = userAndPwd.getYhm();
		TUser tuser=tuserService.findByyhm(un);
		
		if (!pzmac.equals(xtMac)) {
			msg = "FFDL";
			response.getWriter().write(msg);
		}else {
			if (tuser == null) {
				msg = "wuzh";
				response.getWriter().write(msg);
			} else {
				userAndPwd.setYhmm(Encrypt.e(userAndPwd.getYhmm()));
				TUser user = tuserService.findUserAndPwd(userAndPwd);
				if (user == null) {
					msg = "wumm";
					response.getWriter().write(msg);
				} else {
					msg = "YES";
					response.getWriter().write(msg);
					session.setAttribute("user", user);
					session.setAttribute("username", user.getYhm());
					session.setAttribute("isAdmin", tuser.getIsAdmin());
				}
			}
		}
	}
	// 跳转到后台
	@RequestMapping(value = "toIndex")
	public String toIndex() {
		return "index";
	}
	
	// 退出操作
	@RequestMapping(value = "loginout")
	public void loginout(HttpSession session, HttpServletResponse response, String username) throws IOException {
		// 清空用户 Session域中的所有信息
		session.invalidate();
		// 设置响应的内容
		String msg = "YES";
		response.getWriter().write(msg);
	}
	// 获取左侧菜单
	@RequestMapping(value = "queryAllMenu")
	@ResponseBody
	public List<MenuDto> queryAllMenu(HttpSession session) {
		TUser tuser = (TUser) session.getAttribute("user");
		String name = (String) session.getAttribute("username");
		if ("超级管理员".equals(name)){
			tuser.setYhqx("1,2,3,4,100,101,102,103,104,200,300,400");
			tuser.setYhm(name);
		}
		if (null == tuser) {
			return null;
		} else {
			List<MenuDto> listMenuDto = tuserService.queryCode(tuser.getYhqx());
			return listMenuDto;
		}
	}
	
	//绘制验证码
	@RequestMapping(value = "hzyzm")
	public void hzyzm(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws IOException {
		int width = 73;
		int height = 33;	
		// 搞出内存图片的对象BufferedImage，然后取画笔：开始画
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		Graphics g = image.getGraphics();
		// 填充背景
		g.setColor(Color.white);
		g.fillRect(0, 0, width, height);
		// 画边框
		g.setColor(Color.GRAY);
		g.drawRect(1, 1, width - 2, height - 2);
		// 画干扰线
		Random r = new Random();
		g.setColor(new Color(247, 10, 18));
		for (int i = 0; i < 2; i++) {
			g.drawLine(0, r.nextInt(height), r.nextInt(width), r.nextInt(height));
		}
		// 写随机验证码
		g.setColor(Color.RED);
		// 数字
		int x = 0;
		char codes[] = getCodes().toCharArray();
		for (int i = 0; i < codes.length; i++) {
			//g.drawString(codes[i] + "", 5 + x, 20);//------------------------------------login登录时启用
			g.drawString(codes[i] + "", 5 + x, 20);	//------------------------------------logins登录时启用
			x += 20;
		}
		// 把字符数组转换成字符串，复制给全局变量yzm
		yzm = new String(codes);
		// g.drawString(r.nextInt(10) + "", 10, 20);
		response.setContentType("image/jpeg");
		// 输出到页面上：ImageIO
		ImageIO.write(image, "jpg", response.getOutputStream());
		
	}

	public String getCodes() {
		Random ra = new Random();
		String code = "";
		for (int k = 0; k < 4; k++) {
			code = code + ra.nextInt(10);
		}
		// 打印验证码
		return code;
	}

	// 验证码校验
	@RequestMapping(value = "jyyzm")
	public void jyyzm(javax.servlet.http.HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pr = response.getWriter();
		pr.write(yzm);
	}	
	//退出
	@RequestMapping(value = "outlog")
	public String outlog(HttpServletRequest request,HttpServletResponse response) throws IOException {
		//response.getWriter().write("<script>window.top.location.href='${pageContext.request.contextPath}/login.jsp'</script>");
		return "outlog";
	}
	//修改密码
	@RequestMapping(value = "updatePwd_yh")
	public void updatePwd_yh(String password, String newpass, String user, HttpServletResponse response) throws IOException {
		// 响应的格式
		response.setContentType("text/html;charset=utf-8");
		String msg = "";
		TUser uList = tuserService.findByyhm(user);
		
		//System.out.println(uList.getYhmm());
		//System.out.println(Encrypt.e(password));
		
		if (!uList.getYhmm().equals(Encrypt.e(password))) {
			msg = "NO";
			response.getWriter().write(msg);
		} else {
			tuserService.updUser_mm(user, Encrypt.e(newpass));
			response.getWriter().write("YES");
		}

	}
	
	
}
