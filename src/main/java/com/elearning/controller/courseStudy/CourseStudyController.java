package com.elearning.controller.courseStudy;

import cn.kepu.elnbase.webservice.coursemanage.ICourseWebService;
import com.alipay.api.domain.InteligentItemInfo;
import com.elearning.common.*;
import com.elearning.interceptor.loginInterceptor.IsCheckUserLogin;
import com.elearning.pojo.courseStudy.*;
import com.elearning.pojo.coursemanage.CmCourseId;
import com.elearning.pojo.coursemanage.ItemInfo;
import com.elearning.pojo.pub.Course;
import com.elearning.pojo.pub.EosOperator;
import com.elearning.pojo.systemManage.Tenant;
import com.elearning.service.courseStudy.*;
import com.elearning.service.coursemanage.IItemInfoService;
import com.elearning.service.pub.ICourseService;
import com.elearning.service.pub.IEosorgTOrganizationService;
import com.elearning.service.systemManage.ITenantService;
import com.elearning.util.PropertiesUtil;
import org.apache.cxf.transport.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.ParseException;
import java.util.*;

/**
 * 〈<br>
 * 〈〉
 *
 * @author lxx
 * @create 2019/10/8 10:21
 */
@Controller
@RequestMapping("/courseStudy/")
public class CourseStudyController {

    @Autowired
    private ICourseService courseService;


    @Autowired
    private IChapterService chapterService;

    @Autowired
    private IUcsEmployeeCourseService ucsEmployeeCourseService;

    @Autowired
    private IUscUsertliddayStudyInfoService uscUsertliddayStudyInfoService;

    @Autowired
    private ICourseNoteBookService courseNoteBookService;

    @Autowired
    private ITenantService tenantService;

    @Autowired
    private ICaptionsService captionsService;

    @Autowired
    private IItemInfoService iItemInfoService;

    @Autowired
    private IUcsUserScoInfoService ucsUserScoInfoService;

    @Autowired
    private IEosorgTOrganizationService eosorgTOrganizationService;

    @Autowired
    private ICourseWebService courseWebService;

    @RequestMapping("scormStudy.do")
    @IsCheckUserLogin(check = true)
    public String scormStudy(Long courseId, HttpServletRequest request, RedirectAttributes redirectAttributes){
        redirectAttributes.addAttribute("courseId",courseId);
        redirectAttributes.addAttribute("scoID",request.getParameter("scoID"));
        HttpSession session = request.getSession(true);
        session.removeAttribute("INFO");
        String info = request.getParameter("info");
        if(info != null && "admin".equals(info)){
            session.setAttribute("INFO", info);
        }
        if(session.getAttribute("courseVideo") == null){
            String isH5 = request.getParameter("isH5");
            if("1".equals(isH5)){
                session.setAttribute("courseVideo", "videoCourseWare_h5.jsp");
            }else {
                session.setAttribute("courseVideo", "videoCourseWare.jsp");
            }
        }
        EosOperator eosoperator = (EosOperator) session
                .getAttribute(Constants.USERINFO_KEY);
        Integer userID = eosoperator.getOperatorId();
        Integer train_id = (request.getParameter("train_id") == null || "".equals(request.getParameter("train_id"))) ? 0
                : new Integer(request.getParameter("train_id"));
        Integer section_id = (request.getParameter("section_id") == null || "".equals(request.getParameter("section_id"))) ? 0
                : new Integer(request.getParameter("section_id"));
        request.setAttribute("courseId", courseId);
        request.setAttribute("train_id", train_id);
        request.setAttribute("section_id", section_id);
        Course course = courseService.getCourse(courseId);
        String courseType = course.getSliceType();
        if(course.getIsFirstLevelDirectory()!= null && course.getIsFirstLevelDirectory() == 0 ){
            String _currentYear = (String) (session
                    .getAttribute(Constants.YEAR_KEY));
            UcsEmployeeCourse ucsEmployeecourse = new UcsEmployeeCourse();
            try{
                Course oneCourse = chapterService.findCourseBySecondcourse(courseId);
                Map<String,Object> map = new HashMap<>();
                map.put("year",_currentYear);
                map.put("courseId",oneCourse.getCourseId());
                map.put("operatorID",userID);
                List<UcsEmployeeCourse> ucsEmployeecourses = ucsEmployeeCourseService.findByExample(map);
                if(ucsEmployeecourses.size() > 0 ){
                    ucsEmployeecourse = ucsEmployeecourses.get(0);
                }
                if(ucsEmployeecourse == null ){
                    UcsEmployeeCourse ucs = new UcsEmployeeCourse();
                    ucs.setCourseId(oneCourse.getCourseId());
                    ucs.setOperatorID(userID);
                    ucs.setTenantId(eosoperator.getTenantId());
                    ucs.setYear(Integer.valueOf(_currentYear));
                    ucs.setLastWatchCourse(courseId);
                    ucsEmployeeCourseService.insertSelectCourse(ucs);
                }else{
                    ucsEmployeecourse.setLastWatchCourse(courseId);
                    ucsEmployeeCourseService.updateByPrimaryKeySelective(ucsEmployeecourse);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        String enterUrl = "";
        if(course.getEnterUrl() != null && course.getEnterUrl().contains(".")){
            enterUrl = course.getEnterUrl().substring(course.getEnterUrl().lastIndexOf("."));
        }
        if (courseType.equals("3") || courseType.equals("5")
                || courseType.equals("6")) {
            if(course.getClassfication().equals(4003)){
                //思维导图  otherCourse
                return "forward:otherStudy.do";

            }else if(course.getEnterUrl().indexOf(".swf") > -1){
                //documentCourse
                return "forward:documentStudy.do";
            }else{
                //videoCourse
                return "forward:videoCourseStudy.do";
            }
        }else if (courseType.equals("2")) { //老版本三分屏课件
            //scormCourse
            return "forward:scormCourse.do";

        }else if (courseType.equals("1")) { //单一网址课件
            //otherCourse
            return "forward:otherStudy.do";
        }
        if(courseType.equals("1") && ".mp4".equals(enterUrl)){
            //videoCourse
            return "forward:videoCourseStudy.do";
        }
        return "error/error404";
    }

    /**
     * 视频课件学习
     * @param request
     * @param courseId
     * @return
     */
    @RequestMapping("videoCourseStudy.do")
    public String videoCourseStudy(HttpServletRequest request,@ModelAttribute("courseId") Long courseId){
        EosOperator eosoperator = (EosOperator) request.getSession()
                .getAttribute(Constants.USERINFO_KEY);
        Integer userId = eosoperator.getOperatorId();
        Integer tenantId = eosoperator.getTenantId();
        request.getSession().setAttribute("USERID", userId);
        Course course = courseService.getCourse(courseId);
        int currentYear = Integer.parseInt(request.getSession().getAttribute(
                Constants.YEAR_KEY).toString());
        Date now = new Date();
        try{
            Map<String,Object> map = new HashMap<>();
            map.put("operatorID",userId);
            map.put("courseId",courseId);
            map.put("year",currentYear);
            List<UcsEmployeeCourse> ucsEmployeeCourses = ucsEmployeeCourseService.findByExample(map);
            UcsEmployeeCourse ucsEmployeeCourse ;
            String firstTime = Tools.DateSecondToString(now);
            String firstTime24 = Tools.DateSecondTo24String(now);
            if(ucsEmployeeCourses.size() == 0 ){
                ucsEmployeeCourse = new UcsEmployeeCourse();
                ucsEmployeeCourse.setOperatorID(userId);
                ucsEmployeeCourse.setYear(currentYear);
                ucsEmployeeCourse.setCourseId(courseId);
                ucsEmployeeCourse.setTenantId(tenantId);
                ucsEmployeeCourse.setFirstTime(now);
                ucsEmployeeCourseService.insertSelectCourse(ucsEmployeeCourse);
            }else{
                ucsEmployeeCourse = ucsEmployeeCourses.get(0);
                request.setAttribute("lastLearnTime", ucsEmployeeCourse.getLastLearnTime()==null?0:ucsEmployeeCourse.getLastLearnTime());
                request.setAttribute("totalCourseTime", ucsEmployeeCourse.getTotalCourseTime()==null?-1:ucsEmployeeCourse.getTotalCourseTime());
                if(ucsEmployeeCourse.getFirstTime() == null ){
                    ucsEmployeeCourse.setFirstTime(now);
                    ucsEmployeeCourseService.updateByPrimaryKeySelective(ucsEmployeeCourse);
                }
            }
            int train_id = request.getParameter("train_id") == null || "".equals(request.getParameter("train_id") )? 0
                    : Integer.parseInt(request.getParameter("train_id"));
            int section_id = request.getParameter("section_id") == null || "".equals(request.getParameter("section_id") == null)? 0
                    : Integer.parseInt(request.getParameter("section_id"));
            StudyPublics studyPublics = new StudyPublics();
            map.clear();
            map.put("courseId",courseId);
            map.put("userID",userId);
            map.put("trainId",train_id);
            map.put("sectionId",section_id);
            //map.put("STUDYDAY",studyPublics.TransDateFormat1(now));
            List<UscUsertliddayStudyInfo> uscUsertliddayStudyInfos = uscUsertliddayStudyInfoService.findByMap(map);
            int firstStudy = 0;
            for (UscUsertliddayStudyInfo uscUserStudyInfo : uscUsertliddayStudyInfos) {
                String databaseTime = studyPublics
                        .TransDateFormat1(uscUserStudyInfo.getSTUDYDAY());
                String nowTime = studyPublics.TransDateFormat1(now);
                if (uscUserStudyInfo.getUserID().intValue() == userId.intValue()
                        && databaseTime.equals(nowTime)) {
                    int studyamout = uscUserStudyInfo.getSTUDYAMOUNT() + 1;
                    Integer id = uscUserStudyInfo.getUserID();
                    uscUserStudyInfo.setSTUDYAMOUNT(studyamout);
                    uscUserStudyInfo.setYear(String.valueOf(currentYear));
                    uscUsertliddayStudyInfoService.update(uscUserStudyInfo);
                    firstStudy = 1;
                    break;
                }
            }
            if (firstStudy == 0) {
                UscUsertliddayStudyInfo uscUsertliddaystudyinfo = new UscUsertliddayStudyInfo();
                uscUsertliddaystudyinfo.setCourseID(courseId);
                uscUsertliddaystudyinfo.setUserID(userId);
                uscUsertliddaystudyinfo.setSTUDYDAY(now);
                uscUsertliddaystudyinfo.setYear(String.valueOf(currentYear));
                uscUsertliddaystudyinfo.setTrainId(train_id);
                uscUsertliddaystudyinfo.setSectionId(section_id);
                uscUsertliddaystudyinfo.setTenantId(tenantId);
                uscUsertliddayStudyInfoService.insert(uscUsertliddaystudyinfo);
            }
            request.setAttribute("STARTTIME", firstTime24);
            request.getSession().setAttribute("STARTTIME", firstTime24);
            request.getSession().setAttribute("train_id", train_id);
            request.getSession().setAttribute("section_id", section_id);
            map.clear();
            map.put("courseId",courseId);
            map.put("opeateorID",userId);
            List<CourseNotebook> courseNotebooks = courseNoteBookService.findByMap(map);
            if(courseNotebooks.size() > 0){
                request.getSession().setAttribute("courseNoteBook", courseNotebooks.get(0));
            }

            Tenant tenant = tenantService.findById(course.getTenantId());
            String streamServerAddress = tenant.getRtmpAddress()+ "/"+ PropertiesUtil.getProperty("streamServer.appName");
            request.setAttribute("orgDomainName", tenant.getStoredContext());
            request.setAttribute("streamServerAddress", streamServerAddress);
            String httpAddress = tenant.getHttpAddress();
            //2018-04-03 如果配置了课件播放服务器的域名，则使用统一的播放域名地址，对应后台的多个课件服务器；否则使用tenant表中配置的课件服务器的地址
            if(PropertiesUtil.getProperty("course.playAddress") != null && ! PropertiesUtil.getProperty("course.playAddress").toString().trim().equals("")){
                httpAddress = PropertiesUtil.getProperty("course.playAddress").toString();
            }
            request.setAttribute("httpAddress", httpAddress);
            request.setAttribute("courseId", courseId);
            request.setAttribute("COURSEID", Long.toString(courseId));
            request.setAttribute("course", course);
            request.getSession().setAttribute("preMode", 0);
            if("1".equals(course.getSliceType())){
                request.setAttribute("sliceType", course.getSliceType());
            }
            if(course.getIsFirstLevelDirectory() != 1 && course.getIsCoursePackage() != 1){
                Course coursePackage = chapterService.findCourseBySecondcourse(courseId);
                request.setAttribute("coursePackage", coursePackage);
            }
            if(course.getIsFirstLevelDirectory() ==1 &&  course.getIsCoursePackage() != 1){
                request.setAttribute("firstCourse", "yes");
            }
            if(request.getParameter("info")!=null){
                request.setAttribute("info", request.getParameter("info"));
            }
            Captions captions = captionsService.getCaptionsByCourseId(courseId);
            if(captions != null){
                request.setAttribute("captionsChinese", captions.getCaptionsChinese());
                request.setAttribute("captionsEnglish", captions.getCaptionsEnglish());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "courseStudy/videoClassRoom";
    }

    @RequestMapping("otherStudy.do")
    public String otherStudy(@ModelAttribute("courseId") Long courseId,HttpServletRequest request) throws ParseException {
        HttpSession session = request.getSession(true);
        EosOperator eosoperator = (EosOperator) request.getSession()
                .getAttribute(Constants.USERINFO_KEY);
        Integer tenantId=eosoperator.getTenantId();
        Integer userID = eosoperator.getOperatorId();
        session.setAttribute("USERID", userID);
        session.setAttribute("COURSEID", courseId);
        Course course = courseService.getCourse(courseId);
        int currentYear = Integer.parseInt((String) (session.getAttribute(Constants.YEAR_KEY)));
        Map<String,Object> map = new HashMap<>();
        map.put("operatorID",userID);
        map.put("courseId",courseId);
        map.put("year",currentYear);
        List<UcsEmployeeCourse> ucsEmployeeCourses = ucsEmployeeCourseService.findByExample(map);
        UcsEmployeeCourse ucsEmployeeCourse = new UcsEmployeeCourse();
        Integer train_id = (request.getParameter("train_id") == null) || "".equals(request.getParameter("train_id"))? 0
                : new Integer(request.getParameter("train_id"));
        Integer section_id = (request.getParameter("section_id") == null) || "".equals(request.getParameter("section_id"))? 0
                : new Integer(request.getParameter("section_id"));

        Date now = new Date();
        String firstTime = Tools.DateSecondToString(now);
        String firstTime24 = Tools.DateSecondTo24String(now);
        if(ucsEmployeeCourses.size() == 0 ){
            ucsEmployeeCourse.setCourseId(courseId);
            ucsEmployeeCourse.setOperatorID(userID);
            ucsEmployeeCourse.setTenantId(tenantId);
            ucsEmployeeCourse.setYear(currentYear);
            ucsEmployeeCourse.setFirstTime(now);
            ucsEmployeeCourseService.insertSelectCourse(ucsEmployeeCourse);
        }else{
            ucsEmployeeCourse = ucsEmployeeCourses.get(0);
            if(ucsEmployeeCourse.getFirstTime() == null ){
                ucsEmployeeCourse.setFirstTime(now);
                ucsEmployeeCourseService.updateByPrimaryKeySelective(ucsEmployeeCourse);
            }
        }
        map.clear();
        map.put("courseId",courseId);
        map.put("userID",userID);
        map.put("trainId",train_id);
        map.put("sectionId",section_id);
        List<UscUsertliddayStudyInfo> uscUsertliddayStudyInfos = uscUsertliddayStudyInfoService.findByMap(map);
        int firstStudy = 0;
        StudyPublics studyPublics = new StudyPublics();
        for (UscUsertliddayStudyInfo uscUserStudyInfo : uscUsertliddayStudyInfos) {
            String databaseTime = studyPublics
                    .TransDateFormat1(uscUserStudyInfo.getSTUDYDAY());
            String nowTime = studyPublics.TransDateFormat1(now);
            if (uscUserStudyInfo.getUserID().intValue() == userID.intValue()
                    && databaseTime.equals(nowTime)) {
                int studyamout = uscUserStudyInfo.getSTUDYAMOUNT() + 1;
                uscUserStudyInfo.setSTUDYAMOUNT(studyamout);
                uscUserStudyInfo.setYear(String.valueOf(currentYear));
                uscUsertliddayStudyInfoService.update(uscUserStudyInfo);
                firstStudy = 1;
                break;
            }
        }
        if (firstStudy == 0) {
            UscUsertliddayStudyInfo uscUsertliddaystudyinfo = new UscUsertliddayStudyInfo();
            uscUsertliddaystudyinfo.setCourseID(courseId);
            uscUsertliddaystudyinfo.setUserID(userID);
            uscUsertliddaystudyinfo.setSTUDYDAY(now);
            uscUsertliddaystudyinfo.setYear(String.valueOf(currentYear));
            uscUsertliddaystudyinfo.setTrainId(train_id);
            uscUsertliddaystudyinfo.setSectionId(section_id);
            uscUsertliddaystudyinfo.setTenantId(tenantId);
            uscUsertliddayStudyInfoService.insert(uscUsertliddaystudyinfo);
        }
        map.clear();
        map.put("courseId",courseId);
        map.put("opeateorID",userID);
        List<CourseNotebook> courseNotebooks = courseNoteBookService.findByMap(map);
        String enterUrl = course.getEnterUrl();
        String _visitUrl = "";
        if (enterUrl == null) {
            // 如果没有上传课件就预览，直接跳转至错误页面
            return "error/error404";
        } else {
            if (enterUrl.contains("http")) {
                _visitUrl = enterUrl;
            } else {
                _visitUrl = course.getServerFilePath() + course.getEnterUrl();// update
            }
        }
        request.setAttribute("course", course);
        if(course.getIsFirstLevelDirectory() != 1 && course.getIsCoursePackage() != 1){
            Course coursePackage = chapterService.findCourseBySecondcourse(courseId);
            request.setAttribute("coursePackage", coursePackage);
        }
        if(course.getIsFirstLevelDirectory() ==1 &&  course.getIsCoursePackage() != 1){
            request.setAttribute("firstCourse", "yes");
        }
        request.setAttribute("courseurl", _visitUrl);
        request.setAttribute("COURSEID", courseId);
        session.setAttribute("preMode", 0);
        request.setAttribute("STARTTIME", firstTime24);
        session.setAttribute("STARTTIME", firstTime24);
        session.setAttribute("train_id", train_id);
        session.setAttribute("section_id", section_id);
        if(courseNotebooks.size() > 0){
            request.getSession().setAttribute("courseNoteBook", courseNotebooks.get(0));
        }
        Tenant tenant = this.tenantService.findById(course.getTenantId());
        request.setAttribute("tenantURL", tenant.getEnterUrl());
        return "courseStudy/otherClassRoom";
    }


    @RequestMapping("documentStudy.do")
    public String documentStudy(HttpServletRequest request,@ModelAttribute("courseId") Long courseId) throws ParseException {
        EosOperator eosoperator = (EosOperator) request.getSession()
                .getAttribute(Constants.USERINFO_KEY);
        Integer userId = eosoperator.getOperatorId();
        Integer tenantId=eosoperator.getTenantId();
        Date now = new Date();
        request.getSession().setAttribute("USERID", userId);
        Course course = courseService.getCourse(courseId);
        if(course.getSelectedTimes() == null){
            course.setSelectedTimes(0);
        }
        int currentYear = Integer.parseInt(request.getSession().getAttribute(
                Constants.YEAR_KEY).toString());
        Map<String,Object> map = new HashMap<>();
        map.put("operatorID",userId);
        map.put("courseId",courseId);
        map.put("year",currentYear);
        List<UcsEmployeeCourse> ucsEmployeeCourses = ucsEmployeeCourseService.findByExample(map);
        UcsEmployeeCourse ucsEmployeeCourse = new UcsEmployeeCourse();
        if(ucsEmployeeCourses.size() == 0 ){
            ucsEmployeeCourse.setCourseId(courseId);
            ucsEmployeeCourse.setOperatorID(userId);
            ucsEmployeeCourse.setTenantId(tenantId);
            ucsEmployeeCourse.setYear(currentYear);
            ucsEmployeeCourse.setFirstTime(now);
            ucsEmployeeCourseService.insertSelectCourse(ucsEmployeeCourse);
        }else{
            ucsEmployeeCourse = ucsEmployeeCourses.get(0);
            if(ucsEmployeeCourse.getFirstTime() == null ){
                ucsEmployeeCourse.setFirstTime(now);
                ucsEmployeeCourseService.updateByPrimaryKeySelective(ucsEmployeeCourse);
            }
            request.setAttribute("lastLearnTime", ucsEmployeeCourse.getLastLearnTime()==null?0:ucsEmployeeCourse.getLastLearnTime());
            request.setAttribute("totalCourseTime", ucsEmployeeCourse.getTotalCourseTime()==null?-1:ucsEmployeeCourse.getTotalCourseTime());
        }
        int train_id = request.getParameter("train_id") == null || "".equals(request.getParameter("train_id"))? 0
                : Integer.parseInt(request.getParameter("train_id"));
        int section_id = request.getParameter("section_id") == null || "".equals(request.getParameter("section_id"))? 0
                : Integer.parseInt(request.getParameter("section_id"));
        map.clear();
        map.put("courseId",courseId);
        map.put("userID",userId);
        map.put("trainId",train_id);
        map.put("sectionId",section_id);
        List<UscUsertliddayStudyInfo> uscUsertliddayStudyInfos = uscUsertliddayStudyInfoService.findByMap(map);
        int firstStudy = 0;
        StudyPublics studyPublics = new StudyPublics();
        for (UscUsertliddayStudyInfo uscUserStudyInfo : uscUsertliddayStudyInfos) {
            String databaseTime = studyPublics
                    .TransDateFormat1(uscUserStudyInfo.getSTUDYDAY());
            String nowTime = studyPublics.TransDateFormat1(now);
            if (uscUserStudyInfo.getUserID().intValue() == userId.intValue()
                    && databaseTime.equals(nowTime)) {
                int studyamout = uscUserStudyInfo.getSTUDYAMOUNT() + 1;
                uscUserStudyInfo.setSTUDYAMOUNT(studyamout);
                uscUserStudyInfo.setYear(String.valueOf(currentYear));
                uscUsertliddayStudyInfoService.update(uscUserStudyInfo);
                firstStudy = 1;
                break;
            }
        }
        if (firstStudy == 0) {
            UscUsertliddayStudyInfo uscUsertliddaystudyinfo = new UscUsertliddayStudyInfo();
            uscUsertliddaystudyinfo.setCourseID(courseId);
            uscUsertliddaystudyinfo.setUserID(userId);
            uscUsertliddaystudyinfo.setSTUDYDAY(now);
            uscUsertliddaystudyinfo.setYear(String.valueOf(currentYear));
            uscUsertliddaystudyinfo.setTrainId(train_id);
            uscUsertliddaystudyinfo.setSectionId(section_id);
            uscUsertliddaystudyinfo.setTenantId(tenantId);
            uscUsertliddayStudyInfoService.insert(uscUsertliddaystudyinfo);
        }
        String firstTime = Tools.DateSecondToString(now);
        String firstTime24 = Tools.DateSecondTo24String(now);
        request.setAttribute("STARTTIME", firstTime24);
        request.getSession().setAttribute("STARTTIME", firstTime24);
        request.getSession().setAttribute("train_id", train_id);
        request.getSession().setAttribute("section_id", section_id);
        map.clear();
        map.put("courseId",courseId);
        map.put("opeateorID",userId);
        List<CourseNotebook> courseNotebooks = courseNoteBookService.findByMap(map);
        Tenant tenant = this.tenantService.findById(course.getTenantId());
        request.setAttribute("tenantURL", tenant.getEnterUrl());
        request.setAttribute("appOrgName", tenant.getStoredContext());
        String BASEURL = tenant.getHttpAddress();
        //2018-04-03 如果配置了课件播放服务器的域名，则使用统一的播放域名地址，对应后台的多个课件服务器；否则使用tenant表中配置的课件服务器的地址
        if(PropertiesUtil.getProperty("course.playAddress") != null && ! PropertiesUtil.getProperty("course.playAddress").toString().trim().equals("")){
            BASEURL = PropertiesUtil.getProperty("course.playAddress").toString();
        }
        request.setAttribute("BASEURL", BASEURL);

        request.setAttribute("COURSEID", courseId);
        request.setAttribute("course", course);
        request.getSession().setAttribute("preMode", 0);
        if(courseNotebooks.size() > 0){
            request.getSession().setAttribute("courseNoteBook", courseNotebooks.get(0));
        }
        if(course.getIsFirstLevelDirectory() != 1 && course.getIsCoursePackage() != 1){
            Course coursePackage = chapterService.findCourseBySecondcourse(courseId);
            if(coursePackage.getSelectedTimes() == null){
                coursePackage.setSelectedTimes(0);
            }
            request.setAttribute("coursePackage", coursePackage);
        }
        if(course.getIsFirstLevelDirectory() ==1 &&  course.getIsCoursePackage() != 1){
            request.setAttribute("firstCourse", "yes");
        }

        return "courseStudy/documentRoom";
    }

    @Deprecated
    @RequestMapping("scormCourse.do")
    public String scormCourse(HttpServletRequest request ,@ModelAttribute("courseId") Long courseId,@ModelAttribute("scoID") String scoID){
        HttpSession session = request.getSession(true);
        session.setAttribute("COURSEID", courseId);
        EosOperator eosoperator = (EosOperator) request.getSession()
                .getAttribute(Constants.USERINFO_KEY);
        Integer userID = eosoperator.getOperatorId();
        session.setAttribute("USERID", userID);
        Integer tenantId=eosoperator.getTenantId();
        int currentYear = Integer.parseInt((String) (session
                .getAttribute(Constants.YEAR_KEY)));
        Course course = courseService.getCourse(courseId);
        String distrURL = course.getEnterUrl() == null ? "" : course.getEnterUrl();
        String strServerName = request.getServerName().toString();
        String uploadPathStr = PropertiesUtil.getProperty("course.fulladdress")
                + "uploadFiles" + File.separator;
        Date now = new Date();
        Map<String,Object> map = new HashMap<>();
        map.put("operatorID",userID);
        map.put("courseId",courseId);
        map.put("year",currentYear);
        List<UcsEmployeeCourse> ucsEmployeeCourses = ucsEmployeeCourseService.findByExample(map);
        UcsEmployeeCourse ucsEmployeeCourse = new UcsEmployeeCourse();
        if(ucsEmployeeCourses.size() == 0 ){
            ucsEmployeeCourse.setCourseId(courseId);
            ucsEmployeeCourse.setOperatorID(userID);
            ucsEmployeeCourse.setTenantId(tenantId);
            ucsEmployeeCourse.setYear(currentYear);
            ucsEmployeeCourse.setFirstTime(now);
            ucsEmployeeCourseService.insertSelectCourse(ucsEmployeeCourse);
        }else {
            ucsEmployeeCourse = ucsEmployeeCourses.get(0);
            if (ucsEmployeeCourse.getFirstTime() == null) {
                ucsEmployeeCourse.setFirstTime(now);
                ucsEmployeeCourseService.updateByPrimaryKeySelective(ucsEmployeeCourse);
            }
        }
        boolean wasAMenuRequest =false;
        if ((!(scoID == null)) && (!scoID.equals(""))) {
            wasAMenuRequest = true;
        }
        Integer train_id = (request.getParameter("train_id") == null) ? 0
                : new Integer(request.getParameter("train_id"));
        Integer section_id = (request.getParameter("section_id") == null) ? 0
                : new Integer(request.getParameter("section_id"));
        Vector title_vector = new Vector();
        Vector id_vector = new Vector();
        Vector level_vector = new Vector();
        List<ItemInfo> itemInfos = iItemInfoService.findByCourseId(courseId);
        for(ItemInfo itemInfo : itemInfos){
            title_vector.addElement(itemInfo.getTitle());
            id_vector.addElement(itemInfo.getIdentifier());
            level_vector.addElement(itemInfo.getTheLevel());
        }
        session.setAttribute("course_title", course.getCourseName());
        session.setAttribute("title_vector", title_vector);
        session.setAttribute("id_vector", id_vector);
        session.setAttribute("level_vector", level_vector);
        map.clear();
        map.put("userID",userID);
        map.put("courseId",courseId);
        List<UcsUserScoInfo> ucsUserScoInfos = ucsUserScoInfoService.findByMap(map);
        return "course";
    }





    @IsCheckUserLogin(check = true)
    @RequestMapping("updateCourseStudyLastLearnTime.do")
    @ResponseBody
    public ServiceResponse updateCourseStudyLastLearnTime(Long courseId,Integer position, Integer totalTime ,HttpServletRequest request){
        if(courseId == null){
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        int result = 0;
        if(totalTime > 0 ){
            int currentYear = Integer.parseInt(request.getSession().getAttribute(
                    Constants.YEAR_KEY).toString());
            EosOperator eosoperator = (EosOperator) request.getSession()
                    .getAttribute(Constants.USERINFO_KEY);
            int userId = -1;
            if(eosoperator != null && eosoperator.getOperatorId() != null){
                userId = eosoperator.getOperatorId();
            }
            Map<String,Object> map = new HashMap<>();
            map.put("courseId",courseId);
            map.put("operatorID",userId);
            map.put("year",currentYear);
            List<UcsEmployeeCourse> ucsEmployeeCourseList = ucsEmployeeCourseService.findByExample(map);
            if(ucsEmployeeCourseList.size() > 0 ){
                UcsEmployeeCourse ucsEmployeeCourse = ucsEmployeeCourseList.get(0);
                ucsEmployeeCourse.setTotalCourseTime(totalTime);
                ucsEmployeeCourse.setLastLearnTime(position);
                result = ucsEmployeeCourseService.updateByPrimaryKeySelective(ucsEmployeeCourse);
            }
        }
        if(result > 0 ){
           return ServiceResponse.createBySuccess();
        }
        return ServiceResponse.createByError();
    }


    @RequestMapping("previewStudy.do")
    public String previewStudy(HttpServletRequest request,Long courseID,RedirectAttributes redirectAttributes){
        HttpSession session = request.getSession(true);
        redirectAttributes.addAttribute("courseID",courseID);
        if(session.getAttribute("courseVideo") == null){
            String isH5 = request.getParameter("isH5");
            if("1".equals(isH5)){
                session.setAttribute("courseVideo", "videoCourseWare_h5.jsp");
            }else {
                session.setAttribute("courseVideo", "videoCourseWare.jsp");
            }
        }
        session.removeAttribute("INFO");
        if("admin".equals(request.getParameter("info"))){
            session.setAttribute("INFO", "admin");
        }
        Course course = courseService.getCourse(courseID);
        String courseType = course.getSliceType();
        if (courseType.equals("3") || courseType.equals("5")
                || courseType.equals("6")) {
            //3:单一视频/文档课件
            if(course.getClassfication().equals(4003)){
                //思维导图
                return "forward:previewOther.do";
            }
            else if(course.getEnterUrl()!= null &&course.getEnterUrl().indexOf(".swf") > -1){
                return "forward:previewDocument.do";
            }
            else{
                return "forward:previewVideo.do";
            }
        } else if (courseType.equals("2")){
            return "error/error404";
        }
        else if (courseType.equals("1")){
            //单一网址课件（/courseStudy.do?method=previewOther）
            return "forward:previewOther.do";
        }
        return "error/error404";
    }

    @RequestMapping("previewVideo.do")
    public String previewVideo(@ModelAttribute("courseID")Long courseID,HttpServletRequest request){
        Long courseId = courseID;
        EosOperator operator = (EosOperator) request.getSession().getAttribute(Constants.USERINFO_KEY);
        HttpSession session = request.getSession(true);
        Integer tenantId = Integer.parseInt(PropertiesUtil.getProperty("tenantId").toString());
        if(operator != null){
            tenantId=operator.getTenantId();
        }
        Captions captions = captionsService.getCaptionsByCourseId(courseId);
        if(captions != null){
            request.setAttribute("captionsChinese", captions.getCaptionsChinese());
            request.setAttribute("captionsEnglish", captions.getCaptionsEnglish());
        }
        Course course = courseService.findCourseById(courseId);
        Tenant tenant = tenantService.findById(course.getTenantId());
        Integer orgId = 0;
        if(request.getSession().getAttribute(Constants.ROOTORGID_KEY)!=null){
            orgId=Integer.parseInt(request.getSession().getAttribute(Constants.ROOTORGID_KEY).toString());
        }
        if(course.getOrgId() != null && !course.getOrgId().toString().trim().equals("")){
            orgId = course.getOrgId();
        }
        if(tenantId == 1005) {  //外包公司课件上传平台
            tenant = eosorgTOrganizationService.findTenant(orgId);
        }
        String streamServerAddress = tenant.getRtmpAddress() + "/" + PropertiesUtil.getProperty("streamServer.appName");
        request.setAttribute("orgDomainName", tenant.getStoredContext());
        request.setAttribute("streamServerAddress", streamServerAddress);
        request.setAttribute("httpAddress", tenant.getHttpAddress());
        request.setAttribute("COURSEID", Long.toString(courseId));
        request.setAttribute("course", course);
        session.setAttribute("preMode", 1);
        if(request.getAttribute("actionType")!=null){
            request.setAttribute("actionType", request.getAttribute("actionType"));
        }
        if(course.getIsFirstLevelDirectory() != 1 && course.getIsCoursePackage() != 1){
            Course coursePackage = chapterService.findCourseBySecondcourse(courseId);
            if(coursePackage.getSelectedTimes() == null){
                coursePackage.setSelectedTimes(0);
            }
            request.setAttribute("coursePackage", coursePackage);
        }
        if(course.getIsFirstLevelDirectory() ==1 &&  course.getIsCoursePackage() != 1){
            request.setAttribute("firstCourse", "yes");
        }
        Long train_id = (request.getParameter("train_id") == null) ? 0
                : new Long(request.getParameter("train_id"));
        Long section_id = (request.getParameter("section_id") == null) ? 0
                : new Long(request.getParameter("section_id"));

        return "courseStudy/videoClassRoom";
    }


    @RequestMapping("previewDocument.do")
    public String previewDocument(@ModelAttribute("courseID")Long courseID,HttpServletRequest request){
        Long courseId = courseID;
        EosOperator operator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        Integer tenantId=operator.getTenantId();
        Course course = courseService.getCourse(courseId);
        Tenant tenant = this.tenantService.findById(course.getTenantId());
        Integer orgId = 0;
        if(request.getSession().getAttribute(Constants.ROOTORGID_KEY) != null){
            orgId=Integer.parseInt(request.getSession().getAttribute(Constants.ROOTORGID_KEY).toString());
        }
        if(course.getOrgId() != null && !course.getOrgId().toString().trim().equals("")){
            orgId = course.getOrgId();
        }
        if(tenantId == 1005){ //外包公司课件上传平台
            tenant = this.eosorgTOrganizationService.findTenant(orgId);
        }
        request.setAttribute("appOrgName", tenant.getStoredContext());
        request.setAttribute("BASEURL", tenant.getHttpAddress());
        request.setAttribute("COURSEID", courseId);
        request.getSession().setAttribute("preMode", 1);
        request.setAttribute("tenantURL", tenant.getEnterUrl());
        if(course.getIsFirstLevelDirectory() != 1 && course.getIsCoursePackage() != 1){
            Course coursePackage = chapterService.findCourseBySecondcourse(courseId);
            if(coursePackage.getSelectedTimes() == null){
                coursePackage.setSelectedTimes(0);
            }
            request.setAttribute("coursePackage", coursePackage);
        }
        if(course.getIsFirstLevelDirectory() ==1 &&  course.getIsCoursePackage() != 1){
            request.setAttribute("firstCourse", "yes");
        }
        Long train_id = (request.getParameter("train_id") == null) ? 0
                : new Long(request.getParameter("train_id"));
        Long section_id = (request.getParameter("section_id") == null) ? 0
                : new Long(request.getParameter("section_id"));
        return "courseStudy/documentRoom";
    }


    @RequestMapping("previewOther.do")
    public String previewOther(@ModelAttribute("courseID")Long courseID,HttpServletRequest request){
        Long courseId = courseID;
        String _visitUrl = "";
        HttpSession session = request.getSession(true);
        EosOperator operator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        Integer tenantId = operator.getTenantId();
        Course course = courseService.getCourse(courseId);
        if (course == null || course.equals(null)) {
            _visitUrl = getSharedCourseVisitUrl(courseId);
        }else {
            String enterUrl = course.getEnterUrl();
            if (enterUrl == null) {
                // 如果没有上传课件就预览，直接跳转至错误页面
                return "error/error404";
            } else {
                if (enterUrl.contains("http://")) {
                    _visitUrl = enterUrl;
                } else
                    _visitUrl = course.getServerFilePath()
                            + course.getEnterUrl();
            }
        }
        request.setAttribute("courseurl", _visitUrl);
        request.setAttribute("COURSEID", courseId);
        session.setAttribute("preMode", 1);
        Tenant tenant = this.tenantService.findById(course.getTenantId());
        Integer orgId = 0;
        if(request.getSession().getAttribute(Constants.ROOTORGID_KEY) != null){
            orgId=Integer.parseInt(request.getSession().getAttribute(Constants.ROOTORGID_KEY).toString());
        }
        if(course.getOrgId() != null && !course.getOrgId().toString().trim().equals("")){
            orgId = course.getOrgId();
        }
        if(tenantId == 1005){
            tenant = this.eosorgTOrganizationService.findTenant(orgId);
        }
        request.setAttribute("tenantURL", tenant.getEnterUrl());
        if(course.getIsFirstLevelDirectory() != 1 && course.getIsCoursePackage() != 1){
            Course coursePackage = chapterService.findCourseBySecondcourse(courseId);
            if(coursePackage.getSelectedTimes() == null){
                coursePackage.setSelectedTimes(0);
            }
            request.setAttribute("coursePackage", coursePackage);
        }
        if(course.getIsFirstLevelDirectory() ==1 &&  course.getIsCoursePackage() != 1){
            request.setAttribute("firstCourse", "yes");
        }
        return "courseStudy/otherClassRoom";
    }


    @RequestMapping("nextPlayer.do")
    @IsCheckUserLogin(check = true)
    @ResponseBody
    public ServiceResponse nextPlayer(Long courseId){
        Long oneCourseId = null;
        Course oneCourse = chapterService.findCourseBySecondcourse(courseId);
        Long result = null;
        if( oneCourse != null){
            oneCourseId = oneCourse.getCourseId();
        }
        Map<String,Object> map = new HashMap<>();
        if(oneCourseId != null){
            map.put("courseId", oneCourseId);
        }
        List<Chapter> chapters = this.chapterService.findByMap(map);
        if(chapters.size()>0){
            outer:for (int i = 0; i < chapters.size(); i++) {
                result = null;
                List<Course> courses = chapters.get(i).getSubset();
                for (int j = 0; j < courses.size(); j++) {
                    if(courseId.equals(courses.get(j).getCourseId()) && j < courses.size()-1){
                        result = courses.get(j+1).getCourseId();
                        break outer;
                    }
                    if(courseId.equals(courses.get(j).getCourseId()) && j == courses.size()-1 && i < chapters.size()-1){
                        result = chapters.get(i+1).getSubset().get(0).getCourseId();
                        break outer;
                    }
                }
            }
        }
        return ServiceResponse.createBySuccess(result);
    }




    private String getSharedCourseVisitUrl(long courseID) {
        String courseurl ;
        String _uploadOrgId = PropertiesUtil.getProperty("org.Id");
        Long uploadOrgId = Long.valueOf(_uploadOrgId);
        CmCourseId _cmCourseId = new CmCourseId(courseID, uploadOrgId);
        courseurl = courseWebService.getSharedCourseVisitUrl(_cmCourseId);
        return courseurl;
    }


    /**
     *
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("previewScheduleCourse.do")
    public String previewScheduleCourse(HttpServletRequest request){
        String jspName = "test/error";
        EosOperator operator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(operator == null) {
            return jspName;
        }

        long courseId = Long.parseLong(request.getParameter("courseID").toString());
        Course course = this.courseService.findCourseById(courseId);
        Tenant tenant = this.tenantService.findById(course.getTenantId());
        request.setAttribute("tenantURL", tenant.getEnterUrl());
        String courseType = course.getSliceType();
        String enterUrl = course.getEnterUrl();
        if (courseType.equals("3")) { // 单一文档或视频课件
            if (course.getEnterUrl().indexOf(".swf") > -1) {
                // flash文档预览
                request.setAttribute("BASEURL", tenant.getHttpAddress());
                request.setAttribute("appOrgName", tenant.getStoredContext());
                request.setAttribute("COURSEID", Long.toString(courseId));
                request.getSession().setAttribute("preMode", 1);

                jspName = "courseStudy/documentClassRoom";
            } else {
                // flash视频预览
                String streamServerAddress = tenant.getRtmpAddress() + "/" + Constants.elearningProperties.getProperty("streamServer.appName");
                request.setAttribute("orgDomainName", tenant.getStoredContext());
                request.setAttribute("httpAddress", tenant.getHttpAddress());
                request.setAttribute("streamServerAddress", streamServerAddress);
                request.setAttribute("COURSEID", Long.toString(courseId));
                request.getSession().setAttribute("preMode", 1);

                jspName = "courseStudy/videoClassRoom";
            }
        } else if (courseType.equals("2")) {
            // scorm课件预览 ：暂不考虑
        } else if (courseType.equals("1")) {
            // 单一网址课件预览
            request.setAttribute("courseurl", enterUrl);
            request.setAttribute("COURSEID", Long.toString(courseId));
            request.getSession().setAttribute("preMode", 1);

            jspName = "courseStudy/otherClassRoom";

        }

        return jspName;

    }


}
