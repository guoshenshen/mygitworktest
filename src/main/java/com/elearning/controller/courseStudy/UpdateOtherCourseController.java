package com.elearning.controller.courseStudy;

import com.elearning.common.ServiceResponse;
import com.elearning.interceptor.loginInterceptor.IsCheckUserLogin;
import com.elearning.service.courseStudy.IUpdateCourseStudyTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

/**
 * 〈<br>
 * 〈〉
 *
 * @author lxx
 * @create 2019/10/15 10:10
 */
@Controller
@RequestMapping("/updateOtherCourse/")
public class UpdateOtherCourseController {

    @Autowired
    private IUpdateCourseStudyTimeService updateCourseStudyTimeService;


    @IsCheckUserLogin(check = true)
    @RequestMapping("updateStudy")
    @ResponseBody
    public ServiceResponse updateStudy(HttpServletRequest request) throws ParseException {
        return updateCourseStudyTimeService.updateCourseStudyTime(request);
    }






}
