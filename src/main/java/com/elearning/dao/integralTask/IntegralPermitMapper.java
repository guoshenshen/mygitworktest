package com.elearning.dao.integralTask;

import com.elearning.pojo.integralTask.IntegralPermit;

public interface IntegralPermitMapper {
    int deleteByPrimaryKey(Long id);

    int insert(IntegralPermit record);

    int insertSelective(IntegralPermit record);

    IntegralPermit selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(IntegralPermit record);

    int updateByPrimaryKey(IntegralPermit record);
}