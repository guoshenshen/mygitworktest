package com.elearning.service.courseStudy;

import com.elearning.common.ResourceType;
import com.elearning.common.ServiceResponse;
import com.elearning.dao.courseStudy.UserNeedLearnCourseMapper;
import com.elearning.dao.courseStudy.UserTrainMapper;
import com.elearning.dao.coursemanage.CourseTypeMapper;
import com.elearning.dao.onlinetraining.OntOnlineTrainCourseUserArrangeMapper;
import com.elearning.dao.pub.CourseMapper;
import com.elearning.dao.pub.DDictionaryMapper;
import com.elearning.dao.resourceManage.RsmRcmBookDiscussMapper;
import com.elearning.dao.systemManage.TenantMapper;
import com.elearning.pojo.courseStudy.*;
import com.elearning.pojo.coursemanage.CourseType;
import com.elearning.pojo.onlinetraining.OntOnlineTrainCourseUserArrange;
import com.elearning.pojo.pub.Course;
import com.elearning.pojo.pub.EosOperator;
import com.elearning.pojo.pub.EosorgTOrganization;
import com.elearning.pojo.systemManage.Tenant;
import com.elearning.service.pub.CourseServiceImpl;
import com.elearning.service.pub.EosorgTOrganizationServiceImpl;
import com.elearning.service.pub.ICourseService;
import com.elearning.service.pub.IEosorgTOrganizationService;
import com.elearning.util.DateTimeUtil;
import com.elearning.util.DoubleUtil;
import com.elearning.util.ToolsUtil;
import com.elearning.vo.CourseVo;
import com.elearning.vo.EosOrgTOrganizationVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.*;

/**
 * 〈<br>
 * 〈〉
 *
 * @author lxx
 * @create 2019/7/15 10:10
 */
@Service("onlineStudyService")
public class OnlineStudyServiceImpl implements IOnlineStudyService{

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private CourseTypeMapper courseTypeMapper;

    @Autowired
    private ICourseService courseService;

    @Autowired
    private TenantMapper tenantMapper;

    @Autowired
    private RsmRcmBookDiscussMapper rsmRcmBookDiscussMapper;

    @Autowired
    private IEosorgTOrganizationService eosorgTOrganizationService;

    @Autowired
    private OntOnlineTrainCourseUserArrangeMapper ontOnlineTrainCourseUserArrangeMapper;

    @Autowired
    private UserNeedLearnCourseMapper userNeedLearnCourseMapper;

    @Autowired
    private UserTrainMapper userTrainMapper;

    @Override
    public ServiceResponse getDomainSystem(Integer domainId) {
        List<CourseType> courseTypeList = courseTypeMapper.listCourseTypeById(domainId);
        Map<Integer,List<CourseType>> integerListMap = new HashMap<>();
        List<CourseType> currentList=null;
        for(CourseType coursetype:courseTypeList){
            if(!integerListMap.containsKey(coursetype.getParentID())){
                currentList=new ArrayList<>();
                integerListMap.put(coursetype.getParentID(), currentList);
            }
            currentList.add(coursetype);
        }
        Set<Integer> courseTypeIdList=integerListMap.keySet();
        List<Map<String,Object>> mapList = new ArrayList<>();
        for (Integer courseTypeId:courseTypeIdList) {
            Map<String,Object> map = new HashMap<>();
            map.put("domainId",courseTypeId);
            map.put("subdomain",integerListMap.get(courseTypeId));
            mapList.add(map);
        }
        return ServiceResponse.createBySuccess(mapList);
    }

    @Override
    public ServiceResponse getCourseByCondition(Map<String, Object> map) {
        PageHelper.startPage((int) map.get("num"),(int)map.get("size"));
        map.put("resourceType", ResourceType.COURSE.getTypeCode());
        List<String> orgSEQList= EosorgTOrganizationServiceImpl.orgSEQList(map.get("orgSEQ")+"");
        map.put("orgSEQ", orgSEQList);
        List<Course> courseList = courseMapper.listCourseByMap(map);
        PageInfo pageInfo = new PageInfo(courseList);
        List<CourseVo> courseVoList = new ArrayList<>();
        for (Course course: courseList ) {
            // c.Course_id as courseId, c.Course_name as courseName , c.PictureURL as pictureURL , c.creator as creator ,c.score as score,c.tenantId as tenantId
            if(course.getPictureURL() == null){
                course.setPictureURL("");
            }
            if(course.getCreator() == null){
                course.setCreator("");
            }
            if(course.getTenantId() == null){
                course.setTeacherId(0);
            }
            CourseVo courseVo = courseService.getCourseVo(course);
            courseVoList.add(courseVo);
        }
        pageInfo.setList(courseVoList);
        return ServiceResponse.createBySuccess(pageInfo);
    }

    @Override
    public ServiceResponse loadCourseRelatedOrg(Map<String, Object> conditions) {
        List<EosOrgTOrganizationVo> orgList = new ArrayList<>();
        conditions.put("resourceType", ResourceType.COURSE.getTypeCode());
        List<String> orgSEQList= EosorgTOrganizationServiceImpl.orgSEQList(conditions.get("orgSEQ")+"");
        conditions.put("orgSEQ", orgSEQList);
        List<Tenant> tenantList = tenantMapper.findByCondition(conditions);
        if(tenantList!=null){
            List<Integer> orgIdList=new ArrayList<>();
            for(Tenant tenant:tenantList){
                orgIdList.add(tenant.getOrgId());
            }
           orgList = eosorgTOrganizationService.findOrgInfoById(orgIdList);
        }
        return ServiceResponse.createBySuccess(orgList);
    }

    @Override
    @Transactional(rollbackFor = { Exception.class })
    public ServiceResponse selectSingleCourse(Long courseId, EosOperator eosOperator) throws Exception {

        int year = DateTimeUtil.getCurrentYear();
        UserNeedLearnCourseKey userNeedLearnCourseKey = new UserNeedLearnCourseKey(eosOperator.getOperatorId(),
                0,year+"",0,courseId);
        UserNeedLearnCourse userNeedLearnCourse = userNeedLearnCourseMapper.selectByPrimaryKey(userNeedLearnCourseKey);
        if(userNeedLearnCourse != null ){
            return ServiceResponse.createByErrorMessage("您已选学");
        }
        OntOnlineTrainCourseUserArrange courseUserArrange = new OntOnlineTrainCourseUserArrange();
        courseUserArrange.setSerialNO("1");
        courseUserArrange.setTrainID(-1);
        courseUserArrange.setArrangeName("select Courses by " + eosOperator.getUserId());
        courseUserArrange.setYear(year+"");
        Course course = courseService.getCourse(courseId);
        String courseName = course.getCourseName();
        Double classHour = course.getClassHour();

        userNeedLearnCourse = new UserNeedLearnCourse();
        userNeedLearnCourse.setOperatorId(eosOperator.getOperatorId());
        userNeedLearnCourse.setTrainId(0);
        userNeedLearnCourse.setYear(year+"");
        userNeedLearnCourse.setSectionId(0);
        userNeedLearnCourse.setCourseId(courseId);
        userNeedLearnCourse.setClassHour(classHour);
        userNeedLearnCourse.setTrainName("选修课程培训");
        userNeedLearnCourse.setIsStationTrain((byte) 0);
        userNeedLearnCourse.setSectionName("");
        userNeedLearnCourse.setCourseName(courseName);
        userNeedLearnCourse.setCourseType("");
        int result = userNeedLearnCourseMapper.insertSelective(userNeedLearnCourse);
        UserTrainKey userTrainKey = new UserTrainKey(eosOperator.getOperatorId(),-1,year+"");
        UserTrain userTrain = userTrainMapper.selectByPrimaryKey(userTrainKey);
        if(userTrain == null){
            userTrain.setTrainName("选修课程培训");
            userTrain.setTrainWay(0);
            userTrain.setLocation("");
            userTrain.setAttendantCount(0);
            userTrain.setStartTime(DateTimeUtil.stringToDate(year+"-01-01"));
            userTrain.setEndTime(DateTimeUtil.stringToDate(year+"-12-31"));
            userTrain.setIsStationTrain((byte) 0);
            userTrain.setSponsorName("");
            userTrainMapper.insertSelective(userTrain);
        }

        int selectedTimes=0;
        if(course.getSelectedTimes()!=null){
            selectedTimes=course.getSelectedTimes();
        }
        course.setSelectedTimes(selectedTimes+1);
        //修改选学人数
        courseMapper.updateByPrimaryKeySelective(course);

        if(result > 0 ){
            return ServiceResponse.createBySuccessMessage("选学成功");
        }
        return ServiceResponse.createByErrorMessage("选学失败");
    }


}
