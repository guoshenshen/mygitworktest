package com.elearning.controller.courseStudy;

import com.elearning.common.ResponseCode;
import com.elearning.common.ServiceResponse;
import com.elearning.interceptor.loginInterceptor.IsCheckUserLogin;
import com.elearning.service.courseStudy.IUserTrainService;
import com.elearning.service.coursemanage.IUscLectureFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 〈<br>
 * 〈〉
 *
 * @author lxx
 * @create 2019/9/23 14:59
 */
@Controller
@RequestMapping("/selectCourse/")
public class SelectCourseController {

    @Autowired
    private IUserTrainService userTrainService;


    @IsCheckUserLogin(check = true)
    @ResponseBody
    @RequestMapping("selectSingleCourse.do")
    public ServiceResponse selectSingleCourse(Long courseId, HttpServletRequest request){
        if(courseId == null){
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        return userTrainService.selectSingleCourse(courseId,request);
    }








}
