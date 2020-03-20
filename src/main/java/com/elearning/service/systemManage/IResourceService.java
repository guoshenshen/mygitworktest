package com.elearning.service.systemManage;

import com.elearning.pojo.systemManage.Resource;

import java.util.List;
import java.util.Map;

/**
 * 〈<br>
 * 〈〉
 *
 * @author lxx
 * @create 2019/6/25 11:19
 */
public interface IResourceService {

    Resource selectByPrimaryKey(Integer ID);

    List<Resource> getResourceListByRoleId(Map<String,Object> condition);

    List<Resource> getResourceListByConditionSQL(Map<String,Object> condition);

    List<Resource> queryIconResourceList();

    List<Resource> getAllResource();


}
