package com.elearning.service.courseStudy;

import com.elearning.common.Constants;
import com.elearning.common.ServiceResponse;
import com.elearning.dao.courseStudy.ChapterSecondcourseMapper;
import com.elearning.pojo.courseStudy.ChapterSecondcourse;
import com.elearning.pojo.courseStudy.UcsEmployeeCourse;
import com.elearning.pojo.pub.Course;
import com.elearning.pojo.pub.EosOperator;
import com.elearning.service.courseStudy.IChapterSecondcourseService;
import com.elearning.service.pub.ICourseService;
import com.elearning.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("chapterSecondcourseService")
public class ChapterSecondcourseServiceImpl implements IChapterSecondcourseService {

    @Autowired
    private ChapterSecondcourseMapper chapterSecondcourseMapper;

    @Autowired
    private ICourseService courseService;

    @Autowired
    private IChapterService chapterService;

    @Autowired
    private IUcsEmployeeCourseService ucsEmployeeCourseService;

    public List<ChapterSecondcourse> getChapterSecondcourseByIdReturnList(Long id){

        return chapterSecondcourseMapper.getChapterSecondcourseByIdReturnList(id);
    }

    @Override
    public List<ChapterSecondcourse> findChapterSecondcourseByMap(Map<String, Object> map) {
        return chapterSecondcourseMapper.findChapterSecondcourseByMap(map);
    }

    @Override
    public int insertSelective(ChapterSecondcourse chapterSecondcourse) {
        return chapterSecondcourseMapper.insertSelective(chapterSecondcourse);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public int orderChapter(Long courseId, String[] chapters) {
        int result = 0 ;
        for (int i = 0; i < chapters.length; i++) {
            Long chapterId = Long.parseLong(chapters[i]);
            Map<String,Object> map = new HashMap<>();
            map.put("courseId",courseId);
            map.put("chapterId",chapterId);
            map.put("type",1);
            ChapterSecondcourse chapterSecondcourse = chapterSecondcourseMapper.findChapterByChapterIdAndType(map);
            chapterSecondcourse.setSortOrder(i+1);
            chapterSecondcourseMapper.updateByPrimaryKeySelective(chapterSecondcourse);
            result ++ ;
        }
        return result;
    }

    @Override
    public ChapterSecondcourse findChapterByChapterIdAndType(Map<String, Object> map) {
        return chapterSecondcourseMapper.findChapterByChapterIdAndType(map);
    }

    @Override
    public int delete(Long id) {
        return chapterSecondcourseMapper.delete(id);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public ServiceResponse orderSecondCourseId(String[] courses, Long chapterId) {
        int result = 0;
        for (int i = 0; i < courses.length; i++) {
            Long courseId = Long.parseLong(courses[i]);
            Map<String,Object> map = new HashMap<>();
            map.put("chapterId",chapterId);
            map.put("courseId",courseId);
            map.put("type",2);
            ChapterSecondcourse chapterSecondcourse = chapterSecondcourseMapper.findChapterByChapterIdAndType(map);
            chapterSecondcourse.setSortOrder(i+1);
            result = chapterSecondcourseMapper.updateByPrimaryKeySelective(chapterSecondcourse);
        }
        if(result > 0 ){
            return ServiceResponse.createBySuccess();
        }
        return ServiceResponse.createByError();
    }

    @Override
    public ServiceResponse deleteSecond(Long chapterId, Long courseId, HttpServletRequest request) {
        int result = 0;
        Course oldCourse = chapterService.findCourseBySecondcourse(courseId);
        Course course = courseService.getCourse(courseId);
        Map<String,Object> map = new HashMap<>();
        map.put("chapterId",chapterId);
        map.put("courseId",courseId);
        map.put("type",2);
        ChapterSecondcourse chapterSecondcourse = chapterSecondcourseMapper.findChapterByChapterIdAndType(map);
        if(courseService.isAllreadyLearned(courseId) && courseService.isAllreadyArranged(courseId)){
            return ServiceResponse.createByErrorCodeMessage(7,"该课件已被使用，不可以删除");
        }
        BigDecimal b   =   new   BigDecimal(oldCourse.getClassHour()-course.getClassHour());
        double   f1   =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
        oldCourse.setClassHour(f1);
        courseService.updateCourse(oldCourse);
        chapterSecondcourseMapper.delete(chapterSecondcourse.getId());
        result = courseService.delete(courseId);

        Integer userId = null;
        EosOperator eosoperator = (EosOperator) request.getSession()
                .getAttribute(Constants.USERINFO_KEY);
        if (eosoperator != null && eosoperator.getOperatorId() != null) {
            userId = eosoperator.getOperatorId();
        }
        map.clear();
        map.put("operatorID",userId);
        map.put("courseId",oldCourse.getCourseId());
        map.put("year", DateTimeUtil.getCurrentYear());
        List<UcsEmployeeCourse> ucsEmployeeCourseList = ucsEmployeeCourseService.findByExample(map);
        if(ucsEmployeeCourseList.size() > 0){
            UcsEmployeeCourse ucsEmployeeCourse  = ucsEmployeeCourseList.get(0);
            //已经完成的课时   ;
            Double newProgress = new   BigDecimal( ucsEmployeeCourse.getStudyTime() / (oldCourse.getClassHour() * 3600) ).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            //会有0.01的误差，防止误差加入0.01
            ucsEmployeeCourse.setStudyProgress(newProgress * 100 > 100 ?  100 : (float) (newProgress * 100));
            ucsEmployeeCourseService.updateByPrimaryKeySelective(ucsEmployeeCourse);
            result ++ ;
        }
        if(result > 0 ){
            return ServiceResponse.createBySuccess();
        }
        return ServiceResponse.createByError();
    }


}
