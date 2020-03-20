package com.elearning.dao.pub;

import com.elearning.pojo.pub.PotentialUser;

public interface PotentialUserMapper {
    int deleteByPrimaryKey(Integer pid);

    int insert(PotentialUser record);

    int insertSelective(PotentialUser record);

    PotentialUser selectByPrimaryKey(Integer pid);

    int updateByPrimaryKeySelective(PotentialUser record);

    int updateByPrimaryKey(PotentialUser record);
}