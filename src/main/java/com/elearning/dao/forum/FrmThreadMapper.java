package com.elearning.dao.forum;

import com.elearning.pojo.forum.FrmThread;

public interface FrmThreadMapper {
    int deleteByPrimaryKey(Integer threadId);

    int insert(FrmThread record);

    int insertSelective(FrmThread record);

    FrmThread selectByPrimaryKey(Integer threadId);

    int updateByPrimaryKeySelective(FrmThread record);

    int updateByPrimaryKeyWithBLOBs(FrmThread record);

    int updateByPrimaryKey(FrmThread record);
}