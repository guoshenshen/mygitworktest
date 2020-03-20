package com.elearning.service.mixtraining;

import com.elearning.common.*;
import com.elearning.dao.mixtraining.MtMixTrainUserTrainInfoMapper;
import com.elearning.pojo.courseStudy.*;
import com.elearning.pojo.examManage.ExamExamInfo;
import com.elearning.pojo.mixtraining.MtMixTrainUserTrainInfo;
import com.elearning.pojo.pub.*;
import com.elearning.pojo.statistics.UscUserRecordStatics;
import com.elearning.service.courseStudy.ITrainSummaryService;
import com.elearning.service.courseStudy.IUserNeedLearnCourseService;
import com.elearning.service.courseStudy.IUserTrainService;
import com.elearning.service.coursemanage.ICourseTypeService;
import com.elearning.service.examManage.IExamExamInfoService;
import com.elearning.service.integralTask.IIntegralService;
import com.elearning.service.onlinetraining.IOnlineTrainingService;
import com.elearning.service.pub.*;
import com.elearning.service.statistics.IUscUserLearnStaticsService;
import com.elearning.service.statistics.IUscUserRecordStaticsService;
import com.elearning.vo.BasicUserForm;
import com.elearning.vo.mixtraining.MtMixTrainUserTrainInfoForm;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 〈<br>
 * 〈〉
 *
 * @author lxx
 * @create 2019/7/31 10:09
 */
@Service("mtMixTrainUserTrainInfoService")
public class MtMixTrainUserTrainInfoService implements IMtMixTrainUserTrainInfoService{


    @Autowired
    private MtMixTrainUserTrainInfoMapper mtMixTrainUserTrainInfoMapper;

    @Autowired
    private IOnlineTrainingService onlineTrainingService;

    @Autowired
    private IDDictionaryService dictionaryService;

    @Autowired
    private IEosorgTEmployeeService eosorgTEmployeeService;

    @Autowired
    private IEosOperatorService eosOperatorService;

    @Autowired
    private IEosorgTOrganizationService eosorgTOrganizationService;

    @Autowired
    private ITrainSummaryService trainSummaryService;

    @Autowired
    private IUscUserRecordStaticsService uscUserRecordStaticsService;

    @Autowired
    private IUscUserLearnStaticsService uscUserLearnStaticsService;

    @Autowired
    private IUserTrainService userTrainService;

    @Autowired
    private IUserNeedLearnCourseService userNeedLearnCourseService;

    @Autowired
    private IExamExamInfoService examExamInfoService;

    @Autowired
    private IMtMixTrainScheduleItemInfoService mtMixTrainScheduleItemInfoService;

    @Autowired
    private IIntegralService integralService;

    @Autowired
    private ICourseTypeService courseTypeService;

    @Autowired
    private ICourseService courseService;


    private MailSender mailSender;



    @Override
    public int save(MtMixTrainUserTrainInfo record){
        return this.mtMixTrainUserTrainInfoMapper.insertSelective(record);
    }

    @Override
    public int updateByPrimaryKeySelective(MtMixTrainUserTrainInfo record){
        return this.mtMixTrainUserTrainInfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public int getUserApplyTrainStatus(Integer trainId,Integer operatorId){

        int userApplyTrainStatus=0;
        Date date=new Date();

        //TrainWithBLOBs train=this.trainDAO.findById(trainId);
        TrainWithBLOBs train=this.onlineTrainingService.findById(trainId);
        List<MtMixTrainUserTrainInfo> _userTrainList=this.mtMixTrainUserTrainInfoMapper.findByUserId(operatorId);
        if(_userTrainList!=null && _userTrainList.size()>0){
            for(MtMixTrainUserTrainInfo mtInfo:_userTrainList){
                if(mtInfo.getTrainID()!=null && mtInfo.getTrainID().intValue()==trainId){
                    if(mtInfo.getStatus()!=null&&mtInfo.getStatus().intValue()==1051){
                        userApplyTrainStatus=1;  //未审批
                    } else if(mtInfo.getStatus()!=null&&mtInfo.getStatus().intValue()==1053){
                        userApplyTrainStatus=3;  //审核未通过
                    } else if(mtInfo.getStatus()!=null&&mtInfo.getStatus().intValue()==1052){
                        userApplyTrainStatus=2; //审核通过
                    }
                    break;
                }
            }
            if(userApplyTrainStatus==0){
                if(train.getIsEnrolled()!=null && train.getIsEnrolled()){
                    if(train.getProgramStartTime().equals(train.getProgramEndTime())){
                        //尚未设置报名起止时间
                        userApplyTrainStatus=7;
                    } else if(date.before(train.getProgramStartTime())){
                        //报名尚未开始
                        userApplyTrainStatus=5;
                    } else if(date.after(train.getProgramEndTime())){
                        //报名已经结束
                        userApplyTrainStatus=6;
                    } else{
                        //正在报名
                        userApplyTrainStatus=4;
                    }
                } else{
                    //不允许报名
                    userApplyTrainStatus=8;
                }
            }
        }else{
            if(train.getIsEnrolled()!=null && train.getIsEnrolled()){
                if(train.getProgramStartTime()==null||train.getProgramStartTime().equals(train.getProgramEndTime())){
                    //尚未设置报名起止时间
                    userApplyTrainStatus=7;
                } else if(date.before(train.getProgramStartTime())){
                    //报名尚未开始
                    userApplyTrainStatus=5;
                } else if(date.after(train.getProgramEndTime())){
                    //报名已经结束
                    userApplyTrainStatus=6;
                } else{
                    //正在报名
                    userApplyTrainStatus=4;
                }
            } else{
                //不允许报名
                userApplyTrainStatus=8;
            }
        }
        return userApplyTrainStatus;
    }

    @Override
    public List<MtMixTrainUserTrainInfo> getListByUserIDAndTrainID(Map<String,Object> parm){

        return this.mtMixTrainUserTrainInfoMapper.getListByUserIDAndTrainID(parm);
    }

    @Override
    public List<MtMixTrainUserTrainInfo> getListByUserIDAndTrainIDAndStatus(Map<String,Object> parm){

        return this.mtMixTrainUserTrainInfoMapper.getListByUserIDAndTrainIDAndStatus(parm);
    }

    @Override
    public List<MtMixTrainUserTrainInfo> getListByMap(Map<String,Object> parm){

        return this.mtMixTrainUserTrainInfoMapper.getListByMap(parm);
    }

    @Override
    public MtMixTrainUserTrainInfo findById(Integer id){

        return this.mtMixTrainUserTrainInfoMapper.selectByPrimaryKey(id);
    }


    @Override
    @Transactional(rollbackFor = {Exception.class })
    public ServiceResponse searchMtMixTrainUserTrainInfoList(Map<String,Object> queryConditionMap,HttpServletRequest request){

        PageHelper.startPage((Integer) queryConditionMap.get("startIndex"),(Integer) queryConditionMap.get("count"));

        //根据查询条件进行查询
        //根据筛选条件查询实际参会人员
        List<MtMixTrainUserTrainInfo> mtMixTrainUserTrainInfoList = this.mtMixTrainUserTrainInfoMapper.searchMtMixTrainUserTrainInfoList(queryConditionMap);

        PageInfo pageInfo = new PageInfo(mtMixTrainUserTrainInfoList);
        pageInfo.setList(mtMixTrainUserTrainInfoList);

        mtMixTrainUserTrainInfoList = pageInfo.getList();

        //对参会人员信息进行格式化
        List<MtMixTrainUserTrainInfoForm> mixTrainUserInfoFormList = new ArrayList<>();

        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);

        for(MtMixTrainUserTrainInfo mtMixTrainUserTrainInfo : mtMixTrainUserTrainInfoList){

            MtMixTrainUserTrainInfoForm _mtMixTrainUserTrainInfoForm = this.getMtMixTrainUserTrainInfoForm(mtMixTrainUserTrainInfo,eosoperator.getTenantId());
            mixTrainUserInfoFormList.add(_mtMixTrainUserTrainInfoForm);

        }

        pageInfo.setList(mixTrainUserInfoFormList);

        //request.setAttribute("mixTrainUserInfoFormList", mixTrainUserInfoFormList);
        //将选择的参会人员放入表中，用于导出参会人员
        //request.getSession().setAttribute("4", mixTrainUserInfoFormList);

        return ServiceResponse.createBySuccess(pageInfo);
    }

    public MtMixTrainUserTrainInfoForm getMtMixTrainUserTrainInfoForm(MtMixTrainUserTrainInfo mtMixTrainUserTrainInfo, Integer tenantId) {
        MtMixTrainUserTrainInfoForm mtMixTrainUserTrainInfoForm = new MtMixTrainUserTrainInfoForm();
        if(mtMixTrainUserTrainInfo.getID()!=null){
            mtMixTrainUserTrainInfoForm.setId(mtMixTrainUserTrainInfo.getID());
        }
        if(mtMixTrainUserTrainInfo.getAttendable()!=null){
            if(mtMixTrainUserTrainInfo.getAttendable().intValue()==-1){
                mtMixTrainUserTrainInfoForm.setAttendableName("未回执");
            } else if(mtMixTrainUserTrainInfo.getAttendable().intValue()==0){
                mtMixTrainUserTrainInfoForm.setAttendableName("不参加");
            } else if(mtMixTrainUserTrainInfo.getAttendable().intValue()==1){
                mtMixTrainUserTrainInfoForm.setAttendableName("参加");
            }
        }
        if(mtMixTrainUserTrainInfo.getClassHour()!=null){
            mtMixTrainUserTrainInfoForm.setClassHour(mtMixTrainUserTrainInfo.getClassHour());
        }
        if(mtMixTrainUserTrainInfo.getAttended()!=null){
            if(mtMixTrainUserTrainInfo.getAttended().intValue()==0){
                mtMixTrainUserTrainInfoForm.setAttendedName("否");
            } else if(mtMixTrainUserTrainInfo.getAttended().intValue()==1){
                mtMixTrainUserTrainInfoForm.setAttendedName("是");
            }
        }
        if(mtMixTrainUserTrainInfo.getHurrySummary()!=null){
            if(mtMixTrainUserTrainInfo.getHurrySummary().intValue()==0){
                mtMixTrainUserTrainInfoForm.setHurrySummaryName("否");
            } else if(mtMixTrainUserTrainInfo.getHurrySummary().intValue()==1){
                mtMixTrainUserTrainInfoForm.setHurrySummaryName("是");
            }
        }else{
            mtMixTrainUserTrainInfoForm.setHurrySummaryName("否");
        }

        if(mtMixTrainUserTrainInfo.getAttendType()!=null){
            String attendTypeName = this.dictionaryService.getDDictionaryMapperByCode(mtMixTrainUserTrainInfo.getAttendType().toString(), "2500").getName();
            mtMixTrainUserTrainInfoForm.setAttendTypeName(attendTypeName);
        }
        if(mtMixTrainUserTrainInfo.getRemark()!=null){
            mtMixTrainUserTrainInfoForm.setRemark(mtMixTrainUserTrainInfo.getRemark());
        }
        if(mtMixTrainUserTrainInfo.getScore()!=null){
            mtMixTrainUserTrainInfoForm.setScore(mtMixTrainUserTrainInfo.getScore());
        }
        if(mtMixTrainUserTrainInfo.getStatus()!=null){
            String statusName = this.dictionaryService.getDDictionaryByCodeAndParentCode(mtMixTrainUserTrainInfo.getStatus().toString(), "1050");
            mtMixTrainUserTrainInfoForm.setStatusName(statusName);
        }
        if(mtMixTrainUserTrainInfo.getTrainID()!=null){
            mtMixTrainUserTrainInfoForm.setTrainId(mtMixTrainUserTrainInfo.getTrainID());
        }

        Integer operatorId = mtMixTrainUserTrainInfo.getUserID();
        EosorgTEmployee employee = this.eosorgTEmployeeService.findById(operatorId);
        EosorgTOrganization org=this.eosorgTOrganizationService.getEosorgTOrganizationById(employee.getOrgID());
        EosOperator operator = this.eosOperatorService.findById(operatorId);
        mtMixTrainUserTrainInfoForm.setUserName(operator.getOperatorName());
        mtMixTrainUserTrainInfoForm.setUserId(operator.getOperatorId());
        mtMixTrainUserTrainInfoForm.setEmail(employee.getOEmail());
        String parentOrgName="";
        String orgName="";
        if(tenantId!=null){
            if(tenantId.equals(1)){
                EosorgTOrganization secondaryOrg = this.eosorgTOrganizationService.findById(this.eosorgTOrganizationService.findOrgId(employee.getOrgID()));
                parentOrgName=secondaryOrg.getOrgName();
            } else{
                parentOrgName=this.eosorgTOrganizationService.getTenantOrgName(org);
            }
            orgName=this.eosorgTOrganizationService.getStandardOrgName(org, tenantId);
        } else{
            parentOrgName=this.eosorgTOrganizationService.getTenantOrgName(org);
            orgName=parentOrgName;
        }
        mtMixTrainUserTrainInfoForm.setParentOrgName(parentOrgName);
        mtMixTrainUserTrainInfoForm.setOrgName(orgName);

        Map<String,Object> parmMap = new HashMap<>();
        parmMap.put("operatorId",operatorId);
        parmMap.put("train_id",mtMixTrainUserTrainInfo.getTrainID());

        if(this.trainSummaryService.findByExample(parmMap)!=null && this.trainSummaryService.findByExample(parmMap).size()>0){
            TrainSummary _trainSummary = this.trainSummaryService.findByExample(parmMap).get(0);
            mtMixTrainUserTrainInfoForm.setTrainSummaryId(_trainSummary.getId());
        }

        return mtMixTrainUserTrainInfoForm;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public Integer update(MtMixTrainUserTrainInfoForm mtMixTrainUserTrainInfoForm,HttpServletRequest request){

        String year = request.getSession().getAttribute(Constants.YEAR_KEY).toString();

        MtMixTrainUserTrainInfo mtMixTrainUserTrainInfo = this.mtMixTrainUserTrainInfoMapper.selectByPrimaryKey(mtMixTrainUserTrainInfoForm.getId());

        TrainWithBLOBs train = this.onlineTrainingService.findById(mtMixTrainUserTrainInfo.getTrainID());

        UscUserRecordStatics uscUserrecordstatics = new UscUserRecordStatics();
        uscUserrecordstatics.setTrainId(train.getID());
        uscUserrecordstatics.setOperatorId(mtMixTrainUserTrainInfo.getUserID());

        if(mtMixTrainUserTrainInfoForm.getClassHour()!=null && mtMixTrainUserTrainInfoForm.getClassHour()>=0){
            Double oldClassHour = 0.0;
            if(mtMixTrainUserTrainInfo.getClassHour()!=null){
                oldClassHour = mtMixTrainUserTrainInfo.getClassHour();
            }
            Double addedClassHour = mtMixTrainUserTrainInfoForm.getClassHour()-oldClassHour;
            this.uscUserRecordStaticsService.averageDistributeTimeToMonth(mtMixTrainUserTrainInfo.getUserID(), Integer.valueOf(year), train.getStartTime(),train.getEndTime(),addedClassHour);
            mtMixTrainUserTrainInfo.setClassHour(mtMixTrainUserTrainInfoForm.getClassHour());

            //同时需要修改usc_userrecordstatics表
            Map<String,Object> parmMap = new HashMap<>();
            parmMap.put("trainId",train.getID());
            parmMap.put("operatorId",mtMixTrainUserTrainInfo.getUserID());

            List<UscUserRecordStatics> uscUserrecordstaticsList = this.uscUserRecordStaticsService.findByExample(parmMap);
            if(uscUserrecordstaticsList==null || uscUserrecordstaticsList.size()==0){
                EosOperator operator = this.eosOperatorService.findById(mtMixTrainUserTrainInfo.getUserID());
                if(operator!=null){
                    uscUserrecordstatics.setOperatorName(operator.getOperatorName());
                    EosorgTEmployee employee = operator.getEosorgTEmployee();
                    if(employee!=null){
                        Integer isKeyFigure = 0;
                        if(employee.getIsKeyFigure()!=null){
                            isKeyFigure = employee.getIsKeyFigure();
                        }
                        uscUserrecordstatics.setIsKeyFigure(isKeyFigure);
                        if(employee.getEmpCode()!=null){
                            uscUserrecordstatics.setEmpCode(employee.getEmpCode());
                        }
                        uscUserrecordstatics.setClassHour(mtMixTrainUserTrainInfoForm.getClassHour());
                        uscUserrecordstatics.setStartTime(train.getStartTime());
                        uscUserrecordstatics.setEndTime(train.getEndTime());
                        uscUserrecordstatics.setTenantId(this.eosorgTOrganizationService.findTenantId(employee.getOrgID()));
                        uscUserrecordstatics.setTrainCode(train.getTrainID());
                        uscUserrecordstatics.setTrainName(train.getTrainName());
                        uscUserrecordstatics.setYear(train.getYear());
                        uscUserrecordstatics.setTrainTypeName(this.dictionaryService.getDDictionaryByCodeAndParentCode(train.getTrainTypeID().toString(), "2100"));
                        this.uscUserRecordStaticsService.insertSelective(uscUserrecordstatics);
                    }
                }
            }else{
                UscUserRecordStatics uscUserrecordstaticsTemp = uscUserrecordstaticsList.get(0);
                uscUserrecordstaticsTemp.setClassHour(mtMixTrainUserTrainInfoForm.getClassHour());
                this.uscUserRecordStaticsService.updateByPrimaryKeySelective(uscUserrecordstaticsTemp);
            }
        }
        if(mtMixTrainUserTrainInfoForm.getRemark()!=null){
            mtMixTrainUserTrainInfo.setRemark(mtMixTrainUserTrainInfoForm.getRemark());
        }
        mtMixTrainUserTrainInfo.setHurrySummary(mtMixTrainUserTrainInfoForm.getHurrySummary());
        int n = this.mtMixTrainUserTrainInfoMapper.updateByPrimaryKey(mtMixTrainUserTrainInfo);
        request.setAttribute("userTrainInfo",mtMixTrainUserTrainInfo);

        return n;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public int delete(HttpServletRequest request){
        String year = request.getSession().getAttribute(Constants.YEAR_KEY).toString();
        //获取该培训班对应的编号
        Integer trainId = Integer.valueOf(request.getSession().getAttribute("trainId").toString());
        TrainWithBLOBs train = this.onlineTrainingService.findById(trainId);
        Integer flag = 1; //判断是否显示“审核”和“删除”按钮

        int n = 0;

        request.setAttribute("flag", flag);
        if(request.getParameterValues("userTrainInfoId") != null){
            String[] deleteList= request.getParameterValues("userTrainInfoId");
            if(deleteList!=null&&deleteList.length>0){
                for(String id : deleteList) {
                    //删除usertrain表中对应于该学员的记录
                    //删除mtMixtrainUserTrainInfo表中对应于该学员的记录
                    //若该学员删除的记录中已经有学时记录则修改学时累计表中的学时数据
                    MtMixTrainUserTrainInfo mtMixTrainUserTrainInfo = this.mtMixTrainUserTrainInfoMapper.selectByPrimaryKey(Integer.parseInt(id));
                    if(mtMixTrainUserTrainInfo.getClassHour()!=null){
                        this.uscUserLearnStaticsService.averageDistributeTimeToMonth(mtMixTrainUserTrainInfo.getUserID(), Integer.valueOf(year), train.getStartTime(),train.getEndTime(),-mtMixTrainUserTrainInfo.getClassHour());
                    }
                    //this.mtMixTrainUserTrainInfoService.delete(mtMixTrainUserTrainInfo);
                    n = this.mtMixTrainUserTrainInfoMapper.deleteByPrimaryKey(mtMixTrainUserTrainInfo.getID());

                    MtMixTrainUserTrainInfo userRecord=new MtMixTrainUserTrainInfo();
                    userRecord.setUserID(mtMixTrainUserTrainInfo.getUserID());
                    userRecord.setTrainID(trainId);

                    Map<String,Object> parmMap = new HashMap<>();
                    parmMap.put("userID",mtMixTrainUserTrainInfo.getUserID());
                    parmMap.put("trainID",trainId);

                    List<MtMixTrainUserTrainInfo> infos=this.mtMixTrainUserTrainInfoMapper.getListByUserIDAndTrainID(parmMap);
                    if(infos.size()>0){
                        //说明该学员在某培训班中因各种原因被多次录入，在这种情况下不可以删除该培训班中该学员对应的usertrain记录（usertrain记录仅有一条）
                        boolean deleteFlag=true;
                        for(MtMixTrainUserTrainInfo info:infos){
                            if(info.getAttended()!=null&&info.getAttended().equals(1)){
                                deleteFlag=false;
                                break;
                            }
                        }
                        if(deleteFlag==true){
                            //usc_userrecordstatics：学员档案中显示培训信息记录,一个培训班对应于一个学生的只有一条，且在确认参加某会议后显示
                            //删除表usc_userrecordstatics中数据
                            UscUserRecordStatics uscUserrecordstatics = new UscUserRecordStatics();
                            uscUserrecordstatics.setTrainId(train.getID());
                            uscUserrecordstatics.setOperatorId(mtMixTrainUserTrainInfo.getUserID());

                            Map<String,Object> parM = new HashMap<>();
                            parM.put("trainId",train.getID());
                            parM.put("operatorId",mtMixTrainUserTrainInfo.getUserID());

                            List<UscUserRecordStatics> userrecordstatics=this.uscUserRecordStaticsService.findByExample(parM);
                            if(userrecordstatics!=null && userrecordstatics.size()>0){
                                n = this.uscUserRecordStaticsService.deleteByPrimaryKey(userrecordstatics.get(0).getRecordId());
                            }
                        }
                    } else{
                        Integer operatorId = mtMixTrainUserTrainInfo.getUserID();
                        UserTrainKey usertrainId = new UserTrainKey(operatorId,trainId,year);

                        //删除usertrain表和userneedlearncourse表中数据
                        if(this.userTrainService.selectByPrimaryKey(usertrainId)!=null){
                            UserTrain usertrain = this.userTrainService.selectByPrimaryKey(usertrainId);
                            n = this.userTrainService.deleteByTrainID(usertrain.getTrainId());
                            this.userNeedLearnCourseService.deleteByOperatorIdTrainIdYear(operatorId, trainId, year);
                        }

                        //usc_userrecordstatics：学员档案中显示培训信息记录,一个培训班对应于一个学生的只有一条，且在确认参加某会议后显示
                        //删除表usc_userrecordstatics中数据

                        Map<String,Object> parm = new HashMap<>();
                        parm.put("trainId",train.getID());
                        parm.put("operatorId",mtMixTrainUserTrainInfo.getUserID());
                        List<UscUserRecordStatics> userrecordstatics = this.uscUserRecordStaticsService.findByExample(parm);

                        if(userrecordstatics!=null && userrecordstatics.size()>0){
                            n = this.uscUserRecordStaticsService.updateByPrimaryKeySelective(userrecordstatics.get(0));
                        }
                    }
                }
            }
        }
        return n;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public void updateattended(HttpServletRequest request){

        String year = request.getSession().getAttribute(Constants.YEAR_KEY).toString();
        Integer attended = Integer.valueOf(request.getParameter("flag").toString());
        if(request.getParameterValues("userTrainInfoId") != null){
            String[] updateList= request.getParameterValues("userTrainInfoId");
            if(updateList!=null && updateList.length>0){
                for(String id : updateList) {
                    MtMixTrainUserTrainInfo mtMixTrainUserTrainInfo = this.mtMixTrainUserTrainInfoMapper.selectByPrimaryKey(Integer.parseInt(id));
                    TrainWithBLOBs train = this.onlineTrainingService.findById(mtMixTrainUserTrainInfo.getTrainID());
                    Double classHour = train.getClassHour();
                    if(classHour <= 0){
                        continue;
                    }
                    if(mtMixTrainUserTrainInfo.getClassHour()==null||(double)mtMixTrainUserTrainInfo.getClassHour()==0){
                        //将最终参会数据存储到表usc_userlearnstatics
                        this.uscUserLearnStaticsService.averageDistributeTimeToMonth(mtMixTrainUserTrainInfo.getUserID(), Integer.valueOf(year), train.getStartTime(),train.getEndTime(),classHour);
                        mtMixTrainUserTrainInfo.setClassHour(classHour);
                    }

                    mtMixTrainUserTrainInfo.setAttended(attended);
                    this.mtMixTrainUserTrainInfoMapper.updateByPrimaryKeySelective(mtMixTrainUserTrainInfo);
                    /**
                     * 确认学时，学员获取积分
                     */
                    this.integralService.addInnerAndOuterTrainIntegral(mtMixTrainUserTrainInfo,train);

                    //将最终参会数据存储到表usc_userrecordstatics
                    UscUserRecordStatics uscUserrecordstatics = new UscUserRecordStatics();
                    uscUserrecordstatics.setTrainId(train.getID());
                    uscUserrecordstatics.setOperatorId(mtMixTrainUserTrainInfo.getUserID());

                    Map<String,Object> parmMap = new HashMap<>();
                    parmMap.put("trainId",train.getID());
                    parmMap.put("operatorId",mtMixTrainUserTrainInfo.getUserID());


                    if(this.uscUserRecordStaticsService.findByExample(parmMap).size()==0){
                        EosOperator operator = this.eosOperatorService.findById(mtMixTrainUserTrainInfo.getUserID());
                        if(operator!=null){
                            String operatorName = operator.getOperatorName();
                            uscUserrecordstatics.setOperatorName(operatorName);
                            EosorgTEmployee employee = operator.getEosorgTEmployee();
                            if(employee!=null){
                                Integer isKeyFigure = 0;
                                if(employee.getIsKeyFigure()!=null){
                                    isKeyFigure = employee.getIsKeyFigure();
                                }
                                uscUserrecordstatics.setIsKeyFigure(isKeyFigure);
                                if(employee.getEmpCode()!=null){
                                    uscUserrecordstatics.setEmpCode(employee.getEmpCode());
                                }
                                uscUserrecordstatics.setClassHour(mtMixTrainUserTrainInfo.getClassHour());
                                uscUserrecordstatics.setStartTime(train.getStartTime());
                                uscUserrecordstatics.setEndTime(train.getEndTime());
                                uscUserrecordstatics.setTenantId(this.eosorgTOrganizationService.findTenantId(employee.getOrgID()));
                                uscUserrecordstatics.setTrainCode(train.getTrainID());
                                uscUserrecordstatics.setTrainName(train.getTrainName());
                                uscUserrecordstatics.setYear(train.getYear());
                                uscUserrecordstatics.setTrainTypeName(this.dictionaryService.getDDictionaryByCodeAndParentCode(train.getTrainTypeID().toString(), "2100"));
                                this.uscUserRecordStaticsService.insertSelective(uscUserrecordstatics);
                                Long recordId = uscUserrecordstatics.getRecordId();
                                StringBuffer achievement = new StringBuffer();
                                List<ExamExamInfo> examExamInfoList = this.examExamInfoService.getListByTrainId(train.getID());
                                for(ExamExamInfo exam:examExamInfoList){
                                    Map<String,Object> map = new HashMap<>();
                                    map.put("examId", exam.getID());
                                    map.put("employeeId", mtMixTrainUserTrainInfo.getUserID());

                                    if(this.examExamInfoService.findMaxScoreForUniteTest(map).doubleValue()!=0.00){
                                        achievement.append(exam.getExamTitle())
                                                .append("成绩")
                                                .append(this.examExamInfoService.findMaxScoreForUniteTest(map).doubleValue())
                                                .append("分||");
                                    }
                                }
                                UscUserRecordStatics temp = this.uscUserRecordStaticsService.selectByPrimaryKey(recordId);
                                temp.setAchievement(achievement.toString());
                                this.uscUserRecordStaticsService.updateByPrimaryKeySelective(temp);

                                //系统暂时无法判断自测成绩是否来源于培训中的课程自测，所以暂时不做这块开发
                            }
                        }
                    }else{
                        UscUserRecordStatics u = this.uscUserRecordStaticsService.findByExample(parmMap).get(0);
                        u.setClassHour(mtMixTrainUserTrainInfo.getClassHour());
                        this.uscUserRecordStaticsService.updateByPrimaryKeySelective(u);
                    }

                    Integer trainId = mtMixTrainUserTrainInfo.getTrainID();
                    Integer operatorId = mtMixTrainUserTrainInfo.getUserID();
                    UserTrainKey usertrainId = new UserTrainKey(operatorId,trainId,year);
                    if(this.userTrainService.selectByPrimaryKey(usertrainId) == null){
                        String trainName = train.getTrainName();
                        Integer trainWay = train.getTrainWay();
                        String location = "";
                        if(train.getLocation()!=null){
                            location = train.getLocation();
                        }
                        Integer attendantCount = train.getAttendantCount();
                        Date startTime = train.getStartTime();
                        Date endTime = train.getEndTime();
                        String sponsorName = train.getSponsorName();

                        UserTrain usertrain = new UserTrain();
                        usertrain.setOperatorID(operatorId);
                        usertrain.setTrainId(trainId);
                        usertrain.setYear(year);
                        usertrain.setTrainName(trainName);
                        usertrain.setTrainWay(trainWay);
                        usertrain.setLocation(location);
                        usertrain.setAttendantCount(attendantCount);
                        usertrain.setStartTime(startTime);
                        usertrain.setEndTime(endTime);
                        usertrain.setIsStationTrain(new Byte("0"));
                        usertrain.setSponsorName(sponsorName);

                        this.userTrainService.save(usertrain);

                        List<Course> courseList = this.mtMixTrainScheduleItemInfoService.getCourseListByTrainId(trainId);
                        for(Course _course : courseList){
                            String courseTypeName = this.courseTypeService.findByCourseId(_course.getCourseId()).getCourseTypeName();

                            UserNeedLearnCourseKey userneedlearncourseId = new UserNeedLearnCourseKey(operatorId,trainId,year,0,_course.getCourseId());
                            UserNeedLearnCourse userneedlearncourse = this.userNeedLearnCourseService.selectByPrimaryKey(userneedlearncourseId);
                            if(userneedlearncourse == null){
                                userneedlearncourse = new UserNeedLearnCourse(operatorId,trainId,year,0,_course.getCourseId(),
                                        trainName,Byte.valueOf("0"),"",_course.getCourseName(),_course.getClassHour(),courseTypeName);
                                this.userNeedLearnCourseService.insert(userneedlearncourse);
                                int selectedTimes=0;
                                if(_course.getSelectedTimes()!=null){
                                    selectedTimes=_course.getSelectedTimes();
                                }
                                _course.setSelectedTimes(selectedTimes+1);
                                this.courseService.updateCourse(_course);
                            }else{
                                userneedlearncourse.setTrainName(trainName);
                                userneedlearncourse.setIsStationTrain(Byte.valueOf("0"));
                                userneedlearncourse.setSectionName("");
                                userneedlearncourse.setCourseName(_course.getCourseName());
                                userneedlearncourse.setClassHour(_course.getClassHour());
                                userneedlearncourse.setCourseType(courseTypeName);
                                this.userNeedLearnCourseService.updateByPrimaryKey(userneedlearncourse);
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public void addAttendedUsers(HttpServletRequest request){

        String year = request.getSession().getAttribute(Constants.YEAR_KEY).toString();
        EosOperator eosoperator = (EosOperator) request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator==null){
            throw new RuntimeException("账号已登场");
        }
        Integer trainId = null;
        trainId = Integer.parseInt(request.getParameter("trainId"));
        TrainWithBLOBs train = this.onlineTrainingService.findById(trainId);
        String[] operatorIdStrList=request.getParameterValues("memberId");

        if(operatorIdStrList != null && operatorIdStrList.length>0){
            List<Integer> operatorIdList=new ArrayList<>();
            for(String operatorIdStr:operatorIdStrList){
                try {
                    operatorIdList.add(Integer.parseInt(operatorIdStr));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    continue;
                }
            }
            List<BasicUserForm> userList=new ArrayList<>();

            userList=this.eosorgTEmployeeService.findBasicUserInfoById(operatorIdList, eosoperator.getTenantId());
            for(BasicUserForm user:userList){
                MtMixTrainUserTrainInfo example = new MtMixTrainUserTrainInfo();
                example.setTrainID(trainId);
                example.setUserID(user.getOperatorId());

                Map<String,Object> map = new HashMap<>();
                map.put("trainID",trainId);
                map.put("userID",user.getOperatorId());


                //只有在该培训学员登记中尚未录入该学员信息时才进行添加
                if(this.mtMixTrainUserTrainInfoMapper.getListByUserIDAndTrainID(map).size()==0){
                    MtMixTrainUserTrainInfo uti = new MtMixTrainUserTrainInfo();
                    uti.setTrainID(trainId);
                    uti.setUserID(user.getOperatorId());
                    uti.setUserName(user.getOperatorName());
                    uti.setAttended(1);
                    uti.setAttendType(2503);
                    uti.setStatus(1052);
                    String parentOrgName = CacheUtils.getTenantName(user.getTenantId());
                    String orgName = user.getOrgName();
                    uti.setOrgName(orgName);
                    uti.setParentOrgName(parentOrgName);
                    try {
                        this.ifSaveOrUpdate(uti);
                    } catch (Exception e) {
                        e.printStackTrace();
                        continue;
                    }
                    /**
                     * 确认学时，学员获取积分
                     */
                    this.integralService.addInnerAndOuterTrainIntegral(uti,train);
                }

                UserTrainKey usertrainId = new UserTrainKey(user.getOperatorId(),trainId,year);
                if(this.userTrainService.selectByPrimaryKey(usertrainId) == null){
                    String trainName = train.getTrainName();
                    Integer trainWay = train.getTrainWay();
                    String location = "";
                    if(train.getLocation()!=null){
                        location = train.getLocation();
                    }
                    Integer attendantCount = train.getAttendantCount();
                    Date startTime = train.getStartTime();
                    Date endTime = train.getEndTime();
                    Byte isStationTrain = train.getIsStationTrain()==true?Byte.valueOf("1"):Byte.valueOf("0");
                    String sponsorName = train.getSponsorName();
                    UserTrain usertrain = new UserTrain(user.getOperatorId(),trainId,year,trainName,trainWay,location,attendantCount,startTime,endTime,isStationTrain,sponsorName);
                    try {
                        this.userTrainService.save(usertrain);
                    } catch (Exception e) {
                        e.printStackTrace();
                        continue;
                    }
                }
            }
        }
    }

    /**
     * 一个培训班仅应当包含一个学员的单条记录，不管该学员是以何种形式身份参与的
     * 如果插入学员到某个培训班前，该培训班下已经有该学员的记录，则对该记录进行更新，不做插入处理
     * @param transientInstance
     */
    @Transactional(rollbackFor = {Exception.class })
    public void ifSaveOrUpdate(MtMixTrainUserTrainInfo transientInstance){
        Integer operatorId=transientInstance.getUserID();
        Integer trainId=transientInstance.getTrainID();
        if(operatorId==null || trainId==null){
            System.out.println("mtmixtrainUserTrainInfo created failed caused by null argument");
        }
        List<MtMixTrainUserTrainInfo> infos=this.findByTrainAndOperator(trainId, operatorId);
        if(infos!=null && infos.size()>0){
            //update
            for(MtMixTrainUserTrainInfo info:infos){
                if(info.getAttended()!=null&&info.getAttended()==1){
                    //已经确认学时的学员，不再进行更新操作
                }
                else{
                    info.setAttendable(transientInstance.getAttendable());
                    info.setAttendType(transientInstance.getAttendType());
                    info.setStatus(transientInstance.getStatus());
                    info.setOrgName(transientInstance.getOrgName());
                    info.setParentOrgName(transientInstance.getParentOrgName());
                    info.setUserName(transientInstance.getUserName());
                    info.setClassHour(transientInstance.getClassHour());
                    info.setScore(transientInstance.getScore());
                    info.setSection_id(transientInstance.getSection_id());
                    this.mtMixTrainUserTrainInfoMapper.updateByPrimaryKeySelective(info);
                }
            }
        }
        else{
            this.mtMixTrainUserTrainInfoMapper.insertSelective(transientInstance);
        }
    }

    public List<MtMixTrainUserTrainInfo> findByTrainAndOperator(Integer trainId,Integer operatorId){
        Map<String,Object> map = new HashMap<>();
        map.put("userId",operatorId);
        map.put("trainId",trainId);

        List<MtMixTrainUserTrainInfo> results = this.mtMixTrainUserTrainInfoMapper.getListByUserIDAndTrainID(map);
        return results;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public void updatestatus(HttpServletRequest request){

        String year = request.getSession().getAttribute(Constants.YEAR_KEY).toString();
        Integer status = Integer.valueOf(request.getParameter("flag").toString());
        if(request.getParameterValues("userTrainInfoId") != null){
            String[] updateList= request.getParameterValues("userTrainInfoId");
            if(updateList!=null&&updateList.length>0){
                for(String id : updateList) {
                    MtMixTrainUserTrainInfo mtMixTrainUserTrainInfo = this.mtMixTrainUserTrainInfoMapper.selectByPrimaryKey(Integer.parseInt(id));
                    mtMixTrainUserTrainInfo.setStatus(status);
                    if(status==1053){
                        mtMixTrainUserTrainInfo.setAttended(0);
                    }
                    this.mtMixTrainUserTrainInfoMapper.updateByPrimaryKeySelective(mtMixTrainUserTrainInfo);

                    Integer trainId = mtMixTrainUserTrainInfo.getTrainID();
                    Integer operatorId = mtMixTrainUserTrainInfo.getUserID();

                    UserTrainKey usertrainId = new UserTrainKey(operatorId,trainId,year);
                    TrainWithBLOBs train1 = this.onlineTrainingService.findById(trainId);
                    /**
                     * 审核通过，已确认学时，学员获取积分
                     */
                    if(status==1052){
                        this.integralService.addInnerAndOuterTrainIntegral(mtMixTrainUserTrainInfo,train1);
                    }
                    //若原来有数据，而现在审批状态改为未通过，则删除数据;若没有数据，而审批状态为通过，则添加数据
                    if(this.userTrainService.selectByPrimaryKey(usertrainId)!=null && status.intValue()==1053){
                        UserTrain usertrain = this.userTrainService.selectByPrimaryKey(usertrainId);
                        this.userTrainService.deleteByPrimaryKey(usertrain);
                        this.userNeedLearnCourseService.deleteByOperatorIdTrainIdYear(operatorId, trainId, year);

                    }else if(this.userTrainService.selectByPrimaryKey(usertrainId)==null && status.intValue()==1052){
                        TrainWithBLOBs train = this.onlineTrainingService.findById(trainId);
                        String trainName = train.getTrainName();
                        //Integer trainWay = train.getTrainWay();
                        //String location = "";
                        //if(train.getLocation()!=null){ location = train.getLocation(); }
                        //Integer attendantCount = train.getAttendantCount();
                        //Date startTime = train.getStartTime();
                        //Date endTime = train.getEndTime();
                        //Short isStationTrain = train.getIsStationTrain().shortValue();
                        //String sponsorName = train.getSponsorName();
                        //Usertrain usertrain = new Usertrain(usertrainId,trainName,trainWay,location,attendantCount,startTime,endTime,isStationTrain,sponsorName);
                        //this.usertrainService.save(usertrain);
                        List<Course> courseList = mtMixTrainScheduleItemInfoService.getCourseListByTrainId(trainId);
                        for(Course _course:courseList){
                            if(_course.getCourseId()!=null){
                                String courseTypeName = this.courseTypeService.findByCourseId(_course.getCourseId()).getCourseTypeName();

                                UserNeedLearnCourse userNeedLearnCourse = new UserNeedLearnCourse();
                                userNeedLearnCourse.setOperatorId(operatorId);
                                userNeedLearnCourse.setTrainId(trainId);
                                userNeedLearnCourse.setYear(year);
                                userNeedLearnCourse.setSectionId(0);
                                userNeedLearnCourse.setCourseId(_course.getCourseId());
                                userNeedLearnCourse.setTrainName(trainName);
                                userNeedLearnCourse.setIsStationTrain(new Byte("0"));
                                userNeedLearnCourse.setSectionName("");
                                userNeedLearnCourse.setCourseName(_course.getCourseName());
                                userNeedLearnCourse.setClassHour(_course.getClassHour());
                                userNeedLearnCourse.setCourseType(courseTypeName);


                                this.userNeedLearnCourseService.insert(userNeedLearnCourse);
                                int selectedTimes=0;
                                if(_course.getSelectedTimes()!=null){
                                    selectedTimes=_course.getSelectedTimes();
                                }
                                _course.setSelectedTimes(selectedTimes+1);
                                this.courseService.updateCourse(_course);
                            }
                        }

                        //***************************人员审核通过后发送电子邮件进行通知***************************************
                        //构建MsgMessageInfoForm实例，用于发送邮件通知
                        MailMessage mailMessage = new MailMessage();
                        mailMessage.setTitle(Constants.systemName+"审核通过信息");

                        StringBuffer buffer=new StringBuffer();
                        buffer.append("<html><body><b>尊敬的"+this.eosOperatorService.findById(mtMixTrainUserTrainInfo.getUserID()).getOperatorName()+"，您好!</b><br/><br/><br/>");
                        buffer.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;您已通过了"+train.getTrainName()+"的报名审核,可以参加该课程培训。<br/><br/>");
                        buffer.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;培训日期为"+train.getStartTime()+"至"+train.getEndTime()+"，总计"+train.getClassHour()+"学时，");
                        if(train.getLocation()!=null&&train.getLocation().trim().length()!=0){
                            buffer.append("培训地点为"+train.getLocation()+"。<br/><br/>");
                        }
                        else{
                            buffer.append("陪训地点待定");
                        }
                        if((train.getOrganizerEmail()!=null&&train.getOrganizerEmail().trim().length()!=0)||(train.getTelephone()!=null&&train.getTelephone().trim().length()!=0)){
                            buffer.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;如有疑问或建议，可");
                            if(train.getOrganizerEmail()!=null&&train.getOrganizerEmail().trim().length()!=0){
                                buffer.append("&nbsp;&nbsp;发送邮件至"+train.getOrganizerEmail());
                            }
                            if(train.getTelephone()!=null&&train.getTelephone().trim().length()!=0){
                                buffer.append("&nbsp;&nbsp;致电"+train.getTelephone());
                            }
                            buffer.append("<br/><br/>");
                        }
                        buffer.append("<div style='float:right'>中国科学院计算机网络信息中心<div>");
                        buffer.append("</body></html>");

                        mailMessage.setToEmail(new String[]{this.eosorgTEmployeeService.findById(mtMixTrainUserTrainInfo.getUserID()).getOEmail()});
                        mailMessage.setReceiverIDs(new Integer[]{mtMixTrainUserTrainInfo.getUserID()});
                        mailMessage.setContent(buffer.toString());
                        mailMessage.setMailstamp(train.getTrainName());

                        //=================未走通方法====================
                        this.mailSender.send(mailMessage);
                    }
                }
            }
        }
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public void hurrySummary(HttpServletRequest request){

        if(request.getParameterValues("userTrainInfoId") != null){
            String[] updateList= request.getParameterValues("userTrainInfoId");
            if(updateList!=null && updateList.length>0){
                for(String id : updateList) {
                    MtMixTrainUserTrainInfo mtMixTrainUserTrainInfo = this.mtMixTrainUserTrainInfoMapper.selectByPrimaryKey(Integer.parseInt(id));
                    mtMixTrainUserTrainInfo.setHurrySummary(1);
                    this.mtMixTrainUserTrainInfoMapper.updateByPrimaryKeySelective(mtMixTrainUserTrainInfo);
                }
            }
        }
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public Integer findCountByTrainId(Integer trainId){

        int count = this.mtMixTrainUserTrainInfoMapper.findCountByTrainId(trainId);

        return count;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public ServiceResponse getAttendedList(HttpServletRequest request){

        Integer trainId = Integer.parseInt(request.getParameter("trainId"));
        EosOperator operator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        Integer tenantId = null;
        Integer startIndex = null;
        Integer length = null;

        if(operator!=null){
            tenantId=operator.getTenantId();
        }
        if(request.getParameter("startIndex")!=null && request.getParameter("startIndex").trim().length()>0){
            startIndex = Integer.parseInt(request.getParameter("startIndex"));
        }
        if(request.getParameter("length")!=null && request.getParameter("length").trim().length()>0){
            length = Integer.parseInt(request.getParameter("length"));
        }
        try {
            List<BasicUserForm> userList = new ArrayList<>();
            if(startIndex!=null && length!=null){
                userList=this.getAttendedUserList(trainId, startIndex, length+1, tenantId);
            } else{
                userList=this.getAttendedUserList(trainId, null, null, tenantId);
            }
            Map<String,Object> returnMap = new HashMap<>();
            returnMap.put("userList",userList);

            if(length!=null && userList.size()<=length){
                returnMap.put("stopScroll",true);
            }
            return  ServiceResponse.createBySuccess(returnMap);
        } catch (Exception e) {
            e.printStackTrace();
            return  ServiceResponse.createByError();
        }
    }

    public List<BasicUserForm> getAttendedUserList(Integer trainId, Integer startIndex, Integer length, Integer tenantId) {
        StringBuffer queryString = new StringBuffer(" ,mt_mixtrainusertrainInfo mt where operator.operatorId=mt.userId and mt.trainId=:trainId and mt.attended=1 ");
        StringBuffer orderPageString = new StringBuffer(" order by org.orgseq asc ");
        if(startIndex!=null && length!=null){
            orderPageString.append(" limit ").append(startIndex).append(",").append(length);
        }
        Map<String,Object> condition=new HashMap<>();
        condition.put("trainId", trainId);
        List<BasicUserForm> userList=this.eosorgTEmployeeService.findBasicUserInfoByCondition(queryString.toString(), tenantId, condition, orderPageString.toString());
        return userList;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public ServiceResponse updateTrainUserStatic(HttpServletRequest request){

        Integer trainID = Integer.parseInt(request.getParameter("trainId").toString());

        List<MtMixTrainUserTrainInfo> list = this.mtMixTrainUserTrainInfoMapper.findByTrainId(trainID);
        for (MtMixTrainUserTrainInfo mt : list) {
            this.updateUserStatic(mt.getUserID(), trainID);
        }

        return ServiceResponse.createBySuccess();
    }

    //因为继续教育网中没有学时统计这个字段，所以，此方法暂不写
    public void updateUserStatic(Integer operatorId,Integer trainId){

        /*Map<String,Object> conditions = new HashMap<>();
        TrainWithBLOBs train = this.onlineTrainingService.findById(trainId);

        //获取培训要求学时
        Double trainNeededHours = train.getNeededHours();

        //获取线上课程分类
        Integer scheduleId=0;

        List<MtMixTrainScheduleTrain> mtScheduleTrainList=this.mtScheduleTrainService.findScheduleTrainList(trainId);
        if(mtScheduleTrainList!=null&&mtScheduleTrainList.size()>0){
            scheduleId=mtScheduleTrainList.get(0).getScheduleId();
        }
        List<String> sortList = this.mtScheduleItemInfoService.ListSortByscheduleId(scheduleId);
        //如果只有一个分类或没有分类，则查询所有
        String sortLable;
        conditions.put("scheduleId", scheduleId);
        conditions.put("onOrOffLineFlag", 0);
        List<MtMixtrainscheduleIteminfo> scheudleOnlineItemList = this.getMtScheduleItemInfoService().findbyCondition(conditions);
        scheudleOnlineItemList = this.getMtScheduleItemInfoService().getScheduleOnlineItemList(scheudleOnlineItemList);
        BigDecimal progressFlag = new BigDecimal(0);
        if(scheudleOnlineItemList.size()>0){
            for(MtMixtrainscheduleIteminfo schedule:scheudleOnlineItemList){
                Long courseId = schedule.getCourseId();
                UcsEmployeecourse ucsEmployeecourse = getSelectCourseService().SelectUcsEmployeecourse(
                        operatorId, courseId, Integer.valueOf(String.valueOf(Tools.getCurrentYear())));
                if(ucsEmployeecourse != null ){
                    schedule.setStudyProgress(ucsEmployeecourse.getStudyProgress()+"");
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
            List<MtMixTrainUserTrainInfo> listMtMixTrainUserTrainInfo = this.getMtMixTrainUserTrainInfoDAO().findByTrainAndOperator(trainId, operatorId);
            listMtMixTrainUserTrainInfo.get(0).setStatistics(progressFlag+"");
            this.getMtMixTrainUserTrainInfoDAO().update(listMtMixTrainUserTrainInfo.get(0));
        }*/
    }

    /**
     * 获取某培训班确认参会的参会人员基本信息
     * @param queryConditionMap
     * @param request
     * @return
     */
    @Override
    @Transactional(rollbackFor = {Exception.class })
    public ServiceResponse getAttendedListForPaginationTool(Map<String,Object> queryConditionMap,HttpServletRequest request){

        PageHelper.startPage((Integer) queryConditionMap.get("startIndex"),(Integer) queryConditionMap.get("count"));

        //根据查询条件进行查询
        //根据筛选条件查询实际参会人员的基本信息
        List<BasicUserForm> userList = this.getAttendedListForParmMap(queryConditionMap);

        PageInfo pageInfo = new PageInfo(userList);
        pageInfo.setList(userList);

        return ServiceResponse.createBySuccess(pageInfo);
    }


    public List<BasicUserForm> getAttendedListForParmMap(Map<String, Object> condition){
        List<BasicUserForm> userList= this.mtMixTrainUserTrainInfoMapper.getAttendedListForParmMap(condition);
        if(userList!=null){
            for(BasicUserForm user:userList){
                if(user.getTenantId()!=null){
                    if(condition.get("tenantId")!=null && user.getTenantId().equals(condition.get("tenantId"))){

                    } else{
                        user.setOrgName(CacheUtils.getTenantName(user.getTenantId()));
                    }
                }
            }
        }
        return userList;
    }


}
