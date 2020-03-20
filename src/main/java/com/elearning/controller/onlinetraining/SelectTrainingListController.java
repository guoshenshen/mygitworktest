package com.elearning.controller.onlinetraining;

import com.elearning.common.Constants;
import com.elearning.common.ServiceResponse;
import com.elearning.interceptor.loginInterceptor.IsCheckUserLogin;
import com.elearning.pojo.pub.EosOperator;
import com.elearning.pojo.pub.EosorgTOrganization;
import com.elearning.service.onlinetraining.IOnlineTrainingService;
import com.elearning.service.pub.IEosorgTEmployeeService;
import com.elearning.service.pub.IEosorgTOrganizationService;
import com.elearning.vo.onlinetraining.BasicTrainForm;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.omg.CORBA.ORB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 〈<br>
 * 〈〉
 *
 * @author lxx
 * @create 2019/12/6 14:22
 */
@Controller
@RequestMapping("/selectTrainingList/")
public class SelectTrainingListController {

    @Autowired
    private IEosorgTEmployeeService eosorgTEmployeeService;

    @Autowired
    private IEosorgTOrganizationService eosorgTOrganizationService;

    @Autowired
    private IOnlineTrainingService onlineTrainingService;

    @RequestMapping("trainList.do")
    @ResponseBody
    public ServiceResponse trainList(HttpServletRequest request,
                                     @RequestParam(value = "startIndex",defaultValue = "1")Integer startIndex,
                                     @RequestParam(value = "count",defaultValue = "25")Integer count,
                                     String trainName){
        Map<String,Object> conditions=new HashMap<>();
        EosOperator operator = (EosOperator) request.getSession().getAttribute(Constants.USERINFO_KEY);
        // 公开范围
        String orgSEQ = "";
        if(operator!=null){
            orgSEQ = request.getParameter("orgSEQ");
            if(orgSEQ == null || orgSEQ.trim().length()==0){
                Integer orgId = eosorgTEmployeeService.findById(operator.getOperatorId()).getOrgID();
                EosorgTOrganization currentOrg = this.eosorgTOrganizationService.findById(orgId);
                orgSEQ=currentOrg.getOrgSEQ();
            }
        }
        else{
            orgSEQ = Constants.orgSEQ;
        }
        conditions.put("trainName",trainName);
        conditions.put("orgSEQ", orgSEQ);
        PageHelper.startPage(startIndex,count);
        List<BasicTrainForm> basicTrainForms = onlineTrainingService.findVisibleTrainPageByCondition(conditions);
        PageInfo pageInfo = new PageInfo(basicTrainForms);
        pageInfo.setList(basicTrainForms);
        return ServiceResponse.createBySuccess(pageInfo);
    }



}
