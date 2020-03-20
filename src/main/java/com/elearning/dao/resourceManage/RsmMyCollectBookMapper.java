package com.elearning.dao.resourceManage;

import com.elearning.pojo.resourceManage.RsmMyCollectBook;

public interface RsmMyCollectBookMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RsmMyCollectBook record);

    int insertSelective(RsmMyCollectBook record);

    RsmMyCollectBook selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RsmMyCollectBook record);

    int updateByPrimaryKey(RsmMyCollectBook record);
}