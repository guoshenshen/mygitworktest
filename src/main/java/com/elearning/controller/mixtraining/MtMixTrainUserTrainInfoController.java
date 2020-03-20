package com.elearning.controller.mixtraining;

import com.elearning.common.Constants;
import com.elearning.common.ResponseCode;
import com.elearning.common.ServiceResponse;
import com.elearning.interceptor.loginInterceptor.IsCheckUserLogin;
import com.elearning.pojo.mixtraining.MtMixTrainUserTrainInfo;
import com.elearning.pojo.pub.DDictionary;
import com.elearning.pojo.pub.EosOperator;
import com.elearning.pojo.pub.TrainWithBLOBs;
import com.elearning.service.mixtraining.IMtMixTrainUserTrainInfoService;
import com.elearning.service.onlinetraining.IOnlineTrainingService;
import com.elearning.service.pub.IDDictionaryService;
import com.elearning.service.systemManage.ISysFormFieldService;
import com.elearning.vo.mixtraining.MtMixTrainUserTrainInfoForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/mtMixTrainUserTrainInfo/")
public class MtMixTrainUserTrainInfoController {

    @Autowired
    private IMtMixTrainUserTrainInfoService mtMixTrainUserTrainInfoService;

    @Autowired
    private IDDictionaryService dictionaryService;

    @Autowired
    private IOnlineTrainingService onlineTrainingService;

    @Autowired
    private ISysFormFieldService sysFormFieldService;


    /**
     * 获取当前操作驻留的培训项目Id
     * @param request
     * @return
     */
    private Integer getCurrentTrain(HttpServletRequest request){
        Integer trainId = -1;
        if(request.getSession().getAttribute("trainId") != null){
            String trainIdStr = request.getSession().getAttribute("trainId").toString();
            trainId = Integer.parseInt(trainIdStr);
        }
        return trainId;
    }

    /**
     * 获取当前操作人员
     * @param request
     * @return
     */
    private EosOperator getCurrentOperator(HttpServletRequest request){
        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        return eosoperator;
    }

    /**
     * 去查询页面
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("browseTrainUserInfo.do")
    public String browseTrainUserInfo(HttpServletRequest request){

        String jspName = "index";

        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return jspName;
        }

        Integer trainId = this.getCurrentTrain(request);
        TrainWithBLOBs train = this.onlineTrainingService.findById(trainId);

        Integer flag = 1; //判断是否显示“审核”和“删除”按钮

        //允许报名且不需要审核的，不显示“审核”及“删除”按钮
        if(train.getIsEnrolled()!=null && train.getIsNeedCheck()!=null && train.getIsEnrolled() && !train.getIsNeedCheck()){
            flag = 0;  //不显示
        }
        request.setAttribute("trainId", trainId);
        request.setAttribute("flag", flag);

        //审核状态：默认为“所有”
        String status = request.getParameter("status");
        if(status!=null && status!="" && !"0".equals(status)){
            request.setAttribute("status", status);
        }
        //回执情况：默认为“所有”
        String attendable = request.getParameter("attendable");
        if(attendable!=null  && attendable!="" && !"-2".equals(attendable)){
            request.setAttribute("attendable", attendable);
        }
        //参会类型：网上报名/email通知/直接参会/必须参加,默认为“所有”
        String attendType = request.getParameter("attendType");
        if(attendType!=null  && attendType!="" && !"0".equals(attendType)){
            request.setAttribute("attendType", attendType);
        }
        //学员名称
        String userName = request.getParameter("userName");
        if(userName!=null  && userName!="" && !"".equals(userName)){
            request.setAttribute("userName", userName);
        }
        //是否最终参会：默认“所有”
        String attended = request.getParameter("attended");
        if(attended!=null  && attended!="" && !"-1".equals(attended)){
            request.setAttribute("attended", attended);
        }
        //是否确认学时
        String classHourVerified = request.getParameter("classHourVerified");
        if(classHourVerified!=null  && classHourVerified!="" && !"-1".equals(classHourVerified)){
            request.setAttribute("classHourVerified", classHourVerified);
        }
        //获取参会渠道字典信息
        List<DDictionary> attendTypeList = this.dictionaryService.getByParentCode("2500");
        request.setAttribute("attendTypeList", attendTypeList);

        return "mixtraining/mixTrainingUserTrainInfoList";
    }

    /**
     * 根据查询条件进行查询
     * @param startIndex
     * @param count
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("searchMtMixTrainUserTrainInfoList.do")
    @ResponseBody
    public ServiceResponse searchMtMixTrainUserTrainInfoList(@RequestParam(value = "startIndex",defaultValue = "1")Integer startIndex,
                                                             @RequestParam(value = "count",defaultValue = "10")Integer count,
                                                             HttpServletRequest request){

        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }

        Integer trainId = this.getCurrentTrain(request);

        Map<String,Object> queryConditionMap = new HashMap<>();
        queryConditionMap.put("trainId",trainId);
        queryConditionMap.put("startIndex", startIndex);
        queryConditionMap.put("count", count);

        //回执情况：默认为“所有”
        String attendable = request.getParameter("attendable");
        if(attendable!=null  && attendable!="" && !"-2".equals(attendable)){
            queryConditionMap.put("attendable", attendable);
        }
        //参会类型：网上报名/email通知/直接参会/必须参加,默认为“所有”
        String attendType = request.getParameter("attendType");
        if(attendType!=null  && attendType!="" && !"0".equals(attendType)){
            queryConditionMap.put("attendType", attendType);
        }
        //学员名称
        String userName = request.getParameter("userName");
        if(userName!=null  && userName!="" && !"".equals(userName)){
            queryConditionMap.put("userName", userName);
        }
        //是否最终参会：默认“所有”
        String attended = request.getParameter("attended");
        if(attended!=null  && attended!="" && !"-1".equals(attended)){
            queryConditionMap.put("attended", attended);
        }
        //是否确认学时
        String classHourVerified = request.getParameter("classHourVerified");
        if(classHourVerified!=null  && classHourVerified!="" && !"-1".equals(classHourVerified)){
            queryConditionMap.put("classHourVerified", classHourVerified);
        }

        //以后也许会用到的字段
        //审核状态：默认为“所有”
        /*String status = request.getParameter("status");
        if(status!=null && status!="" && !"0".equals(status)){
            queryConditionMap.put("status",status);
        }
        String parentOrgName = request.getParameter("parentOrgName");
        if(parentOrgName!=null  && parentOrgName!="" && !"-1".equals(classHourVerified)){
            queryConditionMap.put("parentOrgName", parentOrgName);
        }*/

        //获取审核状态字典信息
        List<DDictionary> statusList = this.dictionaryService.getByParentCode("1050");
        request.setAttribute("statusList", statusList);

        return this.mtMixTrainUserTrainInfoService.searchMtMixTrainUserTrainInfoList(queryConditionMap,request);
    }

    /**
     * 操作--去修改页面
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("forupdate.do")
    public String forupdate(HttpServletRequest request){

        String jspName = "index";

        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return jspName;
        }

        int id = Integer.parseInt(request.getParameter("id").toString());
        MtMixTrainUserTrainInfo mtMixTrainUserTrainInfo = this.mtMixTrainUserTrainInfoService.findById(id);
        MtMixTrainUserTrainInfoForm mtMixTrainUserTrainInfoForm = new MtMixTrainUserTrainInfoForm();
        mtMixTrainUserTrainInfoForm.setId(id);
        if(mtMixTrainUserTrainInfo.getUserName()!=null){
            mtMixTrainUserTrainInfoForm.setUserName(mtMixTrainUserTrainInfo.getUserName());
        }
        if(mtMixTrainUserTrainInfo.getClassHour()!=null){
            mtMixTrainUserTrainInfoForm.setClassHour(mtMixTrainUserTrainInfo.getClassHour());
        }
        if(mtMixTrainUserTrainInfo.getRemark()!=null){
            mtMixTrainUserTrainInfoForm.setRemark(mtMixTrainUserTrainInfo.getRemark());
        }
        if(mtMixTrainUserTrainInfo.getHurrySummary()!=null && mtMixTrainUserTrainInfo.getHurrySummary()==1 ){
            mtMixTrainUserTrainInfoForm.setHurrySummary(1);
            mtMixTrainUserTrainInfoForm.setHurrySummaryName("是");
        }else{
            mtMixTrainUserTrainInfoForm.setHurrySummary(0);
            mtMixTrainUserTrainInfoForm.setHurrySummaryName("否");
        }
        request.setAttribute("userTrainInfo",mtMixTrainUserTrainInfoForm);

        return "mixtraining/mixTrainingUserTrainInfoforupdate";
    }

    /**
     * 真正的修改数据--确认按钮
     * @param mtMixTrainUserTrainInfoForm
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("update.do")
    public String update(MtMixTrainUserTrainInfoForm mtMixTrainUserTrainInfoForm,HttpServletRequest request){

        String jspName = "index";

        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return jspName;
        }

        int i = this.mtMixTrainUserTrainInfoService.update(mtMixTrainUserTrainInfoForm,request);

        if(i>0){
            return "forward:../mtMixTrainUserTrainInfo/detail.do";
        }else{
            return "error/error404";
        }
    }

    /**
     * 详细信息
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("detail.do")
    public String detail(HttpServletRequest request){

        String jspName = "index";

        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return jspName;
        }

        int id=Integer.parseInt(request.getParameter("id").toString());

        MtMixTrainUserTrainInfo mtMixTrainUserTrainInfo = this.mtMixTrainUserTrainInfoService.findById(id);
        MtMixTrainUserTrainInfoForm mtMixTrainUserTrainInfoForm = new MtMixTrainUserTrainInfoForm();
        if(mtMixTrainUserTrainInfo.getUserName()!=null){
            mtMixTrainUserTrainInfoForm.setUserName(mtMixTrainUserTrainInfo.getUserName());
        }
        if(mtMixTrainUserTrainInfo.getClassHour()!=null){
            mtMixTrainUserTrainInfoForm.setClassHour(mtMixTrainUserTrainInfo.getClassHour());
        }
        if(mtMixTrainUserTrainInfo.getRemark()!=null){
            mtMixTrainUserTrainInfoForm.setRemark(mtMixTrainUserTrainInfo.getRemark());
        }
        if(mtMixTrainUserTrainInfo.getHurrySummary()!=null && mtMixTrainUserTrainInfo.getHurrySummary()==1 ){
            mtMixTrainUserTrainInfoForm.setHurrySummaryName("是");
        }else{
            mtMixTrainUserTrainInfoForm.setHurrySummaryName("否");
        }
        request.setAttribute("userTrainInfo",mtMixTrainUserTrainInfoForm);

        return "mixtraining/mixTrainingUserTrainInfodetail";
    }

    /**
     * 删除
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("delete.do")
    public String delete(HttpServletRequest request){

        String jspName = "index";

        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return jspName;
        }

        int i = this.mtMixTrainUserTrainInfoService.delete(request);

        if(i>0){
            return "redirect:../mtMixTrainUserTrainInfo/browseTrainUserInfo.do";
        }else{
            return "error/error404";
        }
    }

    /**
     * 确认学时
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("updateattended.do")
    public String updateattended(HttpServletRequest request){

        String jspName = "index";

        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return jspName;
        }

        this.mtMixTrainUserTrainInfoService.updateattended(request);

        return "redirect:../mtMixTrainUserTrainInfo/browseTrainUserInfo.do";
    }

    /**
     * 直接添加最终参会人员("其他人员添加"按钮)
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("addAttendedUsers.do")
    public String addAttendedUsers(HttpServletRequest request){

        String jspName = "index";

        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return jspName;
        }

        this.mtMixTrainUserTrainInfoService.addAttendedUsers(request);

        return "redirect:../mtMixTrainUserTrainInfo/browseTrainUserInfo.do";
    }

    /**
     * 总结催促
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("hurrySummary.do")
    public String hurrySummary(HttpServletRequest request){

        String jspName = "index";

        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return jspName;
        }

        this.mtMixTrainUserTrainInfoService.hurrySummary(request);

        jspName = "redirect:../mtMixTrainUserTrainInfo/browseTrainUserInfo.do";

        return jspName;
    }

    /**
     *更改审核状态
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("updatestatus.do")
    public String updatestatus(HttpServletRequest request){

        String jspName = "index";
        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return jspName;
        }
        this.mtMixTrainUserTrainInfoService.updatestatus(request);

        return "redirect:../mtMixTrainUserTrainInfo/browseTrainUserInfo.do";
    }

    /**
     * 获取某培训班确认参会的人员名单
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("getAttendedList.do")
    @ResponseBody
    public ServiceResponse getAttendedList(HttpServletRequest request){
        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }

        return this.mtMixTrainUserTrainInfoService.getAttendedList(request);
    }

    /**
     *
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("updateTrainUserStatic.do")
    @ResponseBody
    public ServiceResponse updateTrainUserStatic(HttpServletRequest request){

        EosOperator operator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(operator == null) {
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }

        return this.mtMixTrainUserTrainInfoService.updateTrainUserStatic(request);
    }


    /**
     *点击预览-->班级成员时调用
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("execute.do")
    public String execute(HttpServletRequest request){

        String jspName = "index";
        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return jspName;
        }

        //学员名称
        String userNameInput=request.getParameter("userNameInput");
        if(userNameInput!=null && !"".equals(userNameInput)){
            request.setAttribute("userNameInput", userNameInput);
        }

        int train_id = Integer.parseInt(request.getParameter("train_id").toString());
        TrainWithBLOBs train = this.onlineTrainingService.findById(train_id);
        request.setAttribute("train", train);
        request.setAttribute("trainId", train.getID());

        return "mixtraining/mixTrainAttendedUserList";
    }

    /**
     * 根据查询条件进行查询
     * @param startIndex
     * @param count
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("getAttendedListForPaginationTool.do")
    @ResponseBody
    public ServiceResponse getAttendedListForPaginationTool(@RequestParam(value = "startIndex",defaultValue = "1")Integer startIndex,
                                                             @RequestParam(value = "count",defaultValue = "10")Integer count,
                                                             HttpServletRequest request){

        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }

        System.out.println(request.getParameter("trainId"));
        System.out.println(request.getSession().getAttribute("trainId"));

        Integer trainId = this.getCurrentTrain(request);

        Map<String,Object> queryConditionMap = new HashMap<>();
        queryConditionMap.put("trainId",trainId);
        queryConditionMap.put("tenantId", eosoperator.getTenantId());
        queryConditionMap.put("startIndex", startIndex);
        queryConditionMap.put("count", count);

        //学员名称
        String userNameInput = request.getParameter("userNameInput");
        if(userNameInput!=null  && userNameInput!="" && !"".equals(userNameInput)){
            queryConditionMap.put("userName", userNameInput);
        }

        return this.mtMixTrainUserTrainInfoService.getAttendedListForPaginationTool(queryConditionMap,request);
    }

}
