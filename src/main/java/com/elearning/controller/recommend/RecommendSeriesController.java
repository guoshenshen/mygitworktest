package com.elearning.controller.recommend;

import com.elearning.common.Constants;
import com.elearning.common.ResourceType;
import com.elearning.common.ResponseCode;
import com.elearning.common.ServiceResponse;
import com.elearning.interceptor.loginInterceptor.IsCheckUserLogin;
import com.elearning.pojo.TopicClassStudy.TopicBanner;
import com.elearning.pojo.pub.Course;
import com.elearning.pojo.pub.EosOperator;
import com.elearning.pojo.pub.EosorgTOrganization;
import com.elearning.pojo.pub.Train;
import com.elearning.pojo.recommend.RecommendSeries;
import com.elearning.pojo.systemManage.Tenant;
import com.elearning.service.TopicClassStudy.ITopicBannerService;
import com.elearning.service.onlinetraining.IOnlineTrainingService;
import com.elearning.service.pub.ICourseService;
import com.elearning.service.pub.IEosorgTEmployeeService;
import com.elearning.service.pub.IEosorgTOrganizationService;
import com.elearning.service.recommend.IRecommendSeriesService;
import com.elearning.service.recommend.ISeriesItemService;
import com.elearning.service.systemManage.ITenantService;
import com.elearning.util.PropertiesUtil;
import com.elearning.vo.CourseVo;
import com.elearning.vo.onlinetraining.BasicTrainForm;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 *
 */
@Controller
@RequestMapping("/recommendSeries/")
public class RecommendSeriesController {

    @Autowired
    private IRecommendSeriesService recommendSeriesService;

    @Autowired
    private ISeriesItemService seriesItemService;

    @Autowired
    private ITopicBannerService topicBannerService;

    @Autowired
    private ITenantService tenantService;

    @Autowired
    private IEosorgTOrganizationService eosorgTOrganizationService;

    @Autowired
    private IEosorgTEmployeeService eosorgTEmployeeService;

    @Autowired
    private ICourseService courseService;

    @RequestMapping("getRecommendSeriesByID.do")
    @ResponseBody
    public ServiceResponse getRecommendSeriesByID(Integer id, Model model){
        RecommendSeries recommendSeries = recommendSeriesService.selectByPrimaryKey(id);

        return ServiceResponse.createBySuccess(recommendSeries);
    }


    /**
     * 根据某课程id,获取相同专题下的其他课程(包含该课程)
     * @param courseId
     * @return
     */
    @RequestMapping("getCourseListUnderSameSeries.do")
    @ResponseBody
    public ServiceResponse getCourseListUnderSameSeries(Long courseId, HttpServletRequest request){
        if(courseId == null ){
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        return seriesItemService.getCourseListUnderSameSeries(courseId,request);
    }


    @RequestMapping("intoSeriesItemFrame.do")
    public String intoSeriesItemFrame(HttpServletRequest request,Integer seriesId){
        RecommendSeries currentRecommendSeries= recommendSeriesService.selectByPrimaryKey(seriesId);
        //获取该专题所包含的子项类型,及该类型的数目（如{课件：12;培训：13; 通知：1}）
        Map<Integer,Long> resourceDistribution = seriesItemService.getSeriesItemTypeDistribution(seriesId);
        //resourceDistribution:{"类型","数量"},{"类型","数量"}
        Set<Integer> keySet=resourceDistribution.keySet();
        List<ResourceType> resourceTypeList=new ArrayList<>();
        for(Integer resourceTypeCode:keySet){
            ResourceType resourceType=ResourceType.findByCode(resourceTypeCode);
            if(resourceType!=null){
                resourceTypeList.add(resourceType);
            }
        }
        Map<String,Object> topicBannerCondition=new HashMap<>();
        topicBannerCondition.put("type", ResourceType.TOPIC.getTypeCode());
        topicBannerCondition.put("seriesId", seriesId);
        List<TopicBanner> topicBanners = topicBannerService.findOrderedBanner(topicBannerCondition);
        //获取专题系列主体信息
        request.setAttribute("recommendSeries", currentRecommendSeries);
        //用于生成专题系列下的选项卡信息
        request.setAttribute("resourceTypeList", resourceTypeList);
        //用于生成系列专题对应的大图信息(支持多大图轮播)
        request.setAttribute("topicBanners", topicBanners);
        return "recommendSeries/seriesItems";
    }


    @ResponseBody
    @RequestMapping("getItemsOfSeries.do")
    public ServiceResponse getItemsOfSeries(HttpServletRequest request,Integer seriesId ,Integer itemType){
        Map<String,Object> map = new HashMap<>();
        Integer startIndex=0;
        if(request.getParameter("startIndex")!=null){
            startIndex=Integer.parseInt(request.getParameter("startIndex") );
        }
        Integer length=0;
        if(request.getParameter("length")!=null){
            length=Integer.parseInt(request.getParameter("length") );
        }
        EosOperator operator = (EosOperator) request.getSession().getAttribute(Constants.USERINFO_KEY);
        String orgSEQ;
        if(operator==null){
            //当前访问学员尚未登录
            Tenant currentTenant = tenantService.findById(Integer.parseInt(PropertiesUtil.getProperty("tenantId")));
            EosorgTOrganization tenantOrg = eosorgTOrganizationService.findById(currentTenant.getOrgId());
            orgSEQ=tenantOrg.getOrgSEQ();
        } else{
            EosorgTOrganization org = eosorgTOrganizationService.findById(eosorgTEmployeeService.findById(operator.getOperatorId()).getOrgID());
            orgSEQ = org.getOrgSEQ();
        }
        Map<String,Object> condition=new HashMap<>();
        condition.put("orgSEQ", orgSEQ);
        condition.put("seriesId", seriesId);
        if(itemType.equals(ResourceType.COURSE.getTypeCode())){
            PageHelper.startPage(startIndex+1,length+1);
            List<Course> courseList = seriesItemService.findCourseByCondition(condition);
            PageInfo pageInfo = new PageInfo(courseList);
            List<CourseVo> courseVos = new ArrayList<>();
            for(Course course:courseList){
                courseVos.add(courseService.getCourseVo(course));
            }
            pageInfo.setList(courseVos);
            map.put("data",pageInfo);
            if(courseList.size()<=length){
                map.put("stopScroll",true);
            }
            return ServiceResponse.createBySuccess(map);
        }
        if(itemType.equals(ResourceType.TRAIN.getTypeCode())){
            PageHelper.startPage(startIndex+1,length+1);
            List<BasicTrainForm> trains = seriesItemService.findTrainByCondition(condition);
            PageInfo pageInfo = new PageInfo(trains);
            pageInfo.setList(trains);
            map.put("data",pageInfo);
            if(trains.size()<=length){
                map.put("stopScroll",true);
            }
            return ServiceResponse.createBySuccess(map);
        }
        if(itemType.equals(ResourceType.TOPIC.getTypeCode())){
            PageHelper.startPage(startIndex+1,length+1);
            List<RecommendSeries> subSeriesList = seriesItemService.findSeriesByCondition(condition);
            PageInfo pageInfo = new PageInfo(subSeriesList);
            pageInfo.setList(subSeriesList);
            map.put("data",pageInfo);
            if(subSeriesList.size()<=length){
                map.put("stopScroll",true);
            }else{
                map.put("stopScroll",false);
            }
            return ServiceResponse.createBySuccess(map);
        }
        return ServiceResponse.createByError();
    }

    @RequestMapping("intoSeriesFrame.do")
    public String intoSeriesFrame(HttpServletRequest request){
        EosOperator operator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        String orgSEQ;
        String tenantOrgSEQ;
        EosorgTOrganization tenantOrg;
        if(operator==null){
            //当前访问学员尚未登录
            Tenant currentTenant = tenantService.findById(Integer.parseInt(PropertiesUtil.getProperty("tenantId")));
            tenantOrg = eosorgTOrganizationService.findById(currentTenant.getOrgId());
            tenantOrgSEQ=tenantOrg.getOrgSEQ();
            orgSEQ=tenantOrg.getOrgSEQ();
        } else{
            EosorgTOrganization org = eosorgTOrganizationService.findById(eosorgTEmployeeService.findById(operator.getOperatorId()).getOrgID());
            orgSEQ = org.getOrgSEQ();
            Tenant operatorTenant = tenantService.findById(operator.getTenantId());
            tenantOrg = eosorgTOrganizationService.findById(operatorTenant.getOrgId());
            tenantOrgSEQ=tenantOrg.getOrgSEQ();
        }
        request.setAttribute("orgSEQ", orgSEQ);
        request.setAttribute("tenantOrgSEQ", tenantOrgSEQ);
        return "recommendSeries/seriesList";
    }


    @ResponseBody
    @RequestMapping("getRecommendSeriesList.do")
    public ServiceResponse getRecommendSeriesList(HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        EosOperator operator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        String startIndex=request.getParameter("startIndex");
        String lengthStr=request.getParameter("length");
        String title=request.getParameter("title");
        String orgSEQ;
        Integer tenantId=null;
        //显示全部专题or本单位专题
        String showAll=request.getParameter("showAll");
        if(startIndex==null){
            startIndex="0";
        }
        if(lengthStr==null){
            lengthStr="19";
        }
        Integer length=Integer.parseInt(lengthStr);

        EosorgTOrganization tenantOrg;
        if(operator==null){
            //当前访问学员尚未登录
            Tenant currentTenant = tenantService.findById(Integer.parseInt(PropertiesUtil.getProperty("tenantId")));
            tenantOrg = eosorgTOrganizationService.findById(currentTenant.getOrgId());
            orgSEQ=tenantOrg.getOrgSEQ();
        } else{
            EosorgTOrganization org = eosorgTOrganizationService.findById(eosorgTEmployeeService.findById(operator.getOperatorId()).getOrgID());
            orgSEQ = org.getOrgSEQ();
            tenantId=operator.getTenantId();
        }
        Map<String,Object> conditionMap=new HashMap<>();
        conditionMap.put("orgSEQ", orgSEQ);
        conditionMap.put("showAll", showAll);
        conditionMap.put("title",title);
        if(showAll!=null&&showAll.trim().equals("false")){
            conditionMap.put("tenantId",tenantId);
        }
        PageHelper.startPage(Integer.parseInt(startIndex)+1,length+1);
        List<RecommendSeries> series = recommendSeriesService.findVisibleSeriesByCondition(conditionMap);
        PageInfo pageInfo = new PageInfo(series);
        pageInfo.setList(series);
        map.put("data",pageInfo);
        if(series.size()==0 || series.size()<=length){
            //已经没有更多的系列可供检索
           map.put("stopScroll",true);
        } else{
            map.put("stopScroll",false);
        }
        return ServiceResponse.createBySuccess(map);
    }

    @RequestMapping("intoBannerPreview.do")
    public String intoBannerPreview(HttpServletRequest request,Integer topicBannerId){
        TopicBanner banner = topicBannerService.selectByPrimaryKey(topicBannerId);
        List<TopicBanner> topicBanners=new ArrayList<>();
        topicBanners.add(banner);
        request.setAttribute("topicBanners", topicBanners);
        return "recommendSeries/seriesItems";
    }








}
