package com.elearning.dao.resourceManage;

import com.elearning.pojo.resourceManage.RsmRcmbook;

public interface RsmRcmbookMapper {
    int deleteByPrimaryKey(Integer bookId);

    int insert(RsmRcmbook record);

    int insertSelective(RsmRcmbook record);

    RsmRcmbook selectByPrimaryKey(Integer bookId);

    int updateByPrimaryKeySelective(RsmRcmbook record);

    int updateByPrimaryKey(RsmRcmbook record);
}