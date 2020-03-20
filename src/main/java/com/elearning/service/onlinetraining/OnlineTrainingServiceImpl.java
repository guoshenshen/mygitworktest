package com.elearning.service.onlinetraining;

import com.elearning.common.Constants;
import com.elearning.common.ServiceResponse;
import com.elearning.common.Tools;
import com.elearning.dao.pub.TrainMapper;
import com.elearning.pojo.courseStudy.UserTrain;
import com.elearning.pojo.message.MsgMessageInfoWithBLOBs;
import com.elearning.pojo.mixtraining.MtMixTrainIngTip;
import com.elearning.pojo.mixtraining.MtMixTrainUserTrainInfo;
import com.elearning.pojo.mixtraining.MtMixTrainingTipItem;
import com.elearning.pojo.mixtraining.MtMixTrainingTipTrain;
import com.elearning.pojo.plan.TpTrainPlanDetail;
import com.elearning.pojo.plan.TpTrainPlanDetailWithBLOBs;
import com.elearning.pojo.pub.*;
import com.elearning.pojo.resourceManage.RsmRcmBookDiscuss;
import com.elearning.pojo.systemManage.PictureBase;
import com.elearning.pojo.systemManage.Tenant;
import com.elearning.service.courseStudy.IUserNeedLearnCourseService;
import com.elearning.service.courseStudy.IUserTrainService;
import com.elearning.service.message.IMsgMessageInfoService;
import com.elearning.service.mixtraining.IMtMixTrainIngTipService;
import com.elearning.service.mixtraining.IMtMixTrainUserTrainInfoService;
import com.elearning.service.mixtraining.IMtMixTrainingTipItemService;
import com.elearning.service.mixtraining.IMtMixTrainingTipTrainService;
import com.elearning.service.plan.ITpTrainPlanDetailService;
import com.elearning.service.pub.IDDictionaryService;
import com.elearning.service.pub.IEosorgTEmployeeService;
import com.elearning.service.pub.IEosorgTOrganizationService;
import com.elearning.service.pub.ITrainOrganizationService;
import com.elearning.service.resourceManage.IRsmRcmbookdiscussService;
import com.elearning.service.systemManage.IPictureBaseService;
import com.elearning.service.systemManage.ISysFormFieldService;
import com.elearning.service.systemManage.ITenantService;
import com.elearning.util.DateTimeUtil;
import com.elearning.util.PropertiesUtil;
import com.elearning.util.ToolsUtil;
import com.elearning.vo.onlinetraining.BasicTrainForm;
import com.elearning.vo.onlinetraining.TrainForm;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service("onlineTrainingService")
public class OnlineTrainingServiceImpl implements IOnlineTrainingService {

    @Autowired
    private TrainMapper trainMapper;

    @Autowired
    private IDDictionaryService dictionaryService;

    @Autowired
    private IEosorgTOrganizationService eosorgTOrganizationService;

    @Autowired
    private IEosorgTEmployeeService eosorgTEmployeeService;

    @Autowired
    private IUserTrainService userTrainService;

    @Autowired
    private ITenantService tenantService;

    @Autowired
    private IMtMixTrainUserTrainInfoService mtMixTrainUserTrainInfoService;

    @Autowired
    private ITpTrainPlanDetailService tpTrainPlanDetailService;

    @Autowired
    private ITrainOrganizationService trainOrganizationService;

    @Autowired
    private ITrainTagService trainTagService;

    @Autowired
    private IUserNeedLearnCourseService userNeedLearnCourseService;

    @Autowired
    private IRsmRcmbookdiscussService rsmRcmbookdiscussService;

    @Autowired
    private IPictureBaseService pictureBaseService;

    @Autowired
    private IMtMixTrainingTipTrainService mtMixTrainingTipTrainService;

    @Autowired
    private IMtMixTrainIngTipService mtMixTrainIngTipService;

    @Autowired
    private IMtMixTrainingTipItemService mtMixTrainingTipItemService;

    @Autowired
    private ISysFormFieldService sysFormFieldService;

    @Autowired
    private IMsgMessageInfoService msgMessageInfoService;


    @Override
    public TrainWithBLOBs getOnlineTrainingByID(Integer ID){
        return trainMapper.selectByPrimaryKey(ID);
    }

    @Override
    public int updateByPrimaryKeyWithBLOBs(TrainWithBLOBs record){
        return trainMapper.updateByPrimaryKeyWithBLOBs(record);
    }

    @Override
    public TrainWithBLOBs findById(Integer ID){
        return trainMapper.selectByPrimaryKey(ID);
    }

    /**
     * 培训班管理(查询)
     * @param parmMap
     * @return
     */
    @Override
    public ServiceResponse listTrains(Map<String,Object> parmMap){
        PageHelper.startPage((Integer) parmMap.get("startIndex"),(Integer) parmMap.get("count"));

        //根据查询条件进行查询
        List<TrainWithBLOBs> trainList = this.trainMapper.listTrains(parmMap);
        PageInfo pageInfo = new PageInfo(trainList);
        pageInfo.setList(trainList);

        trainList = pageInfo.getList();
        List<TrainForm> trainFormList = new ArrayList<>();

        for (int i = 0; i < trainList.size() ;i++){
            TrainForm trainForm = new TrainForm();
            TrainWithBLOBs train = trainList.get(i);
            trainForm.setId(train.getID());
            trainForm.setTrainId(train.getTrainID());
            trainForm.setTrainName(train.getTrainName());
            trainForm.setKeyWordsTag(train.getKeyWordsTag()==null?"":train.getKeyWordsTag());
            trainForm.setStartTime(train.getStartTime()!=null? DateTimeUtil.dateToStr(train.getStartTime()):"");
            trainForm.setEndTime(train.getEndTime()!=null?DateTimeUtil.dateToStr(train.getEndTime()):"");
            trainForm.setTrainTypeId(train.getTrainTypeID());
            trainForm.setImplStatusId(train.getImplStatusId());
            trainForm.setPubstatus(train.getPubStatus());
            String trainTypeName = "";

            List<DDictionary> dDictionaryList = this.dictionaryService.findByCode(String.valueOf(train.getTrainTypeID()));
            if(dDictionaryList.size()>0){
                trainTypeName = dDictionaryList.get(0).getName();
            }
            trainForm.setTrainTypeName(trainTypeName);
            if(train.getSubTrainTypeID()!=null){
                trainForm.setSubTrainTypeID(train.getSubTrainTypeID());
            }
            if(train.getCreamProject()!=null){
                trainForm.setCreamProject(train.getCreamProject());
            }
            if(train.getTrainMode()!=null){
                trainForm.setTrainMode(train.getTrainMode());
            }
            trainForm.setOrgName(train.getOrgName());
            trainForm.setOrganizerName(train.getOrganizerName());
            trainForm.setOrganizerId(train.getOrganizerID());
            trainForm.setApproveStatus(train.getApproveStatus());
            if(train.getApproveStatus()!=null){
                List<DDictionary> approveTypeList = this.dictionaryService.findByCode(train.getApproveStatus()+"");
                if(approveTypeList.size()>0){
                    if("1042".equals(approveTypeList.get(0).getCode())){
                        train.setApproveStatusName(approveTypeList.get(0).getName());
                    }else{
                        train.setApproveStatusName("未上报");
                    }
                }
            }
            trainForm.setApproveStatusName(train.getApproveStatusName());
            if(train.getSponsorName()!=null){
                trainForm.setSponsorName(train.getSponsorName());
            }
            trainForm.setTenantId(train.getTenantId());

            if(trainForm.getImplStatusId()!=null){
                List<DDictionary> dDictionaryList1 = this.dictionaryService.find(String.valueOf(trainForm.getImplStatusId()), "1060");
                if(dDictionaryList1.size()>0){
                    trainForm.setImplstatusName(dDictionaryList1.get(0).getName());
                }else{
                    trainForm.setImplstatusName("");//未查询到结果
                }
            } else{
                trainForm.setImplstatusName("未实施");
            }
            trainFormList.add(trainForm);
        }

        pageInfo.setList(trainFormList);
        return ServiceResponse.createBySuccess(pageInfo);
    }

    /**
     * 更改发布状态
     * @param request
     * @return
     */
    @Override
    public ServiceResponse updatePubStatus(HttpServletRequest request){
        String trainId = request.getParameter("trainID");
        Train train = this.trainMapper.selectByPrimaryKey(Integer.parseInt(trainId));
        if (train!=null) {
            Integer pubStatus = train.getPubStatus();
            if (pubStatus==1092) {
                pubStatus=1091;
                train.setPubStatus(-1);
            } else {
                pubStatus = 1092;
                //设置培训范围
                Integer orgId = Integer.parseInt(request.getSession().getAttribute(Constants.ROOTORGID_KEY).toString());
                train.setOpenOrgSEQ(this.eosorgTOrganizationService.getOpenOrgSEQ(orgId, Short.parseShort(train.getOpenScope().toString())));
            }
            train.setPubStatus(pubStatus);
            this.trainMapper.updateByPrimaryKey(train);

            return ServiceResponse.createBySuccess(pubStatus);
        } else {
            return ServiceResponse.createByErrorMessage("没有查询出该培训班，该培训班不存在！");
        }
    }

    /**
     * 删除培训班
     * @param selectbox
     * @return
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public ServiceResponse delTrainById(String[] selectbox) {
        for (String id : selectbox){
            Integer trainID = Integer.parseInt(id);
            //根据培训班ID查询培训班详情
            TrainWithBLOBs trainWithBLOBs = this.trainMapper.selectByPrimaryKey(trainID);

            if(trainWithBLOBs!=null){
                //根据培训班主键id删除用户培训情况的记录 --  usertrain
                this.userTrainService.deleteByTrainID(trainID);

                //根据培训班id删除用户需要学习的课程  -- userneedlearncourse
                this.userNeedLearnCourseService.deleteByTrainID(trainID);

                //根据trainID删除msg_messageinfo里的数据
                List<MsgMessageInfoWithBLOBs> msgMessageInfoWithBLOBsList = this.msgMessageInfoService.findByTrainId(trainID);
                for(MsgMessageInfoWithBLOBs msgMessageInfoWithBLOBs : msgMessageInfoWithBLOBsList){
                    this.msgMessageInfoService.deleteByPrimaryKey(msgMessageInfoWithBLOBs.getID());
                }

                //根据培训班主键删除培训班信息 -- train
                this.trainMapper.deleteByPrimaryKey(trainID);

            }
        }
        return ServiceResponse.createBySuccessMessage("删除成功");
    }

    /**
     * 新建培训班
     * @param trainForm
     * @param request
     * @return
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public int add(TrainForm trainForm, HttpServletRequest request){
        int result = 0;

        EosOperator operator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        Integer tenantId=operator.getTenantId();

        TrainWithBLOBs train = new TrainWithBLOBs();

        train.setTrainPlanID(trainForm.getTrainPlanID());
        train.setTrainID(trainForm.getTrainId());
        train.setTrainName(trainForm.getTrainName());
        train.setTrainWay(trainForm.getTrainWay());
        train.setTrainTypeID(trainForm.getTrainTypeId());

        train.setStartTime(DateTimeUtil.stringToSeconds(trainForm.getStartTime()));
        train.setEndTime(DateTimeUtil.stringToSeconds(trainForm.getEndTime()));
        train.setProgramNo(trainForm.getProgramNo());
        train.setSponsorName(trainForm.getOrgsname());
        train.setOrganizerName(trainForm.getOrganizerName());
        train.setOrgId(trainForm.getOrgId());
        train.setTelephone(trainForm.getTelephone());
        train.setOrganizerEmail(trainForm.getOrganizerEmail());
        train.setClassHour(trainForm.getClassHour());
        train.setTrainingContent(trainForm.getTrainingContent());
        train.setAttendants(trainForm.getAttendants());
        train.setAttendantCount(trainForm.getAttendantCount());
        train.setTrainGoal(trainForm.getTrainGoal());
        train.setLocation(trainForm.getLocation());

        if("1".equals(trainForm.getIsEnrolled()+"")){
            train.setIsEnrolled(Boolean.valueOf("true"));
        }else{
            train.setIsEnrolled(Boolean.valueOf("false"));
        }

        train.setIsIssued(Boolean.valueOf(trainForm.getIsIssued()+""));

        if("1".equals(trainForm.getIsNeedCheck()+"")){
            train.setIsNeedCheck(Boolean.valueOf("true"));
        }else{
            train.setIsNeedCheck(Boolean.valueOf("false"));
        }

        train.setOrganizerID(request.getParameter("organizerId"));
        train.setSponsorID(request.getParameter("operatorId"));
        train.setOrgName(request.getParameter("orgName"));
        train.setFeeLevel(Integer.parseInt(request.getParameter("feeLevel")));

        train.setAuxiliary(request.getParameter("auxiliary")); // 辅助资料
        String expenseFee = request.getParameter("expenseFee");
        if (expenseFee!=null && !"".equals(expenseFee)) { // 报销费用
            train.setExpenseFee(Double.parseDouble(expenseFee));
        }
        if(trainForm.getProgramStartTime()!=null && !"".equals(trainForm.getProgramStartTime())){
            train.setProgramStartTime(DateTimeUtil.stringToSeconds(trainForm.getProgramStartTime()));
        }

        if(trainForm.getProgramEndTime()!=null && !"".equals(trainForm.getProgramEndTime())){
            train.setProgramEndTime(DateTimeUtil.stringToSeconds(trainForm.getProgramEndTime()));
        }

        train.setIsStationTrain(Boolean.valueOf("0"));
        train.setYear(trainForm.getYear());
        train.setSubTrainTypeID(trainForm.getSubTrainTypeID());

        if(trainForm.getIsPlaned()!=null && !"".equals(trainForm.getIsPlaned())){
            train.setIsPlaned(Byte.valueOf(trainForm.getIsPlaned()+""));
        }
        train.setItem_type(trainForm.getItemType());

        Integer _trainCode1 = Math.abs(new Long(new Date().getTime()).intValue());
        Integer trainCode = this.uniqueTrainCode(_trainCode1);

        //System.out.println(1/0);

        Tenant tenant = this.tenantService.findById(tenantId);

        train.setTrainID(tenant.getStoredContext().toUpperCase()+trainCode.toString());
        train.setTenantId(tenantId);
        train.setCad_report(trainForm.getCad_report());
        train.setImplStatusId(1061);

        train.setPubStatus(1091);
        train.setItem_type(Integer.parseInt(request.getParameter("itemType")));
        if (train.getApproveStatus()==null || "".equals(train.getApproveStatus())) {
            train.setApproveStatus(1041);
        }

        if(train.getTrainWay()==null){
            train.setTrainWay(2);
        }
        if(train.getIsPlaned()==null){
            train.setIsPlaned(Byte.valueOf("1"));
        }
        if(trainForm.getIsStationTrain()!=null && trainForm.getIsStationTrain().intValue()==1){
            train.setIsStationTrain(Boolean.valueOf("1"));
        }
        int operatorId = operator.getOperatorId();
        train.setOperatorId(operatorId);
        train.setKeyWordsTag(trainForm.getKeyWordsTag()==null?"":trainForm.getKeyWordsTag());
        if(request.getParameter("upTenantId")!=null && !request.getParameter("upTenantId").toString().equals("")){
            Integer upTenantId = Integer.valueOf(request.getParameter("upTenantId").toString());
            train.setUpTenantId(upTenantId);
        }
        if(request.getParameter("openScope")==null){
            train.setOpenScope(2202);
        } else{
            train.setOpenScope(Integer.valueOf(request.getParameter("openScope").toString()));
        }
        if(request.getParameter("creamProject")==null||request.getParameter("creamProject").toString().equals("")){
            train.setCreamProject(0);
        }else{
            train.setCreamProject(Integer.parseInt(request.getParameter("creamProject")));
        }
        if(request.getParameter("trainMode")==null||request.getParameter("trainMode").toString().equals("")){
            train.setTrainMode(0);
        }else{
            train.setTrainMode(Integer.parseInt(request.getParameter("trainMode")));
        }
        if(request.getParameter("trainTypeId")==null || request.getParameter("trainTypeId").toString().equals("")){
            train.setTrainTypeID(0);
        }else{
            train.setTrainTypeID(Integer.parseInt(request.getParameter("trainTypeId")));
        }
        if(trainForm.getIsNoted()!=null){
            train.setIsNoted(trainForm.getIsNoted());
        }
        if(trainForm.getComment()!=null){
            train.setComment(trainForm.getComment());
        }
        String trainPlanName = "";
        if(trainForm.getTrainPlanName()!=null){
            trainPlanName = trainForm.getTrainPlanName();
        }
        train.setTrainPlanName(trainPlanName);

        if(request.getParameter("researchPostNum")==null || request.getParameter("researchPostNum").toString().equals("")){
            train.setResearchPostNum(0);
        }else{
            train.setResearchPostNum(Integer.parseInt(request.getParameter("researchPostNum").toString()));
        }
        if(request.getParameter("managePostNum")==null || request.getParameter("managePostNum").toString().equals("")){
            train.setManagePostNum(0);
        }else{
            train.setManagePostNum(Integer.parseInt(request.getParameter("managePostNum")));
        }
        if(request.getParameter("supportPostNum")==null || request.getParameter("supportPostNum").toString().equals("")){
            train.setSupportPostNum(0);
        }else{
            train.setSupportPostNum(Integer.parseInt(request.getParameter("supportPostNum")));
        }
        if(request.getParameter("outSideNum")==null || request.getParameter("outSideNum").toString().equals("")){
            train.setOutSideNum(0);
        }else{
            train.setOutSideNum(Integer.parseInt(request.getParameter("outSideNum")));
        }
        if(request.getParameter("workerNum")==null || request.getParameter("workerNum").toString().equals("")){
            train.setWorkerNum(0);
        }else{
            train.setWorkerNum(Integer.parseInt(request.getParameter("workerNum")));
        }
        if(request.getParameter("feeChannel")==null){
            train.setFeeChannel("");
        }else{
            train.setFeeChannel(request.getParameter("feeChannel").toString());
        }
        if(request.getParameter("fee")==null || request.getParameter("fee").toString().equals("")){
            train.setFee(0.00);
        }else{
            train.setFee(Double.parseDouble(request.getParameter("fee").toString()));
        }
        if(request.getParameter("days")==null || request.getParameter("days").toString().equals("")){
            train.setDays(0.00);
        }else{
            train.setDays(Double.parseDouble(request.getParameter("days").toString()));
        }
        train.setIfBJ(Integer.valueOf(request.getParameter("ifBJ").toString()));
        StringBuffer stationId = new StringBuffer();
        String[] stationIdList = request.getParameterValues("stationId");
        if(stationIdList!=null&&stationIdList.length>0){
            for(String _stationId : stationIdList) {
                stationId.append(_stationId).append(";");
            }
            train.setStationId(stationId.toString());
        }

        result = this.trainMapper.insertSelective(train);

        //将项目联系人添加到项目培训中
        MtMixTrainUserTrainInfo mtInfo=new MtMixTrainUserTrainInfo();
        mtInfo.setAttended(1);
        mtInfo.setStatus(1052);

        Integer userID;
        if (train.getOrganizerID()!=null && !"".equals(train.getOrganizerID())) {
            userID = Integer.valueOf(train.getOrganizerID());
        }else{
            userID = train.getOperatorId();
        }
        mtInfo.setUserID(userID);
        mtInfo.setTrainID(train.getID());
        mtInfo.setAttendType(2503);
        mtInfo.setUserName(train.getOrganizerName());
        this.mtMixTrainUserTrainInfoService.save(mtInfo);


        UserTrain userTrain = new UserTrain();

        userTrain.setOperatorID(userID);
        userTrain.setTrainId(train.getID());
        userTrain.setYear(train.getYear());

        if(this.userTrainService.selectByPrimaryKey(userTrain) == null){
            userTrain.setTrainName(train.getTrainName());
            userTrain.setTrainWay(train.getTrainWay());
            userTrain.setLocation(train.getLocation());
            userTrain.setAttendantCount(train.getAttendantCount());
            userTrain.setStartTime(train.getStartTime());
            userTrain.setEndTime(train.getEndTime());
            userTrain.setIsStationTrain(Byte.valueOf("0"));
            userTrain.setSponsorName(train.getSponsorName());

            this.userTrainService.save(userTrain);
        }

        if(train.getTrainPlanID()!=null && train.getTrainPlanID().longValue() != -1){
            TpTrainPlanDetailWithBLOBs trainplan=this.tpTrainPlanDetailService.findById(train.getTrainPlanID());

            this.tpTrainPlanDetailService.updateByPrimaryKeySelective(trainplan);

            //对培训计划相关的培训项目进行信息填充操作
            List<TrainWithBLOBs> trainList = this.trainMapper.findByTrainPlan(trainplan.getID());

            //若修改的培训计划最后点击的是暂存按钮,则不对该计划创建捆绑的培训班,不上报本计划
            if(trainList != null && trainList.size()>0){
                for(Train _train:trainList){
                    _train.setApproveStatus(trainplan.getApproveStatusID());
                    this.trainMapper.updateByPrimaryKeySelective(train);
                }
            }
        }

        if(request.getParameter("facescopeId") != null && !request.getParameter("facescopeId").toString().equals("")){
            String temp = request.getParameter("facescopeId").toString();
            String[] facescopeIds = temp.split(",");
            for(String id:facescopeIds){
                if(!id.equals("")){
                    int orgId = Integer.valueOf(id);
                    TrainOrganization trainOrganization = new TrainOrganization();
                    trainOrganization.setOrgId(orgId);
                    trainOrganization.setTrainId(train.getID());
                    this.trainOrganizationService.insertSelective(trainOrganization);
                }
            }
        }

        request.getSession().setAttribute("trainId", train.getID());
        String keywords = trainForm.getKeyWordsTag();
        this.trainTagService.saveorUpdateTraintags(train.getID(), keywords);

        return result;
    }

    /**
     * 获取唯一培训编号
     */
    private Integer uniqueTrainCode(Integer _trainCode){
        Map<String,Object> parmMap = new HashMap<>();
        parmMap.put("trainID",_trainCode.toString());
        parmMap.put("tenantId",PropertiesUtil.getProperty("tenantId"));

        List<Train> trainList = this.trainMapper.selectByTrainIDAndTenantId(parmMap);

        if(trainList !=null && trainList.size()>0){
            _trainCode = _trainCode+1;
            return uniqueTrainCode(_trainCode);
        } else{
            return _trainCode;
        }
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public TrainForm getTrainForm(Train train, Integer operatorId){

        TrainForm trainForm = new TrainForm();
        trainForm.setTrainId(train.getID().toString());
        trainForm.setTrainName(train.getTrainName());
        trainForm.setImplStatusId(train.getImplStatusId());
        if (train.getLocation() != null){
            trainForm.setLocation(train.getLocation());
        }
        if (train.getSponsorName() != null){
            trainForm.setSponsorName(train.getSponsorName());
        }
        trainForm.setOrgId(train.getOrgId());
        if (train.getIsEnrolled() != null) {
            trainForm.setIsEnrolled((short)(train.getIsEnrolled()==true?1:0));
            if (train.getIsEnrolled()) {
                trainForm.setProgramStartTime(DateTimeUtil.dateToStr(train.getProgramStartTime()));
                trainForm.setProgramEndTime(DateTimeUtil.dateToStr(train.getProgramEndTime()));
            }
        } else{
            trainForm.setIsEnrolled(new Short("0"));
        }
        Map<String,Object> parmMap = new HashMap<>();
        parmMap.put("trainID",train.getID());
        parmMap.put("userID",operatorId);

        List<MtMixTrainUserTrainInfo> mtInfoList = this.mtMixTrainUserTrainInfoService.getListByUserIDAndTrainID(parmMap);
        if (mtInfoList != null && mtInfoList.size() > 0) {
            MtMixTrainUserTrainInfo mtInfo = mtInfoList.get(0);
            if (mtInfo.getAttended() != null && mtInfo.getAttended().intValue() == 1 || (mtInfo.getStatus() != null && mtInfo.getStatus().intValue() == 1052)){
                trainForm.setLearnStatus("2"); // 审批通过
            } else if (mtInfo.getStatus() != null && mtInfo.getStatus().intValue() == 1051){
                trainForm.setLearnStatus("1"); // 未审批
            }
            else if (mtInfo.getStatus() != null && mtInfo.getStatus().intValue() == 1051){
                trainForm.setLearnStatus("3"); // 审批未通过
            }
            else if (mtInfo.getStatus() != null && mtInfo.getStatus().intValue() == 1054){
                trainForm.setLearnStatus("6"); // 审批中
            }
        } else {
            if (train.getIsEnrolled() && train.getProgramEndTime().after(new Date())){
                trainForm.setLearnStatus("4"); // 正在报名
            } else {
                trainForm.setLearnStatus("5"); // 报名结束
            }

        }
        List<UserTrain> usertrainList = this.userTrainService.getListByTrainId(train.getID());
        trainForm.setTotalLearnedUser(usertrainList.size());

        Map<String,Object> parmMap1 = new HashMap<>();
        parmMap1.put("bookId",train.getID());
        parmMap1.put("discussType",1702);
        parmMap1.put("isReply",0);

        Integer totalScore = 0;
        List<RsmRcmBookDiscuss> rsmList = this.rsmRcmbookdiscussService.getListByBookIdAndDiscussTypeAndIsReply(parmMap1);
        for (RsmRcmBookDiscuss _discuss : rsmList) {
            totalScore = totalScore + _discuss.getScore();
        }
        Integer discussMan = 0;
        if (rsmList.size() > 0){
            discussMan = rsmList.size();
        }
        String score = "0.00";
        if (discussMan != 0){
            score = Tools.getTwoDigitalData(totalScore.doubleValue() / discussMan);
        }
        trainForm.setScore(score);
        trainForm.setDiscussUser(discussMan);
        trainForm.setOpenScope(train.getOpenScope());
        trainForm.setAttendantCount(train.getAttendantCount());
        List<UserTrain> userTrainList = this.userTrainService.getListByTrainId(train.getID());
        trainForm.setAttendedCount(userTrainList.size());
        if (train.getIconId() != null) {
            trainForm.setIconUrl(this.pictureBaseService.findById(train.getIconId()) != null ? this.pictureBaseService.findById(train.getIconId()).getPictureUrl() : "");
        }
        trainForm.setStartTime(DateTimeUtil.dateToStr(train.getStartTime()));
        trainForm.setStartTime_date(train.getStartTime());
        trainForm.setEndTime(DateTimeUtil.dateToStr(train.getEndTime()));
        trainForm.setEndTime_date(train.getEndTime());

        trainForm.setImgUrl(train.getImgUrl());
        return trainForm;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public ServiceResponse getTrainTopBand(Integer trainId){

        int userId=-1;
        TrainWithBLOBs train=this.trainMapper.selectByPrimaryKey(trainId);
        String picUrl="";
        String needWords="1";
        PictureBase picBase = this.pictureBaseService.findById(train.getTopbandId());

        if(train.getTopbandId()!=null && picBase!=null){
            //去除
            if(picBase.getPictureUrl().toString().startsWith(".")){
                picUrl = picBase.getPictureUrl().toString().substring(1);
            }else{
                picUrl=picBase.getPictureUrl();
            }
            needWords=picBase.getNeedWords()+"";
        }

        Map<String,Object> returnMap = new HashMap<>();
        returnMap.put("picUrl",picUrl);
        returnMap.put("tenantId",train.getTenantId());
        returnMap.put("userId",userId);
        returnMap.put("needWords",needWords);
        returnMap.put("trainTypeId",train.getTrainTypeID());
        returnMap.put("trainMode",train.getTrainMode());

        return ServiceResponse.createBySuccess(returnMap);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public ServiceResponse getIfUserJoinTrain(EosOperator eosoperator,Integer trainId){

        int joined=-1;
        if(eosoperator!=null){

            //奇怪了！这个方法的结果根本没用到，为啥原来项目中会使用？醉了
            //boolean  userJoinTrainFlag=this.userTrainService.ifOperatorJoinTrain(eosoperator.getOperatorId(), trainId);

            Map<String,Object> parmMap = new HashMap<>();
            parmMap.put("userID",eosoperator.getOperatorId());
            parmMap.put("trainID",trainId);
            parmMap.put("status",1052);

            List<MtMixTrainUserTrainInfo> examples=this.mtMixTrainUserTrainInfoService.getListByUserIDAndTrainIDAndStatus(parmMap);

            //只有最终参会的学员才可见导航栏所有项目
            if(examples!=null&&examples.size()>0){
                joined=1;
            } else {
                joined=0;
            }
        }

        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("joined",joined);

        return ServiceResponse.createBySuccess(resultMap);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public Integer forupdate(HttpServletRequest request) {
        String jemp = "";

        TrainWithBLOBs train=null;
        int trainID=-1;
        String trainPlanIDStr=request.getParameter("trainPlanID");
        if(trainPlanIDStr!=null){
            Long trainPlanID=-1L;
            trainPlanID=Long.parseLong(trainPlanIDStr);
            List<TrainWithBLOBs> trains=this.trainMapper.findByTrainPlan(trainPlanID);
            if(trains!=null && trains.size()>0){
                train = trains.get(0);
            } else{
                TpTrainPlanDetail plan=this.tpTrainPlanDetailService.findById(trainPlanID);
                //根据培训计划信息表创建相应的培训项目内容
                train=tpTrainPlanDetailService.castToTrain(plan);
                this.trainMapper.insert(train);
            }
        }
        if(trainPlanIDStr==null){
            if(request.getParameter("id")!=null&&!request.getParameter("id").trim().equals("")){
                trainID =Integer.parseInt(request.getParameter("id"));
            } else if(request.getAttribute("trainId")!=null&&!request.getAttribute("trainId").toString().trim().equals("")){
                trainID =Integer.parseInt(request.getAttribute("trainId").toString());
            } else{
                trainID =Integer.parseInt(request.getSession().getAttribute("trainId").toString());
            }
            train = this.trainMapper.selectByPrimaryKey(trainID);

        }
        if(train.getUpTenantId()==null){
            train.setUpTenantId(0);
        }

        TpTrainPlanDetail plan=this.tpTrainPlanDetailService.findById(train.getTrainPlanID());
        request.setAttribute("plan", plan);

        List<TrainOrganization> trainOrganizationList = this.trainOrganizationService.findTrainOrganizationByTrainID(trainID);
        StringBuffer facescopeName = new StringBuffer();
        StringBuffer facescopeId = new StringBuffer();
        for(TrainOrganization trainOrganization:trainOrganizationList){
            Integer _orgId = trainOrganization.getOrgId();
            String _orgName = this.eosorgTOrganizationService.getEosorgTOrganizationById(_orgId).getOrgName();
            facescopeName.append(_orgName).append(";");
            facescopeId.append(_orgId).append(",");
        }
        request.setAttribute("facescopeName", facescopeName.toString());
        request.setAttribute("facescopeId", facescopeId.toString());
        String orgName = this.eosorgTOrganizationService.getEosorgTOrganizationById(train.getOrgId()).getOrgName();
        request.setAttribute("_orgsname", orgName);

        int userid = ((EosOperator) request.getSession().getAttribute(Constants.USERINFO_KEY)).getOperatorId();
        List<DDictionary> trainTypeList = this.dictionaryService.getByParentCode("2100"); // 获得培训类型
        //List<TpTrainPlanDetailForm> trainPlanlList = this.onLineTrainPlanService.findAll(userid, 1052, -1); // 获得(已审批)计划
        HashMap param = new HashMap();
        param.put("login_user_id", userid);
        Integer trainTypeId = train.getTrainTypeID();
        List<DDictionary> subTrainTypeList = this.dictionaryService.getByParentCode(trainTypeId.toString()); // 获得培训类型
        List<DDictionary> openScopeList = this.dictionaryService.findByCodeAndTenantIdAndVisible("2200", Constants.tenantId, 1); // 获得开放范围类型
        List<DDictionary> creamProjectList = this.dictionaryService.findChildren("2230",Constants.tenantId,1); // 获得精品项目类型
        List<DDictionary> trainModeList = this.dictionaryService.findChildren("2240",Constants.tenantId,1); // 获得培训方式类型

        List<DDictionary> trainTypeList_revise=new ArrayList<DDictionary>();
        if(trainTypeList!=null){
            Iterator<DDictionary> iter_trainType=trainTypeList.iterator();
            while(iter_trainType.hasNext()){
                DDictionary next=iter_trainType.next();
                if(next.getVisible()!=null&&next.getVisible()==0){
                    if(next.getCode().trim().equals(train.getTrainTypeID()+"")){
                        trainTypeList_revise.add(next);
                    } else{
                        continue;
                    }
                } else{
                    trainTypeList_revise.add(next);
                }
            }
        }

        request.setAttribute("trainTypeList", trainTypeList_revise);
        request.setAttribute("subTrainTypeList", subTrainTypeList);
        request.setAttribute("trainYear", train.getYear());
        request.setAttribute("openScopeList", openScopeList);
        request.setAttribute("creamProjectList", creamProjectList);
        request.setAttribute("trainModeList", trainModeList);
        request.setAttribute("train", train);
        trainID = train.getID();
        request.getSession().setAttribute("trainId", trainID);
        request.setAttribute("trainID", trainID);
        Boolean implementable=false;
        if(train.getYear()!=null&&train.getApproveStatus()!=null){
            if(train.getYear().equals(""+Tools.getCurrentYear())){
                if(train.getApproveStatus().equals(1041)||train.getApproveStatus().equals(1044)||train.getApproveStatus().equals(1045)){
                    implementable=true;
                }

            }
        }
        request.setAttribute("implementable", implementable);

        //获取培训提示信息
        List<MtMixTrainingTipTrain> tipTrainList=this.mtMixTrainingTipTrainService.findAll();
        int tipId=-2;
        for(MtMixTrainingTipTrain tipTrain:tipTrainList){
            if(tipTrain.getTrainId()==trainID && tipTrain.getStatus().equals("1")){
                tipId=tipTrain.getTipId();
            }
        }
        MtMixTrainIngTip trainTip=this.mtMixTrainIngTipService.findById(tipId);
        List<MtMixTrainingTipItem> trainTipItemList=this.mtMixTrainingTipItemService.findListByTipId(tipId);
        Date trainStartTime=train.getStartTime();
        Date trainEndTime=train.getEndTime();
        Date currentTime=new Date();
        long trainStartDiff=trainStartTime.getTime()-currentTime.getTime();
        long trainEndDiff=currentTime.getTime()-trainEndTime.getTime();
        long beforeTrainStartDays = trainStartDiff/(24*60*60*1000);
        long afterTrainEndDays=trainEndDiff/(24*60*60*1000);
        int _beforeTrainStartDays=Integer.parseInt(String.valueOf(beforeTrainStartDays));
        int _afterTrainEndDays=Integer.parseInt(String.valueOf(afterTrainEndDays));
        List<MtMixTrainingTipItem> currentTrainTipList=new ArrayList();
        int maxTipDay=0,minTipDay=0;//30:培训后三十天
        for(MtMixTrainingTipItem tipItem:trainTipItemList){
            if(_beforeTrainStartDays>0&&tipItem.getTipDays()>=_beforeTrainStartDays){
                if(maxTipDay==0){
                    maxTipDay=tipItem.getTipDays();
                }
                if(maxTipDay>tipItem.getTipDays()){
                    maxTipDay=tipItem.getTipDays();
                }
            }
            if(_afterTrainEndDays>0&&tipItem.getTipDays() <= _afterTrainEndDays){
                if(minTipDay==0){
                    minTipDay=tipItem.getTipDays();
                }
                if(minTipDay<tipItem.getTipDays()){
                    minTipDay=tipItem.getTipDays();
                }
            }
        }
        for(MtMixTrainingTipItem tipItem:trainTipItemList){
            if(tipItem.getTipDays()==maxTipDay||tipItem.getTipDays()==minTipDay){
                currentTrainTipList.add(tipItem);
            }
        }
        if(_beforeTrainStartDays>0){
            currentTrainTipList=this.mtMixTrainingTipItemService.getMtBeforeTipIteminfoList(currentTrainTipList);
        } else if(_afterTrainEndDays>0){
            currentTrainTipList=this.mtMixTrainingTipItemService.getMtAfterTipIteminfoList(currentTrainTipList);
        }
        if(trainTip!=null){
            String trainTipName=trainTip.getTipName();
            request.setAttribute("trainTipName", trainTipName);
        }
        if(currentTrainTipList!=null&&currentTrainTipList.size()>0){
            request.setAttribute("currentTrainTipList", currentTrainTipList);
        }

        Integer tenantId=Constants.tenantId;
        HashMap<String, HashMap<String, String>> formfieldAttributesMap = this.sysFormFieldService.getSysFormfieldAttributesMap(tenantId,2);
        request.setAttribute("formfieldAttributesMap", formfieldAttributesMap);

        return tenantId;
    }

    /**
     * 暂存培训班
     * @param trainForm
     * @param request
     * @return
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public int update(TrainForm trainForm, HttpServletRequest request) {

        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        int result = 0;

        int trainID = -1;
        if(request.getParameter("trainId") != null){
            trainID = Integer.parseInt(request.getParameter("trainId").toString());
        }
        TrainWithBLOBs trainToUpdate = this.trainMapper.selectByPrimaryKey(trainID);

        //获取该培训计划原有信息
        String oldTrainName = trainToUpdate.getTrainName();
        Date oldStartTime = trainToUpdate.getStartTime();
        Date oldEndTime = trainToUpdate.getEndTime();
        String oldLocation = trainToUpdate.getLocation()==null?"":trainToUpdate.getLocation();
        String oldSponsorName = trainToUpdate.getSponsorName()==null?"":trainToUpdate.getSponsorName();
        int oldtrainWay = trainToUpdate.getTrainWay();
        Boolean oldIsStationTrain = trainToUpdate.getIsStationTrain();

        //对培训项目进行更新
        //最基本信息更新
        trainToUpdate.setTenantId(eosoperator.getTenantId());
        trainToUpdate.setTrainName(trainForm.getTrainName()==null?trainToUpdate.getTrainName():trainForm.getTrainName());

        //tptrainPlanDetail表包含信息
        trainToUpdate.setYear(trainForm.getYear()==null?trainToUpdate.getYear():trainForm.getYear());
        trainToUpdate.setClassHour(trainForm.getClassHour()==null?trainToUpdate.getClassHour():trainForm.getClassHour());
        trainToUpdate.setDays(trainForm.getDays()==null?trainToUpdate.getDays():trainForm.getDays());
        trainToUpdate.setTrainTypeID(trainForm.getTrainTypeId()==null?2170:trainForm.getTrainTypeId());
        trainToUpdate.setTrainMode(trainForm.getTrainMode()==null?0:trainForm.getTrainMode());
        trainToUpdate.setIsNoted(trainForm.getIsNoted()==null?0:trainForm.getIsNoted());
        trainToUpdate.setFee(trainForm.getFee()==null?0.0:trainForm.getFee());
        trainToUpdate.setFeeLevel(trainForm.getFeeLevel());
        trainToUpdate.setFeeChannel(trainForm.getFeeChannel()==null?"":trainForm.getFeeChannel());
        trainToUpdate.setIfBJ(trainForm.getIfBJ()==null?0:trainForm.getIfBJ());
        trainToUpdate.setLocation(trainForm.getLocation()==null?"":trainForm.getLocation());

        trainToUpdate.setItemType(trainForm.getItemType());
        trainToUpdate.setAuxiliary(request.getParameter("auxiliary")); // 辅助资料
        String expenseFee = request.getParameter("expenseFee");
        if (expenseFee!=null && !"".equals(expenseFee)) { // 报销费用
            trainToUpdate.setExpenseFee(Double.parseDouble(expenseFee));
        }

        //培训类别为学术会议，学术讲座，专题讲座的，设置其属于学术报告，其余的设置其类型为培训项目
        Integer trainType=trainForm.getTrainTypeId();
        if(trainType!=null){
            if(trainType==2170||trainType==2180||trainType==2192){
                trainToUpdate.setCad_report(5002);
            } else{
                trainToUpdate.setCad_report(5001);
            }
        }
        trainToUpdate.setIsNoted(trainForm.getIsNoted()==null?0:trainForm.getIsNoted());
        trainToUpdate.setAttendants(trainForm.getAttendants()==null?"":trainForm.getAttendants());
        trainToUpdate.setTrainingContent(trainForm.getTrainingContent()==null?"":trainForm.getTrainingContent());
        trainToUpdate.setTrainGoal(trainForm.getTrainGoal()==null?"":trainForm.getTrainGoal());
        trainToUpdate.setComment(trainForm.getComment()==null?"":trainForm.getComment());
        trainToUpdate.setOrgName(trainForm.getOrgsname()==null?"":trainForm.getOrgsname());
        trainToUpdate.setOrganizerName(trainForm.getOrganizerName()==null?"":trainForm.getOrganizerName());
        trainToUpdate.setTelephone(trainForm.getTelephone()==null?"":trainForm.getTelephone());
        trainToUpdate.setOrganizerEmail(trainForm.getOrganizerEmail()==null?"":trainForm.getOrganizerEmail());
        StringBuffer stationId = new StringBuffer();
        String[] stationIdList = request.getParameterValues("stationId");
        if(stationIdList!=null&&stationIdList.length>0){
            for(String _stationId : stationIdList) {
                stationId.append(_stationId).append(";");
            }
            trainToUpdate.setStationId(stationId.toString());
        }

        //附加信息更新
        //培训项目设置为混合培训方式
        //trainToUpdate.setTrainWay(2);
        trainToUpdate.setTrainWay(trainForm.getTrainWay()==null ? 0 : trainForm.getTrainWay());

        if(trainForm.getStartTime()!=null){
            trainToUpdate.setStartTime(Tools.stringToSeconds(trainForm.getStartTime()));
        } else{
            trainToUpdate.setStartTime(null);
        }
        if(trainForm.getEndTime()!=null){
            trainToUpdate.setEndTime(Tools.stringToSeconds(trainForm.getEndTime()));
        } else{
            trainToUpdate.setEndTime(null);
        }
        if(trainForm.getProgramStartTime()!=null){
            trainToUpdate.setProgramStartTime(Tools.stringToSeconds(trainForm.getProgramStartTime()));
        } else{
            trainToUpdate.setProgramStartTime(null);
        }
        if(trainForm.getProgramEndTime()!=null){
            trainToUpdate.setProgramEndTime(Tools.stringToSeconds(trainForm.getProgramEndTime()));
        } else{
            trainToUpdate.setProgramEndTime(null);
        }
        trainToUpdate.setIsEnrolled(trainForm.getIsEnrolled()==1?true:false);
        trainToUpdate.setIsNeedCheck(trainForm.getIsNeedCheck()==1?true:false);
        if(trainToUpdate.getIsEnrolled()!=null && trainToUpdate.getIsEnrolled()){
            trainToUpdate.setProgramStartTime(null);
            trainToUpdate.setProgramEndTime(null);
        }
        trainToUpdate.setKeyWordsTag(trainForm.getKeyWordsTag()==null?"":trainForm.getKeyWordsTag());
        if(trainForm.getAttendantCount()!=null){
            trainToUpdate.setAttendantCount(trainForm.getAttendantCount());
        }
        if(trainForm.getResearchPostNum()!=null){
            trainToUpdate.setResearchPostNum(trainForm.getResearchPostNum());
        }
        if(trainForm.getManagePostNum()!=null){
            trainToUpdate.setManagePostNum(trainForm.getManagePostNum());
        }
        if(trainForm.getSupportPostNum()!=null){
            trainToUpdate.setSupportPostNum(trainForm.getSupportPostNum());
        }
        if(trainForm.getOutSideNum()!=null){
            trainToUpdate.setOutSideNum(trainForm.getOutSideNum());
        }
        if(trainForm.getWorkerNum()!=null){
            trainToUpdate.setWorkerNum(trainForm.getWorkerNum());
        }

        //状态设置

        //not useful currently
        trainToUpdate.setProgramNo(trainForm.getProgramNo());
        if(request.getParameter("iconId")!=null && (!request.getParameter("iconId").trim().equals(""))){
            trainToUpdate.setIconId(Integer.valueOf(request.getParameter("iconId").toString()));
        }
        if(request.getParameter("topbandId")!=null && (!request.getParameter("topbandId").trim().equals(""))){

            trainToUpdate.setTopbandId(Integer.valueOf(request.getParameter("topbandId").toString()));
        }
        if(request.getParameter("upTenantId")!=null && !request.getParameter("upTenantId").toString().equals("")){
            Integer upTenantId = Integer.valueOf(request.getParameter("upTenantId").toString());
            trainToUpdate.setUpTenantId(upTenantId);
        }
        if(request.getParameter("creamProject")==null||request.getParameter("creamProject").toString().equals("")){
            trainToUpdate.setCreamProject(0);
        }else{
            trainToUpdate.setCreamProject(Integer.parseInt(request.getParameter("creamProject")));
        }
        if(request.getParameter("trainMode")==null||request.getParameter("trainMode").toString().equals("")){
            trainToUpdate.setTrainMode(0);
        }else{
            trainToUpdate.setTrainMode(Integer.parseInt(request.getParameter("trainMode")));
        }
        trainToUpdate.setIsStationTrain(trainForm.getIsStationTrain()==1?true:false);
        trainToUpdate.setSubTrainTypeID(trainForm.getSubTrainTypeID());
        if(trainForm.getIsIssued()!=null){
            trainToUpdate.setIsIssued(trainForm.getIsIssued()==1?true:false);
        }
        trainToUpdate.setIsPlaned(trainForm.getIsPlaned()==null?1:Byte.parseByte(trainForm.getIsPlaned()+""));
        Integer openScope=2201;
        if(request.getParameter("openScope")!=null){
            openScope=Integer.valueOf(request.getParameter("openScope").toString());
        }
        trainToUpdate.setOpenScope(openScope);
        String openOrgSEQ="-1";
        if(trainToUpdate.getApproveStatus()!=null && trainToUpdate.getPubStatus()!=null){
            //if(trainToUpdate.getApproveStatus().equals(1044) && trainToUpdate.getPubStatus().equals(1092)){
            if(trainToUpdate.getPubStatus().equals(1092)){
                //当培训项目的审批状态为审批通过时,该项目的公开范围生效
                //openOrgSEQ=this.getEosorgTOrganizationService().getOpenOrgSEQ(Integer.parseInt(trainToUpdate.getSponsorId()), Short.valueOf(trainToUpdate.getOpenScope()+""));
                openOrgSEQ=this.eosorgTOrganizationService.getOpenOrgSEQ(Integer.parseInt(trainToUpdate.getSponsorID()), Short.valueOf(trainToUpdate.getOpenScope()+""));
            }
        }
        trainToUpdate.setOpenOrgSEQ(openOrgSEQ);
        //如果培训计划设置为不允许报名，则置该计划的报名开始时间以及报名截止时间为null;

        //确定是否实施此培训计划
        String executePlan=request.getParameter("executePlan");
        Integer newImplementStatus=null;
        if(executePlan.trim().equals("true")){
            //点击培训实施按钮
            newImplementStatus=1063;
        } else{
            //暂存培训项目
            newImplementStatus=1061;
        }

        trainToUpdate.setImplStatusId(newImplementStatus);
        if(trainForm.getTrainPlanID()!=null){
            //培训项目对应的培训计划状态变为正在实施
            TpTrainPlanDetailWithBLOBs trainPlan=this.tpTrainPlanDetailService.findById(trainForm.getTrainPlanID());
            if(trainPlan!=null){
                trainPlan.setImplStatusID(newImplementStatus);
                this.tpTrainPlanDetailService.updateByPrimaryKeySelective(trainPlan);
            }
        }


        result = this.trainMapper.updateByPrimaryKeySelective(trainToUpdate);

        //移动端对接：当报名时间发生变化时，需要同步更新移动端创建的报名
        this.msgMessageInfoService.updateEnrollTime(trainToUpdate);

        List<TrainOrganization> trainOrganizationList = this.trainOrganizationService.findTrainOrganizationByTrainID(trainID);
        for(TrainOrganization trainOrganization:trainOrganizationList){
            this.trainOrganizationService.deleteByPrimaryKey(trainOrganization.getId());
        }
        if(request.getParameter("facescopeId")!=null&&!request.getParameter("facescopeId").toString().equals("")){
            String temp = request.getParameter("facescopeId").toString();
            String[] facescopeIds = temp.split(",");
            for(String id:facescopeIds){
                if(!id.equals("")){
                    int orgId = Integer.valueOf(id);
                    //TrainOrganization trainOrganizationId = new TrainOrganization(orgId,trainID);
                    TrainOrganization trainOrganization = new TrainOrganization();
                    trainOrganization.setTrainId(trainID);
                    trainOrganization.setOrgId(orgId);

                    trainOrganizationService.insertSelective(trainOrganization);
                }
            }
        }

        //若培训基本信息发生变化，更新usertrain和userneedlearncourse表数据
        if(!oldTrainName.equals(trainForm.getTrainName())
                ||!DateTimeUtil.dateToStr(oldStartTime).equals(trainForm.getStartTime())
                ||!DateTimeUtil.dateToStr(oldEndTime).equals(trainForm.getEndTime())
                ||!oldLocation.equals(trainForm.getLocation()==null?"":trainForm.getLocation())
                ||!oldSponsorName.equals(trainForm.getSponsorName()==null?"":trainForm.getSponsorName())
                ||oldtrainWay!= trainForm.getTrainWay()
                ||(oldIsStationTrain==true?1:0)!=trainForm.getIsStationTrain()
                ){
            this.userNeedLearnCourseService.updateTrainName(trainID, trainToUpdate.getTrainName());
            this.userTrainService.updateTrainInfo(trainID, trainToUpdate.getTrainName(),trainForm.getStartTime(),trainForm.getEndTime(),trainToUpdate.getSponsorName(),trainToUpdate.getTrainWay(),trainToUpdate.getIsStationTrain());
        }

        request.getSession().setAttribute("trainId", trainToUpdate.getID());
        String keywords = trainForm.getKeyWordsTag();
        this.trainTagService.saveorUpdateTraintags(trainID, keywords);

        return result;
    }

    @Override
    public List<TrainWithBLOBs> getListByTopbandId(Integer topbandId){
        return trainMapper.getListByTopbandId(topbandId);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public List<BasicTrainForm> findVisibleTrainPageByCondition(Map<String, Object> conditions) {
        if(conditions.get("orgSEQ") == null ){
            return new ArrayList<>();
        }
        List<String> orgSEQList = ToolsUtil.orgSEQList(conditions.get("orgSEQ").toString());
        conditions.put("orgSEQ", orgSEQList);
        return trainMapper.findByConditionSQL(conditions);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public List<TrainForm> listTrainByOperatorId(Map<String, Object> map){
        Integer currentOperatorId = null;
        if (map.get("currentOperatorId") != null) {
            currentOperatorId = Integer.parseInt(map.get("currentOperatorId").toString());
        }
        Integer operatorOrgId = this.eosorgTEmployeeService.findById(currentOperatorId).getOrgID();
        ArrayList<Integer> parentOrgIdList = new ArrayList<>();
        //this.eosorgTOrganizationService.getParentOrgIdList(operatorOrgId, parentOrgIdList);
        parentOrgIdList.add(operatorOrgId);
        map.put("parentOrgId",parentOrgIdList);
        int tenantOrgId = 1;
        if (map.get("tenantId") != null) {
            Integer tenantId = Integer.parseInt(map.get("tenantId").toString());
            tenantOrgId=this.tenantService.findById(tenantId).getOrgId();
        }
        map.put("tenantOrgId", tenantOrgId);
        this.eosorgTOrganizationService.putSameSecondaryParentTenantIdListInMap(map);
        List<TrainWithBLOBs> trainList = this.trainMapper.listTrainByOperatorId(map);
        List<TrainForm> trainFormList = new ArrayList<>();
        for (TrainWithBLOBs train : trainList) {
            TrainForm form = new TrainForm(train);
            if (form != null) {
                trainFormList.add(form);
            }
        }
        return trainFormList;
    }


}
