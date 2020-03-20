package com.elearning.dao.trainNeeds;

import com.elearning.pojo.trainNeeds.TnResultReport;

public interface TnResultReportMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TnResultReport record);

    int insertSelective(TnResultReport record);

    TnResultReport selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TnResultReport record);

    int updateByPrimaryKey(TnResultReport record);
}