package com.elearning.common;

import com.elearning.util.ExportFileTool;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FileTool {
	
public static String FILE_ROOT_PATH=null;
	
	static{
		InputStream inputStream = ExportFileTool.class.getClassLoader().getResourceAsStream("elearning.properties");
	  	Properties p = new Properties();  
	  	try {   
	  	   p.load(inputStream);   
	  	} catch (IOException e1) {   
	  	   e1.printStackTrace();  
	  	}	
		
	  	FILE_ROOT_PATH= p.getProperty("uploadFile.fulladdress");
	  	
	}
	
	public static String convertFromURLToPhysicalPath(String url){
		String physicalPath=null;
		Integer index=url.indexOf("uploadFile");
		if(index<0);
		else{
			index+="uploadFile".length();
			physicalPath=(FileTool.FILE_ROOT_PATH+url.substring(index)).replace("/", File.separator);
		}
		return physicalPath;
	}

}
