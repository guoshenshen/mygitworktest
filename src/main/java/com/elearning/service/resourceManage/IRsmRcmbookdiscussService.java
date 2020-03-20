package com.elearning.service.resourceManage;

import com.elearning.common.ServiceResponse;
import com.elearning.pojo.resourceManage.RsmRcmBookDiscuss;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 〈<br>
 * 〈〉
 *
 * @author lxx
 * @create 2019/7/15 16:01
 */
public interface IRsmRcmbookdiscussService {

    /**
     * 求该门课程的平均评分
     * @param scoreCondition
     * @return
     */
    Double getAverageScore(Map<String,Object> scoreCondition);


    /**
     * 课程评价
     * @param scoreCondition
     * @return
     */
    ServiceResponse getScoreDistribute(Map<String,Object> scoreCondition);

    /**
     * 课程评论
     * @param map
     * @return
     */
    ServiceResponse listRsmRcmbookdiscuss(HashMap map);

    ServiceResponse addRsmRcmBookDiscuss(RsmRcmBookDiscuss rsmRcmBookDiscuss);

    List<RsmRcmBookDiscuss> getListByBookIdAndDiscussTypeAndIsReply(Map<String,Object> parm);

    RsmRcmBookDiscuss findById(Integer discussId);

    List<RsmRcmBookDiscuss> getRsmRcmbookdiscussList(Map<String,Object> map);

    List<RsmRcmBookDiscuss> queryRsmRcmbookdiscussList(Map<String,Object> map);

}
