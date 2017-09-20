package com.taotao.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

import com.taotao.common.utils.FtpUtil;

public class TestFtp {
	@Test
	public void testFtpClinet() throws Exception{
		//创建ftpClinet对象
		FTPClient ftpClient=new FTPClient();
		//创建链接
		ftpClient.connect("192.168.1.106");
		//登录ftp服务器,使用用户名 密码
		ftpClient.login("ftpuser", "m123");
		//设置上传的路径
		ftpClient.changeWorkingDirectory("/home/ftpuser/www/images");
		//修改上传文件格式
		ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
		//上传文件 参数1:服务器端文件名 参数2:inputstream
		ftpClient.storeFile("hello1.jpg", new FileInputStream(new File("C:\\Users\\Administrator\\Desktop\\1.jpg")));
		//关闭链接
		ftpClient.logout();
	}
	@Test
	public void testFtpUtil() throws Exception{
		FtpUtil.uploadFile("192.168.1.106", 21, "ftpuser", "m123", "/home/ftpuser/www/images", "", "2.jpg", new FileInputStream(new File("C:\\Users\\Administrator\\Desktop\\1.jpg")));
	}
	
}
