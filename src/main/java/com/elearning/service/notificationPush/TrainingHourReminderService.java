package com.elearning.service.notificationPush;

import com.elearning.common.Constants;
import com.elearning.common.ServiceResponse;
import com.elearning.common.Tools;
import com.elearning.pojo.pub.EosOperator;
import com.elearning.pojo.pub.EosorgTEmployee;
import com.elearning.service.pub.IEosorgTEmployeeService;
import com.elearning.service.statistics.IUscUserLearnStaticsService;
import com.elearning.vo.statistics.UscUserLearnStaticsYearSumForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("trainingHourReminderService")
public class TrainingHourReminderService implements ITrainingHourReminderService{

    @Autowired
    private IEosorgTEmployeeService eosorgTEmployeeService;

    @Autowired
    private IUscUserLearnStaticsService uscUserLearnStaticsService;

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public ServiceResponse getTrainingHour(HttpServletRequest request){

        Map<String,Object> parameters = new HashMap<>();
        EosOperator operator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        EosorgTEmployee employee = this.eosorgTEmployeeService.findById(operator.getOperatorId());
        int operatorId = operator.getOperatorId();

        String startTime = "2000-01";
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        if(request.getSession().getAttribute(Constants.YEAR_KEY)!=null){
            startTime = request.getSession().getAttribute(Constants.YEAR_KEY).toString()+"-01-01";
            year = Integer.parseInt(request.getSession().getAttribute(Constants.YEAR_KEY).toString());
        }
        String endTime = "2050-01";
        if(request.getSession().getAttribute(Constants.YEAR_KEY)!=null)
            endTime = request.getSession().getAttribute(Constants.YEAR_KEY).toString()+"-12-31";

        //System.out.println("startTime:"+startTime+";endTime:"+endTime);
        parameters.put("startTime", startTime);
        parameters.put("endTime", endTime);

        //分年度统计有效学时
        List<UscUserLearnStaticsYearSumForm> uscUserlearnstaticsYearSumFormList = this.uscUserLearnStaticsService.queryUserlearnstaticsInProfile(parameters, operatorId);

        String totalTime = "0小时";
        Double remainingTrainingHours = Constants.Training_Total_Hour + 0.0;
        if(uscUserlearnstaticsYearSumFormList!=null && uscUserlearnstaticsYearSumFormList.size()>0){
            UscUserLearnStaticsYearSumForm uscUserlearnstaticsYearSumForm = uscUserlearnstaticsYearSumFormList.get(0);
            totalTime = uscUserlearnstaticsYearSumForm.getTotalEffectiveTime();
            remainingTrainingHours = uscUserlearnstaticsYearSumForm.getRemainingTrainingHours();
        }

        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("year",year);
        resultMap.put("totalTime",totalTime);
        resultMap.put("remainingTrainingTime",Tools.formatCeilHours(remainingTrainingHours));
        resultMap.put("remainingTrainingHours",Tools.getTwoDigitalData(remainingTrainingHours));
        if(employee.getIsCtnEduRequire()==null || employee.getIsCtnEduRequire().equals(0)){
            resultMap.put("noNeed",true);
        }

        return ServiceResponse.createBySuccess(resultMap);
    }



}
