package com.elearning.service.pub;

import com.elearning.common.*;
import com.elearning.dao.courseStudy.UcsEmployeeCourseMapper;
import com.elearning.dao.coursemanage.CourseCoursetypeMapper;
import com.elearning.dao.coursemanage.CourseKeytagMapper;
import com.elearning.dao.coursemanage.CourseTypeMapper;
import com.elearning.dao.coursemanage.CourseVideoConvertMapper;
import com.elearning.dao.courseshop.CourseCollectMapper;
import com.elearning.dao.pub.*;
import com.elearning.dao.resourceManage.RsmRcmBookDiscussMapper;
import com.elearning.dao.systemManage.TenantMapper;
import com.elearning.pojo.courseStudy.Chapter;
import com.elearning.pojo.courseStudy.UcsEmployeeCourse;
import com.elearning.pojo.courseStudy.UscUsertliddayStudyInfo;
import com.elearning.pojo.coursemanage.*;
import com.elearning.pojo.courseshop.CourseCollect;
import com.elearning.pojo.mixtraining.MtMixTrainScheduleItemInfo;
import com.elearning.pojo.pub.*;
import com.elearning.pojo.resourceManage.RsmRcmBookDiscuss;
import com.elearning.pojo.systemManage.Tenant;
import com.elearning.service.courseStudy.IChapterService;
import com.elearning.service.courseStudy.IUcsEmployeeCourseService;
import com.elearning.service.courseStudy.IUscUsertliddayStudyInfoService;
import com.elearning.service.coursemanage.ICourseTypeService;
import com.elearning.service.coursemanage.ICourseVideoConvertService;
import com.elearning.service.coursemanage.IUscLectureFileService;
import com.elearning.service.mixtraining.IMtMixTrainScheduleItemInfoService;
import com.elearning.service.resourceManage.IRsmRcmbookdiscussService;
import com.elearning.util.DateTimeUtil;
import com.elearning.util.DoubleUtil;
import com.elearning.util.PropertiesUtil;
import com.elearning.util.ToolsUtil;
import com.elearning.vo.BasicUserVo;
import com.elearning.vo.CourseForm;
import com.elearning.vo.CourseFormAll;
import com.elearning.vo.CourseVo;
import cn.kepu.elearningfs.webservice.ICommonToolsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

@Service("courseService")
public class CourseServiceImpl implements ICourseService{


    private SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private DDictionaryMapper dDictionaryMapper;

    @Autowired
    private IDDictionaryService dictionaryService;

    @Autowired
    private CourseTypeMapper courseTypeMapper;

    @Autowired
    private ICourseTypeService courseTypeService;

    @Autowired
    private TenantMapper tenantMapper;

    @Autowired
    private RsmRcmBookDiscussMapper rsmRcmBookDiscussMapper;

    @Autowired
    private EosorgTOrganizationMapper eosorgTOrganizationMapper;

    @Autowired
    private UcsEmployeeCourseMapper ucsEmployeeCourseMapper;

    @Autowired
    private IUcsEmployeeCourseService ucsEmployeeCourseService;

    @Autowired
    private EosorgTEmployeeMapper eosorgTEmployeeMapper;

    @Autowired
    private EosOperatorMapper eosOperatorMapper;

    @Autowired
    private CourseVideoConvertMapper courseVideoConvertMapper;

    @Autowired
    private IEosorgTOrganizationService eosorgTOrganizationService;

    @Autowired
    private CourseCollectMapper courseCollectMapper;

    @Autowired
    private CourseCoursetypeMapper courseCoursetypeMapper;

    @Autowired
    private CourseKeytagMapper courseKeytagMapper;

    @Autowired
    private TrainMapper trainMapper;

    @Autowired
    private ResourceCourseTypeMapper resourceCourseTypeMapper;

    @Autowired
    private IMtMixTrainScheduleItemInfoService mtMixTrainScheduleItemInfoService;

    @Autowired
    private IUscUsertliddayStudyInfoService uscUsertliddayStudyInfoService;

    @Autowired
    private IUscLectureFileService uscLectureFileService;

    @Autowired
    private IChapterService chapterService;

    @Autowired
    private IRsmRcmbookdiscussService rsmRcmbookdiscussService;

    @Autowired
    private ICourseVideoConvertService courseVideoConvertService;

    /**
     * 调用webservice 忽略报红
     */
    @Autowired
    private ICommonToolsService commonToolsService;

    @Override
    public Course getCourse(Long courseId) {
        return courseMapper.selectByPrimaryKey(courseId);
    }

    /**
     * 将course转换为courseVo
     *
     * @param course
     * @return
     */
    @Override
    public  CourseVo getCourseVo(Course course){
        CourseVo courseVo = new CourseVo();
        courseVo.setCourseId(course.getCourseId());
        courseVo.setCategory(course.getCategory());
        if(course.getCategory() != null){
            courseVo.setCategoryStr(dDictionaryMapper.getDDictionaryMapperByCode(course.getCategory()+"",
                    "4020")==null ? "" : dDictionaryMapper.getDDictionaryMapperByCode(course.getCategory()+"",
                    "4020").getName());
        }
        courseVo.setClassfication(course.getClassfication());
        courseVo.setClassHour(course.getClassHour());
        if(course.getClassfication() != null ){
            courseVo.setClassificationStr(dDictionaryMapper.getDDictionaryMapperByCode(course.getClassfication()+"",
                    "4000").getName());
        }
        courseVo.setCourseName(course.getCourseName());
        courseVo.setCourseNo(course.getCourseNo());
        courseVo.setCreateDate(course.getCreateDate());
        //courseVo.setCreateDateStr(course.getCreateDate().toString());
        courseVo.setCreator(course.getCreator());
        courseVo.setExpertAreaId(course.getExpertAreaId());
        if(course.getExpertAreaId() != null ){
            courseVo.setExpertAreaStr(courseTypeMapper.selectByPrimaryKey(
                    Integer.parseInt(course.getExpertAreaId())).getCourseTypeName());
        }
        courseVo.setFundingOrgName(course.getFundingOrgName());
        //courseVo.setItemId();
        courseVo.setKeyWords(course.getKeyWords());
        courseVo.setMaker(course.getMaker());
        //courseVo.setOrderWeight();
        courseVo.setPictureUrl(course.getPictureURL());
        courseVo.setProduceOrgName(course.getProduceOrgName());

        Map<String,Object> scoreCondition=new HashMap<>();
        scoreCondition.put("bookId", course.getCourseId());
        scoreCondition.put("isReply", "0");
        scoreCondition.put("discussType","1703");
        Double score = rsmRcmBookDiscussMapper.getCourseScore(scoreCondition);
        courseVo.setScore( score == null ? 0.00 : DoubleUtil.getTwoDigitalDoubleData(score) );

        courseVo.setTenantId(course.getTenantId());
        if(course.getTenantId() != null ){
            courseVo.setTenantName(tenantMapper.selectByPrimaryKey(course.getTenantId()).getTenantName());
        }
        courseVo.setOrgId(course.getOrgId());
        if(course.getOrgId() != null){
            courseVo.setOrgName(eosorgTOrganizationMapper.selectByPrimaryKey(course.getOrgId()).getOrgName());
        }
        courseVo.setIsCoursePackage(course.getIsCoursePackage());
        courseVo.setIsFirstLevelDirectory(course.getIsFirstLevelDirectory());
        courseVo.setSliceType(course.getSliceType());
        courseVo.setSelectedTimes(course.getSelectedTimes());
        courseVo.setContent(course.getContent());
        courseVo.setEnterUrl(course.getEnterUrl());
        courseVo.setShareDate(course.getShareDate());
        courseVo.setShareDateStr(DateTimeUtil.dateToStr(course.getShareDate(),"yyyy-MM-dd"));
        courseVo.setCreateDateStr(DateTimeUtil.dateToStr(course.getCreateDate(),"yyyy-MM-dd"));
        courseVo.setMainContent(course.getContent());
        courseVo.setTotalLearnedUser(course.getSelectedTimes() == null ? 0 : course.getSelectedTimes());
        courseVo.setMobilePlayAddress(course.getMobilePlayAddress());
        return courseVo;
    }

    /**
     * 热门课件
     * @param orgSEQ
     * @return
     */
    @Override
    public ServiceResponse listPopularCourse(String orgSEQ) {
        if(orgSEQ==null||orgSEQ.trim().length()==0){
            orgSEQ="1.";
        }
        // factor为时间参数，factor越大时间所占的比重便越大（新发布的课程会越占优）
        final double factor = 1.5;
        // x为学员评分参数，x越大学员评分所占的比重越大
        final double x = 70.2;
        List<String> orgSEQList= EosorgTOrganizationServiceImpl.orgSEQList(orgSEQ);
        Map<String,Object> orgMap = new HashMap<>();
        orgMap.put("orgSEQ",orgSEQList);
        List<CourseVo> courseVoList = courseMapper.listCourseCreateDateAndEnrollNums(orgMap);
        //List<CourseVo> courseVoList = new ArrayList<>();
        List<RsmRcmBookDiscuss> rsmRcmBookDiscusses = rsmRcmBookDiscussMapper.listRsmRcm();
        for (CourseVo courseVo: courseVoList ) {
            Map<Integer,Integer> integerMap = new HashMap<>();
            for(RsmRcmBookDiscuss rsmRcmBookDiscuss : rsmRcmBookDiscusses){
                if(courseVo.getCourseId() == rsmRcmBookDiscuss.getBookId()){
                    integerMap.put(rsmRcmBookDiscuss.getScore(),rsmRcmBookDiscuss.getCount().intValue());
                }
            }
            courseVo.setScoreMap(integerMap);
        }
        Queue<CourseVo> courseQueue = new PriorityQueue<>(10, new Comparator<CourseVo>() {
            // 课程热度的比较函数
            public int compare(CourseVo form1, CourseVo form2) {
            Date now = new Date();
            Date date1 = form1.getCreateDate();
            Date date2 = form2.getCreateDate();
            // t1, t2为课程上传日到当日的时间间隔，单位为天
            int t1 = 0, t2 = 0;
            try {
                t1 = ToolsUtil.daysBetween(date1, now);
                t2 = ToolsUtil.daysBetween(date2, now);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            // num1, num2为课程选学人数
            int num1 = 0, num2 = 0;
            if (form1.getSelectedTimes() != null) {
                num1 = form1.getSelectedTimes() ;
            }
            if (form2.getSelectedTimes() != null) {
                num2 = form2.getSelectedTimes() ;
            }
            // rating1, rating2为课程的学员打分的加权评分与评分人数的乘积，同样评分下评分人数越多该值越大
            double rating1 = getWeightedRating(form1.getScoreMap());
            double rating2 = getWeightedRating(form2.getScoreMap());

            // score为最终的课程分数
            double score1 = (num1 + rating1 * x) / Math.pow(t1 + 2, factor);
            double score2 = (num2 + rating2 * x) / Math.pow(t2 + 2, factor);
            if (score1 - score2 < 0) {
                return -1;
            } else if (score1 - score2 > 0) {
                return 1;
            } else {
                return 0;
            }
            }
        });
        for (CourseVo form : courseVoList) {
            courseQueue.add(form);
            // 当堆中课程大于10时， 删除评分最低的课程，始终保证堆中的10门课程是分数最高的
            if (courseQueue.size() > 10) {
                courseQueue.poll();
            }
        }
        List<CourseVo> result = new LinkedList<>();
        if(courseQueue.size()>0){
            for (int i = 0; i < 10; i++) {
                CourseVo form = courseQueue.poll();
                if(form != null && form.getCourseId() != null){
                    Course temp = courseMapper.selectByPrimaryKey(form.getCourseId());
                    form = getCourseVo(temp);
                    // 由于小根堆poll的顺序是由小到大，故依次将课程加到队首，保证10门课程按降序排列
                    result.add(0, form);
                }
            }
        }
        return ServiceResponse.createBySuccess(result);
    }

    @Override
    public List<CourseVo> listPopularCourseReturnCourseVoList(String orgSEQ) {
        if(orgSEQ==null||orgSEQ.trim().length()==0){
            orgSEQ="1.";
        }
        // factor为时间参数，factor越大时间所占的比重便越大（新发布的课程会越占优）
        final double factor = 1.5;
        // x为学员评分参数，x越大学员评分所占的比重越大
        final double x = 70.2;
        List<String> orgSEQList= EosorgTOrganizationServiceImpl.orgSEQList(orgSEQ);
        Map<String,Object> orgMap = new HashMap<>();
        orgMap.put("orgSEQ",orgSEQList);
        List<CourseVo> courseVoList = courseMapper.listCourseCreateDateAndEnrollNums(orgMap);
        //List<CourseVo> courseVoList = new ArrayList<>();
        List<RsmRcmBookDiscuss> rsmRcmBookDiscusses = rsmRcmBookDiscussMapper.listRsmRcm();
        for (CourseVo courseVo: courseVoList ) {
            Map<Integer,Integer> integerMap = new HashMap<>();
            for(RsmRcmBookDiscuss rsmRcmBookDiscuss : rsmRcmBookDiscusses){
                if(courseVo.getCourseId() == rsmRcmBookDiscuss.getBookId()){
                    integerMap.put(rsmRcmBookDiscuss.getScore(),rsmRcmBookDiscuss.getCount().intValue());
                }
            }
            courseVo.setScoreMap(integerMap);
        }
        Queue<CourseVo> courseQueue = new PriorityQueue<>(10, new Comparator<CourseVo>() {
            // 课程热度的比较函数
            public int compare(CourseVo form1, CourseVo form2) {
            Date now = new Date();
            Date date1 = form1.getCreateDate();
            Date date2 = form2.getCreateDate();
            // t1, t2为课程上传日到当日的时间间隔，单位为天
            int t1 = 0, t2 = 0;
            try {
                t1 = ToolsUtil.daysBetween(date1, now);
                t2 = ToolsUtil.daysBetween(date2, now);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            // num1, num2为课程选学人数
            int num1 = 0, num2 = 0;
            if (form1.getSelectedTimes() != null) {
                num1 = form1.getSelectedTimes() ;
            }
            if (form2.getSelectedTimes() != null) {
                num2 = form2.getSelectedTimes() ;
            }
            // rating1, rating2为课程的学员打分的加权评分与评分人数的乘积，同样评分下评分人数越多该值越大
            double rating1 = getWeightedRating(form1.getScoreMap());
            double rating2 = getWeightedRating(form2.getScoreMap());

            // score为最终的课程分数
            double score1 = (num1 + rating1 * x) / Math.pow(t1 + 2, factor);
            double score2 = (num2 + rating2 * x) / Math.pow(t2 + 2, factor);
            if (score1 - score2 < 0) {
                return -1;
            } else if (score1 - score2 > 0) {
                return 1;
            } else {
                return 0;
            }
            }
        });
        for (CourseVo form : courseVoList) {
            courseQueue.add(form);
            // 当堆中课程大于10时， 删除评分最低的课程，始终保证堆中的10门课程是分数最高的
            if (courseQueue.size() > 10) {
                courseQueue.poll();
            }
        }
        List<CourseVo> result = new LinkedList<>();
        if(courseQueue.size()>0){
            for (int i = 0; i < 10; i++) {
                CourseVo form = courseQueue.poll();
                if(form != null && form.getCourseId() != null){
                    Course temp = courseMapper.selectByPrimaryKey(form.getCourseId());
                    form = getCourseVo(temp);
                    // 由于小根堆poll的顺序是由小到大，故依次将课程加到队首，保证10门课程按降序排列
                    result.add(0, form);
                }
            }
        }
        return result;
    }

    /**
     * 选学成员
     * @param map
     * @return
     */
    @Override
    public ServiceResponse listLimitedUserInfoByCourseId(Map<String, Object> map) {

        List<UcsEmployeeCourse> ucsEmployeeCourseList = ucsEmployeeCourseMapper.findByExample(map);
        List<BasicUserVo> basicUserVoList = new ArrayList<>();
        for (UcsEmployeeCourse ucsEmployeeCourse :ucsEmployeeCourseList){
            Integer operatorId = ucsEmployeeCourse.getOperatorID();
            EosorgTEmployee eosorgTEmployee = eosorgTEmployeeMapper.selectByPrimaryKey(operatorId);
            BasicUserVo form = new BasicUserVo();
            form.setAddress(eosorgTEmployee.getADDRESS());
            form.setGender(eosorgTEmployee.getGender());
            form.setMobileNo(eosorgTEmployee.getMobileNO());
            form.setOemail(eosorgTEmployee.getOEmail());
            form.setOperatorId(operatorId);
            form.setOperatorName(eosOperatorMapper.selectByPrimaryKey(operatorId).getOperatorName());
            form.setOtel1(eosorgTEmployee.getOTel1());
            form.setTenantId(eosOperatorMapper.selectByPrimaryKey(operatorId).getTenantId());
            Integer orgTenantId = form.getTenantId();
            if (orgTenantId != null && orgTenantId.equals(map.get("tenantId"))) {
                form.setOrgName(eosorgTOrganizationMapper.selectByPrimaryKey(eosorgTEmployee.getOrgID()).getOrgName());
            } else {
                if(map.get("tenantId") != null){
                    form.setOrgName(tenantMapper.selectByPrimaryKey(Integer.parseInt(map.get("tenantId")+"")).getTenantName());
                }
            }
            form.setUserId(eosOperatorMapper.selectByPrimaryKey(operatorId).getUserId());
            basicUserVoList.add(form);
        }
        Map<String,Object> resultMap = new HashMap<>();
        Integer selectedTimes = courseMapper.selectByPrimaryKey(Long.parseLong(map.get("courseId")+""))
                .getSelectedTimes();
        resultMap.put("selectedTimes",selectedTimes == null ? 0 : selectedTimes);
        resultMap.put("userList",basicUserVoList);
        return ServiceResponse.createBySuccess(resultMap);
    }


    /**
     * 课程管理
     * @param map
     * @return
     */
    @Override
    public ServiceResponse queryAllOffLineCourseList(Map<String, Object> map) {
        PageHelper.startPage((Integer) map.get("startIndex"),(Integer) map.get("count"));
        List<Course> courseList = courseMapper.findAllCourseListByOrgInner(map);
        //判断课程包里的片段是否包含内容
        for (Course course : courseList)
            if(course.getIsCoursePackage() != null && course.getIsCoursePackage() ==1 ){
                Boolean url = true;
                Map<String,Object> map1 = new HashMap();
                map1.put("courseId", course.getCourseId());
                List<Chapter> chapters = chapterService.findByMap(map1);
                int row = 0;
                if(chapters.size() > 0 ){
                    for (Chapter chapter : chapters) {
                        List<Course> courses = chapter.getSubset();
                        if(courses.size() > 0 ){
                            row ++ ;
                        }
                        for (Course course2 : courses) {
                            if(course2.getEnterUrl() == null  || "".equals(course2.getEnterUrl())){
                                url = false;
                                break;
                            }
                        }
                    }
                }
                if(row == 0 ){
                    url = false;
                }
                if(url){
                    course.setEnterUrl("1");
                }else{
                    course.setEnterUrl("");
                }
            }

        PageInfo pageInfo = new PageInfo(courseList);
        pageInfo.setList(courseList);
        List<CourseVo> courseVoList = new ArrayList<>();
        Integer rootOrgId = map.get("rootOrgId") == null ? 0 : Integer.parseInt(map.get("rootOrgId").toString());
        List<Course> courses = pageInfo.getList();
        for (int i = 0; i < courses.size() ;i++){
            Course course = courses.get(i);
            CourseVo courseVo = getCourseVo(course,rootOrgId,null);
            courseVoList.add(courseVo);
        }
        pageInfo.setList(courseVoList);
        return ServiceResponse.createBySuccess(pageInfo);
    }


    /**
     * 查询共享课程池
     *
     * @param map
     * @return
     */
    @Override
    public ServiceResponse queryAllShareCourseList(Map<String, Object> map) {
        Integer rootOrgId=Integer.valueOf(map.get("rootOrgId").toString());
        Integer operatorId=Integer.valueOf(map.get("operatorId").toString());
        int tenantOrgId=tenantMapper.selectByPrimaryKey((Integer) map.get("tenantId")).getOrgId();
        map.put("tenantOrgId",tenantOrgId);
        eosorgTOrganizationService.putSameSecondaryParentTenantIdListInMap(map);
        List<Course> courseList = new ArrayList<>();
        if(map.get("resultName")!=null && map.get("resultName").equals("shareCourse")){
            PageHelper.startPage((Integer) map.get("startIndex"),(Integer) map.get("count"));
            courseList = courseMapper.findAllCourseListByOrgOuter(map);
        }else if(map.get("resultName").equals("collectCourse") ){
            PageHelper.startPage((Integer) map.get("startIndex"),(Integer) map.get("count"));
            courseList = courseMapper.findCollectCourseListByOrg(map);
        }
        PageInfo pageInfo = new PageInfo(courseList);
        pageInfo.setList(courseList);
        courseList = pageInfo.getList();
        List<CourseVo> courseVoList = new ArrayList<>();
        for (int i = 0; i < courseList.size() ;i++){
            Course course = courseList.get(i);
            CourseVo courseVo = getCourseVo(course,rootOrgId,operatorId);
            courseVoList.add(courseVo);
        }
        pageInfo.setList(courseVoList);
        return ServiceResponse.createBySuccess(pageInfo);
    }

    /**
     * 删除课程
     * @param selectbox
     * @return
     */
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public ServiceResponse deleteCourse(String[] selectbox) {
        for (String id : selectbox){
            Long courseId = Long.parseLong(id);
            Course course = courseMapper.selectByPrimaryKey(courseId);
            if(courseMapper.isAllreadyLearned(courseId) > 0){
                return ServiceResponse.createByErrorMessage("课程<" + course.getCourseName() + "> 已被使用,不能删除");
            }
            if(courseMapper.isAllreadyArranged(courseId) > 0 ){
                return ServiceResponse.createByErrorMessage("已有学员学习课程<" + course.getCourseName() + ">,不能删除");
            }
            courseMapper.deleteByPrimaryKey(courseId);
            List<CourseCoursetype> courseCoursetypeList = courseCoursetypeMapper.findCourseCoursetypebyCourseId(courseId);
            for(CourseCoursetype courseCoursetype:courseCoursetypeList){
                courseCoursetypeMapper.deleteByPrimaryKey(courseCoursetype.getID());
            }
            List<CourseKeytagKey> courseKeytagKeys = courseKeytagMapper.findByCourseId(courseId);
            for(CourseKeytagKey courseKeytagKey : courseKeytagKeys){
                courseKeytagMapper.deleteByPrimaryKey(courseKeytagKey);
            }
        }
        return ServiceResponse.createBySuccessMessage("删除成功");
    }

    /**
     * 修改发布转态
     * @param courseId
     * @param pubStatusId
     * @return
     */
    @Override
    public ServiceResponse updateCoursePubStatus(Long courseId, Integer pubStatusId) {
        Course course = courseMapper.selectByPrimaryKey(courseId);
        course.setPubStatus(pubStatusId);
        //调整发布状态,相应公开范围一律调成成不公开
        course.setOpenScope(2201);
        int result = courseMapper.updateByPrimaryKeySelective(course);
        if(result > 0 ){
            return ServiceResponse.createBySuccessMessage("修改成功");
        }
        return ServiceResponse.createByErrorMessage("修改失败");
    }

    /**
     * 修改公开范围,发布时间
     * @param courseId
     * @param openScope
     * @return
     */
    @Override
    public ServiceResponse updateCourseOpenScope(Long courseId, Integer openScope,Integer orgId) {
        Course course = courseMapper.selectByPrimaryKey(courseId);
        course.setOpenScope(openScope);
        course.setShareDate(new Date());
        course.setOpenOrgSEQ(eosorgTOrganizationService.getOpenOrgSEQ(orgId,(short)openScope.intValue()));
        int result = courseMapper.updateByPrimaryKeySelective(course);
        if(result > 0 ){
            return ServiceResponse.createBySuccessMessage("修改成功");
        }
        return ServiceResponse.createByErrorMessage("修改失败");
    }

    /**
     * 计算课程的加权评分
     * @param map 课程的scoreMap
     * @return 加权评分
     */
    private static double getWeightedRating(Map<Integer, Integer> map) {
        final double c = 6.55; 		// c为所有课程的平均选学人数
        final double m = 4.458;     // m为所有课程的平均分
        final double b = 3.916;     // 评分高于b的课程分数为正，低于b的则为负
        if (map == null) {
            return m;
        }
        int fiveScoreNum = 0;
        int fourScoreNum = 0;
        int threeScoreNum = 0;
        int twoScoreNum = 0;
        int oneScoreNum = 0;
        if (map.get(5) != null) {
            fiveScoreNum = map.get(5);
        }
        if (map.get(4) != null) {
            fourScoreNum = map.get(4);
        }
        if (map.get(3) != null) {
            threeScoreNum = map.get(3);
        }
        if (map.get(2) != null) {
            twoScoreNum = map.get(2);
        }
        if (map.get(1) != null) {
            oneScoreNum = map.get(1);
        }
        int scoreSum = fiveScoreNum * 5 + fourScoreNum * 4 + threeScoreNum * 3 + twoScoreNum * 2 + oneScoreNum;
        int numSum = fiveScoreNum + fourScoreNum + threeScoreNum + twoScoreNum + oneScoreNum;
        // 计算加权评分时给每门课程假想的增加c个打m分的人，减少评分人数过少时分数的不合理性
        double weightedRating = (c * m + scoreSum) / (numSum + c);

        // 返回打分人数与评分的乘积，同样评分下打分人数越多该值越大
        return (weightedRating - b) * numSum;
    }

    /**
     * 课程VO的装换
     * @param _course
     * @param orgId
     * @param operatorId
     * @return
     */

    @Override
    public CourseVo getCourseVo(Course _course,Integer orgId,Integer operatorId){
        CourseVo temp = new CourseVo();
        if(_course.getCourseId()!=null)temp.setCourseId(_course.getCourseId());
        if(_course.getClassHour()!=null)temp.setClassHour(_course.getClassHour());
        if(_course.getCourseName()!=null) temp.setCourseName(_course.getCourseName());
        if(_course.getCourseNo()!=null) temp.setCourseNo(_course.getCourseNo());
        if(_course.getStudyDay()!=null)temp.setStudyDay(_course.getStudyDay());
        //查询发布状态
        if(_course.getPubStatus()!=null) {
            String statusName = dDictionaryMapper.getDDictionaryMapperByCode(_course.getPubStatus().toString(), "1090").getName();
            temp.setPubStatus(statusName.toString());
            temp.setPubSts(_course.getPubStatus());
        }
        temp.setEnterUrl(_course.getEnterUrl()==null?"":_course.getEnterUrl());
        temp.setOpenScope(_course.getOpenScope()==null?0:_course.getOpenScope());

        OpenScopeType type=OpenScopeType.findByCode(Short.valueOf(temp.getOpenScope()+""));
        if(type!=null){
            temp.setOpenScopeStr(type.getName());
        }
        temp.setSliceType(_course.getSliceType());
        temp.setCreator(_course.getCreator()==null?"":_course.getCreator());
        temp.setMaker(_course.getMaker()==null?"":_course.getMaker());
        temp.setSuitableObject(_course.getSuitableObject()==null?"":_course.getSuitableObject());
        temp.setIsOpenCourse(_course.getIsOpenCourse()==null?"":_course.getIsOpenCourse());
        temp.setRemark(_course.getRemark()==null?"":_course.getRemark());
        temp.setMainContent(_course.getContent()==null?"":_course.getContent());
        temp.setIsSharedCourse(_course.getIsSharedCourse()==null?"":_course.getIsSharedCourse());
        int watchable = 1;   //管理员只能编辑本机构的课程，不能编辑子机构的课程，只能观看本机构课程和其他机构设置为共享的课程
        if(_course.getOrgId()!=null&& orgId != null &&_course.getOrgId().longValue()==orgId){
            temp.setUpdatable(1);
        }else{
            temp.setUpdatable(0);
        }
        temp.setWatchable(watchable);
        temp.setProduceOrgName(_course.getProduceOrgName());
        temp.setTenantId(_course.getTenantId());
        temp.setOrgId(_course.getOrgId());
        StringBuffer stations = new StringBuffer().append("");
        //查字典
        if(_course.getStationId()!=null){
            String[] stationIdArr=_course.getStationId().split(";");
            for(int j=0;j<stationIdArr.length;j++){
                stations.append(dDictionaryMapper.getDDictionaryMapperByCode(stationIdArr[j],"2900").getName()).append(";");
            }
        }
        //4ms
        if(_course.getExpertAreaId()!=null&&!"".equals(_course.getExpertAreaId())){
            Integer courseTypeId = Integer.parseInt(_course.getExpertAreaId());
            CourseType coursetype = this.courseTypeMapper.selectByPrimaryKey(courseTypeId);
            if(coursetype!=null)
                temp.setExpertAreas(coursetype.getCourseTypeName().toString());
        }else{
            temp.setExpertAreas("");
        }
        temp.setCourseStations(stations.toString());
        temp.setApproveStatus(_course.getApproveStatus());
        temp.setCreateDateStr(DateTimeUtil.dateToStr(_course.getCreateDate(),"yyyy-MM-dd"));
        temp.setShareDateStr(DateTimeUtil.dateToStr(_course.getShareDate(),"yyyy-MM-dd"));
        //long t222 = System.currentTimeMillis();
        //5ms
        if(_course.getOrgId()!=null){
            temp.setOrgId(_course.getOrgId());
            EosorgTOrganization eo = eosorgTOrganizationMapper.selectByPrimaryKey(_course.getOrgId());
            if(eo != null){
                temp.setOrgName(eo.getOrgName());
            }else
                temp.setOrgName("");
        }
        else
            temp.setOrgName("");

        temp.setClassfication(_course.getClassfication()==null?0:_course.getClassfication());

        if(_course.getClassfication()==null||_course.getClassfication()==0){
            temp.setClassficationName("");
        }else{
            temp.setClassficationName(dDictionaryMapper.getDDictionaryMapperByCode(_course.getClassfication().toString(), "4000").getName());
        }
        temp.setCategory(_course.getCategory()==null?0:_course.getCategory());
        if(_course.getCategory()==null||_course.getCategory()==0){
            temp.setCategoryName("");
        }else{
            temp.setCategoryName(dDictionaryMapper.getDDictionaryMapperByCode(_course.getCategory().toString(), "4020").getName());
        }
        String keyWords = _course.getKeyWords()==null?"":_course.getKeyWords();
        keyWords = keyWords.replace("；", ";");
        temp.setKeyWords(keyWords);
        temp.setIsSharedCourse(_course.getIsSharedCourse());
        Map<String ,Object> map = new HashMap<>();
        map.put("bookId",_course.getCourseId());
        map.put("discussType",1703);
        map.put("isReply",0);
        Integer totalScore = 0;
        List<RsmRcmBookDiscuss> rsmList = rsmRcmBookDiscussMapper.getRsmRcmbookdiscussList(map);
        for(RsmRcmBookDiscuss _discuss:rsmList){
            totalScore = totalScore + _discuss.getScore();
        }
        Integer discussMan = 1;
        if(rsmList.size()>0)
            discussMan = rsmList.size();
        Double score = DoubleUtil.getTwoDigitalDoubleData(totalScore.doubleValue()/discussMan) ;
        temp.setDiscussUser(rsmList.size());
        temp.setScore(score);
        map.clear();
        map.put("courseId",_course.getCourseId());
        List<UcsEmployeeCourse> ucsEmployeecourseList = ucsEmployeeCourseMapper.findByExample(map);
        temp.setUsedByOrgFlag(0);
        for(UcsEmployeeCourse ucs:ucsEmployeecourseList){
            if(ucs.getTenantId()!=null && ucs.getTenantId()==Integer.parseInt(PropertiesUtil.getProperty("tenantId"))){
                temp.setUsedByOrgFlag(1);
                break;
            }
        }
        temp.setTotalLearnedUser(ucsEmployeecourseList.size());
        List<Integer> tenantIdList=new ArrayList();
        for(UcsEmployeeCourse ucs:ucsEmployeecourseList){
            int num=0;
            for(int j=0;j<tenantIdList.size();j++){
                if(ucs.getTenantId()!=null&&ucs.getTenantId().intValue()==tenantIdList.get(j).intValue())break;
                else
                    num++;
            }
            if(num==tenantIdList.size()&&ucs.getTenantId()!=null)tenantIdList.add(ucs.getTenantId());
        }
        temp.setShareCourseToTenantNum(tenantIdList.size());
        String videoConvertFlag=Constants.VIDEO_UNCONVERT_STATUS;
        if(_course.getSliceType()!=null&&_course.getSliceType().equals("3")){
            CourseVideoConvert courseVideoConvert = courseVideoConvertMapper.findCourseVideoconvertByCourseId(_course.getCourseId());
            if(courseVideoConvert!=null&&!courseVideoConvert.getConvertDescription().equals(Constants.VIDEO_CONVERT_COMPLETE))
                videoConvertFlag=Constants.VIDEO_CONVERT_STATUS;
            else
                videoConvertFlag=Constants.VIDEO_UNCONVERT_STATUS;
        }
        temp.setVideoConvertFlag(videoConvertFlag);
        map.put("tenantId", PropertiesUtil.getProperty("tenantId"));
        ucsEmployeecourseList = ucsEmployeeCourseMapper.findByExample(map);
        if(temp.getUsedByOrgFlag()==1){
            temp.setShareCourseToOrgLearnerNum(ucsEmployeecourseList.size());
        }
        if(operatorId != null ){
            map.clear();
            map.put("courseId",_course.getCourseId());
            map.put("operatorId",operatorId);
            map.put("orgId",orgId);
            List<CourseCollect> courseCollects = courseCollectMapper.findByExample(map);
            if(courseCollects!=null&&courseCollects.size()>0)
                temp.setShareCourseCollectStatus(2912);
            else
                temp.setShareCourseCollectStatus(2911);
        }
        temp.setSelectedTimes(_course.getSelectedTimes());
        return temp;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public int insertCourse(CourseForm courseForm, HttpServletRequest request) {
        int result ;
        EosOperator operator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        Integer tenantId=operator.getTenantId();
        if (request.getParameter("trainId") != null
                && !"".equals(request.getParameter("trainId"))) {
            int trainId = Integer.parseInt(request.getParameter("trainId")
                    .toString());
            Train train = trainMapper.selectByPrimaryKey(trainId);
            request.setAttribute("train", train);
        }
        Course course = new Course();
        String courseId = request.getParameter("courseId");
        Long _courseId;
        if(courseId != null){
            _courseId = Long.parseLong(courseId);
            course = courseMapper.selectByPrimaryKey(_courseId);
        }else{
            _courseId = new Date().getTime();

        }
        if (request.getParameter("upTenantId") != null){
            course.setUpTenantId(Integer.valueOf(request.getParameter(
                    "upTenantId").toString()));
        }
        course.setCategory(courseForm.getCategory());
        course.setCourseId(_courseId);
        course.setCourseNo(String.valueOf(new Date().getTime()));
        course.setCourseName(courseForm.getCourseName());
        course.setCreateDate(DateTimeUtil.strToDate(courseForm.getCreateDate(),"yyyy-MM-dd"));
        course.setCreator(courseForm.getCreator());
        course.setMaker(courseForm.getMaker());
        course.setSuitableObject(courseForm.getSuitableObject());
        course.setContent(courseForm.getMainContent());
        course.setFee(courseForm.getFee());
        course.setScore(courseForm.getScore());
        course.setStudyDay(courseForm.getStudyDay());
        course.setClassHour(courseForm.getClassHour());
        if("1".equals(request.getParameter("isCoursePackage"))){
            course.setSliceType(String.valueOf(3));
        }else{
            course.setSliceType(String.valueOf(courseForm.getKindId()));
        }
        course.setUploadOrgId(PropertiesUtil.getProperty("org.Id") == null ? 0L :
                Long.parseLong(PropertiesUtil.getProperty("org.Id").toString()));
        course.setClassfication(courseForm.getClassfication());
        course.setRequiredCourseTypeId(courseForm
                .getRequiredCourseTypeId());
        course.setBreed(courseForm.getBreed());
        course.setTenantId(tenantId);
        course.setSourceTenantId(tenantId);
        course.setIsUnderScope(1181);
        course.setPubStatus(1091);
        course.setOpenScope(2201);
        course.setIsSharedCourse("0");
        course.setRemark(courseForm.getRemark());
        course.setIsOpenCourse(courseForm.getIsOpenCourse());
        course.setSourceCourseId(_courseId);
        course.setIsFormal(1);
        Integer isCoursePackage = null;
        if(request.getParameter("isCoursePackage") != null){
            isCoursePackage = Integer.parseInt(request.getParameter("isCoursePackage"));
        }
        if(isCoursePackage == null){
            course.setIsCoursePackage(0);
            course.setIsFirstLevelDirectory(0);
        }else{
            course.setIsCoursePackage(isCoursePackage);
            //是课程包的时候肯定是一级课程，不是课程包的时候也是一级课程
            course.setIsFirstLevelDirectory(1);
        }

        //课程领域
        String selectTerritory=request.getParameter("selectTerritory");
        List<ResourceCourseType> rctList = resourceCourseTypeMapper.findByResourceId(_courseId, ResourceType.COURSE.getTypeCode());
        ResourceCourseType rct ;
        if(rctList==null||rctList.size()==0){
            rct = new ResourceCourseType();
        }
        else{
            rct = rctList.get(0);
        }
        rct.setCourseTypeId(Integer.parseInt(selectTerritory));
        rct.setResourceType(ResourceType.COURSE.getTypeCode());
        rct.setResourceId(_courseId);
        if(rct.getId() == null){
            rct.setId(idWorker.nextId());
            resourceCourseTypeMapper.insertSelective(rct);
        }
        else{
            resourceCourseTypeMapper.updateByPrimaryKeySelective(rct);
        }

        // 制作单位
        course.setProduceOrgName(courseForm.getProduceOrgName());
        // 资助单位
        course.setFundingOrgName(courseForm.getFundingOrgName());

        if (request.getParameter("isNoted") != null
                && !request.getParameter("isNoted").toString().equals("")){
            course.setIsNoted(Integer.valueOf(request.getParameter("isNoted")
                    .toString()));
        }else{
            course.setIsNoted(0);
        }

        if(course.getIsMultiSize() == null){
            course.setIsMultiSize(0);
        }
        int orgId = Integer.parseInt(request.getSession()
                .getAttribute(Constants.ROOTORGID_KEY).toString());
        if (courseForm.getOrgId() != null
                && !courseForm.getOrgId().toString().trim().equals("")) {
            orgId = courseForm.getOrgId();
        }

        int operatorId = 0;
        if (operator != null){
            operatorId = operator.getOperatorId();
        }

        // 处理主讲人纳入或撤出教师库的逻辑操作
        MtMixTrainScheduleItemInfo mtScheduleItemForm = new MtMixTrainScheduleItemInfo();
        mtScheduleItemForm.setCourseId(_courseId);
        if (courseForm.getIsTeacher() == 1
                && courseForm.getCreator() != "") {
            mtScheduleItemForm.setTeacherName(courseForm.getCreator());
            mtScheduleItemForm.setCourseName(courseForm.getCourseName());
            int teacherId = mtMixTrainScheduleItemInfoService.synTeacher(
                    mtScheduleItemForm, orgId, operatorId);
            course.setHasTeacher(1);
            course.setTeacherId(teacherId);
        } else {
            course.setHasTeacher(0);
            if (course.getTeacherId() != null
                    && course.getTeacherId().intValue() != 0) {
                mtScheduleItemForm.setTeacherId(course.getTeacherId());
                this.mtMixTrainScheduleItemInfoService.dropScheduleCourseAndTeacher(
                        mtScheduleItemForm, 0);
                course.setTeacherId(0);
            }
        }
        Integer rootTenantId =  Integer.parseInt(PropertiesUtil.getProperty("tenantId"));
        Tenant tenant = tenantMapper.selectByPrimaryKey(rootTenantId);

        if (rootTenantId == 1005) {
            tenant = this.eosorgTOrganizationService.findTenant(orgId);
        }


        // 两种情况：1.课件新增(新创建，未上传课件)；
        if (course.getOrgId() == null) {
            course.setOrgId(orgId);
            course.setServerFilePath(null);
            course.setEnterUrl(null);
            course.setIndexUrl(null);
            course.setCustomFilePath(null);
            course.setMobilePlayAddress(null);
            course.setPictureURL(null);
        }

        // 2.课件所属单位修改，需要调整已上传课件所在的文件夹位置
        // 2018-02-24修改；修复bug：当修改"课件所属单位",需要重新上传课件问题
        if (course.getOrgId() != null && course.getOrgId() != orgId) {
            changeCourseOrgId(course,orgId);
        }


        String keywords = courseForm.getKeyWords();
        course.setKeyWords(keywords);
        List<CourseKeytagKey> courseKeyTagList = courseKeytagMapper.findByCourseId(_courseId);
        if (courseKeyTagList != null && courseKeyTagList.size() > 0) {
            for (CourseKeytagKey courseKeyTag : courseKeyTagList) {
                courseKeytagMapper.deleteByPrimaryKey(courseKeyTag);
            }
        }
        String[] keyArr = keywords.split(";|；");
        for (int i = 0; i < keyArr.length; i++) {
            CourseKeytagKey coursekey = new CourseKeytagKey();
            coursekey.setCourseId(_courseId);
            coursekey.setKeyWords(keyArr[i]);
            this.courseKeytagMapper.insertSelective(coursekey);
        }
        if (courseId != null && !"".equals(courseId)){
            Course  oldCourse = courseMapper.selectByPrimaryKey(Long.parseLong(courseId));
            if(oldCourse.getIsCoursePackage() == 0 && course.getIsCoursePackage() == 1){
                Double newClassHour = 0.00;
                List<Course> chapterCourses = courseMapper.listSecondCourseByCourseId(Long.parseLong(courseId));
                if(chapterCourses != null && chapterCourses.size() >0){
                    for (Course course2 : chapterCourses) {
                        newClassHour += course2.getClassHour();
                    }
                }
                course.setClassHour(DoubleUtil.getTwoDigitalDoubleData(newClassHour));
            }
            result = courseMapper.updateByPrimaryKeySelective(course);
        }else{
            result = courseMapper.insertSelective(course);
        }
        afterInsertOrUpdate(request);

        if(course.getIsFirstLevelDirectory() == 1){
            request.setAttribute("isFirstLevelDirectory", 1);
        }
        request.setAttribute("courseId",_courseId);
        request.setAttribute("PictureURL",course.getPictureURL());
        request.setAttribute("courseUrl",course.getEnterUrl());
        request.setAttribute("uploadAddress",PropertiesUtil.getProperty("elearning-fs.address"));
        request.setAttribute("courseName", course.getCourseName());
        request.setAttribute("coursekind", courseForm.getKindId());
        request.setAttribute("operatorOrgId", orgId);
        request.setAttribute("customPath", course.getCustomFilePath());
        request.setAttribute("serverFilePath", course.getServerFilePath());
        request.setAttribute("courseInfo", course);
        request.setAttribute("orgDomainName", tenant.getStoredContext());
        return result;
    }

    @Override
    public ServiceResponse updateCoursePic(Long courseId, String pictureUrl) {
        Course course = courseMapper.selectByPrimaryKey(courseId);
        course.setPictureURL(pictureUrl);
        int result = courseMapper.updateByPrimaryKeySelective(course);
        if(result > 0 ){
            return ServiceResponse.createBySuccess("成功");
        }
        return ServiceResponse.createByErrorMessage("失败");
    }

    @Override
    public String uploadSingleVideo(HttpServletRequest request, HttpServletResponse response) {
        String result = "";
        String uploadFileName = request.getParameter("uploadFileName");
        String uploadFileType = uploadFileName.substring(uploadFileName
                .lastIndexOf("."), uploadFileName.length());
        if (request.getParameter("trainId") != null  && !"".equals(request.getParameter("trainId"))) {
            Integer trainId = Integer.parseInt(request.getParameter("trainId").toString());
            Train train = trainMapper.selectByPrimaryKey(trainId);
            request.setAttribute("train", train);
        }
        long courseId = Long.parseLong(request.getParameter("uploadCourseId").toString());
        Course course = courseMapper.selectByPrimaryKey(courseId);
        EosOperator operator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        Integer tenantId=operator.getTenantId();
        Tenant tenant = tenantMapper.selectByPrimaryKey(tenantId);
        Integer orgId = Integer.parseInt(request.getSession().getAttribute(Constants.ROOTORGID_KEY).toString());
        if(course.getOrgId() != null && !course.getOrgId().toString().trim().equals("")){
            orgId = course.getOrgId();
        }
        if(tenantId == 1005){
            tenant = this.eosorgTOrganizationService.findTenant(orgId);
        }
        String orgDomainName = tenant.getStoredContext();
        String serverFilePath = orgDomainName + "/" + courseId + "/";
        course.setServerFilePath(serverFilePath);

        if(Constants.SUPPORT_MindMap_TYPE.indexOf(uploadFileType) > -1){
            try {
                String coursePath=tenant.getHttpAddress()+"/"+tenant.getStoredContext()+"/"+courseId+"/"+courseId+".html";
                course.setEnterUrl(coursePath);
                course.setMobilePlayAddress(coursePath);
                course.setSliceType("1");
                response.sendRedirect("../courseType/otherFileUpload.do?courseID="+courseId+"&courseTypeId=");
                courseMapper.updateByPrimaryKeySelective(course);
                request.setAttribute("courseId", courseId);
                return null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if (Constants.SUPPORT_DOCUMENT_TYPE.indexOf(uploadFileType) > -1) {//如果是文档，统一先转化为pdf，然后pdf转为swf
            course.setEnterUrl(String.valueOf(courseId) + ".swf");
            course.setMobilePlayAddress(tenant.getHttpAddress() + "/"
                    + orgDomainName + "/" + courseId + "/"
                    + String.valueOf(courseId) + ".pdf");
            if (null == course.getPictureURL() || course.getPictureURL().equals("")) {
                course.setPictureURL(tenant.getHttpAddress() + "/"
                        + orgDomainName + "/" + courseId + "/"
                        + String.valueOf(courseId) + ".jpg");
            }
            if (request.getParameter("studentFlag") != null){
                result = "courseManage/singleDocumentConvertStudentFlag";
            }else{
                result = "courseManage/singleDocumentConvert";
            }
        } else {//如果是视频的话，后续全部压缩转化为.mp4格式的
            course.setEnterUrl(Constants.VIDEO_NAME_PREFIX + String.valueOf(courseId) + Constants.VIDEO_POSTFIX);
            course.setMobilePlayAddress(tenant.getHttpAddress() + "/"
                    + orgDomainName + "/" + courseId + "/" + "sv"
                    + String.valueOf(courseId) + ".mp4");
            if (null == course.getPictureURL() || course.getPictureURL().equals("")){
                course.setPictureURL(tenant.getHttpAddress() + "/"
                        + orgDomainName + "/" + courseId + "/" + "i"
                        + String.valueOf(courseId) + ".jpg");
            }
            if (request.getParameter("studentFlag") != null){
                result = "courseManage/singleVideoConvertStudentFlag";
            }else{
                result = "courseManage/singleVideoConvert";
            }
        }
        courseMapper.updateByPrimaryKeySelective(course);
        request.setAttribute("courseId", courseId);
        if(request.getParameter("uploadChapter") != null && !"".equals(request.getParameter("uploadChapter"))){
            request.setAttribute("uploadChapter", 1);
            request.setAttribute("chapterId", request.getParameter("chapterId"));
        }
        if("1".equals(request.getParameter("courseDescription"))){
            request.setAttribute("courseDescription", 1);
        }
        return result;
    }

    @Override
    public Integer updateCourse(Course course) {
        return courseMapper.updateByPrimaryKeySelective(course);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public String uploadSingleCourse(HttpServletRequest request) {
        String result = "";
        EosOperator operator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        Integer tenantId=operator.getTenantId();
        Map<String, Object> map = new HashMap<>();
        Tenant tenant = tenantMapper.selectByPrimaryKey(tenantId);
        String courseIdStr = request.getParameter("courseId");
        Long courseId = 0L;
        Course course = new Course();
        if(courseIdStr != null && !courseIdStr.equals("")){
            courseId = Long.parseLong(courseIdStr);
            course = courseMapper.selectByPrimaryKey(courseId);
        }
        String courseUrl = request.getParameter("courseUrl");
        String courseTypeId = request.getParameter("courseTypeId");
        String customPath = "";
        String indexUrl = "";
        if (courseUrl.indexOf("/") > -1) {
            customPath = courseUrl.substring(0, courseUrl.lastIndexOf("/"));
            indexUrl = courseUrl.substring(courseUrl.lastIndexOf("/") + 1,
                    courseUrl.length());
        } else{
            indexUrl = courseUrl;
        }
        try {
            map.put("courseId",courseId);
            List<UcsEmployeeCourse> ucsEmployeeCourseList = ucsEmployeeCourseMapper.findByExample(map);
            UcsEmployeeCourse ucsEmployeecourse;
            if (ucsEmployeeCourseList.size() != 0) {
                for (int i = 0; i < ucsEmployeeCourseList.size(); i++) {
                    ucsEmployeecourse =  ucsEmployeeCourseList.get(i);
                    ucsEmployeeCourseMapper.deleteByPrimaryKey(ucsEmployeecourse.getID());
                }
            }
            List<UscUsertliddayStudyInfo> uscUsertliddayStudyInfos = uscUsertliddayStudyInfoService.findByMap(map);
            UscUsertliddayStudyInfo uscUsertliddaystudyinfo ;
            if (uscUsertliddayStudyInfos.size() != 0) {
                for (int i = 0; i < uscUsertliddayStudyInfos.size(); i++) {
                    uscUsertliddaystudyinfo =  uscUsertliddayStudyInfos.get(i);
                    uscUsertliddayStudyInfoService.deleteByPrimaryKey(uscUsertliddaystudyinfo.getUTDID());
                }
            }
            if (courseUrl != null
                    && !courseUrl.equals(course.getCustomFilePath())) {
                if (courseUrl.indexOf("http:") > -1
                        || courseUrl.indexOf("www.") > -1) {

                    if (courseUrl.contains("http:")) {
                        course.setEnterUrl(courseUrl);
                        course.setMobilePlayAddress(courseUrl);
                        course.setIndexUrl(courseUrl);
                        course.setCustomFilePath(courseUrl);
                    } else {
                        course.setEnterUrl("http://" + courseUrl);
                        course.setMobilePlayAddress("http://" + courseUrl);
                        course.setIndexUrl(courseUrl);
                        course.setCustomFilePath(courseUrl);
                    }
                } else {
                    String oldIndexUrl = course.getIndexUrl();
                    String relativeServerFilePath = "";
                    String oldCustomServerFilePath = course.getCustomFilePath();
                    String originServerFilePath = course.getServerFilePath();
                    if (oldCustomServerFilePath != null
                            && !oldCustomServerFilePath.equals("")) {
                        relativeServerFilePath = oldCustomServerFilePath
                                .replace(oldIndexUrl, "");
                        if (relativeServerFilePath != ""
                                && !relativeServerFilePath.equals("")) {
                            if (relativeServerFilePath.lastIndexOf("/") == relativeServerFilePath
                                    .length() - 1)
                                relativeServerFilePath = relativeServerFilePath
                                        .substring(0, relativeServerFilePath
                                                .lastIndexOf("/"));
                            originServerFilePath = course.getServerFilePath()
                                    .replace(relativeServerFilePath, "");
                        }
                    }
                    Integer orgId = Integer.parseInt(request.getSession().getAttribute(Constants.ROOTORGID_KEY).toString());
                    if(course.getOrgId() != null && !course.getOrgId().toString().trim().equals("")){
                        orgId = course.getOrgId();
                    }
                    if(tenantId == 1005){
                        tenant = this.eosorgTOrganizationService.findTenant(orgId);
                    }
                    String httpAdress = tenant.getHttpAddress();
                    String serverFilePath = originServerFilePath + customPath;
                    course.setServerFilePath(serverFilePath);
                    course.setCustomFilePath(customPath);
                    course.setIndexUrl(indexUrl);
                    course.setEnterUrl(httpAdress + "/" + serverFilePath + "/"
                            + indexUrl);
                    course.setMobilePlayAddress(httpAdress + "/" + serverFilePath + "/"
                            + indexUrl);
                    course.setCustomFilePath(courseUrl);
                }
                courseMapper.updateByPrimaryKeySelective(course);
            }

            List<UscLectureFile> lectureFileList = uscLectureFileService.findAllLectureFile(courseId).getData();
            if (request.getParameter("trainId") != null
                    && !"".equals(request.getParameter("trainId"))) {
                String trainId1 = (request.getParameter("trainId")
                        .toString());
                Integer trainId = Integer.parseInt(trainId1);
                Train train = trainMapper.selectByPrimaryKey(trainId);
                request.setAttribute("train", train);
            }
            request.setAttribute("courseTypeId", courseTypeId);
            request.setAttribute("LectureList", lectureFileList);
            request.setAttribute("courseId", courseId);
            request.setAttribute("customPath", courseUrl);
            request.setAttribute("courseUrl", course.getEnterUrl());
            request.setAttribute("uploadCourseSuccess", "入口地址设置成功");
            request.setAttribute("serverFilePath", course.getServerFilePath());
            request.setAttribute("orgDomainName", tenant.getStoredContext());
            if(request.getParameter("uploadChapter")!=null && !"".equals(request.getParameter("uploadChapter"))){
                request.setAttribute("uploadChapter", 1);
                request.setAttribute("chapterId", request.getParameter("chapterId"));
            }
            if (course.getSliceType().equals("1")){
                result = "courseManage/addSingleCourseware";
            }else if (course.getSliceType().equals("7")){
                result = "courseManage/addOtherResourceAddress";
                //todo
                //forward = mapping.findForward("otherResourceUploadUrlSuccess");
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean isAllreadyLearned(Long courseId) {
        return courseMapper.isAllreadyLearned(courseId) > 0 ;
    }

    @Override
    public boolean isAllreadyArranged(Long courseId) {
        return courseMapper.isAllreadyArranged(courseId) > 0 ;
    }

    @Override
    public int insert(Course course) {
        return courseMapper.insertSelective(course);
    }

    @Override
    public Integer delete(Long courseId) {
        return courseMapper.deleteByPrimaryKey(courseId);
    }

    @Override
    public ServiceResponse findVisibleCourseByCondition(Integer startIndex, Integer count, HttpServletRequest request) {

        Map<String,Object> map = new HashMap<>();
        EosOperator operator = (EosOperator) request.getSession().getAttribute(Constants.USERINFO_KEY);
        String orgSEQ;
        if(operator==null){
            //当前访问学员尚未登录
            Tenant currentTenant = tenantMapper.selectByPrimaryKey(Integer.parseInt(PropertiesUtil.getProperty("tenantId")));
            EosorgTOrganization tenantOrg = eosorgTOrganizationMapper.selectByPrimaryKey(currentTenant.getOrgId());
            orgSEQ=tenantOrg.getOrgSEQ();
        } else{
            EosorgTOrganization org = eosorgTOrganizationMapper.selectByPrimaryKey(eosorgTEmployeeMapper.selectByPrimaryKey(operator.getOperatorId()).getOrgID());
            orgSEQ = org.getOrgSEQ();
        }
        String tenant = request.getParameter("tenant");// 是否本单位 1本单位 0其他
        Integer tenantId = null;
        String innerAndOuter = null;
        if (tenant != null){
            if("1".equals(tenant)){
                innerAndOuter = "2701";
            }
            if("0".equals(tenant)){
                innerAndOuter = "2702";
            }
        }
        String requestInnerAndOuter=request.getParameter("innerAndOuter");
        if(requestInnerAndOuter!=null && requestInnerAndOuter.trim().length()>0){
            innerAndOuter = requestInnerAndOuter ;
        }
        if(innerAndOuter != null && innerAndOuter.trim().length()>0){
            tenantId = operator.getTenantId();
            if(innerAndOuter.equals("2701")){
                map.put("tenantId",tenantId);
            }else {
                map.put("noTenantId",tenantId);
            }
        }
        map.put("courseName",request.getParameter("courseName"));
        List<String> orgSEQList=EosorgTOrganizationServiceImpl.orgSEQList(orgSEQ);
        map.put("orgSEQ",orgSEQList);
        PageHelper.startPage(startIndex,count);
        List<Course> courseList = courseMapper.findVisibleCourseByCondition(map);
        PageInfo pageInfo = new PageInfo(courseList);
        pageInfo.setList(courseList);
        return ServiceResponse.createBySuccess(pageInfo);
    }

    @Override
    public List<Course> listSecondCourseByCourseId(Long courseId) {
        return courseMapper.listSecondCourseByCourseId(courseId);
    }

    @Override
    public ServiceResponse getCourseRelevantCourse(Long originalCourseId, HttpServletRequest request) {
        EosOperator operator = (EosOperator) request.getSession().getAttribute(Constants.USERINFO_KEY);
        String orgSEQ;
        if(operator==null){
            //当前访问学员尚未登录
            Tenant currentTenant = tenantMapper.selectByPrimaryKey(Integer.parseInt(PropertiesUtil.getProperty("tenantId")));
            EosorgTOrganization tenantOrg = eosorgTOrganizationMapper.selectByPrimaryKey(currentTenant.getOrgId());
            orgSEQ=tenantOrg.getOrgSEQ();
        } else{
            EosorgTOrganization org = eosorgTOrganizationMapper.selectByPrimaryKey(eosorgTEmployeeMapper.selectByPrimaryKey(operator.getOperatorId()).getOrgID());
            orgSEQ = org.getOrgSEQ();
        }
        List<String> orgSEQList=EosorgTOrganizationServiceImpl.orgSEQList(orgSEQ);
        Map<String,Object> map = new HashMap<>();
        map.put("originalCourseId",originalCourseId);
        map.put("orgSEQ",orgSEQList);
        List<Course> courseList = courseMapper.getCourseRelevantCourse(map);
        List<CourseVo> courseVos = new ArrayList<>();
        for (Course course : courseList ) {
            courseVos.add(getCourseVo(course));
        }
        return ServiceResponse.createBySuccess(courseVos);
    }


    private void changeCourseOrgId(Course course,int orgId){
        Integer tenantId = Integer.parseInt(PropertiesUtil.getProperty("tenantId"));
        Tenant tenant = tenantMapper.selectByPrimaryKey(tenantId);

        if (tenantId == 1005) {
            tenant = this.eosorgTOrganizationService.findTenant(orgId);
        }

        // 将原有的课件移动到正确的文件夹下面,调用elearning-fs的WebServcie方法，操作课件服务器
        String originServerFilePath = course.getServerFilePath();
        if(originServerFilePath != null && !originServerFilePath.equals("")){
            String oldChar = originServerFilePath.substring(0,originServerFilePath.indexOf("/")+1);
            String newServerFilePath = originServerFilePath.replace(oldChar, tenant.getStoredContext() + "/");
//			调用WebService方法
            boolean flag = commonToolsService.moveFolder(originServerFilePath,newServerFilePath);
            if(flag){// 课件资源移动文件夹成功，则同步修改数据表中course的对应记录
                //按照课程类别不同，分别处理
                String kindId = course.getSliceType();
                if(kindId != null && kindId.equals("1")){//单一网址课件（包括知识地图类课件，知识地图类课件上传时选择：单一文档/视频(.json文件)；上传成功之后生成网址，记录为单一网址课件）
                    course.setServerFilePath(newServerFilePath);
                    if(course.getEnterUrl() != null){
                        String enterUrl = course.getEnterUrl().replace(originServerFilePath, newServerFilePath);
                        course.setEnterUrl(enterUrl);
                    }
                    if(course.getMobilePlayAddress() != null){
                        String mobilePlayAddress = course.getMobilePlayAddress().replace(originServerFilePath, newServerFilePath);
                        course.setMobilePlayAddress(mobilePlayAddress);
                    }
                    if(course.getPictureURL() != null && course.getPictureURL().contains(originServerFilePath)){
                        String pictureUrl = course.getPictureURL().replace(originServerFilePath, newServerFilePath);
                        course.setPictureURL(pictureUrl);
                    }
                }else if(kindId != null && kindId.equals("2")){ //SCORM课件
                    //此类课件暂时弃用
                }else if(kindId != null && kindId.equals("3")){ //单一文档/视频
                    course.setServerFilePath(newServerFilePath);
                    if(course.getMobilePlayAddress() != null){
                        String mobilePlayAddress = course.getMobilePlayAddress().replace(originServerFilePath, newServerFilePath);
                        course.setMobilePlayAddress(mobilePlayAddress);
                    }
                    if(course.getPictureURL() != null && course.getPictureURL().contains(originServerFilePath)){
                        String pictureUrl = course.getPictureURL().replace(originServerFilePath, newServerFilePath);
                        course.setPictureURL(pictureUrl);
                    }
                }
            }else{//否则需要重新上传资源
                course.setServerFilePath(null);
                course.setEnterUrl(null);
                course.setIndexUrl(null);
                course.setCustomFilePath(null);
                course.setMobilePlayAddress(null);
                course.setPictureURL(null);
            }
            course.setOrgId(orgId);
        }else{//第三方网址课件，课件实体未在本地课件资源服务器上，则不需要调整课件位置
            course.setOrgId(orgId);
        }
    }

    /**
     * 无论是inster 还是update执行之后都需要进行的操作，就是读取配置文件，确定是否使用了云存储，企业的编号是多少，并且放入session之中
     */
    private void afterInsertOrUpdate( HttpServletRequest request) {
        //通过读取配置，确定是否使用云
        String useCloud = PropertiesUtil.getProperty("useCloud");
        if (useCloud != null && (!useCloud.trim().equals(""))) {
            request.getSession().setAttribute("useCloud", useCloud);
        }
        String orgId = PropertiesUtil.getProperty("org.Id");
        Tenant tenant = tenantMapper.selectByPrimaryKey(Integer.parseInt(PropertiesUtil.getProperty("tenantId")));
        String orgDomainName = tenant.getStoredContext();
        if (orgId != null && (!orgId.trim().equals(""))) {
            request.getSession().setAttribute("orgId", orgId);
        }
        if (orgDomainName != null && (!orgDomainName.trim().equals(""))) {
            request.getSession().setAttribute("orgDomainName", orgDomainName);
        }
        return;
    }


    @Override
    public int deleteByPrimaryKey(Long courseId) {
        return this.courseMapper.deleteByPrimaryKey(courseId);
    }

    @Override
    public List<Course> listCourseByMap(Map<String,Object> map) {
        return this.courseMapper.listCourseByMap(map);
    }
    @Override
    public Course findCourseById(Long course_id) {
        return this.courseMapper.selectByPrimaryKey(course_id);
    }

    @Override
    public List<Course> getListByCourseNameAndOrgId(Map<String,Object> map){
        return this.courseMapper.getListByCourseNameAndOrgId(map);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public List<CourseFormAll> getCourseFormList(List<Course> courseList){
        ArrayList<CourseFormAll> courseFormList = new ArrayList<>();
        if(!courseList.isEmpty() && courseList.size()>0){
            for(Course _course : courseList){
                CourseFormAll courseForm = this.getCourseForm(_course);
                if(courseForm.getCourse_id() != null){
                    courseForm.setStudyNumber(this.uscUsertliddayStudyInfoService.findTotalCountbyCourseId(courseForm.getCourse_id()));
                }
                courseFormList.add(courseForm);
            }
        }
        return courseFormList;

    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public CourseFormAll getCourseForm(Course _course) {
        CourseFormAll temp = new CourseFormAll();
        if(_course.getCourseId()!=null){
            temp.setCourse_id(_course.getCourseId());
        }
        if(_course.getClassHour()!=null){
            temp.setClassHour(_course.getClassHour());
        }
        if(_course.getCourseName()!=null){
            temp.setCourseName(_course.getCourseName());
        }
        if(_course.getCourseNo()!=null){
            temp.setCourseNo(_course.getCourseNo());
        }
        if(_course.getPubStatus()!=null) {
            String statusName = this.dictionaryService.getDDictionaryByCodeAndParentCode(_course.getPubStatus().toString(), "1090");
            temp.setPubStatus(statusName);
            temp.setPubSts(_course.getPubStatus());
        }
        temp.setSliceType(_course.getSliceType());
        temp.setCreator(_course.getCreator()==null?"":_course.getCreator());
        temp.setMaker(_course.getMaker()==null?"":_course.getMaker());
        temp.setSuitableObject(_course.getSuitableObject()==null?"":_course.getSuitableObject());
        temp.setIsOpenCourse(_course.getIsOpenCourse()==null?"":_course.getIsOpenCourse());
        temp.setRemark(_course.getRemark()==null?"":_course.getRemark());
        temp.setMainContent(_course.getContent()==null?"":_course.getContent());
        if(_course.getPictureURL()!=null){
            temp.setPictureUrl(_course.getPictureURL());
        }
        temp.setIsSharedCourse(_course.getIsSharedCourse()==null?"":_course.getIsSharedCourse());
        temp.setOpenScope(_course.getOpenScope()==null?2201:_course.getOpenScope());
        OpenScopeType type=OpenScopeType.findByCode(Short.valueOf(temp.getOpenScope()+""));
        if(type!=null){
            temp.setOpenScopeStr(type.getName());
        }
        temp.setEnterUrl(_course.getEnterUrl()==null?"":_course.getEnterUrl());
        temp.setBreed(this.dictionaryService.getDDictionaryByCodeAndParentCode(_course.getBreed(), "3000"));
        temp.setClassfication(_course.getClassfication()==null?0:_course.getClassfication());
        //制作单位
        if(_course.getProduceOrgName()!=null){
            temp.setProduceOrgName(_course.getProduceOrgName());
        }
        //资助单位
        if(_course.getFundingOrgName()!=null){
            temp.setFundingOrgName(_course.getFundingOrgName());
        }
        if(_course.getClassfication()==null||_course.getClassfication()==0){
            temp.setClassficationName("");
        }else{
            temp.setClassficationName(this.dictionaryService.getDDictionaryByCodeAndParentCode(_course.getClassfication().toString(), "4000"));
        }
        temp.setCategory(_course.getCategory()==null?0:_course.getCategory());
        if(_course.getCategory()==null||_course.getCategory()==0){
            temp.setCategoryName("");
        }else{
            temp.setCategoryName(this.dictionaryService.getDDictionaryByCodeAndParentCode(_course.getCategory().toString(), "4020"));
        }
        temp.setIsNoted(_course.getIsNoted());
        temp.setIsApproved(_course.getIsApproved());
        temp.setIsFormal(_course.getIsFormal());
        if(_course.getUpTenantId()!=null){
            temp.setUpTenantId(_course.getUpTenantId());
        } else{
            temp.setUpTenantId(0);
        }
        temp.setTenantId(_course.getTenantId()==null?0:_course.getTenantId());
        StringBuffer stations = new StringBuffer().append("");
        if(_course.getStationId()!=null){
            for(int j=1;j<_course.getStationId().length();j++){
                if(_course.getStationId().indexOf(String.valueOf(j)) != -1){
                    if(getStationDescription(j)!=null)
                        stations.append(getStationDescription(j)).append(";");
                }
            }
        }
        StringBuffer experts=new StringBuffer().append("");
        if(_course.getExpertAreaId()!=null && !"".equals(_course.getExpertAreaId())){
            //	String expertAreaName=this.getCoursetypeService().findById(Integer.parseInt(_course.getExpertAreaId())).getCourseTypeName();
            Integer courseTypeId = Integer.parseInt(_course.getExpertAreaId());
            CourseType coursetype = this.courseTypeService.selectByPrimaryKey(courseTypeId);
            String expertAreaName = "";
            if(coursetype!=null){
                expertAreaName= coursetype.getCourseTypeName().toString();
            }
            temp.setExpertAreas(expertAreaName);
            String subExpertAreaName="";
            if(_course.getSubExpertAreaId()!=null && !"".equals(_course.getSubExpertAreaId())){
                if(this.courseTypeService.selectByPrimaryKey(Integer.parseInt(_course.getSubExpertAreaId())) != null){
                    subExpertAreaName=this.courseTypeService.selectByPrimaryKey(Integer.parseInt(_course.getSubExpertAreaId())).getCourseTypeName();
                    temp.setExpertAreas(subExpertAreaName.equals("")?expertAreaName:expertAreaName+"->"+subExpertAreaName);
                }
            }
        }
        temp.setCourseStations(stations.toString());

        String keyWords = _course.getKeyWords()==null?"":_course.getKeyWords();
        keyWords = keyWords.replace("；", ";");
        temp.setKeyWords(keyWords);

        temp.setCreateDate(DateTimeUtil.dateToStr(_course.getCreateDate(),"yyyy-MM-dd"));
        temp.setShareDate(DateTimeUtil.dateToStr(_course.getShareDate(),"yyyy-MM-dd"));

        if(_course.getOrgId()!=null){
            temp.setOrgId(_course.getOrgId().longValue());
            if(this.eosorgTOrganizationService.findById(_course.getOrgId())!=null){
                temp.setOrgName(this.eosorgTOrganizationService.findById(_course.getOrgId()).getOrgName());
            }else
                temp.setOrgName("");
        } else{
            temp.setOrgName("");
        }

        Map<String,Object> parmMap = new HashMap<>();
        parmMap.put("bookId",_course.getCourseId());
        parmMap.put("discussType",1703);
        parmMap.put("isReply",0);

        Integer totalScore = 0;
        List<RsmRcmBookDiscuss> rsmList = this.rsmRcmbookdiscussService.getListByBookIdAndDiscussTypeAndIsReply(parmMap);
        for(RsmRcmBookDiscuss _discuss:rsmList){
            totalScore = totalScore + _discuss.getScore();
        }
        Integer discussMan = 1;
        if(rsmList.size()>0){
            discussMan = rsmList.size();
        }
        String score = Tools.getTwoDigitalData(totalScore.doubleValue()/discussMan);
        temp.setDiscussUser(rsmList.size());
        temp.setScore(score);
        List<UcsEmployeeCourse> ucsEmployeecourseList = this.ucsEmployeeCourseService.findByCourseId(_course.getCourseId());
        temp.setTotalLearnedUser(ucsEmployeecourseList.size());
        String videoConvertFlag=Constants.VIDEO_UNCONVERT_STATUS;
        if(_course.getSliceType()!=null && _course.getSliceType().equals("3")){
            CourseVideoConvert courseVideoConvert = this.courseVideoConvertService.getCourseVideoconvertByCourseId(_course.getCourseId());
            if(courseVideoConvert!=null && !courseVideoConvert.getConvertDescription().equals(Constants.VIDEO_CONVERT_COMPLETE)){
                videoConvertFlag=Constants.VIDEO_CONVERT_STATUS;
            } else{
                videoConvertFlag=Constants.VIDEO_UNCONVERT_STATUS;
            }
        }
        temp.setVideoConvertFlag(videoConvertFlag);
        return temp;
    }

    private String getStationDescription(int i){
        String result = null;
        switch (i) {
            case 1: result = Constants.GANGWEI001; break;
            case 2: result = Constants.GANGWEI002; break;
            case 3: result = Constants.GANGWEI003; break;
            case 4: result = Constants.GANGWEI004; break;
            case 5: result = Constants.GANGWEI005; break;
        }
        return result;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public List<Course> listCoursesByOperatorId(Map<String, Object> map){
        return this.courseMapper.listCoursesByOperatorId(map);

    }


}
