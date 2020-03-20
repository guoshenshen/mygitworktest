package com.elearning.dao.pub;

import com.elearning.pojo.pub.CigitOrg;

public interface CigitOrgMapper {
    int insert(CigitOrg record);

    int insertSelective(CigitOrg record);
}