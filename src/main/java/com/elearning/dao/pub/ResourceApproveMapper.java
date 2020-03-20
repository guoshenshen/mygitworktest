package com.elearning.dao.pub;

import com.elearning.pojo.pub.ResourceApprove;

public interface ResourceApproveMapper {
    int insert(ResourceApprove record);

    int insertSelective(ResourceApprove record);
}