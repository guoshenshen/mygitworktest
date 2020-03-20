package com.elearning.dao.coursemanage;

import com.elearning.pojo.coursemanage.CoursePubStatus;

public interface CoursePubStatusMapper {
    int insert(CoursePubStatus record);

    int insertSelective(CoursePubStatus record);
}