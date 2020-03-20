package com.elearning.dao.selfStudy;

import com.elearning.pojo.selfStudy.SlStudy;

public interface SlStudyMapper {
    int deleteByPrimaryKey(Long slStudyId);

    int insert(SlStudy record);

    int insertSelective(SlStudy record);

    SlStudy selectByPrimaryKey(Long slStudyId);

    int updateByPrimaryKeySelective(SlStudy record);

    int updateByPrimaryKey(SlStudy record);
}