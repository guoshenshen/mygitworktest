package com.elearning.dao.pub;

import com.elearning.pojo.pub.LiveTeacher;

public interface LiveTeacherMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LiveTeacher record);

    int insertSelective(LiveTeacher record);

    LiveTeacher selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LiveTeacher record);

    int updateByPrimaryKey(LiveTeacher record);
}