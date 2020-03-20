package com.elearning.common;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Calendar;
import java.text.ParseException;
import java.math.BigDecimal;
public class StudyPublics {

	
	public final Date TransDate(String str){
	
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat format2= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 Date date = null;
		//String str = null;
//		 StringתDate
		//str = "2007-1-18";
		try {
		date = format1.parse(str);
		return date;
		//data = format2.parse(str);
		}
		catch(Exception e){

		e.printStackTrace();
		}
		return date;
	}
	
	
	
	
	public final String TransDateFormat(Date date1){
		
		
		
		
		DateFormat d2 = DateFormat.getDateTimeInstance();  
	      String str2 = d2.format(date1);  

		return str2;
	}
	
	
	
	public final String TransDateFormat1(Date date1){
		
		
		
		
		DateFormat d2 = DateFormat.getDateInstance(); 
	      String str2 = d2.format(date1);  

		return str2;
	}
	
   public final String TransDateFormatFullDate(Date date1){
		
		
		
		
		DateFormat d2 = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL); 
	      String str2 = d2.format(date1);  

		return str2;
	}
	
	
	
	
	
	
	
	/**
    * ������������֮����������
    * 
    * @param date1
    * @param date2
    * @return
    */
   public  int diffdates(Date date1, Date date2) {
       int result = 0;
       //ElapsedTime et = new ElapsedTime();

       GregorianCalendar gc1 = new GregorianCalendar();
       GregorianCalendar gc2 = new GregorianCalendar();

       gc1.setTime(date1);
       gc2.setTime(date2);
       
       result = getDays(gc1, gc2)-1;

       return result;
   } 


public static int getDays(GregorianCalendar g1, GregorianCalendar g2) {
  int elapsed = 0;
  GregorianCalendar gc1, gc2;

  if (g2.after(g1)) {
   gc2 = (GregorianCalendar) g2.clone();
   gc1 = (GregorianCalendar) g1.clone();
  } else {
   gc2 = (GregorianCalendar) g1.clone();
   gc1 = (GregorianCalendar) g2.clone();
  }

  gc1.clear(Calendar.MILLISECOND);
  gc1.clear(Calendar.SECOND);
  gc1.clear(Calendar.MINUTE);
  gc1.clear(Calendar.HOUR_OF_DAY);

  gc2.clear(Calendar.MILLISECOND);
  gc2.clear(Calendar.SECOND);
  gc2.clear(Calendar.MINUTE);
  gc2.clear(Calendar.HOUR_OF_DAY);

  while (gc1.before(gc2)) {
   gc1.add(Calendar.DATE, 1);
   elapsed++;
  }
  return elapsed;
}


/**
* ����ָ�����ڵ���һ��
* 
* @param dateTime
*            ���ڣ���ʽΪ��yyyy-MM-dd
* @return
*/
public static String getBeforeDay(String dateTime) {
   Calendar now = Calendar.getInstance();
   SimpleDateFormat simpledate = new SimpleDateFormat("yyyy-MM-dd");
   Date date = null;
  try {
    date = simpledate.parse(dateTime);
   } catch (ParseException ex) {
    System.out.println("���ڸ�ʽ�����Ҫ��" + ex.getMessage());
   return null;
   }
   now.setTime(date);
  int year = now.get(Calendar.YEAR);
  int month = now.get(Calendar.MONTH);
  int day = now.get(Calendar.DAY_OF_MONTH) - 1;
   now.set(year, month, day);
   String time = simpledate.format(now.getTime());
  return time;
} 

/**
* ����ָ�����ڵ���һ��
* 
* @param dateTime
*            ���ڣ���ʽΪ��yyyy-MM-dd
* @return
*/
public static String getNextDay(String dateTime) {
   Calendar now = Calendar.getInstance();
   SimpleDateFormat simpledate = new SimpleDateFormat("yyyy-MM-dd");
   Date date = null;
  try {
    date = simpledate.parse(dateTime);
   } catch (ParseException ex) {
    System.out.println("���ڸ�ʽ�����Ҫ��" + ex.getMessage());
   return null;
   }
   now.setTime(date);
  int year = now.get(Calendar.YEAR);
  int month = now.get(Calendar.MONTH);
  int day = now.get(Calendar.DAY_OF_MONTH) + 1;
   now.set(year, month, day);
   String time = simpledate.format(now.getTime());
  return time;
}

/**
* �õ�ָ���µ�����
* @param _year
* @param _month
* @return
*/
public static int getMaxDayOfMonth(int _year, int _month){
   Calendar now = Calendar.getInstance();
  int year = 0;
  int month = 0;
  if(_month==1){
    year = _year - 1;
    month = 12;
   }else{
    year = _year;
    month = _month - 1;
   }
  
   now.set(Calendar.YEAR, year);
   now.set(Calendar.MONTH, month);

  return now.getActualMaximum(Calendar.DATE);
}

/**
* 
* 
* @param beginTime yyyy-MM-dd HH:mm:ss
*          
* @param endTime yyyy-MM-dd HH:mm:ss
*          
* @return 
*/
public static long getTimeDifference(String beginTime, String endTime) {
  long between = 0;
   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
   
   Date end = null;
   Date begin = null;
   end = Tools.stringToSeconds(endTime);    
   begin = Tools.stringToSeconds(beginTime);
   between = (end.getTime() - begin.getTime()) / 1000;
   return between;
}




public static long getTimeDifference(Date begin, Date end) {

 
  
  long betweenTime = (end.getTime() - begin.getTime()) / 1000;// ����1000��Ϊ��ת������

  return betweenTime;
}



/**
* ����ʱ���
* 
* @param time
*            ָ����ʱ�䣬��ʽΪ��yyyy-MM-dd HH:mm:ss
* @return ��ǰʱ���ָ��ʱ���ʱ���룩
*/
public static long getTimeDifference(String time) {
  long between = 0;
   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
   String systemTime = sdf.format(new Date()).toString();

   Date end = null;
   Date begin = null;
  try {// ����ȡ����ʱ���ַ�ת��Ϊʱ���ʽ���ַ�
    end = sdf.parse(time);
    begin = sdf.parse(systemTime);
   } catch (ParseException e) {
    e.printStackTrace();
   }

   between = Math.abs(end.getTime() - begin.getTime()) / 1000;// ����1000��Ϊ��ת������

  return between;
}

/**
* �ṩ��ȷ��С��λ�������봦��
* 
* @param v ��Ҫ�������������
* @param scale  С��������λ
* * @return ���������Ľ��
*/
public static double round(double v,int scale) {


	
  BigDecimal b = new BigDecimal(Double.toString(v));
  BigDecimal one = new BigDecimal("1");
  
  return b.divide(one, scale,BigDecimal.ROUND_HALF_UP).doubleValue();
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
