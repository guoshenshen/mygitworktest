package com.elearning.controller.courseStudy;

import com.elearning.common.*;
import com.elearning.interceptor.loginInterceptor.IsCheckUserLogin;
import com.elearning.pojo.courseStudy.Chapter;
import com.elearning.pojo.courseStudy.UcsEmployeeCourse;
import com.elearning.pojo.courseStudy.UserNeedLearnCourse;
import com.elearning.pojo.coursemanage.CourseType;
import com.elearning.pojo.pub.*;
import com.elearning.pojo.systemManage.Tenant;
import com.elearning.service.courseStudy.IChapterService;
import com.elearning.service.courseStudy.IUcsEmployeeCourseService;
import com.elearning.service.courseStudy.IUserNeedLearnCourseService;
import com.elearning.service.coursemanage.ICourseTypeService;
import com.elearning.service.coursemanage.IUscLectureFileService;
import com.elearning.service.examManage.IExamExamInfoService;
import com.elearning.service.pub.ICourseService;
import com.elearning.service.pub.IEosorgTEmployeeService;
import com.elearning.service.pub.IEosorgTOrganizationService;
import com.elearning.service.pub.IResourceCourseTypeService;
import com.elearning.service.systemManage.ITenantService;
import com.elearning.util.DateTimeUtil;
import com.elearning.util.PropertiesUtil;
import com.elearning.vo.CourseVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 〈<br>
 * 〈〉
 *
 * @author lxx
 * @create 2019/7/16 10:08
 */
@Controller
@RequestMapping("/onlineCourse/")
public class OnlineCourseController {

    @Autowired
    private ICourseService courseService;


    @Autowired
    private IUcsEmployeeCourseService ucsEmployeeCourseService;

    @Autowired
    private IEosorgTOrganizationService eosorgTOrganizationService;

    @Autowired
    private ITenantService tenantService;

    @Autowired
    private IUscLectureFileService uscLectureFileService;

    @Autowired
    private IChapterService chapterService;

    @Autowired
    private IResourceCourseTypeService resourceCourseTypeService;

    @Autowired
    private ICourseTypeService courseTypeService;

    @Autowired
    private IEosorgTEmployeeService eosorgTEmployeeService;


    @Autowired
    private IUserNeedLearnCourseService userNeedLearnCourseService;


    @Autowired
    private IExamExamInfoService examExamInfoService;

    /**
     * 课程详情
     * @param bookId
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("detail.do")
    public String detail(Long bookId, Model model, HttpServletRequest request){
        if(bookId == null ){
            return "test/error";
        }
        Map<String,Object> map = new HashMap<>();
        Integer operatorId = 0;
        Course course = courseService.getCourse(bookId);
        CourseVo courseVo = courseService.getCourseVo(course);
        HttpSession session = request.getSession(true);
        String year = String.valueOf(Tools.getCurrentYear());
        EosOperator currentOperator =(EosOperator) request.getSession().getAttribute(PropertiesUtil.getProperty("USERINFO_KEY"));
        List<UcsEmployeeCourse> ucsEmployeeCourseList = new ArrayList<>();
        if(currentOperator != null ){
            operatorId = currentOperator.getOperatorId();
            map.put("year",year);
            map.put("courseId",course.getCourseId());
            map.put("operatorID",currentOperator.getOperatorId());
            ucsEmployeeCourseList = ucsEmployeeCourseService.findByExample(map);
            if(ucsEmployeeCourseList != null && ucsEmployeeCourseList.size() > 0){
                courseVo.setStudyProgress(ucsEmployeeCourseList.get(0).getStudyProgress());
            }

            EosorgTEmployee eosorgTEmployee = eosorgTEmployeeService.findById(operatorId);
            if(eosorgTEmployee != null ){
                request.setAttribute("headPic",eosorgTEmployee.getADDRESS());
                request.setAttribute("gender",eosorgTEmployee.getGender());
            }
        }
        String info = request.getParameter("info");
        if(info != null && "admin".equals(info)){
            request.setAttribute("info", info);
        }
        request.setAttribute("operatorId",operatorId );
        request.setAttribute("currentOperator",currentOperator );
        Integer courseStudyFlag = 0;
        request.setAttribute("bookId", bookId);
        if(course != null ){
            if(course.getMobilePlayAddress() != null && course.getMobilePlayAddress().contains(".mp4")){
                request.setAttribute("isVideoCourse", 1);
                if(course.getIsCoursePackage()!=null&&course.getIsCoursePackage()!=1){
                    request.setAttribute("isPackage", 0);
                }
            }else{
                request.setAttribute("isVideoCourse", 0);
            }
        }
        ResourceCourseType rctList = resourceCourseTypeService.findByResourceId(course.getCourseId(), ResourceType.COURSE.getTypeCode()).getData();
        if( rctList!= null && rctList.getCourseTypeId() != null ){
            CourseType coursetype=courseTypeService.selectByPrimaryKey(Integer.parseInt(rctList.getCourseTypeId().toString()));
            courseVo.setExpertAreas(coursetype.getCourseTypeName());
            courseVo.setExpertAreaId(coursetype.getCourseTypeID()+"");
        }
        model.addAttribute("course",courseVo);
        Map<String,Object> courseMap = new HashMap<String,Object>();
        courseMap.put("courseId", bookId);
        List<Chapter> list = chapterService.findByMap(courseMap);
        if(course.getIsCoursePackage()!=null&&course.getIsCoursePackage() == 1){
            if( list.size()>0){
                List<Course> courses = courseService.listSecondCourseByCourseId(course.getCourseId());
                Long LastWatchCourse = null;
                UcsEmployeeCourse ucsEmployeecourse ;
                try {
                    if( ucsEmployeeCourseList.size() > 0 ){
                        ucsEmployeecourse = ucsEmployeeCourseList.get(0);
                        LastWatchCourse = ucsEmployeecourse.getLastWatchCourse();
                        if(ucsEmployeecourse.getStudyProgress() != null && ucsEmployeecourse.getStudyProgress() > 0 ){
                            courseStudyFlag = 2;
                        }else{
                            courseStudyFlag = 1;
                        }
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if(LastWatchCourse != null && !"".equals(LastWatchCourse)){
                    request.setAttribute("carryCourse",  this.courseService.getCourse(LastWatchCourse));
                    request.setAttribute("firstWatch", list.get(0).getSubset().get(0));
                }else{
                    if(courses.size()<=0){
                        courses.add(course);
                    }
                    request.setAttribute("carryCourse", list.get(0).getSubset().get(0));
                    request.setAttribute("firstWatch", list.get(0).getSubset().get(0));
                }
            }
        }
        map.clear();
        map.put("operatorId",operatorId);
        map.put("bookId",bookId);
        map.put("year",year);
        UserNeedLearnCourse userNeedLearnCourse = userNeedLearnCourseService.findCourseRelatedTrainId(map);
        if(userNeedLearnCourse != null  ){
            if(ucsEmployeeCourseList.size() > 0 && ucsEmployeeCourseList.get(0).getStudyProgress() > 0 ){
                courseStudyFlag = 2;
            }else{
                courseStudyFlag = 1;
            }
        }
        if(ucsEmployeeCourseList.size()>0){
            request.setAttribute("studyProgress", ucsEmployeeCourseList.get(0).getStudyProgress());
        }
        request.setAttribute("courseStudyFlag", courseStudyFlag);
        //获取课程的发布机构
        Integer publishOrgId=course.getOrgId();
        EosorgTOrganization publishOrg=eosorgTOrganizationService.getEosorgTOrganizationById(publishOrgId);
        request.setAttribute("publishOrg", publishOrg);
        //获取课程选学人次
        request.setAttribute("totalLearnedMan", courseVo.getTotalLearnedUser()==null?0:courseVo.getTotalLearnedUser());
        int trainId = 0;
        if(request.getParameter("trainId")!=null&&!request.getParameter("trainId").toString().equals("")){
            trainId = Integer.valueOf(request.getParameter("trainId").toString());
            request.setAttribute("trainId", trainId);
            request.getSession().setAttribute("trainId", trainId);
        }else if(request.getAttribute("trainId")!=null&&!request.getAttribute("trainId").toString().equals("")){
            trainId = Integer.valueOf(request.getAttribute("trainId").toString());
            request.setAttribute("trainId", trainId);
        }else{
            map.clear();
            map.put("operatorId",operatorId);
            map.put("bookId",bookId);
            map.put("year",year);
            if( userNeedLearnCourse != null ){
                trainId = userNeedLearnCourse.getTrainId()==null? 0 : userNeedLearnCourse.getTrainId();
            }
            request.setAttribute("trainId", trainId);
        }

        map.clear();
        map.put("classifyId", bookId);
        map.put("operatorId", operatorId);
        map.put("examId", -1);// 自测考试默认为-1
        Double maxSelfTestScore = examExamInfoService.findMaxScoreForSelfTest(map);
        request.setAttribute("maxSelfTestScore", maxSelfTestScore);
        return "courseStudy/onlineCourseDetailInfo";
    }

    /**
     * 热门课件
     * @param request
     * @return
     */
    @RequestMapping("getHotCourseList.do")
    @ResponseBody
    public ServiceResponse getHotCourseList(HttpServletRequest request){

        EosOperator operator = (EosOperator)request.getSession().getAttribute(PropertiesUtil.getProperty("USERINFO_KEY"));
        String orgSEQ=null;
        if(operator==null){
            //学员尚未登录
            Tenant tenant = tenantService.findById(Integer.parseInt(PropertiesUtil.getProperty("tenantId")));
            EosorgTOrganization tenantOrg = eosorgTOrganizationService.getEosorgTOrganizationById(tenant.getOrgId());
            orgSEQ=tenantOrg.getOrgSEQ();
        }
        else{
            Integer currentOrgId = null;
            if(request.getSession().getAttribute(PropertiesUtil.getProperty("ROOTORGID_KEY"))==null){
                currentOrgId = operator.getEosorgTEmployee().getOrgID();
            }
            else{
                currentOrgId = Integer.valueOf(request.getSession().getAttribute(PropertiesUtil.getProperty("ROOTORGID_KEY")).toString());
            }
            EosorgTOrganization currentOrg = this.eosorgTOrganizationService.getEosorgTOrganizationById(currentOrgId);
            orgSEQ=currentOrg.getOrgSEQ();
        }
        return courseService.listPopularCourse(orgSEQ);
    }

    /**
     * 选学成员
     * @param courseId
     * @param rowCount
     * @param request
     * @return
     */
    @RequestMapping("getCourseSelectedUserInfo.do")
    @ResponseBody
    public ServiceResponse getCourseSelectedUserInfo(Long courseId,Integer rowCount,HttpServletRequest request){
        if(courseId == null){
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        if(rowCount == null){
            rowCount = 16 ;
        }
        EosOperator operator = (EosOperator) request.getSession().getAttribute(PropertiesUtil.getProperty("USERINFO_KEY"));
        Integer tenantId=null;
        if(operator!=null&&operator.getTenantId()!=null){
            tenantId=operator.getTenantId();
        }
        Map<String,Object> map = new HashMap<>();
        map.put("courseId",courseId);
        map.put("rowCount",rowCount);
        map.put("tenantId",tenantId);
        return courseService.listLimitedUserInfoByCourseId(map);
    }

    /**
     * 相关资料
     * @param courseId
     * @return
     */
    @RequestMapping("getLectureFile.do")
    @ResponseBody
    public ServiceResponse getLectureFile(Long courseId){
        if(courseId == null ){
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        return uscLectureFileService.findAllLectureFile(courseId);

    }

    /**
     * 课程目录
     * @param courseId
     * @param request
     * @return
     */
    @RequestMapping("getChapters.do")
    @ResponseBody
    public ServiceResponse getChapters(Long courseId,HttpServletRequest request){
        if(courseId == null ){
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        int userId = 0;
        EosOperator eosoperator = (EosOperator) request.getSession()
                .getAttribute(PropertiesUtil.getProperty("USERINFO_KEY"));
        if (eosoperator != null && eosoperator.getOperatorId() != null) {
            userId = eosoperator.getOperatorId();
        }
        return chapterService.getChaptersByCourseId(courseId,userId);
    }

}
