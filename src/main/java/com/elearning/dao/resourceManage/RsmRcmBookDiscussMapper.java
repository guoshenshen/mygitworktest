package com.elearning.dao.resourceManage;

import com.elearning.pojo.resourceManage.RsmRcmBookDiscuss;
import com.elearning.vo.MapVo;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface RsmRcmBookDiscussMapper {
    int deleteByPrimaryKey(Integer discussId);

    int insert(RsmRcmBookDiscuss record);

    int insertSelective(RsmRcmBookDiscuss record);

    RsmRcmBookDiscuss selectByPrimaryKey(Integer discussId);

    int updateByPrimaryKeySelective(RsmRcmBookDiscuss record);

    int updateByPrimaryKeyWithBLOBs(RsmRcmBookDiscuss record);

    int updateByPrimaryKey(RsmRcmBookDiscuss record);


    /**
     * 求取课程评分的平均分
     * @param map
     * @return
     */
    Double getCourseScore(Map<String,Object> map);



    List<MapVo> getScoreDistribute(Map<String,Object> map);


    List<RsmRcmBookDiscuss> listRsmRcm();

    List<RsmRcmBookDiscuss> getRsmRcmbookdiscussList(Map<String,Object> map);

    Set<RsmRcmBookDiscuss> getRsmRcmbookdiscussByparentDiscussId(Map<String,Object> map);

    List<RsmRcmBookDiscuss> getListByBookIdAndDiscussTypeAndIsReply(Map<String,Object> map);



}