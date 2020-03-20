package com.elearning.dao.integralTask;

import com.elearning.pojo.integralTask.Integral;
import com.elearning.pojo.integralTask.IntegralRule;

import java.util.List;
import java.util.Map;

public interface IntegralMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Integral record);

    int insertSelective(Integral record);

    Integral selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Integral record);

    int updateByPrimaryKey(Integral record);

    List<Integral> getListByUserIdAndStatus(Map<String,Object> parMap );

}