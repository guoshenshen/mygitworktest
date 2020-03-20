package com.elearning.controller.teacher;

import com.elearning.common.Constants;
import com.elearning.common.ServiceResponse;
import com.elearning.interceptor.loginInterceptor.IsCheckUserLogin;
import com.elearning.pojo.pub.EosOperator;
import com.elearning.service.teacher.ITchrBaseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 〈<br>
 * 〈〉
 *
 * @author lxx
 * @create 2019/7/30 9:49
 */
@Controller
@RequestMapping("tchrBaseInfo")
public class TchrBaseInfoController {

    @Autowired
    private ITchrBaseInfoService tchrBaseInfoService;

    /**
     * 新增课程时教师列表
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("listAllTeacherForCourse.do")
    @ResponseBody
    public ServiceResponse listAllTeacherForCourse(@RequestParam(value = "startIndex" ,defaultValue = "1")Integer startIndex ,
                                                   @RequestParam(value = "count" ,defaultValue = "20") Integer count,
                                                   String teacherName, HttpServletRequest request){
        Object orgIdStr = request.getSession().getAttribute("orgId");
        Long orgId ;
        if(orgIdStr != null){
            orgId = Long.parseLong(orgIdStr.toString());
        }else{
            // TODO
            orgId = Long.parseLong(request.getSession().getAttribute(Constants.ROOTORGID_KEY).toString());
        }
        Map<String,Object> map = new HashMap<>();
        map.put("startIndex",startIndex);
        map.put("count",count);
        map.put("teacherName",teacherName);
        map.put("orgId",orgId);
        return tchrBaseInfoService.getTeacherByConditionByPage(map);
    }

    /**
     * 判断教师名称是否存在
     * @param teacherName
     * @param request
     * @return
     */
    @RequestMapping("judgeTeacher.do")
    @ResponseBody
    public ServiceResponse judgeTeacher(String teacherName,HttpServletRequest request){
        Long orgId=Long.parseLong(request.getSession().getAttribute(Constants.ROOTORGID_KEY).toString());
        return tchrBaseInfoService.judgeTeacher(teacherName,orgId);
    }


}
