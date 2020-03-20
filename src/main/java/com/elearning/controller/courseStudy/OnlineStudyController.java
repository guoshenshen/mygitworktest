package com.elearning.controller.courseStudy;

import com.alipay.api.domain.MpPrizeInfoModel;
import com.elearning.common.Constants;
import com.elearning.common.ResponseCode;
import com.elearning.common.ServiceResponse;
import com.elearning.common.Tools;
import com.elearning.interceptor.loginInterceptor.IsCheckUserLogin;
import com.elearning.pojo.courseStudy.Chapter;
import com.elearning.pojo.courseStudy.UcsEmployeeCourse;
import com.elearning.pojo.pub.Course;
import com.elearning.pojo.pub.EosOperator;
import com.elearning.pojo.pub.EosorgTOrganization;
import com.elearning.service.courseStudy.IChapterService;
import com.elearning.service.courseStudy.IOnlineStudyService;
import com.elearning.service.courseStudy.IUcsEmployeeCourseService;
import com.elearning.service.pub.ICourseService;
import com.elearning.service.pub.IEosorgTOrganizationService;
import com.elearning.util.PropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 〈<br>
 * 〈〉
 *
 * @author lxx
 * @create 2019/7/15 9:46
 */
@Controller
@RequestMapping("/onlineStudy/")
public class OnlineStudyController {

    @Autowired
    private IOnlineStudyService onlineStudyService;

    @Autowired
    private IEosorgTOrganizationService eosorgTOrganizationService;

    @Autowired
    private IChapterService chapterService;

    @Autowired
    private IUcsEmployeeCourseService ucsEmployeeCourseService;


    @RequestMapping("loadDomainSystem.do")
    @ResponseBody
    public ServiceResponse loadDomainSystem(Integer rootDomain){
        if(rootDomain == null){
            rootDomain = Integer.parseInt(PropertiesUtil.getProperty("domainId"));
        }
        return onlineStudyService.getDomainSystem(rootDomain);
    }


    /**
     * 课程目录
     * @param num
     * @param size
     * @param request
     * @return
     */
    @RequestMapping("courseList.do")
    @ResponseBody
    public ServiceResponse courseList(@RequestParam(value = "num",defaultValue = "1")Integer num,
                                      @RequestParam(value = "size",defaultValue = "10")Integer size,
                                      HttpServletRequest request){
        String domainId = request.getParameter("domainId");
        String[] classificationStr = request.getParameterValues("classification");
        String filter = request.getParameter("filter");
        String courseName = request.getParameter("courseName");
        String[] tenantStr = request.getParameterValues("source");
        String orgSEQ ;
        EosOperator operator = (EosOperator)request.getSession().getAttribute(PropertiesUtil.getProperty("USERINFO_KEY"));
        if(operator!=null){
            orgSEQ=request.getParameter("orgSEQ");
            if(orgSEQ==null||orgSEQ.trim().length()==0){
                Integer currentOrgId = operator.getEosorgTEmployee().getOrgID();
                EosorgTOrganization currentOrg = this.eosorgTOrganizationService.getEosorgTOrganizationById(currentOrgId);
                orgSEQ = currentOrg.getOrgSEQ();
            }
        }else{
            orgSEQ = PropertiesUtil.getProperty("orgSEQ");
        }
        Map<String,Object> conditions=new HashMap<>();
        if(domainId!=null&&domainId.trim().length()>0){
            conditions.put("domain",domainId);
        }
        if(courseName!=null&&courseName.trim().length()>0){
            conditions.put("courseName",courseName);
        }
        if(filter!=null&&filter.trim().length()>0){
            conditions.put("filter", filter.trim());
        }
        if(classificationStr!=null&&classificationStr.length>0){
            conditions.put("classification", Arrays.asList(classificationStr));
        }
        if(tenantStr!=null&&tenantStr.length>0){
            conditions.put("tenant", Arrays.asList(tenantStr));
        }
        conditions.put("orgSEQ", orgSEQ);
        conditions.put("num",num);
        conditions.put("size",size);
        return onlineStudyService.getCourseByCondition(conditions);
    }


    /**
     * 课程来源
     * @param request
     * @return
     */
    @RequestMapping("loadRelatedOrgList.do")
    @ResponseBody
    public ServiceResponse loadRelatedOrgList(HttpServletRequest request){
        EosOperator operator = (EosOperator)request.getSession().getAttribute(PropertiesUtil.getProperty("USERINFO_KEY"));
        StringBuffer result=new StringBuffer("");
        String orgSEQ;
        String domainId=request.getParameter("domainId");
        String filter=request.getParameter("filter");
        String[] classificationStr=request.getParameterValues("classification");
        String courseName=request.getParameter("courseName");
        Map<String,Object> conditions=new HashMap<String,Object>();
        if(operator!=null){
            orgSEQ=request.getParameter("orgSEQ");
            if(orgSEQ==null || orgSEQ.trim().length() == 0){
                Integer currentOrgId = operator.getEosorgTEmployee().getOrgID();
                EosorgTOrganization currentOrg = this.eosorgTOrganizationService.getEosorgTOrganizationById(currentOrgId);
                orgSEQ = currentOrg.getOrgSEQ();
            }
        }else{
            orgSEQ = PropertiesUtil.getProperty("orgSEQ");
        }
        conditions.put("orgSEQ", orgSEQ);



        if(domainId == null || domainId.trim().length() == 0){
            domainId = PropertiesUtil.getProperty("domainId");
        }
        conditions.put("domain",domainId);
        if(courseName!=null&&courseName.trim().length()>0){
            conditions.put("courseName",courseName);
        }
        if(filter!=null&&filter.trim().length()>0){
            conditions.put("filter", filter.trim());
        }
        if(classificationStr!=null&&classificationStr.length>0){
            conditions.put("classification", Arrays.asList(classificationStr));
        }
        return onlineStudyService.loadCourseRelatedOrg(conditions);
    }


    /**
     * 选学按钮
     * @param courseId
     * @param request
     * @return
     * @throws Exception
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("selectSingleCourse.do")
    @ResponseBody
    public ServiceResponse selectSingleCourse(Long courseId,HttpServletRequest request) throws Exception {
        if(courseId == null){
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        EosOperator eosoperator = (EosOperator) request.getSession()
                .getAttribute(PropertiesUtil.getProperty("USERINFO_KEY"));
        if(eosoperator == null ){
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }
        return onlineStudyService.selectSingleCourse(courseId,eosoperator);

    }


    @RequestMapping("getChapters.do")
    @ResponseBody
    @IsCheckUserLogin(check = true)
    public ServiceResponse getChapters(Long courseId,HttpServletRequest request){
        int userId = 0;
        EosOperator eosoperator = (EosOperator) request.getSession()
                .getAttribute(Constants.USERINFO_KEY);
        if (eosoperator != null && eosoperator.getOperatorId() != null) {
            userId = eosoperator.getOperatorId();
        }
        if(courseId == null){
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Map<String,Object> map = new HashMap<>();
        map.put("courseId",courseId);
        List<Chapter> chapterList = chapterService.findByMap(map);
        for (Chapter chapter : chapterList){
            List<Course> courseList = chapterService.listCourseByChapterId(chapter.getChapterId());
            for(Course course : courseList){
                map.put("operatorID",userId);
                map.put("year",Integer.valueOf(String.valueOf(Tools.getCurrentYear())));
                List<UcsEmployeeCourse> ucsEmployeeCourseList = ucsEmployeeCourseService.findByExample(map);
                if(ucsEmployeeCourseList.size()>0){
                    course.setStudyProgress(ucsEmployeeCourseList.get(0).getStudyProgress()+"");
                }else{
                    course.setStudyProgress("0.0");
                }
            }
            chapter.setSubset(courseList);
        }
        return ServiceResponse.createBySuccess(chapterList);
    }
}
