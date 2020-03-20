package com.elearning.dao.pub;

import com.elearning.pojo.pub.UserVOrganization;
import com.elearning.pojo.pub.UserVOrganizationKey;

import java.util.List;

public interface UserVOrganizationMapper {
    int deleteByPrimaryKey(UserVOrganizationKey key);

    int insert(UserVOrganization record);

    int insertSelective(UserVOrganization record);

    UserVOrganization selectByPrimaryKey(UserVOrganizationKey key);

    int updateByPrimaryKeySelective(UserVOrganization record);

    int updateByPrimaryKey(UserVOrganization record);

    List<UserVOrganization> getUserVorganizationListByOperatorId(Integer operatorId);
}