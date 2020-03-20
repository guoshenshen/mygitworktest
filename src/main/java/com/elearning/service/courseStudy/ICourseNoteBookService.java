package com.elearning.service.courseStudy;

import com.elearning.pojo.courseStudy.CourseNotebook;

import java.util.List;
import java.util.Map; /**
 * 〈<br>
 * 〈〉
 *
 * @author lxx
 * @create 2019/10/12 10:07
 */
public interface ICourseNoteBookService {
    List<CourseNotebook> findByMap(Map<String, Object> map);
}
