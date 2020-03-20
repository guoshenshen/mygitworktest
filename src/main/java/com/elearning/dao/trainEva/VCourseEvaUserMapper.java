package com.elearning.dao.trainEva;

import com.elearning.pojo.trainEva.VCourseEvaUser;

public interface VCourseEvaUserMapper {
    int insert(VCourseEvaUser record);

    int insertSelective(VCourseEvaUser record);
}