package com.elearning.service.pub;


import com.elearning.pojo.pub.UserVOrganization;
import com.elearning.pojo.pub.UserVOrganizationKey;

import java.util.List;

public interface IUserVOrganizationService {

    UserVOrganization selectByPrimaryKey(UserVOrganizationKey key);

    List<UserVOrganization> getUserVorganizationListByOperatorId(Integer operatorId);


}
