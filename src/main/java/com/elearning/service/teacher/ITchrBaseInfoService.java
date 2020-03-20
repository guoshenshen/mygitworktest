package com.elearning.service.teacher;

import com.elearning.common.ServiceResponse;
import com.elearning.pojo.teacher.TchrBaseInfo;
import com.elearning.vo.teacher.TchrBaseInfoForm;

import java.util.Map;

/**
 * 〈<br>
 * 〈〉
 *
 * @author lxx
 * @create 2019/7/29 17:05
 */
public interface ITchrBaseInfoService {
    TchrBaseInfo findById(int teacherId);

    /**
     * 新增课程时教师列表
     * @param map
     * @return
     */
    ServiceResponse getTeacherByConditionByPage(Map<String, Object> map);

    /**
     * 判断教师名称是否存在
     * @param teacherName
     * @param orgId
     * @return
     */
    ServiceResponse judgeTeacher(String teacherName, Long orgId);

    TchrBaseInfoForm getTeacherInfoForm(TchrBaseInfo tempTeacherBaseinfo);

}
