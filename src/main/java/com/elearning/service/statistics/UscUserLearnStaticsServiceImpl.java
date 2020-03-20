package com.elearning.service.statistics;

import com.elearning.common.Constants;
import com.elearning.common.Tools;
import com.elearning.dao.statistics.UscUserLearnStaticsMapper;
import com.elearning.pojo.pub.EosOperator;
import com.elearning.pojo.pub.EosorgTEmployee;
import com.elearning.pojo.pub.EosorgTOrganization;
import com.elearning.pojo.statistics.UscUserLearnStatics;
import com.elearning.pojo.statistics.UscUserLearnStaticsKey;
import com.elearning.pojo.systemManage.Tenant;
import com.elearning.service.courseStudy.IUscUsertliddayStudyInfoService;
import com.elearning.service.pub.IEosOperatorService;
import com.elearning.service.pub.IEosorgTEmployeeService;
import com.elearning.service.pub.IEosorgTOrganizationService;
import com.elearning.vo.statistics.UscUserLearnStaticsYearSum;
import com.elearning.vo.statistics.UscUserLearnStaticsYearSumDouble;
import com.elearning.vo.statistics.UscUserLearnStaticsYearSumForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;

/**
 * 〈<br>
 * 〈〉
 *
 * @author lxx
 * @create 2019/10/15 11:22
 */
@Service("uscUserLearnStaticsService")
public class UscUserLearnStaticsServiceImpl implements IUscUserLearnStaticsService {

    @Autowired
    private UscUserLearnStaticsMapper uscUserLearnStaticsMapper;

    @Autowired
    private IEosorgTEmployeeService eosorgTEmployeeService;

    @Autowired
    private IEosOperatorService eosOperatorService;

    @Autowired
    private IEosorgTOrganizationService eosorgTOrganizationService;

    @Autowired
    private IUscUsertliddayStudyInfoService uscUsertliddayStudyInfoService;


    @Override
    @Transactional(rollbackFor = {Exception.class})
    public int insertOnlineTime(Integer userId, Long courseId, Date now, long needAddedTime) {
        int result = 0 ;
        Integer year = now.getYear()+1900;
        Integer month = now.getMonth()+1;
        UscUserLearnStaticsKey uscUserLearnStaticsKey = new UscUserLearnStaticsKey(userId,year,month);
        UscUserLearnStatics uscUserLearnStatics = uscUserLearnStaticsMapper.selectByPrimaryKey(uscUserLearnStaticsKey);
        if(uscUserLearnStatics == null ){
            EosOperator eosOperator = eosOperatorService.selectByPrimaryKey(userId);
            EosorgTEmployee eosorgTEmployee = eosorgTEmployeeService.findById(userId);
            EosorgTOrganization eosorgTOrganization = eosorgTOrganizationService.
                    getEosorgTOrganizationById(eosorgTOrganizationService.findOrgId(eosorgTEmployee.getOrgID()));
            if(eosorgTOrganization != null ){
                uscUserLearnStatics.setOperatorName(eosOperator.getOperatorName());
                uscUserLearnStatics.setOrgId(eosorgTOrganization.getOrgID());
                uscUserLearnStatics.setOrgName(eosorgTOrganization.getOrgName());
                uscUserLearnStatics.setOnlineTime(needAddedTime);
                uscUserLearnStatics.setTotalTime(needAddedTime);
                uscUserLearnStatics.setOperatorId(userId);
                uscUserLearnStatics.setMonth(month);
                uscUserLearnStatics.setYear(year);
                Tenant tenant = this.eosorgTOrganizationService.findTenant(eosorgTOrganization.getOrgID());
                if(tenant!=null){
                    uscUserLearnStatics.setTenantId(tenant.getTenantId());
                    result = uscUserLearnStaticsMapper.insertSelective(uscUserLearnStatics);
                }
            }
        }else{
            if(uscUserLearnStatics.getOnlineTime()==null){
                uscUserLearnStatics.setOnlineTime(needAddedTime);
            }else{
                uscUserLearnStatics.setOnlineTime(uscUserLearnStatics.getOnlineTime()+needAddedTime);
            }
            uscUserLearnStatics.setTotalTime(uscUserLearnStatics.getTotalTime()+needAddedTime);
            result = uscUserLearnStaticsMapper.updateByPrimaryKeySelective(uscUserLearnStatics);
        }
        //添加课程学习数目
        Map<String,Object> map = new HashMap<>();
        map.put("courseId",courseId);
        map.put("userID",userId);
        map.put("year",year);
        map.put("month",month);
        if(uscUsertliddayStudyInfoService.isAllreadyLearned(map) == false){
            Integer courseNum = 0;
            if(uscUserLearnStatics !=null && uscUserLearnStatics.getCourseNum()!=null){
                courseNum = uscUserLearnStatics.getCourseNum();
                uscUserLearnStatics.setCourseNum(courseNum+1);
                uscUserLearnStaticsMapper.updateByPrimaryKeySelective(uscUserLearnStatics);
            }
        }
        return result;
    }

    @Override
    public UscUserLearnStatics selectByPrimaryKey(UscUserLearnStaticsKey key){
        return this.uscUserLearnStaticsMapper.selectByPrimaryKey(key);
    }

    @Override
    public int updateByPrimaryKeySelective(UscUserLearnStatics record){
        return this.uscUserLearnStaticsMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int insertSelective(UscUserLearnStatics record){
        return this.uscUserLearnStaticsMapper.insertSelective(record);
    }

    @Override
    public void averageDistributeTimeToMonth(Integer operatorId,Integer _year,Date startTime,Date endTime,Double classHour){
        this.averageDistributeOutTimeToMonth(operatorId, _year, null, startTime, endTime, classHour);
    }

    public void averageDistributeOutTimeToMonth(Integer operatorId, Integer _year,Integer outTrainType, Date startTime, Date endTime, Double classHour) {
        HashMap<String,String> monthTrainTime = Tools.averageDistributeTrainTime(startTime,endTime,classHour);
        Iterator titer=monthTrainTime.entrySet().iterator();
        while(titer.hasNext()){
            Map.Entry ent=(Map.Entry)titer.next();
            String year_month = (ent.getKey().toString());
            Integer year = Integer.valueOf(year_month.substring(0,year_month.indexOf("~")));
            Integer month = Integer.valueOf(year_month.substring(year_month.indexOf("~")+1));
            Double temp = Double.valueOf(ent.getValue().toString());
            BigDecimal b1 = new BigDecimal(Double.toString(temp));
            BigDecimal b2 = new BigDecimal(Double.toString(3600));
            Long trainTime = (long) (b1.multiply(b2).doubleValue());

            UscUserLearnStaticsKey uscUserlearnstaticsId = new UscUserLearnStaticsKey(operatorId,year,month);

            if(this.uscUserLearnStaticsMapper.selectByPrimaryKey(uscUserlearnstaticsId)!=null){
                //某人当月学时记录已经创建
                UscUserLearnStatics uscUserlearnstatics = this.uscUserLearnStaticsMapper.selectByPrimaryKey(uscUserlearnstaticsId);
                //累计总学时
                if(uscUserlearnstatics.getTotalTime()!=null){
                    uscUserlearnstatics.setTotalTime(uscUserlearnstatics.getTotalTime()+trainTime);
                } else{
                    uscUserlearnstatics.setTotalTime(trainTime);
                }
                if(outTrainType!=null){
                    if(outTrainType==5202){
                        //在职自学
                        if(uscUserlearnstatics.getOfflineSelfLearningTime()!=null){
                            uscUserlearnstatics.setOfflineSelfLearningTime(uscUserlearnstatics.getOfflineSelfLearningTime()+trainTime);
                        } else{
                            uscUserlearnstatics.setOfflineSelfLearningTime(trainTime);
                        }
                    } else if(outTrainType==5203){
                        //外部网络学时
                        if(uscUserlearnstatics.getOnlineTime2()!=null){
                            uscUserlearnstatics.setOnlineTime2(uscUserlearnstatics.getOnlineTime2()+trainTime);
                        } else{
                            uscUserlearnstatics.setOnlineTime2(trainTime);
                        }
                    }else if(outTrainType==5204){
                        //公派留学
                        if(uscUserlearnstatics.getOverseasStudyTime()!=null){
                            uscUserlearnstatics.setOverseasStudyTime(uscUserlearnstatics.getOverseasStudyTime()+trainTime);
                        } else{
                            uscUserlearnstatics.setOverseasStudyTime(trainTime);
                        }
                    } else if(outTrainType==5201){
                        //外部培训
                        //累计培训学时(包括：培训班学时+外部培训学时)
                        if(uscUserlearnstatics.getOfflineTrainTime()==null){
                            uscUserlearnstatics.setOfflineTrainTime(trainTime);
                        } else{
                            uscUserlearnstatics.setOfflineTrainTime(uscUserlearnstatics.getOfflineTrainTime()+trainTime);
                        }
                    }
                } else{
                    //累计培训学时(包括：培训班学时+外部培训学时)
                    if(uscUserlearnstatics.getOfflineTrainTime()==null){
                        uscUserlearnstatics.setOfflineTrainTime(trainTime);
                    } else{
                        uscUserlearnstatics.setOfflineTrainTime(uscUserlearnstatics.getOfflineTrainTime()+trainTime);
                    }
                }
                this.uscUserLearnStaticsMapper.updateByPrimaryKeySelective(uscUserlearnstatics);
            }else{
                //某人当月学时记录尚未创建
                //UscUserlearnstatics uscUserlearnstatics = new UscUserlearnstatics(uscUserlearnstaticsId);
                UscUserLearnStatics uscUserlearnstatics = new UscUserLearnStatics();
                //operatorId,year,month
                uscUserlearnstatics.setOperatorId(operatorId);
                uscUserlearnstatics.setYear(year);
                uscUserlearnstatics.setMonth(month);

                EosOperator operator = this.eosOperatorService.findById(operatorId);
                EosorgTEmployee employee=null;
                EosorgTOrganization org=null;

                if(operator!=null){
                    employee=this.eosorgTEmployeeService.findById(operatorId);
                    if(employee!=null){
                        org=this.eosorgTEmployeeService.findOrg(employee, true);
                    }
                }
                if(org!=null){
                    uscUserlearnstatics.setOperatorName(operator.getOperatorName());
                    uscUserlearnstatics.setOrgId(org.getOrgID());
                    uscUserlearnstatics.setOrgName(org.getOrgName());
                    uscUserlearnstatics.setTotalTime(trainTime);
                    if(outTrainType!=null){
                        if(outTrainType==5202){
                            uscUserlearnstatics.setOfflineSelfLearningTime(trainTime);
                        }
                        else if(outTrainType==5203){
                            uscUserlearnstatics.setOnlineTime2(trainTime);
                        }else if(outTrainType==5204){
                            uscUserlearnstatics.setOverseasStudyTime(trainTime);
                        }
                        else if(outTrainType==5201){
                            uscUserlearnstatics.setOfflineTrainTime(trainTime);
                        }
                    } else{
                        uscUserlearnstatics.setOfflineTrainTime(trainTime);
                    }
                    Tenant tenant = this.eosorgTOrganizationService.findTenant(org);
                    if(tenant!=null){
                        uscUserlearnstatics.setTenantId(tenant.getTenantId());
                        this.uscUserLearnStaticsMapper.insertSelective(uscUserlearnstatics);
                    }
                }
            }
        }
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public List<UscUserLearnStaticsYearSumForm> queryUserlearnstaticsInProfile(Map<String,Object> map, Integer operatorId){

        String startTime = "2000-01";
        String endTime = "2050-01";
        if(map.get("startTime")!=null&&!map.get("startTime").toString().trim().equals("")){
            startTime = map.get("startTime").toString();
        }
        if(map.get("endTime")!=null&&!map.get("endTime").toString().trim().equals("")){
            endTime = map.get("endTime").toString();
        }
        List<UscUserLearnStaticsYearSum> uscUserlearnstaticsYearSumList = null;
        try {
            uscUserlearnstaticsYearSumList = this.queryUserlearnstaticsSeveralYears(startTime,endTime,operatorId);
        }catch (Exception e){
            e.printStackTrace();
        }
        List<UscUserLearnStaticsYearSumForm>  uscUserlearnstaticsYearSumFormList = new ArrayList<>();
        for(UscUserLearnStaticsYearSum uscUserlearnstaticsYearSum : uscUserlearnstaticsYearSumList){
            UscUserLearnStaticsYearSumForm uscUserlearnstaticsYearSumForm = getUscUserlearnstaticsYearSumForm(uscUserlearnstaticsYearSum);
            uscUserlearnstaticsYearSumFormList.add(uscUserlearnstaticsYearSumForm);
        }
        return uscUserlearnstaticsYearSumFormList;
    }

    /**
     * UscUserlearnstaticsYearSum转化为UscUserlearnstaticsYearSumForm对象
     * */
    public UscUserLearnStaticsYearSumForm getUscUserlearnstaticsYearSumForm(UscUserLearnStaticsYearSum uscUserlearnstaticsYearSum){
        UscUserLearnStaticsYearSumForm uscUserlearnstaticsYearSumForm = new UscUserLearnStaticsYearSumForm();
        if(uscUserlearnstaticsYearSum != null){
            uscUserlearnstaticsYearSumForm.setOperatorId(uscUserlearnstaticsYearSum.getOperatorId());
            uscUserlearnstaticsYearSumForm.setStartTime(uscUserlearnstaticsYearSum.getStartTime());
            uscUserlearnstaticsYearSumForm.setEndTime(uscUserlearnstaticsYearSum.getEndTime());
            try {
                uscUserlearnstaticsYearSumForm.setStartTimeDate(Tools.stringToDate(uscUserlearnstaticsYearSum.getStartTime()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            try {
                uscUserlearnstaticsYearSumForm.setEndTimeDate(Tools.stringToDate(uscUserlearnstaticsYearSum.getEndTime()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            uscUserlearnstaticsYearSumForm.setTotalTime(uscUserlearnstaticsYearSum.getTotalTimeMutiMonth()==0.0?"0小时0分":Tools.formatHours(uscUserlearnstaticsYearSum.getTotalTimeMutiMonth()));
            uscUserlearnstaticsYearSumForm.setTotalEffectiveTime(uscUserlearnstaticsYearSum.getTotalEffectiveTime()==0.0?"0小时0分":Tools.formatHours(uscUserlearnstaticsYearSum.getTotalEffectiveTime()));
            uscUserlearnstaticsYearSumForm.setTotalOfflineTrainTime(uscUserlearnstaticsYearSum.getTotalOfflineTrainTime()==0.0?"0小时0分":Tools.formatHours(uscUserlearnstaticsYearSum.getTotalOfflineTrainTime()));
            uscUserlearnstaticsYearSumForm.setTotalAllOnlineTime(uscUserlearnstaticsYearSum.getTotalAllOnlineTime()==0.0?"0小时0分":Tools.formatHours(uscUserlearnstaticsYearSum.getTotalAllOnlineTime()));
            uscUserlearnstaticsYearSumForm.setTotalEffectiveOnlineTime(uscUserlearnstaticsYearSum.getTotalEffectiveOnlineTime()==0.0?"0小时0分":Tools.formatHours(uscUserlearnstaticsYearSum.getTotalEffectiveOnlineTime()));
            uscUserlearnstaticsYearSumForm.setTotalOfflineSelfLearningTime(uscUserlearnstaticsYearSum.getTotalOfflineSelfLearningTime()==0.0?"0小时0分":Tools.formatHours(uscUserlearnstaticsYearSum.getTotalOfflineSelfLearningTime()));
            uscUserlearnstaticsYearSumForm.setTotalEffectiveOfflineSelfLearningTime(uscUserlearnstaticsYearSum.getTotalEffectiveOfflineSelfLearningTime()==0.0?"0小时0分":Tools.formatHours(uscUserlearnstaticsYearSum.getTotalEffectiveOfflineSelfLearningTime()));
            uscUserlearnstaticsYearSumForm.setTotalOverseasStudyTime(uscUserlearnstaticsYearSum.getTotalOverseasStudyTime()==0.0?"0小时0分":Tools.formatHours(uscUserlearnstaticsYearSum.getTotalOverseasStudyTime()));
            uscUserlearnstaticsYearSumForm.setTotalEffectiveOverseasStudyTime(uscUserlearnstaticsYearSum.getTotalEffectiveOverseasStudyTime()==0.0?"0小时0分":Tools.formatHours(uscUserlearnstaticsYearSum.getTotalEffectiveOverseasStudyTime()));
            uscUserlearnstaticsYearSumForm.setRemainingTrainingHours(uscUserlearnstaticsYearSum.getRemainingTrainingHours());
        }
        return uscUserlearnstaticsYearSumForm;
    }

    /**
     * 返回某个学员某段时间内(跨年度)学时时长统计列表(每一年为一条记录，返回年度列表)
     * */
    public List<UscUserLearnStaticsYearSum> queryUserlearnstaticsSeveralYears(String startTime, String endTime, Integer operatorId)  throws ParseException {

        int yearSpan = Tools.getYearSpan(startTime, endTime);

        List<UscUserLearnStaticsYearSum> uscUserlearnstaticsYearSumList = new ArrayList<>();
        List<HashMap> timeSpanList = Tools.getTimeSpanList(startTime, endTime);
        Map<String,Object> timeSpan = new HashMap();
        for (int i = 0; i < yearSpan; i++) {
            timeSpan = timeSpanList.get(i);
            String tempStartTime = timeSpan.get("startTime").toString();
            String tempEndTime = timeSpan.get("endTime").toString();
            UscUserLearnStaticsYearSum uscUserlearnstaticsYearSum = this.queryUserlearnstaticsInOneYear(tempStartTime, tempEndTime, operatorId);
            uscUserlearnstaticsYearSumList.add(uscUserlearnstaticsYearSum);
        }
        return uscUserlearnstaticsYearSumList;
    }

    public UscUserLearnStaticsYearSum queryUserlearnstaticsInOneYear(String startTime, String endTime, Integer operatorId) throws ParseException {
        Map<String,Object> parmMap = new HashMap<>();
        parmMap.put("startTime",startTime);
        parmMap.put("endTime",endTime);
        parmMap.put("operatorId",operatorId);

        UscUserLearnStaticsYearSumDouble doubleUsc = this.uscUserLearnStaticsMapper.queryUserlearnstaticsInOneYear(parmMap);

        int year = Tools.getYear(startTime);

        if(doubleUsc != null){
            UscUserLearnStaticsYearSum uscUserlearnstaticsYearSum = null;
            if (doubleUsc.getTotalTime() == null || Double.valueOf(doubleUsc.getTotalTime().toString()) == 0) {
                UscUserLearnStaticsYearSum uscUserLearnStaticsYearSum = new UscUserLearnStaticsYearSum();
                uscUserLearnStaticsYearSum.setOperatorId(operatorId);
                uscUserLearnStaticsYearSum.setYear(year);
                uscUserLearnStaticsYearSum.setMonth(null);
                uscUserLearnStaticsYearSum.setTotalTimeMutiMonth(0.0);
                uscUserLearnStaticsYearSum.setTotalEffectiveTime(0.0);
                uscUserLearnStaticsYearSum.setTotalAllOnlineTime(0.0);
                uscUserLearnStaticsYearSum.setTotalOnlineTime(0.0);
                uscUserLearnStaticsYearSum.setTotalOnlineTime2(0.0);
                uscUserLearnStaticsYearSum.setTotalEffectiveOnlineTime(0.0);
                uscUserLearnStaticsYearSum.setTotalOfflineTrainTime(0.0);
                uscUserLearnStaticsYearSum.setTotalOfflineSelfLearningTime(0.0);
                uscUserLearnStaticsYearSum.setTotalEffectiveOfflineSelfLearningTime(0.0);
                uscUserLearnStaticsYearSum.setTotalOverseasStudyTime(0.0);
                uscUserLearnStaticsYearSum.setTotalEffectiveOverseasStudyTime(0.0);
                uscUserLearnStaticsYearSum.setRemainingTrainingHours(Double.valueOf(Constants.Training_Total_Hour));

                uscUserlearnstaticsYearSum.setStartTime(startTime);
                uscUserlearnstaticsYearSum.setEndTime(endTime);
                return uscUserlearnstaticsYearSum;
            } else {// 总学时
                uscUserlearnstaticsYearSum = new UscUserLearnStaticsYearSum();
                uscUserlearnstaticsYearSum.setOperatorId(operatorId);
                uscUserlearnstaticsYearSum.setYear(year);
                uscUserlearnstaticsYearSum.setMonth(null);

                uscUserlearnstaticsYearSum.setTotalTimeMutiMonth(doubleUsc.getTotalTime());
            }
            if (doubleUsc.getTotalOnlineTime() != null) { // 继续教育网上自动记录的在线学时
                uscUserlearnstaticsYearSum.setTotalOnlineTime(doubleUsc.getTotalOnlineTime());
            } else{
                uscUserlearnstaticsYearSum.setTotalOnlineTime(0.0);
            }
            if (doubleUsc.getTotalOfflineTrainTime() != null) {// 培训学时(包括内部培训+外部培训学时)
                uscUserlearnstaticsYearSum.setTotalOfflineTrainTime(doubleUsc.getTotalOfflineTrainTime());
            } else{
                uscUserlearnstaticsYearSum.setTotalOfflineTrainTime(0.0);
            }
            if (doubleUsc.getTotalOnlineTime2() != null) { // 外部网络学时
                uscUserlearnstaticsYearSum.setTotalOnlineTime2(doubleUsc.getTotalOnlineTime2());
            } else{
                uscUserlearnstaticsYearSum.setTotalOnlineTime2(0.0);
            }
            if (doubleUsc.getTotalofflineSelfLearningTime() != null) {// 在职自学学时
                uscUserlearnstaticsYearSum.setTotalOfflineSelfLearningTime(doubleUsc.getTotalofflineSelfLearningTime());
            } else{
                uscUserlearnstaticsYearSum.setTotalOfflineSelfLearningTime(0.0);
            }
            if (doubleUsc.getTotalOverseasStudyTime() != null) {// 公派留学学时
                uscUserlearnstaticsYearSum.setTotalOverseasStudyTime(doubleUsc.getTotalOverseasStudyTime());
            } else{
                uscUserlearnstaticsYearSum.setTotalOverseasStudyTime(0.0);
            }

            Double totalAllOnlineTime = uscUserlearnstaticsYearSum.getTotalOnlineTime() + uscUserlearnstaticsYearSum.getTotalOnlineTime2();
            Double totalEffectiveOnlineTime = (totalAllOnlineTime - 50) > 0 ? 50.0 : (totalAllOnlineTime);
            Double totalEffectiveOfflineSelfLearningTime = (uscUserlearnstaticsYearSum.getTotalOfflineSelfLearningTime() - 10) > 0 ? 10.0 : uscUserlearnstaticsYearSum.getTotalOfflineSelfLearningTime();
            Double totalEffectiveOverseasStudyTime = (uscUserlearnstaticsYearSum.getTotalOverseasStudyTime() - 100) > 0 ? 100.0 : uscUserlearnstaticsYearSum.getTotalOverseasStudyTime();
            uscUserlearnstaticsYearSum.setTotalAllOnlineTime(totalAllOnlineTime); // 总网络学时
            uscUserlearnstaticsYearSum.setTotalEffectiveOnlineTime(totalEffectiveOnlineTime);// 总有效网络学时(网络学时每年最多计入50个学时)
            uscUserlearnstaticsYearSum.setTotalEffectiveOfflineSelfLearningTime(totalEffectiveOfflineSelfLearningTime); // 在职自学有效学时(在职自学每年最多计入10个学时)
            uscUserlearnstaticsYearSum.setTotalEffectiveOverseasStudyTime(totalEffectiveOverseasStudyTime);// 公派留学有效学时(公派留学每年最多计入100个学时)
            uscUserlearnstaticsYearSum.setTotalEffectiveTime(totalEffectiveOnlineTime + totalEffectiveOfflineSelfLearningTime
                            + uscUserlearnstaticsYearSum.getTotalOfflineTrainTime()
                            + totalEffectiveOverseasStudyTime);// 总有效学时
            uscUserlearnstaticsYearSum.setStartTime(startTime);
            uscUserlearnstaticsYearSum.setEndTime(endTime);

            if (uscUserlearnstaticsYearSum.getTotalEffectiveTime() < Constants.Training_Total_Hour){
                uscUserlearnstaticsYearSum.setRemainingTrainingHours(Constants.Training_Total_Hour - uscUserlearnstaticsYearSum.getTotalEffectiveTime());
            } else{
                uscUserlearnstaticsYearSum.setRemainingTrainingHours(0.0);
            }
            return uscUserlearnstaticsYearSum;
        }else{
            UscUserLearnStaticsYearSum uscUserLearnStaticsYearSum = new UscUserLearnStaticsYearSum();
            uscUserLearnStaticsYearSum.setOperatorId(operatorId);
            uscUserLearnStaticsYearSum.setYear(year);
            uscUserLearnStaticsYearSum.setMonth(null);
            uscUserLearnStaticsYearSum.setTotalTimeMutiMonth(0.0);
            uscUserLearnStaticsYearSum.setTotalEffectiveTime(0.0);
            uscUserLearnStaticsYearSum.setTotalAllOnlineTime(0.0);
            uscUserLearnStaticsYearSum.setTotalOnlineTime(0.0);
            uscUserLearnStaticsYearSum.setTotalOnlineTime2(0.0);
            uscUserLearnStaticsYearSum.setTotalEffectiveOnlineTime(0.0);
            uscUserLearnStaticsYearSum.setTotalOfflineTrainTime(0.0);
            uscUserLearnStaticsYearSum.setTotalOfflineSelfLearningTime(0.0);
            uscUserLearnStaticsYearSum.setTotalEffectiveOfflineSelfLearningTime(0.0);
            uscUserLearnStaticsYearSum.setTotalOverseasStudyTime(0.0);
            uscUserLearnStaticsYearSum.setTotalEffectiveOverseasStudyTime(0.0);
            uscUserLearnStaticsYearSum.setRemainingTrainingHours(Double.valueOf(Constants.Training_Total_Hour));

            uscUserLearnStaticsYearSum.setStartTime(startTime);
            uscUserLearnStaticsYearSum.setEndTime(endTime);
            return uscUserLearnStaticsYearSum;
        }

    }


}
