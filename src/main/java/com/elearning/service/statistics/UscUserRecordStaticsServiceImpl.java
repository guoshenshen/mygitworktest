package com.elearning.service.statistics;

import com.elearning.common.Tools;
import com.elearning.dao.statistics.UscUserRecordStaticsMapper;
import com.elearning.pojo.pub.EosOperator;
import com.elearning.pojo.pub.EosorgTEmployee;
import com.elearning.pojo.pub.EosorgTOrganization;
import com.elearning.pojo.statistics.UscUserLearnStatics;
import com.elearning.pojo.statistics.UscUserLearnStaticsKey;
import com.elearning.pojo.statistics.UscUserRecordStatics;
import com.elearning.pojo.systemManage.Tenant;
import com.elearning.service.pub.IEosOperatorService;
import com.elearning.service.pub.IEosorgTEmployeeService;
import com.elearning.service.pub.IEosorgTOrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;


@Service("uscUserRecordStaticsService")
public class UscUserRecordStaticsServiceImpl implements IUscUserRecordStaticsService {

    @Autowired
    private UscUserRecordStaticsMapper uscUserRecordStaticsMapper;

    @Autowired
    private IUscUserLearnStaticsService uscUserLearnStaticsService;

    @Autowired
    private IEosOperatorService eosOperatorService;

    @Autowired
    private IEosorgTEmployeeService eosorgTEmployeeService;

    @Autowired
    private IEosorgTOrganizationService eosorgTOrganizationService;

    @Override
    public UscUserRecordStatics selectByPrimaryKey(Long recordId){

        return this.uscUserRecordStaticsMapper.selectByPrimaryKey(recordId);
    }

    @Override
    public List<UscUserRecordStatics> findByExample(Map<String,Object> parmMap){

        return this.uscUserRecordStaticsMapper.findByExample(parmMap);
    }

    @Override
    public int insertSelective(UscUserRecordStatics record){

        return this.uscUserRecordStaticsMapper.insertSelective(record);
    }

    @Override
    public int updateByPrimaryKeySelective(UscUserRecordStatics record){

        return this.uscUserRecordStaticsMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int deleteByPrimaryKey(Long recordId){

        return this.uscUserRecordStaticsMapper.deleteByPrimaryKey(recordId);
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
            if(this.uscUserLearnStaticsService.selectByPrimaryKey(uscUserlearnstaticsId)!=null){
                //某人当月学时记录已经创建
                UscUserLearnStatics uscUserlearnstatics = this.uscUserLearnStaticsService.selectByPrimaryKey(uscUserlearnstaticsId);
                //累计总学时
                if(uscUserlearnstatics.getTotalTime()!=null){
                    uscUserlearnstatics.setTotalTime(uscUserlearnstatics.getTotalTime()+trainTime);
                } else{
                    uscUserlearnstatics.setTotalTime(trainTime);
                }
                if(outTrainType!=null){
                    if(outTrainType==5202){//在职自学
                        if(uscUserlearnstatics.getOfflineSelfLearningTime()!=null){
                            uscUserlearnstatics.setOfflineSelfLearningTime(uscUserlearnstatics.getOfflineSelfLearningTime()+trainTime);
                        } else{
                            uscUserlearnstatics.setOfflineSelfLearningTime(trainTime);
                        }
                    } else if(outTrainType==5203){//外部网络学时
                        if(uscUserlearnstatics.getOnlineTime2()!=null){
                            uscUserlearnstatics.setOnlineTime2(uscUserlearnstatics.getOnlineTime2()+trainTime);
                        } else{
                            uscUserlearnstatics.setOnlineTime2(trainTime);
                        }
                    }else if(outTrainType==5204){//公派留学
                        if(uscUserlearnstatics.getOverseasStudyTime()!=null){
                            uscUserlearnstatics.setOverseasStudyTime(uscUserlearnstatics.getOverseasStudyTime()+trainTime);
                        } else{
                            uscUserlearnstatics.setOverseasStudyTime(trainTime);
                        }
                    } else if(outTrainType==5201){//外部培训
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
                this.uscUserLearnStaticsService.updateByPrimaryKeySelective(uscUserlearnstatics);
            }else{
                //某人当月学时记录尚未创建
                UscUserLearnStatics uscUserlearnstatics = new UscUserLearnStatics();
                //operatorId
                uscUserlearnstatics.setOperatorId(operatorId);
                // year
                uscUserlearnstatics.setYear(year);
                // month
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
                    }
                    else{
                        uscUserlearnstatics.setOfflineTrainTime(trainTime);
                    }
                    Tenant tenant = this.eosorgTOrganizationService.findTenant(org);
                    if(tenant!=null){
                        uscUserlearnstatics.setTenantId(tenant.getTenantId());
                        this.uscUserLearnStaticsService.insertSelective(uscUserlearnstatics);
                    }
                }
            }
        }

    }



}
