package com.elearning.service.courseStudy;

import com.elearning.dao.courseStudy.CourseNotebookMapper;
import com.elearning.pojo.courseStudy.CourseNotebook;
import com.elearning.pojo.courseStudy.UscUsertliddayStudyInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 〈<br>
 * 〈〉
 *
 * @author lxx
 * @create 2019/10/12 10:07
 */
@Service("courseNoteBookService")
public class CourseNoteBookServiceImpl implements ICourseNoteBookService {

    @Autowired
    private CourseNotebookMapper courseNotebookMapper;

    @Override
    public List<CourseNotebook> findByMap(Map<String, Object> map) {
        return courseNotebookMapper.findByMap(map);
    }
}
