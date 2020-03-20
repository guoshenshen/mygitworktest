package com.elearning.service.mixtraining;

import com.elearning.common.Constants;
import com.elearning.common.ServiceResponse;
import com.elearning.dao.mixtraining.MtMixTrainCertificateMapper;
import com.elearning.pojo.mixtraining.MtMixTrainCertificate;
import com.elearning.pojo.onlinetraining.TrainSignRecord;
import com.elearning.pojo.onlinetraining.TrainSignWithBLOBs;
import com.elearning.pojo.pub.EosOperator;
import com.elearning.pojo.pub.EosorgTEmployee;
import com.elearning.pojo.pub.EosorgTOrganization;
import com.elearning.service.onlinetraining.ITrainSignRecordService;
import com.elearning.service.onlinetraining.ITrainSignService;
import com.elearning.service.pub.IEosOperatorService;
import com.elearning.service.pub.IEosorgTEmployeeService;
import com.elearning.service.pub.IEosorgTOrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


@Service("mtMixTrainCertificateService")
public class MtMixTrainCertificateService implements IMtMixTrainCertificateService{

    @Autowired
    private MtMixTrainCertificateMapper mtMixTrainCertificateMapper;

    @Autowired
    private ITrainSignService trainSignService;

    @Autowired
    private IMtMixTrainUserTrainInfoService mtMixTrainUserTrainInfoService;

    @Autowired
    private IEosOperatorService eosOperatorService;

    @Autowired
    private ITrainSignRecordService trainSignRecordService;

    @Autowired
    private IEosorgTEmployeeService eosorgTEmployeeService;

    @Autowired
    private IEosorgTOrganizationService eosorgTOrganizationService;

    @Override
    public MtMixTrainCertificate selectByPrimaryKey(Integer id){

        return this.mtMixTrainCertificateMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public ServiceResponse findTrainSign(HttpServletRequest request){

        Integer trainId = Integer.parseInt(request.getParameter("trainId"));

        List<TrainSignWithBLOBs> list = this.trainSignService.getListByTrainId(trainId);
        for (TrainSignWithBLOBs sign : list) {

            DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            if(sign.getStart() != null ){
                sign.setStartS(sdf.format(sign.getStart()));
            }
            if(sign.getEnd() != null){
                sign.setEndS(sdf.format(sign.getEnd()));
            }
            if(sign.getStartClass()!=null){
                sign.setStartClassS(sdf.format(sign.getStartClass()));
            }
            if(sign.getEndClass()!=null){
                sign.setEndClassS(sdf.format(sign.getEndClass()));
            }
            sign.setSignCount(this.mtMixTrainUserTrainInfoService.findCountByTrainId(sign.getId()));
        }

        return ServiceResponse.createBySuccess(list);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public ServiceResponse findTrainSignEwm(HttpServletRequest request){

        Integer id = Integer.parseInt(request.getParameter("id"));
        TrainSignWithBLOBs ts = this.trainSignService.selectByPrimaryKey(id);

        return ServiceResponse.createBySuccess(ts.getEwm());
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public ServiceResponse getOrgList(HttpServletRequest request){

        Integer id = Integer.parseInt(request.getParameter("id"));
        List<TrainSignRecord> list = this.trainSignRecordService.getListBySignId(id);
        for (TrainSignRecord tsr : list) {
            EosOperator eos = this.eosOperatorService.findById(tsr.getUser_id());
            if(tsr.getTime() != null){
                DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                tsr.setTimeS(sdf.format(tsr.getTime()));
            }
            tsr.setUserName(eos.getUserId());
            tsr.setOperatorName(eos.getOperatorName());
            EosorgTEmployee ete = this.eosorgTEmployeeService.findById(tsr.getUser_id());
            tsr.setEmail(ete.getOEmail());
            tsr.setOrgName(this.eosorgTOrganizationService.findById(ete.getOrgID()).getOrgName());
        }

        return ServiceResponse.createBySuccess(list);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public ServiceResponse getCertificateTemplate(HttpServletRequest request){

        Integer orgId = Integer.parseInt(request.getParameter("orgId"));
        Integer tenantId = Constants.tenantId;

        //获取上级部门以及展示基地
        List<EosorgTOrganization> upreportOrgList = new ArrayList<>();
        EosorgTOrganization parentOrg = this.eosorgTOrganizationService.findById(this.eosorgTOrganizationService.findParentOrgId(orgId));
        if(parentOrg!=null){
            upreportOrgList.add(parentOrg);
        }
        EosorgTOrganization temp = new EosorgTOrganization();
        temp.setAttachedOrgId(orgId);
       /* List<EosorgTOrganization> vOrgList = this.eosorgTOrganizationService.findByExample(temp);
        for(EosorgTOrganization vorg : vOrgList){
            EosorgTOrganization vparentOrg = this.eosorgTOrganizationService.findById(this.eosorgTOrganizationService.findParentOrgId(vorg.getOrgID()));
            if(vparentOrg!=null){
                upreportOrgList.add(vparentOrg);
            }
        }
        Tenant tenant = CacheUtils.getTenant(tenantId);
        if(tenant!=null && !tenant.getOrgId().equals(tenantId)){
            temp.setAttachedOrgId(tenant.getOrgId());
            vOrgList = this.eosorgTOrganizationService.findByExample(temp);
            for(EosorgTOrganization vorg : vOrgList){
                EosorgTOrganization vparentOrg = this.eosorgTOrganizationService.findById(this.eosorgTOrganizationService.findParentOrgId(vorg.getOrgID()));
                if(vparentOrg!=null)upreportOrgList.add(vparentOrg);
            }
        }

        List<CertificateTemplate> templateList=new ArrayList<CertificateTemplate>();
        CertificateTemplate instance=new CertificateTemplate();
        instance.setOrgId(orgId);
        instance.setStatus(1);
        List<CertificateTemplate> templates=this.getCertificateTemplateDAO().findByExample(instance);
        templateList.addAll(templates);

        for(EosorgTOrganization org:upreportOrgList){
            instance.setOrgId(org.getOrgId());
            instance.setStatus(1);
            templates=this.getCertificateTemplateDAO().findByExample(instance);
            for(CertificateTemplate template:templates){
                if(template.getStatus()!=null&&template.getOpenScope()>2201){
                    templateList.add(template);
                }
            }
        }
        Set<String> alreadyUrls = new HashSet<String>();
        StringBuffer message = new StringBuffer("[");
        int start=0;
        for(CertificateTemplate instance_1 : templateList){
            if(alreadyUrls.contains(instance_1.getCertificateTemplatePath())){
                //图片重复
                continue;
            }
            if(start==0){

            } else{
                message.append(",");
            }
            start++;
            message.append("{");
            message.append("\"id\":");
            message.append("\"");
            message.append(instance_1.getId());
            message.append("\"");
            message.append(",");
            message.append("\"url\":");
            message.append("\"");
            message.append(instance_1.getCertificateTemplatePath());
            message.append("\"");
            message.append(",");
            message.append("\"name\":");
            message.append("\"");
            message.append(instance_1.getCertificateTemplateName());
            message.append("\"");
            message.append(",");
            message.append("\"certificateTemplateType\":");
            message.append("\"");
            message.append(instance_1.getCertificateTemplateType());
            message.append("\"");
            message.append("}");
        }*/

        return ServiceResponse.createBySuccess();
    }






}
