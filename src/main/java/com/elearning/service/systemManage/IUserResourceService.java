package com.elearning.service.systemManage;


import com.elearning.pojo.systemManage.UserResource;

import java.util.HashMap;
import java.util.List;

public interface IUserResourceService {

    UserResource selectByPrimaryKey(Integer ID);

    HashMap queryOperatorResourceList(Integer operatorId);

    List queryIconResourceList();




}
