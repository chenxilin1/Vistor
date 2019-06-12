package com.zlkj.visitor.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public class TTpscUtil {

	// 文件上传
	public static String uploadFile(MultipartFile file, HttpServletRequest request, String path) throws IOException {
		File dir = new File(path);
		judeDirExists(dir);

		String fileName = file.getOriginalFilename();
		
		//fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
		//fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
		File tempFile = new File(path, new Date().getTime() + String.valueOf("") + fileName);
		if (!tempFile.getParentFile().exists()) {
			tempFile.getParentFile().mkdir();
		}
		if (!tempFile.exists()) {
			tempFile.createNewFile();
		}
		file.transferTo(tempFile);
		return tempFile.getName();
	}

	// 删除指定文件夹下所有文件
	public static void deleteFile(HttpServletRequest request) {
		try {
			String path = request.getSession().getServletContext().getRealPath("/upload");
			delAllFile(path); // 删除完里面所有内容
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// param path 文件夹完整绝对路径
	public static boolean delAllFile(String path) {
		boolean flag = false;
		File file = new File(path);
		if (!file.exists()) {
			return flag;
		}
		if (!file.isDirectory()) {
			return flag;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件

				flag = true;
			}
		}
		return flag;
	}

	// 判断文件夹是否存在
	public static void judeDirExists(File file) {
		if (!file.exists()) {
			if (file.isDirectory()) {
				System.out.println("dir存在");
			} else {
				System.out.println("相同的名称文件存在，不能创建dir");
			}
		} else {
			System.out.println("不存在，创建它…");
			file.mkdir();
		}
	}

	// -------------------------------------------------------------------------------------
	//图片复制
	public static void Fztp(String ytpdd,String xztpdd) {
		FileOutputStream fos = null;
		FileInputStream fis = null;
		try {
			fos = new FileOutputStream(xztpdd);// 复制文件
			fis = new FileInputStream(ytpdd);// 源文件
			byte[] buf = new byte[1024];// 缓冲区
			int len = 0;
			while ((len = fis.read(buf)) != -1) {
				fos.write(buf, 0, len);// 复制
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fis != null)
					fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if (fos != null)
					fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
