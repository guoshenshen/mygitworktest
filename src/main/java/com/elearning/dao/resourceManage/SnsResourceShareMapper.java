package com.elearning.dao.resourceManage;

import com.elearning.pojo.resourceManage.SnsResourceShare;

public interface SnsResourceShareMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SnsResourceShare record);

    int insertSelective(SnsResourceShare record);

    SnsResourceShare selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SnsResourceShare record);

    int updateByPrimaryKey(SnsResourceShare record);
}