package com.zlkj.visitor.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import com.zlkj.visitor.dto.FtpBean;

public class FtpUtil {

	public static byte[] downloads(FtpBean ftp) throws IOException {
		byte[] data = null;
		String fileData = "";
		String filename = ftp.getFileName();
		String remotePath = "";
		boolean exist = false;
		OutputStream os = null;
		InputStream is = null;
		File localFile = null;
		FTPClient otherClient = null;
		ByteArrayOutputStream swapStream;
		try {
			otherClient = getClient(ftp.getIp(), ftp.getPort(), ftp.getUsername(), ftp.getPassword());
			otherClient.setControlEncoding("UTF-8"); // 添上这句设置编码就没中文乱码了
			otherClient.setFileType(FTP.BINARY_FILE_TYPE);
			String fpath = new String(ftp.getFtpPath().getBytes("UTF-8"), "iso-8859-1");
			FTPFile[] files = otherClient.listFiles(fpath);
			FTPFile file = new FTPFile();
			for (int j = 0; j < files.length; j++) {
				file = files[j];
				String fileNames = new String(file.getName().getBytes("UTF-8"), "utf-8");
				if (fileNames.contains(filename)) {
					// logger.info("【查找到目标文件:"+ files[j]+" 】");
					exist = true;
					remotePath = ftp.getFtpPath() + filename;
					is = otherClient.retrieveFileStream(new String(remotePath.getBytes("UTF-8"), "iso-8859-1"));// 获取文件流
					if (is != null) {

						byte[] buffer = new byte[5 * 1024];
						swapStream = new ByteArrayOutputStream();
						int bytesRead = 0;
						while ((bytesRead = is.read(buffer)) != -1) {
							// os.write(buffer, 0, bytesRead);//将文件发送到客户端
							swapStream.write(buffer, 0, bytesRead);
						}
						data = swapStream.toByteArray();
						swapStream.close();

						break;
					} else {
						// logger.info("【文件内容为空,请联系管理员】");
					}
				}
			}
			if (!exist) {
				// logger.info("【目标文件不存在】");
			} else {
				// logger.info("【文件内容："+fileData+" 】");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			// logger.error(e.toString()+"@line:"+e.getStackTrace()[0].getLineNumber());
		} catch (IOException e) {
			e.printStackTrace();
			// logger.error(e.toString()+"@line:"+e.getStackTrace()[0].getLineNumber());
		} catch (Exception e) {
			e.printStackTrace();
			// logger.error(e.toString()+"@line:"+e.getStackTrace()[0].getLineNumber());
		} finally {
			try {
				boolean bl = otherClient.logout();
				otherClient.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException("关闭FTP连接发生异常！", e);
			}

		}
		return data;
	}

	public static FTPClient getClient(String ip, int port, String name, String pwd) {
		FTPClient ftp = null;
		try {
			ftp = new FTPClient();
			int reply;
			ftp.connect(ip, port);
			ftp.setControlEncoding("UTF-8");
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				ftp = null;
			}
			if (!ftp.login(name, pwd)) {
				ftp.logout();
				ftp = null;
			}
		} catch (SocketException e) {
			e.printStackTrace();
			// logger.error(e.toString()+"@line:"+e.getStackTrace()[0].getLineNumber());
		} catch (IOException e) {
			e.printStackTrace();
			// logger.error(e.toString()+"@line:"+e.getStackTrace()[0].getLineNumber());
		}
		return ftp;
	}

	/** 文件下载 从客服FTP下载到本地 */
	public static String download(FtpBean ftp) throws IOException {
		String fileData = "";
		String filename = ftp.getFileName();
		String remotePath = "";
		boolean exist = false;
		OutputStream os = null;
		InputStream is = null;
		File localFile = null;
		FTPClient otherClient = null;
		try {
			otherClient = getClient(ftp.getIp(), ftp.getPort(), ftp.getUsername(), ftp.getPassword());
			otherClient.setControlEncoding("GBK"); // 添上这句设置编码就没中文乱码了
			otherClient.setFileType(FTP.BINARY_FILE_TYPE);
			String fpath = new String(ftp.getFtpPath().getBytes("GBK"), "iso-8859-1");
			// String fpath= new String("//");
			FTPFile[] files = otherClient.listFiles(fpath);
			FTPFile file = new FTPFile();
			for (int j = 0; j < files.length; j++) {
				file = files[j];
				String fileNames = new String(file.getName().getBytes("GBK"), "utf-8");
				if (fileNames.contains(filename)) {
					// logger.info("【查找到目标文件:"+ files[j]+" 】");
					exist = true;
					remotePath = ftp.getFtpPath() + filename;
					is = otherClient.retrieveFileStream(new String(remotePath.getBytes("GBK"), "iso-8859-1"));// 获取文件流
					if (is != null) {
						String filePath = ftp.getLocalPath() + "/" + filename;
						// logger.info("【filePath："+filePath+"】");
						localFile = new File(filePath);
						os = new FileOutputStream(localFile);
						int bytesRead = 0;
						byte[] buffer = new byte[5 * 1024];
						while ((bytesRead = is.read(buffer)) != -1) {
							os.write(buffer, 0, bytesRead);// 将文件发送到客户端
						}
						BufferedReader in = new BufferedReader(new FileReader(localFile));
						StringBuffer sb = new StringBuffer();
						String str;
						while ((str = in.readLine()) != null) {
							sb.append(str);
						}
						in.close();
						fileData = sb.toString();
						// logger.info("【 "+filename+" 文件下载成功】");
						break;
					} else {
						// logger.info("【文件内容为空,请联系管理员】");
					}
				}
			}
			if (!exist) {
				// logger.info("【目标文件不存在】");
			} else {
				// logger.info("【文件内容："+fileData+" 】");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			// logger.error(e.toString()+"@line:"+e.getStackTrace()[0].getLineNumber());
		} catch (IOException e) {
			e.printStackTrace();
			// logger.error(e.toString()+"@line:"+e.getStackTrace()[0].getLineNumber());
		} catch (Exception e) {
			e.printStackTrace();
			// logger.error(e.toString()+"@line:"+e.getStackTrace()[0].getLineNumber());
		} finally {
			if (os != null) {
				os.close();
			}
			if (exist) {
				// otherClient.deleteFile(new
				// String(remotePath.getBytes("UTF-8"), "iso-8859-1"));//删除远程文件
				// boolean mark=deleteFile(localFile);//删除本地文件
				// if(mark){
				// System.out.println("【删除文件成功】");
				// logger.info("【删除文件成功】");
				// }
			}
			otherClient.disconnect();
		}
		return fileData;
	}
}
