package com.elearning.service.systemManage;


import com.elearning.common.ServiceResponse;
import com.elearning.pojo.systemManage.UserResource;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface IFindMenuService {

    UserResource selectByPrimaryKey(Integer ID);

    ServiceResponse updateIconList(Map<String,Object> map);

    ServiceResponse getAllIcons(Integer operatorId);

    ServiceResponse getSelectedIcon(Integer operatorId);

    ServiceResponse getAdminMenu(Integer currentRoleId,Map<Integer,List> menuStringMap);


    ServiceResponse changeRole(HttpServletRequest request,Integer operatorId);




}
