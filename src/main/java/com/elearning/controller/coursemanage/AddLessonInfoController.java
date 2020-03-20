package com.elearning.controller.coursemanage;

import com.elearning.common.*;
import com.elearning.interceptor.TokenInterceptor.SameUrlData;
import com.elearning.interceptor.loginInterceptor.IsCheckUserLogin;
import com.elearning.pojo.courseStudy.Chapter;
import com.elearning.pojo.coursemanage.CourseType;
import com.elearning.pojo.pub.Course;
import com.elearning.pojo.pub.EosOperator;
import com.elearning.pojo.pub.ResourceCourseType;
import com.elearning.pojo.pub.Train;
import com.elearning.pojo.systemManage.Tenant;
import com.elearning.service.courseStudy.IChapterSecondcourseService;
import com.elearning.service.courseStudy.IChapterService;
import com.elearning.service.coursemanage.ICourseTypeService;
import com.elearning.service.onlinetraining.IOnlineTrainingService;
import com.elearning.service.pub.ICourseService;
import com.elearning.service.pub.IDDictionaryService;
import com.elearning.service.pub.IEosorgTOrganizationService;
import com.elearning.service.pub.IResourceCourseTypeService;
import com.elearning.service.systemManage.ITenantService;
import com.elearning.util.DateTimeUtil;
import com.elearning.util.PropertiesUtil;
import com.elearning.vo.pub.BasicOrgForm;
import com.elearning.vo.CourseForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 〈<br>
 * 〈〉
 *
 * @author lxx
 * @create 2019/7/29 16:04
 */
@Controller
@RequestMapping("addLessonInfo")
public class AddLessonInfoController {

    @Autowired
    private IResourceCourseTypeService resourceCourseTypeService;

    @Autowired
    private IDDictionaryService dDictionaryService;

    @Autowired
    private ICourseService courseService;

    @Autowired
    private IOnlineTrainingService onlineTrainingService;

    @Autowired
    private IEosorgTOrganizationService eosorgTOrganizationService;

    @Autowired
    private ICourseTypeService courseTypeService;

    @Autowired
    private IChapterService chapterService;

    @Autowired
    private IChapterSecondcourseService chapterSecondcourseService;

    @Autowired
    private ITenantService tenantService;

    /**
     * 获取某部门对应的资源关联领域
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("getOrgRelatedTerritory.do")
    @ResponseBody
    public ServiceResponse getOrgRelatedTerritory(HttpServletRequest request){
        Long orgId = Long.parseLong(request.getSession()
                .getAttribute(Constants.ROOTORGID_KEY).toString());
        return resourceCourseTypeService.
                findByResourceId(orgId, ResourceType.ORG.getTypeCode());
    }


    @IsCheckUserLogin(check = true)
    @RequestMapping("insertCourse.do")
    public String insertCourse(CourseForm courseForm,HttpServletRequest request){
        int result = courseService.insertCourse(courseForm,request);
        String jspName = "test/error";
        if(result > 0 ){
            Integer courseDescription = Integer.parseInt(request.getParameter("courseDescription"));
            if(courseForm.getIsCoursePackage() != null && courseForm.getIsCoursePackage() == 1){
                if(courseDescription == 1){
                    request.setAttribute("courseDescription", 1);
                    jspName = "courseManage/addSingleVideoCourseware";
                }else{
                    jspName = "courseManage/addSectionCourse";
                }
            }else{
                if (courseForm.getKindId() == 1){
                    jspName = "courseManage/addSingleCourseware";
                }
                if (courseForm.getKindId() == 2){
                    jspName =  "courseManage/addLessonCourseware";
                }
                if (courseForm.getKindId() == 3){
                    jspName =  "courseManage/addSingleVideoCourseware";
                }
            }
        }
        return jspName;
    }

    @RequestMapping("select.do")
    public String select(HttpServletRequest request){
        return request.getParameter("jspName");
    }




    @IsCheckUserLogin(check = true)
    @RequestMapping("updateCourse.do")
    public String updateCourse(Long courseId,HttpServletRequest request){
        EosOperator operator=(EosOperator) request.getSession().getAttribute(Constants.USERINFO_KEY);
        if (request.getParameter("trainId") != null
                && !"".equals(request.getParameter("trainId"))) {
            Integer trainId = Integer.parseInt(request.getParameter("trainId")
                    .toString());
            Train train = onlineTrainingService.getOnlineTrainingByID(trainId);
            request.setAttribute("train", train);
        }
        //课件类别
        Map<String,String> kindMap = new HashMap<>();
        kindMap.put("1",Constants.single_address_courseware);
        kindMap.put("2",Constants.SCORM_courseware);
        kindMap.put("3",Constants.single_video_document_courseware);
        request.setAttribute("kinds", kindMap);
        Course course = courseService.getCourse(courseId);
        CourseForm addLessonInfoForm = new CourseForm();
        addLessonInfoForm.setIsCoursePackage(course.getIsCoursePackage());
        addLessonInfoForm.setIsFirstLevelDirectory(course.getIsFirstLevelDirectory());
        addLessonInfoForm.setClassHour(course.getClassHour());
        addLessonInfoForm.setCourseId(courseId);
        addLessonInfoForm.setCourseName(course.getCourseName());
        addLessonInfoForm.setCourseNo(course.getCourseNo());
        if (course.getOrgId() != null){
            addLessonInfoForm.setOrgId(course.getOrgId());
        }
        Date createDate = course.getCreateDate();
        String _createDate = DateTimeUtil.dateToStr(createDate,"yyyy-MM-dd");
        addLessonInfoForm.setCreateDate(_createDate);
        addLessonInfoForm.setCreator(course.getCreator());
        addLessonInfoForm.setFee(course.getFee());
        addLessonInfoForm.setIsOpenCourse(course.getIsOpenCourse());
        addLessonInfoForm.setSuitableObject(course.getSuitableObject());
        addLessonInfoForm.setMainContent(course.getContent());
        addLessonInfoForm.setRemark(course.getRemark());
        addLessonInfoForm.setMaker(course.getMaker());
        addLessonInfoForm.setPubStatus(course.getPubStatus());
        addLessonInfoForm.setScore(course.getScore());
        addLessonInfoForm.setMainContent(course.getContent());
        addLessonInfoForm.setRemark(course.getRemark());
        addLessonInfoForm.setExpertAreaId(course.getExpertAreaId());
        addLessonInfoForm.setSubExpertAreaId(course.getSubExpertAreaId());
        addLessonInfoForm.setKeyWords(course.getKeyWords());
        addLessonInfoForm.setRequiredCourseTypeId(course
                .getRequiredCourseTypeId());
        addLessonInfoForm.setBreed(course.getBreed());

        // 制作单位
        addLessonInfoForm.setProduceOrgName(course.getProduceOrgName());
        // 资助单位
        addLessonInfoForm.setFundingOrgName(course.getFundingOrgName());

        if (course.getClassfication() != null) {
            addLessonInfoForm.setClassfication(course.getClassfication());
        } else {
            addLessonInfoForm.setClassfication(0);
        }
        if (course.getCategory() != null) {
            addLessonInfoForm.setCategory(course.getCategory());
        } else {
            addLessonInfoForm.setCategory(0);
        }
        if (course.getHasTeacher() != null
                && course.getHasTeacher().intValue() == 1){
            addLessonInfoForm.setIsTeacher(1);
        }
        else{
            addLessonInfoForm.setIsTeacher(0);
        }
        if (course.getUpTenantId() != null){
            addLessonInfoForm.setUpTenantId(course.getUpTenantId());
        }else{
            addLessonInfoForm.setUpTenantId(0);
        }
        if (course.getIsNoted() != null && !course.getIsNoted().equals("")){
            addLessonInfoForm.setIsNoted(course.getIsNoted());
        }else{
            addLessonInfoForm.setIsNoted(0);
        }
        addLessonInfoForm.setKeyWords(course.getKeyWords());
        addLessonInfoForm.setKindId(Integer.parseInt(course.getSliceType()));
        if (course.getClassfication() != null
                && course.getClassfication().equals(4003)) {
            // 思维导图
            addLessonInfoForm.setKindId(3);
        }
        ResourceCourseType rct=  resourceCourseTypeService.findByResourceId(course.getCourseId(), ResourceType.COURSE.getTypeCode()).getData();
        if(rct != null){
            request.setAttribute("resourceCourseType", rct);
        }

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
        request.setAttribute("breedList", dDictionaryService.getByParentCode("3000"));
        //科目分类
        request.setAttribute("categoryList", dDictionaryService.getByParentCode("4020"));
        //资源分类
        request.setAttribute("resourceList", dDictionaryService.getByParentCode("4000"));
        request.setAttribute("stationIdList", dDictionaryService.getByParentCode("2900"));
        //专业领域  继续教育网中未用到
        Map<String,Object> map = new HashMap<>();
        map.put("parentId",1);
        List<CourseType> courseExpertAreaList = courseTypeService.findByMap(map);
        request.setAttribute("expertAreaList", courseExpertAreaList);
        request.setAttribute("course", addLessonInfoForm);
        return "courseManage/updateCourse";
    }
    @IsCheckUserLogin(check = true)
    @RequestMapping("courseDescription.do")
    public String courseDescription(Long courseId,HttpServletRequest request){
        request.setAttribute("courseId",courseId);
        request.setAttribute("courseDescription", request.getParameter("courseDescription"));
        return "courseManage/addSectionCourse";
    }

    @RequestMapping("getChapter.do")
    @ResponseBody
    public ServiceResponse getChapter(Long courseId,String chapterName){
        if(courseId == null){
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Map<String,Object> map = new HashMap<>();
        map.put("courseId",courseId);
        map.put("chapterName",chapterName);
        List<Chapter> chapterList = chapterService.findByMap(map);
        return ServiceResponse.createBySuccess(chapterList);
    }

    @IsCheckUserLogin(check = true)
    @RequestMapping("insertChapter.do")
    @ResponseBody
    public ServiceResponse insertChapter(Long courseId,String chapterName ,Long chapterId){
        if(courseId == null || chapterName == null){
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        return chapterService.inertChapter(courseId,chapterName,chapterId);
    }


    @IsCheckUserLogin(check = true)
    @ResponseBody
    @RequestMapping("orderChapter.do")
    public ServiceResponse orderChapter(HttpServletRequest request){
        Long courseId = Long.parseLong(request.getParameter("courseId"));
        String[] chapters = request.getParameterValues("chapterIds");
        int result = chapterSecondcourseService.orderChapter(courseId,chapters);
        if(result > 0 ){
            return ServiceResponse.createBySuccess();
        }
        return ServiceResponse.createByError();
    }

    @IsCheckUserLogin(check = true)
    @RequestMapping("deletechapter.do")
    @ResponseBody
    public ServiceResponse deletechapter(Long courseId,Long chapterId){
        if(courseId == null || chapterId == null){
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        return chapterService.deleteChapter(courseId,chapterId);
    }

    @IsCheckUserLogin(check = true)
    @RequestMapping("intoSectionFragmentEditor.do")
    public String intoSectionFragmentEditor(Long chapterId,HttpServletRequest request){
        Map<String,String> kindMap = new HashMap<>();
        kindMap.put("1",Constants.single_address_courseware);
        kindMap.put("2",Constants.SCORM_courseware);
        kindMap.put("3",Constants.single_video_document_courseware);
        request.setAttribute("kinds", kindMap);
        request.setAttribute("chapterId", chapterId);
        if(request.getSession().getAttribute("course") != null ){
            request.setAttribute("course",courseService.getCourseVo((Course)request.getSession().getAttribute("course")));
        }
        return "courseManage/sectionFragmentEditor";
    }


    @IsCheckUserLogin(check = true)
    @RequestMapping("insertChapterSecond.do")
    @ResponseBody
    public ServiceResponse insertChapterSecond(Long courseId,Long chapterId,
                                               Double classHour,Integer kindId,String courseName,HttpServletRequest request){
        if(courseName == null || classHour == null || kindId == null || chapterId == null){
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        return chapterService.insertChapterSecond(courseId,chapterId,classHour,kindId,courseName,request);
    }


    @ResponseBody
    @RequestMapping("getsecondCourse.do")
    public ServiceResponse getsecondCourse(String courseName,Long chapterId){
        if(chapterId == null){
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        return chapterService.getsecondCourse(courseName,chapterId);
    }


    @ResponseBody
    @RequestMapping("isCourseHold.do")
    public ServiceResponse isCourseHold(Long courseId){
        Course course = courseService.getCourse(courseId);
        Course oldCourse = chapterService.findCourseBySecondcourse(courseId);
        if(courseService.isAllreadyLearned(course.getCourseId()) && courseService.isAllreadyArranged(oldCourse.getCourseId())){
            return ServiceResponse.createByError();
        }
        return ServiceResponse.createBySuccess();
    }

    @RequestMapping("orderSecondCourseId.do")
    @ResponseBody
    public ServiceResponse orderSecondCourseId(HttpServletRequest request,Long chapterId){
        String[] courses = request.getParameterValues("courseIds");
        return chapterSecondcourseService.orderSecondCourseId(courses,chapterId);
    }

    @IsCheckUserLogin(check = true)
    @ResponseBody
    @RequestMapping("deleteSecond.do")
    public ServiceResponse deleteSecond(Long chapterId,Long courseId,HttpServletRequest request){
        if(chapterId == null || courseId == null ){
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        return chapterSecondcourseService.deleteSecond(chapterId,courseId,request);
    }


    @RequestMapping("uploadFileOrVideo.do")
    public String uploadFileOrVideo(Long courseId,Long chapterId,Integer sliceType,HttpServletRequest request){
        EosOperator operator = (EosOperator) request.getSession().getAttribute(Constants.USERINFO_KEY);
        Integer tenantId=operator.getTenantId();
        if (request.getParameter("trainId") != null
                && !"".equals(request.getParameter("trainId"))) {
            Integer trainId = Integer.parseInt(request.getParameter("trainId")
                    .toString());
            Train train = onlineTrainingService.getOnlineTrainingByID(trainId);
            request.setAttribute("train", train);
        }
        Tenant tenant = tenantService.findById(tenantId);
        Long orgId = Long.parseLong(request.getSession()
                .getAttribute(Constants.ROOTORGID_KEY).toString());
        Course course = courseService.getCourse(courseId);
        request.setAttribute("courseId",courseId);
        request.setAttribute("courseName", course.getCourseName());
        request.setAttribute("coursekind", sliceType);
        request.setAttribute("operatorOrgId", orgId);
        request.setAttribute("PictureURL", course.getPictureURL());// 课件缩略图
        request.setAttribute("courseUrl", course.getEnterUrl());// set to page
        request.setAttribute("customPath", course.getCustomFilePath());
        request.setAttribute("serverFilePath", course.getServerFilePath());
        request.setAttribute("uploadAddress",
                PropertiesUtil.getProperty("elearning-fs.address"));
        request.setAttribute("courseInfo", course);
        request.setAttribute("orgDomainName", tenant.getStoredContext());
        request.setAttribute("uploadChapter", 1);
        request.setAttribute("chapterId", chapterId);
        if (sliceType == 1){
            return  "courseManage/addSingleCourseware";
        }
        if (sliceType == 2){
            return   "courseManage/addLessonCourseware";
        }
        if (sliceType == 3){
            return  "courseManage/addSingleVideoCourseware";
        }
        return "test/error";
    }

    @ResponseBody
    @RequestMapping("getCourseRelevantCourse.do")
    public ServiceResponse getCourseRelevantCourse(Long originalCourseId,HttpServletRequest request){
        if(originalCourseId == null ){
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        return courseService.getCourseRelevantCourse(originalCourseId,request);
    }




}
