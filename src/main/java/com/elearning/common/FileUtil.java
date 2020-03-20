package com.elearning.common;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;



public class FileUtil {
	
	/**
	 * author：xiongying
	 * 获得path路径下（文件夹下），匹配上regex表达式的文件的个数，不包括子文件夹
	 * */
	public static int countFileNums(String path,String regex){
//		System.out.println(path);
		File[] files = new File(path).listFiles();
		int filecount = 0;
		
		Pattern p = Pattern.compile(regex);
		Matcher m;
		String filename;
		for(File file:files){
			if(file.isFile()){
				filename = file.getName();
				m = p.matcher(filename);
				if(m.find()){
//					System.out.println(file.getName());
					filecount++;
				}
			}
		}
		
		return filecount;
	}
	/**
	 * author：xiongying
	 * 从网络上读取文件(文件服务器)path路径下（文件夹下），匹配上regex表达式的文件的个数，不包括子文件夹
	 * */
	public static int countFileNumsFromNet(String strUrlPath,String fileNamePrefix,String fileType){
//		System.out.println(path);
		String strUrl ="";
		int count = 0;
		int i = 1;
		for(;;){
			strUrl = strUrlPath +  fileNamePrefix + "_" + i + "." + fileType;
			URL url;
			try {
				url = new URL(strUrl);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
					count++;
					i++;
				}else{
					break;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return count;
	}
	
	
	public static boolean isFileExistFromNet(String strUrl){
		
		try {
//			System.out.println(strUrl);
			URL url = new URL(strUrl);
            // 返回一个 URLConnection 对象，它表示到 URL 所引用的远程对象的连接。
            URLConnection uc = url.openConnection();
            // 打开的连接读取的输入流。
            InputStream in = uc.getInputStream();
            return  true;
          
//          HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//          conn.setConnectTimeout(30 * 1000);
//          conn.setReadTimeout(30 * 1000);
//			if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
//				return true;
//			}else{
//				return false;
//			}

        } catch (Exception e) {
//        	e.printStackTrace();
            return false;
        }
	}
	
	
	public static boolean saveToFile(String orgFile,String finalFilePath,String finalFileName) { 
		boolean flag = false;
		FileOutputStream fos = null;  
		BufferedInputStream bis = null;  
		HttpURLConnection httpUrl = null;  
		URL url = null;  
		int BUFFER_SIZE = 1024;  
		byte[] buf = new byte[BUFFER_SIZE];  
		int size = 0;  
		String fullName = finalFilePath + finalFileName + "_original.png";
		try {  
			url = new URL(orgFile);  
			httpUrl = (HttpURLConnection) url.openConnection();  
			httpUrl.connect();  
			bis = new BufferedInputStream(httpUrl.getInputStream());  
			fos = new FileOutputStream(fullName);  
			while ((size = bis.read(buf)) != -1) {  
				fos.write(buf, 0, size);  
			}  
			fos.flush();  
			flag = true;
		 } catch (IOException e) {  
			 e.printStackTrace();
		 } catch (ClassCastException e) {  
			 e.printStackTrace();
		 } finally {  
			 try {  
				 fos.close();  
				 bis.close();  
				 httpUrl.disconnect();  
			 } catch (IOException e) { 
				 e.printStackTrace();
			 } catch (NullPointerException e) {  
				 e.printStackTrace();
			 }  
		 	}
		 return flag;
	} 

}
