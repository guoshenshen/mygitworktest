package com.elearning.service.integralTask;

import com.elearning.dao.integralTask.IntegralMapper;
import com.elearning.pojo.integralTask.*;
import com.elearning.pojo.mixtraining.MtMixTrainUserTrainInfo;
import com.elearning.pojo.pub.TrainWithBLOBs;
import com.elearning.pojo.pub.UserForm;
import com.elearning.service.pub.IEosorgTEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("integralService")
public class IntegralService implements IIntegralService{

    @Autowired
    private IntegralMapper integralMapper;

    @Autowired
    private IIntegralRuleService integralRuleService;

    @Autowired
    private IIntegralOfflineTrainRecordService integralOfflineTrainRecordService;

    @Autowired
    private IIntegralRecordService integralRecordService;

    @Autowired
    private IEosorgTEmployeeService eosorgTEmployeeService;

    @Autowired
    private IIntegralSummaryHistoryService integralSummaryHistoryService;

    @Override
    public Integral selectByPrimaryKey(Integer id){
        return this.integralMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public boolean addInnerAndOuterTrainIntegral(MtMixTrainUserTrainInfo mtMixTrainUserTrainInfo, TrainWithBLOBs train){

        Long trainId = mtMixTrainUserTrainInfo.getTrainID().longValue();
        Long operatorId = mtMixTrainUserTrainInfo.getUserID().longValue();
        String userName = mtMixTrainUserTrainInfo.getUserName();
        Double classHour = train.getClassHour();

         //获取积分规则
        IntegralRule ir = new IntegralRule();
        //ir.setStatus("1");
        //规则code是写死的
        //ir.setItemCode("offline_train");

        Map<String,Object> parmM = new HashMap<>();
        parmM.put("status",1);
        parmM.put("itemCode","offline_train");

        List<IntegralRule> irList = this.integralRuleService.findListByStatusAndItemCode(parmM);
        if(irList!=null && irList.size()>0){
            ir = irList.get(0);
            if(ir.getScore()>0){
                //首先判断学员是否已经获取了参加该培训班的积分
                IntegralOfflineTrainRecord iotr = new IntegralOfflineTrainRecord();
                iotr.setUserId(operatorId);
                iotr.setTrainId(trainId);
                iotr.setStatus("1");

                Map<String,Object> map = new HashMap<>();
                map.put("userId",operatorId);
                map.put("trainId",trainId);
                map.put("status",1);


                List<IntegralOfflineTrainRecord> iotrList = this.integralOfflineTrainRecordService.getListByUserIdAndTrainIdAndStatus(map);
                if(!(iotrList !=null && iotrList.size()>0)){
                    //学时
                    iotr.setClassHour(classHour);
                    iotr.setRecordTime(new Date());
                    this.integralOfflineTrainRecordService.saveAndGetIntegralOfflineTrainRecord(iotr);

                    //记录record表

                    IntegralRecord ird = new IntegralRecord();
                    ird.setUpdateCauseId("4");
                    ird.setUpdateCause("" + "参加培训班获取积分");
                    ird.setRuleId(ir.getId());
                    ird.setOfferScore((classHour*ir.getScore()));
                    ird.setStudentId(operatorId);
                    ird.setIntegralHistoryName("integral_offlinetrain_record");
                    ird.setIntegralHistoryId(iotr.getId());
                    ird.setRecordTime(new Date());
                    List<IntegralRecord> neweastRecordList = this.integralRecordService.findNewestRecord();
                    if(neweastRecordList!=null && neweastRecordList.size()>0){
                        ird.setPreRecordId(neweastRecordList.get(0).getId());
                    }
                    this.integralRecordService.saveIntegralRecord(ird);

                    //操作integral，修改积分

                    Integral integral = new Integral();
                    integral.setUserId(operatorId);
                    integral.setStatus("1");

                    Map<String,Object> parMap = new HashMap<>();
                    parMap.put("userId",operatorId);
                    parMap.put("status",1);

                    List<Integral> inList = this.integralMapper.getListByUserIdAndStatus(parMap);
                    if(inList!=null && inList.size()>0){
                        integral = inList.get(0);
                    }else{
                        UserForm uForm = this.eosorgTEmployeeService.findByUserId(operatorId.intValue());
                        integral.setOrgId(uForm.getOrgId().longValue());
                        integral.setOrgName(uForm.getOrgName());
                        integral.setUserName(uForm.getOperatorName());
                        //integral.setUserName(userName);
                        this.integralMapper.insert(integral);
                    }
                    integral.setUpdateTime(new Date());
                    integral.setTotalScore((long)(integral.getTotalScore()==null?ir.getScore()*classHour:(integral.getTotalScore()+ir.getScore()*classHour)));
                    integral.setOfflineTrainScore(integral.getOfflineTrainScore()==null?ir.getScore()*classHour:(integral.getOfflineTrainScore()+ir.getScore()*classHour));
                    this.integralMapper.updateByPrimaryKeySelective(integral);
                } else{
                    //已获得积分?
                    iotr = iotrList.get(0);
                    Double classHour0 = iotr.getClassHour();
                    iotr.setClassHour(classHour);
                    iotr.setRecordTime(new Date());
                    this.integralOfflineTrainRecordService.updateIntegralOfflineTrainRecord(iotr);

                    //更新记录record表

                    IntegralRecord ird = new IntegralRecord();
                    ird.setStudentId(operatorId);
                    ird.setIntegralHistoryName("integral_offlinetrain_record");
                    ird.setIntegralHistoryId(iotr.getId());

                    Map<String,Object> parmMap = new HashMap<>();
                    parmMap.put("studentId",operatorId);
                    parmMap.put("integralHistoryName","integral_offlinetrain_record");
                    parmMap.put("integralHistoryId",iotr.getId());

                    List<IntegralRecord> irdList = this.integralRecordService.getListByStudentIdAndHistoryNameAndHistoryId(parmMap);
                    if(irdList!=null && irdList.size()>0){
                        ird = irdList.get(0);
                        ird.setOfferScore(ir.getScore()*classHour);
                        ird.setRecordTime(new Date());
                        this.integralRecordService.updateIntegralRecord(ird);
                    } else{
                    }

                    //操作integral，修改积分
                    Integral integral = new Integral();
                    integral.setUserId(operatorId);
                    integral.setStatus("1");

                    Map<String,Object> parMap = new HashMap<>();
                    parMap.put("userId",operatorId);
                    parMap.put("status",1);

                    List<Integral> inList = this.integralMapper.getListByUserIdAndStatus(parMap);
                    if(inList!=null && inList.size()>0){
                        integral = inList.get(0);
                    } else{
                        UserForm uForm = this.eosorgTEmployeeService.findByUserId(operatorId.intValue());
                        integral.setOrgId(uForm.getOrgId().longValue());
                        integral.setOrgName(uForm.getOrgName());
                        integral.setUserName(uForm.getOperatorName());
                        this.integralMapper.insertSelective(integral);
                    }
                    integral.setUpdateTime(new Date());
                    integral.setTotalScore(new Double((integral.getTotalScore()==null?ir.getScore()*classHour:integral.getTotalScore()+ir.getScore()*(classHour-classHour0))).longValue());
                    integral.setOfflineTrainScore(integral.getOfflineTrainScore()==null?ir.getScore()*classHour:integral.getOfflineTrainScore()+ir.getScore()*(classHour-classHour0));
                    this.integralMapper.updateByPrimaryKeySelective(integral);

                }
            }
        }
        return true;

    }

    /**
     * 填写心得体会获得积分
     * @param summaryId
     * @param trainId
     * @param operatorId
     * @param conclusion
     * @return
     */
    @Override
    @Transactional(rollbackFor = {Exception.class })
    public boolean addTrainSummaryIntegral(String summaryId,String trainId,Long operatorId,String conclusion){
        Long summary_Id = 0L;
        if(!"".equals(summaryId)){
            summary_Id = Long.parseLong(summaryId);
        }
        Integer conLength = conclusion.length();
        String ruleCode = "";
        if(20 < conLength && conLength<49){
            ruleCode = "summary_20_49";
        } else if(50 < conLength && conLength<99){
            ruleCode = "summary_50_99";
        } else if(100 < conLength){
            ruleCode = "summary_100_";
        }
        /***
         * 获取积分规则
         */
        IntegralRule ir = new IntegralRule();

        Map<String,Object> parmMap = new HashMap<>();
        parmMap.put("status",1);
        //规则code是写死的
        parmMap.put("itemCode",ruleCode);

        List<IntegralRule> irList = this.integralRuleService.findListByStatusAndItemCode(parmMap);
        if(irList!=null && irList.size()>0){
            ir = irList.get(0);
            if(ir.getScore() > 0){
                //首先,判断学员是否对该培训班写过心得体会..
                IntegralSummaryHistory ish = new IntegralSummaryHistory();
                ish.setTrainId(Long.parseLong(trainId));
                ish.setUserId(operatorId);
                ish.setStatus("1");

                Map<String,Object> parmMap2 = new HashMap<>();
                parmMap2.put("trainId",Long.parseLong(trainId));
                parmMap2.put("userId",operatorId);
                parmMap2.put("status","1");

                List<IntegralSummaryHistory> list = this.integralSummaryHistoryService.getListByTrainIdAndUserIdAndStatus(parmMap2);
                /**
                 * 如果填写心得体会在积分模块有获取积分的记录，则去修改该次记录对应的积分
                 */
                if(list!=null && list.size()>0){
                    //return false;
                    ish = list.get(0);
                    /**
                     * 修改IntegralRecord
                     */
                    IntegralRecord ird = new IntegralRecord();

                    Map<String,Object> parmMap3 = new HashMap<>();
                    parmMap3.put("updateCauseId","3");
                    parmMap3.put("studentId",operatorId);
                    parmMap3.put("integralHistoryName","integral_summary_history");
                    parmMap3.put("integralHistoryId",ish.getId());

                    List<IntegralRecord> integralRecordList = this.integralRecordService.getListByMap(parmMap3);
                    if(integralRecordList != null && integralRecordList.size() > 0){
                        ird = integralRecordList.get(0);
                        Double preOfferScore = ird.getOfferScore();
                        Double newOfferScore = ir.getScore().doubleValue();

                        ird.setOfferScore(newOfferScore);
                        ird.setRecordTime(new Date());
                        this.integralRecordService.updateIntegralRecord(ird);

                        /**
                         * 操作integral，修改积分
                         */
                        Integral integral = new Integral();
                        integral.setUserId(operatorId);
                        integral.setStatus("1");

                        Map<String,Object> parmMap4 = new HashMap<>();
                        parmMap4.put("userId",operatorId);
                        parmMap4.put("status","1");

                        List<Integral> inList = this.integralMapper.getListByUserIdAndStatus(parmMap4);
                        if(inList!=null && inList.size()>0){
                            integral = inList.get(0);
                        } else{
                            UserForm uForm = this.eosorgTEmployeeService.findByUserId(operatorId.intValue());
                            integral.setOrgId(uForm.getOrgId().longValue());
                            integral.setOrgName(uForm.getOrgName());
                            integral.setUserName(uForm.getOperatorName());
                            this.integralMapper.insert(integral);
                        }
                        integral.setUpdateTime(new Date());
                        integral.setTotalScore(integral.getTotalScore()==null?ir.getScore():integral.getTotalScore()+(long)Math.floor((newOfferScore-preOfferScore)));
                        integral.setSummaryScore(integral.getSummaryScore()==null?ir.getScore():integral.getSummaryScore()+(long)Math.floor((newOfferScore-preOfferScore)));
                        this.integralMapper.updateByPrimaryKeySelective(integral);
                    }
                } else{
                    /**
                     * 记录表integral_summary_history
                     */
                    ish.setIntegralRuleId(ir.getId());
                    ish.setSummaryId(summary_Id);
                    ish.setSummaryCount(conLength.toString());
                    ish.setSummaryTime(new Date());

                    this.integralSummaryHistoryService.saveIntegralSummaryHistory(ish);

                    /**
                     * 插入integral_record
                     */
                    IntegralRecord ird = new IntegralRecord();
                    ird.setUpdateCauseId("3");
                    ird.setUpdateCause("填写培训班心得体会获取积分");
                    ird.setRuleId(ir.getId());
                    ird.setOfferScore(ir.getScore().doubleValue());
                    ird.setStudentId(operatorId);
                    ird.setIntegralHistoryName("integral_summary_history");
                    ird.setIntegralHistoryId(ish.getId());
                    ird.setRecordTime(new Date());

                    List<IntegralRecord> neweastRecordList = this.integralRecordService.findNewestRecord();

                    if(neweastRecordList!=null && neweastRecordList.size()>0){
                        ird.setPreRecordId(neweastRecordList.get(0).getId());
                    }
                    //ird = this.getIntegralRecordService().saveIntegralRecord(ird);
                    this.integralRecordService.saveIntegralRecord(ird);
                    /**
                     * 操作integral，修改积分
                     */
                    Integral integral = new Integral();
                    integral.setUserId(operatorId);
                    integral.setStatus("1");

                    Map<String,Object> parmMap5 = new HashMap<>();
                    parmMap5.put("userId",operatorId);
                    parmMap5.put("status","1");

                    List<Integral> inList = this.integralMapper.getListByUserIdAndStatus(parmMap5);
                    if(inList!=null && inList.size()>0){
                        integral = inList.get(0);
                    } else{
                        UserForm uForm = this.eosorgTEmployeeService.findByUserId(operatorId.intValue());
                        integral.setOrgId(uForm.getOrgId().longValue());
                        integral.setOrgName(uForm.getOrgName());
                        integral.setUserName(uForm.getOperatorName());
                        this.integralMapper.insert(integral);
                    }
                    integral.setUpdateTime(new Date());
                    integral.setTotalScore(integral.getTotalScore()==null?ir.getScore():integral.getTotalScore()+ir.getScore());
                    integral.setSummaryScore(integral.getSummaryScore()==null?ir.getScore():integral.getSummaryScore()+ir.getScore());
                    this.integralMapper.updateByPrimaryKeySelective(integral);
                }
            }
        }
        return false;
    }




}
