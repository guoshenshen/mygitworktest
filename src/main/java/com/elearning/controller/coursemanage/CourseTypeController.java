package com.elearning.controller.coursemanage;

import com.elearning.common.Constants;
import com.elearning.common.ServiceResponse;
import com.elearning.interceptor.loginInterceptor.IsCheckUserLogin;
import com.elearning.pojo.coursemanage.CourseRelevantcourse;
import com.elearning.pojo.coursemanage.CourseType;
import com.elearning.pojo.coursemanage.UscLectureFile;
import com.elearning.pojo.mixtraining.MtMixTrainScheduleItemInfo;
import com.elearning.pojo.pub.EosOperator;
import com.elearning.pojo.pub.Train;
import com.elearning.pojo.teacher.TchrBaseInfo;
import com.elearning.service.coursemanage.ICourseRelevantcourseService;
import com.elearning.service.coursemanage.ICourseTypeService;
import com.elearning.service.coursemanage.IUscLectureFileService;
import com.elearning.service.mixtraining.IMtMixTrainScheduleItemInfoService;
import com.elearning.service.onlinetraining.IOnlineTrainingService;
import com.elearning.service.pub.ICourseService;
import com.elearning.service.pub.IDDictionaryService;
import com.elearning.service.pub.IEosorgTOrganizationService;
import com.elearning.service.teacher.ITchrBaseInfoService;
import com.elearning.util.DateTimeUtil;
import com.elearning.vo.pub.BasicOrgForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 〈<br>
 * 〈〉
 *
 * @author lxx
 * @create 2019/7/29 9:52
 */
@Controller
@RequestMapping("courseType")
public class CourseTypeController {

    @Autowired
    private ITchrBaseInfoService tchrBaseInfoService;

    @Autowired
    private ICourseTypeService courseTypeService;

    @Autowired
    private IDDictionaryService dDictionaryService;

    @Autowired
    private IEosorgTOrganizationService eosorgTOrganizationService;

    @Autowired
    private IOnlineTrainingService onlineTrainingService;

    @Autowired
    private ICourseService courseService;

    @Autowired
    private IMtMixTrainScheduleItemInfoService mtMixTrainScheduleItemInfoService;

    @Autowired
    private IUscLectureFileService uscLectureFileService;

    @Autowired
    private ICourseRelevantcourseService courseRelevantcourseService;


    @IsCheckUserLogin(check = true)
    @RequestMapping("addNewCourse")
    public String addNewCourse(HttpServletRequest request){
        EosOperator operator=(EosOperator) request.getSession().getAttribute(Constants.USERINFO_KEY);

        //写入 teacher
        TchrBaseInfo tchrBaseInfo;
        String teacherId = request.getParameter("teacherId");
        if(teacherId != null && !"".equals(teacherId)){
            tchrBaseInfo = tchrBaseInfoService.findById(Integer.parseInt(teacherId));
            request.setAttribute("teacher", tchrBaseInfo);
        }

        //专业领域  继续教育网中未用到
        Map<String,Object> map = new HashMap<>();
        map.put("parentId",1);
        List<CourseType> courseExpertAreaList = courseTypeService.findByMap(map);
        request.setAttribute("expertAreaList", courseExpertAreaList);

        //课件类别
        Map<String,String> kindMap = new HashMap<>();
        kindMap.put("1",Constants.single_address_courseware);
        kindMap.put("2",Constants.SCORM_courseware);
        kindMap.put("3",Constants.single_video_document_courseware);
        request.setAttribute("kinds", kindMap);

        //科目分类
        request.setAttribute("categoryList", dDictionaryService.getByParentCode("4020"));

        //资源分类
        request.setAttribute("resourceList", dDictionaryService.getByParentCode("4000"));


        // 课件上传平台，供课件外包公司上传使用、研究所管理员确认学时使用
        Integer tenantId=operator.getTenantId();
        if (tenantId == 1005) {
            request.setAttribute("courseUploadPlatform", 1);
            // 中科院基本机构查询
            Map<String,Object> condition = new HashMap<>();
            condition.put("openOrgSEQ", "1.2.99999.");
            List<BasicOrgForm> casOrgList = eosorgTOrganizationService
                    .findOrderTenantOrgByCondition(condition);
            request.setAttribute("casOrgList", casOrgList);
        } else {
            request.setAttribute("courseUploadPlatform", 0);
        }

        request.setAttribute("maker", operator.getOperatorName());
        request.setAttribute("createDate", DateTimeUtil.dateToStr(new Date(),"yyyy-MM-dd"));
        return "courseManage/addLessonInfo";
    }


    @IsCheckUserLogin(check = true)
    @RequestMapping("otherFileUpload.do")
    public String otherFileUpload(Long courseID,HttpServletRequest request,Integer courseTypeId){
        if(courseID == null){
            return "test/error";
        }
        if (request.getParameter("trainId") != null
                && !"".equals(request.getParameter("trainId"))) {
            int trainId = Integer.parseInt(request.getParameter("trainId")
                    .toString());
            Train train = onlineTrainingService.getOnlineTrainingByID(trainId);
            request.setAttribute("train", train);
        }
        String enterUrl = courseService.getCourse(courseID).getEnterUrl();
        Map<String,Object> map = new HashMap<>();
        map.put("courseId",courseID);
        if (enterUrl != null && !"".equals(enterUrl)) {
            List<MtMixTrainScheduleItemInfo> scheduleItemList = mtMixTrainScheduleItemInfoService.findByMap(map);
            if (scheduleItemList != null && scheduleItemList.size() > 0) {
                MtMixTrainScheduleItemInfo scheduleItem = scheduleItemList
                        .get(0);
                scheduleItem.setHasCourseUrl(1);
                mtMixTrainScheduleItemInfoService.update(scheduleItem);
            }
        }
        List<UscLectureFile> uscLectureFiles = uscLectureFileService.findAllLectureFile(courseID).getData();
        request.setAttribute("LectureList", uscLectureFiles);

        List<CourseRelevantcourse> courseRelevantcourses = courseRelevantcourseService.findByMap(map);
        for (CourseRelevantcourse courseRelevantcourse : courseRelevantcourses ) {
            courseRelevantcourse.setCourseName(courseService.getCourse(courseID).getCourseName());
        }
        request.setAttribute("RelatedCourseList", courseRelevantcourses);
        request.setAttribute("courseId", courseID);
        request.setAttribute("courseTypeId", courseTypeId);
        String chapter = request.getParameter("chapter");
        if(chapter  != null && !"".equals(chapter)){
            String chapterId = request.getParameter("chapterId");
            request.setAttribute("uploadChapter", 1);
            request.setAttribute("chapterId", chapterId);
        }
        return "courseManage/addLessonOtherFile";
    }


    @ResponseBody
    @RequestMapping("addRelatedCourseList.do")
    public ServiceResponse addRelatedCourseList(Long courseID){
        Map<String,Object> map = new HashMap<>();
        map.put("courseId",courseID);
        List<CourseRelevantcourse> courseRelevantcourses = courseRelevantcourseService.findByMap(map);
        for (CourseRelevantcourse courseRelevantcourse : courseRelevantcourses ) {
            courseRelevantcourse.setCourseName(courseService.getCourse(courseRelevantcourse.getOriginal_course_id()).getCourseName());
        }
        return ServiceResponse.createBySuccess(courseRelevantcourses);
    }
    @ResponseBody
    @RequestMapping("addLectureList.do")
    public ServiceResponse addLectureList(Long courseID){
        Map<String,Object> map = new HashMap<>();
        map.put("courseId",courseID);
        List<UscLectureFile> uscLectureFiles = uscLectureFileService.findAllLectureFile(courseID).getData();
        return ServiceResponse.createBySuccess(uscLectureFiles);
    }

    @IsCheckUserLogin(check = true)
    @RequestMapping("deleteLectureList.do")
    @ResponseBody
    public ServiceResponse deleteLectureList(HttpServletRequest request){
        String[] releCourseIds = request.getParameterValues("relevantCourseList");
        int result = uscLectureFileService.deleteUscLectureFile(releCourseIds);
        if(result > 0 ){
            return ServiceResponse.createBySuccess();
        }
        return ServiceResponse.createByError();
    }


}
