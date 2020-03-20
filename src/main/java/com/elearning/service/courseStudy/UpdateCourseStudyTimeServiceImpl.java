package com.elearning.service.courseStudy;

import com.elearning.common.*;
import com.elearning.pojo.courseStudy.UcsEmployeeCourse;
import com.elearning.pojo.courseStudy.UscUsertliddayStudyInfo;
import com.elearning.pojo.pub.Course;
import com.elearning.pojo.pub.EosOperator;
import com.elearning.pojo.systemManage.Log;
import com.elearning.service.pub.ICourseService;
import com.elearning.service.statistics.IUscUserLearnStaticsService;
import com.elearning.service.systemManage.ILogService;
import com.elearning.util.PropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 〈<br>
 * 〈〉
 *
 * @author lxx
 * @create 2019/10/15 10:11
 */
@Service("updateCourseStudyTimeService")
public class UpdateCourseStudyTimeServiceImpl implements IUpdateCourseStudyTimeService {

    @Autowired
    private ILogService logService;

    @Autowired
    private ICourseService courseService;

    @Autowired
    private IUcsEmployeeCourseService ucsEmployeeCourseService;

    @Autowired
    private IUscUsertliddayStudyInfoService uscUsertliddayStudyInfoService;

    @Autowired
    private IUscUserLearnStaticsService uscUserLearnStaticsService;

    @Autowired
    private IChapterService chapterService;



    @Override
    @Transactional(rollbackFor = {Exception.class})
    public ServiceResponse updateCourseStudyTime(HttpServletRequest request) throws ParseException {
        int result = 0 ;
        EosOperator operator = (EosOperator) request.getSession().getAttribute(Constants.USERINFO_KEY);
        Integer tenantId = operator.getTenantId();
        HttpSession session = request.getSession(true);
        Integer userId ;
        if (operator != null && operator.getOperatorId() != null) {
            userId = operator.getOperatorId();
        } else {
            request.setAttribute("isSessionValid", false);
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }
        String courseIdStr = request.getSession().getAttribute("COURSEID").toString();
        if(courseIdStr == null){
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Long courseId = Long.parseLong(courseIdStr);
        //只有文档学习的时候才会添加一个train_id   传入为null的时候是0 不是null的时候是参数传递过来的
        int trainId = (Integer) (session.getAttribute("train_id"));
        int sectionId = (Integer) (session.getAttribute("section_id"));
        Date now = new Date();
        String nowTime24 = Tools.DateSecondTo24String(now);
        String beginTime = session.getAttribute("STARTTIME").toString();
        session.setAttribute("STARTTIME", nowTime24);
        StudyPublics studyPublics = new StudyPublics();
        long finishnumber = studyPublics.getTimeDifference(beginTime,nowTime24);
        // 时间间隔
        String action = "";
        if (finishnumber < 0) {
            action = "tenant(" + PropertiesUtil.getProperty("tenantId")  + ") suffers time error and sytem time has been turned, details: beginTime>"
                    + beginTime + ";nowTime>" + nowTime24 ;
            finishnumber = 0;
        }
        if (finishnumber > 10 * 60) {
            action = "tenant(" + tenantId+ ") suffers time error and sytem time has been turned, details: beginTime>"
                    + beginTime + ";nowTime>" + nowTime24 ;
            finishnumber = 30;
        }
        Log log = new Log();
        log.setAction(action);
        log.setCreatedate(now);
        log.setTenantId(tenantId);
        log.setOperatorID(userId);
        log.setSourceUrl(request.getHeader("REFERER"));
        logService.save(log);
        long needAddedTime = 0; // 实际需要记录的新增时长；如果超过课程设置的时长，则不再记录
        Course course = courseService.getCourse(courseId);
        Map<String,Object> map = new HashMap<>();
        if(course.getIsFirstLevelDirectory() == 1 ){
            String courseAddress = course.getEnterUrl();
            //学时
            Double courseHour = course.getClassHour() * 3600;
            //计算此次学习的进度 保留两位小数
            double currentFinishProgress = StudyPublics.round((finishnumber / courseHour)* 100, 2);
            //当前年限
            String _currentYear = (String) (session.getAttribute(Constants.YEAR_KEY));
            map.put("operatorID",userId);
            map.put("courseId",courseId);
            map.put("year",_currentYear);
            UcsEmployeeCourse ucsEmployeeCourse = new UcsEmployeeCourse();
            List<UcsEmployeeCourse> ucsEmployeeCourseList = ucsEmployeeCourseService.findByExample(map);
            if(ucsEmployeeCourseList.size() > 0 ){
                ucsEmployeeCourse = ucsEmployeeCourseList.get(0);
            }else{
                ucsEmployeeCourse.setStudyProgress((float) 0.0);
            }
            double databaseTotalProgress = ucsEmployeeCourse.getStudyProgress() == null ? 0 : ucsEmployeeCourse.getStudyProgress();
            if (databaseTotalProgress < 0) {
                databaseTotalProgress = 0.0;
            }
            //计算出总进度
            databaseTotalProgress = databaseTotalProgress + currentFinishProgress;
            if (databaseTotalProgress > 100.0) {
                databaseTotalProgress = 100.0;
            }
            ucsEmployeeCourse.setLateTime(nowTime24);
            ucsEmployeeCourse.setStudyProgress((float) databaseTotalProgress);
            ucsEmployeeCourseService.updateByPrimaryKeySelective(ucsEmployeeCourse);
            String nowShortString = Tools.DateToString(now);
            map.clear();
            map.put("userID",userId);
            map.put("courseId",courseId);
            map.put("tenantId",tenantId);
            map.put("sectionId",sectionId);
            map.put("STUDYDAY",nowShortString);
            List<UscUsertliddayStudyInfo> uscUsertliddayStudyInfos = uscUsertliddayStudyInfoService.findByMap(map);
            UscUsertliddayStudyInfo uscUsertliddayStudyInfo = new UscUsertliddayStudyInfo();
            // 该门课程某一天学习时长占课件总时长比例
            double databaseprogress ;
            map.clear();
            map.put("userID",userId);
            map.put("courseId",courseId);
            map.put("year",_currentYear);
            long alreadyFinishedTime  = uscUsertliddayStudyInfoService.getTotalAlreadyLearnedTimeByCourseIdAndUserIdInOneYear(map);
            // 如果该课程还未达到课程总时长
            if(alreadyFinishedTime < courseHour ){
                if (alreadyFinishedTime + (int) finishnumber <= courseHour) {
                    needAddedTime = finishnumber;
                } else {
                    needAddedTime = (long) (courseHour - alreadyFinishedTime);
                }
                if(uscUsertliddayStudyInfos.size() > 0 ){
                    uscUsertliddayStudyInfo = uscUsertliddayStudyInfos.get(0);
                    int studytime = uscUsertliddayStudyInfo.getSTUDYTIME() + (int) needAddedTime;
                    databaseprogress = StudyPublics.round((studytime / courseHour )* 100, 2);
                    uscUsertliddayStudyInfo.setSTUDYTIME(studytime);
                    uscUsertliddayStudyInfo.setSTUDYPROGRESS((float) databaseprogress);
                    uscUsertliddayStudyInfoService.update(uscUsertliddayStudyInfo);
                }else{
                    uscUsertliddayStudyInfo.setCourseID(courseId);
                    uscUsertliddayStudyInfo.setUserID(userId);
                    uscUsertliddayStudyInfo.setSTUDYDAY(now);
                    uscUsertliddayStudyInfo.setYear(Tools.getNowYear(now));
                    uscUsertliddayStudyInfo.setTrainId(trainId);
                    uscUsertliddayStudyInfo.setSectionId(sectionId);
                    uscUsertliddayStudyInfo.setTenantId(tenantId);
                    int studytime = (int) needAddedTime;
                    databaseprogress = StudyPublics.round((studytime / courseHour)* 100, 2);
                    uscUsertliddayStudyInfo.setSTUDYTIME(studytime);
                    uscUsertliddayStudyInfo
                            .setSTUDYPROGRESS((float) databaseprogress);
                    uscUsertliddayStudyInfoService.insert(uscUsertliddayStudyInfo);
                }
               result =  uscUserLearnStaticsService.insertOnlineTime(userId,courseId,now,needAddedTime);
            }
        }else{
            Course coursePackage = chapterService.findCourseBySecondcourse(courseId);
            String courseAddress = course.getEnterUrl();
            //章节的学时
            double courseHour = (double)Math.round(course.getClassHour() * 3600*100)/100;
            String _currentYear = (String) (session.getAttribute(Constants.YEAR_KEY));
            String nowShortString = Tools.DateToString(now);
            double databaseprogress = 0;
            //课程包的学时
            Double packageHour = (double)Math.round(coursePackage.getClassHour()*3600*100)/100;
            map.clear();
            map.put("userID",userId);
            map.put("courseId",courseId);
            map.put("year",_currentYear);
            long alreadyFinishedTime  = uscUsertliddayStudyInfoService.getTotalAlreadyLearnedTimeByCourseIdAndUserIdInOneYear(map);
            map.clear();
            map.put("userID",userId);
            map.put("courseId",coursePackage.getCourseId());
            map.put("tenantId",tenantId);
            map.put("sectionId",sectionId);
            map.put("STUDYDAY",nowShortString);
            //课程包中UscUsertliddayStudyInfo的记录
            List<UscUsertliddayStudyInfo> uscUsertliddaystudyinfoPackages = uscUsertliddayStudyInfoService.findByMap(map);
            UscUsertliddayStudyInfo uscUsertliddaystudyinfoPackage = new UscUsertliddayStudyInfo();
            if(uscUsertliddaystudyinfoPackages.size() == 0 ){
                uscUsertliddaystudyinfoPackage.setCourseID(coursePackage.getCourseId());
                uscUsertliddaystudyinfoPackage.setUserID(userId);
                uscUsertliddaystudyinfoPackage.setSTUDYDAY(now);
                uscUsertliddaystudyinfoPackage.setYear(Tools.getNowYear(now));
                uscUsertliddaystudyinfoPackage.setTrainId(trainId);
                uscUsertliddaystudyinfoPackage.setSectionId(sectionId);
                uscUsertliddaystudyinfoPackage.setTenantId(tenantId);
                uscUsertliddaystudyinfoPackage.setSTUDYTIME(0);
                uscUsertliddaystudyinfoPackage.setSTUDYPROGRESS((float) 0);
                uscUsertliddayStudyInfoService.insert(uscUsertliddaystudyinfoPackage);
            }else{
                uscUsertliddaystudyinfoPackage = uscUsertliddaystudyinfoPackages.get(0);
            }
            double currentFinishProgress = StudyPublics.round(
                    (finishnumber / courseHour) * 100, 2);
            map.clear();
            map.put("operatorID",userId);
            map.put("courseId",courseId);
            map.put("year",_currentYear);
            UcsEmployeeCourse ucsEmployeecourse = new UcsEmployeeCourse();
            List<UcsEmployeeCourse> ucsEmployeeCourseList = ucsEmployeeCourseService.findByExample(map);
            if(ucsEmployeeCourseList.size() > 0 ){
                ucsEmployeecourse = ucsEmployeeCourseList.get(0);
            }
            double databaseTotalProgress = ucsEmployeecourse.getStudyProgress() == null ? 0 :ucsEmployeecourse.getStudyProgress();
            if (databaseTotalProgress < 0) {
                databaseTotalProgress = 0.0;
            }
            Integer study = ucsEmployeecourse.getStudyTime() == null ? 0 : ucsEmployeecourse.getStudyTime();
            Integer oldStudy = study;
            study = (int) (study + finishnumber) ;
            Double progress = databaseTotalProgress;
            databaseTotalProgress = databaseTotalProgress
                    + currentFinishProgress;
            Double progress2 = databaseTotalProgress;
            if (databaseTotalProgress > 100.0) {
                databaseTotalProgress = 100.0;
                study = (int) courseHour;
            }
            ucsEmployeecourse.setLateTime(nowTime24);
            ucsEmployeecourse.setStudyTime(study);
            //System.out.println("当前章节播放了"+study+"秒");
            ucsEmployeecourse.setStudyProgress((float) StudyPublics.round(study / courseHour * 100, 2));
            result = ucsEmployeeCourseService.updateByPrimaryKeySelective(ucsEmployeecourse);

            map.clear();
            map.put("operatorID",userId);
            map.put("courseId",coursePackage.getCourseId());
            map.put("year",_currentYear);
            UcsEmployeeCourse ucsEmployeecoursePackage = new UcsEmployeeCourse();
            ucsEmployeeCourseList = ucsEmployeeCourseService.findByExample(map);
            if(ucsEmployeeCourseList.size() > 0){
                ucsEmployeecoursePackage = ucsEmployeeCourseList.get(0);
            }
            if (alreadyFinishedTime < courseHour) {// 如果该课程还未达到课程总时长
                if(ucsEmployeecoursePackage == null ){
                    ucsEmployeecoursePackage.setOperatorID(userId);
                    ucsEmployeecoursePackage.setCourseId(coursePackage.getCourseId());
                    ucsEmployeecoursePackage.setFirstTime(now);
                    ucsEmployeecoursePackage.setYear(Integer.parseInt(_currentYear));
                    ucsEmployeecoursePackage.setTenantId(tenantId);
                    ucsEmployeecoursePackage.setStudyTime(0);
                }
                double currentFinishProgressPackage = StudyPublics.round((finishnumber / packageHour)* 100, 2);
                double databaseTotalProgressPackage = ucsEmployeecoursePackage.getStudyProgress() == null ? 0.0 : ucsEmployeecoursePackage.getStudyProgress();
                if (databaseTotalProgressPackage < 0) {
                    databaseTotalProgressPackage = 0.0;
                }
                databaseTotalProgressPackage = databaseTotalProgressPackage
                        + currentFinishProgressPackage;
                if (databaseTotalProgressPackage > 100.0) {
                    databaseTotalProgressPackage = 100.0;
                }
                ucsEmployeecoursePackage.setLateTime(nowTime24);
                ucsEmployeecoursePackage.setStudyProgress((float) databaseTotalProgressPackage);
                //添加已学时长
                int studyTime = ucsEmployeecoursePackage.getStudyTime() == null ? 0 : ucsEmployeecoursePackage.getStudyTime();
                Long needAddedTimePackage = 0L;
                if (oldStudy + (int) finishnumber <= courseHour) {
                    needAddedTimePackage = finishnumber;
                } else {
                    needAddedTimePackage = (long) ((int) courseHour - oldStudy);
                }
                int studyTimePackage = studyTime + needAddedTimePackage.intValue();
                ucsEmployeecoursePackage.setStudyTime(studyTimePackage);
                ucsEmployeecoursePackage.setStudyProgress(StudyPublics.round(studyTimePackage / packageHour  * 100, 2) > 100 ? 100
                        : (float) StudyPublics.round(studyTimePackage / packageHour * 100, 2));
                if(ucsEmployeecoursePackage.getID() == null ){
                    result = ucsEmployeeCourseService.insertSelectCourse(ucsEmployeecoursePackage);
                }else{
                    result = ucsEmployeeCourseService.updateByPrimaryKeySelective(ucsEmployeecoursePackage);
                }
            }else{
                if(ucsEmployeecoursePackage.getID() != null ){
                    ucsEmployeecoursePackage.setLateTime(nowTime24);
                    result = ucsEmployeeCourseService.updateByPrimaryKeySelective(ucsEmployeecoursePackage);
                }
            }
            map.clear();
            map.put("userID",userId);
            map.put("courseId",course.getCourseId());
            map.put("tenantId",tenantId);
            map.put("sectionId",sectionId);
            map.put("STUDYDAY",nowShortString);
            UscUsertliddayStudyInfo uscUsertliddaystudyinfo = new UscUsertliddayStudyInfo();
            List<UscUsertliddayStudyInfo> uscUsertliddayStudyInfos = uscUsertliddayStudyInfoService.findByMap(map);
            if(uscUsertliddayStudyInfos.size() > 0 ){
                uscUsertliddaystudyinfo = uscUsertliddayStudyInfos.get(0);
            }
            if (alreadyFinishedTime < courseHour) {// 如果该课程还未达到课程总时长
                if (alreadyFinishedTime + (int) finishnumber <= courseHour) {
                    needAddedTime = finishnumber;
                } else {
                    needAddedTime = (int) courseHour - alreadyFinishedTime;
                }
                if(uscUsertliddaystudyinfo != null){
                    int studytime = uscUsertliddaystudyinfo.getSTUDYTIME() + (int) needAddedTime;
                    databaseprogress = StudyPublics
                            .round((studytime / courseHour)* 100, 2);
                    uscUsertliddaystudyinfo.setSTUDYTIME(studytime);
                    uscUsertliddaystudyinfo.setSTUDYPROGRESS((float) databaseprogress);
                    result = uscUsertliddayStudyInfoService.update(uscUsertliddaystudyinfo);
                    int studyTimePackage = uscUsertliddaystudyinfoPackage.getSTUDYTIME()+(int)needAddedTime;
                    databaseprogress = studyTimePackage / packageHour;
                    databaseprogress = StudyPublics
                            .round(databaseprogress * 100, 2);
                    uscUsertliddaystudyinfoPackage.setSTUDYTIME(studyTimePackage);
                    uscUsertliddaystudyinfoPackage.setSTUDYPROGRESS((float)databaseprogress);
                    result = uscUsertliddayStudyInfoService.update(uscUsertliddaystudyinfoPackage);
                }else{
                    //当看一门课程横跨两天时，uscUsertliddaystudyinfo=null,新增分支
                    uscUsertliddaystudyinfo.setCourseID(course.getCourseId());
                    uscUsertliddaystudyinfo.setUserID(userId);
                    uscUsertliddaystudyinfo.setSTUDYDAY(now);
                    uscUsertliddaystudyinfo.setYear(Tools.getNowYear(now));
                    uscUsertliddaystudyinfo.setTrainId(trainId);
                    uscUsertliddaystudyinfo.setSectionId(sectionId);
                    uscUsertliddaystudyinfo.setTenantId(tenantId);
                    int studytime = (int) needAddedTime;
                    databaseprogress = StudyPublics
                            .round((studytime / courseHour) * 100, 2);
                    uscUsertliddaystudyinfo.setSTUDYTIME(studytime);
                    uscUsertliddaystudyinfo.setSTUDYPROGRESS((float) databaseprogress);
                    result = uscUsertliddayStudyInfoService.insert(uscUsertliddaystudyinfo);
                    int studyTimePackage = uscUsertliddaystudyinfoPackage.getSTUDYTIME()+(int)needAddedTime;
                    databaseprogress = StudyPublics.round((studyTimePackage / packageHour) * 100, 2);
                    uscUsertliddaystudyinfoPackage.setSTUDYTIME(studyTimePackage);
                    uscUsertliddaystudyinfoPackage.setSTUDYPROGRESS((float)databaseprogress);
                    result = uscUsertliddayStudyInfoService.update(uscUsertliddaystudyinfoPackage);
                }
                result =  uscUserLearnStaticsService.insertOnlineTime(userId,courseId,now,needAddedTime);
            }
        }
        if(result > 0 ){
            return ServiceResponse.createBySuccess();
        }
        return ServiceResponse.createByError();
    }
}
