package com.elearning.service.statistics;

import com.elearning.pojo.statistics.UscUserLearnStatics;
import com.elearning.pojo.statistics.UscUserLearnStaticsKey;
import com.elearning.vo.statistics.UscUserLearnStaticsYearSumForm;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 〈<br>
 * 〈〉
 *
 * @author lxx
 * @create 2019/10/15 11:22
 */
public interface IUscUserLearnStaticsService {

    int insertOnlineTime(Integer userId, Long courseId, Date now, long needAddedTime);

    UscUserLearnStatics selectByPrimaryKey(UscUserLearnStaticsKey key);

    int updateByPrimaryKeySelective(UscUserLearnStatics record);

    int insertSelective(UscUserLearnStatics record);

    void averageDistributeTimeToMonth(Integer operatorId,Integer year,Date startTime,Date endTime,Double classHour);

    /**
     * 我的档案->个人学时统计
     */
    List<UscUserLearnStaticsYearSumForm> queryUserlearnstaticsInProfile(Map<String,Object> map, Integer operatorId);






}
