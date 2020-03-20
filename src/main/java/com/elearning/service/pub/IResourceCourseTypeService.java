package com.elearning.service.pub;

import com.elearning.common.ServiceResponse;
import com.elearning.pojo.pub.ResourceCourseType;

import java.util.List;

/**
 * 〈<br>
 * 〈〉
 *
 * @author lxx
 * @create 2019/7/29 16:07
 */
public interface IResourceCourseTypeService {
    /**
     * 获取某部门对应的资源关联领域
     * @param resourceId
     * @param resourceType
     * @return
     */
    ServiceResponse<ResourceCourseType> findByResourceId(Long resourceId, Integer resourceType);
}
