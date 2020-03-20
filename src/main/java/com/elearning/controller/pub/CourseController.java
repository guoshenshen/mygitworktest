package com.elearning.controller.pub;

import com.elearning.interceptor.loginInterceptor.IsCheckUserLogin;
import com.elearning.pojo.pub.Course;
import com.elearning.service.pub.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 〈<br>
 * 〈〉
 *
 * @author lxx on 2019-06-21
 * @create 2019/6/21 15:45
 */
@Controller
@RequestMapping("/course/")
public class CourseController {
    @Autowired
    private ICourseService courseService;

    @IsCheckUserLogin(check = true)
    @RequestMapping("get.do")
    public String get(Long courseId, Model model){
        //测试git连接
        Course course = courseService.getCourse(courseId);
        model.addAttribute("course",course);
        model.addAttribute("courseName",course.getCourseId());
        return "course";
    }

    @IsCheckUserLogin(check = true)
    @RequestMapping(value = "ajaxGet.do",method = RequestMethod.POST)
    @ResponseBody
    public String ajaxGet(Long courseId, Model model){
        //测试git连接
        Course course = courseService.getCourse(courseId);
        model.addAttribute("courseName",course.getCourseId());
        return course.getCourseName();
    }

    @RequestMapping("login.do")
    @ResponseBody
    public String login(Long courseId, Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        session.setAttribute("orgId","1");
        //测试git连接
        Course course = courseService.getCourse(courseId);
        return course.getCourseName();
    }


    @RequestMapping("logout.do")
    @ResponseBody
    public String logon( HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute("orgId");
        //测试git连接

        return "删除session成功,退出登录";
    }












}
