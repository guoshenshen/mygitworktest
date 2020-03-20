package com.elearning.controller.coursemanage;

import com.elearning.common.Constants;
import com.elearning.common.ResponseCode;
import com.elearning.common.ServiceResponse;
import com.elearning.interceptor.loginInterceptor.IsCheckUserLogin;
import com.elearning.pojo.coursemanage.CourseRelevantcourse;
import com.elearning.pojo.coursemanage.UscLectureFile;
import com.elearning.pojo.pub.Course;
import com.elearning.pojo.pub.DDictionary;
import com.elearning.pojo.pub.EosOperator;
import com.elearning.service.coursemanage.ICourseCoursetypeService;
import com.elearning.service.coursemanage.ICourseRelevantcourseService;
import com.elearning.service.coursemanage.IUscLectureFileService;
import com.elearning.service.courseshop.ICourseCollectService;
import com.elearning.service.pub.*;
import com.elearning.util.PropertiesUtil;
import com.elearning.vo.CourseFormAll;
import com.elearning.vo.CourseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 〈<br>
 * 〈〉
 *
 * @author lxx
 * @create 2019/7/22 14:21
 */
@Controller
@RequestMapping("/courseCourseType/")
public class CourseCourseTypeController {

    @Autowired
    private ICourseService courseService;

    @Autowired
    private IExportService exportService;

    @Autowired
    private IDDictionaryService dDictionaryService;

    @Autowired
    private ICourseCollectService courseCollectService;

    @Autowired
    private IUscLectureFileService uscLectureFileService;

    @Autowired
    private ICourseRelevantcourseService courseRelevantcourseService;

    @Autowired
    private ICourseCoursetypeService courseCoursetypeService;

    @Autowired
    private IEosorgTEmployeeService eosorgTEmployeeService;
    /**
     * 课程管理
     * @param startIndex
     * @param count
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping(value = "listCourses.do",method = RequestMethod.POST)
    @ResponseBody
    public ServiceResponse listCourses(@RequestParam(value = "startIndex",defaultValue = "1")Integer startIndex,
                                       @RequestParam(value = "count",defaultValue = "10")Integer count,
                                       HttpServletRequest request){
        EosOperator operator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        Integer orgId = eosorgTEmployeeService.findById(operator.getOperatorId()).getOrgID();
        Integer tenantId= Integer.parseInt(PropertiesUtil.getProperty("tenantId"));
        String coursePublishStatusInput=request.getParameter("coursePublishStatusInput");  //发布状态
        String courseNameInput=request.getParameter("courseNameInput"); //课程名称
        String courseKeyWordsInput=request.getParameter("courseKeyWordsInput"); // 关键字
        String courseCreatorInput=request.getParameter("courseCreatorInput"); //讲师
        Map<String,Object> map = new HashMap<>();
        map.put("startIndex", startIndex);
        map.put("count", count);
        map.put("operatorId",operator.getOperatorId());
        map.put("rootOrgId",orgId);
        map.put("tenantId",tenantId);
        map.put("pubStatus",coursePublishStatusInput == null || coursePublishStatusInput.equals("0") ? null :  coursePublishStatusInput);
        map.put("courseName",courseNameInput);
        map.put("courseKeyWords",courseKeyWordsInput);
        map.put("courseCreator",courseCreatorInput);
        String resultName = request.getParameter("resultName");
        //共享课程 和收藏夹
        if(resultName != null && !resultName.equals("")){
            map.put("resultName",resultName);
            return courseService.queryAllShareCourseList(map);
        }else{
            return courseService.queryAllOffLineCourseList(map);
        }
    }

    /**
     * 删除课程
     *
     * @param selectbox
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("deleteCourse.do")
    @ResponseBody
    public ServiceResponse deleteCourse(String[] selectbox){
        if(selectbox == null){
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        return courseService.deleteCourse(selectbox);
    }

    /**
     * 导出课程excel表格
     * @param
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("exportCourseInfo.do")
    @ResponseBody
    public ServiceResponse exportCourseInfo(HttpServletRequest request) throws Exception {

        if(request.getParameterValues("selectbox") == null){
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        String[] selectbox = request.getParameterValues("selectbox");
        return exportService.exportCourseInfo(selectbox,request);
    }

    /**
     * 课程管理页面
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("listOffLineAllCourse.do")
    public String listOffLineAllCourse(HttpServletRequest request){
        List<DDictionary> dDictionaryList = dDictionaryService.getByParentCode("1090");
        request.setAttribute("pubishStatusList", dDictionaryList);
        return "courseManage/manageCourse_cast";
    }

    /**
     * 修改发布状态
     * @param courseId
     * @param pubStatusId
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("updateCoursePubStatus.do")
    @ResponseBody
    public ServiceResponse updateCoursePubStatus(Long courseId,Integer pubStatusId){
        if(courseId == null || pubStatusId == null){
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        return courseService.updateCoursePubStatus(courseId,pubStatusId);
    }

    /**
     * 更新课程的发布状态 及公开范围
     * @param courseId
     * @param openScope
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("updateCourseOpenScope.do")
    @ResponseBody
    public ServiceResponse updateCourseOpenScope(Long courseId,Integer openScope,HttpServletRequest request){
        if(courseId ==null || openScope == null){
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        int orgId = Integer.valueOf(request.getSession().getAttribute(Constants.ROOTORGID_KEY).toString());
        return courseService.updateCourseOpenScope(courseId,openScope,orgId);
    }


    /**
     * 课程详情
     * @param courseId
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("detail.do")
    public String detail(Long courseId,HttpServletRequest request){
        Course course = courseService.getCourse(courseId);
        CourseVo courseVo = courseService.getCourseVo(course,null,null);
        request.setAttribute("course",courseVo);
        return "courseManage/coursedetail";
    }

    /**
     * 本机构员工学习与否
     * @param courseId
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("findUserLearnCourseInfo.do")
    public String findUserLearnCourseInfo(Long courseId,HttpServletRequest request){
        int orgId=Integer.valueOf(request.getSession().getAttribute(Constants.ROOTORGID_KEY).toString());
        EosOperator operator=(EosOperator) request.getSession().getAttribute(Constants.USERINFO_KEY);
        int operatorId=operator.getOperatorId();
        Course course=courseService.getCourse(courseId);
        CourseVo courseForm=courseService.getCourseVo(course, orgId,operatorId);
        request.setAttribute("courseForm", courseForm);
        return "courseShop/shareCourseUserLearnInfo";
    }


    /**
     * 修改课程的收藏状态
     * @param courseId
     * @param collectId
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("updateCourseCollectStatus.do")
    @ResponseBody
    public ServiceResponse updateCourseCollectStatus(Long courseId,Integer collectId,HttpServletRequest request){
        if(collectId == null || collectId == null ) {
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        int orgId=Integer.valueOf(request.getSession().getAttribute(Constants.ROOTORGID_KEY).toString());
        EosOperator operator=(EosOperator) request.getSession().getAttribute(Constants.USERINFO_KEY);
        return courseCollectService.updateCourseCollectStatus(courseId,collectId,orgId,operator);
    }

    /**
     * 修改课程的图片
     * @param courseId
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("updateCoursePic.do")
    @ResponseBody
    public ServiceResponse updateCoursePic(Long courseId,String pictureUrl){
        return courseService.updateCoursePic(courseId,pictureUrl);
    }


    /**
     * 修改相关资料的公开范围
     * @param openScope
     * @param lectureId
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("updateUscLectureFileOpenScope.do")
    @ResponseBody
    public ServiceResponse updateUscLectureFileOpenScope(Integer openScope,Integer lectureId ){
        UscLectureFile uscLectureFile =  uscLectureFileService.findById(lectureId);
        String reslut = "";
        if(openScope>2200){
            uscLectureFile.setOpenScope(openScope);
            uscLectureFileService.update(uscLectureFile);
            reslut = dDictionaryService.getDDictionaryMapperByCode(openScope+"","2200").getName();
        }
        return ServiceResponse.createBySuccess(reslut);
    }



    @RequestMapping("listCourse.do")
    @ResponseBody
    public ServiceResponse listCourse(Integer startIndex,Integer count,HttpServletRequest request){
        return courseService.findVisibleCourseByCondition(startIndex,count,request);
    }


    @IsCheckUserLogin(check = true)
    @ResponseBody
    @RequestMapping("ajaxSetRelevantCourse.do")
    public ServiceResponse ajaxSetRelevantCourse(HttpServletRequest request,Long courseID){
        String[] releCourseIds = request.getParameterValues("rele_courseIds");
        int result = 0 ;
        for(int i = 0;i < releCourseIds.length ;i++){
            Map<String,Object> map = new HashMap<>();
            map.put("courseId",courseID);
            map.put("originalCourseId",releCourseIds[i]);
            CourseRelevantcourse rc = new CourseRelevantcourse();
            List<CourseRelevantcourse> courseRelevantcourses = courseRelevantcourseService.findByMap(map);
            if(courseRelevantcourses.size() > 0){
                rc = courseRelevantcourses.get(0);
                rc.setCourse_id(courseID);
                rc.setOriginal_course_id(Long.parseLong(releCourseIds[i]));
                result = courseRelevantcourseService.update(rc);
            }else {
                rc.setCourse_id(courseID);
                rc.setOriginal_course_id(Long.parseLong(releCourseIds[i]));
                result = courseRelevantcourseService.save(rc);
            }
        }
        if(result > 0 ){
            return ServiceResponse.createBySuccess();
        }
        return ServiceResponse.createByError();
    }

    @IsCheckUserLogin(check = true)
    @RequestMapping("ajaxdeleteRelevantCourse.do")
    @ResponseBody
    public ServiceResponse ajaxdeleteRelevantCourse(String[] relevantCourseList){
        int result = courseRelevantcourseService.delete(relevantCourseList);
        if(result > 0 ){
            return ServiceResponse.createBySuccess();
        }
        return ServiceResponse.createByError();
    }

    @IsCheckUserLogin(check = true)
    @RequestMapping("informServerAndList.do")
    public String informServerAndList(HttpServletRequest request){
        //todo
        boolean isUsedCloud = Boolean.parseBoolean(""+request.getSession().getAttribute("useCloud"));
        if(isUsedCloud){

        }
        return "test/error";
    }

    @IsCheckUserLogin(check = true)
    @RequestMapping("clearMySelectCourseSession.do")
    @ResponseBody
    public ServiceResponse clearMySelectCourseSession(HttpServletRequest request){

        request.getSession().removeAttribute("courseFormList");
        return ServiceResponse.createBySuccess();
    }

    @IsCheckUserLogin(check = true)
    @RequestMapping("listMySelectCourseToSchedule.do")
    public String listMySelectCourseToSchedule(HttpServletRequest request){

        this.courseCoursetypeService.listMySelectCourseToSchedule(request);

        String fromRelaCourse = request.getParameter("fromRelaCourse");

        if(fromRelaCourse!=null && fromRelaCourse.equals("true")){
            return  "courseManage/mySelectRelavantCourse";
        }else{
            return  "mixtraining/mySelectCourse";
        }
    }

    @IsCheckUserLogin(check = true)
    @RequestMapping("deleteMySelectCourseToSchedule.do")
    public String deleteMySelectCourseToSchedule(HttpServletRequest request){

        this.courseCoursetypeService.deleteMySelectCourseToSchedule(request);

        String relevantCoursePageFlag =  request.getParameter("relevantCoursePageFlag");

        if(relevantCoursePageFlag!=null && relevantCoursePageFlag.equals("1")){
            return  "courseManage/mySelectRelavantCourse";
        }else{
            return  "mixtraining/mySelectCourse";
        }
        /*<forward name="toMySelectCourse" path="/mixtraining/mySelectCourse.jsp" />
        <forward name="toMySelectRelavantCourse" path="/courseManage/mySelectRelavantCourse.jsp" />*/

    }



}
