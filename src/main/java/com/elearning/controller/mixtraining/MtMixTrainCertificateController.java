package com.elearning.controller.mixtraining;

import com.elearning.common.Constants;
import com.elearning.common.ResponseCode;
import com.elearning.common.ServiceResponse;
import com.elearning.interceptor.loginInterceptor.IsCheckUserLogin;
import com.elearning.pojo.mixtraining.MtMixTrainCertificateTemplate;
import com.elearning.pojo.pub.EosOperator;
import com.elearning.pojo.pub.TrainWithBLOBs;
import com.elearning.service.mixtraining.IMtMixTrainCertificateService;
import com.elearning.service.mixtraining.IMtMixTrainCertificateTemplateService;
import com.elearning.service.onlinetraining.IOnlineTrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/mtMixTrainCertificate/")
public class MtMixTrainCertificateController {

    @Autowired
    private IMtMixTrainCertificateService mtMixTrainCertificateService;

    @Autowired
    private IOnlineTrainingService onlineTrainingService;

    @Autowired
    private IMtMixTrainCertificateTemplateService mtMixTrainCertificateTemplateService;


    /**
     * 点击培训日程图标后---跳转到培训日程首页
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("trainSign.do")
    public String trainSign(HttpServletRequest request){

        String jspName = "index";

        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return jspName;
        }

        int trainId = Integer.parseInt(request.getSession().getAttribute("trainId").toString());
        request.setAttribute("operator", eosoperator);
        Integer orgId = Integer.parseInt(request.getSession().getAttribute(Constants.ROOTORGID_KEY).toString());
        request.setAttribute("orgId", orgId);

        TrainWithBLOBs train = this.onlineTrainingService.findById(trainId);
        request.setAttribute("trainId", trainId);
        request.setAttribute("train", train);

        jspName = "mixtraining/trainSign";

        return jspName;
    }

    /**
     * 培训管理中的培训签到
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("findTrainSign.do")
    @ResponseBody
    public ServiceResponse findTrainSign(HttpServletRequest request){

        EosOperator operator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(operator == null) {
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }

        return this.mtMixTrainCertificateService.findTrainSign(request);
    }

    /**
     * 刷新培训签到二维码
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("findTrainSignEwm.do")
    @ResponseBody
    public ServiceResponse findTrainSignEwm(HttpServletRequest request){

        EosOperator operator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(operator == null) {
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }

        return this.mtMixTrainCertificateService.findTrainSignEwm(request);
    }

    /**
     * 刷新培训签到二维码
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("getOrgList.do")
    @ResponseBody
    public ServiceResponse getOrgList(HttpServletRequest request){

        EosOperator operator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(operator == null) {
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }

        return this.mtMixTrainCertificateService.getOrgList(request);
    }

    /**
     * 查询--电子证书主页面
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("manageTrainCertificate.do")
    public String manageTrainCertificate(HttpServletRequest request){

        String jspName = "index";
        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return jspName;
        }
        int trainId = Integer.parseInt(request.getSession().getAttribute("trainId").toString());
        request.setAttribute("operator", eosoperator);

        Integer orgId = Integer.parseInt(request.getSession().getAttribute(Constants.ROOTORGID_KEY).toString());
        request.setAttribute("orgId", orgId);
        TrainWithBLOBs train = this.onlineTrainingService.findById(trainId);

        List<MtMixTrainCertificateTemplate> mtCertificateTemplateList = this.mtMixTrainCertificateTemplateService.findByOperatorId(eosoperator.getOperatorId());
        List<MtMixTrainCertificateTemplate> _mtCertificateTemplateList = new ArrayList();
        for(MtMixTrainCertificateTemplate mtCertificateTemplate : mtCertificateTemplateList){
            if(mtCertificateTemplate.getStatus().intValue()==1){
                _mtCertificateTemplateList.add(mtCertificateTemplate);
            }
        }

        request.setAttribute("certificateTemplateList", _mtCertificateTemplateList);
        request.setAttribute("train", train);

        jspName = "mixtraining/mixTrainCertificate";

        return jspName;
    }

    /**
     * 选择证书样式
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("getCertificateTemplate.do")
    @ResponseBody
    public ServiceResponse getCertificateTemplate(HttpServletRequest request){

        EosOperator operator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(operator == null) {
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }

        return this.mtMixTrainCertificateService.getCertificateTemplate(request);
    }





}
