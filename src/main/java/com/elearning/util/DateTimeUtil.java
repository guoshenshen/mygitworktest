package com.elearning.util;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateTimeUtil {

    public static final String STANDARD_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT = "yyyy-MM-dd";
    public static Date strToDate(String datetimeStr,String formatStr){
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(formatStr);
        DateTime dateTime = dateTimeFormatter.parseDateTime(datetimeStr);
        return dateTime.toDate();
    }

    public static String dateToStr(Date date,String formatStr){
        if(date == null){
            return StringUtils.EMPTY;
        }
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(formatStr);
    }

    public static Date strToDate(String datetimeStr){
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(STANDARD_FORMAT);
        DateTime dateTime = dateTimeFormatter.parseDateTime(datetimeStr);
        return dateTime.toDate();
    }

    public static String dateToStr(Date date){
        if(date == null){
            return StringUtils.EMPTY;
        }
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(FORMAT);
    }

    public static int getCurrentYear(){
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.YEAR);
    }

    public static int getCurrentMonth(){
        Calendar c=Calendar.getInstance();
        return c.get(Calendar.MONTH)+1;
    }

    public static String getNowYear(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
        return formatter.format(date);
    }

    public static String getNowMonth(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("MM");
        return formatter.format(date);
    }

    public static String getNowDay(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("dd");
        return formatter.format(date);
    }

    public static Date stringToDate(String timeString) {
        Date date=null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM");
        SimpleDateFormat sdf2=new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sdf3=new SimpleDateFormat("yyyyMM");
        SimpleDateFormat sdf4=new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat sdf5=new SimpleDateFormat("yyyy/MM");
        SimpleDateFormat sdf6=new SimpleDateFormat("yyyy:MM:dd");
        SimpleDateFormat sdf7=new SimpleDateFormat("yyyy:MM");
        SimpleDateFormat sdf8=new SimpleDateFormat("yyyy");

        List<SimpleDateFormat> formats=new ArrayList<SimpleDateFormat>();
        formats.add(sdf);
        formats.add(sdf1);
        formats.add(sdf2);
        formats.add(sdf3);
        formats.add(sdf4);
        formats.add(sdf5);
        formats.add(sdf6);
        formats.add(sdf7);
        formats.add(sdf8);

        if(timeString!=null&&!timeString.equals("")){
            timeString =timeString.trim();
            Iterator<SimpleDateFormat> formatIter=formats.iterator();
            while(formatIter.hasNext()){
                SimpleDateFormat next=formatIter.next();
                try{
                    date=next.parse(timeString);
                    break;
                }catch(Exception e){
                }
            }

        }
        return date;
    }

    public static Date stringToSeconds(String timeString){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(timeString!=null&&!timeString.equals(""))
            try {
                return sdf.parse(timeString);
            } catch (ParseException e) {
                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                try {
                    return sdf1.parse(timeString);
                } catch (ParseException e1) {
                    //e1.printStackTrace();
                    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        return sdf2.parse(timeString);
                    } catch (ParseException e2) {
                        e2.printStackTrace();
                    }
                }
            }
        return new Date();
    }
}
