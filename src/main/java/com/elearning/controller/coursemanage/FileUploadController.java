package com.elearning.controller.coursemanage;

import com.elearning.common.Constants;
import com.elearning.common.ResponseCode;
import com.elearning.common.ServiceResponse;
import com.elearning.interceptor.loginInterceptor.IsCheckUserLogin;
import com.elearning.pojo.coursemanage.UscLectureFile;
import com.elearning.pojo.pub.Course;
import com.elearning.pojo.pub.EosOperator;
import com.elearning.pojo.systemManage.Tenant;
import com.elearning.service.coursemanage.ICourseVideoConvertService;
import com.elearning.service.coursemanage.IFileUploadService;
import com.elearning.service.coursemanage.IUscLectureFileService;
import com.elearning.service.pub.ICourseService;
import com.elearning.service.pub.IEosorgTOrganizationService;
import com.elearning.service.systemManage.ITenantService;
import com.elearning.vo.FileUploadActionForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 〈<br>
 * 〈〉
 *
 * @author lxx
 * @create 2019/8/1 16:09
 */
@Controller
@RequestMapping("/fileUpload/")
public class FileUploadController {

    @Autowired
    private ICourseService courseService;

    @Autowired
    private ICourseVideoConvertService courseVideoConvertService;

    @Autowired
    private IEosorgTOrganizationService eosorgTOrganizationService;

    @Autowired
    private ITenantService tenantService;

    @Autowired
    private IFileUploadService fileUploadService;

    @Autowired
    private IUscLectureFileService uscLectureFileService;

    @IsCheckUserLogin(check = true)
    @RequestMapping("uploadSingleVideo")
    public String uploadSingleVideo(HttpServletRequest request, HttpServletResponse response){
        String result = courseService.uploadSingleVideo(request,response);
        return result;
    }


    @IsCheckUserLogin(check = true)
    @RequestMapping("getConvertSingleVideoStatus.do")
    @ResponseBody
    public ServiceResponse getConvertSingleVideoStatus(Long uploadCourseId ){
        return courseVideoConvertService.findCourseVideoconvertByCourseId(uploadCourseId);
    }


    @IsCheckUserLogin(check = true)
    @RequestMapping("uploadSingleCourseFileSuccess.do")
    @ResponseBody
    public ServiceResponse uploadSingleCourseFileSuccess(Long courseId,String folder,HttpServletRequest request){
        if(courseId == null || folder == null){
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }

        EosOperator operator = (EosOperator) request.getSession().getAttribute(Constants.USERINFO_KEY);
        Integer tenantId=operator.getTenantId();
        Tenant tenant = tenantService.findById(tenantId);
        String serverFilePath = tenant.getStoredContext() + "/" + folder
                + "/";
        String courseUrl = "";
        try {
            Map<String,Object> map = new HashMap<>();
            Course course = courseService.getCourse(courseId);
            Integer orgId = Integer.parseInt(request.getSession().getAttribute(Constants.ROOTORGID_KEY).toString());
            if(course.getOrgId() != null && !course.getOrgId().toString().trim().equals("")){
                orgId = course.getOrgId();
            }
            if(tenantId == 1005){
                tenant = eosorgTOrganizationService.findTenant(orgId);
                serverFilePath = tenant.getStoredContext() + "/" + folder
                        + "/";
            }
            course.setServerId(tenant.getServerId());
            if(course.getServerFilePath()!=null && !course.getServerFilePath().equals(serverFilePath)){
                if(course.getEnterUrl()!=null && !"".equals(course.getEnterUrl())){
                    String enterUrl = course.getEnterUrl().replace(course.getServerFilePath(), serverFilePath);
                    course.setEnterUrl(enterUrl);
                    courseUrl = enterUrl;
                }
                if(course.getMobilePlayAddress()!=null && !"".equals(course.getMobilePlayAddress())){
                    String mobilePlayAddress = course.getMobilePlayAddress().replace(course.getServerFilePath(), serverFilePath);
                    course.setMobilePlayAddress(mobilePlayAddress);
                }
            }
            course.setServerFilePath(serverFilePath);
            int result = courseService.updateCourse(course);
            if(result > 0 ){
                map.put("courseUrl",courseUrl);
                map.put("serverFilePath",serverFilePath);
                return ServiceResponse.createBySuccess(map);
            }
            return ServiceResponse.createByError();
        }catch (Exception e){
            return ServiceResponse.createByError();
        }
    }


    @IsCheckUserLogin(check = true)
    @RequestMapping("uploadSingleCourse.do")
    public String uploadSingleCourse(HttpServletRequest request){
        String resultName = courseService.uploadSingleCourse(request);
        return resultName;
    }

    @IsCheckUserLogin(check = true)
    @RequestMapping("updateCourseUrl.do")
    @ResponseBody
    public ServiceResponse updateCourseUrl(Long courseId,String enterUrl){
        if(courseId == null || enterUrl == null){
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Course  course = courseService.getCourse(courseId);
        course.setEnterUrl(enterUrl);
        int result = courseService.updateCourse(course);
        if(result > 0){
            return ServiceResponse.createBySuccessMessage("课件存储地址修改成功!");
        }
        return ServiceResponse.createByErrorMessage("课件存储地址修改失败!");
    }


    @IsCheckUserLogin(check = true)
    @RequestMapping(value = "uploadCourse.do",method=RequestMethod.POST)
    public String uploadCourse(HttpServletRequest request, FileUploadActionForm fileUploadActionForm){
        if(fileUploadService.uploadCourse(request,fileUploadActionForm.getCoursezipfile())){
            return "courseManage/addLessonCourseware";
        }
        return "test/error";
    }


    @IsCheckUserLogin(check = true)
    @RequestMapping("saveScheduleCourseLecture.do")
    @ResponseBody
    public ServiceResponse saveScheduleCourseLecture(Long courseId,String lectrueName ,String lecturefilePath){
        if(courseId == null || lectrueName == null || lecturefilePath == null ){
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        UscLectureFile uscLectureFile = new UscLectureFile();
        uscLectureFile.setLectureName(lectrueName);
        uscLectureFile.setLectureURL(lecturefilePath);
        uscLectureFile.setCourseId(courseId);
        uscLectureFile.setOpenScope(0);
        int result = uscLectureFileService.insertLectureFile(uscLectureFile);
        if(result > 0 ){
            return ServiceResponse.createBySuccess();
        }
        return ServiceResponse.createByError();
    }








}
