package com.elearning.common;

import java.util.*;

public class DAOTool {


    /**
     * 将参数封装至查询语句中
     * @param query
     * @param condition
     * @return
     */
    public static String setParameterValue(String query,Map<String,Object> condition){
        String result = query;
        Set<String> keySet=condition.keySet();
        for(String key:keySet){
            Object value=condition.get(key);
            if(query.contains(":"+key)){
                if(value instanceof List){
                    result = result.replaceAll(":"+key , getSqlStringByList((List) value));
                }if(value instanceof String){
                    result = result.replaceAll(":"+key , "'"+value.toString()+"'");
                }else{
                    result = result.replaceAll(":"+key , value.toString());
                }
            }
        }
        return result;
    }


    public static String setParameterValueLike(String query , Map<String,Object> condition){
        String result = query;
        Set<String> keySet=condition.keySet();
        for(String key:keySet){
            Object value=condition.get(key);
            if(!(value instanceof List || value instanceof Set)){
                if(query.contains(":"+key)){
                    result = result.replaceAll(":"+key , "'%"+ condition.get(key).toString().trim()+"%'");
                }
            }
        }
        return result;
    }



    public static String getSqlStringByList(List list){
        StringBuffer buffer = new StringBuffer("(");
        for(int i = 0 ; i < list.size() ; i++ ){
            String str = list.get(i).toString();
            buffer.append("'"+str+"'");
            if(i  < list.size()-1 ){
                buffer.append(",");
            }
        }
        buffer.append(")");
        return buffer.toString();
    }

    public static void main(String[] args) {
        String query = "select * from course where courseId = :courseId and orgSQL in :orgList and courseName = :courseName ";
        Map<String,Object> map = new HashMap<>();
        map.put("courseId",1);
        List<String> list = new ArrayList<>();
        list.add("1.");
        list.add("2.");
        list.add("3.");
        map.put("orgList",list);
        map.put("courseName","name");
        System.out.println(setParameterValue(query,map));
        System.out.println(setParameterValueLike(query,map));
    }



}
