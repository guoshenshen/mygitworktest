package com.elearning.controller.recommend;

import com.alibaba.fastjson.JSONArray;
import com.elearning.common.Constants;
import com.elearning.common.ResourceType;
import com.elearning.common.ServiceResponse;
import com.elearning.common.SnowflakeIdWorker;
import com.elearning.interceptor.loginInterceptor.IsCheckUserLogin;
import com.elearning.pojo.pub.EosOperator;
import com.elearning.pojo.pub.EosorgTOrganization;
import com.elearning.pojo.recommend.RecommendSeries;
import com.elearning.pojo.recommend.SeriesItem;
import com.elearning.pojo.systemManage.Tenant;
import com.elearning.service.pub.ICourseService;
import com.elearning.service.pub.IEosorgTEmployeeService;
import com.elearning.service.pub.IEosorgTOrganizationService;
import com.elearning.service.recommend.IRecommendSeriesService;
import com.elearning.service.recommend.ISeriesItemService;
import com.elearning.service.systemManage.ITenantService;
import com.elearning.util.PropertiesUtil;
import com.elearning.vo.recommend.SeriesItemDTO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 〈<br>
 * 〈〉
 *
 * @author lxx
 * @create 2019/12/4 11:26
 */
@Controller
@RequestMapping("/seriesResourceBinding/")
public class seriesResourceBindingController {

    private SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);

    @Autowired
    private ITenantService tenantService;

    @Autowired
    private IEosorgTOrganizationService eosorgTOrganizationService;

    @Autowired
    private IEosorgTEmployeeService eosorgTEmployeeService;

    @Autowired
    private ISeriesItemService seriesItemService;

    @Autowired
    private IRecommendSeriesService recommendSeriesService;

    @RequestMapping("getList.do")
    @ResponseBody
    public ServiceResponse getList(HttpServletRequest request,
                                   @RequestParam(value = "startIndex",defaultValue = "1")Integer startIndex,
                                   @RequestParam(value = "count",defaultValue = "20")Integer count){


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
        Integer seriesId=Integer.parseInt(request.getParameter("seriesId"));
        //0 课件资源 1 培训项目 4系列专题
        Integer itemType=Integer.parseInt(request.getParameter("itemType"));
        String name = request.getParameter("name");
        Map<String,Object> condition=new HashMap<String,Object>();
        condition.put("orgSEQ", orgSEQ);
        condition.put("seriesId", seriesId);
        condition.put("itemType", itemType);
        condition.put("name", name);
        if(itemType.equals(ResourceType.COURSE.getTypeCode())){
            PageHelper.startPage(startIndex,count);
            List<SeriesItemDTO> courseList = seriesItemService.conjQuery("course",condition);
            PageInfo pageInfo = new PageInfo(courseList);
            pageInfo.setList(courseList);
            return ServiceResponse.createBySuccess(pageInfo);
        }
        if(itemType.equals(ResourceType.TRAIN.getTypeCode())){
            PageHelper.startPage(startIndex,count);
            List<SeriesItemDTO> trainList = seriesItemService.conjQuery("train",condition);
            PageInfo pageInfo = new PageInfo(trainList);
            pageInfo.setList(trainList);
            return ServiceResponse.createBySuccess(pageInfo);
        }
        if(itemType.equals(ResourceType.TOPIC.getTypeCode())){
            PageHelper.startPage(startIndex,count);
            List<SeriesItemDTO> subSeriesList = seriesItemService.conjQuery("series",condition);
            PageInfo pageInfo = new PageInfo(subSeriesList);
            pageInfo.setList(subSeriesList);
            return ServiceResponse.createBySuccess(pageInfo);
        }
        return ServiceResponse.createByError();
    }



    @RequestMapping("addCourseForSeries.do")
    @ResponseBody
    @IsCheckUserLogin(check = true)
    public ServiceResponse addCourseForSeries(HttpServletRequest request){
        int result = 0 ;
        String seriesId = request.getParameter("seriesId");
        String courseIdList = request.getParameter("courseIdList");
        courseIdList = courseIdList.replaceAll("&quot;","\"");
        JSONArray courseList = JSONArray.parseArray(courseIdList);
        List<SeriesItem> saveList=new ArrayList<>();
        for(Object o:courseList){

            SeriesItem si = new SeriesItem();
            si.setSeriesId(Integer.parseInt(seriesId));
            si.setItemId(Long.parseLong(o.toString()));
            si.setItemType(ResourceType.COURSE.getTypeCode());
            si.setOrderWeight(0L);
            si.setId(idWorker.nextId());
            Map<String,Object> map = new HashMap<>();
            map.put("seriesId",si.getSeriesId());
            map.put("itemType",si.getItemType());
            map.put("itemId",si.getItemId());
            List<SeriesItem> rlist= seriesItemService.findByMap(map);
            if(rlist==null || (rlist!=null&&rlist.size()==0)){
                saveList.add(si);
            }
        }
        if(saveList.size()>0){
            for(SeriesItem seriesItem : saveList){
                result = seriesItemService.save(seriesItem);
            }
        }else{
            return ServiceResponse.createBySuccessMessage(2,"该课程已存在");
        }
        if(result > 0 ){
            return ServiceResponse.createBySuccess();
        }
        return ServiceResponse.createByError();
    }


    @ResponseBody
    @RequestMapping("updateItem.do")
    @IsCheckUserLogin(check = true)
    public ServiceResponse updateItem(HttpServletRequest request,Long seriesItemId ,String operate, Long value){
        int result = 0 ;
        SeriesItem seriesItem= seriesItemService.selectByPrimaryKey(seriesItemId);
        Long weight = 0L;
        if(seriesItem.getOrderWeight()!=null){
            weight = seriesItem.getOrderWeight();
        }
        if("add".equals(operate)){
            seriesItem.setOrderWeight(weight+1);
        }else if("sub".equals(operate)){
            seriesItem.setOrderWeight(weight-1);
        }else if("toZero".equals(operate)){
            seriesItem.setOrderWeight(0L);
        }else if("set".equals(operate)){
            seriesItem.setOrderWeight(value);
        }
        if(seriesItem.getOrderWeight()<0){
            return ServiceResponse.createByErrorMessage("排序权值不得低于0！");
        }else{
            result = seriesItemService.update(seriesItem);
        }
        if(result > 0 ){
            return ServiceResponse.createBySuccessMessage("设置排序成功！");
        }
        return ServiceResponse.createByErrorMessage("设置排序异常");
    }

    @ResponseBody
    @RequestMapping("removeItem.do")
    @IsCheckUserLogin(check = true)
    public ServiceResponse removeItem(HttpServletRequest request ,Integer seriesId,Integer key ){
        int result = 0;
        if(request.getParameterValues("itemId") != null) {
            String[] item_ids = request.getParameterValues("itemId");
            for(int i=0;i<item_ids.length;i++){
                Map<String,Object> map = new HashMap<>();
                map.put("seriesId",seriesId);
                map.put("itemType",key);
                map.put("itemId",item_ids[i]);
                List<SeriesItem> rlist = seriesItemService.findByMap(map);
                for(SeriesItem seriesItem : rlist){
                    result = seriesItemService.delete(seriesItem.getId());
                }
            }
        }
        if(result > 0 ){
            return ServiceResponse.createBySuccess();
        }
        return ServiceResponse.createByError();
    }

    @RequestMapping("addTrainForSeries.do")
    @ResponseBody
    @IsCheckUserLogin(check = true)
    public ServiceResponse addTrainForSeries(HttpServletRequest request,Integer seriesId){
        int result = 0 ;
        String trainIdList = request.getParameter("trainIdList");
        trainIdList = trainIdList.replaceAll("&quot;","\"");
        JSONArray courseList = JSONArray.parseArray(trainIdList);
        List<SeriesItem> saveList=new ArrayList<SeriesItem>();
        for(Object o:courseList){
            SeriesItem si = new SeriesItem();
            si.setId(idWorker.nextId());
            si.setSeriesId(seriesId);
            si.setItemId(Long.parseLong(o.toString()));
            si.setItemType(ResourceType.TRAIN.getTypeCode());
            si.setOrderWeight(0L);
            Map<String,Object> map = new HashMap<>();
            map.put("seriesId",seriesId);
            map.put("itemType",ResourceType.TRAIN.getTypeCode());
            map.put("itemId",Long.parseLong(o.toString()));
            List<SeriesItem> rlist = seriesItemService.findByMap(map);
            if(rlist==null || (rlist!=null&&rlist.size()==0)){
                saveList.add(si);
            }
        }
        if(saveList.size()>0){
           for(SeriesItem seriesItem : saveList){
                result = seriesItemService.save(seriesItem);
           }
        }else{
            return ServiceResponse.createBySuccessMessage(2,"该培训已存在");
        }
        if(result > 0 ){
            return ServiceResponse.createBySuccess();
        }
        return ServiceResponse.createByError();
    }


    @ResponseBody
    @RequestMapping("ListSubSeriesBySubSeriesName.do")
    public ServiceResponse ListSubSeriesBySubSeriesName(HttpServletRequest request,String name,Integer seriesId,
                                                        @RequestParam(value = "startIndex",defaultValue = "1")Integer startIndex,
                                                        @RequestParam(value = "count",defaultValue = "20")Integer count){
        EosOperator operator = (EosOperator) request.getSession().getAttribute(Constants.USERINFO_KEY);
        Map<String,Object> conditionMap=new HashMap<>();
        String orgSEQ=null;
        if(operator==null){
            //当前访问学员尚未登录
            Tenant currentTenant = tenantService.findById(Integer.parseInt(PropertiesUtil.getProperty("tenantId")));
            EosorgTOrganization tenantOrg = eosorgTOrganizationService.findById(currentTenant.getOrgId());
            orgSEQ=tenantOrg.getOrgSEQ();
        } else{
            EosorgTOrganization org = eosorgTOrganizationService.findById(eosorgTEmployeeService.findById(operator.getOperatorId()).getOrgID());
            orgSEQ = org.getOrgSEQ();
        }
        conditionMap.put("orgSEQ",orgSEQ);
        conditionMap.put("name",name);
        conditionMap.put("seriesId",seriesId);
        PageHelper.startPage(startIndex,count);
        List<RecommendSeries> series=recommendSeriesService.findVisibleSeriesByCondition(conditionMap);
        PageInfo pageInfo = new PageInfo(series);
        pageInfo.setList(series);
        return ServiceResponse.createBySuccess(pageInfo);
    }


    @ResponseBody
    @RequestMapping("addSubSeriesForSeries.do")
    @IsCheckUserLogin(check = true)
    public ServiceResponse addSubSeriesForSeries(Integer seriesId,HttpServletRequest request){
        int result = 0;
        String subSeriesIdList = request.getParameter("subSeriesIdList");
        subSeriesIdList = subSeriesIdList.replaceAll("&quot;","\"");
        JSONArray subSeriesList = JSONArray.parseArray(subSeriesIdList);
        List<SeriesItem> saveList=new ArrayList<SeriesItem>();
        for(Object o:subSeriesList){
            SeriesItem si = new SeriesItem();
            si.setId(idWorker.nextId());
            si.setSeriesId(seriesId);
            si.setItemId(Long.parseLong(o.toString()));
            si.setItemType(ResourceType.TOPIC.getTypeCode());
            si.setOrderWeight(0L);
            Map<String,Object> map = new HashMap<>();
            map.put("seriesId",seriesId);
            map.put("itemType",ResourceType.TOPIC.getTypeCode());
            map.put("itemId",Long.parseLong(o.toString()));
            List<SeriesItem> rlist = seriesItemService.findByMap(map);
            if(rlist==null || (rlist!=null&&rlist.size()==0)){
                saveList.add(si);
            }
        }
        if(saveList.size()>0){
            for(SeriesItem seriesItem : saveList){
                result = seriesItemService.save(seriesItem);
            }
        }else{
            return ServiceResponse.createBySuccessMessage(2,"该子专题已存在");
        }
        if(result > 0 ){
            return ServiceResponse.createBySuccess();
        }
        return ServiceResponse.createByError();
    }



}
