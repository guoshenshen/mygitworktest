package com.elearning.dao.mixtraining;

import com.elearning.pojo.mixtraining.MtMixTrainOnlineSectionCourse;

public interface MtMixTrainOnlineSectionCourseMapper {
    int deleteByPrimaryKey(Integer ID);

    int insert(MtMixTrainOnlineSectionCourse record);

    int insertSelective(MtMixTrainOnlineSectionCourse record);

    MtMixTrainOnlineSectionCourse selectByPrimaryKey(Integer ID);

    int updateByPrimaryKeySelective(MtMixTrainOnlineSectionCourse record);

    int updateByPrimaryKey(MtMixTrainOnlineSectionCourse record);
}