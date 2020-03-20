package com.elearning.service.coursemanage;

import com.elearning.dao.coursemanage.CourseTypeMapper;
import com.elearning.pojo.coursemanage.CourseType;
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
 * @create 2019/7/29 17:10
 */
@Service("courseTypeService")
public class CourseTypeServiceImpl implements ICourseTypeService{

    @Autowired
    private CourseTypeMapper courseTypeMapper;


    @Override
    public List<CourseType> findByMap(Map<String, Object> map) {
        return courseTypeMapper.findByMap(map);
    }

    @Override
    public CourseType selectByPrimaryKey(Integer courseTypeID) {
        return courseTypeMapper.selectByPrimaryKey(courseTypeID);
    }

    @Override
    public CourseType findByCourseId(long id) {
        return courseTypeMapper.findByCourseId(id);
    }

    @Override
    public List<CourseType> findByCourseName(String courseName) {
        return courseTypeMapper.findByCourseName(courseName);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public String getCourseTypeNameByCourseId(Long courseId) {
        String result = "";
        List<String> list = this.courseTypeMapper.getCourseTypeNameByCourseId(courseId);
        if(list.size()>0){
            result = list.get(0).toString();
        }
        return result;
    }




}
