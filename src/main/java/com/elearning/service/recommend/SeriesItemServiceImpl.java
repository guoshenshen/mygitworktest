package com.elearning.service.recommend;

import com.elearning.common.Constants;
import com.elearning.common.ResourceType;
import com.elearning.common.ServiceResponse;
import com.elearning.dao.recommend.SeriesItemMapper;
import com.elearning.pojo.pub.Course;
import com.elearning.pojo.pub.EosOperator;
import com.elearning.pojo.pub.EosorgTOrganization;
import com.elearning.pojo.pub.Train;
import com.elearning.pojo.recommend.RecommendSeries;
import com.elearning.pojo.recommend.SeriesItem;
import com.elearning.pojo.systemManage.Tenant;
import com.elearning.service.pub.*;
import com.elearning.service.recommend.ISeriesItemService;
import com.elearning.service.systemManage.ITenantService;
import com.elearning.util.PropertiesUtil;
import com.elearning.util.ToolsUtil;
import com.elearning.vo.CourseVo;
import com.elearning.vo.onlinetraining.BasicTrainForm;
import com.elearning.vo.recommend.SeriesItemDTO;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service("seriesItemService")
public class SeriesItemServiceImpl implements ISeriesItemService {

    @Autowired
    private SeriesItemMapper seriesItemMapper;

    @Autowired
    private ITenantService tenantService;

    @Autowired
    private IEosorgTOrganizationService eosorgTOrganizationService;

    @Autowired
    private IEosorgTEmployeeService eosorgTEmployeeService;

    @Autowired
    private ICourseService courseService;

    @Autowired
    private IDDictionaryService dictionaryService;


    public SeriesItem selectByPrimaryKey(Long id){

        return seriesItemMapper.selectByPrimaryKey(id);
    }

    @Override
    public ServiceResponse getCourseListUnderSameSeries(Long courseId, HttpServletRequest request) {
        EosOperator operator = (EosOperator) request.getSession().getAttribute(Constants.USERINFO_KEY);
        String orgSEQ;
        if(operator==null){
            //当前访问学员尚未登录
            Tenant currentTenant = tenantService.findById(Integer.parseInt(PropertiesUtil.getProperty("tenantId")));
            EosorgTOrganization tenantOrg = eosorgTOrganizationService.getEosorgTOrganizationById(currentTenant.getOrgId());
            orgSEQ=tenantOrg.getOrgSEQ();
        } else{
            EosorgTOrganization org = eosorgTOrganizationService.getEosorgTOrganizationById(eosorgTEmployeeService.findById(operator.getOperatorId()).getOrgID());
            orgSEQ = org.getOrgSEQ();//1.2.2030011975.
        }
        List<String> orgSEQList= EosorgTOrganizationServiceImpl.orgSEQList(orgSEQ);
        List<Course> courses = new ArrayList<>();
        Map<String,Object> condition=new HashMap<>();
        condition.put("itemId", courseId);
        condition.put("itemType", ResourceType.COURSE.getTypeCode());
        List<SeriesItem> seriesItemList = seriesItemMapper.findByMap(condition);
        Set<Integer> seriesIdSet=new HashSet<>();
        if(seriesItemList != null && seriesItemList.size() > 0){
            for(SeriesItem item:seriesItemList){
                seriesIdSet.add(item.getSeriesId());
            }
            Map<String,Object> conditionForCourseSearch=new HashMap<>();
            conditionForCourseSearch.put("seriesIdList", seriesIdSet);
            conditionForCourseSearch.put("orgSEQ", orgSEQList);
            conditionForCourseSearch.put("itemType",ResourceType.COURSE.getTypeCode());
            courses = seriesItemMapper.findCourseByCondition(conditionForCourseSearch);
        }
        List<CourseVo> courseVoList = new ArrayList<>();
        for(Course course :courses){
            courseVoList.add(courseService.getCourseVo(course));
        }
        return ServiceResponse.createBySuccess(courseVoList);
    }

    @Override
    public List<SeriesItem> findByMap(Map<String, Object> seriesitemCondition) {
        return seriesItemMapper.findByMap(seriesitemCondition);
    }

    @Override
    public int delete(Long id) {
        return seriesItemMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<SeriesItemDTO> conjQuery(String conjTable ,Map<String, Object> condition) {
        List<String> orgSEQList = ToolsUtil.orgSEQList(condition.get("orgSEQ").toString());
        condition.put("orgSEQ", orgSEQList);
        List<SeriesItemDTO> seriesItemDTOS = new ArrayList<>();
        if("course".equals(conjTable)){
            seriesItemDTOS = seriesItemMapper.findCourseDtoByCondition(condition);
            for(SeriesItemDTO seriesItemDTO : seriesItemDTOS){
                if(seriesItemDTO.getCategory() != null && seriesItemDTO.getCategory() != 0 ){
                    seriesItemDTO.setCategoryStr(dictionaryService.getDDictionaryMapperByCode(seriesItemDTO.getCategory()+"",
                            "4020")==null ? "" : dictionaryService.getDDictionaryMapperByCode(seriesItemDTO.getCategory()+"",
                            "4020").getName());
                }
            }
        }
        if("train".equals(conjTable)){
            seriesItemDTOS = seriesItemMapper.findTrainDtoByCondition(condition);
        }
        if("series".equals(conjTable)){
            seriesItemDTOS = seriesItemMapper.findSeriesDtoByCondition(condition);
        }





        return seriesItemDTOS;
    }

    @Override
    public int save(SeriesItem seriesItem) {
        return seriesItemMapper.insertSelective(seriesItem);
    }

    @Override
    public int update(SeriesItem seriesItem) {
        return seriesItemMapper.updateByPrimaryKeySelective(seriesItem);
    }

    @Override
    public Map<Integer, Long> getSeriesItemTypeDistribution(Integer seriesId) {
        Map<Integer,Long> map = new HashMap<>();
        List<Map<String, Object>> list  = seriesItemMapper.getSeriesItemTypeDistribution(seriesId);
        if (list != null && !list.isEmpty()) {
            for (Map<String, Object> map1 : list) {
                Integer key = null;
                Long value = null;
                for (Map.Entry<String, Object> entry : map1.entrySet()) {
                    if ("key".equals(entry.getKey())) {
                        key = (Integer) entry.getValue();
                    } else if ("value".equals(entry.getKey())) {
                        value = (Long)entry.getValue();
                    }
                }
                map.put(key, value);
            }
        }
        return map;
    }

    @Override
    public List<Course> findCourseByCondition(Map<String, Object> condition) {
        List<String> orgSEQList = ToolsUtil.orgSEQList(condition.get("orgSEQ").toString());
        condition.put("orgSEQ", orgSEQList);
        return seriesItemMapper.findCourseByCondition(condition);
    }

    @Override
    public List<BasicTrainForm> findTrainByCondition(Map<String, Object> condition) {
        List<String> orgSEQList = ToolsUtil.orgSEQList(condition.get("orgSEQ").toString());
        condition.put("orgSEQ", orgSEQList);
        return seriesItemMapper.findTrainByCondition(condition);
    }

    @Override
    public List<RecommendSeries> findSeriesByCondition(Map<String, Object> condition) {
        List<String> orgSEQList = ToolsUtil.orgSEQList(condition.get("orgSEQ").toString());
        condition.put("orgSEQ", orgSEQList);
        return seriesItemMapper.findSeriesByCondition(condition);
    }


}
