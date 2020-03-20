package com.elearning.dao.pub;

import com.elearning.pojo.pub.CigitUsers;

public interface CigitUsersMapper {
    int insert(CigitUsers record);

    int insertSelective(CigitUsers record);
}