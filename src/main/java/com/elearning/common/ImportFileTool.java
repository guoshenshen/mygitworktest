package com.elearning.common;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


import com.elearning.util.PropertiesUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class ImportFileTool {
	
	public static String getUploadRootPath(){
		return PropertiesUtil.getProperty("uploadFile.fulladdress");
	}
	
	public static String getUploadUrlPath(){
		return PropertiesUtil.getProperty("uploadFile.address").toString();
	}
	
	public static Map<String,File> uploadFile(HttpServletRequest request,String savePath){
		Map<String,File> resultMap=new HashMap<String,File>();
		DiskFileItemFactory fac = new DiskFileItemFactory();
	    ServletFileUpload upload = new ServletFileUpload(fac);
	    upload.setHeaderEncoding("utf-8");
	    List fileList = null;
        try {
          fileList = upload.parseRequest(request);
        } catch (FileUploadException ex) {
        }
        Iterator it = fileList.iterator();
        String fileName = "";
        String name = "";
        String type = "";
        String revisePath=savePath.replaceAll("/", File.separator);
        String uploadPathStr =ImportFileTool.getUploadRootPath()+revisePath;
        while (it.hasNext()){
	    	 FileItem item = (FileItem) it.next();
	         if (!item.isFormField()){
	        	 fileName = item.getName();
	             if (fileName == null || fileName.trim().equals("")){
	              continue;
	             }
	            if (fileName.lastIndexOf(".") >= 0){
	           	 	type = fileName.substring(fileName.lastIndexOf("."));
	            }
	            name = String.valueOf(System.currentTimeMillis());
	            fileName = name + type; 
	            File theRTEUploadDir = new File( uploadPathStr );
	    		
	    		if(!theRTEUploadDir.isDirectory()){
	    		      theRTEUploadDir.mkdirs();
	    		}
	    		File file = new File(uploadPathStr + File.separator, fileName);
	            try {
					item.write(file);
					String savedFilePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+ImportFileTool.getUploadUrlPath() + savePath+"/"+fileName;
					savedFilePath=savedFilePath.replaceAll("\\\\", "/");
					resultMap.put(savedFilePath, file);
				} catch (Exception e) {
					e.printStackTrace();
				}
	        	 
	         } 
        }
		return resultMap;
	}
	

	

	
}
