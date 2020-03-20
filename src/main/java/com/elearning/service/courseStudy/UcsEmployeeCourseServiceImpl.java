package com.elearning.service.courseStudy;

import com.elearning.common.Tools;
import com.elearning.dao.courseStudy.UcsEmployeeCourseMapper;
import com.elearning.pojo.courseStudy.UcsEmployeeCourse;
import com.elearning.pojo.resourceManage.RsmRcmBookDiscuss;
import com.elearning.service.resourceManage.IRsmRcmbookdiscussService;
import com.elearning.vo.CourseStudy.UserJoinedCourseForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 〈<br>
 * 〈〉
 *
 * @author lxx
 * @create 2019/7/16 13:42
 */
@Service("ucsEmployeeCourseService")
public class UcsEmployeeCourseServiceImpl implements IUcsEmployeeCourseService{

    @Autowired
    private UcsEmployeeCourseMapper ucsEmployeeCourseMapper;

    @Autowired
    private IRsmRcmbookdiscussService rsmRcmbookdiscussService;


    @Override
    public List<UcsEmployeeCourse> findByExample(Map<String, Object> map) {
        return ucsEmployeeCourseMapper.findByExample(map);
    }

    @Override
    public int updateByPrimaryKeySelective(UcsEmployeeCourse ucsEmployeeCourse) {
        return ucsEmployeeCourseMapper.updateByPrimaryKeySelective(ucsEmployeeCourse);
    }

    @Override
    public int insertSelectCourse(UcsEmployeeCourse ucs) {
        return ucsEmployeeCourseMapper.insertSelective(ucs);
    }

    /**
     * currentYear:null 时代表当前年
     * currentYear:-1 时代表忽略此条件
     * */
    @Override
    public UcsEmployeeCourse SelectUcsEmployeecourse(Integer userID,long courseID,Integer currentYear){
        UcsEmployeeCourse ucsEmployeeCourse = null;

        Map<String,Object> parmMap = new HashMap<>();
        parmMap.put("operatorID",userID);
        parmMap.put("Course_id",courseID);

        int _year = new Date().getYear()+1900;
        if(currentYear != null){
            _year = currentYear;
        }
        parmMap.put("year",_year);

        if(currentYear != null && currentYear == -1){
            ucsEmployeeCourse  = this.ucsEmployeeCourseMapper.findByuserIdAndCourseIdWithoutYear(parmMap);
        }else{
            ucsEmployeeCourse  = this.ucsEmployeeCourseMapper.findByuserIdAndCourseId(parmMap);
        }

        return ucsEmployeeCourse;
    }

    @Override
    public List<UserJoinedCourseForm> getUserCourseProgress(Map<String, Object> map) {
        List<UserJoinedCourseForm> userJoinedCourseForms = ucsEmployeeCourseMapper.getUserCourseProgress(map);
        for(UserJoinedCourseForm userJoinedCourseForm : userJoinedCourseForms) {
            Long courseId = userJoinedCourseForm.getCourseId();
            map.clear();
            map.put("bookId", courseId);
            map.put("discussType", 1703);
            List<RsmRcmBookDiscuss> rsmRcmBookDiscusses = rsmRcmbookdiscussService.getRsmRcmbookdiscussList(map);
            Integer totalScore = 0;
            for (RsmRcmBookDiscuss _discuss : rsmRcmBookDiscusses) {
                if (_discuss.getScore() != null) {
                    totalScore = totalScore + _discuss.getScore();
                }
            }
            Integer discussMan = 1;
            if (rsmRcmBookDiscusses.size() > 0) {
                discussMan = rsmRcmBookDiscusses.size();
            }
            String score = Tools.getTwoDigitalData(totalScore.doubleValue() / discussMan);
            userJoinedCourseForm.setDiscussUser(rsmRcmBookDiscusses.size());
            userJoinedCourseForm.setScore(score);
            map.clear();
            map.put("courseId", courseId);
            List<UcsEmployeeCourse> ucsEmployeeCourseList = ucsEmployeeCourseMapper.findByExample(map);
            userJoinedCourseForm.setTotalLearnedUser(ucsEmployeeCourseList.size());
            Integer courseStudyFlag = 0;
            Integer operatorId = (Integer) map.get("operatorID");
            Calendar cal = Calendar.getInstance();
            String year = cal.get(Calendar.YEAR) + "";
            map.put("year", year);
            map.put("operatorID", operatorId);
            ucsEmployeeCourseList = ucsEmployeeCourseMapper.findByExample(map);
            if (ucsEmployeeCourseList.size() > 0 && ucsEmployeeCourseList.get(0).getStudyProgress() != null && ucsEmployeeCourseList.get(0).getStudyProgress() > 0) {
                courseStudyFlag = 2;
            } else {
                courseStudyFlag = 1;
            }
            userJoinedCourseForm.setCourseStudyFlag(courseStudyFlag);
        }
        return userJoinedCourseForms;
    }

    @Override
    public List<UcsEmployeeCourse> findByCourseId(Object courseId) {
        return this.ucsEmployeeCourseMapper.findByCourseId(courseId);
    }


}
