package com.elearning.service.coursemanage;

import com.elearning.common.ServiceResponse;
import com.elearning.dao.coursemanage.CourseVideoConvertMapper;
import com.elearning.pojo.coursemanage.CourseVideoConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 〈<br>
 * 〈〉
 *
 * @author lxx
 * @create 2019/8/1 17:13
 */
@Service("courseVideoConvertService")
public class CourseVideoConvertServiceImpl implements ICourseVideoConvertService{
    @Autowired
    private CourseVideoConvertMapper courseVideoConvertMapper;


    @Override
    public ServiceResponse findCourseVideoconvertByCourseId(Long uploadCourseId) {
        CourseVideoConvert courseVideoConvert = courseVideoConvertMapper.findCourseVideoconvertByCourseId(uploadCourseId);
        if(courseVideoConvert != null ){
            return ServiceResponse.createBySuccessMessage(courseVideoConvert.getConvertDescription());
        }else{
            return ServiceResponse.createBySuccessMessage("启动转化流程...");
        }
    }

    @Override
    public CourseVideoConvert getCourseVideoconvertByCourseId(Long courseId) {
        List<CourseVideoConvert> courseVideoConvertList = this.courseVideoConvertMapper.findByCourseId(courseId);
        if(courseVideoConvertList!=null && courseVideoConvertList.size()>0){
            return courseVideoConvertList.get(0);
        } else{
            return null;
        }
    }
}
