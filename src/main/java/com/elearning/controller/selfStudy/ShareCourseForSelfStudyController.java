package com.elearning.controller.selfStudy;

import com.elearning.common.Constants;
import com.elearning.common.ServiceResponse;
import com.elearning.interceptor.loginInterceptor.IsCheckUserLogin;
import com.elearning.pojo.pub.EosOperator;
import com.elearning.service.courseStudy.IUcsEmployeeCourseService;
import com.elearning.vo.CourseStudy.UserJoinedCourseForm;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 〈<br>
 * 〈〉
 *
 * @author lxx
 * @create 2019/10/22 14:00
 */
@Controller
@RequestMapping("/shareCourseForSelfStudy/")
public class ShareCourseForSelfStudyController {

    @Autowired
    private IUcsEmployeeCourseService ucsEmployeeCourseService;


    @RequestMapping("getShareCourseList.do")
    @ResponseBody
    @IsCheckUserLogin(check = true)
    public ServiceResponse getShareCourseList(@RequestParam(value = "startIndex",defaultValue = "1")Integer startIndex,
                                              @RequestParam(value = "count",defaultValue = "20")Integer count,
                                              HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        map.put("startIndex",startIndex);
        map.put("count",count);
        EosOperator eosoperator =(EosOperator) request.getSession().getAttribute(Constants.USERINFO_KEY);
        Integer userId = eosoperator.getOperatorId();
        map.put("operatorID",userId);
        Integer tenantId = eosoperator.getTenantId();
        String studyProgress = request.getParameter("studyProgress");
        map.put("studyProgress",studyProgress);
        PageHelper.startPage(startIndex,count);
        List<UserJoinedCourseForm> userJoinedCourseForms = ucsEmployeeCourseService.getUserCourseProgress(map);
        PageInfo pageInfo = new PageInfo(userJoinedCourseForms);
        pageInfo.setList(userJoinedCourseForms);
        return ServiceResponse.createBySuccess(pageInfo);
    }




}
