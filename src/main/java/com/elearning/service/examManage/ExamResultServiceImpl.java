package com.elearning.service.examManage;

import com.elearning.common.Constants;
import com.elearning.common.MyUtils;
import com.elearning.common.ServiceResponse;
import com.elearning.dao.examManage.ExamResultMapper;
import com.elearning.pojo.examManage.ExamExamInfo;
import com.elearning.pojo.examManage.ExamExamUserScore;
import com.elearning.pojo.examManage.ExamResult;
import com.elearning.pojo.pub.EosOperator;
import com.elearning.pojo.pub.EosorgTEmployee;
import com.elearning.pojo.pub.EosorgTOrganization;
import com.elearning.service.pub.IEosOperatorService;
import com.elearning.service.pub.IEosorgTEmployeeService;
import com.elearning.service.pub.IEosorgTOrganizationService;
import com.elearning.util.DateTimeUtil;
import com.elearning.vo.examManage.ExamOnlinePaperReadingForm;
import com.elearning.vo.examManage.SimpleExanForm;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service("examResultService")
public class ExamResultServiceImpl implements IExamResultService {

    @Autowired
    private ExamResultMapper examResultMapper;

    @Autowired
    private IEosOperatorService eosOperatorService;

    @Autowired
    private IEosorgTEmployeeService eosorgTEmployeeService;

    @Autowired
    private IEosorgTOrganizationService eosorgTOrganizationService;

    @Autowired
    private IExamExamInfoService examExamInfoService;

    @Autowired
    private IExamExamUserScoreService examExamUserScoreService;

    @Override
    public ExamResult selectByPrimaryKey(Integer id){
        return this.examResultMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public List<ExamOnlinePaperReadingForm> findExamOnlinePaperReadingByExamId(Integer eid){
        Map<String,Object> map = new HashMap();
        int operatorId = 0;
        map.put("examId", eid);

        Iterator<SimpleExanForm> it = this.examExamInfoService.searchOnlineReadingExam(map).iterator();
        ArrayList<ExamOnlinePaperReadingForm> result = new ArrayList<>();
        while(it.hasNext()){
            SimpleExanForm object = it.next();
            ExamOnlinePaperReadingForm temp = new ExamOnlinePaperReadingForm();
            if(object.getEmployeeId() != null){
                operatorId = object.getEmployeeId();
            }

            EosOperator operator = this.eosOperatorService.findById(operatorId);
            EosorgTEmployee eosorgTEmployee = this.eosorgTEmployeeService.findById(operatorId);
            EosorgTOrganization eosorgTOrganization = this.eosorgTOrganizationService.findById(eosorgTEmployee.getOrgID());

            temp.setEmpCode(eosorgTEmployee.getEmpCode());
            temp.setEmployeeID(operatorId);
            temp.setExamId(eid);
            temp.setEmployeeName(operator.getOperatorName());
            temp.setOrgName(eosorgTOrganization.getOrgName());
            if(object.getSubmitDate()!= null) {
                temp.setSubmitDate(DateTimeUtil.dateToStr(object.getSubmitDate(),"yyyy-MM-dd HH:mm:ss"));

                temp.setExamPartStatus(Constants.S_EXAMPARTSTATUS_YES);
            }else{
                temp.setSubmitDate(DateTimeUtil.dateToStr(object.getSubmitDate(),"yyyy-MM-dd HH:mm:ss"));

                temp.setExamPartStatus(Constants.S_EXAMPARTSTATUS_NO);
            }

            ExamExamInfo examInfo = this.examExamInfoService.selectByPrimaryKey(eid);
            Integer examType = examInfo.getExamType();

            if(Constants.I_EXAMTYPE_OFFLINE.intValue() == examType.intValue()){
                temp.setReadingStatus(Constants.S_PAPERAUDIT_NONEED);
            }else if(Constants.I_EXAMTYPE_ONLINE.intValue() == examType.intValue()){
                if(!MyUtils.isNull(object.getTotalScore())){
                    temp.setReadingStatus(Constants.S_PAPERAUDIT_YES + "总分: " + object.getTotalScore().toString());
                }else {
                    temp.setReadingStatus(Constants.S_PAPERAUDIT_NO);
                }
            }
            temp.setReplyId(object.getReplyId()==null?0:object.getReplyId());

            result.add(temp);
        }
        return result;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public ServiceResponse searchExamScoreList(Map<String,Object> queryConditionMap){

        Integer employeeId = null;
        List<EosorgTEmployee> et = null;
        List<EosOperator> eo = null;
        if(queryConditionMap.get("empCode") != null && !"".equalsIgnoreCase(queryConditionMap.get("empCode").toString())){
            et = this.eosorgTEmployeeService.findByEmpCode(queryConditionMap.get("empCode").toString());
        }
        if(null != queryConditionMap.get("empName") && !"".equalsIgnoreCase(queryConditionMap.get("empName").toString())){
            eo = this.eosOperatorService.findByOperatorName(queryConditionMap.get("empName").toString());
        }
        if(null != et && et.size()>0 && eo == null) {
            EosorgTEmployee e =et.get(0);
            employeeId = e.getOperatorID();
        }
        if(null!=eo && eo.size()>0 && et == null) {
            EosOperator e = eo.get(0);
            employeeId = e.getOperatorId();
        }
        if(eo != null && et != null && et.size()>0 && eo.size()>0) {
            EosorgTEmployee e1 = et.get(0);
            EosOperator e2 = eo.get(0);
            if(e1.getOperatorID() == e2.getOperatorId()) {
                employeeId = e1.getOperatorID();
            }
        }

        PageHelper.startPage((Integer) queryConditionMap.get("startIndex"),(Integer) queryConditionMap.get("count"));

        Map<String,Object> parmMap = new HashMap<>();
        parmMap.put("examId",queryConditionMap.get("examId"));
        parmMap.put("employeeId",employeeId);

        // 该方法使用分页查询不正确  ---   未写完
        List<ExamExamUserScore> examExamUserScoreList ;
        if(et == null && eo==null){
            //examExamUserScoreList= this.examExamUserScoreService.listExamExamUserScore(examId);
        }else{
            //examExamUserScoreList= this.examExamUserScoreService.listExamExamUserScoreSearch(examId,employeeId);
        }

        //根据查询条件进行查询

        PageInfo pageInfo = new PageInfo();
        //pageInfo.setList();

        //对查询出的结果进行封装或者修改


        return ServiceResponse.createBySuccess(pageInfo);
    }





}
