package com.elearning.dao.systemManage;

import com.elearning.pojo.systemManage.TimeSetting;

public interface TimeSettingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TimeSetting record);

    int insertSelective(TimeSetting record);

    TimeSetting selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TimeSetting record);

    int updateByPrimaryKey(TimeSetting record);

    TimeSetting getCurrenTimeSetting();


}