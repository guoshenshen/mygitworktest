package com.elearning.common;

import java.text.DateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyUtils {

	public static boolean isNull(Object obj) {

		return (obj == null || "".equals(obj))?true:false;
	}
	
	public static boolean isBeforeDate(Date date) {
		boolean result = false;
		try{
			Date nowDate = new Date();
			DateFormat df = DateFormat.getDateTimeInstance();
			if(nowDate.before(date))
				result = true;
		}catch (Exception e) {
			System.out.println("日期转化出错！");
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static boolean isMarkNeed(String str, String matchString){
		Pattern pattern = Pattern.compile("^"+matchString+".*");
		Matcher isMarkNeedMatcher = pattern.matcher(str);
		if(!isMarkNeedMatcher.matches()){
			return false;
		}else{
			return true;
		}
	}
	
	
}
