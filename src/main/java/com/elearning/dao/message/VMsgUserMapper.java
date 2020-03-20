package com.elearning.dao.message;

import com.elearning.pojo.message.VMsgUser;

public interface VMsgUserMapper {
    int insert(VMsgUser record);

    int insertSelective(VMsgUser record);
}