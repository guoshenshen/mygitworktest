package com.elearning.service.pub;

import com.elearning.common.ServiceResponse;
import com.elearning.dao.pub.ResourceCourseTypeMapper;
import com.elearning.pojo.pub.ResourceCourseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 〈<br>
 * 〈〉
 *
 * @author lxx
 * @create 2019/7/29 16:07
 */
@Service("resourceCourseTypeService")
public class ResourceCourseTypeServiceImpl implements IResourceCourseTypeService{


    @Autowired
    private ResourceCourseTypeMapper resourceCourseTypeMapper;


    /**
     * 获取某部门对应的资源关联领域
     * @param resourceId
     * @param resourceType
     * @return
     */
    @Override
    public ServiceResponse<ResourceCourseType> findByResourceId(Long resourceId, Integer resourceType) {
        try {
            List<ResourceCourseType> resourceCourseTypes = resourceCourseTypeMapper.findByResourceId(resourceId,resourceType);
            if(resourceCourseTypes != null || resourceCourseTypes.size() > 0){
                return ServiceResponse.createBySuccess(resourceCourseTypes.get(0));
            }
        }catch (Exception e){
            return ServiceResponse.createByErrorMessage("单位关联领域获取失败");
        }
        return ServiceResponse.createBySuccess();
    }
}
