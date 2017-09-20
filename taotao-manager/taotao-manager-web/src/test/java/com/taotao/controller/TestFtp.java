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
		//����ftpClinet����
		FTPClient ftpClient=new FTPClient();
		//��������
		ftpClient.connect("192.168.1.106");
		//��¼ftp������,ʹ���û��� ����
		ftpClient.login("ftpuser", "m123");
		//�����ϴ���·��
		ftpClient.changeWorkingDirectory("/home/ftpuser/www/images");
		//�޸��ϴ��ļ���ʽ
		ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
		//�ϴ��ļ� ����1:���������ļ��� ����2:inputstream
		ftpClient.storeFile("hello1.jpg", new FileInputStream(new File("C:\\Users\\Administrator\\Desktop\\1.jpg")));
		//�ر�����
		ftpClient.logout();
	}
	@Test
	public void testFtpUtil() throws Exception{
		FtpUtil.uploadFile("192.168.1.106", 21, "ftpuser", "m123", "/home/ftpuser/www/images", "", "2.jpg", new FileInputStream(new File("C:\\Users\\Administrator\\Desktop\\1.jpg")));
	}
	
}
