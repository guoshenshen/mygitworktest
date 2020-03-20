package com.elearning.common.documentTool;

import com.elearning.common.excel.ExcelOperate;
import com.elearning.util.ZipUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Properties;

public class ExportFileTool{
	
	public static String systemManagedownLoadPathStr = null;
	
	static{
		InputStream inputStream = ExportFileTool.class.getClassLoader().getResourceAsStream("elearning.properties");   
	  	Properties p = new Properties();  
	  	try {   
	  	   p.load(inputStream);   
	  	} catch (IOException e1) {   
	  	   e1.printStackTrace();  
	  	}	
		
	  	systemManagedownLoadPathStr= p.getProperty("uploadFile.fulladdress")+ "systemManage";
	  	
	}
	
	/**
	 * 将urlPath对应的资源输出到指定输出流中
	 * @param urlPath
	 * @param output
	 * @throws IOException 
	 * 有问题：文件内容没有换行
	 */
	public static void streamOutFromURL(String urlPath,OutputStream output) throws IOException{
		URL url = new URL(urlPath);  
		BufferedOutputStream bos=null;
		BufferedInputStream bis = null;
	    //打开链接  
	    HttpURLConnection conn = (HttpURLConnection)url.openConnection(); 
	    conn.setUseCaches(false);
	    conn.setRequestMethod("GET");
        bos = new BufferedOutputStream(output);
	    //超时响应时间为5秒  
	    conn.setConnectTimeout(5 * 1000);  
	    try {
			InputStream inStream = null;
			conn.connect();
			inStream=conn.getInputStream();
			bis = new BufferedInputStream(inStream);
			byte[] buff = new byte[1024*5];
			int bytesRead=0;

			while(-1 != (bytesRead = bis.read(buff))) {
			    bos.write(buff,0,bytesRead);
			    bos.flush();
			}
			
			ExcelOperate.operateOrgExcel();
			ExcelOperate.operateOperatorExcel();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			 if (bis != null){
				 bis.close();
			 }
		     if (bos != null){
		    	 bos.close(); 
		     }
		           
		}
	}
	
	/**
	 * 将urlPath对应的资源输出到指定输出流中
	 * @param urlPath
	 * @param output
	 * @throws IOException 
	 * author:xiongying
	 * Date:2018-05-09
	 * 输入的文件必须要加入换行符
	 */
	public static void streamOutFromURL2(String urlPath,OutputStream output) throws IOException{
		URL url = new URL(urlPath);  
		//BufferedOutputStream bos=null;
		InputStreamReader isReader = null;
	    //打开链接  
	    HttpURLConnection conn = (HttpURLConnection)url.openConnection();  
        //设置请求方式为"GET"  
        conn.setRequestMethod("GET");  
      	//bos = new BufferedOutputStream(output);
	    //超时响应时间为5秒  
	    conn.setConnectTimeout(5 * 1000);  
	    try {
			InputStream inStream = null;
			inStream=conn.getInputStream();
			isReader = new InputStreamReader(inStream,"UTF-8");
			BufferedReader reader = new BufferedReader(isReader);
            StringBuffer buffer = new StringBuffer();
            
            String line; // 用来保存每行读取的内容
            line = reader.readLine(); // 读取第一行
            while (line != null) { // 如果 line 为空说明读完了
                buffer.append(line); // 将读到的内容添加到 buffer 中
                buffer.append("\r\n"); // 添加换行符
                line = reader.readLine(); // 读取下一行
            }
            output.write(buffer.toString().getBytes("UTF-8"));

		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			 if (isReader != null){
				 isReader.close();
			 }
		}
	}
	
	/**
	 * 将urlPath对应的资源保存成文件
	 * @param urlPath
	 * @param fileName
	 * @return
	 * @throws IOException 
	 */
	public static File saveFileFromURL(String urlPath,String fileName) throws IOException{

		File generatedFile =null;
		FileOutputStream outStream =null;
		generatedFile = new File(systemManagedownLoadPathStr+File.separator+fileName); 
		outStream = new FileOutputStream(generatedFile); 
		ExportFileTool.streamOutFromURL(urlPath, outStream);
	    return generatedFile;
	}
	
	/**
	 * @param //demoWorkBook 将excel文件压缩成zip文件供下载处理
	 * @param request
	 * fileFolderName:生成文件所在的文件夹
	 * @return
	 */
	public static String getnerateZipLinkFile(File[] files,String zipFileName,HttpServletRequest request,String fileFolderName){
		
		if(fileFolderName == null || fileFolderName.equals(""))
			fileFolderName = "systemManage";
		String result=null;
		if(files==null||files.length==0||zipFileName==null||zipFileName.trim().length()==0){
			return result;
		}
		InputStream inputStream = ExportFileTool.class.getClassLoader().getResourceAsStream("elearning.properties");   
	  	Properties p = new Properties();  
	  	try {   
	  	   p.load(inputStream);   
	  	} catch (IOException e1) {   
	  	   e1.printStackTrace();  
	  	}	
		String theWebPath = request.getSession().getServletContext().getRealPath( "/");
				
		int lastpath = theWebPath.lastIndexOf(File.separator);
		theWebPath = theWebPath.substring(0,lastpath);	
		lastpath = theWebPath.lastIndexOf(File.separator);
		theWebPath = theWebPath.substring(0,lastpath);
		 
		  	 
	  	theWebPath = theWebPath.substring(0,lastpath)+ p.getProperty("uploadFile.address");				
			 
		File[] fileList = files;
		
		SimpleDateFormat dateFm = new SimpleDateFormat("yyyyMMddHHmmss"); //格式化当前系统日期
		String fileName =zipFileName+ dateFm.format(new java.util.Date())+".zip";
		File zipFile = new File(p.getProperty("uploadFile.fulladdress") + fileFolderName + File.separator+fileName);
		ZipUtil.zip(fileList, zipFile);
		String zipFilePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+p.getProperty("uploadFile.address") + fileFolderName + "/" + fileName; 					 
		result=zipFilePath;
		return result;
	}
	
	
	/**
	 * @param demoWorkBook 将excel文件压缩成zip文件供下载处理
	 * @param request
	 * fileFolderName:生成文件所在的文件夹
	 * @return
	 */
	public static String generateZipLinkFile(HSSFWorkbook demoWorkBook,HttpServletRequest request,String fileFolderName){
		String result=null;
		
		if(fileFolderName == null || fileFolderName.equals(""))
			fileFolderName = "systemManage";
		
		InputStream inputStream = ExportFileTool.class.getClassLoader().getResourceAsStream("elearning.properties");   
	  	Properties p = new Properties();  
	  	try {   
	  	   p.load(inputStream);   
	  	} catch (IOException e1) {   
	  	   e1.printStackTrace();  
	  	}	
		String theWebPath = request.getSession().getServletContext().getRealPath( "/");
				
		int lastpath = theWebPath.lastIndexOf(File.separator);
		theWebPath = theWebPath.substring(0,lastpath);	
		lastpath = theWebPath.lastIndexOf(File.separator);
		theWebPath = theWebPath.substring(0,lastpath);
		 
		  	 
	  	theWebPath = theWebPath.substring(0,lastpath)+ p.getProperty("uploadFile.address");				
	  	String downloadPathStr = p.getProperty("uploadFile.fulladdress") + fileFolderName;
//	  			+ "systemManage";

		SimpleDateFormat dateFm = new SimpleDateFormat("yyyyMMddHHmmss"); //格式化当前系统日期
		String fileName = dateFm.format(new java.util.Date())+".xls";
        File exportFileDir = new File(downloadPathStr);
        if (!exportFileDir.isDirectory() ){
        	exportFileDir.mkdirs();
        }
		File exportFile = new File(downloadPathStr + File.separator+ fileName);
		if (!exportFile.exists()) {
			try {
				exportFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
        FileOutputStream os = null;  
         try {  
             os = new FileOutputStream(exportFile); 
             demoWorkBook.write(os);  

         } catch (Exception e) {  
             e.printStackTrace();  
         } finally {  
             try {  
                 os.close();  
             } catch (Exception e) {  
                 e.printStackTrace();  
             }  
         }  
		//将已上传的文档压缩
		File[] fileList = new File[]{exportFile};
		File zipFile = new File(exportFile.getAbsolutePath().substring(0, exportFile.getAbsolutePath().lastIndexOf("."))+".zip");
		ZipUtil.zip(fileList, zipFile);
		String zipFilePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+p.getProperty("uploadFile.address")
				+ fileFolderName + "/"
				+fileName.substring(0,fileName.lastIndexOf("."))+".zip";
		result=zipFilePath;
		return result;
	}

}
