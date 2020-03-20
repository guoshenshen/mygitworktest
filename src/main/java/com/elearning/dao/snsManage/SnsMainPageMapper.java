package com.elearning.dao.snsManage;

import com.elearning.pojo.snsManage.SnsMainPage;

public interface SnsMainPageMapper {
    int deleteByPrimaryKey(Integer infoId);

    int insert(SnsMainPage record);

    int insertSelective(SnsMainPage record);

    SnsMainPage selectByPrimaryKey(Integer infoId);

    int updateByPrimaryKeySelective(SnsMainPage record);

    int updateByPrimaryKeyWithBLOBs(SnsMainPage record);

    int updateByPrimaryKey(SnsMainPage record);
}