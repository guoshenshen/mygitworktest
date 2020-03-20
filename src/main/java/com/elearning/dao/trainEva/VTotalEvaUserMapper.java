package com.elearning.dao.trainEva;

import com.elearning.pojo.trainEva.VTotalEvaUser;

public interface VTotalEvaUserMapper {
    int insert(VTotalEvaUser record);

    int insertSelective(VTotalEvaUser record);
}