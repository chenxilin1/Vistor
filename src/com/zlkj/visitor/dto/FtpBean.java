package com.zlkj.visitor.dto;

public class FtpBean {

	 public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFtpPath() {
		return ftpPath;
	}

	public void setFtpPath(String ftpPath) {
		this.ftpPath = ftpPath;
	}

	public String getLocalPath() {
		return localPath;
	}

	public void setLocalPath(String localPath) {
		this.localPath = localPath;
	}

	public String getLoadPath() {
		return loadPath;
	}

	public void setLoadPath(String loadPath) {
		this.loadPath = loadPath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getIp() {
		return ip;
	}

	private String ip; // Ftp服务器的IP地址

	    private int port; // FtP端口号

	    private String username; // Ftp服务器登录名

	    private String password; // Ftp服务器登录密码

	    private String ftpPath; // FTP文件放置路径

	    private String localPath; // 文件本地路径

	    private String loadPath; // 下载文件放置路径

	    private String fileName; // 文件名称

		@Override
		public String toString() {
			return "FtpBean [ip=" + ip + ", port=" + port + ", username=" + username + ", password=" + password + ", ftpPath=" + ftpPath
					+ ", localPath=" + localPath + ", loadPath=" + loadPath + ", fileName=" + fileName + "]";
		}

	  
}
