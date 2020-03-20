package com.elearning.service.interaction;

import com.elearning.common.CacheUtils;
import com.elearning.common.Constants;
import com.elearning.common.ServiceResponse;
import com.elearning.pojo.interaction.Following;
import com.elearning.pojo.pub.EosOperator;
import com.elearning.pojo.pub.UserForm;
import com.elearning.service.courseStudy.IEmployeeCourseService;
import com.elearning.service.onlinetraining.IOnlineTrainingService;
import com.elearning.service.pub.IEosorgTEmployeeService;
import com.elearning.service.pub.IEosorgTOrganizationService;
import com.elearning.service.systemManage.ITenantService;
import com.elearning.vo.EosOrgTOrganizationVo;
import com.elearning.vo.logon.CourseStudyForm;
import com.elearning.vo.onlinetraining.TrainForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("interactionService")
public class InteractionService implements IInteractionService{

    @Autowired
    private IEosorgTOrganizationService eosorgTOrganizationService;

    @Autowired
    private IEosorgTEmployeeService eosorgTEmployeeService;

    @Autowired
    private IFollowingService followingService;

    @Autowired
    private ITenantService tenantService;

    @Autowired
    private IEmployeeCourseService employeeCourseService;

    @Autowired
    private IOnlineTrainingService onlineTrainingService;

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public void intoUserZone(HttpServletRequest request){

        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);

        Integer operatorId = Integer.parseInt(request.getParameter("operatorId"));
        Integer followingType = 1;
        if (request.getParameter("itemType") != null) {
            followingType = Integer.parseInt(request.getParameter("itemType"));
        }
        request.setAttribute("itemType", followingType);
        Integer tenantId = null;
        if (eosoperator != null) {
            tenantId = eosoperator.getTenantId();
        }
        UserForm userForm = null;
        if (followingType.equals(0)) {
            List<Integer> orgIdList = new ArrayList<>();
            orgIdList.add(operatorId);
            List<EosOrgTOrganizationVo> orgFormList = this.eosorgTOrganizationService.findOrgInfoById(orgIdList);
            EosOrgTOrganizationVo focusOrg = orgFormList.get(0);
            userForm = new UserForm();
            userForm.setOperatorId(focusOrg.getOrgId());
            userForm.setOrgName(focusOrg.getParentOrgName());
            userForm.setOperatorName(focusOrg.getOrgName());
            userForm.setGender(1);
            userForm.setAddress(focusOrg.getLogo());
            userForm.setLogo(focusOrg.getLogo());
        } else {
            userForm = this.eosorgTEmployeeService.findByUserId(operatorId,tenantId);
        }
        Integer countOfFollows = this.followingService.findFollowsCount(operatorId);
        Integer countOfFans = this.followingService.findFansCount(operatorId);
        Integer countOfFollowOrgs = this.followingService.findFollowOrgsCount(operatorId);
        String currentOperatorId="";
        request.setAttribute("userinfo", userForm);
        request.setAttribute("countOfFollows", countOfFollows);
        request.setAttribute("countOfFans", countOfFans);
        request.setAttribute("countOfFollowOrgs", countOfFollowOrgs);

        if (eosoperator != null) {
            currentOperatorId=eosoperator.getOperatorId().toString();
        }
        request.setAttribute("currentOperatorId",currentOperatorId);

    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public ServiceResponse getBasicFollowingInfo(HttpServletRequest request){

        Integer operatorId = Integer.parseInt(request.getParameter("operatorId"));
        //获取当前类型是学员还是机构
        Integer followingType = null;
        if (request.getParameter("itemType") != null) {
            followingType = Integer.parseInt(request.getParameter("itemType"));
        } else {
            followingType = this.followingService.getFollowingType(operatorId);
        }

        EosOperator currentOperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        Integer tenantId = null;
        if (currentOperator != null) {
            tenantId = currentOperator.getTenantId();
        }

        UserForm userForm = null;
        if (followingType == 1) {
            userForm = this.eosorgTEmployeeService.findByUserId(operatorId,tenantId);
        } else {
            List<Integer> orgIdList = new ArrayList<>();
            orgIdList.add(operatorId);
            List<EosOrgTOrganizationVo> orgFormList = this.eosorgTOrganizationService.findOrgInfoById(orgIdList);
            EosOrgTOrganizationVo focusOrg = orgFormList.get(0);
            userForm = new UserForm();
            userForm.setOperatorId(focusOrg.getOrgId());
            userForm.setLogo(CacheUtils.getTenantLogo(this.eosorgTOrganizationService.findTenantId(focusOrg.getOrgId())));
            userForm.setOrgName(focusOrg.getParentOrgName());
            userForm.setOperatorName(focusOrg.getOrgName());
            userForm.setGender(1);
        }
        //判断用户是否已经登录以及是否对目标信息是否处于关注状态

        Boolean hasFocused = false;
        if (currentOperator != null) {
            Map<String,Object> parmMap = new HashMap<>();
            parmMap.put("follows",operatorId);
            parmMap.put("isFollowedBy",currentOperator.getOperatorId());

            List<Following> followingList = this.followingService.getListByFollowsAndIsFolloweBy(parmMap);
            if (followingList != null && followingList.size()>0) {
                hasFocused = true;
            }
        }

        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("followingType",followingType);

        if (currentOperator != null) {
            resultMap.put("currentOperatorId",currentOperator.getOperatorId());
        } else {
            resultMap.put("currentOperatorId","");
        }

        resultMap.put("userForm",userForm);

        //获取该user的关注数及粉丝数
        Integer countOfFollows = this.followingService.findFollowsCount(operatorId);
        Integer countOfFans = this.followingService.findFansCount(operatorId);
        Integer countOfFollowOrgs = this.followingService.findFollowOrgsCount(operatorId);
        Integer followerNum = countOfFollows + countOfFollowOrgs;
        Integer fansNum = countOfFans;

        resultMap.put("followerNum",followerNum);
        resultMap.put("fansNum",fansNum);
        resultMap.put("hasFocused",hasFocused);

        return ServiceResponse.createBySuccess(resultMap);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public ServiceResponse listCoursesOfSpecifiedUser(HttpServletRequest request){

        EosOperator currentOperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);

        Map<String, Object> map = new HashMap<>();
        Integer tenantId = currentOperator.getTenantId();
        Integer currentOperatorId = currentOperator.getOperatorId();
        map.put("tenantId", tenantId);
        map.put("currentOperatorId", currentOperatorId);

        int tenantOrgId = this.tenantService.findById(tenantId).getOrgId();
        map.put("tenantOrgId", tenantOrgId);

        Integer operatorId = null;
        Integer recordNums = 100;

        if (request.getParameter("operatorId") != null) {
            operatorId = Integer.parseInt(request.getParameter("operatorId"));
        }
        if (request.getParameter("recordNums") != null) {
            recordNums = Integer.parseInt(request.getParameter("recordNums"));
        }
        map.put("operatorId", operatorId);
        map.put("recordNums", recordNums);
        List<CourseStudyForm> courseFormList = this.employeeCourseService.listCoursesByOperatorId(map);

        return ServiceResponse.createBySuccess(courseFormList);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public ServiceResponse listTrainsOfSpecifiedUser(HttpServletRequest request){

        Map<String, Object> map = new HashMap<>();
        EosOperator currentOperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        Integer tenantId = currentOperator.getTenantId();
        Integer currentOperatorId = currentOperator.getOperatorId();
        map.put("tenantId", tenantId);
        map.put("currentOperatorId", currentOperatorId);
        Integer operatorId = null;

        if (request.getParameter("operatorId") != null) {
            operatorId = Integer.parseInt(request.getParameter("operatorId"));
        }
        map.put("operatorId", operatorId);

        List<TrainForm> trainFormList = this.onlineTrainingService.listTrainByOperatorId(map);

        return ServiceResponse.createBySuccess(trainFormList);
    }

}
