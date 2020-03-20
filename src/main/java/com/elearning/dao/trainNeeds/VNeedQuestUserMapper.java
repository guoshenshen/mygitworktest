package com.elearning.dao.trainNeeds;

import com.elearning.pojo.trainNeeds.VNeedQuestUser;

public interface VNeedQuestUserMapper {
    int insert(VNeedQuestUser record);

    int insertSelective(VNeedQuestUser record);
}