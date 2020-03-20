package com.elearning.service.teacher;

import com.elearning.dao.teacher.TchrTeacherUseCourseMapper;
import com.elearning.pojo.teacher.TchrTeacherUseCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("tchrTeacherUseCourseService")
public class TchrTeacherUseCourseServiceImpl implements ITchrTeacherUseCourseService{

    @Autowired
    private TchrTeacherUseCourseMapper tchrTeacherUseCourseMapper;

    @Override
    public TchrTeacherUseCourse selectByPrimaryKey(Integer id) {

        return tchrTeacherUseCourseMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<TchrTeacherUseCourse> findByTeacherId(Integer teacherId) {

        return tchrTeacherUseCourseMapper.findByTeacherId(teacherId);
    }


}
