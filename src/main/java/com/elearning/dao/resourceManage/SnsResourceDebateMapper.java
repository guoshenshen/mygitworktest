package com.elearning.dao.resourceManage;

import com.elearning.pojo.resourceManage.SnsResourceDebate;

public interface SnsResourceDebateMapper {
    int deleteByPrimaryKey(Integer debateId);

    int insert(SnsResourceDebate record);

    int insertSelective(SnsResourceDebate record);

    SnsResourceDebate selectByPrimaryKey(Integer debateId);

    int updateByPrimaryKeySelective(SnsResourceDebate record);

    int updateByPrimaryKey(SnsResourceDebate record);
}