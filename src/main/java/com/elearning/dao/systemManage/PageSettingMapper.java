package com.elearning.dao.systemManage;

import com.elearning.pojo.systemManage.PageSetting;

public interface PageSettingMapper {
    int deleteByPrimaryKey(Integer operatorID);

    int insert(PageSetting record);

    int insertSelective(PageSetting record);

    PageSetting selectByPrimaryKey(Integer operatorID);

    int updateByPrimaryKeySelective(PageSetting record);

    int updateByPrimaryKey(PageSetting record);
}