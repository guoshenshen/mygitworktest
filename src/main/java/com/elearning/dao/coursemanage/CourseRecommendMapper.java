package com.elearning.dao.coursemanage;

import com.elearning.pojo.coursemanage.CourseRecommend;
import com.elearning.pojo.coursemanage.CourseRecommendKey;

public interface CourseRecommendMapper {
    int deleteByPrimaryKey(CourseRecommendKey key);

    int insert(CourseRecommend record);

    int insertSelective(CourseRecommend record);

    CourseRecommend selectByPrimaryKey(CourseRecommendKey key);

    int updateByPrimaryKeySelective(CourseRecommend record);

    int updateByPrimaryKey(CourseRecommend record);
}