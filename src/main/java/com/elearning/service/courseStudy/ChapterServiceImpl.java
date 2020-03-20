package com.elearning.service.courseStudy;

import com.elearning.common.Constants;
import com.elearning.common.ServiceResponse;
import com.elearning.common.SnowflakeIdWorker;
import com.elearning.dao.courseStudy.ChapterMapper;
import com.elearning.dao.courseStudy.UcsEmployeeCourseMapper;
import com.elearning.pojo.courseStudy.Chapter;
import com.elearning.pojo.courseStudy.ChapterSecondcourse;
import com.elearning.pojo.courseStudy.UcsEmployeeCourse;
import com.elearning.pojo.pub.Course;
import com.elearning.pojo.pub.EosOperator;
import com.elearning.service.courseStudy.IChapterService;
import com.elearning.service.pub.ICourseService;
import com.elearning.util.DateTimeUtil;
import com.elearning.util.DoubleUtil;
import com.elearning.util.ToolsUtil;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.*;

@Service("chapterService")
public class ChapterServiceImpl implements IChapterService {

    @Autowired
    private ChapterMapper chapterMapper;

    @Autowired
    private UcsEmployeeCourseMapper ucsEmployeeCourseMapper;

    @Autowired
    private IChapterSecondcourseService chapterSecondcourseService;

    @Autowired
    private ICourseService courseService;

    private SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);

    /**
     * 课程目录
     * @param courseId
     * @param userId
     * @return
     */
    @Override
    public ServiceResponse getChaptersByCourseId(Long courseId, int userId) {
        Map<String,Object> map1 = new HashMap<>();
        map1.put("courseId",courseId);
        List<Chapter> chapterList = chapterMapper.findByCourseId(map1);
        if (chapterList.size()>0 ) {
            for (Chapter cha:chapterList) { // 封装章节下课程片段
                map1.clear();
                map1.put("chapterId",cha.getChapterId());
                List<Course> courseList = chapterMapper.findByChapterId(map1);
                for (Course course : courseList){
                    Map<String,Object> map = new HashMap<>();
                    map.put("operatorID",userId);
                    map.put("courseId",course.getCourseId());
                    map.put("year", DateTimeUtil.getCurrentYear());
                    List<UcsEmployeeCourse> ucsEmployeeCourseList = ucsEmployeeCourseMapper.findByExample(map);
                    UcsEmployeeCourse ucsEmployeeCourse = null;
                    if(ucsEmployeeCourseList.size() > 0){
                        ucsEmployeeCourse = ucsEmployeeCourseList.get(0);
                    }
                    if(ucsEmployeeCourse != null ){
                        course.setStudyProgress(ucsEmployeeCourse.getStudyProgress()+"");
                    }else{
                        course.setStudyProgress("0.00");
                    }
                }
                cha.setSubset(courseList);
            }
        }
        return ServiceResponse.createBySuccess(chapterList);
    }

    @Override
    public List<Chapter> findByMap(Map<String, Object> map) {
        List<Chapter> chapterList = chapterMapper.findByCourseId(map);
        if (chapterList.size()>0 ) {
            for (Chapter cha:chapterList) { // 封装章节下课程片段
                map.clear();
                map.put("chapterId",cha.getChapterId());
                cha.setSubset(chapterMapper.findByChapterId(map));
            }
        }
        return chapterList;
    }

    @Override
    public int insertSelective(Chapter chapter) {
        return chapterMapper.insertSelective(chapter);
    }

    @Override
    public Chapter getChapterById(Long chapterId) {
        return chapterMapper.getChapterByChapterIdReturnList(chapterId);
    }

    @Override
    public int updateByPrimaryKeySelective(Chapter chapter) {
        return chapterMapper.updateByPrimaryKeySelective(chapter);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public ServiceResponse inertChapter(Long courseId, String chapterName, Long chapterId) {
        int result ;
        if(chapterId == null){
            Chapter chapter = new Chapter();
            chapter.setChapterName(chapterName);
            chapter.setCreateDate(new Date());
            Long newChapterId = this.idWorker.nextId();
            chapter.setChapterId(newChapterId);
            chapterMapper.insertSelective(chapter);
            ChapterSecondcourse chapterSecondcourse = new ChapterSecondcourse();
            chapterSecondcourse.setId(this.idWorker.nextId());
            chapterSecondcourse.setChapterId(newChapterId);
            chapterSecondcourse.setSecondCourseId(courseId);
            chapterSecondcourse.setType(1);
            Map<String,Object> map = new HashMap<>();
            map.put("courseId",courseId);
            map.put("type",1);
            List<ChapterSecondcourse> chapterSecondcourses = chapterSecondcourseService.findChapterSecondcourseByMap(map);
            if(chapterSecondcourses.size()>0){
                chapterSecondcourse.setSortOrder(chapterSecondcourses.get(chapterSecondcourses.size()-1).getSortOrder()+1);
            }else{
                chapterSecondcourse.setSortOrder(0);
            }
            result = chapterSecondcourseService.insertSelective(chapterSecondcourse);
        }else{
            Chapter chapter = chapterMapper.getChapterByChapterIdReturnList(chapterId);
            chapter.setChapterName(chapterName);
            result = chapterMapper.updateByPrimaryKeySelective(chapter);
        }
        if(result > 0 ){
            return ServiceResponse.createBySuccess();
        }
        return ServiceResponse.createByError();
    }

    @Override
    public List<Course> listCourseByChapterId(Long chapterId) {
        return chapterMapper.listCourseByChapterId(chapterId);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public ServiceResponse deleteChapter(Long courseId, Long chapterId) {
        Course oldCourse = courseService.getCourse(courseId);
        List<Course> list = chapterMapper.listCourseByChapterId(chapterId);
        Double classHour = 0.0;
        if(list.size() > 0){
            for (Course course : list) {
                classHour += course.getClassHour();
                if(courseService.isAllreadyLearned(course.getCourseId())){
                    return ServiceResponse.createByError();
                }
            }
            oldCourse.setClassHour(DoubleUtil.getTwoDigitalDoubleData(oldCourse.getClassHour() - classHour ) < 0 ? 0 :
                    DoubleUtil.getTwoDigitalDoubleData(oldCourse.getClassHour() - classHour ));
        }
        Map<String,Object> map = new HashMap<>();
        map.put("courseId",courseId);
        map.put("chapterId",chapterId);
        map.put("type",1);
        ChapterSecondcourse chapSecondcourse = this.chapterSecondcourseService.findChapterByChapterIdAndType(map);
        chapterSecondcourseService.delete(chapSecondcourse.getId());
        int result = chapterMapper.delete(chapterId);
        if(result > 0 ){
            return ServiceResponse.createBySuccess();
        }
        return ServiceResponse.createByError();
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public ServiceResponse insertChapterSecond(Long courseId, Long chapterId, Double classHour, Integer kindId,
                                               String courseName, HttpServletRequest request) {
        int result = 0;
        Integer userId = null;
        EosOperator eosoperator = (EosOperator) request.getSession()
                .getAttribute(Constants.USERINFO_KEY);
        if (eosoperator != null && eosoperator.getOperatorId() != null) {
            userId = eosoperator.getOperatorId();
        }
        Map<String,Object> map = new HashMap<>();
        map.put("chapterId", chapterId);
        map.put("type", 1);
        ChapterSecondcourse courseChapter= null;
        List<ChapterSecondcourse> chapterSecondcourses = chapterSecondcourseService.findChapterSecondcourseByMap(map);
        if(chapterSecondcourses.size() <= 0 ){
            return ServiceResponse.createByError();
        }
        courseChapter = chapterSecondcourses.get(0);
        Course course = courseService.getCourse(courseChapter.getSecondCourseId());
        Course oldCourse = courseService.getCourse(courseChapter.getSecondCourseId());
        Long newCourseId = new Date().getTime();
        if(courseId == null){
            if(oldCourse.getClassHour() != null && !"".equals(oldCourse.getClassHour())){
                //直接double相加丢失精度
                BigDecimal   b   =   new BigDecimal(oldCourse.getClassHour()+classHour);
                double   f1   =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
                oldCourse.setClassHour(f1);
                courseService.updateCourse(oldCourse);
            }else{
                oldCourse.setClassHour(classHour);
                this.courseService.updateCourse(oldCourse);
            }
            course.setCourseId(newCourseId);
            course.setClassHour(classHour);
            course.setIsCoursePackage(0);
            course.setIsFirstLevelDirectory(0);
            course.setSliceType(kindId+"");
            course.setEnterUrl("");
            course.setServerFilePath(null);
            course.setPictureURL("");
            course.setCourseName(courseName);
            courseService.insert(course);
            ChapterSecondcourse chapterSecondcourse = new ChapterSecondcourse();
            chapterSecondcourse.setId(this.idWorker.nextId());
            chapterSecondcourse.setChapterId(chapterId);
            chapterSecondcourse.setSecondCourseId(newCourseId);
            chapterSecondcourse.setType(2);
            map.put("chapterId",chapterId);
            map.put("type", 2);
            List<ChapterSecondcourse> seconds = this.chapterSecondcourseService.findChapterSecondcourseByMap(map);
            if(seconds.size()>0){
                chapterSecondcourse.setSortOrder(seconds.get(seconds.size()-1).getSortOrder()+1);
            }else{
                chapterSecondcourse.setSortOrder(0);
            }
            result = chapterSecondcourseService.insertSelective(chapterSecondcourse);

        }else if(courseId != null){
            Course updateCourse = courseService.getCourse(courseId);
            if(oldCourse.getClassHour()!=null && !"".equals(oldCourse.getClassHour())){
                BigDecimal   b   =   new   BigDecimal(oldCourse.getClassHour()+Double.parseDouble(request.getParameter("classHour"))-updateCourse.getClassHour());
                double   f1   =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
                oldCourse.setClassHour(f1);
                this.courseService.updateCourse(oldCourse);
            }

            updateCourse.setSliceType(kindId+"");
            updateCourse.setCourseName(courseName);
            updateCourse.setClassHour(classHour);
            updateCourse.setEnterUrl("");
            result = courseService.updateCourse(updateCourse);
        }
        map.clear();
        map.put("operatorID",userId);
        map.put("courseId",oldCourse.getCourseId());
        map.put("year", DateTimeUtil.getCurrentYear());
        List<UcsEmployeeCourse> ucsEmployeeCourseList = ucsEmployeeCourseMapper.findByExample(map);
        if(ucsEmployeeCourseList.size() > 0){
            UcsEmployeeCourse ucsEmployeeCourse = ucsEmployeeCourseList.get(0);
            //已经完成的课时   ;
            Double newProgress = new   BigDecimal( ucsEmployeeCourse.getStudyTime() / (oldCourse.getClassHour() * 3600) ).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            //会有0.01的误差，防止误差加入0.01
            ucsEmployeeCourse.setStudyProgress(newProgress * 100 > 100 ?  100 : (float) (newProgress * 100));
            ucsEmployeeCourseMapper.updateByPrimaryKeySelective(ucsEmployeeCourse);
        }

        if(result > 0){
            return ServiceResponse.createBySuccess();
        }
        return ServiceResponse.createByError();
    }

    @Override
    public ServiceResponse getsecondCourse(String courseName, Long chapterId) {
        Map<String,Object> map = new HashMap<>();
        map.put("chapterId", chapterId);
        map.put("courseName", courseName);
        List<Course> courseList = chapterMapper.findByChapterId(map);
        return ServiceResponse.createBySuccess(courseList);
    }

    @Override
    public Course findCourseBySecondcourse(Long courseId) {
        return chapterMapper.findCourseBySecondcourse(courseId);
    }


}