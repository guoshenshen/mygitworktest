package com.elearning.dao.teacher;

import com.elearning.pojo.teacher.TchrBaseInfoElearning;

public interface TchrBaseInfoElearningMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TchrBaseInfoElearning record);

    int insertSelective(TchrBaseInfoElearning record);

    TchrBaseInfoElearning selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TchrBaseInfoElearning record);

    int updateByPrimaryKeyWithBLOBs(TchrBaseInfoElearning record);

    int updateByPrimaryKey(TchrBaseInfoElearning record);
}