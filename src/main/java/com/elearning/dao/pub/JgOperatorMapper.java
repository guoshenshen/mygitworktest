package com.elearning.dao.pub;

import com.elearning.pojo.pub.JgOperator;

public interface JgOperatorMapper {
    int insert(JgOperator record);

    int insertSelective(JgOperator record);
}