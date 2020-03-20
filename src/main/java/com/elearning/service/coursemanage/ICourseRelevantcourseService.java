package com.elearning.service.coursemanage;

import com.elearning.pojo.coursemanage.CourseRelevantcourse;

import java.util.List;
import java.util.Map; /**
 * 〈<br>
 * 〈〉
 *
 * @author lxx
 * @create 2019/8/15 9:48
 */
public interface ICourseRelevantcourseService {
    List<CourseRelevantcourse> findByMap(Map<String, Object> map);

    int update(CourseRelevantcourse rc);

    int save(CourseRelevantcourse rc);

    int delete(String[] relevantCourseList);
}
