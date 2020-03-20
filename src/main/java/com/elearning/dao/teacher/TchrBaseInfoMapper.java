package com.elearning.dao.teacher;

import com.elearning.pojo.teacher.TchrBaseInfo;

import java.util.List;
import java.util.Map;

public interface TchrBaseInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TchrBaseInfo record);

    int insertSelective(TchrBaseInfo record);

    TchrBaseInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TchrBaseInfo record);

    int updateByPrimaryKeyWithBLOBs(TchrBaseInfo record);

    int updateByPrimaryKey(TchrBaseInfo record);

    List<TchrBaseInfo> findByCondition(Map<String, Object> map);
}