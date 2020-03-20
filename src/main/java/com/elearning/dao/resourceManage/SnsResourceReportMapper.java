package com.elearning.dao.resourceManage;

import com.elearning.pojo.resourceManage.SnsResourceReport;

public interface SnsResourceReportMapper {
    int deleteByPrimaryKey(Integer reportId);

    int insert(SnsResourceReport record);

    int insertSelective(SnsResourceReport record);

    SnsResourceReport selectByPrimaryKey(Integer reportId);

    int updateByPrimaryKeySelective(SnsResourceReport record);

    int updateByPrimaryKey(SnsResourceReport record);
}