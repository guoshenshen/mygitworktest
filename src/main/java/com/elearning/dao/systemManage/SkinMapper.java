package com.elearning.dao.systemManage;

import com.elearning.pojo.systemManage.Skin;

public interface SkinMapper {
    int deleteByPrimaryKey(Integer skinId);

    int insert(Skin record);

    int insertSelective(Skin record);

    Skin selectByPrimaryKey(Integer skinId);

    int updateByPrimaryKeySelective(Skin record);

    int updateByPrimaryKeyWithBLOBs(Skin record);

    int updateByPrimaryKey(Skin record);
}