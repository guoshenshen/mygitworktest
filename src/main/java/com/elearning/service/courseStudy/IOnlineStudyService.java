package com.elearning.service.courseStudy;

import com.elearning.common.ServiceResponse;
import com.elearning.pojo.pub.EosOperator;

import java.text.ParseException;
import java.util.Map;

/**
 * 〈<br>
 * 〈〉
 *
 * @author lxx
 * @create 2019/7/15 10:10
 */
public interface IOnlineStudyService {


    /**
     * 导航栏分类
     * @param domainId
     * @return
     */
    ServiceResponse getDomainSystem(Integer domainId);


    /**
     * 学习资源中的课程目录
     * @param map
     * @return
     */
    ServiceResponse getCourseByCondition(Map<String,Object> map);

    /**
     *
     * 课程来源
     * @param conditions
     * @return
     */
    ServiceResponse loadCourseRelatedOrg(Map<String, Object> conditions);

    /**
     *选学按钮
     * @param courseId
     * @param eosOperator
     * @return
     * @throws Exception
     */
    ServiceResponse selectSingleCourse(Long courseId, EosOperator eosOperator) throws Exception;
}
