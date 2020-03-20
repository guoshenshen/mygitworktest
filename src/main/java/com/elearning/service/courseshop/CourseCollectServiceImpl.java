package com.elearning.service.courseshop;

import com.elearning.common.ServiceResponse;
import com.elearning.dao.courseshop.CourseCollectMapper;
import com.elearning.pojo.courseshop.CourseCollect;
import com.elearning.pojo.pub.EosOperator;
import com.elearning.util.PropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;


/**
 * 〈<br>
 * 〈〉
 *
 * @author lxx
 * @create 2019/7/26 9:57
 */
@Service("courseCollectService")
public class CourseCollectServiceImpl implements ICourseCollectService{

    @Autowired
    private CourseCollectMapper courseCollectMapper;


    @Override
    public ServiceResponse updateCourseCollectStatus(Long courseId, Integer collectId, int orgId, EosOperator operator) {
        int result = 0;
        Integer tenantId = Integer.parseInt(PropertiesUtil.getProperty("tenantId"));
        Integer operatorId = operator.getOperatorId();
        CourseCollect courseCollect = new CourseCollect();
        courseCollect.setCourseId(courseId);
        courseCollect.setOperatorId(operatorId);
        courseCollect.setTenantId(tenantId);
        courseCollect.setOrgId(orgId);
        if(collectId == 2911){
            courseCollect.setCreateDate(new Date());
            result = courseCollectMapper.insertSelective(courseCollect);
        }else if(collectId == 2912){
            if(operatorId != 0 ){
                CourseCollect alreadyCollect=new CourseCollect();
                alreadyCollect.setCourseId(courseId);
                alreadyCollect.setOperatorId(operatorId);
                Map<String,Object> map = new HashMap<>();
                map.put("courseId",courseId);
                map.put("operatorId",operatorId);
                List<CourseCollect> courses = courseCollectMapper.findByExample(map);
                if(courses != null && courses.size()>0){
                    result = courseCollectMapper.deleteByPrimaryKey(courses.get(0).getId());
                }
            }
        }
        if(result > 0 ){
            return ServiceResponse.createBySuccessMessage("修改成功");
        }
        return ServiceResponse.createByErrorMessage("修改失败");
    }
}
