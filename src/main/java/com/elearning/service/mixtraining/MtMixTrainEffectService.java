package com.elearning.service.mixtraining;

import com.elearning.common.Constants;
import com.elearning.pojo.pub.EosOperator;
import com.elearning.pojo.pub.EosorgTEmployee;
import com.elearning.pojo.pub.EosorgTOrganization;
import com.elearning.pojo.systemManage.Tenant;
import com.elearning.service.courseStudy.ITrainSummaryService;
import com.elearning.service.pub.IEosOperatorService;
import com.elearning.service.pub.IEosorgTEmployeeService;
import com.elearning.service.pub.IEosorgTOrganizationService;
import com.elearning.service.systemManage.ITenantService;
import com.elearning.vo.CourseStudy.TrainSummaryForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;


@Service("mtMixTrainEffectService")
public class MtMixTrainEffectService implements IMtMixTrainEffectService{

    @Autowired
    private IEosorgTEmployeeService eosorgTEmployeeService;

    @Autowired
    private IEosOperatorService eosOperatorService;

    @Autowired
    private IEosorgTOrganizationService eosorgTOrganizationService;

    @Autowired
    private ITenantService tenantService;

    @Autowired
    private ITrainSummaryService trainSummaryService;

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public int add(TrainSummaryForm trainSummaryForm ,HttpServletRequest request){

        String parentOrgName = "";
        String _orgName = "";
        String userName = "";
        Tenant tenant = this.tenantService.findById(Constants.tenantId);
        EosOperator operator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        int operatorId = operator.getOperatorId();

        EosorgTEmployee employee = this.eosorgTEmployeeService.findById(operatorId);
        EosOperator eosOperator = this.eosOperatorService.findById(operatorId);

        if(employee != null){
            if(eosOperator != null){
                userName = eosOperator.getOperatorName();
            }
            EosorgTOrganization organization = null;
            EosorgTOrganization parentOrg = null;
            if(tenant!=null && tenant.getIsVirOrg()!=null && tenant.getIsVirOrg().intValue()==1){
                Tenant _tenant =this.eosorgTOrganizationService.findTenant(employee.getOrgID());
                parentOrgName = _tenant.getTenantName();

                organization = this.eosorgTOrganizationService.findById(this.eosorgTOrganizationService.findOrgId(employee.getOrgID()));
                _orgName = organization.getOrgName();
            }else{
                parentOrg = this.eosorgTOrganizationService.findById(this.eosorgTOrganizationService.findOrgId(employee.getOrgID()));
                if(parentOrg!=null && parentOrg.getOrgName()!=null){
                    parentOrgName = parentOrg.getOrgName();
                }
                organization = this.eosorgTOrganizationService.findById(employee.getOrgID());
                _orgName = organization.getOrgName();
            }
        }
        trainSummaryForm.setOrgName(_orgName);
        trainSummaryForm.setParentOrgName(parentOrgName);
        trainSummaryForm.setUserName(userName);
        int train_id = Integer.valueOf(request.getSession().getAttribute("trainId").toString());
        trainSummaryForm.setTrainId(train_id);
        trainSummaryForm.setSubmitDate(new Date());
        trainSummaryForm.setOperatorId(operatorId);
        trainSummaryForm.setIsTrainEffect(1);
        int summaryId = this.trainSummaryService.saveTrainSummaryForm(trainSummaryForm);
        trainSummaryForm.setId(summaryId);

        //=======================此方法未完成====================
        this.trainSummaryService.saveAttachFile(trainSummaryForm);

        return summaryId;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public int update(TrainSummaryForm trainSummaryForm ,HttpServletRequest request){
        trainSummaryForm.setIsTrainEffect(1);
        int n = this.trainSummaryService.updateTrainSummaryForm(trainSummaryForm);
        return n;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public int delete(TrainSummaryForm trainSummaryForm ,HttpServletRequest request){
        int n = 0;
        if(request.getParameterValues("id") != null) {
            String[] delete_ids = request.getParameterValues("id");
            n = this.trainSummaryService.deleteSummaryForms(delete_ids);
        }

        return n;
    }





}
