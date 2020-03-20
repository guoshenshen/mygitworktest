package com.elearning.dao.plan;

import com.elearning.pojo.plan.PlanReport;

public interface PlanReportMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PlanReport record);

    int insertSelective(PlanReport record);

    PlanReport selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PlanReport record);

    int updateByPrimaryKey(PlanReport record);
}