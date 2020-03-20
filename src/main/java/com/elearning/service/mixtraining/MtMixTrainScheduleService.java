package com.elearning.service.mixtraining;

import com.elearning.common.Constants;
import com.elearning.common.ServiceResponse;
import com.elearning.dao.mixtraining.MtMixTrainScheduleMapper;
import com.elearning.pojo.courseStudy.UserNeedLearnCourse;
import com.elearning.pojo.courseStudy.UserTrain;
import com.elearning.pojo.mixtraining.MtMixTrainSchedule;
import com.elearning.pojo.mixtraining.MtMixTrainScheduleItemInfo;
import com.elearning.pojo.mixtraining.MtMixTrainScheduleTrain;
import com.elearning.pojo.pub.Course;
import com.elearning.pojo.pub.EosOperator;
import com.elearning.pojo.pub.TrainWithBLOBs;
import com.elearning.service.courseStudy.IUserNeedLearnCourseService;
import com.elearning.service.courseStudy.IUserTrainService;
import com.elearning.service.coursemanage.ICourseTypeService;
import com.elearning.service.onlinetraining.IOnlineTrainingService;
import com.elearning.service.pub.ICourseService;
import com.elearning.service.systemManage.ISysFormFieldService;
import com.elearning.util.DateTimeUtil;
import com.elearning.vo.CourseFormAll;
import com.elearning.vo.mixtraining.MtMixTrainScheduleItemForm;
import com.elearning.vo.teacher.TchrBaseInfoForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


@Service("mtMixTrainScheduleService")
public class MtMixTrainScheduleService implements IMtMixTrainScheduleService{

    @Autowired
    private MtMixTrainScheduleMapper mtMixTrainScheduleMapper;

    @Autowired
    private IOnlineTrainingService onlineTrainingService;

    @Autowired
    private IMtMixTrainScheduleTrainService mtMixTrainScheduleTrainService;

    @Autowired
    private IUserTrainService userTrainService;

    @Autowired
    private IMtMixTrainScheduleItemInfoService mtMixTrainScheduleItemInfoService;

    @Autowired
    private ICourseService courseService;

    @Autowired
    private ICourseTypeService courseTypeService;

    @Autowired
    private IUserNeedLearnCourseService userNeedLearnCourseService;

    @Autowired
    private ISysFormFieldService sysFormFieldService;

    @Autowired
    private IMtMixTrainUserTrainInfoService mtMixTrainUserTrainInfoService;


    @Override
    public MtMixTrainSchedule selectByPrimaryKey(Integer scheduleId){

        return this.mtMixTrainScheduleMapper.selectByPrimaryKey(scheduleId);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public String listScheduleItem(HttpServletRequest request){

        String jspName = "";

        EosOperator operator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        int operatorId = 0;
        if(operator != null){
            operatorId = operator.getOperatorId();
        }
        int trainId = Integer.parseInt(request.getParameter("trainId").toString());
        TrainWithBLOBs train = this.onlineTrainingService.findById(trainId);
        int scheduleId = 0;
        List<MtMixTrainScheduleTrain> mtScheduleTrainList = this.mtMixTrainScheduleTrainService.findScheduleTrainList(trainId);
        if(mtScheduleTrainList!=null && mtScheduleTrainList.size()>0){
            scheduleId=mtScheduleTrainList.get(0).getScheduleId();
        }
        Boolean userJoinedTrainFlag = this.userTrainService.ifOperatorJoinTrain(operatorId, trainId);
        if(userJoinedTrainFlag==false && operatorId==train.getOperatorId().intValue()){
            userJoinedTrainFlag=true;
        }
        List<MtMixTrainScheduleItemInfo> scheduleItemList = this.mtMixTrainScheduleItemInfoService.findByScheduleId(scheduleId);
        List<MtMixTrainScheduleItemInfo> scheudleOnlineItemList = this.mtMixTrainScheduleItemInfoService.getScheduleOnlineItemList(scheduleItemList);
        List<MtMixTrainScheduleItemInfo> scheduleOfflineItemList = this.mtMixTrainScheduleItemInfoService.getScheduleOfflineItemList(scheduleItemList);
        request.setAttribute("train", train);
        request.setAttribute("joinTrainFlag",userJoinedTrainFlag);
        if(scheudleOnlineItemList!=null&&scheudleOnlineItemList.size()>0){
            //request.setAttribute("onlineItemList", scheudleOnlineItemList);
            List<List<MtMixTrainScheduleItemInfo>> reviseMtScheduleOnlineItemList = new ArrayList<>();
            request.setAttribute("reviseOnlineItemList", reviseMtScheduleOnlineItemList);
            Date oldScheduleDate=null;
            List<MtMixTrainScheduleItemInfo> itemList=null;
            for(MtMixTrainScheduleItemInfo schedule:scheudleOnlineItemList){
                Date scheduleDate = schedule.getScheduleDate();
                if(scheduleDate != null){
                    if(!scheduleDate.equals(oldScheduleDate)){
                        oldScheduleDate = scheduleDate;
                        itemList = new ArrayList<>();
                        reviseMtScheduleOnlineItemList.add(itemList);
                    }
                } else{
                    itemList = new ArrayList<>();
                    reviseMtScheduleOnlineItemList.add(itemList);
                }
                itemList.add(schedule);
            }
        }

        if(scheduleOfflineItemList!=null && scheduleOfflineItemList.size()>0){
            List<List<MtMixTrainScheduleItemInfo>> reviseMtScheduleOfflineItemList = new ArrayList<List<MtMixTrainScheduleItemInfo>>();
            request.setAttribute("reviseOfflineItemList", reviseMtScheduleOfflineItemList);
            Date oldScheduleDate = null;
            List<MtMixTrainScheduleItemInfo> itemList = null;
            for(MtMixTrainScheduleItemInfo schedule:scheduleOfflineItemList){
                Date scheduleDate = schedule.getScheduleDate();
                if(!scheduleDate.equals(oldScheduleDate)){
                    oldScheduleDate = scheduleDate;
                    itemList = new ArrayList<>();
                    reviseMtScheduleOfflineItemList.add(itemList);
                }
                itemList.add(schedule);
            }
            for(MtMixTrainScheduleItemInfo itemInfo:scheduleOfflineItemList){
                if(itemInfo.getHoster()!=null && !"".equals(itemInfo.getHoster())){
                    request.setAttribute("scheduleHasHoster", true);
                    break;
                }
            }
        }
        request.setAttribute("userId", operatorId);
        if(request.getParameter("singleFlag")!=null){
            jspName = "courseStudy/trainSchedule";
        } else{
            jspName = "mixtraining/mixTrainingScheduleItemList";
        }
        return jspName;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public void forAddOnLineScheduleItemInfo(HttpServletRequest request){

        String scheduleStartDate=request.getParameter("scheduleStartDate");
        String scheduleEndDate=request.getParameter("scheduleEndDate");
        String courseId=request.getParameter("courseId");

        int trainId = Integer.parseInt(request.getSession().getAttribute("trainId").toString());
        int orgId = Integer.parseInt(request.getSession().getAttribute(Constants.ROOTORGID_KEY).toString());
        String year=(String) request.getSession().getAttribute(Constants.YEAR_KEY);

        EosOperator operator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        int operatorId=0;
        if(operator!=null){
            operatorId = operator.getOperatorId();
        }

        TrainWithBLOBs train=onlineTrainingService.findById(trainId);
        int scheduleId=-1;

        List<MtMixTrainScheduleTrain> mtScheduleTrainList=this.mtMixTrainScheduleTrainService.findScheduleTrainList(trainId);

        if(mtScheduleTrainList!=null && mtScheduleTrainList.size()>0){
            scheduleId=mtScheduleTrainList.get(0).getScheduleId();
        }

        MtMixTrainSchedule mtSchedule=new MtMixTrainSchedule();
        if(scheduleId==-1){
            mtSchedule.setScheduleName(train.getTrainName().concat("日程"));
            mtSchedule.setOrgId(orgId);
            mtSchedule.setCreateDate(new Date());
            mtSchedule.setCreatorId(operatorId);
            mtSchedule.setIsToWebSite(0);
            int i = this.mtMixTrainScheduleMapper.insertSelective(mtSchedule);
            if(i > 0){
                scheduleId = mtSchedule.getScheduleId();
                MtMixTrainScheduleTrain mtMixTrainScheduleTrain = new MtMixTrainScheduleTrain();
                mtMixTrainScheduleTrain.setScheduleId(scheduleId);
                mtMixTrainScheduleTrain.setTrainId(trainId);
                mtMixTrainScheduleTrain.setStatus("1");

                this.mtMixTrainScheduleTrainService.save(mtMixTrainScheduleTrain);
                request.getSession().setAttribute("scheduleId", scheduleId);
            }
        }

        List<Course> courseList=new ArrayList();
        List<Course> addCourseList=new ArrayList();
        if(courseId.indexOf(",")>-1){
            String [] courseIdArr=courseId.split(",");
            for(int i=0;i<courseIdArr.length;i++){
                if(!courseIdArr[i].equals("")){
                    Course course = this.courseService.findCourseById(Long.valueOf(courseIdArr[i]));
                    courseList.add(course);
                    if(!this.mtMixTrainScheduleItemInfoService.judgeCourseIdInSchedule(scheduleId, course.getCourseId())){
                        addCourseList.add(course);
                    }
                }
            }
        }else{
            courseList.add(this.courseService.findCourseById(Long.valueOf(courseId)));
        }
        if(courseList!=null && courseList.size()>0){
            for(Course course:courseList){
                MtMixTrainScheduleItemInfo scheduleItem=new MtMixTrainScheduleItemInfo();
                scheduleItem.setCourseId(course.getCourseId());
                scheduleItem.setCourseName(course.getCourseName());
                scheduleItem.setOnOrOffLineFlag(0);
                scheduleItem.setScheduleStartTime(scheduleStartDate);
                scheduleItem.setScheduleEndTime(scheduleEndDate);
                scheduleItem.setScheduleId(scheduleId);
                scheduleItem.setOperatorId(operatorId);
                this.mtMixTrainScheduleItemInfoService.saveOrUpdate(scheduleItem);
            }
        }
        if(addCourseList!=null && addCourseList.size()>0){
            Map<String,Object> userLearnCoursemap=new HashMap<>();
            userLearnCoursemap.put("scheduleId", scheduleId);
            userLearnCoursemap.put("train", train);
            userLearnCoursemap.put("year", year);
            userLearnCoursemap.put("courseList", courseList);
            this.saveOrUpdateUserNeedLearnCourse(userLearnCoursemap);
        }
        request.getSession().setAttribute("recentOnlineScheduleDate", scheduleStartDate);

    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public void saveOrUpdateUserNeedLearnCourse(Map<String,Object> map){

        String year=map.get("year").toString();
        int scheduleId=Integer.parseInt(map.get("scheduleId").toString());
        TrainWithBLOBs train=(TrainWithBLOBs) map.get("train");
        List<Course> _courseList=new ArrayList();
        if(map.get("courseList")!=null){
            _courseList=(List<Course>) map.get("courseList");
        }
        List<MtMixTrainScheduleItemInfo> mtScheduleItemList=this.mtMixTrainScheduleItemInfoService.findByScheduleId(scheduleId);

        List<UserTrain> userTrainList=this.userTrainService.getListByTrainId( train.getID());

        if(!userTrainList.isEmpty() && userTrainList.size()>0){
            for(UserTrain userTrain : userTrainList){
                if(_courseList!=null && _courseList.size()>0){
                    for(Course course:_courseList){
                        UserNeedLearnCourse userNeedLearnCourse=new UserNeedLearnCourse();
                        userNeedLearnCourse.setClassHour(course.getClassHour());
                        userNeedLearnCourse.setCourseName(course.getCourseName());

                        if(this.courseTypeService.findByCourseId(course.getCourseId())!=null){
                            userNeedLearnCourse.setCourseType(this.courseTypeService.findByCourseId(course.getCourseId()).getCourseTypeName());
                        }
                        userNeedLearnCourse.setIsStationTrain(Byte.valueOf(String.valueOf(train.getIsStationTrain()==true?1:0)));
                        userNeedLearnCourse.setTrainName(train.getTrainName());

                        userNeedLearnCourse.setOperatorId(userTrain.getOperatorID());
                        userNeedLearnCourse.setTrainId(train.getID());
                        userNeedLearnCourse.setYear(year);
                        userNeedLearnCourse.setSectionId(scheduleId);
                        userNeedLearnCourse.setCourseId(course.getCourseId());

                        //根据主键查询数据库中是否有数据
                        UserNeedLearnCourse userNeedLearnCourse1 = this.userNeedLearnCourseService.selectByPrimaryKey(userNeedLearnCourse);

                        if(userNeedLearnCourse1==null){
                            this.userNeedLearnCourseService.insert(userNeedLearnCourse);
                        }
                    }
                }else{
                    for(MtMixTrainScheduleItemInfo scheduleItem : mtScheduleItemList){
                        UserNeedLearnCourse userNeedLearnCourse=new UserNeedLearnCourse();
                        Course course=courseService.findCourseById(scheduleItem.getCourseId());
                        userNeedLearnCourse.setClassHour(course.getClassHour());
                        userNeedLearnCourse.setCourseName(course.getCourseName());

                        if(this.courseTypeService.findByCourseId(course.getCourseId())!=null){
                            userNeedLearnCourse.setCourseType(this.courseTypeService.findByCourseId(course.getCourseId()).getCourseTypeName());
                        }
                        userNeedLearnCourse.setIsStationTrain(Byte.valueOf(String.valueOf(train.getIsStationTrain()==true?1:0)));

                        userNeedLearnCourse.setTrainName(train.getTrainName());

                        userNeedLearnCourse.setOperatorId(userTrain.getOperatorID());
                        userNeedLearnCourse.setTrainId(train.getID());
                        userNeedLearnCourse.setYear(year);
                        userNeedLearnCourse.setSectionId(scheduleId);
                        userNeedLearnCourse.setCourseId(course.getCourseId());

                        userNeedLearnCourseService.insert(userNeedLearnCourse);
                    }
                }
            }
        }
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public void forAddOffLineScheduleItemInfo(MtMixTrainScheduleItemForm mtScheduleItemForm, HttpServletRequest request){

        int trainId=Integer.parseInt(request.getSession().getAttribute("trainId").toString());
        int orgId=Integer.parseInt(request.getSession().getAttribute(Constants.ROOTORGID_KEY).toString());

        EosOperator operator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);

        int operatorId=0;
        if(operator!=null){
            operatorId = operator.getOperatorId();
        }
        TrainWithBLOBs train=onlineTrainingService.findById(trainId);
        int scheduleId=-1;
        List<MtMixTrainScheduleTrain> mtScheduleTrainList=this.mtMixTrainScheduleTrainService.findScheduleTrainList(trainId);
        if(mtScheduleTrainList!=null&&mtScheduleTrainList.size()>0){
            scheduleId=mtScheduleTrainList.get(0).getScheduleId();
        }
        MtMixTrainSchedule mtSchedule=new MtMixTrainSchedule();

        if(scheduleId==-1){
            mtSchedule.setScheduleName(train.getTrainName().concat("日程"));
            mtSchedule.setOrgId(orgId);
            mtSchedule.setCreateDate(new Date());
            mtSchedule.setCreatorId(operatorId);
            mtSchedule.setIsToWebSite(0);
            scheduleId=this.mtMixTrainScheduleMapper.insertSelective(mtSchedule);

            MtMixTrainScheduleTrain mtMixTrainScheduleTrain = new MtMixTrainScheduleTrain();
            mtMixTrainScheduleTrain.setScheduleId(scheduleId);
            mtMixTrainScheduleTrain.setTrainId(trainId);
            mtMixTrainScheduleTrain.setStatus("1");

            this.mtMixTrainScheduleTrainService.save(mtMixTrainScheduleTrain);
            request.getSession().setAttribute("scheduleId", scheduleId);
        }

        MtMixTrainScheduleItemInfo scheduleItem=new MtMixTrainScheduleItemInfo();

        long courseId=-1;
        if(mtScheduleItemForm.getIsTrainTheme().intValue()==1){

            courseId=this.mtMixTrainScheduleItemInfoService.synCourse(mtScheduleItemForm, orgId,operator.getOperatorName());
            Course course=courseService.findCourseById(courseId);
            scheduleItem.setCourseId(courseId);
            mtScheduleItemForm.setCourseId(courseId);
            int teacherId=this.mtMixTrainScheduleItemInfoService.synTeacher(mtScheduleItemForm, orgId,operatorId);
            if(course!=null){
                course.setTeacherId(teacherId);
                courseService.updateCourse(course);
            }
            scheduleItem.setTeacherId(teacherId);
        }
        if(mtScheduleItemForm.getScheduleDate()!=null){
            scheduleItem.setScheduleDate(DateTimeUtil.strToDate(mtScheduleItemForm.getScheduleDate(),"yyyy-MM-dd"));
            request.getSession().setAttribute("recentOfflineScheduleDate", mtScheduleItemForm.getScheduleDate());
        }
        scheduleItem.setCourseId(courseId);
        scheduleItem.setCourseName(mtScheduleItemForm.getCourseName());
        scheduleItem.setTeacherName(mtScheduleItemForm.getTeacherName());
        scheduleItem.setOnOrOffLineFlag(1);
        scheduleItem.setScheduleStartTime(mtScheduleItemForm.getScheduleStartTime());
        scheduleItem.setScheduleEndTime(mtScheduleItemForm.getScheduleEndTime());
        scheduleItem.setIsTrainTheme(mtScheduleItemForm.getIsTrainTheme());
        scheduleItem.setHoster(mtScheduleItemForm.getHoster());
        scheduleItem.setHasCourseUrl(0);
        scheduleItem.setScheduleId(scheduleId);
        scheduleItem.setOperatorId(operatorId);
        scheduleItem.setLocation(mtScheduleItemForm.getLocation());
        /*FormFile file=mtScheduleItemForm.getUploadFile();
        if(file!=null&&file.getFileSize()>0){
            String fileStr=uploadFile(request,file);
            String fname=fileStr.substring(fileStr.lastIndexOf("/")+1, fileStr.length());
            scheduleItem.setFilePath(fileStr);
            scheduleItem.setFileName(fname);
            if(courseId!=-1){    //将资料同步至课程资料的第三步
                UscLectureFile lectureFile=new UscLectureFile();
                lectureFile.setCourseId(courseId);
                lectureFile.setLectureName("资料");
                lectureFile.setLectureUrl(fileStr);
                this.uscLectureFileService.insertLectureFile(lectureFile);
            }
        }*/
        //this.mtScheduleItemInfoService.getMtMixtTrainScheduleItemDAO().save(scheduleItem);
        this.mtMixTrainScheduleItemInfoService.insert(scheduleItem);

    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public int deleteAjax(HttpServletRequest request){

        int itemId=Integer.parseInt(request.getParameter("itemid").toString());

        MtMixTrainScheduleItemInfo scheduleItem=this.mtMixTrainScheduleItemInfoService.findById(itemId);

        int trainId = Integer.parseInt(request.getSession().getAttribute("trainId").toString());

        if(scheduleItem.getOnOrOffLineFlag().intValue()==0 && scheduleItem.getCourseId()!=null){
            this.userNeedLearnCourseService.deleteByCourseIdTrainId(scheduleItem.getCourseId(),trainId);

        }
        int n = this.mtMixTrainScheduleItemInfoService.deleteByPrimaryKey(scheduleItem.getId());


        return n;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public ServiceResponse editScheduleItem(HttpServletRequest request){

        int itemId=Integer.parseInt(request.getParameter("itemid").toString());
        MtMixTrainScheduleItemInfo scheduleItemInfo=this.mtMixTrainScheduleItemInfoService.findById(itemId);
        Date scheduleDate=scheduleItemInfo.getScheduleDate();

        if(scheduleDate!=null){
            scheduleDate=java.sql.Date.valueOf(DateTimeUtil.dateToStr(scheduleItemInfo.getScheduleDate()));
        }
        Map<String,Object> returnMap = new HashMap<>();
        returnMap.put("Id",scheduleItemInfo.getId());
        returnMap.put("scheduleDate",scheduleDate);
        returnMap.put("startTime",scheduleItemInfo.getScheduleStartTime());
        returnMap.put("endTime",scheduleItemInfo.getScheduleEndTime());
        returnMap.put("courseName",scheduleItemInfo.getCourseName());
        returnMap.put("teacher",scheduleItemInfo.getTeacherName());
        returnMap.put("scheduleRemark",scheduleItemInfo.getRemark());
        returnMap.put("courseId",scheduleItemInfo.getCourseId());
        returnMap.put("teacherId",scheduleItemInfo.getTeacherId());
        returnMap.put("isTrainTheme",scheduleItemInfo.getIsTrainTheme());
        returnMap.put("hoster",scheduleItemInfo.getHoster());
        returnMap.put("sortLable",scheduleItemInfo.getHoster());
        returnMap.put("location",scheduleItemInfo.getLocation());

        List<Map<String,Object>> List = new ArrayList<>();
        List.add(returnMap);

        /*List<MtMixTrainScheduleItemInfo> mtMixTrainScheduleItemInfoList = new ArrayList<>();
        mtMixTrainScheduleItemInfoList.add(scheduleItemInfo);*/

        return ServiceResponse.createBySuccess(List);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public void updateOnLineScheduleItem(HttpServletRequest request){

        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);

        int operatorId=0;
        if(eosoperator != null){
            operatorId = eosoperator.getOperatorId();
        }
        int itemId=Integer.parseInt(request.getParameter("editItem_id").toString());
        String year=(String) request.getSession().getAttribute(Constants.YEAR_KEY);

        int scheduleId = this.mtMixTrainScheduleItemInfoService.findById(itemId).getScheduleId();
        /*if(this.mtMixTrainScheduleItemInfoService.findById(itemId) != null){
            scheduleId=this.mtMixTrainScheduleItemInfoService.findById(itemId).getScheduleId();
        }*/

        int trainId = Integer.parseInt(request.getSession().getAttribute("trainId").toString());

        TrainWithBLOBs train=onlineTrainingService.findById(trainId);

        String scheduleStartDate=request.getParameter("editItem_startTrainTime");
        String scheduleEndDate=request.getParameter("editItem_endTrainTime");
        String courseId=request.getParameter("editItem_courseId");

        //该字段数据库中现在还没有添加
        //String sortLable=request.getParameter("editItem_sortLable");

        List<Course> courseList=new ArrayList();
        List<Course> addCourseList=new ArrayList();

        if(courseId.indexOf(",")>-1){
            String [] courseIdArr=courseId.split(",");
            for(int i=0;i<courseIdArr.length;i++){
                Course course = this.courseService.findCourseById(Long.valueOf(courseIdArr[i]));
                courseList.add(course);
                if(!this.mtMixTrainScheduleItemInfoService.judgeCourseIdInSchedule(scheduleId, course.getCourseId())){
                    addCourseList.add(course);
                }
            }
        }else{
            courseList.add(courseService.findCourseById(Long.valueOf(courseId)));
        }

        if(courseList!=null && courseList.size()>0){
            for(Course course : courseList){
                MtMixTrainScheduleItemInfo scheduleItem = new MtMixTrainScheduleItemInfo();

                scheduleItem.setCourseId(course.getCourseId());
                scheduleItem.setCourseName(course.getCourseName());
                scheduleItem.setOnOrOffLineFlag(0);
                scheduleItem.setScheduleStartTime(scheduleStartDate);
                scheduleItem.setScheduleEndTime(scheduleEndDate);
                scheduleItem.setScheduleId(scheduleId);
                scheduleItem.setOperatorId(operatorId);

                //该字段数据库中现在还没有添加
                //scheduleItem.setSortLable(sortLable);
                this.mtMixTrainScheduleItemInfoService.saveOrUpdate(scheduleItem);
            }
        }
        if(addCourseList!=null&&addCourseList.size()>0){
            Map<String,Object> userLearnCoursemap=new HashMap<>();

            userLearnCoursemap.put("scheduleId", scheduleId);
            userLearnCoursemap.put("train", train);
            userLearnCoursemap.put("year", year);
            userLearnCoursemap.put("courseList", addCourseList);

            this.saveOrUpdateUserNeedLearnCourse(userLearnCoursemap);
        }

    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public void updateOffLineScheduleItem(MtMixTrainScheduleItemForm mtMixTrainScheduleItemForm,HttpServletRequest request){

        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);

        int operatorId=0;
        if(eosoperator!=null){
            operatorId = eosoperator.getOperatorId();
        }
        int orgId=Integer.parseInt(request.getSession().getAttribute(Constants.ROOTORGID_KEY).toString());
        int itemId=Integer.parseInt(request.getParameter("editItem_id").toString());
        String _trainDate=request.getParameter("editItem_trainDate");
        String _startTrainTime=request.getParameter("editItem_startTrainTime");
        String _endTrainTime=request.getParameter("editItem_endTrainTime");
        String _courseName=request.getParameter("editItem_courseName");
        String _teacher=request.getParameter("editItem_teacher");
        String _teacherId=request.getParameter("editItem_teacherId");
        String _isTrainTheme=request.getParameter("editItem_isTrainTheme");
        String _hoster=request.getParameter("editItem_hoster");
        String _location=request.getParameter("editItem_location");
        MtMixTrainScheduleItemInfo scheduleItemInfo=this.mtMixTrainScheduleItemInfoService.findById(itemId);
        if(_trainDate!=null && !_trainDate.equals("")){
            scheduleItemInfo.setScheduleDate(DateTimeUtil.strToDate(_trainDate.toString(),"yyyy-MM-dd"));
        }
        scheduleItemInfo.setScheduleStartTime(_startTrainTime != null?_startTrainTime.toString():"");
        scheduleItemInfo.setScheduleEndTime(_endTrainTime!=null?_endTrainTime.toString():"");
        scheduleItemInfo.setCourseName(_courseName.toString());
        scheduleItemInfo.setTeacherName(_teacher!=null?_teacher.toString():"");
        //scheduleItemInfo.setTeacherId(Integer.parseInt(_teacherId));
        scheduleItemInfo.setHoster(_hoster!=null?_hoster.toString():"");
        scheduleItemInfo.setOperatorId(operatorId);
        scheduleItemInfo.setOnOrOffLineFlag(1);
        scheduleItemInfo.setLocation(_location);
        //同步更新课程库对应课程名称
        if(scheduleItemInfo.getCourseId().intValue()==-1&&Integer.parseInt(_isTrainTheme)==1){
            mtMixTrainScheduleItemForm.setIsTrainTheme(1);
            mtMixTrainScheduleItemForm.setCourseName(_courseName.toString().trim());
            mtMixTrainScheduleItemForm.setTeacherName(_teacher!=null?_teacher.toString():"");
            //MtMixTrainScheduleItemInfo
            long courseId = this.mtMixTrainScheduleItemInfoService.synCourse(mtMixTrainScheduleItemForm, orgId,eosoperator.getOperatorName());
            Course course=courseService.findCourseById(courseId);
            scheduleItemInfo.setCourseId(courseId);
            mtMixTrainScheduleItemForm.setCourseId(courseId);
            int teacherId = this.mtMixTrainScheduleItemInfoService.synTeacher(mtMixTrainScheduleItemForm, orgId,operatorId);
            if(course!=null){
                course.setTeacherId(teacherId);
                courseService.updateCourse(course);
            }
            scheduleItemInfo.setTeacherId(teacherId);
        }else if(scheduleItemInfo.getCourseId()!=-1 && Integer.parseInt(_isTrainTheme)==0 && scheduleItemInfo.getIsTrainTheme().intValue()==1){
            mtMixTrainScheduleItemForm.setCourseId(scheduleItemInfo.getCourseId());
            mtMixTrainScheduleItemForm.setTeacherId(scheduleItemInfo.getTeacherId());
            mtMixTrainScheduleItemForm.setCourseName(scheduleItemInfo.getCourseName());
            //MtMixTrainScheduleItemInfo
            this.mtMixTrainScheduleItemInfoService.dropScheduleCourseAndTeacher(mtMixTrainScheduleItemForm, 1);
            scheduleItemInfo.setCourseId(Long.valueOf(-1));
            scheduleItemInfo.setTeacherId(0);
        }
        scheduleItemInfo.setIsTrainTheme(Integer.parseInt(_isTrainTheme.toString()));
        /*FormFile file=mtScheduleItemForm.getUploadFile();
        if(file!=null&&file.getFileSize()>0){
            String fileStr=uploadFile(request,file);
            String fname=fileStr.substring(fileStr.lastIndexOf("/")+1, fileStr.length());
            scheduleItemInfo.setFilePath(fileStr);
            scheduleItemInfo.setFileName(fname);
            if(scheduleItemInfo.getCourseId()!=-1){    //将资料同步至课程资料的第三步
                UscLectureFile lectureFile=new UscLectureFile();
                lectureFile.setCourseId(scheduleItemInfo.getCourseId());
                lectureFile.setLectureName("资料");
                lectureFile.setLectureUrl(fileStr);
                this.uscLectureFileService.insertLectureFile(lectureFile);
            }
        }*/
        //MtMixtrainscheduleIteminfo
        this.mtMixTrainScheduleItemInfoService.update(scheduleItemInfo);

    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public void listScheduleTeacher(HttpServletRequest request){

        int trainId=Integer.parseInt(request.getSession().getAttribute("trainId").toString());
        TrainWithBLOBs train = this.onlineTrainingService.findById(trainId);
        int scheduleId=0;

        List<MtMixTrainScheduleTrain> mtScheduleTrainList=this.mtMixTrainScheduleTrainService.findScheduleTrainList(trainId);

        if(mtScheduleTrainList!=null&&mtScheduleTrainList.size()>0){
            scheduleId=mtScheduleTrainList.get(0).getScheduleId();
        }

        List<TchrBaseInfoForm> teacherFormList=this.mtMixTrainScheduleItemInfoService.getTeacherFormListByScheduleId(scheduleId);
        request.setAttribute("train", train);
        request.setAttribute("teacherFormList", teacherFormList);

        /*TreeMap<Integer, HashMap<String, String>> formfieldSeqsMap = this.sysFormFieldService.getSysFormfieldSeqsMap(Constants.tenantId,3);
        request.setAttribute("formfieldSeqsMap", formfieldSeqsMap);*/

    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public void listScheduleCourse(HttpServletRequest request){

        int trainId=Integer.parseInt(request.getSession().getAttribute("trainId").toString());
        TrainWithBLOBs train = this.onlineTrainingService.findById(trainId);

        int scheduleId=0;
        List<MtMixTrainScheduleTrain> mtScheduleTrainList=this.mtMixTrainScheduleTrainService.findScheduleTrainList(trainId);
        if(mtScheduleTrainList!=null&&mtScheduleTrainList.size()>0){
            scheduleId=mtScheduleTrainList.get(0).getScheduleId();
        }

        //MtMixTrainScheduleItemInfo
        List<Course> courseList = this.mtMixTrainScheduleItemInfoService.getOfflineCourseList(scheduleId);

        List<CourseFormAll> courseFormList=new ArrayList<>();

        if(courseList!=null && courseList.size()>0){
            courseFormList = this.courseService.getCourseFormList(courseList);
        }
        request.setAttribute("courseFormList", courseFormList);
        request.setAttribute("train", train);

    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public ServiceResponse loadOnlineTrainScheduleItem(HttpServletRequest request){

        EosOperator operator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        Integer operatorId = operator.getOperatorId();

        Map<String,Object> returnMap = new HashMap<>();

        Integer trainId = Integer.parseInt(request.getParameter("trainId").toString());
        TrainWithBLOBs train = this.onlineTrainingService.findById(trainId);

        //获取培训要求学时
        Double trainNeededHours = train.getNeededHours();
        returnMap.put("trainNeededHours",trainNeededHours);

        //获取线上课程分类
        Integer scheduleId=0;
        List<MtMixTrainScheduleTrain> mtScheduleTrainList=this.mtMixTrainScheduleTrainService.findScheduleTrainList(trainId);
        if(mtScheduleTrainList!=null&&mtScheduleTrainList.size()>0){
            scheduleId=mtScheduleTrainList.get(0).getScheduleId();
        }
        //因为数据库中sortLable该字段没有，所以现在暂时返回空
        List<String> sortList = this.mtMixTrainScheduleItemInfoService.ListSortByscheduleId(scheduleId);

        returnMap.put("sortList",sortList);

        //如果只有一个分类或没有分类，则查询所有
        //String sortLable;

        //该方法现在写不了，需要用到的字段数据库中没有

        /*Map<String,Object> conditions=new HashMap<>();
        conditions.put("scheduleId", scheduleId);
        conditions.put("onOrOffLineFlag", 0);
        List<MtMixTrainScheduleItemInfo> scheudleOnlineItemList = this.mtMixTrainScheduleItemInfoService.getListByScheduleIdAndOnOroffLineFlag(conditions);
        scheudleOnlineItemList = this.getMtScheduleItemInfoService().getScheduleOnlineItemList(scheudleOnlineItemList);
        BigDecimal progressFlag = new BigDecimal(0);
        if(scheudleOnlineItemList.size()>0){
            for(MtMixTrainScheduleItemInfo schedule:scheudleOnlineItemList){
                Long courseId = schedule.getCourseId();
                UcsEmployeecourse ucsEmployeecourse = getSelectCourseService().SelectUcsEmployeecourse(
                        operatorId, courseId, Integer.valueOf(String.valueOf(Tools.getCurrentYear())));
                if(ucsEmployeecourse != null ){
                    schedule.setStudyProgress(ucsEmployeecourse.getStudyProgress()+"");

                    //gss--add--2019年6月25日11:13:49
                    //要求取消学到80%更新学时。
                    //if(ucsEmployeecourse.getStudyProgress()!=null&&ucsEmployeecourse.getStudyProgress()>=80){
                    progressFlag = this.updataOnlineTrainStudyHours(ucsEmployeecourse,progressFlag,schedule);

                }else{
                    schedule.setStudyProgress("0.0");
                }
                Course course = this.getCourseService().findCourseById(courseId);
                if(course.getIsCoursePackage() == 1 && course.getIsFirstLevelDirectory() == 1){
                    Map<String, Object> courseMap = new HashMap<String, Object>();
                    courseMap.put("courseId", courseId);
                    List<Chapter> list = getChapterService().findByCourseId(courseMap);
                    if(list.size()>0){
                        schedule.setCourseId(list.get(0).getSubset().get(0).getCourseId());
                    }
                }
            }
            //更新学时统计
            List<MtMixTrainUserTrainInfo> listMtMixTrainUserTrainInfo = this.getMtMixTrainUserTrainInfoDAO().findByTrainAndOperator(trainId, operator.getOperatorId());
            listMtMixTrainUserTrainInfo.get(0).setStatistics(progressFlag+"");
            this.getMtMixTrainUserTrainInfoDAO().update(listMtMixTrainUserTrainInfo.get(0));


            result.append("\"courseList\":").append(BeanToJSONTool.getJSONList(scheudleOnlineItemList)).append(",");
        }
        result.append("\"learnedHours\":").append(progressFlag);
        */
        return ServiceResponse.createBySuccess();
    }






}
