package com.elearning.service.courseStudy;

import com.elearning.common.Constants;
import com.elearning.common.ServiceResponse;
import com.elearning.dao.courseStudy.TrainSummaryMapper;
import com.elearning.pojo.courseStudy.TrainSummary;
import com.elearning.pojo.courseStudy.TrainSummaryWithBLOBs;
import com.elearning.pojo.mixtraining.MtMixTrainUserTrainInfo;
import com.elearning.pojo.pub.*;
import com.elearning.pojo.resourceManage.RsmRcmBookDiscuss;
import com.elearning.pojo.systemManage.Tenant;
import com.elearning.service.integralTask.IIntegralService;
import com.elearning.service.mixtraining.IMtMixTrainUserTrainInfoService;
import com.elearning.service.onlinetraining.IOnlineTrainingService;
import com.elearning.service.pub.IEosOperatorService;
import com.elearning.service.pub.IEosorgTEmployeeService;
import com.elearning.service.pub.IEosorgTOrganizationService;
import com.elearning.service.resourceManage.IRsmRcmbookdiscussService;
import com.elearning.service.systemManage.ITenantService;
import com.elearning.vo.CourseStudy.TrainSummaryForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("trainSummaryService")
public class TrainSummaryServiceImpl implements ITrainSummaryService {

    @Autowired
    private TrainSummaryMapper trainSummaryMapper;

    @Autowired
    private IEosOperatorService eosOperatorService;

    @Autowired
    private IOnlineTrainingService onlineTrainingService;

    @Autowired
    private IUserTrainService userTrainService;

    @Autowired
    private IEosorgTOrganizationService eosorgTOrganizationService;

    @Autowired
    private IRsmRcmbookdiscussService rsmRcmbookdiscussService;

    @Autowired
    private ITenantService tenantService;

    @Autowired
    private IEosorgTEmployeeService eosorgTEmployeeService;

    @Autowired
    private IIntegralService integralService;

    @Autowired
    private IMtMixTrainUserTrainInfoService mtMixTrainUserTrainInfoService;

    //根据主键进行查询
    public TrainSummaryWithBLOBs selectByPrimaryKey(Integer id){

        return trainSummaryMapper.selectByPrimaryKey(id);
    }

    //根据属性进行查询
    public List<TrainSummaryWithBLOBs> findByExample(Map<String,Object> parmMap){

        return trainSummaryMapper.findByExample(parmMap);
    }

    //根据属性进行查询
    @Override
    @Transactional(rollbackFor = {Exception.class })
    public TrainSummaryForm getTrainSummaryForm(TrainSummaryWithBLOBs trainSummary){

        TrainSummaryForm trainSummaryForm = new TrainSummaryForm();
        if(trainSummary.getOperatorId()!=null){

            String operatorName = this.eosOperatorService.findById(trainSummary.getOperatorId()).getOperatorName();
            //private Integer id;
            if(trainSummary.getId() != null){
                trainSummaryForm.setId(trainSummary.getId());
            }
            //private String year;
            if(trainSummary.getYear() != null){
                trainSummaryForm.setYear(trainSummary.getYear());
            }
            //private Integer train_id;
            if(trainSummary.getTrain_id() != null){
                trainSummaryForm.setTrainId(trainSummary.getTrain_id());
            }
            //private Integer operatorId;
            if(trainSummary.getOperatorId() != null){
                trainSummaryForm.setOperatorId(trainSummary.getOperatorId());
            }
            //private Date submitDate;
            if(trainSummary.getSubmitDate() != null){
                trainSummaryForm.setSubmitDate(trainSummary.getSubmitDate());
            }
            //private String summaryName;
            if(trainSummary.getSummaryName() != null){
                trainSummaryForm.setSummaryName(trainSummary.getSummaryName());
            }
            //private String attachmentPath;
            if(trainSummary.getAttachmentPath() != null){
                trainSummaryForm.setAttachmentPath(trainSummary.getAttachmentPath());
            }
            //private String orgName;
            if(trainSummary.getOrgName() != null){
                trainSummaryForm.setOrgName(trainSummary.getOrgName());
            }
            //private String parentOrgName;
            if(trainSummary.getParentOrgName() != null){
                trainSummaryForm.setParentOrgName(trainSummary.getParentOrgName());
            }
            //private String userName;
            if(trainSummary.getUserName() != null){
                trainSummaryForm.setUserName(trainSummary.getUserName());
            }
            //private Integer isTrainEffect;
            if(trainSummary.getIsTrainEffect() != null){
                trainSummaryForm.setIsTrainEffect(trainSummary.getIsTrainEffect());
            }
            //private Integer multiplyTimes;
            if(trainSummary.getMultiplyTimes() != null){
                trainSummaryForm.setMultiplyTimes(trainSummary.getMultiplyTimes());
            }
            //private Double totalFee;
            if(trainSummary.getTotalFee() != null){
                trainSummaryForm.setTotalFee(trainSummary.getTotalFee());
            }
            //private Integer completedNum;
            if(trainSummary.getCompletedNum() != null){
                trainSummaryForm.setCompletedNum(trainSummary.getCompletedNum());
            }
            //private Double keyCompletedRadio;
            if(trainSummary.getKeyCompletedRadio() != null){
                trainSummaryForm.setKeyCompletedRadio(trainSummary.getKeyCompletedRadio());
            }
            //private Integer totalNum;
            if(trainSummary.getTotalNum() != null){
                trainSummaryForm.setTotalNum(trainSummary.getTotalNum());
            }
            //private Integer calibratedNum;
            if(trainSummary.getCalibratedNum() != null){
                trainSummaryForm.setCalibratedNum(trainSummary.getCalibratedNum());
            }
            //private String comment;
            if(trainSummaryForm.getComment() != null){
                trainSummaryForm.setComment(trainSummaryForm.getComment());
            }
            //private Double trainPlanExeRate;
            if(trainSummary.getTrainPlanExeRate() != null){
                trainSummaryForm.setTrainPlanExeRate(trainSummary.getTrainPlanExeRate());
            }
            //private Double learnFinishedRate;
            if(trainSummary.getLearnFinishedRate() != null){
                trainSummaryForm.setLearnFinishedRate(trainSummary.getLearnFinishedRate());
            }
            //private Integer needFinishedNum;
            if(trainSummary.getNeedFinishedNum() != null){
                trainSummaryForm.setNeedFinishedNum(trainSummary.getNeedFinishedNum());
            }
            //private Double totalTime;
            if(trainSummary.getTotalTime() != null){
                trainSummaryForm.setTotalTime(trainSummary.getTotalTime());
            }
            //private Double averageTime;
            if(trainSummary.getAverageTime() != null){
                trainSummaryForm.setAverageTime(trainSummary.getAverageTime());
            }
            //private Integer tenantId;
            if(trainSummary.getTenantId() != null){
                trainSummaryForm.setTenantId(trainSummary.getTenantId());
            }
            //private Date lastModifiedDate;
            if(trainSummary.getLastModifiedDate() != null){
                trainSummaryForm.setLastModifiedDate(trainSummary.getLastModifiedDate());
            }
            //private Integer lastEditorOperatorId;
            if(trainSummary.getLastEditorOperatorId() != null){
                trainSummaryForm.setLastEditorOperatorId(trainSummary.getLastEditorOperatorId());
            }
            //private Date lastRefreshDataTime;
            if(trainSummary.getLastRefreshDataTime() != null){
                trainSummaryForm.setLastRefreshDataTime(trainSummary.getLastRefreshDataTime());
            }
            //private String conclusion;
            if(trainSummary.getConclusion() != null){
                trainSummaryForm.setConclusion(trainSummary.getConclusion());
            }
            //private String overallProfile;
            if(trainSummary.getOverallProfile() != null){
                trainSummaryForm.setOverallProfile(trainSummary.getOverallProfile());
            }

            trainSummaryForm.setOperatorName(operatorName);
            trainSummaryForm.setUserName(operatorName);
            if(trainSummary.getTrain_id()!=null && this.onlineTrainingService.findById(trainSummary.getTrain_id())!=null){
                String trainName = this.onlineTrainingService.findById(trainSummary.getTrain_id()).getTrainName();
                trainSummaryForm.setTrainName(trainName);
            }
        }
        return trainSummaryForm;

    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public int saveTrainSummaryForm(TrainSummaryForm trainSummaryForm){
        TrainSummaryWithBLOBs trainSummary = new TrainSummaryWithBLOBs();
        //private Integer id;
        if(trainSummaryForm.getId() != null){
            trainSummary.setId(trainSummaryForm.getId());
        }

        //private String year;
        if(trainSummaryForm.getYear() != null){
            trainSummary.setYear(trainSummaryForm.getYear());
        }
        //private Integer train_id;
        if(trainSummaryForm.getTrainId() != null){
            trainSummary.setTrain_id(trainSummaryForm.getTrainId());
        }
        //private Integer operatorId;
        if(trainSummaryForm.getOperatorId() != null){
            trainSummary.setOperatorId(trainSummaryForm.getOperatorId());
        }
        //private Date submitDate;
        if(trainSummaryForm.getSubmitDate() != null){
            trainSummary.setSubmitDate(trainSummaryForm.getSubmitDate());
        }
        //private String summaryName;
        if(trainSummaryForm.getSummaryName() != null){
            trainSummary.setSummaryName(trainSummaryForm.getSummaryName());
        }
        //private String attachmentPath;
        if(trainSummaryForm.getAttachmentPath() != null){
            trainSummary.setAttachmentPath(trainSummaryForm.getAttachmentPath());
        }
        //private String orgName;
        if(trainSummaryForm.getOrgName() != null){
            trainSummary.setOrgName(trainSummaryForm.getOrgName());
        }
        //private String parentOrgName;
        if(trainSummaryForm.getParentOrgName() != null){
            trainSummary.setParentOrgName(trainSummaryForm.getParentOrgName());
        }
        //private String userName;
        if(trainSummaryForm.getUserName() != null){
            trainSummary.setUserName(trainSummaryForm.getUserName());
        }
        //private Integer isTrainEffect;
        if(trainSummaryForm.getIsTrainEffect() != null){
            trainSummary.setIsTrainEffect(trainSummaryForm.getIsTrainEffect());
        }
        //private Integer multiplyTimes;
        if(trainSummaryForm.getMultiplyTimes() != null){
            trainSummary.setMultiplyTimes(trainSummaryForm.getMultiplyTimes());
        }
        //private Double totalFee;
        if(trainSummaryForm.getTotalFee() != null){
            trainSummary.setTotalFee(trainSummaryForm.getTotalFee());
        }
        //private Integer completedNum;
        if(trainSummaryForm.getCompletedNum() != null){
            trainSummary.setCompletedNum(trainSummaryForm.getCompletedNum());
        }
        //private Double keyCompletedRadio;
        if(trainSummaryForm.getKeyCompletedRadio() != null){
            trainSummary.setKeyCompletedRadio(trainSummaryForm.getKeyCompletedRadio());
        }
        //private Integer totalNum;
        if(trainSummaryForm.getTotalNum() != null){
            trainSummary.setTotalNum(trainSummaryForm.getTotalNum());
        }
        //private Integer calibratedNum;
        if(trainSummaryForm.getCalibratedNum() != null){
            trainSummary.setCalibratedNum(trainSummaryForm.getCalibratedNum());
        }
        //private String comment;
        if(trainSummaryForm.getComment() != null){
            trainSummary.setComment(trainSummaryForm.getComment());
        }
        //private Double trainPlanExeRate;
        if(trainSummaryForm.getTrainPlanExeRate() != null){
            trainSummary.setTrainPlanExeRate(trainSummaryForm.getTrainPlanExeRate());
        }
        //private Double learnFinishedRate;
        if(trainSummaryForm.getLearnFinishedRate() != null){
            trainSummary.setLearnFinishedRate(trainSummaryForm.getLearnFinishedRate());
        }
        //private Integer needFinishedNum;
        if(trainSummaryForm.getNeedFinishedNum() != null){
            trainSummary.setNeedFinishedNum(trainSummaryForm.getNeedFinishedNum());
        }
        //private Double totalTime;
        if(trainSummaryForm.getTotalTime() != null){
            trainSummary.setTotalTime(trainSummaryForm.getTotalTime());
        }
        //private Double averageTime;
        if(trainSummaryForm.getAverageTime() != null){
            trainSummary.setAverageTime(trainSummaryForm.getAverageTime());
        }
        //private Integer tenantId;
        if(trainSummaryForm.getTenantId() != null){
            trainSummary.setTenantId(trainSummaryForm.getTenantId());
        }
        //private Date lastModifiedDate;
        if(trainSummaryForm.getLastModifiedDate() != null){
            trainSummary.setLastModifiedDate(trainSummaryForm.getLastModifiedDate());
        }
        //private Integer lastEditorOperatorId;
        if(trainSummaryForm.getLastEditorOperatorId() != null){
            trainSummary.setLastEditorOperatorId(trainSummaryForm.getLastEditorOperatorId());
        }
        //private Date lastRefreshDataTime;
        if(trainSummaryForm.getLastRefreshDataTime() != null){
            trainSummary.setLastRefreshDataTime(trainSummaryForm.getLastRefreshDataTime());
        }
        //private String conclusion;
        if(trainSummaryForm.getConclusion() != null){
            trainSummary.setConclusion(trainSummaryForm.getConclusion());
        }
        //private String overallProfile;
        if(trainSummaryForm.getOverallProfile() != null){
            trainSummary.setOverallProfile(trainSummaryForm.getOverallProfile());
        }
        this.trainSummaryMapper.insert(trainSummary);
        return trainSummary.getId();
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public void saveAttachFile(TrainSummaryForm trainSummaryForm){



    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public int updateTrainSummaryForm(TrainSummaryForm trainSummaryForm){
        TrainSummaryWithBLOBs trainSummary = this.trainSummaryMapper.selectByPrimaryKey(trainSummaryForm.getId());
        int n = 0;
        if(trainSummary != null){
            if(trainSummaryForm.getYear() != null){
                trainSummary.setYear(trainSummaryForm.getYear());
            }
            if(trainSummaryForm.getTrainId() != null){
                trainSummary.setTrain_id(trainSummaryForm.getTrainId());
            }
            if(trainSummaryForm.getOperatorId() != null){
                trainSummary.setOperatorId(trainSummaryForm.getOperatorId());
            }
            if(trainSummaryForm.getSubmitDate() != null){
                trainSummary.setSubmitDate(trainSummaryForm.getSubmitDate());
            }
            if(trainSummaryForm.getSummaryName() != null){
                trainSummary.setSummaryName(trainSummaryForm.getSummaryName());
            }
            if(trainSummaryForm.getAttachmentPath() != null){
                trainSummary.setAttachmentPath(trainSummaryForm.getAttachmentPath());
            }
            if(trainSummaryForm.getOrgName() != null){
                trainSummary.setOrgName(trainSummaryForm.getOrgName());
            }
            if(trainSummaryForm.getParentOrgName() != null){
                trainSummary.setParentOrgName(trainSummaryForm.getParentOrgName());
            }
            if(trainSummaryForm.getUserName() != null){
                trainSummary.setUserName(trainSummaryForm.getUserName());
            }
            if(trainSummaryForm.getIsTrainEffect() != null){
                trainSummary.setIsTrainEffect(trainSummaryForm.getIsTrainEffect());
            }
            if(trainSummaryForm.getMultiplyTimes() != null){
                trainSummary.setMultiplyTimes(trainSummaryForm.getMultiplyTimes());
            }
            if(trainSummaryForm.getTotalFee() != null){
                trainSummary.setTotalFee(trainSummaryForm.getTotalFee());
            }
            if(trainSummaryForm.getCompletedNum() != null){
                trainSummary.setCompletedNum(trainSummaryForm.getCompletedNum());
            }
            if(trainSummaryForm.getKeyCompletedRadio() != null){
                trainSummary.setKeyCompletedRadio(trainSummaryForm.getKeyCompletedRadio());
            }
            if(trainSummaryForm.getTotalNum() != null){
                trainSummary.setTotalNum(trainSummaryForm.getTotalNum());
            }
            if(trainSummaryForm.getCalibratedNum() != null){
                trainSummary.setCalibratedNum(trainSummaryForm.getCalibratedNum());
            }
            if(trainSummaryForm.getComment() != null){
                trainSummary.setComment(trainSummaryForm.getComment());
            }
            if(trainSummaryForm.getTrainPlanExeRate() != null){
                trainSummary.setTrainPlanExeRate(trainSummaryForm.getTrainPlanExeRate());
            }
            if(trainSummaryForm.getLearnFinishedRate() != null){
                trainSummary.setLearnFinishedRate(trainSummaryForm.getLearnFinishedRate());
            }
            if(trainSummaryForm.getNeedFinishedNum() != null){
                trainSummary.setNeedFinishedNum(trainSummaryForm.getNeedFinishedNum());
            }
            if(trainSummaryForm.getTotalTime() != null){
                trainSummary.setTotalTime(trainSummaryForm.getTotalTime());
            }
            if(trainSummaryForm.getAverageTime() != null){
                trainSummary.setAverageTime(trainSummaryForm.getAverageTime());
            }
            if(trainSummaryForm.getTenantId() != null){
                trainSummary.setTenantId(trainSummaryForm.getTenantId());
            }
            if(trainSummaryForm.getLastModifiedDate() != null){
                trainSummary.setLastModifiedDate(trainSummaryForm.getLastModifiedDate());
            }
            if(trainSummaryForm.getLastEditorOperatorId() != null){
                trainSummary.setLastEditorOperatorId(trainSummaryForm.getLastEditorOperatorId());
            }
            if(trainSummaryForm.getLastRefreshDataTime() != null){
                trainSummary.setLastRefreshDataTime(trainSummaryForm.getLastRefreshDataTime());
            }
            if(trainSummaryForm.getConclusion() != null){
                trainSummary.setConclusion(trainSummaryForm.getConclusion());
            }
            if(trainSummaryForm.getOverallProfile() != null){
                trainSummary.setOverallProfile(trainSummaryForm.getOverallProfile());
            }
            n = this.trainSummaryMapper.updateByPrimaryKeySelective(trainSummary);
        }
        return n;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public int deleteSummaryForms(String[] delete_ids){
        int n = 0;

        for(int i=0;i<delete_ids.length;i++){
            String id = delete_ids[i];
            int temp = Integer.parseInt(id);
            n = this.trainSummaryMapper.deleteByPrimaryKey(temp);
        }

        return  n;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public Map<String,Object> getTrainingSummary(HttpServletRequest request){

        int train_id = 0;
        boolean  userJoinTrainFlag = false;
        if(request.getParameter("train_id")!=null){
            train_id = Integer.parseInt(request.getParameter("train_id"));
        } else{
            train_id = (Integer)request.getAttribute("train_id");
        }
        TrainWithBLOBs train = this.onlineTrainingService.findById(train_id);
        int tenantId = train.getTenantId();
        TrainSummaryForm ownTrainSummary = new TrainSummaryForm();
        String summaryFlag = request.getParameter("summaryFlag");
        String userName = request.getParameter("userName");
        String parentOrgName = request.getParameter("parentOrgName");
        EosOperator operator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);

        Map<String,Object> conditionMap = new HashMap<>();
        if(operator != null){
            int operatorId = operator.getOperatorId();
            userJoinTrainFlag = this.userTrainService.ifOperatorJoinTrain(operator.getOperatorId(), train_id);
            conditionMap.put("operatorId", operatorId);
        }
        conditionMap.put("trainId", train_id);
        conditionMap.put("summaryFlag", summaryFlag);//标识我的总结和其他总结
        conditionMap.put("userJoinTrainFlag", userJoinTrainFlag);
        if(userName!=null && !userName.equals("")){
            conditionMap.put("userName", userName);
            request.setAttribute("userName", userName);
        }
        if(parentOrgName!=null && !parentOrgName.equals("")){
            conditionMap.put("parentOrgName", parentOrgName);
            request.setAttribute("parentOrgName", parentOrgName);
        }

        List<TrainSummaryWithBLOBs>  trainSummaryList = this.queryUserTrainSummaryList(conditionMap);

        List<TrainSummaryForm> trainSummaryFormList = this.getTrainSummaryFormList(trainSummaryList);
        if(summaryFlag==null && operator!=null){
            if(trainSummaryFormList.size()>0){
                ownTrainSummary = trainSummaryFormList.get(0);//我的培训总结
                int trainSummaryId = ownTrainSummary.getId();
                Map<String,Object> map = new HashMap<>();
                map.put("bookId", trainSummaryId);
                request.setAttribute("trainSummaryId", trainSummaryId);
                map.put("discussType", 1705);
                map.put("isReply", 0);
                List<RsmRcmBookDiscuss> rsmRcmBookDiscussList = this.rsmRcmbookdiscussService.queryRsmRcmbookdiscussList(map);
                request.setAttribute("rsmRcmbookdiscussList", rsmRcmBookDiscussList);//别人评论及回复
            }
        }
        Tenant tenant = this.tenantService.findById(tenantId);
        List<EosorgTOrganization> subOrgList = this.eosorgTOrganizationService.findOneStepSubOrg(new int[]{tenant.getOrgId()});
        request.setAttribute("subOrgList", subOrgList);
        request.setAttribute("ownTrainSummary", ownTrainSummary);
        request.setAttribute("trainSummaryFormList",trainSummaryFormList);
        request.setAttribute("train", train);
        request.setAttribute("train_id", train_id);
        request.setAttribute("summaryFlag", summaryFlag);
        request.setAttribute("userJoinTrainFlag", userJoinTrainFlag);

        Map<String,Object> returnMap = new HashMap<>();
        returnMap.put("summaryFlag",summaryFlag);
        returnMap.put("userJoinTrainFlag",userJoinTrainFlag);

        return returnMap;
    }

    @Transactional(rollbackFor = {Exception.class })
    public List<TrainSummaryWithBLOBs> queryUserTrainSummaryList(Map<String,Object> map){
        if(map.get("currentOrgId") != null && Integer.parseInt(map.get("currentOrgId").toString()) != 1){
            List<Integer> tenantIdList = new ArrayList<>();
            int currentOrgId = Integer.parseInt(map.get("currentOrgId").toString());

            //=======================此方法未完成=========gss edit 2019年12月13日11:09:25===========
            //tenantIdList = this.eosorgTOrganizationService.findOrgAndSubTenantIdList(currentOrgId);

            if(tenantIdList != null && tenantIdList.size()>0){
                map.put("tenantIdList", tenantIdList);
            }
        }
        return this.trainSummaryMapper.queryUserTrainSummaryList(map);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public List<TrainSummaryForm> getTrainSummaryFormList(List<TrainSummaryWithBLOBs> trainSummaryList){
        List<TrainSummaryForm> trainSummaryFormList = new ArrayList<>();
        EosOperator temp = new EosOperator();
        for(TrainSummaryWithBLOBs trainSummary : trainSummaryList){
            if(trainSummary != null){
                TrainSummaryForm trainSummaryForm = new TrainSummaryForm();
                if(trainSummary.getId()!=null){
                    trainSummaryForm.setId(trainSummary.getId());
                }
                if(trainSummary.getSummaryName()!=null){
                    trainSummaryForm.setSummaryName(trainSummary.getSummaryName());
                }
                if(trainSummary.getOrgName()!=null){
                    trainSummaryForm.setOrgName(trainSummary.getOrgName());
                }
                if(trainSummary.getParentOrgName()!=null){
                    trainSummaryForm.setParentOrgName(trainSummary.getParentOrgName());
                }
                if(trainSummary.getConclusion()!=null){
                    trainSummaryForm.setConclusion(trainSummary.getConclusion());
                }
                if(trainSummary.getTrain_id()!=null){
                    trainSummaryForm.setTrainId(trainSummary.getTrain_id());
                }
                if(trainSummary.getSubmitDate()!=null){
                    trainSummaryForm.setSubmitDate(trainSummary.getSubmitDate());
                }
                if(trainSummary.getIsTrainEffect()!=null){
                    trainSummaryForm.setIsTrainEffect(trainSummary.getIsTrainEffect());
                } else{
                    trainSummaryForm.setIsTrainEffect(0);
                }
                if(trainSummary.getTrain_id()!=null){
                    TrainWithBLOBs train = this.onlineTrainingService.findById(trainSummary.getTrain_id());
                    if(train!=null && train.getTrainName()!=null){
                        String trainName = train.getTrainName();
                        trainSummaryForm.setTrainName(trainName);
                    }
                }
                if(trainSummary.getOperatorId() != null){
                    temp = this.eosOperatorService.findById(trainSummary.getOperatorId());
                    if(temp != null ){
                        if(temp.getOperatorName() != null){
                            trainSummaryForm.setOperatorName(temp.getOperatorName());
                        }
                        //设置orgName，orgName=提交年度总结的人所在的部门
                        if(trainSummary.getIsTrainEffect() != null && trainSummary.getIsTrainEffect() != 2){ //不是所级的年度总结
                            if(temp.getEosorgTEmployee() != null && temp.getEosorgTEmployee().getEosorgTOrganization() != null && temp.getEosorgTEmployee().getEosorgTOrganization().getOrgName()!=null){
                                trainSummaryForm.setOrgName(temp.getEosorgTEmployee().getEosorgTOrganization().getOrgName());
                            }
                        }else{
                            if(trainSummary.getOrgName() == null && trainSummary.getTenantId()!=null){
                                EosorgTOrganization eo = this.eosorgTOrganizationService.findByTenantId(trainSummary.getTenantId());
                                if(null != eo){
                                    trainSummary.setOrgName(eo.getOrgName());
                                }
                            }
                        }
                    }
                }
                if(trainSummary.getCalibratedNum()!=null){
                    trainSummaryForm.setCalibratedNum(trainSummary.getCalibratedNum());
                }
                if(trainSummary.getCompletedNum()!=null){
                    trainSummaryForm.setCompletedNum(trainSummary.getCompletedNum());
                }
                if(trainSummary.getMultiplyTimes()!=null){
                    trainSummaryForm.setMultiplyTimes(trainSummary.getMultiplyTimes());
                }
                if(trainSummary.getKeyCompletedRadio()!=null){
                    trainSummaryForm.setKeyCompletedRadio(trainSummary.getKeyCompletedRadio());
                }
                if(trainSummary.getTotalNum()!=null){
                    trainSummaryForm.setTotalNum(trainSummary.getTotalNum());
                }
                if(trainSummary.getTotalFee()!=null){
                    trainSummaryForm.setTotalFee(trainSummary.getTotalFee());
                }
                if(trainSummary.getYear()!=null){
                    trainSummaryForm.setYear(trainSummary.getYear());
                }
                if(trainSummary.getAttachmentPath()!=null && !trainSummary.getAttachmentPath().equals("")){
                    trainSummaryForm.setAttachmentPath(trainSummary.getAttachmentPath());
                }
                if(trainSummary.getTrainPlanExeRate()!=null){
                    trainSummaryForm.setTrainPlanExeRate(trainSummary.getTrainPlanExeRate());
                }
                if(trainSummary.getLearnFinishedRate()!=null){
                    trainSummaryForm.setLearnFinishedRate(trainSummary.getLearnFinishedRate());
                }
                if(trainSummary.getNeedFinishedNum()!=null){
                    trainSummaryForm.setNeedFinishedNum(trainSummary.getNeedFinishedNum());
                }
                if(trainSummary.getTotalTime()!=null){
                    trainSummaryForm.setTotalTime(trainSummary.getTotalTime());
                }
                if(trainSummary.getAverageTime()!=null && !trainSummary.getAverageTime().equals("")){
                    trainSummaryForm.setAverageTime(trainSummary.getAverageTime());
                }
                if(trainSummary.getOverallProfile()!=null){
                    trainSummaryForm.setOverallProfile(trainSummary.getOverallProfile());
                }
                trainSummaryFormList.add(trainSummaryForm);
            }
        }
        return trainSummaryFormList;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public ServiceResponse saveOrupdateSubmit(HttpServletRequest request){

        String summaryName = request.getParameter("summaryName");
        String conclusion = request.getParameter("conclusion");
        String summary_id = request.getParameter("summary_id");
        String train_id = request.getParameter("train_id");
        EosOperator operator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);

        EosorgTEmployee employee = this.eosorgTEmployeeService.findById(operator.getOperatorId());
        String userName = operator.getOperatorName();
        Integer tenantId = operator.getTenantId();
        Tenant userTenant = this.tenantService.findById(tenantId);
        EosorgTOrganization organization = this.eosorgTOrganizationService.findById(this.eosorgTOrganizationService.findOrgId(employee.getOrgID()));
        Date currentDate = new Date();
        TrainSummaryWithBLOBs summary = null;
        if(summary_id==null||summary_id.trim().length()==0){
            //insert new summary
            summary = new TrainSummaryWithBLOBs();
        } else{
            //update summary
            summary = this.trainSummaryMapper.selectByPrimaryKey(Integer.parseInt(summary_id));
        }
        summary.setSubmitDate(currentDate);
        summary.setLastModifiedDate(currentDate);
        summary.setLastRefreshDataTime(currentDate);
        summary.setOperatorId(operator.getOperatorId());
        summary.setLastEditorOperatorId(operator.getOperatorId());
        summary.setUserName(userName);
        summary.setOrgName(organization.getOrgName());
        summary.setParentOrgName(userTenant.getTenantName());
        summary.setSummaryName(summaryName);
        summary.setConclusion(conclusion);
        summary.setTrain_id(Integer.parseInt(train_id));
        if(summary_id==null||summary_id.trim().length()==0){
            this.trainSummaryMapper.insert(summary);
        } else{
            this.trainSummaryMapper.updateByPrimaryKeySelective(summary);
        }
        /**
         * 填写心得体会获得积分
         */
        this.integralService.addTrainSummaryIntegral(summary_id, train_id, operator.getOperatorId().longValue(), conclusion);

        return ServiceResponse.createBySuccess(summary);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public ServiceResponse deleteSummary(HttpServletRequest request){

        if(request.getParameterValues("id") != null) {
            String[] delete_ids = request.getParameterValues("id");
            for(int i=0;i<delete_ids.length;i++){
                String id = delete_ids[i];
                int temp = Integer.parseInt(id);
                this.trainSummaryMapper.deleteByPrimaryKey(temp);
            }
        }
        return ServiceResponse.createBySuccess();

    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public ServiceResponse showTrainSummary(HttpServletRequest request){

        EosOperator operator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        String trainIdStr = request.getParameter("trainId");
        Integer trainId = Integer.valueOf(trainIdStr);
        TrainWithBLOBs train = this.onlineTrainingService.findById(trainId);
        int ifEnd = 0;
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String now = dateFormat.format(date);
        String endTime = train.getEndTime().toString();
        ifEnd = now.compareTo(endTime);

        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("ifEnd",ifEnd);

        //获取培训班是否有学时要求才能提交总结
        if(train.getNeededHours()!=null){
            resultMap.put("neededHourse",train.getNeededHours());
            //获取当前用户已学时长
            Map<String,Object> parmMap = new HashMap<>();
            parmMap.put("trainID",trainId);
            parmMap.put("userID",operator.getOperatorId());
            List<MtMixTrainUserTrainInfo> infoList = this.mtMixTrainUserTrainInfoService.getListByUserIDAndTrainID(parmMap);
            if(infoList.size()>0){
                MtMixTrainUserTrainInfo userInfo = infoList.get(0);
                if(userInfo.getStatistics()!=null && userInfo.getStatistics()!=""){
                    resultMap.put("learnedHours",userInfo.getStatistics());
                } else{
                    resultMap.put("learnedHours",0);
                }
            }else {
                resultMap.put("learnedHours",0);
            }
        }else{
            resultMap.put("neededHourse",0);
        }

        Map<String,Object> parmMap2 = new HashMap<>();
        parmMap2.put("train_id",trainId);
        parmMap2.put("isTrainEffect",3);

        List<TrainSummaryWithBLOBs> summaryList = this.trainSummaryMapper.findByExample(parmMap2);
        if(summaryList.size()>0){
            TrainSummary summaryTitle = summaryList.get(0);
            String subject = summaryTitle.getSummaryName();
            resultMap.put("subject",subject);
        }else{
            //参数为0表示没有
            resultMap.put("subject",0);
        }

        Map<String,Object> userMap = new HashMap<>();
        userMap.put("operatorId",operator.getOperatorId());
        userMap.put("train_id",trainId);
        userMap.put("isTrainEffect",0);

        summaryList = this.trainSummaryMapper.findByExample(userMap);
        if(summaryList.size()>0){
            TrainSummary summary = summaryList.get(0);
            resultMap.put("summary",summary);
        }else{
            resultMap.put("summary",0);
        }

        return ServiceResponse.createBySuccess(resultMap);

    }


}
