package com.elearning.controller.onlinetraining;

import com.elearning.common.Constants;
import com.elearning.common.ResponseCode;
import com.elearning.common.ServiceResponse;
import com.elearning.controller.BaseController;
import com.elearning.interceptor.loginInterceptor.IsCheckUserLogin;
import com.elearning.pojo.examManage.ExamExamInfo;
import com.elearning.pojo.mixtraining.MtTrainNewsWithBLOBs;
import com.elearning.pojo.pub.*;
import com.elearning.pojo.trainNeeds.TnNeedsQuestionnAire;
import com.elearning.service.examManage.IExamExamInfoService;
import com.elearning.service.mixtraining.IMtMixTrainUserTrainInfoService;
import com.elearning.service.mixtraining.IMtTrainNewsService;
import com.elearning.service.onlinetraining.IOnlineTrainingService;
import com.elearning.service.pub.*;
import com.elearning.service.systemManage.ISysFormFieldService;
import com.elearning.service.trainNeeds.ITnNeedsQuestionnAireService;
import com.elearning.util.PropertiesUtil;
import com.elearning.vo.onlinetraining.TrainForm;
import com.elearning.vo.pub.PageForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 *
 */
@Controller
@RequestMapping("/onlineTraining/")
public class OnlineTrainingController extends BaseController {

    @Autowired
    private IOnlineTrainingService onlineTrainingService;

    @Autowired
    private IEosOperatorService eosOperatorService;

    @Autowired
    private IEosorgTEmployeeService eosorgTEmployeeService;

    @Autowired
    private IDDictionaryService dictionaryService;

    @Autowired
    private ISysFormFieldService sysFormFieldService;

    @Autowired
    private ITrainOrganizationService trainOrganizationService;

    @Autowired
    private IEosorgTOrganizationService eosorgTOrganizationService;

    @Autowired
    private IExamExamInfoService examExamInfoService;

    @Autowired
    private ITnNeedsQuestionnAireService tnNeedsQuestionnAireService;

    @Autowired
    private IMtTrainNewsService mtTrainNewsService;

    @Autowired
    private IMtMixTrainUserTrainInfoService mtMixTrainUserTrainInfoService;


    @RequestMapping("getOnlineTrainingByID.do")
    @ResponseBody
    public ServiceResponse getOnlineTrainingByID(Integer ID, Model model){
        TrainWithBLOBs trainWithBLOBs = onlineTrainingService.getOnlineTrainingByID (ID);

        return ServiceResponse.createBySuccess(trainWithBLOBs);
    }

    /**
     * searchTrain
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("searchTrain.do")
    public String searchTrain(HttpServletRequest request){

        /**
         * 由于需求要求部门管理员只有培训班报名权限，培训管理员能管理所以培训班
         * 为防止报名的人进入培训班管理模块，增加判断条件当管理员权限不包含培训班管理时
         * 跳转到培训报名页面
         */
       /* String sessionTagForRole= SessionTemp.CURRENT_ROLE.getName();
        String adminMenuTag=SessionTemp.ADMIN_MENU.getName();
        Integer currentRoleId=(Integer)request.getSession().getAttribute(sessionTagForRole);
        Map<Integer,String> menuStringMap=(Map<Integer, String>) request.getSession().getAttribute(adminMenuTag);
        if(!menuStringMap.get(currentRoleId).contains("pxssgl")){
            //forward = mapping.findForward("toTrainCourseRegistration");
            //return forward;
            return "training/trainHome_cast";
        }*/

        if(!this.eosOperatorService.validateEnter(request)){
            return "template/ssofail";
        }

        EosOperator operator = (EosOperator)request.getSession().getAttribute(PropertiesUtil.getProperty("USERINFO_KEY"));

        // 获得培训类型
        List<DDictionary> trainTypeList = this.dictionaryService.getByParentCode("2100");
        request.setAttribute("trainTypeList", trainTypeList);

        // 获得精品项目类型
        List<DDictionary> creamProjectList = this.dictionaryService.findChildren("2230",operator.getTenantId(),1);
        request.setAttribute("creamProjectList", creamProjectList);

        //获得培训方式类型
        List<DDictionary> trainModeList = this.dictionaryService.findChildren("2240",operator.getTenantId(),1);

        request.setAttribute("trainModeList", trainModeList);

        TreeMap<Integer, HashMap<String, String>> formfieldSeqsMap = this.sysFormFieldService.getSysFormfieldSeqsMap(operator.getTenantId(),2);
        request.setAttribute("formfieldSeqsMap", formfieldSeqsMap);

        //培训年度
        String trainYearInput=request.getParameter("trainYearInput");
        if(trainYearInput!=null && !"".equals(trainYearInput)){
            request.setAttribute("trainYearInput", trainYearInput);
        }

        //培训名称
        String trainNameInput=request.getParameter("trainNameInput");
        if(trainNameInput!=null && !"".equals(trainNameInput)){
            request.setAttribute("trainNameInput", trainNameInput);
        }
        //关键词
        String trainKeyWordsTagInput=request.getParameter("trainKeyWordsTagInput");
        if(trainKeyWordsTagInput!=null && !"".equals(trainKeyWordsTagInput)){
            request.setAttribute("trainKeyWordsTagInput", trainKeyWordsTagInput);
        }
        //培训类型
        String trainTypeInput=request.getParameter("trainTypeInput");
        if(trainTypeInput!=null && !"0".equals(trainTypeInput)){
            request.setAttribute("trainTypeInput", trainTypeInput);
        }

        return "training/trainHome_cast";
    }

    /**
     * 培训班管理
     * @param startIndex
     * @param count
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping(value = "listTrains.do",method = RequestMethod.POST)
    @ResponseBody
    public ServiceResponse listTrains(@RequestParam(value = "startIndex",defaultValue = "1")Integer startIndex,
                                       @RequestParam(value = "count",defaultValue = "10")Integer count,
                                       HttpServletRequest request){

        EosOperator operator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);

        Map<String,Object> parmMap = new HashMap<>();

        parmMap.put("startIndex", startIndex);
        parmMap.put("count", count);
        parmMap.put("operatorId",operator.getOperatorId());

        Integer tenantId= Integer.parseInt(PropertiesUtil.getProperty("tenantId"));
        parmMap.put("tenantId",tenantId);

        int orgId = Integer.valueOf(request.getSession().getAttribute(Constants.ROOTORGID_KEY).toString());

        parmMap.put("orgId", orgId);

        //培训年度
        String trainYearInput=request.getParameter("trainYearInput");
        if(trainYearInput!=null && !"".equals(trainYearInput)){
            parmMap.put("year", trainYearInput);
            //request.setAttribute("searchYear", year);
        } else{
            parmMap.put("year", trainYearInput);
            //request.setAttribute("year", "");
        }

        //培训名称
        String trainNameInput=request.getParameter("trainNameInput");
        if(trainNameInput!=null && !"".equals(trainNameInput)){
            parmMap.put("trainName",trainNameInput);
        }
        //关键词
        String trainKeyWordsTagInput=request.getParameter("trainKeyWordsTagInput");
        if(trainKeyWordsTagInput!=null && !"".equals(trainKeyWordsTagInput)){
            parmMap.put("keyWordsTag",trainKeyWordsTagInput);
        }
        //培训类型
        String trainTypeInput=request.getParameter("trainTypeInput");
        if(trainTypeInput!=null && !"0".equals(trainTypeInput)){
            parmMap.put("trainTypeID",trainTypeInput);
        }else{
            parmMap.put("trainTypeID","");
        }

        if(request.getParameter("isStationTrain")==null || request.getParameter("isStationTrain").toString().equals("0")){
            parmMap.put("isStation", 0);
            request.getSession().setAttribute("isStationTrain",0);
        }
        if(request.getParameter("trainMode")!=null && !request.getParameter("trainMode").equals("") && !request.getParameter("trainMode").equals("-1")){
            Integer trainMode = Integer.valueOf(request.getParameter("trainMode").toString());
            parmMap.put("trainMode", trainMode);
            //request.setAttribute("trainMode", trainMode);
        }
        if(request.getParameter("creamProject")!=null && !request.getParameter("creamProject").equals("") &&!request.getParameter("creamProject").equals("-1")){
            Integer creamProject = Integer.valueOf(request.getParameter("creamProject").toString());
            parmMap.put("creamProject", creamProject);
            //request.setAttribute("creamProject", creamProject);
        }

        return onlineTrainingService.listTrains(parmMap);
    }


    /**
     * 培训班详情
     * fordetail
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("fordetail.do")
    public String fordetail(HttpServletRequest request){

        Integer trainID = 0;

        //修改了逻辑，原因：当修改培训班管理下的“管理”链接后，修改相应的培训班信息，再回到查询页面时，点培训班名称进去后不改变培训班详情bug，不知道会不会引发别的bug,需要测试
        if (request.getParameter("trainId")!=null) {
            trainID = Integer.valueOf(request.getParameter("trainId"));
        } else {
            trainID = Integer.parseInt(request.getSession().getAttribute("trainId").toString());
        }

       /* if (request.getSession().getAttribute("trainId")!=null) {
            trainID = Integer.parseInt(request.getSession().getAttribute("trainId").toString());
        } else {
            trainID = Integer.valueOf(request.getParameter("trainId"));
        }*/
        TrainWithBLOBs train = this.onlineTrainingService.findById(trainID);
        EosOperator operator = (EosOperator) request.getSession().getAttribute(Constants.USERINFO_KEY);
        String mode = request.getParameter("mode");

        //培训类别
        String trainType = "";
        List<DDictionary> dictionarydList = this.dictionaryService.findByCode(train.getTrainTypeID().toString());
        if(dictionarydList!=null && dictionarydList.size()>0){
            DDictionary dictionary = (DDictionary)dictionarydList.get(0);
            if(dictionary!=null){
                trainType = dictionary.getName();
            }
        }
        String subTrainType = "";
        if(train.getSubTrainTypeID()!=null){
            List _dictionarydList = this.dictionaryService.findByCode(train.getSubTrainTypeID().toString());
            if(_dictionarydList!=null && _dictionarydList.size()>0){
                DDictionary dictionary = (DDictionary)_dictionarydList.get(0);
                if(dictionary!=null){
                    subTrainType = dictionary.getName();
                }
            }
        }
        //精品项目
        String creamProject = "";
        if(train.getCreamProject()!=null){
            List<DDictionary> _dictionarydList = this.dictionaryService.findTrainByCodeAndTenantId(train.getCreamProject().toString(),operator.getTenantId());
            if(_dictionarydList!=null && _dictionarydList.size()>0){
                DDictionary dictionary = _dictionarydList.get(0);
                if(dictionary!=null){
                    creamProject = dictionary.getName();
                }
            }
        }
        //培训方式
        String trainMode = "";
        if(train.getTrainMode()!=null){
            List<DDictionary> _dictionarydList = this.dictionaryService.findTrainByCodeAndTenantId(train.getTrainMode().toString(),operator.getTenantId());
            if(_dictionarydList!=null && _dictionarydList.size()>0){
                DDictionary dictionary = _dictionarydList.get(0);
                if(dictionary!=null){
                    trainMode = dictionary.getName();
                }
            }
        }
        List<TrainOrganization> trainOrganizationList = this.trainOrganizationService.findTrainOrganizationByTrainID(trainID);
        StringBuffer facescopeName = new StringBuffer();
        for(TrainOrganization trainOrganization:trainOrganizationList){
            Integer _orgId = trainOrganization.getOrgId();
            //String _orgName = this.eosorgTOrganizationService.findById(_orgId).getOrgName();
            String _orgName = this.eosorgTOrganizationService.getEosorgTOrganizationById(_orgId).getOrgName();
            facescopeName.append(_orgName).append(";");
        }
        request.setAttribute("facescopeName", facescopeName.toString());
        String orgName = this.eosorgTOrganizationService.getEosorgTOrganizationById(train.getOrgId()).getOrgName();
        request.setAttribute("orgName", orgName);
        request.setAttribute("trainType", trainType);
        request.setAttribute("subTrainType", subTrainType);

        request.setAttribute("creamProject", creamProject);
        request.setAttribute("trainMode", trainMode);

        request.setAttribute("train", train);
        HashMap<String, HashMap<String, String>> formfieldAttributesMap = this.sysFormFieldService.getSysFormfieldAttributesMap(operator.getTenantId(),2);
        request.setAttribute("formfieldAttributesMap", formfieldAttributesMap);

        Integer tenantId=operator.getTenantId();
        request.setAttribute("tenant", tenantId);
        if(mode!=null && "copy".equals(mode)){
            request.setAttribute("mode", true);
        }else{
            request.setAttribute("mode", false);
        }

        return "training/traintraydetail";
     }

     /**
     *更改发布状态
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping(value = "updatePubStatus.do",method = RequestMethod.POST)
    @ResponseBody
    public ServiceResponse updatePubStatus(HttpServletRequest request){

        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }

        return onlineTrainingService.updatePubStatus(request);
    }

    /**
     *去新建培训班页面
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping(value = "foradd.do")
    public String foradd(HttpServletRequest request){

        EosOperator operator = (EosOperator) request.getSession().getAttribute( Constants.USERINFO_KEY);
        Integer userid = operator.getOperatorId();
        String operatorName = operator.getOperatorName();

        Integer orgId = Integer.parseInt(request.getSession().getAttribute(Constants.ROOTORGID_KEY).toString());
        String orgName = this.eosorgTOrganizationService.getEosorgTOrganizationById(orgId).getOrgName();
        request.setAttribute("_orgsname", orgName);

        List<DDictionary> trainTypeList = this.dictionaryService.getByParentCode("2100");

        List<DDictionary> openScopeList = this.dictionaryService.findByCodeAndTenantIdAndVisible("2200",operator.getTenantId(),1); // 获得开放范围类型
        List<DDictionary> creamProjectList = this.dictionaryService.findChildren("2230",operator.getTenantId(),1); // 获得精品项目类型

        List<DDictionary> trainModeList = this.dictionaryService.findChildren("2240",operator.getTenantId(),1); // 获得培训方式类型
        HashMap param = new HashMap();
        param.put("login_user_id", userid);
        request.setAttribute("trainTypeList", trainTypeList);
        request.setAttribute("openScopeList", openScopeList);
        request.setAttribute("creamProjectList", creamProjectList);
        request.setAttribute("trainModeList", trainModeList);
        request.setAttribute("trainYear", this.getSession(request, Constants.YEAR_KEY));
        //Train train = new Train();
        TrainWithBLOBs train = new TrainWithBLOBs();
        if(request.getParameter("trainId") == null || request.getParameter("trainId").trim().equals("")){
            int isStationTrain = Integer.valueOf(request.getSession().getAttribute("isStationTrain").toString());
            if(isStationTrain==0){
                train.setTrainWay(2);
            }
            train.setIsEnrolled(Boolean.valueOf("0"));
            train.setIsStationTrain(Boolean.getBoolean(isStationTrain+""));
            train.setOrgId(orgId);
            train.setTrainPlanID(Long.valueOf(-1));
            train.setOrganizerName(operatorName);
            train.setSponsorName(orgName);
            train.setIfBJ(1);
            /*if(openScopeList.size()>0){
                train.setOpenScope(Integer.parseInt(openScopeList.get(0).getCode()));
            }*/
            EosorgTEmployee employee=operator.getEosorgTEmployee();
            if( employee.getOEmail()!=null && !"".equals(employee.getOEmail())){
                train.setOrganizerEmail(employee.getOEmail());
            }
            if(employee.getOTel1()!=null && !"".equals(employee.getOTel1())){
                train.setTelephone(employee.getOTel1());
            }

            EosorgTOrganization eosorgTOrganization = this.eosorgTOrganizationService.getEosorgTOrganizationById(employee.getOrgID());
            Integer operatorId = eosorgTOrganization.getOrgID();
            train.setOperatorId(operatorId); // 创建人id 实际存储创建人机构id

            train.setOrgName(orgName);
        } else{
            train = this.initialize(request);
        }
        request.setAttribute("train", train);
        request.setAttribute("trainID", -1);
        //HashMap<String, HashMap<String, String>> formfieldAttributesMap = this.sysFormFieldService.getSysFormfieldAttributesMap(operator.getTenantId(),2);
        //request.setAttribute("formfieldAttributesMap", formfieldAttributesMap);

        return "training/trainBasicInfo";
    }

    public TrainWithBLOBs initialize(HttpServletRequest request){
        String trainId = request.getParameter("trainId");
        TrainWithBLOBs train = this.onlineTrainingService.findById(Integer.parseInt(trainId));
        return train;
    }

    /**
     *新建培训班
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping(value = "add.do")
    public String add(TrainForm trainForm, HttpServletRequest request){
        String jspName = "test/error";
        int result = this.onlineTrainingService.add(trainForm,request);

        if(result > 0){
            jspName = "redirect:../onlineTraining/searchTrain.do";
        }

        return jspName;
    }

    /**
     *更改培训班信息
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping(value = "forupdate.do")
    public String forupdate(HttpServletRequest request){

        String jspName = "index";
        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return jspName;
        }

        Integer tenantId = onlineTrainingService.forupdate(request);

        if(tenantId!=null && tenantId==1){
            String executePlan=request.getParameter("executePlan");
            request.setAttribute("executePlan",executePlan);
            jspName = "training/jgTraintrayforupdate";
        } else{
            jspName = "training/traintrayforupdate";
        }

        return jspName;
    }

    /**
     * 删除培训班
     *
     * @param selectbox
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("delTrainById.do")
    @ResponseBody
    public ServiceResponse delTrainById(String[] selectbox){
        if(selectbox == null){
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        return onlineTrainingService.delTrainById(selectbox);
    }

    /**
     *预览培训班详情信息
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping(value = "viewTrain4Admin.do")
    public String viewTrain4Admin(HttpServletRequest request){

        Integer trainId = Integer.parseInt(request.getParameter("trainId"));
        TrainWithBLOBs train = this.onlineTrainingService.findById(trainId);

        if(train.getAttendants()!=null){
            train.setAttendants(train.getAttendants().replace("\r\n", "<br/>"));
        }
        if(train.getTrainGoal()!=null){
            train.setTrainGoal(train.getTrainGoal().replace("\r\n", "<br/>"));
        }
        if(train.getTrainingContent()!=null){
            train.setTrainingContent(train.getTrainingContent().replace("\r\n", "<br/>"));
        }

        EosOperator eosOperator = (EosOperator)request.getSession().getAttribute(PropertiesUtil.getProperty("USERINFO_KEY"));

        Integer userid = 0;
        if(eosOperator == null){
            userid= -1;
        }else{
            HttpSession session = request.getSession();
            String systemTime = session.getAttribute(Constants.YEAR_KEY)!=null?session.getAttribute(Constants.YEAR_KEY).toString():String.valueOf(new Date().getYear());
            Date nowdate = new Date();
            Map<String,Object> parmMap=new HashMap();
            parmMap.put("systemTime", systemTime);
            parmMap.put("nowdate", nowdate);
            parmMap.put("trainId", trainId);

            try{
                userid=eosOperator.getOperatorId();
            }catch(Exception e){
                return "error/error404";
            }

            PageForm pageForm = new PageForm();

            parmMap.put("startIndex", pageForm.getPageSize() * (pageForm.getPageNo() - 1));//pageSize * (pageNo - 1)
            parmMap.put("count", pageForm.getPageSize());

            //获取学员端考试列表
            List<ExamExamInfo> myExamList = this.examExamInfoService.findByUserAndTime(parmMap,eosOperator);

            // 获得培训类型
            List<DDictionary> trainTypeList = this.dictionaryService.getByParentCode("1040");

            //获取调查列表
            Map<String,Object> _map = new HashMap();
            _map.put("trainId", trainId);
            _map.put("operatorId", userid);
            _map.put("startIndex", pageForm.getPageSize() * (pageForm.getPageNo() - 1));//pageSize * (pageNo - 1)
            _map.put("count", pageForm.getPageSize());

            List<TnNeedsQuestionnAire> tnNeedsQuestionnAireList = this.tnNeedsQuestionnAireService.getMyTnNeedsList(_map);

            if (tnNeedsQuestionnAireList!=null && tnNeedsQuestionnAireList.size()>0) {
                request.setAttribute("myTnNeedsList", tnNeedsQuestionnAireList);
            }
            //获取新闻列表
            List<MtTrainNewsWithBLOBs> trainNewsList = this.mtTrainNewsService.findByTrainId(trainId);//默认当前trainId为0
            request.setAttribute("trainNewsList", trainNewsList);

            request.setAttribute("myExamList",myExamList);
            request.setAttribute("trainForm", train);
            request.setAttribute("trainTypeList", trainTypeList);
            request.setAttribute("userId", userid);
        }

        int userApplyTrainStatus=this.mtMixTrainUserTrainInfoService.getUserApplyTrainStatus(trainId, userid);

        request.setAttribute("applyTrainStatus", userApplyTrainStatus);
        request.setAttribute("train", train);
        request.setAttribute("trainForm", this.onlineTrainingService.getTrainForm(train,userid));
        request.setAttribute("trainId", train.getID());
        request.getSession().setAttribute("trainId", train.getID());
        request.setAttribute("userId", userid);

        String jspName = "";
        //培训方式：0，线上培训；1，线下培训；2，混合（线上+线下）培训
        if(train.getTrainWay()==0){
            jspName = "alltraining/trainBasicInfo4Online";
        }else{
            jspName = "alltraining/trainBasicInfo4Admin";
        }

        return jspName;
    }

    /**
     * 查询某培训项目对应的模板图片
     *
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("getTrainTopBand.do")
    @ResponseBody
    public ServiceResponse getTrainTopBand(HttpServletRequest request){

        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }

        Integer trainId = Integer.parseInt(request.getParameter("trainId").toString());

        return onlineTrainingService.getTrainTopBand(trainId);
    }

    /**
     * 判断某用户是否参加了特定培训班(-1:未登录;0:已登录未参加;1:已参加)
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("getIfUserJoinTrain.do")
    @ResponseBody
    public ServiceResponse getIfUserJoinTrain(HttpServletRequest request){

        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }

        Integer trainId=Integer.parseInt(request.getParameter("trainId").toString());

        return onlineTrainingService.getIfUserJoinTrain(eosoperator,trainId);
    }

    @IsCheckUserLogin(check = true)
    @RequestMapping(value = "update.do")
    public String update(TrainForm trainForm,HttpServletRequest request){

        String jspName = "test/error";

        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return jspName;
        }

        int result = this.onlineTrainingService.update(trainForm,request);

        if(result > 0){
            jspName = "redirect:../onlineTraining/fordetail.do";
        }

        return jspName;
    }


}
