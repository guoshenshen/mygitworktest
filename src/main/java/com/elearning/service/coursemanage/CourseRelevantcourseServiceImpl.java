package com.elearning.service.coursemanage;

import com.elearning.dao.coursemanage.CourseRelevantcourseMapper;
import com.elearning.pojo.coursemanage.CourseRelevantcourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 〈<br>
 * 〈〉
 *
 * @author lxx
 * @create 2019/8/15 9:48
 */
@Service("courseRelevantcourseService")
public class CourseRelevantcourseServiceImpl implements ICourseRelevantcourseService{

    @Autowired
    private CourseRelevantcourseMapper courseRelevantcourseMapper;

    @Override
    public List<CourseRelevantcourse> findByMap(Map<String, Object> map) {
        return courseRelevantcourseMapper.findByMap(map);
    }

    @Override
    public int update(CourseRelevantcourse rc) {
        return courseRelevantcourseMapper.updateByPrimaryKeySelective(rc);
    }

    @Override
    public int save(CourseRelevantcourse rc) {
        return courseRelevantcourseMapper.insertSelective(rc);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public int delete(String[] relevantCourseList) {
        int result = 0 ;
        for(int i = 0 ;i < relevantCourseList.length; i++ ){
            result = courseRelevantcourseMapper.deleteByPrimaryKey(Integer.parseInt(relevantCourseList[i]));
        }
        return result;
    }
}
