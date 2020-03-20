package com.elearning.dao.courseStudy;

import com.elearning.pojo.courseStudy.UcsUserScoInfo;

import java.util.List;
import java.util.Map;

public interface UcsUserScoInfoMapper {
    int deleteByPrimaryKey(Integer ID);

    int insert(UcsUserScoInfo record);

    int insertSelective(UcsUserScoInfo record);

    UcsUserScoInfo selectByPrimaryKey(Integer ID);

    int updateByPrimaryKeySelective(UcsUserScoInfo record);

    int updateByPrimaryKey(UcsUserScoInfo record);

    List<UcsUserScoInfo> findByMap(Map<String, Object> map);
}