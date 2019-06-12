package com.zlkj.visitor.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
public class TPublicUtil {

	public void Csysdm2mc() {

	}

	/**
	 * 获取本机Mac地址
	 * 
	 * @param ia
	 * @return
	 * @throws SocketException
	 */
	public static String getLocalMac(InetAddress ia) throws SocketException {
		// 获取网卡，获取地址
		byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
		System.out.println("mac数组长度：" + mac.length);
		StringBuffer sb = new StringBuffer("");
		for (int i = 0; i < mac.length; i++) {
			if (i != 0) {
				sb.append("-");
			}
			// 字节转换为整数
			int temp = mac[i] & 0xff;
			String str = Integer.toHexString(temp);
			System.out.println("每8位:" + str);
			if (str.length() == 1) {
				sb.append("0" + str);
			} else {
				sb.append(str);
			}
		}
		System.out.println("本机MAC地址:" + sb.toString().toUpperCase());
		return sb.toString().toUpperCase();
	}
	/*
	 *获取浏览器端真实IP 
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	//更新 properties 文件
	
	public static void updateProperties(String keyname,String keyvalue,HttpServletRequest request)throws IOException {          
		String path = request.getSession().getServletContext().getRealPath("/WEB-INF")+"/classes/jdbc.properties";
		System.out.println(path);
		
		Properties pps = new Properties();
        InputStream in = new FileInputStream(path);
        //从输入流中读取属性列表（键和元素对） 
        pps.load(in);
        //调用 Hashtable 的方法 put。使用 getProperty 方法提供并行性。  
        //强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。
        OutputStream out = new FileOutputStream(path);
        pps.setProperty(keyname, keyvalue);
        //pps.setProperty("001", "我不知道");
        //以适合使用 load 方法加载到 Properties 表中的格式，  
        //将此 Properties 表中的属性列表（键和元素对）写入输出流  
        pps.store(out, "Update " + keyname + " name");
    
    }
	public static int getConnection(String url){
		int i=0;
        try {
            Properties prop=new Properties();
            prop.load(TPublicUtil.class.getClassLoader().getResourceAsStream("jdbc.properties"));
            String className=prop.getProperty("jdbc.driver");
            System.out.println(className);
            //加载注册驱动
            Class.forName(className);
            //获取连接对象
            System.out.println("url=="+url);
            java.sql.Connection conn= DriverManager.getConnection(url, "root", "zlkj");
            i=1;
        }  catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
        	System.out.println("ip错误");
        	i=0;
            //e.printStackTrace();
        }

        return i;
    }
	/**
	 * 去除字符串中的空格、回车、换行符、制表符
	 * @param str
	 * @return
	 */
	public static String replaceBlank(String str) {
        String dest = "";
        if (str!=null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }
	/**
	 * 把时间有 年月日的换成有 - 的时间
	 * @param sj
	 * @return
	 */
	public static String FatData(String sj){
		sj = sj.replace("年", "-");
		sj = sj.replace("月", "-");
		sj = sj.replace("日", " ");
		sj = sj.replace("时", ":");
		return sj;
	}
	//数据库查出来的不全的时间拼接成全的
	public static String DataFat2KsJs(String kssj,String jssj,String sjlx,String ksorjs){
		try {
			if ("1".equals(sjlx)) {
				kssj=kssj+"-01-01 00:00:00";
				jssj=jssj+"-12-31 24:59:59";
			}else if ("2".equals(sjlx)) {
				kssj=kssj+"-01 00:00:00";
				jssj=jssj+"-31 24:59:59";
			}else if ("3".equals(sjlx)) {
				kssj=kssj+" 00:00:00";
				jssj=jssj+" 24:59:59";
			}else if ("4".equals(sjlx)) {
				kssj=kssj+":00:00";
				jssj=jssj+":59:59";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if ("开始".equals(ksorjs)) {
			return kssj;
		}else {
			return jssj;
		}
	}
	/**
	 * 格式化时间 - 条件的时间--》不全的拼接成全的时间类型
	 * @param kssj
	 * @param jssj
	 * @param sjlx
	 * @param ksOrJs
	 * @return
	 */
	public static String DataTjFat(String kssj,String jssj,String sjlx,String ksOrJs){
		try {
			if ("1".equals(sjlx)) {
				//kssj=tjtCsDto.getKssj().substring(0,4)+"-01-01 00:00:00";
				kssj=kssj.substring(0,4)+"-01-01 00:00:00";
				jssj=jssj.substring(0,4)+"-12-31 24:59:59";
			}else if ("2".equals(sjlx)) {
				//kssj=tjtCsDto.getKssj().substring(0,7)+"-01 00:00:00";
				kssj=kssj.substring(0,7)+"-01 00:00:00";
				jssj=jssj.substring(0,7)+"-31 24:59:59";
			}else if ("3".equals(sjlx)) {
				//kssj=tjtCsDto.getKssj().substring(0,10)+" 00:00:00";
				kssj=kssj.substring(0,10)+" 00:00:00";
				jssj=jssj.substring(0,10)+" 24:59:59";
			}else if ("4".equals(sjlx)) {
				//kssj=tjtCsDto.getKssj().substring(0,13)+":00:00";
				kssj=kssj.substring(0,13)+":00:00";
				jssj=jssj.substring(0,13)+":59:59";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if ("开始时间".equals(ksOrJs)) {
			return kssj;
		}else {
			return jssj;
		}
	}
    /**
     * 将一个日期时分秒转换成一个字符串
     * @param date
     * @return
     */
    public  static String Date2StringMiao(Date date){
        SimpleDateFormat zhuanhuan= new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return zhuanhuan.format(date).replace("/", "-");
    }
    /**
     * 将一个字符串转换成日期时分秒
     * @param date
     * @return
     */
    public static Date String2DateMiao(String date){
        SimpleDateFormat zhuanhuan= new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        try {
            return zhuanhuan.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
	
	
	
	
}
