package com.taotao.web.test;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

public class FTPTest {
	public static void main(String[] args) {
		FTPClient ftpClient = new FTPClient();
		try {
			ftpClient.connect("10.211.55.8");
			ftpClient.login("lixiongcheng", "lee525079");
			FileInputStream inputStream = new FileInputStream(new File("/Users/lixiongcheng/Downloads/taotao/001.jpg"));
			ftpClient.changeWorkingDirectory("/home/files");
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			ftpClient.storeFile("123.jpg", inputStream);
			inputStream.close();

			ftpClient.logout();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
