package com.elearning.controller.recommend;

import com.elearning.common.Constants;
import com.elearning.common.ResourceType;
import com.elearning.common.ServiceResponse;
import com.elearning.common.SnowflakeIdWorker;
import com.elearning.interceptor.loginInterceptor.IsCheckUserLogin;
import com.elearning.pojo.TopicClassStudy.TopicBanner;
import com.elearning.pojo.pub.EosOperator;
import com.elearning.pojo.pub.EosorgTOrganization;
import com.elearning.pojo.recommend.RecommendSeries;
import com.elearning.pojo.recommend.SeriesBanner;
import com.elearning.pojo.recommend.SeriesItem;
import com.elearning.service.TopicClassStudy.ITopicBannerService;
import com.elearning.service.pub.IEosorgTEmployeeService;
import com.elearning.service.pub.IEosorgTOrganizationService;
import com.elearning.service.recommend.IRecommendSeriesService;
import com.elearning.service.recommend.ISeriesBannerService;
import com.elearning.service.recommend.ISeriesItemService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 〈<br>
 * 〈〉
 *
 * @author lxx
 * @create 2019/12/2 15:39
 */

@Controller
@RequestMapping("/seriesManage/")
public class SeriesManageController {

    @Autowired
    private IEosorgTOrganizationService eosorgTOrganizationService;

    @Autowired
    private IEosorgTEmployeeService eosorgTEmployeeService;

    @Autowired
    private IRecommendSeriesService recommendSeriesService;

    @Autowired
    private ISeriesBannerService seriesBannerService;

    @Autowired
    private ITopicBannerService topicBannerService;

    @Autowired
    private ISeriesItemService seriesItemService;

    private SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
    /**
     * 进入系列专题管理
     * @param request
     * @return
     */
    @RequestMapping("toSeriesManage.do")
    @IsCheckUserLogin(check = true)
    public String toSeriesManage(HttpServletRequest request){
        EosOperator operator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        Integer orgId = eosorgTEmployeeService.findById(operator.getOperatorId()).getOrgID();
        EosorgTOrganization org = eosorgTOrganizationService.findById(orgId);
        request.setAttribute("org", org);
        return "recommendSeries/seriesManageFrame";
    }

    /**
     * 获取系列专题的分页list
     * @param name
     * @param request
     * @param startIndex
     * @param count
     * @return
     */
    @RequestMapping("getSeriesList.do")
    @ResponseBody
    @IsCheckUserLogin(check = true)
    public ServiceResponse getSeriesList(String name,HttpServletRequest request,
                                         @RequestParam(value = "startIndex",defaultValue = "1")Integer startIndex,
                                         @RequestParam(value = "count",defaultValue = "20")Integer count){
        EosOperator operator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        Integer orgId = eosorgTEmployeeService.findById(operator.getOperatorId()).getOrgID();
        Map<String,Object> conditionMap=new HashMap<>();
        conditionMap.put("name",name);
        conditionMap.put("orgId", orgId);
        PageHelper.startPage(startIndex,count);
        List<RecommendSeries> recommendSeriesList = recommendSeriesService.getSeriesList(conditionMap);
        PageInfo pageInfo = new PageInfo(recommendSeriesList);
        pageInfo.setList(recommendSeriesList);
        return ServiceResponse.createBySuccess(pageInfo);
    }

    /**
     *  通过Id获取series，用于修改信息
     * @param seriesId
     * @return
     */
    @ResponseBody
    @RequestMapping("findSeriesById.do")
    @IsCheckUserLogin(check = true)
    public ServiceResponse findSeriesById(Integer seriesId){
        if(seriesId != null && seriesId != 0 ){
            return ServiceResponse.createBySuccess(recommendSeriesService.selectByPrimaryKey(seriesId));
        }
        return ServiceResponse.createByError();
    }

    /**
     * 获取系列专题关联样式
     * @param seriesId
     * @return
     */
    @ResponseBody
    @RequestMapping("findSeriesBannerByCondition.do")
    public ServiceResponse findSeriesBannerByCondition(Integer seriesId){
        if(seriesId == null || seriesId == 0 ) {
            return ServiceResponse.createByError();
        }
        Map<String,Object> map = new HashMap<>();
        map.put("seriesId",seriesId);
        List<SeriesBanner> bannerList = seriesBannerService.findSeriesBannerByCondition(map);
        return ServiceResponse.createBySuccess(bannerList);

    }

    /**
     * 查询专题样式List
     * @param startIndex
     * @param count
     * @param name
     * @param request
     * @return
     */
    @RequestMapping("getBannerList.do")
    @ResponseBody
    @IsCheckUserLogin(check = true)
    public ServiceResponse getBannerList(@RequestParam(value = "startIndex",defaultValue = "1")Integer startIndex,
                                         @RequestParam(value = "count",defaultValue = "20")Integer count,
                                         String name,HttpServletRequest request){
        EosOperator operator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        Integer orgId = eosorgTEmployeeService.findById(operator.getOperatorId()).getOrgID();
        Map<String,Object> map = new HashMap<>();
        map.put("name",name);
        map.put("orgId", orgId);
        PageHelper.startPage(startIndex,count);
        List<TopicBanner> list  = topicBannerService.findListByMap(map);
        PageInfo pageInfo = new PageInfo(list);
        pageInfo.setList(list);
        return ServiceResponse.createBySuccess(pageInfo);
    }

    /**
     *新增或编辑series
     * @param request
     * @return
     */
    @RequestMapping("addOrEditSeries.do")
    @IsCheckUserLogin(check = true)
    @ResponseBody
    public ServiceResponse addOrEditSeries(HttpServletRequest request){
        int result = 0 ;
        String 	title = request.getParameter("title");
        String seriesIdStr = request.getParameter("seriesId");
        String 	mainPicUrl = request.getParameter("mainPicUrl");
        String 	picUrl = request.getParameter("picUrl");
        String orgIdStr = request.getParameter("orgId");
        String detail = request.getParameter("detail");
        String[] topicBannerId = request.getParameterValues("topicBannerId");//(String[])request.getParameter("topicBannerId");
        EosOperator operator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        Integer orgId = eosorgTEmployeeService.findById(operator.getOperatorId()).getOrgID();
        EosorgTOrganization org = eosorgTOrganizationService.findById(orgId);
        RecommendSeries series = new RecommendSeries();
        if(seriesIdStr == null || "".equals(seriesIdStr)){
            series.setTitle(title);
            if(detail!=null){
                series.setDetail(detail);
            }
            series.setOutstandingStandard(0);
            series.setIsPublish(false);
            series.setSponsorInfo(org.getOrgName());
            series.setOperatorId(operator.getOperatorId());
            series.setMainPicUrl(mainPicUrl);
            series.setPicUrl(picUrl);
            series.setOrgId(orgId);
            series.setTenantId(operator.getTenantId());
            series.setOpenOrgSEQ("-1");
            series.setCreateTime(new Date());
            result = recommendSeriesService.add(series);

        }else{
            Integer seriesId = Integer.valueOf(seriesIdStr);
            series = recommendSeriesService.selectByPrimaryKey(seriesId);
            series.setTitle(title);
            series.setDetail(detail);
            series.setOrgId(Integer.valueOf(orgIdStr));
            series.setMainPicUrl(mainPicUrl);
            series.setPicUrl(picUrl);
            result = recommendSeriesService.update(series);
            Map<String, Object> conditionMapDelete = new HashMap<String, Object>();
            conditionMapDelete.put("seriesId", seriesIdStr);
            List<SeriesBanner> deletelist = seriesBannerService.findSeriesBannerByCondition(conditionMapDelete);
            for(SeriesBanner seriesBanner :deletelist){
                seriesBannerService.delete(seriesBanner);
            }
        }
        if(topicBannerId != null){
            List<SeriesBanner> newTopicBannerlist = new ArrayList<SeriesBanner>();
            for(Object topicId:topicBannerId){
                SeriesBanner banner = new SeriesBanner();
                banner.setBannerId(Integer.parseInt(topicId.toString()));
                banner.setSeriesId(series.getId());
                banner.setType((byte) 4);
                banner.setOrderweight((byte) 0);
                Map<String, Object> conditionMap = new HashMap<String, Object>();
                conditionMap.put("seriesId", banner.getSeriesId());
                conditionMap.put("bannerId", banner.getBannerId());
                List<SeriesBanner> list = seriesBannerService.findSeriesBannerByCondition(conditionMap);
                if(list == null || list.size()==0){
                    newTopicBannerlist.add(banner);
                }
            }
            for(SeriesBanner seriesBanner:newTopicBannerlist){
                seriesBannerService.insert(seriesBanner);
            }
        }

        if(result == 0 ){
            return ServiceResponse.createByError();
        }
        return ServiceResponse.createBySuccess();
    }

    /**
     * 发布系列专题
     * @param seriesId
     * @param openScope
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("publishSeries.do")
    @IsCheckUserLogin(check = true)
    public ServiceResponse publishSeries(Integer seriesId , Integer openScope ,HttpServletRequest request){
        Boolean result = true;
        if(seriesId == null || openScope == null){
            return ServiceResponse.createByError();
        }
        EosOperator operator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        Integer orgId = eosorgTEmployeeService.findById(operator.getOperatorId()).getOrgID();
        RecommendSeries series = recommendSeriesService.selectByPrimaryKey(seriesId);
        series.setOpenScope((short)openScope.intValue());
        if(openScope==2201) {//登录2201是不公开
            series.setIsPublish(false);
            series.setPublishTime(null);
            series.setOpenOrgSEQ("-1");
            result = false;
        }else{
            series.setIsPublish(true);
            series.setOpenOrgSEQ(eosorgTOrganizationService.getOpenOrgSEQ(orgId, (short)openScope.intValue()));
            series.setPublishTime(new Date());
            series.setUrl("/recommendSeries/intoSeriesItemFrame.do?seriesId="+seriesId);
        }
        int status = recommendSeriesService.update(series);
        if(status > 0 ){
            return ServiceResponse.createBySuccess(result);
        }
        return ServiceResponse.createByError();
    }

    /**
     *删除系列专题
     * @param seriesId
     * @return
     */
    @ResponseBody
    @RequestMapping("deleteSeries.do")
    @IsCheckUserLogin(check = true)
    public ServiceResponse deleteSeries(Integer seriesId,HttpServletRequest request){
        if(seriesId == null){
            return ServiceResponse.createByError();
        }
        int result = recommendSeriesService.delete(seriesId);
        //删除与样式表series_banner关联信息
        Map<String, Object> seriesbannerCondition = new HashMap<>();
        seriesbannerCondition.put("seriesId", seriesId);
        List<SeriesBanner> seriesbannerList = seriesBannerService.findSeriesBannerByCondition(seriesbannerCondition);
        for(SeriesBanner seriesBanner : seriesbannerList){
            seriesBannerService.delete(seriesBanner);
        }
        //删除与资源表seriesitem关联信息
        Map<String, Object> seriesitemCondition = new HashMap<>();
        seriesitemCondition.put("seriesId", seriesId);
        List<SeriesItem> seriesitemList = seriesItemService.findByMap(seriesitemCondition);
        for(SeriesItem seriesItem :seriesitemList){
            seriesItemService.delete(seriesItem.getId());
        }
        //删除专题作为被关联的子专题在关联表中的记录
        Map<String, Object> subseriesitemCondition = new HashMap<String, Object>();
        subseriesitemCondition.put("itemId", seriesId);
        subseriesitemCondition.put("itemType", ResourceType.TOPIC.getTypeCode());
        List<SeriesItem> subseriesitemList = seriesItemService.findByMap(subseriesitemCondition);
        for(SeriesItem seriesItem : subseriesitemList){
            seriesItemService.delete(seriesItem.getId());
        }
        if(result > 0 ){
            return ServiceResponse.createBySuccess();
        }
        return ServiceResponse.createByError();
    }

    /**
     * 新增或编辑Topicbanner
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("addorEditTopicbanner.do")
    @IsCheckUserLogin(check = true)
    public ServiceResponse addorEditTopicbanner(HttpServletRequest request){
        int result = 0 ;
        String 	bannerId = request.getParameter("bannerId");
        String 	title = request.getParameter("title");
        String description = request.getParameter("banner-description");
        String[] templateClass = request.getParameterValues("templateClass");
        String bannerClass = request.getParameter("bannerClass");
        String bannerPicUrl = request.getParameter("bannerPicUrl");
        StringBuffer templateString = new StringBuffer();
        // 将已选择的CheckBox专题样式，使用“，”拼接
        if(templateClass!=null&&!"".equals(templateClass)){
            for (String string : templateClass) {
                templateString.append(string+",");
            }
        }
        TopicBanner banner = new TopicBanner();
        if(title!=null&&!"".equals(title)){
            banner.setTitle(title);
        }
        if(description!=null&&!"".equals(description)){
            banner.setDescription(description);
        }
        if(bannerClass!=null&&!"".equals(bannerClass)){
            banner.setBannerClass(bannerClass);
        }
        if(templateString!=null&&!"".equals(templateString)){
            banner.setTemplateClass(templateString.toString());
        }
        if(bannerPicUrl!=null&&!"".equals(bannerPicUrl)){
            banner.setBannerPicUrl(bannerPicUrl);
        }
        EosOperator operator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        Integer orgId = eosorgTEmployeeService.findById(operator.getOperatorId()).getOrgID();
        banner.setOrgId((long) orgId);
        if(bannerId!=null&&!"".equals(bannerId)){
            banner.setId(Integer.valueOf(bannerId));
            result = this.topicBannerService.update(banner);
        }else{
            result = this.topicBannerService.save(banner);
        }
        if(result > 0 ){
            return ServiceResponse.createBySuccess();
        }
        return ServiceResponse.createByError();
    }

    /**
     * 删除Topicbanner
     * @param bannerId
     * @return
     */
    @ResponseBody
    @RequestMapping("deleteTopicBanner.do")
    @IsCheckUserLogin(check = true)
    public ServiceResponse deleteTopicBanner(Integer bannerId){
        int result = 0;
        Map<String,Object> map = new HashMap<>();
        map.put("bannerId", bannerId);
        List<RecommendSeries> seriesList = new ArrayList<RecommendSeries>();
        List<SeriesBanner> list = this.seriesBannerService.findSeriesBannerByCondition(map);
        if(list != null && list.size() > 0){
            for (SeriesBanner seriesBanner : list) {
                RecommendSeries series = this.recommendSeriesService.selectByPrimaryKey(seriesBanner.getSeriesId());
                seriesList.add(series);
            }
        }else{
            result = this.topicBannerService.delete(bannerId);
        }
        if(seriesList.size() > 0 ){
            return ServiceResponse.createByError(seriesList);
        }
        if(result > 0 ){
            return ServiceResponse.createBySuccess();
        }
        return ServiceResponse.createByError();
    }


    @RequestMapping("toSeriesResourceBinding.do")
    public String toSeriesResourceBinding(Integer seriesId,HttpServletRequest request){
        request.setAttribute("seriesId", seriesId);
        return "recommendSeries/seriesResourceBinding";
    }

    @RequestMapping("findBannerById.do")
    @ResponseBody
    @IsCheckUserLogin(check = true)
    public ServiceResponse findBannerById(Integer bannerId){
        TopicBanner banner = topicBannerService.selectByPrimaryKey(bannerId);
        return ServiceResponse.createBySuccess(banner);
    }

}
