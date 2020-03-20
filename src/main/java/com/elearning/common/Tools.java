package com.elearning.common;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sun.misc.BASE64Decoder;

public class Tools {

    /**
     * @param timeString
     * @return
     * @throws ParseException
     */

    /**
     * 获取当前年份
     * */
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

    /**
     * 获得指定日期的后一天
     * @param specifiedDay "yyyy-MM"或者"yyyy-MM-dd"
     * 如果specifiedDay="yyyy-MM"则返回结果为：下个月的第一天；
     * 如果specifiedDay="yyyy-MM-dd"则返回结果为：当前日期的后一天；
     * @return "yyyy-MM-dd"
     */
    public static String getSpecifiedDayNextDay(String specifiedDay) {
        Calendar c = Calendar.getInstance();
        String dayAfter = null;
        Date date = null;
        try {
            date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
            c.setTime(date);
            int day = c.get(Calendar.DATE);
            c.set(Calendar.DATE, day + 1);
            dayAfter = new SimpleDateFormat("yyyy-MM-dd")
                    .format(c.getTime());
        } catch (ParseException e) {
            try{
                date = new SimpleDateFormat("yy-MM").parse(specifiedDay);
                c.setTime(date);
                int month = c.get(Calendar.MONTH);
//        		System.out.println(month);
                c.set(Calendar.MONTH, month+1);
                c.set(Calendar.DAY_OF_MONTH, 1);
                dayAfter = new SimpleDateFormat("yyyy-MM-dd")
                        .format(c.getTime());
            }catch(ParseException ee){
                ee.printStackTrace();
            }
        }
        return dayAfter;
    }

    /**
     * 获取某年某月有多少天
     * @param year
     * @param month
     * @return
     */
    public static Integer getDaysOfMonth(Integer year,Integer month){
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,month-1);
        return c.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    public static Integer getDaysOfMonth(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return Tools.getDaysOfMonth(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH)+1);
    }

    public static Integer getDaysOfMonth(String dateStr) throws ParseException{
        Date date=Tools.stringToDate(dateStr);
        return Tools.getDaysOfMonth(date);
    }

    /**
     * 日期比较
     * @param date1
     * @param date2
     * @return 若日期1晚于日期2,返回1;若日期2晚于日期1,返回-1;相同返回0;非null日期比null日期晚
     */
    public static Short dateCompare(Date date1,Date date2){
        if(date1==null||date2==null){
            if(date1!=null){
                return 1;
            }
            else if(date2!=null){
                return -1;
            }
            else{
                return 0;
            }
        }
        else{
            if(date1.getTime()<date2.getTime()){
                return -1;
            }
            else if(date1.getTime()>date2.getTime()){
                return 1;
            }
            else{
                return 0;
            }
        }
    }



    public static Date stringToDate(String timeString)throws ParseException{
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

    /**
     * 返回日期格式 yyyy-MM-dd
     * */
    public static String stringDateToSimpleFormat(String dateString){
        String result = null;
        try{
            Date date = stringToDate(dateString);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            result = sdf.format(date);
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }


    @Deprecated
    /*
     * 输入的字符串中间没有"-"，如20140524
     */
    public static Date stringToDate2(String timeString)throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMM");
        if(timeString!=null&&!timeString.equals(""))
            try {
                return sdf.parse(timeString);
            } catch (ParseException e) {
                e.printStackTrace();
                return sdf1.parse(timeString);
            }
        else return null;
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
    public static Date stringToTime(String timeString) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        if(timeString!=null&&!timeString.equals(""))
            return sdf.parse(timeString);
        else return null;
    }


    /**
     * 将毫秒转化为HH:mm:ss.SSS的形式
     * author:xiongying
     * date:2017-10-25
     * */
    public static String formalMillsSeconds(long millisSeconds){
        if(millisSeconds>=0){
            long second = millisSeconds/1000;
            long milSec = millisSeconds%1000;
            long hour = second/3600;
            long minute = (second%3600)/60;
            return hour+":"+minute+":"+second+"."+milSec;
        }else{
            return "00:00:00.000";
        }
    }

    /**
     * 返回某段时间段跨越年份的个数
     * */
    public static int getYearSpan(String startTime,String endTime) throws ParseException{
        int yearSpan = 1;
        int startYear = getYear(startTime);
        int endYear = getYear(endTime);
        if(startYear<=endYear){
            yearSpan = endYear-startYear+1;
        } else{
            yearSpan = -1;
        }
        return yearSpan;
    }


    public static Integer getYear(Date time){
        Calendar c = Calendar.getInstance();
        c.setTime(time);
        int year = c.get(Calendar.YEAR);
        return year;
    }

    /**
     * 返回某个时间对应的年份
     * */
    public static Integer getYear(String startTime) throws ParseException{
        DateFormat   format=new   SimpleDateFormat("yyyy-MM");
        Date startDate = format.parse(startTime);
        Calendar c = Calendar.getInstance();
        c.setTime(startDate);
        int year = c.get(Calendar.YEAR);

        return year;
    }

    /**
     * 将某段时间按照年份分段，如2015-09~2016-10分为两段，2015-09~2015-12和2016-01~2016-10
     * */
    public static List<HashMap> getTimeSpanList(String startTime,String endTime) throws ParseException{
        List<HashMap> timeSpanList = new ArrayList<HashMap>();
        int yearSpan = getYearSpan(startTime,endTime);
        int startYear = getYear(startTime);
        int endYear = getYear(endTime);
        int currentYear = startYear;
        if(yearSpan>0){
            for(int i=0;i<yearSpan;i++){
                HashMap<String,String> map = new HashMap<String,String>();
                if(currentYear == startYear)
                    map.put("startTime", startTime);
                else
                    map.put("startTime", currentYear+"-01");
                if(currentYear == endYear)
                    map.put("endTime", endTime);
                else
                    map.put("endTime", currentYear+"-12");
                timeSpanList.add(map);
                currentYear++;
            }
        }
        return timeSpanList;
    }


    public static String DateToStringCore(String formatStr, Date date){
        if(formatStr==null||date==null){
            return null;
        }
        DateFormat format=new SimpleDateFormat(formatStr);
        String dateString=format.format(date);
        return dateString;
    }


    /**
     * @param date 为null时,返回null
     * @return
     * @throws ParseException
     */
    public static String DateToString_simple(Date date) throws ParseException{
        return Tools.DateToStringCore("yyyy.MM.dd", date);
    }

    /**
     * @param date 为null时,返回null
     * @return
     * @throws ParseException
     */
    public static String DateToString(Date date) throws ParseException {
        return Tools.DateToStringCore("yyyy-MM-dd", date);
    }

    /**
     * @param date 为null时,返回null
     * @return
     * @throws ParseException
     */
    public static String DateSecondToString(Date date) throws ParseException {
        return Tools.DateToStringCore("yyyy-MM-dd HH:mm:ss", date);
    }

    /**
     * @param date 为null时,返回null
     * @return
     * @throws ParseException
     */
    public static String DateMinuteToString(Date date) throws ParseException {
        return Tools.DateToStringCore("yyyy-MM-dd HH:mm", date);
    }

    /**
     * @param date
     * @return
     * @throws ParseException
     */
    public static String DateSecondTo24String(Date date) throws ParseException {
        return Tools.DateToStringCore("yyyy-MM-dd HH:mm:ss", date);
    }



    public static String[] lastDayAndToday(){
        String [] m = new String[2];
        Calendar ca = Calendar.getInstance();
        Date nowDate = new Date(ca.getTimeInMillis());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        m[1] = formatter.format(nowDate);

        ca.add(Calendar.DAY_OF_MONTH, -1);
        Date lastDay = ca.getTime();
        m[0] = formatter.format(lastDay);
        return m;
    }
    /**
     * 将double类型数据返回保留小数点后2位的string
     * @param data
     * @return
     */
//	public static String getTwoDigitalData(Double data){
//		DecimalFormat df = new DecimalFormat("0.00");
//		String formatedData = df.format(data);
//		if(Double.parseDouble(formatedData)==0.00)
//			formatedData = "0.00";
//		return formatedData;
//	}

    /**
     * author:xiongying
     * Date:2017-11-24
     * 将double类型数据返回保留小数点后2位的string,原有data或者格式化后的数据的小数点后如果为零则显示整数否则保留两位小数
     * DecimalFormat 对于数据的格式化规则：四舍五入
     * */
    public static String getTwoDigitalData(Double data){
//		如果是整数，则取整；
        if(data%1.0 == 0){
            return String.valueOf(data.intValue());
        }
        DecimalFormat df = new DecimalFormat("0.00");
        String formatedData = df.format(data);
        if(Double.parseDouble(formatedData)==0.00)
            formatedData = "0.00";
        Double temp = Double.valueOf(formatedData);
        if(temp%1.0 == 0)
            return String.valueOf(temp.intValue());
        else
            return formatedData;
    }

    /**
     * DecimalFormat 对于数据的格式化规则：四舍五入
     * 小数点后为零显示整数否则保留两位小数
     * */
    public static Double getTwoDigitalDoubleData(Double data){

        DecimalFormat df = new DecimalFormat("0.00");
        String formatedData = df.format(data);
        if(Double.parseDouble(formatedData)==0.00)
            formatedData = "0.00";
        return Double.valueOf(formatedData);
    }

    /**
     * Date:2018-01-18
     * author:xiongying
     * DecimalFormat 对于数据的格式化规则：直接保留两位小数，其他位直接去掉
     * 小数点后为零显示整数否则保留两位小数
     * */
    public static Double getTwoDigitalDoubleDataDown(Double data){

        DecimalFormat df = new DecimalFormat("0.00");
        String formatedData = df.format(data);
        if(Double.parseDouble(formatedData)==0.00){
            formatedData = "0.00";
            return Double.valueOf(formatedData);
        }
        BigDecimal bg = new BigDecimal(data);
        double result = bg.setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
        return Double.valueOf(result);
    }



//	 ClassPathXmlApplicationContext 是读取 src 目录下的配置文件
//	ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");

    //	  FileSystemXmlApplicationContext 即系统文件路径，文件的目录。
//	ApplicationContext context = new FileSystemXmlApplicationContext("WebRoot/WEB-INF/applicationContext.xml");
//	public static int findRootOrgId(Integer userId){
//		ApplicationContext factory =
//			new FileSystemXmlApplicationContext(new String[]{Constants.webRootPath+"WEB-INF/applicationContext.xml",Constants.webRootPath+"WEB-INF/applicationContext_hw.xml",Constants.webRootPath+"WEB-INF/applicationContext_tgj.xml",
//					Constants.webRootPath+"WEB-INF/applicationContext_csq.xml",Constants.webRootPath+"WEB-INF/applicationContext_sun.xml",Constants.webRootPath+"WEB-INF/applicationContext_lsl.xml"});
//		UserRoleDAO userRoleDAO = (UserRoleDAO)factory.getBean("userRoleDAO");
//		EosorgTEmployeeDAO eosorgTEmployeeDAO = (EosorgTEmployeeDAO)factory.getBean("eosorgTEmployeeDAO");
//		List<UserRole> userRoleList = userRoleDAO.findByUserId(userId);
//		for(UserRole userRole:userRoleList){
//			if(userRole.getRoleId()==5||userRole.getRoleId()==6){
//				 return eosorgTEmployeeDAO.findById(userId).getEosorgTOrganization().getParent().getParent().getOrgId();
//			}
//
//		}
//		 return eosorgTEmployeeDAO.findById(userId).getEosorgTOrganization().getParent().getOrgId();
//
//	}
    //人员-菜单 map
    private static HashMap<Integer,String> operatorMenu=new HashMap<Integer,String>();
    //角色-父菜单 map (一级菜单)
    private static HashMap<Integer,List> roleresourcemap = new HashMap<Integer,List>();
    // 父菜单-子菜单 map (二级菜单)
    private static HashMap<Integer,List> resourcerelationmap = new HashMap<Integer,List>();
    //人员-功能图标 map  (三级菜单)  eg:培训讲座-》通知/人员/日程.etc
    private static HashMap<Integer,List> operatorIconmap=new HashMap<Integer,List>();
    //rest方式获取当前人员的组织机构
    private static HashMap<String,Object> currentOrgIdmap=new HashMap<String,Object>();

    /**
     * 将unicode代码转换为中文
     * @param input
     * @return
     */
    public static String unicodeToCHN(String input){
        String outPut = "";
        String str = input.replaceAll("&#",",").replaceAll(";","");
        String [] s2 = str.split(",");
        for (int i=1;i<s2.length;i++){
            int a = Integer.parseInt(s2[i],10);
            outPut = outPut+(char)a;
        }

        return outPut;
    }


    /**
     * 获得指定日期的每周起止时间
     * @param date
     */
    public static Map<String,Object> getWeekPeriod(String date){
        Map<String,Object> map = new HashMap<String,Object>();

        if(null==date){
            Date now = new Date();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            date = df.format(now);
        }

        String[] dateformat = date.split("-");
        Calendar cal = Calendar.getInstance();
        try{
            cal.set(Integer.parseInt(dateformat[0]), Integer.parseInt(dateformat[1])-1,Integer.parseInt(dateformat[2]));
            int day = cal.get(7);

            cal.add(7, -day);
            map.put("beforeTime", cal.get(Calendar.YEAR)+"-"+ (cal.get(Calendar.MONTH)+1) +"-"+ cal.get(Calendar.DAY_OF_MONTH));
            cal.add(7, 2);
            map.put("startTime", cal.get(Calendar.YEAR)+"-"+ (cal.get(Calendar.MONTH)+1) +"-"+ cal.get(Calendar.DAY_OF_MONTH));
            cal.add(7, 6);
            map.put("endTime", cal.get(Calendar.YEAR)+"-"+ (cal.get(Calendar.MONTH)+1) +"-"+ cal.get(Calendar.DAY_OF_MONTH));
            cal.add(7, 1);
            map.put("afterTime", cal.get(Calendar.YEAR)+"-"+ (cal.get(Calendar.MONTH)+1) +"-"+ cal.get(Calendar.DAY_OF_MONTH));
        }catch(Exception ex){
            System.out.println("日期转换格式失败");
            ex.printStackTrace();
        }


        return map;
    }


    public static String getAllValuesFromList(List t)
    {
        StringBuffer result=new StringBuffer();
        String _result="";
        Iterator it = t.iterator();
        while(it.hasNext())
        {
            result.append(it.next());
            result.append(",");
        }
        if(result.length()>0)
            _result=result.toString().substring(0, result.length()-1);
        return _result;
    }


    public static HashMap<Integer, List> getRoleresourcemap() {
        return roleresourcemap;
    }
    public static void setRoleresourcemap(HashMap<Integer, List> roleresourcemap) {
        Tools.roleresourcemap = roleresourcemap;
    }
    public static HashMap<Integer, List> getResourcerelationmap() {
        return resourcerelationmap;
    }
    public static void setResourcerelationmap(
            HashMap<Integer, List> resourcerelationmap) {
        Tools.resourcerelationmap = resourcerelationmap;
    }
    public static HashMap<Integer, List> getOperatorIconmap() {
        return operatorIconmap;
    }
    public static void setOperatorIconmap(HashMap<Integer, List> operatorIconmap) {
        Tools.operatorIconmap = operatorIconmap;
    }


    /**
     * 取得随机文件名
     *
     */
    public  static String getRndFilename(){
        return String.valueOf(System.currentTimeMillis());
    }

    /**
     * 读文件
     *
     */
    public static String readFile(String filePath){
        File file = new File(filePath);
        StringBuffer document = new StringBuffer();
        BufferedReader reader = null;
        try {
            InputStreamReader read = new InputStreamReader(new FileInputStream(file),"UTF-8");
            reader = new BufferedReader(read);
            String tempString = null;
            int line = 1;
            while ((tempString = reader.readLine()) != null) {
                document.append(tempString + "");
                line++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return document.toString();
    }

    /**
     * 重写文件
     *
     */
    public  static void writeFile(String path, String content) {
        File desFile = new File(path);
        try {
            OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(desFile),"utf-8");
            BufferedWriter fw = new BufferedWriter(write);
            //FileWriter desWriter = new FileWriter(desFile);
            fw.write(content);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 检验一个字符串是否是数字
     * @param str
     * @return
     */
    public static boolean isNumeric(String str){
        if(str.matches("\\d*")){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 过滤html标签，返回html文本内容
     * @param inputString
     * @return
     */
    public static String Html2Text(String inputString) {
        String htmlStr = inputString; // 含html标签的字符串
        String textStr = "";
        java.util.regex.Pattern p_script;
        java.util.regex.Matcher m_script;
        java.util.regex.Pattern p_style;
        java.util.regex.Matcher m_style;
        java.util.regex.Pattern p_html;
        java.util.regex.Matcher m_html;
        java.util.regex.Pattern p_html1;
        java.util.regex.Matcher m_html1;

        try {
            String regEx_script = "<[//s]*?script[^>]*?>[//s//S]*?<[//s]*?///[//s]*?script[//s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[//s//S]*?<///script>
            String regEx_style = "<[//s]*?style[^>]*?>[//s//S]*?<[//s]*?///[//s]*?style[//s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[//s//S]*?<///style>
            String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
            String regEx_html1 = "<[^>]+";
            p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
            m_script = p_script.matcher(htmlStr);
            htmlStr = m_script.replaceAll(""); // 过滤script标签
            p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
            m_style = p_style.matcher(htmlStr);
            htmlStr = m_style.replaceAll(""); // 过滤style标签

            p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
            m_html = p_html.matcher(htmlStr);
            htmlStr = m_html.replaceAll(""); // 过滤html标签

            p_html1 = Pattern.compile(regEx_html1, Pattern.CASE_INSENSITIVE);
            m_html1 = p_html1.matcher(htmlStr);
            htmlStr = m_html1.replaceAll(""); // 过滤html标签
            //htmlStr=htmlStr.replace("/&nbsp;/ig","");//去掉&nbsp;
            htmlStr=htmlStr.replace("&nbsp;","");//去掉&nbsp;
            textStr = htmlStr;
        } catch (Exception e) {
            System.err.println("Html2Text: " + e.getMessage());
        }

        return textStr;// 返回文本字符串
    }


    /*
     * 格式化通知内容，为FCK中添加的图片和附件链接添加访问地址，用于发邮件时查看
     */
    public static String formatHtmlLink(String htmlStr,String enterUrl){

        String address = "";
        StringBuffer inputString = new StringBuffer(htmlStr);
        if(enterUrl!=null&&!enterUrl.equals(""))
            address = "http://"+Tools.getHostName(enterUrl);
        String img="";
        Pattern p_image;
        Matcher m_image;

        String regEx_img = "<img.*src\\s*=\\s*(.*?)[^>]*?>";
        p_image = Pattern.compile
                (regEx_img,Pattern.CASE_INSENSITIVE);
        m_image = p_image.matcher(inputString);
        while(m_image.find()){
            img =  m_image.group();
            Matcher m  = Pattern.compile("src\\s*=\\s*\"?(.*?)(\"|>|\\s+)").matcher(img);
            while(m.find()){
                if(!m.group(1).contains("http:")){
                    int beginIndex = inputString.indexOf(m.group(1));
                    inputString.insert(beginIndex,address);
                }
            }
        }

        String regEx_href = "<a.*href\\s*=\\s*(.*?)[^>]*?>";
        p_image = Pattern.compile
                (regEx_href,Pattern.CASE_INSENSITIVE);
        m_image = p_image.matcher(inputString);
        while(m_image.find()){
            img = m_image.group();
            Matcher m  = Pattern.compile("href\\s*=\\s*\"?(.*?)(\"|>|\\s+)").matcher(img);
            while(m.find()){
                if(!m.group(1).contains("http:")){
                    int beginIndex = inputString.indexOf(m.group(1));
                    inputString.insert(beginIndex,address);
                }
            }
        }

        return inputString.toString();
    }

    public static String getHostName(String urlString) {
        int index = urlString.indexOf("://");
        if (index != -1) {
            urlString = urlString.substring(index + 3);
        }
        index = urlString.indexOf("/");
        if (index != -1) {
            urlString = urlString.substring(0, index);
        }
        return urlString;
    }



    public static HashMap<String, Object> getCurrentOrgIdmap() {
        return currentOrgIdmap;
    }

    public static void setCurrentOrgIdmap(HashMap<String, Object> currentOrgIdmap) {
        Tools.currentOrgIdmap = currentOrgIdmap;
    }

    /**
     * 将培训总时间平均分配到培训期间的时间段中
     */
    public static HashMap<String,String> averageDistributeTrainTime(Date startTime,Date endTime,Double totalTimes){
        HashMap<String,String> distributeTime = new HashMap<String,String>();
        if(startTime.getMonth()==endTime.getMonth()&&startTime.getYear()==endTime.getYear())
            distributeTime.put((endTime.getYear()+1900)+"~"+(startTime.getMonth()+1), totalTimes.toString());
        else{
            int monthNumber = 12*(endTime.getYear()-startTime.getYear())+(endTime.getMonth()-startTime.getMonth());
            int totalDay = getTwoDateDistance(startTime,endTime);
            int leftDay = totalDay;
            int[] times = null;
            Date endDate = new Date(endTime.getYear(),endTime.getMonth(),1,0,0,0);
            if (endTime.getTime() == endDate.getTime()) {
                times = new int[monthNumber];
            } else {
                times = new int[monthNumber+1];
            }
            times[0]=getMonthDayNumber(startTime.getYear(),startTime.getMonth()+1)-startTime.getDate()+1; //第一个月的培训时间
            distributeTime.put((startTime.getYear()+1900)+"~"+(startTime.getMonth()+1), Tools.getTwoDigitalData(times[0]*totalTimes/totalDay));
            //		distributeTime.put((startTime.getYear()+1900)+"//"+(startTime.getMonth()+1), String.valueOf(times[0]));
            int year = startTime.getYear()+1900;
            int month= startTime.getMonth()+2;
            Double allocatedTime = Tools.getTwoDigitalDoubleData(times[0]*totalTimes/totalDay); //已分配的时间
            for(int i=1;i<times.length;i++){
                if(month>12){
                    month=1;
                    year=year+1;
                }
                int monthDay = getMonthDayNumber(year,month);
                leftDay = leftDay - times[i-1];
                if(monthDay<leftDay)
                    times[i]= monthDay;
                else
                    times[i]=leftDay;

                if(i==times.length-1){//最后一个月，分配的时间为剩余时间；保证所有时间相加等于总时间
                    distributeTime.put(year+"~"+month, Tools.getTwoDigitalData(totalTimes-allocatedTime));
                }else{
                    distributeTime.put(year+"~"+month, Tools.getTwoDigitalData(times[i]*totalTimes/totalDay));
                    Double temp = Tools.getTwoDigitalDoubleData(times[i]*totalTimes/totalDay);
                    BigDecimal b1 = new BigDecimal(Double.toString(temp));
                    BigDecimal b2 = new BigDecimal(Double.toString(allocatedTime));
                    allocatedTime = b1.add(b2).doubleValue();
                }

                //		distributeTime.put(year+"//"+month, String.valueOf(times[i]));
                month++;
            }
        }
        return distributeTime;
    }
    public static int getMonthDayNumber(int year,int month){
        Calendar time = Calendar.getInstance () ;
        time.clear () ;
        time.set (Calendar.YEAR , 1900+year) ;
        time.set (Calendar.MONTH , month-1) ; //Calendar对象默认一月为0
        return time.getActualMaximum (Calendar.DAY_OF_MONTH) ; //本月份的天数
    }
    public static int getTwoDateDistance(Date startTime,Date endTime){
        long distance = endTime.getTime() - startTime.getTime();
        return (int) (distance % (1000 * 60 * 60 * 24)==0 ? (distance / (1000 * 60 * 60 *24)) : (distance / (1000 * 60 * 60 *24)+1));
    }
    public static String getLastDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        // 某年某月的最后一天
        //   return cal.getActualMaximum(Calendar.DATE);
//        cal.set(Calendar.YEAR,2012);
//	    cal.set(Calendar.MONTH, 12);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.add(Calendar.DAY_OF_MONTH, -1);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        System.out.println(simpleDateFormat.format(cal.getTime()));
        return simpleDateFormat.format(cal.getTime());
    }

    /**
     * author:xiongying
     * Date:2017-08-29
     * 将某一年的第一天格式化为想要的格式，返回String；如果不要求格式，默认返回：yyyy-MM-dd格式
     * */
    public static String getFirstDayOfYear(int year, SimpleDateFormat sf) {
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(Calendar.YEAR, year);
        if(sf==null){
            sf = new SimpleDateFormat("yyyy-MM-dd");
        }
//        System.out.println(cal.getTime());
        return sf.format(cal.getTime());
    }

    /**
     * 返回下个月的第一天
     * */
    public static String getFistDayOfNextMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        System.out.println(simpleDateFormat.format(cal.getTime()));
        return simpleDateFormat.format(cal.getTime());
    }

    /**
     * 返回一天的最小时间 00:00:00
     * */
    public static Date getStartTimeOfDay(Date date) {
        Calendar dateStart = Calendar.getInstance();
        dateStart.setTime(date);
        dateStart.set(Calendar.HOUR_OF_DAY, 0);
        dateStart.set(Calendar.MINUTE, 0);
        dateStart.set(Calendar.SECOND, 0);
        dateStart.set(Calendar.MILLISECOND, 0);
        return dateStart.getTime();
    }

    /**
     * 返回一天的最大时间 23:59:59
     * */
    public static Date getEndTimeOfDay(Date date) {
        Calendar dateEnd = Calendar.getInstance();
        dateEnd.setTime(date);
        dateEnd.set(Calendar.HOUR_OF_DAY, 23);
        dateEnd.set(Calendar.MINUTE, 59);
        dateEnd.set(Calendar.SECOND, 59);
        dateEnd.set(Calendar.MILLISECOND, 999);
        return dateEnd.getTime();
    }


    public static Date getFirstDayOfYear(int year) {
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(Calendar.YEAR, year);
        return cal.getTime();
    }

    /**
     * author:xiongying
     * Date:2017-08-29
     * 将某一年的第一天格式化为想要的格式，返回String；如果不要求格式，默认返回：yyyy-MM-dd格式
     * */
    public static String getLastDayOfYear(int year, SimpleDateFormat sf) {
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(Calendar.YEAR, year+1);
        cal.add(Calendar.DAY_OF_YEAR, -1);
        if(sf==null){
            sf = new SimpleDateFormat("yyyy-MM-dd");
        }
//        System.out.println(cal.getTime());
        return sf.format(cal.getTime());
    }

    public static Date getLastDayOfYear(int year) {
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(Calendar.YEAR, year+1);
        cal.add(Calendar.DAY_OF_YEAR, -1);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE,59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }




    public static String formalStudyTime(int s){
        int N = s/3600;
        s = s%3600;
        int K = s/60;
        s = s%60;
        int M = s;
        StringBuffer result=new StringBuffer();
        if(N!=0)
            result.append(""+N+"").append("小时");
        if(K!=0)
            result.append(""+K+"").append("分");
        if(M!=0)
            result.append(""+M+"").append("秒");
        return result.toString();
    }

    /**
     * 将秒换算为XX小时XX分,向下取整,没有误差
     * */
    public static String formalSeconds(int second){
        if(second>=0){
            int hour = second/3600;
            int minute = (second%3600)/60;
            if(minute != 0){
                if(minute != 60)
                    return new StringBuffer().append(hour).append("小时").append(minute).append("分").toString();
                else
                    return new StringBuffer().append(hour+1).append("小时").toString();
            }
            else
                return new StringBuffer().append(hour).append("小时").toString();
        }else{
            return "0小时";
        }
    }

    /**
     * 将秒换算为XX小时XX分,向下取整
     * (注意此种方法转化出来的结果并不准确，例如：433740s，double hour = 120.4833,按照此方法转化后为：120小时28分钟，实际应该是120小时29分钟)
     * */
    public static String formalStudySeconds(int second){
        double hour = second/3600.0;
        return formatHours(hour);
    }

    /**
     * 将double小时换算成XX小时XX分,向下取整
     * 例如：1.5139h=1h30min50s,换算后为：1h30min
     * */
    public static String formatHours(double hours) {
        Integer hour = 0;
        if((int)Math.floor(hours)!=0)
            hour = (int)Math.floor(hours);
//      Integer minute = (int)Math.round(60*(hours - hour));
        BigDecimal b1 = new BigDecimal(Double.toString(hours));
        BigDecimal b2 = new BigDecimal(hour);
//		System.out.println(b1.subtract(b2).doubleValue());
//		System.out.println(60*(b1.subtract(b2).doubleValue()));
//		System.out.println((new BigDecimal(60).multiply(b1.subtract(b2))).doubleValue());
        Integer minute = (int)Math.floor((new BigDecimal(60).multiply(b1.subtract(b2))).doubleValue());
        if(minute != 0){
            if(minute != 60)
                return new StringBuffer().append(hour).append("小时").append(minute).append("分").toString();
            else
                return new StringBuffer().append(hour+1).append("小时").toString();
        }
        else
            return new StringBuffer().append(hour).append("小时").toString();
    }

    /**
     * 将double小时换算成XX小时XX分，向上取整
     * 例如：1.5139h=1h30min50s，换算后为：1h31min
     * 主要用于计算剩余学时时使用
     * */
    public static String formatCeilHours(double hours) {
        Integer hour = 0;
        if((int)Math.floor(hours)!=0)
            hour = (int)Math.floor(hours);
//      Integer minute = (int)Math.round(60*(hours - hour));
//      System.out.println((hours - hour));
        BigDecimal b1 = new BigDecimal(Double.toString(hours));
        BigDecimal b2 = new BigDecimal(hour);
//		System.out.println(b1.subtract(b2).doubleValue());
//		System.out.println(60*(b1.subtract(b2).doubleValue()));
        Integer minute = (int)Math.ceil((new BigDecimal(60).multiply(b1.subtract(b2))).doubleValue());
        if(minute != 0){
            if(minute != 60)
                return new StringBuffer().append(hour).append("小时").append(minute).append("分").toString();
            else
                return new StringBuffer().append(hour+1).append("小时").toString();
        }
        else
            return new StringBuffer().append(hour).append("小时").toString();
    }

    /**
     * 将String类型的时间换算成XX小时XX分,向下取整
     * 例如：1.5139h=1h30min50s,换算后为：1h30min
     * */
    public static String formatHours(String hours) {
        Double _hours = Double.valueOf(hours);
        Integer hour = 0;
        if((int)Math.floor(_hours)!=0)
            hour = (int)Math.floor(_hours);
//      Integer minute = (int)Math.round(60*(_hours - hour));
        BigDecimal b1 = new BigDecimal(Double.toString(_hours));
        BigDecimal b2 = new BigDecimal(hour);
//		System.out.println(b1.subtract(b2).doubleValue());
//		System.out.println(60*(b1.subtract(b2).doubleValue()));
//		System.out.println((new BigDecimal(60).multiply(b1.subtract(b2))).doubleValue());
        Integer minute = (int)Math.floor((new BigDecimal(60).multiply(b1.subtract(b2))).doubleValue());
        if(minute != 0)
            return new StringBuffer().append(hour).append("小时").append(minute).append("分").toString();
        else
            return new StringBuffer().append(hour).append("小时").toString();
    }

    public static void copyProperties(Object oldObject,Object newObject) throws SecurityException, NoSuchMethodException, Exception{
        if(oldObject!=null && newObject!=null){
            Class<?> clz = oldObject.getClass();
            // 获取实体类的所有属性，返回Field数组
            Field[] fields = clz.getDeclaredFields();
            for (Field field : fields) {
                Field fi = null;
                try{
                    fi = newObject.getClass().getDeclaredField(field.getName());
                }catch(NoSuchFieldException e){
                    continue;
                }
                fi.setAccessible(true);
                // 如果类型是String
                if (field.getGenericType().toString().equals(
                        "class java.lang.String")) { // 如果type是类类型，则前面包含"class "，后面跟类名
                    // 拿到该属性的gettet方法
                    /**
                     * 这里需要说明一下：他是根据拼凑的字符来找你写的getter方法的
                     * 在Boolean值的时候是isXXX（默认使用ide生成getter的都是isXXX）
                     * 如果出现NoSuchMethod异常 就说明它找不到那个gettet方法 需要做个规范
                     */
                    Method m = (Method) oldObject.getClass().getMethod(
                            "get" + getMethodName(field.getName()));
                    String val = (String) m.invoke(oldObject);// 调用getter方法获取属性值
                    if (val != null) {
                        fi.set(newObject, val);
                    }else
                        fi.set(newObject, "");
                }
                // 如果类型是Integer
                if (field.getGenericType().toString().equals(
                        "class java.lang.Integer")) {
                    Method m = (Method) oldObject.getClass().getMethod(
                            "get" + getMethodName(field.getName()));
                    Integer val = (Integer) m.invoke(oldObject);
                    if (val != null) {
                        fi.set(newObject, val);
                    }else
                        fi.set(newObject, 0);

                }
                // 如果类型是Double
                if (field.getGenericType().toString().equals(
                        "class java.lang.Double")) {
                    Method m = (Method) oldObject.getClass().getMethod(
                            "get" + getMethodName(field.getName()));
                    Double val = (Double) m.invoke(oldObject);
                    if (val != null) {
                        fi.set(newObject, val);
                    }else
                        fi.set(newObject, 0.00);

                }
                // 如果类型是Boolean 是封装类
                if (field.getGenericType().toString().equals(
                        "class java.lang.Boolean")) {
                    Method m = (Method) oldObject.getClass().getMethod(
                            field.getName());
                    Boolean val = (Boolean) m.invoke(oldObject);
                    if (val != null) {
                        fi.set(newObject, val);
                    }else
                        fi.set(newObject, false);

                }
                // 如果类型是Date
                if (field.getGenericType().toString().equals(
                        "class java.util.Date")) {
                    Method m = (Method) oldObject.getClass().getMethod(
                            "get" + getMethodName(field.getName()));
                    Date val = (Date) m.invoke(oldObject);
                    if (val != null) {
                        fi.set(newObject, val);
                    }else
                        fi.set(newObject, new Date());

                }
                // 如果类型是Short
                if (field.getGenericType().toString().equals(
                        "class java.lang.Short")) {
                    Method m = (Method) oldObject.getClass().getMethod(
                            "get" + getMethodName(field.getName()));
                    Short val = (Short) m.invoke(oldObject);
                    if (val != null) {
                        fi.set(newObject, val);
                    }
                }
                // 如果类型是Short
                if (field.getGenericType().toString().equals(
                        "class java.lang.Long")) {
                    Method m = (Method) oldObject.getClass().getMethod(
                            "get" + getMethodName(field.getName()));
                    Long val = (Long) m.invoke(oldObject);
                    if (val != null) {
                        fi.set(newObject, val);
                    }
                }
            }

        }
    }
    // 把一个字符串的第一个字母大写、效率是最高的、
    private static String getMethodName(String fildeName) throws Exception{
        byte[] items = fildeName.getBytes();
        items[0] = (byte) ((char) items[0] - 'a' + 'A');
        return new String(items);
    }
    // 复制文件
    public static void copyFile(File sourceFile, File targetFile) throws IOException {
        BufferedInputStream inBuff = null;
        BufferedOutputStream outBuff = null;
        try {
            // 新建文件输入流并对它进行缓冲
            inBuff = new BufferedInputStream(new FileInputStream(sourceFile));

            // 新建文件输出流并对它进行缓冲
            outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));

            // 缓冲数组
            byte[] b = new byte[1024 * 5];
            int len;
            while ((len = inBuff.read(b)) != -1) {
                outBuff.write(b, 0, len);
            }
            // 刷新此缓冲的输出流
            outBuff.flush();
        } finally {
            // 关闭流
            if (inBuff != null)
                inBuff.close();
            if (outBuff != null)
                outBuff.close();
        }
    }

    // 复制文件夹
    public static void copyDirectiory(String sourceDir, String targetDir) throws IOException {
        // 新建目标目录
        (new File(targetDir)).mkdirs();
        // 获取源文件夹当前下的文件或目录
        File[] file = (new File(sourceDir)).listFiles();
        for (int i = 0; i < file.length; i++) {
            if (file[i].isFile()) {
                // 源文件
                File sourceFile = file[i];
                // 目标文件
                File targetFile = new File(new File(targetDir).getAbsolutePath() + File.separator + file[i].getName());
                copyFile(sourceFile, targetFile);
            }
            if (file[i].isDirectory()) {
                // 准备复制的源文件夹
                String dir1 = sourceDir + File.separator + file[i].getName();
                // 准备复制的目标文件夹
                String dir2 = targetDir + File.separator + file[i].getName();
                copyDirectiory(dir1, dir2);
            }
        }
    }


    /**
     * 检查并初始化Map中的传参startTime、endTime；若没有传参，则为默认值
     * 传入参数格式：yyyy-MM-dd、yyyy-MM、yyyyMMdd、yyyyMM
     * 处理后格式为：yyyy-MM-dd
     * Date:2017-05-04
     * author:xiongying
     * */
    public static void initStartTimeAndEndTime(HashMap map){

        String startTime = Constants.startTime;
        String endTime = Constants.endTime;
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM");
        SimpleDateFormat sdf3 = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sdf4 = new SimpleDateFormat("yyyyMM");

        Date date = new Date();

//		startTime参数处理
        if(map.get("startTime")!=null && !map.get("startTime").toString().trim().equals("")){
            startTime = map.get("startTime").toString();
            if(startTime.contains("-")){
                try {
                    date = sdf1.parse(startTime); //判断是否是yyyy-MM-dd
//    				startTime = sdf1.format(date);
                } catch (Exception e) {
                    try{
                        date = sdf2.parse(startTime);//判断是否是yyyy-MM
                        startTime = sdf1.format(date); //转化为yyyy-MM-dd(自动转化为当前月的1号)
                    }catch(Exception ee) {
                        ee.printStackTrace();
                    }
                }
            }else{
                try {
                    date = sdf3.parse(startTime); //判断是否是yyyyMMdd
                    startTime = sdf1.format(date);
                } catch (Exception e) {
                    try{
                        date = sdf4.parse(startTime);//判断是否是yyyyMM
                        startTime = sdf1.format(date);//转化为yyyy-MM-dd(自动转化为当前月的1号)
                    }catch(Exception ee) {
                        ee.printStackTrace();
                    }
                }
            }
        }
//    	System.out.println("startTime:"+startTime);

//    	endTime参数处理
        if(map.get("endTime")!=null && !map.get("endTime").toString().trim().equals("")){
            endTime = map.get("endTime").toString();
            if(endTime.contains("-")){
                try {
                    date = sdf1.parse(endTime); //判断是否是yyyy-MM-dd
                } catch (Exception e) {
                    try{
                        date = sdf2.parse(endTime);//判断是否是yyyy-MM
//    					通过date获取本月最后一天
                        endTime = getLastday_Month(date,sdf1);//转化为yyyy-MM-dd(自动转化为当前月的最后一天)
                    }catch(Exception ee) {
                        ee.printStackTrace();
                    }
                }
            }else{
                try {
                    date = sdf3.parse(endTime); //判断是否是yyyyMMdd
                    endTime = sdf1.format(date);
                } catch (Exception e) {
                    try{
                        date = sdf4.parse(endTime);//判断是否是yyyyMM
//    					通过date获取本月最后一天
                        endTime = getLastday_Month(date,sdf1);//转化为yyyy-MM-dd(自动转化为当前月的最后一天)
                    }catch(Exception ee) {
                        ee.printStackTrace();
                    }
                }
            }
        }
//    	System.out.println("endTime:"+endTime);

        map.put("startTime", startTime);
        map.put("endTime", endTime);
    }

    /**
     * 某一个月的最后一天
     * @param date 日期(至少包含年月，如果只传入yyyy，则返回是yyyy-01-31日);
     * @param SimpleDateFormat df，输出结果格式
     * @return 某一个月的最后一天,按照df进行格式化
     */
    private static String getLastday_Month(Date date,SimpleDateFormat df) {

        Calendar calendar = Calendar.getInstance();

//        System.out.println(date);
        calendar.setTime(date);

        calendar.add(Calendar.MONTH, 1);    //加一个月
        calendar.set(Calendar.DATE, 1);        //设置为该月第一天
        calendar.add(Calendar.DATE, -1);    //再减一天即为上个月最后一天
        String day_last = df.format(calendar.getTime());

        return day_last;
    }


    /**
     * @Description: 将base64编码字符串转换为图片
     * @Author: nisikai
     * @CreateTime:
     * @param imgStr base64编码字符串
     * @param path 图片路径-具体到文件
     * @return
     */
    public static boolean generateImage(String imgStr, String path) {
        if (imgStr == null) {
            return false;
        }
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // 解密
            byte[] b = decoder.decodeBuffer(imgStr);
            // 处理数据
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            OutputStream out = new FileOutputStream(path);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    /**
     * 计算两个日期之间相差的天数
     * @Author: nisikai
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysBetween(Date smdate,Date bdate) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        smdate=sdf.parse(sdf.format(smdate));
        bdate=sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days=(time2-time1)/(1000*3600*24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * author:xiongying
     * date:2017-09-22
     * 判断某个对象≠null，并且≠""
     * */
    public static boolean isNotNullAndNotEmpty(Object object){
        if(object == null)
            return false;
        if(object.toString().trim().equals(""))
            return false;
        return true;

    }

    /**
     * 判断某个日期是否在两个日期中间
     * */
    public static int isCurrentDateBetween(Date currentDate, Date reportStartTime,
                                           Date reportEndTime) {
        if(currentDate.getTime() <= reportEndTime.getTime() && currentDate.getTime() >=  reportStartTime.getTime())
            return 1;
        else
            return 0;
    }

    /**
     * 将 Date原始格式"EEE MMM dd HH:mm:ss Z yyyy"，或者"yyyy-MM-dd HH:mm:ss.SSS"的String转成Date类型
     * */
    public static Date originalDateStringToDate(String orginalDateString){
        SimpleDateFormat sdf = new SimpleDateFormat ("EEE MMM dd HH:mm:ss Z yyyy", Locale.UK);
        SimpleDateFormat sdf2 = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss.SSS");
        Date date = null;
        try
        {
            date=sdf.parse(orginalDateString);
        }
        catch (ParseException e)
        {
            try{
                date = sdf2.parse(orginalDateString);
            }catch(ParseException ee){
                ee.printStackTrace();
            }
        }
        return date;
    }

    /**
     * 与直播系统进行用户对接，形成本系统operatorId与直播系统的uid一一对应关系
     * */
    /**
     * Date:2018-09-18
     * 通过本系统的operatorId映射成直播系统的uid
     * */
    public static Long generatorLiveId(int operatorId){
        //System.out.println(Constants.LiveUidBaseValue + (long)operatorId);
        return Constants.LiveUidBaseValue + (long)operatorId;
    }

    /**
     * Date:2018-09-18
     * 通过直播系统的uid映射成本系统的operatorId,注意如果值不在[0,Integer.Max]范围内，则返回-1
     * */
    public static Integer generatorOperatorId(long uid){
        //System.out.println(Constants.LiveUidBaseValue - (long)operatorId);
        long id = uid - Constants.LiveUidBaseValue;
//    	System.out.println(id);
        if(id >=0 && id<= Integer.MAX_VALUE)
            return (int)id;
        else
            return -1;
    }


    /**
     * 得到两个日期之间的日期
     * */
    public static List<Date> getBetweenDates(Date start, Date end) {
        List<Date> result = new ArrayList<Date>();
        Calendar tempStart = Calendar.getInstance();
        tempStart.setTime(start);
        tempStart.add(Calendar.DAY_OF_YEAR, 1);

        Calendar tempEnd = Calendar.getInstance();
        tempEnd.setTime(end);
        while (tempStart.before(tempEnd)) {
            result.add(tempStart.getTime());
            tempStart.add(Calendar.DAY_OF_YEAR, 1);
        }
        return result;
    }

    /**
     * 得到两个日期之间的年份
     * */
    public static List<Integer> getYearBetweenDates(Date start, Date end) {
        List<Integer> result = new ArrayList<Integer>();
        Calendar tempStart = Calendar.getInstance();
        tempStart.setTime(start);

        Calendar tempEnd = Calendar.getInstance();
        tempEnd.setTime(end);


        while (tempStart.before(tempEnd)) {
            result.add(tempStart.get(Calendar.YEAR));
            tempStart.add(Calendar.YEAR, 1);
        }
        return result;
    }

    /**
     * author:xiongying
     * Date:2018-09-07
     * 密码至少8位，且必须包含数字、字母、下划线
     * */
    public static boolean isValidPassword(String password) {
        String pwdRegEx = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*_)[\\w\\W]{8,}$";
        Pattern pattern = Pattern.compile(pwdRegEx);
        Matcher matcher = pattern.matcher(password);

        if (!matcher.find()) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 两个时间之间相隔分钟数，超过min，则大于0；不超过min，则小于0；否则等于0
     * */
    public static boolean exceedMinsbetweenDates(Date startTime,Date endTime,int min){
        if(startTime == null || endTime == null)
            return false;
        if(startTime.after(endTime))
            return false;
        long intervalMilli = endTime.getTime() - startTime.getTime();

        int minspan = (int)(intervalMilli/(60*1000));
//		System.out.println(minspan);

        if(minspan > min)
            return true;
        else
            return false;
    }

}
