package com.elearning.service.mixtraining;

import com.elearning.common.Constants;
import com.elearning.dao.coursemanage.CourseTypeMapper;
import com.elearning.dao.mixtraining.MtMixTrainScheduleItemInfoMapper;
import com.elearning.dao.teacher.TchrBaseInfoMapper;
import com.elearning.dao.teacher.TchrTeacherUseCourseMapper;
import com.elearning.pojo.coursemanage.CourseCoursetype;
import com.elearning.pojo.coursemanage.CourseType;
import com.elearning.pojo.mixtraining.MtMixTrainScheduleItemInfo;
import com.elearning.pojo.pub.Course;
import com.elearning.pojo.teacher.TchrBaseInfo;
import com.elearning.pojo.teacher.TchrTeacherUseCourse;
import com.elearning.service.coursemanage.ICourseCoursetypeService;
import com.elearning.service.coursemanage.ICourseTypeService;
import com.elearning.service.pub.ICourseService;
import com.elearning.service.teacher.ITchrBaseInfoService;
import com.elearning.util.DateTimeUtil;
import com.elearning.util.PropertiesUtil;
import com.elearning.util.ToolsUtil;
import com.elearning.vo.mixtraining.MtMixTrainScheduleItemForm;
import com.elearning.vo.teacher.TchrBaseInfoForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 〈<br>
 * 〈〉
 *
 * @author lxx
 * @create 2019/7/31 10:09
 */
@Service("mtMixTrainScheduleItemInfoService")
public class MtMixTrainScheduleItemInfoService implements IMtMixTrainScheduleItemInfoService{


    @Autowired
    private TchrBaseInfoMapper tchrBaseInfoMapper;

    @Autowired
    private ITchrBaseInfoService tchrBaseInfoService;

    @Autowired
    private CourseTypeMapper courseTypeMapper;

    /*@Autowired
    private CourseMapper courseMapper;*/

    @Autowired
    private MtMixTrainScheduleItemInfoMapper mtMixTrainScheduleItemInfoMapper;

    @Autowired
    private TchrTeacherUseCourseMapper tchrTeacherUseCourseMapper;

    @Autowired
    private ICourseService courseService;

    @Autowired
    private ICourseTypeService courseTypeService;

    @Autowired
    private ICourseCoursetypeService courseCoursetypeService;



    @Override
    @Transactional(rollbackFor = {Exception.class})
    public int synTeacher(MtMixTrainScheduleItemInfo mtScheduleItemForm, int orgId, int operatorId) {
        Map<String,Object> map = new HashMap<>();
        int teacherId=0;
        int tenantId= Integer.parseInt(PropertiesUtil.getProperty("tenantId"));
        String teacherName=mtScheduleItemForm.getTeacherName();
        if(teacherName!=null&&!"".equals(teacherName.trim())){
            map.put("teacherNameEq",teacherName);
            map.put("orgId",orgId);
            List<TchrBaseInfo> teacherList=tchrBaseInfoMapper.findByCondition(map);
            if(teacherList.size()==0&&teacherList.isEmpty()){
                map.clear();
                map.put("courseName","缺省分类");
                List<CourseType> courseTypeList = courseTypeMapper.findByMap(map);
                Integer defaultExpertAreaId = null;
                if(courseTypeList.size() > 0 ){
                    defaultExpertAreaId = courseTypeList.get(0).getCourseTypeID();
                }
                TchrBaseInfo teacher=new TchrBaseInfo();
                teacher.setTeacherName(teacherName);
                teacher.setOrgId(orgId);
                teacher.setIsUnderScope(1181);
                teacher.setIsShared(false);
                teacher.setGender(1);
                teacher.setOperatorId(operatorId);
                teacher.setTenantId(tenantId);
                teacher.setExpertAreaId(defaultExpertAreaId);
                teacher.setOpenScope(2201);
                teacher.setCreateDate(new Date());
                teacher.setIsNoted(0);
                teacher.setIsSendToIndex(0);
                teacher.setIsLinkSchedule(1);
                teacher.setHireStyle(0);
                tchrBaseInfoMapper.insertSelective(teacher);
                teacherId = teacher.getId();
                teacher.setSourceTeacherId(teacherId);
                tchrBaseInfoMapper.updateByPrimaryKeySelective(teacher);
            }
            else{
                teacherId=teacherList.get(0).getId();
            }
        }
        map.clear();
        map.put("courseId",mtScheduleItemForm.getCourseId());
        map.put("courseName",mtScheduleItemForm.getCourseName());
        map.put("teacherId",teacherId);
        map.put("isCourse",1);
        List<TchrTeacherUseCourse> teacherusecourseList=tchrTeacherUseCourseMapper.findByMap(map);
        if(teacherusecourseList==null||teacherusecourseList.size()==0){
            TchrTeacherUseCourse teacherusecourse=new TchrTeacherUseCourse();
            teacherusecourse.setCourseID(mtScheduleItemForm.getCourseId());
            teacherusecourse.setCourseName(mtScheduleItemForm.getCourseName());
            teacherusecourse.setTeacherID(teacherId);
            teacherusecourse.setIsCourse(1);
           tchrTeacherUseCourseMapper.insertSelective(teacherusecourse);
        }
        return teacherId;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public int synTeacher(MtMixTrainScheduleItemForm mtScheduleItemForm, int orgId, int operatorId) {
        Map<String,Object> map = new HashMap<>();
        int teacherId=0;
        int tenantId= Integer.parseInt(PropertiesUtil.getProperty("tenantId"));
        String teacherName=mtScheduleItemForm.getTeacherName();
        if(teacherName!=null&&!"".equals(teacherName.trim())){
            map.put("teacherNameEq",teacherName);
            map.put("orgId",orgId);
            List<TchrBaseInfo> teacherList=tchrBaseInfoMapper.findByCondition(map);
            if(teacherList.size()==0&&teacherList.isEmpty()){
                map.clear();
                map.put("courseName","缺省分类");
                List<CourseType> courseTypeList = courseTypeMapper.findByMap(map);
                Integer defaultExpertAreaId = null;
                if(courseTypeList.size() > 0 ){
                    defaultExpertAreaId = courseTypeList.get(0).getCourseTypeID();
                }
                TchrBaseInfo teacher=new TchrBaseInfo();
                teacher.setTeacherName(teacherName);
                teacher.setOrgId(orgId);
                teacher.setIsUnderScope(1181);
                teacher.setIsShared(false);
                teacher.setGender(1);
                teacher.setOperatorId(operatorId);
                teacher.setTenantId(tenantId);
                teacher.setExpertAreaId(defaultExpertAreaId);
                teacher.setOpenScope(2201);
                teacher.setCreateDate(new Date());
                teacher.setIsNoted(0);
                teacher.setIsSendToIndex(0);
                teacher.setIsLinkSchedule(1);
                teacher.setHireStyle(0);
                tchrBaseInfoMapper.insertSelective(teacher);
                teacherId = teacher.getId();
                teacher.setSourceTeacherId(teacherId);
                tchrBaseInfoMapper.updateByPrimaryKeySelective(teacher);
            }
            else{
                teacherId=teacherList.get(0).getId();
            }
        }
        map.clear();
        map.put("courseId",mtScheduleItemForm.getCourseId());
        map.put("courseName",mtScheduleItemForm.getCourseName());
        map.put("teacherId",teacherId);
        map.put("isCourse",1);
        List<TchrTeacherUseCourse> teacherusecourseList=tchrTeacherUseCourseMapper.findByMap(map);
        if(teacherusecourseList==null||teacherusecourseList.size()==0){
            TchrTeacherUseCourse teacherusecourse=new TchrTeacherUseCourse();
            teacherusecourse.setCourseID(mtScheduleItemForm.getCourseId());
            teacherusecourse.setCourseName(mtScheduleItemForm.getCourseName());
            teacherusecourse.setTeacherID(teacherId);
            teacherusecourse.setIsCourse(1);
            tchrTeacherUseCourseMapper.insertSelective(teacherusecourse);
        }
        return teacherId;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void dropScheduleCourseAndTeacher(MtMixTrainScheduleItemInfo mtScheduleItemForm, int operatorFlag ) {
        Map<String,Object> map = new HashMap<>();
        int teacherId = mtScheduleItemForm.getTeacherId();
        long courseId = mtScheduleItemForm.getCourseId();
        map.put("teacherId",teacherId);
        List<TchrTeacherUseCourse> teacherusecourseList = tchrTeacherUseCourseMapper.findByMap(map);
        int courseNum = 0;
        if(teacherusecourseList != null && teacherusecourseList.size() > 0){
            for(TchrTeacherUseCourse teacherCourse:teacherusecourseList){
                if(teacherCourse.getIsCourse() == 1){
                    courseNum++;
                }
            }
        }
        map.put("courseId",courseId);
        List<TchrTeacherUseCourse> _teacherusecourse = tchrTeacherUseCourseMapper.findByMap(map);
        if(_teacherusecourse != null && _teacherusecourse.size() > 0){
            for(TchrTeacherUseCourse teacherusecourse2 : _teacherusecourse){
                tchrTeacherUseCourseMapper.deleteByPrimaryKey(teacherusecourse2.getId());
            }

        }
        if(operatorFlag == 0){       //来自课程库的请求
            if(courseNum == 1){
                TchrBaseInfo teacher = tchrBaseInfoMapper.selectByPrimaryKey(teacherId);
                map.clear();
                map.put("teacherId",teacher.getId());
                List<MtMixTrainScheduleItemInfo> mtMixTrainScheduleItemInfos = mtMixTrainScheduleItemInfoMapper.findByMap(map);
                if(teacher != null){
                    if( mtMixTrainScheduleItemInfos == null || mtMixTrainScheduleItemInfos.size() == 0){
                        tchrBaseInfoMapper.deleteByPrimaryKey(teacher.getId());
                    }
                }
            }
        }else if(operatorFlag==1){   //来自培训日程的请求
            Course course = this.courseService.getCourse(courseId);
            if(course!=null){
                this.courseService.deleteByPrimaryKey(course.getCourseId());
            }
            map.clear();
            map.put("teacherId",teacherId);
            List<Course> _courseList = this.courseService.listCourseByMap(map);
            if(_courseList==null||_courseList.size()==0){
                TchrBaseInfo teacher = tchrBaseInfoMapper.selectByPrimaryKey(teacherId);
                if(teacher!=null){
                    tchrBaseInfoMapper.deleteByPrimaryKey(teacher.getId());
                }
            }
        }
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void dropScheduleCourseAndTeacher(MtMixTrainScheduleItemForm mtScheduleItemForm, int operatorFlag ) {
        Map<String,Object> map = new HashMap<>();
        int teacherId = mtScheduleItemForm.getTeacherId();
        long courseId = mtScheduleItemForm.getCourseId();
        map.put("teacherId",teacherId);
        List<TchrTeacherUseCourse> teacherusecourseList = tchrTeacherUseCourseMapper.findByMap(map);
        int courseNum = 0;
        if(teacherusecourseList != null && teacherusecourseList.size() > 0){
            for(TchrTeacherUseCourse teacherCourse:teacherusecourseList){
                if(teacherCourse.getIsCourse() == 1){
                    courseNum++;
                }
            }
        }
        map.put("courseId",courseId);
        List<TchrTeacherUseCourse> _teacherusecourse = tchrTeacherUseCourseMapper.findByMap(map);
        if(_teacherusecourse != null && _teacherusecourse.size() > 0){
            for(TchrTeacherUseCourse teacherusecourse2 : _teacherusecourse){
                tchrTeacherUseCourseMapper.deleteByPrimaryKey(teacherusecourse2.getId());
            }

        }
        if(operatorFlag == 0){       //来自课程库的请求
            if(courseNum == 1){
                TchrBaseInfo teacher = tchrBaseInfoMapper.selectByPrimaryKey(teacherId);
                map.clear();
                map.put("teacherId",teacher.getId());
                List<MtMixTrainScheduleItemInfo> mtMixTrainScheduleItemInfos = mtMixTrainScheduleItemInfoMapper.findByMap(map);
                if(teacher != null){
                    if( mtMixTrainScheduleItemInfos == null || mtMixTrainScheduleItemInfos.size() == 0){
                        tchrBaseInfoMapper.deleteByPrimaryKey(teacher.getId());
                    }
                }
            }
        }else if(operatorFlag==1){   //来自培训日程的请求
            Course course = this.courseService.getCourse(courseId);
            if(course!=null){
                this.courseService.deleteByPrimaryKey(course.getCourseId());
            }
            map.clear();
            map.put("teacherId",teacherId);
            List<Course> _courseList = this.courseService.listCourseByMap(map);
            if(_courseList==null||_courseList.size()==0){
                TchrBaseInfo teacher = tchrBaseInfoMapper.selectByPrimaryKey(teacherId);
                if(teacher!=null){
                    tchrBaseInfoMapper.deleteByPrimaryKey(teacher.getId());
                }
            }
        }
    }

    @Override
    public List<MtMixTrainScheduleItemInfo> findByMap(Map<String, Object> map) {
        return mtMixTrainScheduleItemInfoMapper.findByMap(map);
    }

    @Override
    public int insert(MtMixTrainScheduleItemInfo record) {
        return mtMixTrainScheduleItemInfoMapper.insert(record);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return mtMixTrainScheduleItemInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public MtMixTrainScheduleItemInfo findById(Integer id) {
        return mtMixTrainScheduleItemInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public int update(MtMixTrainScheduleItemInfo scheduleItem) {
        return mtMixTrainScheduleItemInfoMapper.updateByPrimaryKeySelective(scheduleItem);
    }

    @Override
    public List<MtMixTrainScheduleItemInfo> findByScheduleId(Integer scheduleId){

        return this.mtMixTrainScheduleItemInfoMapper.findByScheduleId(scheduleId);
    }

    @Override
    public List<MtMixTrainScheduleItemInfo> getScheduleOnlineItemList(List<MtMixTrainScheduleItemInfo> scheduleItemList){

        List<MtMixTrainScheduleItemInfo> onlineItemList = new ArrayList<>();
        if(scheduleItemList != null && scheduleItemList.size()>0){
            for(MtMixTrainScheduleItemInfo item:scheduleItemList){
                if(item.getOnOrOffLineFlag().intValue() == 0) {  //0:线上
                    if(item.getCourseId() != null){
                        Course course = this.courseService.findCourseById(item.getCourseId());
                        if(course!=null){
                            item.setTeacherName(course.getCreator());
                        }
                    }
                    onlineItemList.add(item);
                }
            }
        }
        return onlineItemList;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public List<MtMixTrainScheduleItemInfo> getScheduleOfflineItemList(List<MtMixTrainScheduleItemInfo> scheduleItemList){

        List<MtMixTrainScheduleItemInfo> offlineItemList = new ArrayList();
        if(scheduleItemList!=null&&scheduleItemList.size()>0){
            for(MtMixTrainScheduleItemInfo item:scheduleItemList){
                if(item.getOnOrOffLineFlag().intValue() == 1){  //1:线下
                    Date _date = item.getScheduleDate();
                    if(_date != null){
                        item.setScheduleDate(java.sql.Date.valueOf(DateTimeUtil.dateToStr(_date)));
                    }
                    offlineItemList.add(item);
                }
            }
        }
        Collections.sort(offlineItemList, ToolsUtil.sortoffLineScheduleItemSeq());
        return offlineItemList;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public List<Course> getCourseListByTrainId(int trainId){

        List<Course> courseList=this.mtMixTrainScheduleItemInfoMapper.findCourseListByTrainId(trainId);
        return courseList;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public List<Course> getOfflineCourseList(int scheduleId){

        List<Course> courseList=new ArrayList();
        List<MtMixTrainScheduleItemInfo> scheduleItemInfoList=this.mtMixTrainScheduleItemInfoMapper.findByScheduleId(scheduleId);
        Course c = null;
        if(scheduleItemInfoList!=null&&scheduleItemInfoList.size()>0){
            for(MtMixTrainScheduleItemInfo item : scheduleItemInfoList){
                if(item.getCourseId()!=null && item.getOnOrOffLineFlag().intValue()==1 && item.getIsTrainTheme().intValue()==1){
                    int count=0;
                    for(Course course:courseList){
                        if(course.getCourseId().intValue()==item.getCourseId().intValue())break;
                        count++;
                    }
                    if(courseList.size()==count){
                        c = courseService.findCourseById(item.getCourseId());
                    }
                    if(c!=null){
                        courseList.add(courseService.findCourseById(item.getCourseId()));
                    }
                }
            }
        }
        return courseList;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public boolean judgeCourseIdInSchedule(int scheduleId,long courseId){
        List<MtMixTrainScheduleItemInfo> scheduleItemInfoList=this.mtMixTrainScheduleItemInfoMapper.findByScheduleId(scheduleId);
        Long _courseId=Long.valueOf(courseId);
        if(scheduleItemInfoList!=null && scheduleItemInfoList.size()>0){
            for(MtMixTrainScheduleItemInfo item:scheduleItemInfoList){
                if(item.getCourseId().intValue() == _courseId.intValue())
                    return true;
            }
        }
        return false;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public void saveOrUpdate(MtMixTrainScheduleItemInfo temp){

        Map<String,Object> parmMap = new HashMap<>();
        parmMap.put("scheduleId",temp.getScheduleId());
        parmMap.put("courseId",temp.getCourseId());
        parmMap.put("onOrOffLineFlag",temp.getOnOrOffLineFlag());
        parmMap.put("operatorId",temp.getOperatorId());

        List<MtMixTrainScheduleItemInfo> mtScheduleItemInfoList=this.mtMixTrainScheduleItemInfoMapper.getListBySchAndCouAndOnAndOpe(parmMap);

        if(mtScheduleItemInfoList==null||mtScheduleItemInfoList.isEmpty()){
            this.mtMixTrainScheduleItemInfoMapper.insert(temp);
        }else{
            for(MtMixTrainScheduleItemInfo item:mtScheduleItemInfoList){
                if(temp.getOnOrOffLineFlag().intValue()==0){
                    item.setCourseId(temp.getCourseId());
                    item.setCourseName(temp.getCourseName());
                    item.setScheduleStartTime(temp.getScheduleStartTime());
                    item.setScheduleEndTime(temp.getScheduleEndTime());
                    item.setScheduleId(temp.getScheduleId());
                    item.setOperatorId(temp.getOperatorId());
                    item.setId(item.getId());
                }else{
                    item.setCourseId(temp.getCourseId());
                    item.setCourseName(temp.getCourseName());
                    item.setScheduleDate(temp.getScheduleDate());
                    item.setScheduleStartTime(temp.getScheduleStartTime());
                    item.setScheduleEndTime(temp.getScheduleEndTime());
                    item.setScheduleId(temp.getScheduleId());
                    item.setTeacherId(temp.getTeacherId());
                    item.setTeacherName(temp.getTeacherName());
                    item.setOperatorId(temp.getOperatorId());
                    item.setId(item.getId());
                    item.setFileName(temp.getFileName());
                    item.setFilePath(temp.getFilePath());
                }
                this.mtMixTrainScheduleItemInfoMapper.updateByPrimaryKeySelective(item);
            }
        }
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public long synCourse(MtMixTrainScheduleItemForm scheduleItemForm, int orgId, String operatorName){
        long courseId = -1;
        String courseName=scheduleItemForm.getCourseName();
        int isTrainTheme=scheduleItemForm.getIsTrainTheme();
        int tenantId=Constants.tenantId;
        if(isTrainTheme==1){          //1:代表是培训主题
            Course course=new Course();
            course.setCourseName(courseName);
            course.setOrgId(orgId);

            Map<String,Object> parmMap = new HashMap<>();
            parmMap.put("courseName",courseName);
            parmMap.put("orgId",orgId);

            List<Course> courseList=this.courseService.getListByCourseNameAndOrgId(parmMap);
            int count=0;
            if(courseList!=null&&courseList.size()>0){
                for(Course _course:courseList){
                    if(_course.getCreator().equals(scheduleItemForm.getTeacherName()))break;
                    count++;
                }
            }
            if(courseList.size()==0 && courseList.isEmpty()||courseList.size()>0&&courseList.size()==count){
                List<CourseType> courseTypeList=this.courseTypeService.findByCourseName("缺省分类");
                int defaultExpertAreaId = courseTypeList.get(0).getCourseTypeID();
                long _courseId = (new Date()).getTime();
                course.setCourseId(_courseId);
                Random random = new Random();
                course.setCourseNo(String.valueOf(Math.abs(random.nextInt())));
                course.setPubStatus(1091);
                course.setIsOpenCourse("0");
                course.setApproveStatus("1051");
                course.setIsSharedCourse("0");
                course.setSliceType("3");
                course.setStudyDay(0);
                course.setClassHour(2.0);
                course.setCreator(scheduleItemForm.getTeacherName());
                course.setScore(0.0);
                course.setCreateDate(new Date());
                course.setTenantId(tenantId);
                course.setSourceTenantId(tenantId);
                course.setOpenScope(2201);
                course.setExpertAreaId(String.valueOf(defaultExpertAreaId));
                course.setStationId("2901");
                course.setSourceCourseId(_courseId);
                course.setBreed("3004");
                course.setMaker(operatorName);
                course.setIsFormal(1);
                course.setHasTeacher(1);
                course.setIsMultiSize(0);
                course.setIsFirstLevelDirectory(1);
                courseId=this.courseService.insert(course);

                CourseCoursetype courseCoursetype = new CourseCoursetype();
                courseCoursetype.setCourse_id(_courseId);
                courseCoursetype.setCourseType_ID(defaultExpertAreaId);

                this.courseCoursetypeService.insert(courseCoursetype);
            } else{
                courseId=courseList.get(0).getCourseId();
            }
        }
        return courseId;

    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public List<TchrBaseInfoForm> getTeacherFormListByScheduleId(int scheduleId) {
        List<TchrBaseInfoForm> teacherFormList = new ArrayList<>();
        List<MtMixTrainScheduleItemInfo> scheduleItemInfoList=this.mtMixTrainScheduleItemInfoMapper.findByScheduleId(scheduleId);

        if(scheduleItemInfoList!=null && scheduleItemInfoList.size()>0){
            for(MtMixTrainScheduleItemInfo item : scheduleItemInfoList){
                if(item.getTeacherId()!=null && item.getOnOrOffLineFlag().intValue()==1 && item.getTeacherId().intValue()!=0){
                    int count=teacherFormList.size();
                    for(TchrBaseInfoForm teacher : teacherFormList){
                        if(teacher.getTeacherId().intValue() == item.getTeacherId().intValue()){
                            count=-1;
                            break;
                        }
                    }
                    if(teacherFormList.size() == count){
                        if(item.getTeacherId() != null){

                            teacherFormList.add(this.tchrBaseInfoService.getTeacherInfoForm(this.tchrBaseInfoService.findById(item.getTeacherId())));
                        }
                    }
                }
            }
        }
        return teacherFormList;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public List<String> ListSortByscheduleId(Integer scheduleId) {
        //return this.mtMixTrainScheduleItemInfoMapper.ListSortByscheduleId(scheduleId);
        return new ArrayList<>();
    }



}
