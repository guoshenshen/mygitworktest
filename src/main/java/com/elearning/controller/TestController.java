package com.elearning.controller;

import com.elearning.common.Constants;
import com.elearning.common.ServiceResponse;
import com.elearning.interceptor.loginInterceptor.IsCheckUserLogin;
import com.elearning.pojo.pub.Course;
import com.elearning.pojo.pub.EosOperator;
import com.elearning.service.ITestService;
import com.elearning.service.pub.ICourseService;
import com.elearning.service.pub.IEosOperatorService;
import com.elearning.service.systemManage.IUserRoleService;
import com.elearning.util.PropertiesUtil;
import cn.kepu.elearningfs.webservice.ICommonToolsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * 〈<br>
 * 〈〉
 * 测试controller   以课程或登录为例
 * @author lxx
 * @create 2019/7/2 8:51
 */

@Controller
@RequestMapping("/test/")
public class TestController {


    /**
     * 通过@Autowired 注解 注入 service
     */
    @Autowired
    private ITestService testService;

    @Autowired
    private IEosOperatorService eosOperatorService;

    @Autowired
    private ICommonToolsService commonToolsService;

    @Autowired
    private IUserRoleService userRoleService;

    @Autowired
    private ICourseService courseService;

    /**
     * @RequestMapping 配置url
     * 返回jsp时 返回值为String类型
     * @param request
     * @return
     */
    @RequestMapping("login.do")
    public String login(HttpServletRequest request, Model model,String userId ,String password){
        HttpSession session = request.getSession();
        EosOperator eosOperator = eosOperatorService.selectByPrimaryKey(977904893);
        eosOperator.setTenantId(Integer.parseInt(PropertiesUtil.getProperty("tenantId")));
        session.setAttribute(PropertiesUtil.getProperty("USERINFO_KEY"),eosOperator);
        session.setAttribute(Constants.ROOTORGID_KEY,2030011975);

        //=============================================

        HashMap map = this.userRoleService.queryUserRoleOrgId(eosOperator.getOperatorId(), eosOperator.getTenantId());
        request.getSession().setAttribute(Constants.USERROLE_KEY, map);
        int currentRoleId = -1;
        int studentRoleId = -1;
        if (map.size() > 0) {
            Set set = map.keySet();
            Iterator it = set.iterator();
            boolean hasStudentRole = false;
            while (it.hasNext()) {
                Integer roleId = (Integer) it.next();
                Integer orgId = (Integer) map.get(roleId);
                if (orgId==-1) {
                    studentRoleId = roleId;
                    hasStudentRole = true;
                }
                if (!orgId.toString().equals("-1")) {
                    if(currentRoleId == -1){ // currentRoleId赋值为map中第一个非学员的角色
                        currentRoleId = roleId;
                        request.getSession().setAttribute(Constants.ROOTORGID_KEY,
                                orgId);
                    }
                    if(hasStudentRole){
                        break;// 如果已经放入学员角色，则不必再循环，如果没有，则需遍历全部角色
                    }
                }
            }
        }
        //有学员端角色,则优先存入学员端角色
        if(studentRoleId!=-1){
            currentRoleId = studentRoleId;
        }

        session.setAttribute("userRoleId", currentRoleId);

        //=============================================

        model.addAttribute("msg","登录成功");
        return "test/success";
    }

    @RequestMapping("logout.do")
    public String logout(HttpServletRequest request,Model model){
        HttpSession session = request.getSession();
        session.removeAttribute("orgId");
        model.addAttribute("msg","退出成功");
        return "test/success";
    }

    /**
     *  IsCheckUserLogin 为验证登录的注解   注解在返回jsp的方法上时 跳转到主页,注解在接口上时 返回 status = 10 的json数据
     *  ResponseBody为接口注解,使用时返回json数据 不返回jsp  可以返回 ServiceResponse
     *  Model 相当于
     *   num,size 为分页数据  默认为 1,10 分页插件见 testServiceImpl
     *
     * @param
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("get.do")
    @ResponseBody
    public ServiceResponse get(@RequestParam(value = "num",defaultValue = "1")Integer num,
                               @RequestParam(value = "size",defaultValue = "10")Integer size){
        return testService.listCourse(num, size);
    }


    /**
     * 未登陆的情况下跳转到主页
     * @param courseId
     * @param model
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("course.do")
    public String course(Long courseId,Model model){
        if( courseId == null){
            model.addAttribute("msg","参数出错");
            return "test/error";
        }
        Course course = testService.getCourseById(courseId);
        model.addAttribute("course",course);
        return "course";
    }


    /**
     * service中有事务回滚
     * @param courseId
     * @return
     */

    @RequestMapping("delete.do")
    @ResponseBody
    public ServiceResponse delete(Long courseId){

        return testService.delete(courseId);

    }

    @IsCheckUserLogin()
    @RequestMapping("admin.do")
    public String admin(HttpServletRequest request){
        EosOperator operator = (EosOperator)request.getSession().getAttribute(PropertiesUtil.getProperty("USERINFO_KEY"));
        if(operator == null) {
            return "index";
        }else{
            return "template/defaultAdminFrame";
        }
    }




    @RequestMapping("webservice.do")
    @ResponseBody
    public String webService(){

        boolean a  = commonToolsService.moveFolder("123456","654321");
        if(a ){
            return "true";
        }else{
            return "false";
        }
    }












}
