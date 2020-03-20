package com.elearning.dao.recommend;

import com.elearning.pojo.recommend.ReceiverItem;

public interface ReceiverItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ReceiverItem record);

    int insertSelective(ReceiverItem record);

    ReceiverItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ReceiverItem record);

    int updateByPrimaryKey(ReceiverItem record);
}