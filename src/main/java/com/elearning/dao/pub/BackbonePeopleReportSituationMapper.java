package com.elearning.dao.pub;

import com.elearning.pojo.pub.BackbonePeopleReportSituation;

public interface BackbonePeopleReportSituationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BackbonePeopleReportSituation record);

    int insertSelective(BackbonePeopleReportSituation record);

    BackbonePeopleReportSituation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BackbonePeopleReportSituation record);

    int updateByPrimaryKey(BackbonePeopleReportSituation record);
}