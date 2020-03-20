package com.elearning.dao.systemManage;

import com.elearning.pojo.systemManage.VUserInfo;

import java.util.List;
import java.util.Map;

public interface VUserInfoMapper {
    int insert(VUserInfo record);

    int insertSelective(VUserInfo record);

    List<VUserInfo> queryUserList(Map<String, Object> condition);
}