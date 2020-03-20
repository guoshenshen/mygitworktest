package com.elearning.util;


import com.elearning.pojo.mixtraining.MtMixTrainScheduleItemInfo;
import com.elearning.pojo.systemManage.Resource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 〈<br>
 * 〈〉
 *
 * @author lxx
 * @create 2019/7/16 15:33
 */
public class ToolsUtil {


    public static Map<String,Object> getBaseMap( List<Map<String, Object>> baseList) {
        Map<String, Object> baseMap = new HashMap<>();
        for (Map<String, Object> map:baseList//遍历list
                ) {
            String base = null;
            Object fare = null;
            for (Map.Entry<String,Object>  entry:map.entrySet()   //遍历map的key集合 获取对应key的value
                    ) {
                baseMap.put(entry.getKey(),entry.getValue());
            }
        }
        return baseMap;
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


    public static String stripHtml(String content) {
        // <p>段落替换为换行
        content = content.replaceAll("<p .*?>", "\r\n");
        // <br><br/>替换为换行
        content = content.replaceAll("<br\\s*/?>", "\r\n");
        // 去掉其它的<>之间的东西
        content = content.replaceAll("\\<.*?>", "");
        // 去掉空格
        content = content.replaceAll(" ", "");
        return content;
    }

    /*
     * 对菜单进行排序
     * 修复bug：机关学习平台，管理员端菜单栏顺序不正确问题
     * author：xiongying
     * Date：2018-03-23
     */
    public static Comparator sortMenuSeq(){
        Comparator comp = new Comparator(){
            public int compare(Object o1,Object o2) {
                Resource p1=(Resource)o1;
                Resource p2=(Resource)o2;
                if(p1.getSeq()> p2.getSeq()){//从小到大排列，返回值1，-1，0
                    return 1;
                }else if(p1.getSeq()< p2.getSeq()){
                    return -1;
                }else{
                    return 0;
                }
            }
        };
        return comp;
    }

    public static  Comparator sortoffLineScheduleItemSeq(){
        Comparator comp = new Comparator(){
            public int compare(Object o1,Object o2) {
                MtMixTrainScheduleItemInfo p1=(MtMixTrainScheduleItemInfo)o1;
                MtMixTrainScheduleItemInfo p2=(MtMixTrainScheduleItemInfo)o2;
                if(p1.getScheduleDate()!=null && p2.getScheduleDate()!=null){
                    if(p1.getScheduleDate().after(p2.getScheduleDate())){
                        return 1;
                    } else if(p1.getScheduleDate().compareTo(p2.getScheduleDate())==0){
                        if(p1.getScheduleStartTime()!=null&&p1.getScheduleStartTime()!=null){
                            if(p1.getScheduleStartTime().compareTo(p2.getScheduleStartTime())>0){
                                return 1;
                            } else if(p1.getScheduleStartTime().compareTo(p2.getScheduleStartTime())==0){
                                return 0;
                            } else{
                                return -1;
                            }
                        }else{
                            return 0;
                        }
                    }else{
                        return -1;
                    }
                }
                return 0;
            }
        };
        return comp;
    }

    public static List<String> orgSEQList(String orgSEQ){
        List<String> orgSeqList=new ArrayList<String>();
        StringBuffer orgSEQStr=new StringBuffer("");
        String[] orgs=orgSEQ.split("\\.");
        for(String orgStr:orgs){
            orgSEQStr.append(orgStr);
            orgSEQStr.append(".");
            orgSeqList.add(orgSEQStr.toString());

        }
        return orgSeqList;
    }


}
