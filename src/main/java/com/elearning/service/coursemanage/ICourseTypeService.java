package com.elearning.service.coursemanage;

import com.elearning.pojo.coursemanage.CourseType;

import java.util.List;
import java.util.Map; /**
 * 〈<br>
 * 〈〉
 *
 * @author lxx
 * @create 2019/7/29 17:10
 */
public interface ICourseTypeService {

    List<CourseType> findByMap(Map<String, Object> map);

    CourseType selectByPrimaryKey(Integer courseTypeID);

    //根据课程id获取他的courseType
    CourseType findByCourseId(long id);

    List<CourseType> findByCourseName(String courseName);

    String getCourseTypeNameByCourseId(Long courseId);


}
