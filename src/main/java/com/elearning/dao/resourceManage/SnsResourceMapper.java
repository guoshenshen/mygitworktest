package com.elearning.dao.resourceManage;

import com.elearning.pojo.resourceManage.SnsResource;

public interface SnsResourceMapper {
    int deleteByPrimaryKey(Long resourceId);

    int insert(SnsResource record);

    int insertSelective(SnsResource record);

    SnsResource selectByPrimaryKey(Long resourceId);

    int updateByPrimaryKeySelective(SnsResource record);

    int updateByPrimaryKey(SnsResource record);
}