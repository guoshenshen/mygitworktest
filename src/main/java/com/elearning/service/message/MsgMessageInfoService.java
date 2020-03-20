package com.elearning.service.message;

import com.elearning.common.*;
import com.elearning.dao.message.MsgMessageInfoMapper;
import com.elearning.pojo.courseStudy.UserTrain;
import com.elearning.pojo.courseStudy.UserTrainKey;
import com.elearning.pojo.message.MsgMessageDept;
import com.elearning.pojo.message.MsgMessageInfo;
import com.elearning.pojo.message.MsgMessageInfoWithBLOBs;
import com.elearning.pojo.message.MsgMessageUser;
import com.elearning.pojo.mixtraining.MtMixTrainUserTrainInfo;
import com.elearning.pojo.pub.*;
import com.elearning.pojo.systemManage.Tenant;
import com.elearning.service.courseStudy.IUserTrainService;
import com.elearning.service.mixtraining.IMtMixTrainUserTrainInfoService;
import com.elearning.service.onlinetraining.IOnlineTrainingService;
import com.elearning.service.pub.IDDictionaryService;
import com.elearning.service.pub.IEosOperatorService;
import com.elearning.service.pub.IEosorgTEmployeeService;
import com.elearning.service.pub.IEosorgTOrganizationService;
import com.elearning.service.systemManage.ITenantService;
import com.elearning.util.DateTimeUtil;
import com.elearning.vo.BasicUserForm;
import com.elearning.vo.message.MsgMessageInfoForm;
import com.elearning.vo.pub.BasicOrgForm;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


@Service("msgMessageInfoService")
public class MsgMessageInfoService implements IMsgMessageInfoService{

    @Autowired
    private MsgMessageInfoMapper msgMessageInfoMapper;

    @Autowired
    private IDDictionaryService dictionaryService;

    @Autowired
    private IEosorgTEmployeeService eosorgTEmployeeService;

    @Autowired
    private IEosOperatorService eosOperatorService;

    @Autowired
    private IOnlineTrainingService onlineTrainingService;

    @Autowired
    private IEosorgTOrganizationService eosorgTOrganizationService;

    @Autowired
    private IMsgMessageUserService msgMessageUserService;

    @Autowired
    private IMsgMessageDeptService msgMessageDeptService;

    @Autowired
    private ITenantService tenantService;

    @Autowired
    private IUserTrainService userTrainService;

    @Autowired
    private IMtMixTrainUserTrainInfoService mtMixTrainUserTrainInfoService;

    private MailSender sender;


    @Override
    public MsgMessageInfoWithBLOBs selectByPrimaryKey(Integer ID){
        return this.msgMessageInfoMapper.selectByPrimaryKey(ID);
    }

    @Override
    public int deleteByPrimaryKey(Integer ID){
        return this.msgMessageInfoMapper.deleteByPrimaryKey(ID);
    }

    @Override
    public void updateEnrollTime(TrainWithBLOBs trainToUpdate){
        if(null != trainToUpdate && null != trainToUpdate.getID()){
            if(trainToUpdate.getIsEnrolled() != null  && trainToUpdate.getIsEnrolled()){ //允许报名
                List<MsgMessageInfoWithBLOBs> msgInfoList = this.msgMessageInfoMapper.findByTrainId(trainToUpdate.getID());
                if(null != msgInfoList && msgInfoList.size()>0){
                    MsgMessageInfoWithBLOBs msgMessageInfo = null;
                    for(MsgMessageInfoWithBLOBs msgInfo:msgInfoList){
                        if(null != msgInfo && null != msgInfo.getEwmType() && null != msgInfo.getInvalidDate()){
                            msgMessageInfo = msgInfo;
                            break;
                        }
                    }
                    if(null != msgMessageInfo){
                        if(null != trainToUpdate.getProgramStartTime()){
                            msgMessageInfo.setValidDate(trainToUpdate.getProgramStartTime());
                        }
                        if(null != trainToUpdate.getProgramEndTime()){
                            msgMessageInfo.setInvalidDate(trainToUpdate.getProgramEndTime());
                        }
                        this.msgMessageInfoMapper.updateByPrimaryKeySelective(msgMessageInfo);
                    }
                }
            }
        }
    }

    @Override
    public List<MsgMessageInfoWithBLOBs> findByTrainId(Integer trainId){
        return this.msgMessageInfoMapper.findByTrainId(trainId);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public List<MsgMessageInfoForm> getMessageFormList(List<MsgMessageInfoWithBLOBs> messageList){
        List<MsgMessageInfoForm> messageFormList = new ArrayList<>();
        for (MsgMessageInfoWithBLOBs msgMessageInfo : messageList) {
            messageFormList.add(this.getMsgBaseInfoForm(msgMessageInfo));
        }
        return messageFormList;
    }

    private MsgMessageInfoForm getMsgBaseInfoForm(MsgMessageInfoWithBLOBs msgMessageInfo){
        MsgMessageInfoForm msgMessageInfoForm = new MsgMessageInfoForm();

        if (msgMessageInfo.getID() != null) {
            msgMessageInfoForm.setId(msgMessageInfo.getID());
        }
        if (msgMessageInfo.getTitle() != null) {
            msgMessageInfoForm.setTitle(msgMessageInfo.getTitle());
        }
        if (msgMessageInfo.getValidDate() != null) {
            msgMessageInfoForm.setValidDate(DateTimeUtil.dateToStr(msgMessageInfo.getValidDate()));
        }
        if (msgMessageInfo.getInvalidDate() != null) {
            msgMessageInfoForm.setInvalidDate(DateTimeUtil.dateToStr(msgMessageInfo.getInvalidDate()));
        }
        if (msgMessageInfo.getIsPublic() != null) {
            msgMessageInfoForm.setIsPublic(msgMessageInfo.getIsPublic());
            List<DDictionary> dDictionaryList = this.dictionaryService.find(msgMessageInfo.getIsPublic().toString(), "1150");
            if(dDictionaryList!=null && dDictionaryList.size()>0){
                String publicName =dDictionaryList.get(0).getName();
                msgMessageInfoForm.setPublicName(publicName);
            }
            Integer isPublic = msgMessageInfo.getIsPublic();
            Integer hasSend = msgMessageInfo.getHasSend();
            Integer isEmail = msgMessageInfo.getIsEmail();
            Integer isSMS = msgMessageInfo.getIsSMS();

            if(isPublic==1153){//系统公告
                msgMessageInfoForm.setMessageCategory("系统公告");
                msgMessageInfoForm.setMessageType("学员通知消息 | 公告动态");
                if(hasSend==null){
                    msgMessageInfoForm.setMessageType("学员通知消息 | 公告动态");
                }else if(hasSend==0){
                    msgMessageInfoForm.setMessageType("学员通知消息");
                }else if(hasSend==1){
                    msgMessageInfoForm.setMessageType("学员通知消息 | 公告动态");
                }
            }

            if(isPublic==1152){//系统通知
                msgMessageInfoForm.setMessageCategory("系统通知");
                if(isSMS!=null&&isEmail==null){
                    msgMessageInfoForm.setMessageType("学员通知消息 | 手机短信");
                }else if(isEmail!=null&&isSMS==null){
                    msgMessageInfoForm.setMessageType("学员通知消息 | 邮件");
                }else if(isEmail!=null&&isSMS!=null){
                    msgMessageInfoForm.setMessageType("学员通知消息 | 邮件| 手机短信");
                }else if(isEmail==null&&isSMS==null){
                    msgMessageInfoForm.setMessageType("学员通知消息");
                }
            }

            if(isPublic==1156){//报名通知
                msgMessageInfoForm.setIsEmail(isEmail);
            }

            if(isPublic==1157){//提醒通知
                msgMessageInfoForm.setIsEmail(isEmail);//默认邮件
                if(isSMS!=null){
                    msgMessageInfoForm.setIsSMS(isSMS);//附件短信通知
                }
            }
        }
        if (msgMessageInfo.getHasSend() != null){
            msgMessageInfoForm.setHasSend(msgMessageInfo.getHasSend());
        }
        if (msgMessageInfo.getContent() != null){
            msgMessageInfoForm.setContent(msgMessageInfo.getContent());
        }
        if(msgMessageInfo.getSmsContent()!=null){
            msgMessageInfoForm.setSmscontent(msgMessageInfo.getSmsContent());
        }
        if (msgMessageInfo.getStatus() != null) {
            msgMessageInfoForm.setStatus(msgMessageInfo.getStatus());
            List<DDictionary> dDictionaryList = this.dictionaryService.find(msgMessageInfo.getStatus().toString(), "1090");
            if(dDictionaryList!=null && dDictionaryList.size()>0){
                String statusName = dDictionaryList.get(0).getName();
                msgMessageInfoForm.setStatusName(statusName == null ? "" : statusName);
            }
        }
        if (msgMessageInfo.getAttachmentPath() != null) {
            msgMessageInfoForm.setAttachmentPath(msgMessageInfo.getAttachmentPath());
        }
        if (msgMessageInfo.getEditorID() != null) {
            if(eosorgTEmployeeService.findById(msgMessageInfo.getEditorID())!=null){
                msgMessageInfoForm.setPublicer(eosorgTEmployeeService.findById(msgMessageInfo.getEditorID()).getOrgName());
            }
        }
        if(msgMessageInfo.getBackflag()!=null){
            msgMessageInfoForm.setBackflag(msgMessageInfo.getBackflag());
        }
        if(msgMessageInfo.getRecommendTag()!=null){
            msgMessageInfoForm.setRecommendTag(msgMessageInfo.getRecommendTag());
        }

        System.out.println(msgMessageInfo.getTrainID());
        if(msgMessageInfo.getTrainID()!=null){
            msgMessageInfoForm.setTrainId(msgMessageInfo.getTrainID());
            if(this.onlineTrainingService.findById(msgMessageInfo.getTrainID())!=null){
                String trainName = this.onlineTrainingService.findById(msgMessageInfo.getTrainID()).getTrainName();
                msgMessageInfoForm.setTrainName(trainName);
            }
        }
        if(msgMessageInfo.getConfirmType()!=null){
            msgMessageInfoForm.setConfirmType(msgMessageInfo.getConfirmType());
        }

        return msgMessageInfoForm;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public List<BasicOrgForm> getSelectedOrgList(Map<String,Object> condition){

        List<BasicOrgForm> orgList=new ArrayList<>();
        StringBuffer query=new StringBuffer(" ,msg_message_dept mmd where org.orgId=mmd.deptID ");
        if(condition.get("msgId")!=null){
            query.append(" and mmd.msgId=:msgId ");
        }
        if(condition.get("orgId")!=null){
            query.append(" and org.orgId=:orgId");
        }
        orgList=this.eosorgTOrganizationService.findByConditionSQL(query.toString(), condition,null);
        return orgList;

    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public List<BasicUserForm> getSpecifiedUserList(Map<String,Object> condition, Integer tenantId){

        List<BasicUserForm> userList=new ArrayList<BasicUserForm>();
        StringBuffer query=new StringBuffer(", msg_message_user mmu where operator.operatorId=mmu.userId ");
        if(condition.get("msgId")!=null){
            query.append(" and mmu.msgId=:msgId ");
        }
        if(condition.get("role")!=null){
            query.append(" and mmu.role=:role");
        }
        if(condition.get("operatorName")!=null){
            query.append(" and operator.operatorName like '%:operatorName%' ");
        }
        if(condition.get("orgId")!=null){
            query.append(" and org.orgId=:orgId");
        }

        userList=this.eosorgTEmployeeService.findBasicUserInfoByCondition(query.toString(), tenantId, condition,null);

        return userList;

    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public ServiceResponse listOldMessage(Map<String,Object> map, HttpServletRequest request){
        PageHelper.startPage((Integer) map.get("startIndex"),(Integer) map.get("count"));

        List<MsgMessageInfoWithBLOBs> msgMessageInfoList = this.msgMessageInfoMapper.queryMessageList(map);

        PageInfo pageInfo = new PageInfo(msgMessageInfoList);
        pageInfo.setList(msgMessageInfoList);

        msgMessageInfoList = pageInfo.getList();

        List<MsgMessageInfoForm> msgMessageInfoFormList = new ArrayList<>();

        for (MsgMessageInfoWithBLOBs msgMessageInfo : msgMessageInfoList) {
            msgMessageInfoFormList.add(this.getMsgBaseInfoForm(msgMessageInfo));
        }

        pageInfo.setList(msgMessageInfoFormList);
        return ServiceResponse.createBySuccess(pageInfo);

    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public ServiceResponse changeStatus(HttpServletRequest request){
        String id = request.getParameter("id");
        if (id!=null && !"".equals(id)) {
            MsgMessageInfoWithBLOBs msg = this.msgMessageInfoMapper.selectByPrimaryKey(Integer.parseInt(id));
            if(msg != null){
                Integer status = msg.getStatus();
                if (status==null || status==1091) { //未推送变已推送
                    msg.setStatus(1092);
                } else {
                    msg.setStatus(1091);
                }
                this.msgMessageInfoMapper.updateByPrimaryKeyWithBLOBs(msg);
                return ServiceResponse.createBySuccess();
            }else{
                return ServiceResponse.createByError();
            }
        } else {
            return ServiceResponse.createByError();
        }
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public ServiceResponse changeRecommendMsg(HttpServletRequest request){
        String id = request.getParameter("id");
        if (id!=null && !"".equals(id)) {
            MsgMessageInfoWithBLOBs msg = this.msgMessageInfoMapper.selectByPrimaryKey(Integer.parseInt(id));
            if(msg != null){
                Integer recommend = msg.getRecommendTag();
                if (recommend==null || recommend==0) { //未推送变已推送
                    msg.setRecommendTag(1);
                    msg.setStatus(1092);
                } else {
                    msg.setRecommendTag(0);
                }
                this.msgMessageInfoMapper.updateByPrimaryKeyWithBLOBs(msg);
                return ServiceResponse.createBySuccess();
            }else{
                return ServiceResponse.createByError();
            }
        } else {
            return ServiceResponse.createByError();
        }
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public int add(MsgMessageInfoForm msgMessageInfoForm,HttpServletRequest request){

        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);

        //id of creator
        int editorId = eosoperator.getOperatorId();
        msgMessageInfoForm.setEditorId(editorId);
        //set unpublished tag
        msgMessageInfoForm.setStatus(1091);
        //msg is of email type
        msgMessageInfoForm.setIsEmail(1);
        if(msgMessageInfoForm.getTipType()!=null && msgMessageInfoForm.getTipType().equals("1")){
            msgMessageInfoForm.setIsSMS(1);//表示选择手机短信
            //msgMessageInfoForm.setContent(msgMessageInfoForm.getSmscontent());//保存短信内容
        }
        msgMessageInfoForm.setValidDate(DateTimeUtil.dateToStr(new Date(),"yyyy-MM-dd HH:mm:ss"));
        String[] orgIdList=request.getParameterValues("orgSelect");
        List<Integer> deptList=new ArrayList<Integer>();
        if(orgIdList!=null){
            for(String orgId:orgIdList){
                deptList.add(Integer.parseInt(orgId));
            }
            msgMessageInfoForm.setDeptList(deptList);
        }
        String[] studentIdListStr=request.getParameterValues("studentList");
        List<Integer> studentIdList=new ArrayList<Integer>();
        if(studentIdListStr!=null){
            for(String operatorIdStr:studentIdListStr){
                studentIdList.add(Integer.parseInt(operatorIdStr));
            }
            msgMessageInfoForm.setStudentIdList(studentIdList);
        }
        String[] secretaryIdListStr=request.getParameterValues("secretaryList");
        List<Integer> secretaryIdList=new ArrayList<Integer>();
        if(secretaryIdListStr!=null){
            for(String operatorIdStr:secretaryIdListStr){
                secretaryIdList.add(Integer.parseInt(operatorIdStr));
            }
            msgMessageInfoForm.setSecretaryIdList(secretaryIdList);
        }
        String[] directorIdListStr=request.getParameterValues("directorList");
        List<Integer> directorIdList=new ArrayList<Integer>();
        if(directorIdListStr!=null){
            for(String operatorIdStr:directorIdListStr){
                directorIdList.add(Integer.parseInt(operatorIdStr));
            }
            msgMessageInfoForm.setDirectorIdList(directorIdList);
        }
        msgMessageInfoForm.setTenantId(eosoperator.getTenantId());

        int msgId = this.save(msgMessageInfoForm);
        msgMessageInfoForm.setId(msgId);

        this.saveMsgArrangeUsers(msgMessageInfoForm);
        request.setAttribute("id", msgId);

        return msgId;
    }

    public int save(MsgMessageInfoForm msgMessageInfoForm) {
        MsgMessageInfoWithBLOBs msgMessageInfo = new MsgMessageInfoWithBLOBs();

        this.msgMessageInfoMapper.insertSelective(this.formTomsgMessageInfo(msgMessageInfo, msgMessageInfoForm));

        int msgId = msgMessageInfo.getID();
        return msgId;
    }

    private MsgMessageInfoWithBLOBs formTomsgMessageInfo(MsgMessageInfoWithBLOBs msgMessageInfo, MsgMessageInfoForm msgMessageInfoForm) {
        msgMessageInfo.setID(msgMessageInfoForm.getId());
        if (msgMessageInfoForm.getTitle() != null){
            msgMessageInfo.setTitle(msgMessageInfoForm.getTitle());
        }
        if (msgMessageInfoForm.getValidDate() != null) {
            msgMessageInfo.setValidDate(Tools.stringToSeconds(msgMessageInfoForm.getValidDate()));
        }
        if (msgMessageInfoForm.getInvalidDate() != null) {
            msgMessageInfo.setInvalidDate(Tools.stringToSeconds(msgMessageInfoForm.getInvalidDate()));
        }
        if (msgMessageInfoForm.getIsPublic() != null){
            msgMessageInfo.setIsPublic(msgMessageInfoForm.getIsPublic());
        }

        if (msgMessageInfoForm.getContent() != null){
            msgMessageInfo.setContent(msgMessageInfoForm.getContent());
        }

        if(msgMessageInfoForm.getTipType()!=null&&msgMessageInfoForm.getTipType().equals("1")){
            //msgMessageInfo.setContent(msgMessageInfoForm.getSmscontent());
            msgMessageInfo.setSmsContent(msgMessageInfoForm.getSmscontent());
            msgMessageInfo.setIsSMS(1);
        }else{
            msgMessageInfo.setIsSMS(null);
            msgMessageInfo.setSmsContent(null);
        }

        if (msgMessageInfoForm.getEditorId() != null){
            msgMessageInfo.setEditorID(msgMessageInfoForm.getEditorId());
        }
        if (msgMessageInfoForm.getStatus() != null){
            msgMessageInfo.setStatus(msgMessageInfoForm.getStatus());
        }
        if (msgMessageInfoForm.getIsEmail() != null){
            msgMessageInfo.setIsEmail(msgMessageInfoForm.getIsEmail());
        }
        if (msgMessageInfoForm.getIsSMS() != null){
            msgMessageInfo.setIsSMS(msgMessageInfoForm.getIsSMS());
            msgMessageInfo.setSmsContent(msgMessageInfoForm.getSmscontent());
        }
        if (msgMessageInfoForm.getInSystem() != null){
            msgMessageInfo.setInSystem(msgMessageInfoForm.getInSystem());
        }

        if(msgMessageInfoForm.getIsPublic()!=null && msgMessageInfoForm.getIsPublic().intValue()==1156) {
            msgMessageInfo.setBackflag(1);
        }else{
            msgMessageInfo.setBackflag(null);
        }
        if(msgMessageInfoForm.getConfirmType()!=null){
            msgMessageInfo.setConfirmType(msgMessageInfoForm.getConfirmType());
        }
        if(msgMessageInfoForm.getTrainId()!=null){
            msgMessageInfo.setTrainID(msgMessageInfoForm.getTrainId());
        }
        if (msgMessageInfoForm.getTenantId() != null){
            msgMessageInfo.setTenantId(msgMessageInfoForm.getTenantId());
        }
        if(msgMessageInfoForm.getRecommendTag()!=null){
            msgMessageInfo.setRecommendTag(msgMessageInfoForm.getRecommendTag());
        }
        if (msgMessageInfoForm.getAttachmentPath()!=null){
            msgMessageInfo.setAttachmentPath(msgMessageInfoForm.getAttachmentPath());
        }

        return msgMessageInfo;
    }

    /*
     * 为所有培训通知对象创建回执记录
     */
    public void saveMsgArrangeUsers(MsgMessageInfoForm msgMessageInfoForm) {
        //organizers用于避免重复创建回执情况记录
        Set<Integer> organizers=new HashSet<Integer>();
        //将部门领导添加到通知对象中
        if(msgMessageInfoForm.getDirectorIdList()!=null && !msgMessageInfoForm.getDirectorIdList().equals("")){
            List<Integer> directorIds = msgMessageInfoForm.getDirectorIdList();
            for(Integer directorId:directorIds){
                if(directorId!=null && !directorId.equals("") && !organizers.contains(directorId)){
                    organizers.add(directorId);
                    MsgMessageUser msgMessageUser = new MsgMessageUser();
                    msgMessageUser.setMsgID(msgMessageInfoForm.getId());
                    msgMessageUser.setUserID(Integer.valueOf(directorId));
                    //领导标识
                    msgMessageUser.setRole(2);
                    msgMessageUser.setAttendable(-1);
                    msgMessageUser.setTrainID(msgMessageInfoForm.getTrainId());
                    this.msgMessageUserService.insertSelective(msgMessageUser);
                }
            }
        }
        //将部门通讯联络员添加到通知对象中
        if(msgMessageInfoForm.getSecretaryIdList()!=null&&!msgMessageInfoForm.getSecretaryIdList().equals("")){
            List<Integer> secretaryIds = msgMessageInfoForm.getSecretaryIdList();
            for(Integer secretaryId:secretaryIds){
                if(secretaryId!=null && !secretaryId.equals("")&&!organizers.contains(secretaryId)){
                    organizers.add(secretaryId);
                    MsgMessageUser msgMessageUser = new MsgMessageUser();
                    msgMessageUser.setMsgID(msgMessageInfoForm.getId());
                    msgMessageUser.setUserID(Integer.valueOf(secretaryId));
                    //联络员标识
                    msgMessageUser.setRole(1);
                    msgMessageUser.setAttendable(-1);
                    msgMessageUser.setTrainID(msgMessageInfoForm.getTrainId());
                    this.msgMessageUserService.insertSelective(msgMessageUser);
                }
            }
        }
        //将学员按姓名添加到通知对象中
        if(msgMessageInfoForm.getStudentIdList()!=null&&!msgMessageInfoForm.getStudentIdList().equals("")){
            List<Integer> studentIds = msgMessageInfoForm.getStudentIdList();
            for(Integer studentId:studentIds){
                if(studentId!=null && !studentId.equals("")&&!organizers.contains(studentId)){
                    organizers.add(studentId);
                    MsgMessageUser msgMessageUser = new MsgMessageUser();
                    msgMessageUser.setMsgID(msgMessageInfoForm.getId());
                    msgMessageUser.setUserID(Integer.valueOf(studentId));
                    //普通通知对象标识
                    msgMessageUser.setRole(0);
                    msgMessageUser.setAttendable(-1);
                    msgMessageUser.setTrainID(msgMessageInfoForm.getTrainId());
                    this.msgMessageUserService.insertSelective(msgMessageUser);
                }
            }
        }

        //按选定部门将学员添加到通讯录中
        List<Integer> deptIdList=msgMessageInfoForm.getDeptList();
        if(deptIdList!=null){
            for(Integer deptId:deptIdList){
                MsgMessageDept msgMessageDept = new MsgMessageDept();
                msgMessageDept.setMsgID(msgMessageInfoForm.getId());
                msgMessageDept.setDeptID(Integer.valueOf(deptId));
                this.msgMessageDeptService.insertSelective(msgMessageDept);

                //将人员放到msg_message_user表

                //tenantId传null
                //List<BasicUserForm> findBasicUserInfoByOrgId(msgMessageDept.getDeptID(),null);



                //???这段代码需要进行更改

                /*List<Integer> subOrgList = this.eosorgTOrganizationService.findSubOrgById(msgMessageDept.getDeptID());
                for(Integer orgId:subOrgList){
                    EosorgTOrganization org = this.eosorgTOrganizationService.findById(orgId);
                    MsgMessageUser msgMessageUser = new MsgMessageUser();
                    if((org!=null)&&(org.getIsVirOrg()==null||org.getIsVirOrg()==0)){
                        List<EosorgTEmployee> employeeList = this.eosorgTEmployeeService.findByOrgId(orgId);
                        EosOperator eosoperator = null;
                        for(EosorgTEmployee employee:employeeList){
                            eosoperator = employee.getEosoperator();
                            if(eosoperator!=null&&eosoperator.getStatus()==1){
                                Integer operatorId=eosoperator.getOperatorId();
                                if(!organizers.contains(operatorId)){
                                    organizers.add(operatorId);

                                    msgMessageUser.setMsgID(msgMessageInfoForm.getId());
                                    msgMessageUser.setUserID(employee.getOperatorID());
                                    msgMessageUser.setRole(3);
                                    msgMessageUser.setAttendable(-1);
                                    msgMessageUser.setTrainID(msgMessageInfoForm.getTrainId());

                                    Map<String,Object> parmMap = new HashMap<>();
                                    parmMap.put("msgID",msgMessageInfoForm.getId());
                                    parmMap.put("userID",employee.getOperatorID());
                                    parmMap.put("role",3);
                                    parmMap.put("attendable",-1);
                                    parmMap.put("trainID",msgMessageInfoForm.getTrainId());

                                    if(this.msgMessageUserService.findByExample(parmMap)!=null){
                                        this.msgMessageUserService.insertSelective(msgMessageUser);
                                    }
                                }
                            }
                        }
                    }else{
                        List<UserVorganization> userVorganizationList = this.userVorganizationDAO.findByProperty("id.orgId", orgId);
                        for(UserVorganization userVorganization:userVorganizationList){
                            Integer operatorId=userVorganization.getId().getOperatorId();
                            if(userVorganization.getStatus()==1&&!organizers.contains(operatorId)){
                                organizers.add(operatorId);
                                msgMessageUser.setMsgID(msgMessageInfoForm.getId());
                                msgMessageUser.setUserID(userVorganization.getId().getOperatorId());
                                msgMessageUser.setRole(3);
                                msgMessageUser.setAttendable(-1);
                                msgMessageUser.setTrainId(msgMessageInfoForm.getTrainId());
                                if(this.msgMessageArrangeUserService.findByExample(msgMessageUser)!=null)
                                    this.msgMessageArrangeUserService.save(msgMessageUser);
                            }
                        }
                    }
                }*/
            }
        }

    }

    @Transactional(rollbackFor = {Exception.class })
    public MsgMessageInfoForm getMsgMessageInfoForm(MsgMessageInfoWithBLOBs msgMessageInfo) {
        MsgMessageInfoForm msgMessageInfoForm = this.getMsgBaseInfoForm(msgMessageInfo);
        //获取邮件接收对象
        List<Integer> studentIds = new ArrayList<Integer>();
        Map<String,Object> parmMap = new HashMap<>();
        parmMap.put("msgID",msgMessageInfoForm.getId());
        parmMap.put("role",0);


        List<MsgMessageUser> studentList = this.msgMessageUserService.findByExample(parmMap);
        for(MsgMessageUser student:studentList){
            Integer operatorId = student.getUserID();
            studentIds.add(operatorId);
        }
        msgMessageInfoForm.setStudentIdList(studentIds);

        //获取邮件联络员对象
        parmMap.put("role",1);
        List<Integer> secretaryIds = new ArrayList<Integer>();
        List<MsgMessageUser> secretaryList = this.msgMessageUserService.findByExample(parmMap);
        for(MsgMessageUser secretary:secretaryList){
            Integer operatorId = secretary.getUserID();
            secretaryIds.add(operatorId);
        }
        msgMessageInfoForm.setSecretaryIdList(secretaryIds);

        //获取邮件抄送领导对象
        parmMap.put("role",2);
        List<Integer> directorIds = new ArrayList<Integer>();
        List<MsgMessageUser> directorList = this.msgMessageUserService.findByExample(parmMap);
        for(MsgMessageUser director:directorList){
            Integer operatorId = director.getUserID();
            directorIds.add(operatorId);
        }
        msgMessageInfoForm.setDirectorIdList(directorIds);

        //MsgMessageDept msgMessageDept = new MsgMessageDept();
        //msgMessageDept.setMsgID(msgMessageInfoForm.getId());
        Map<String,Object> msgDeptMap = new HashMap<>();
        msgDeptMap.put("msgID",msgMessageInfoForm.getId());

        List<MsgMessageDept> msgDeptList = this.msgMessageDeptService.findByExm(msgDeptMap);
        List<Integer> deptOrgIdList=new ArrayList<>();
        for(MsgMessageDept dept:msgDeptList){
            Integer deptId = dept.getDeptID();
            deptOrgIdList.add(deptId);
        }
        msgMessageInfoForm.setDeptList(deptOrgIdList);
        return msgMessageInfoForm;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public ServiceResponse deleteAjax(HttpServletRequest request){

        if(request.getParameterValues("id") != null) {
            Integer id = Integer.valueOf(request.getParameter("id").toString());
            this.msgMessageInfoMapper.deleteByPrimaryKey(id);
            return ServiceResponse.createBySuccess();
        } else {
            return ServiceResponse.createByError();
        }
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public void update(MsgMessageInfoForm msgMessageInfoForm,HttpServletRequest request){

        Integer id = msgMessageInfoForm.getId();

        //基本信息更新
        msgMessageInfoForm.setTenantId(Constants.tenantId);

        MsgMessageInfoWithBLOBs msgMessageInfo = this.msgMessageInfoMapper.selectByPrimaryKey(msgMessageInfoForm.getId());
        this.msgMessageInfoMapper.updateByPrimaryKeySelective(this.formTomsgMessageInfo(msgMessageInfo, msgMessageInfoForm));

        /*//附件更新
        if(msgMessageInfoForm.getAttachmentFile()!=null){
            this.saveAttachFile(msgMessageInfoForm, request);
        }

        Map<String,Object> condition=new HashMap<String,Object>();
        condition.put("msgId", msgMessageInfoForm.getId());
        this.getMsgMessageArrangeUserService().clearMessageUser(condition);

        this.getMsgMessageArrangeDeptService().clearMessageDept(condition);
        //重新加入通知单位及人员
        String[] orgIdList=request.getParameterValues("orgSelect");
        List<Integer> deptList=new ArrayList<Integer>();
        if(orgIdList!=null){
            for(String orgId:orgIdList){
                deptList.add(Integer.parseInt(orgId));
            }
            msgMessageInfoForm.setDeptList(deptList);
        }
        String[] studentIdListStr=request.getParameterValues("studentList");
        List<Integer> studentIdList=new ArrayList<Integer>();
        if(studentIdListStr!=null){
            for(String operatorIdStr:studentIdListStr){
                studentIdList.add(Integer.parseInt(operatorIdStr));
            }
            msgMessageInfoForm.setStudentIdList(studentIdList);
        }
        String[] secretaryIdListStr=request.getParameterValues("secretaryList");
        List<Integer> secretaryIdList=new ArrayList<Integer>();
        if(secretaryIdListStr!=null){
            for(String operatorIdStr:secretaryIdListStr){
                secretaryIdList.add(Integer.parseInt(operatorIdStr));
            }
            msgMessageInfoForm.setSecretaryIdList(secretaryIdList);
        }
        String[] directorIdListStr=request.getParameterValues("directorList");
        List<Integer> directorIdList=new ArrayList<Integer>();
        if(directorIdListStr!=null){
            for(String operatorIdStr:directorIdListStr){
                directorIdList.add(Integer.parseInt(operatorIdStr));
            }
            msgMessageInfoForm.setDirectorIdList(directorIdList);
        }

        Train train = this.onlineTrainingService.findById(msgMessageInfoForm.getTrainId());
        request.setAttribute("train", train);
        this.msgMessageInfoService.saveMsgArrangeUsers(msgMessageInfoForm);
        request.setAttribute("id", id);*/

    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public void sendMessageByType(HttpServletRequest request){

        Integer msgId = Integer.valueOf(request.getParameter("msgId").toString());
        MsgMessageInfo msgMessageInfo = this.msgMessageInfoMapper.selectByPrimaryKey(msgId);
        if (msgMessageInfo.getIsSMS()!=null && msgMessageInfo.getIsSMS()==1) {
            //如果通知形式为短信提醒，则通知以短信和邮件双重形式发送
            this.sendEmail(msgId,request);
            this.sendMobileMessage(msgId);
        } else {
            this.sendEmail(msgId,request);
        }
        request.setAttribute("id", msgId);
    }

    public void sendEmail(Integer msgId,HttpServletRequest request) {

        EosOperator senderOperator = (EosOperator)request.getSession(true).getAttribute(Constants.USERINFO_KEY);
        EosorgTEmployee senderEmployee= this.eosorgTEmployeeService.findById(senderOperator.getOperatorId());
        MsgMessageInfoWithBLOBs msgInfo = this.msgMessageInfoMapper.selectByPrimaryKey(msgId);

        Tenant tenant = this.tenantService.findById(Constants.tenantId);

        //缓存邮件通知对象中各通知成员所属部门编号及所属研究所名称对应关系
        Map<Integer,String> tenantNameMap = new HashMap<>();

        //缓存邮件通知对象中各通知成员所属机构编号及所属部门名称对应关系
        Map<Integer,String> orgNameMap=new HashMap<>();

        Integer trainId = msgInfo.getTrainID();
        TrainWithBLOBs train = this.onlineTrainingService.findById(msgInfo.getTrainID());
        if(train == null){
            return;
        }

        //获取通知对象
        List<MsgMessageUser> userList = this.msgMessageUserService.findByMsgId(msgId);

        //获取通知机构
        List<MsgMessageDept> deptList = this.msgMessageDeptService.findByMsgId(msgId);

        MailMessage mailMessage = new MailMessage();
        mailMessage.setMsgId(msgId);
        StringBuffer toUsers = new StringBuffer();//收件人栏信息
        //抄送人员：包括部门领导及部门联络员
        StringBuffer ccUsers = new StringBuffer();
        StringBuffer secretarys = new StringBuffer();//部门联络员
        StringBuffer leaders = new StringBuffer();//部门领导

        for(MsgMessageUser msgMessageUser : userList){
            if(msgMessageUser.getRole() == 0){//普通学员
                int operatorId = msgMessageUser.getUserID();
                EosOperator eosoperator = this.eosOperatorService.findById(operatorId);
                if(eosoperator==null || eosoperator.getStatus()==null || eosoperator.getStatus()!=1){
                    continue;
                }
                String operatorName = eosoperator.getOperatorName();
                toUsers.append(operatorName+";");
            }else if(msgMessageUser.getRole()==1){//部门联络人
                int operatorId = msgMessageUser.getUserID();
                EosOperator eosoperator = this.eosOperatorService.findById(operatorId);
                if(eosoperator==null || eosoperator.getStatus()==null || eosoperator.getStatus()!=1){
                    continue;
                }
                String operatorName = eosoperator.getOperatorName();
                secretarys.append(operatorName+";");
            }else if(msgMessageUser.getRole()==2){//领导
                int operatorId = msgMessageUser.getUserID();
                EosOperator eosoperator = this.eosOperatorService.findById(operatorId);
                if(eosoperator==null||eosoperator.getStatus()==null||eosoperator.getStatus()!=1){
                    continue;
                }
                String operatorName = eosoperator.getOperatorName();
                leaders.append(operatorName+";");
            }
        }

        //收件人栏添加部门
        for(MsgMessageDept msgMessageDept : deptList){
            if(msgMessageDept.getDeptID() != null){
                EosorgTOrganization org = this.eosorgTOrganizationService.findById(msgMessageDept.getDeptID());
                if(org!=null&&org.getOrgName()!=null){
                    toUsers.append(org.getOrgName()+";");
                }
            }
        }

        ccUsers.append(leaders).append(secretarys);//保证抄送人中领导在前

        //向学员发送邮件
        for(MsgMessageUser msgMessageUser : userList){
            if(msgMessageUser.getUserID() != null){
                Integer userId = msgMessageUser.getUserID();
                EosorgTEmployee employee = this.eosorgTEmployeeService.findById(userId);

                if(employee!=null && employee.getOEmail()!=null && employee.getOEmail().contains("@")){
                    EosOperator operator = this.eosOperatorService.findById(userId);

                    if(operator==null||operator.getStatus()==null||operator.getStatus()!=1){
                        //不向无效学员发送邮件通知
                        continue;
                    }

                    mailMessage.setToEmail(new String[]{eosorgTEmployeeService.findById(userId).getOEmail()});

                    mailMessage.setReceiverIDs(new Integer[]{userId});
                    StringBuffer content = new StringBuffer();
                    //发送邮件给普通学员
                    if(msgMessageUser.getRole()==0 || msgMessageUser.getRole()==3 || msgMessageUser.getRole()==null){
                        //int operatorId = msgMessageUser.getUserID();
                        //EosOperator eosoperator = this.eosOperatorService.findById(operatorId);
                        content.append("<span style='font-size:14px;font-weight:bold'>收件人: </span><span style='font-size:14px'>" + operator.getOperatorName() + "</span><br/>");
                        if(ccUsers.toString().length()>0){
                            content.append("<span style='font-size:14px;font-weight:bold'>抄送: </span><span style='font-size:14px'>" + ccUsers.toString() + "</span><br/>");
                        }
                    }
                    //发送邮件给领导或联络员
                    else if(msgMessageUser.getRole()==1 || msgMessageUser.getRole()==2){
                        if(toUsers.toString().length()>0){
                            content.append("<span style='font-size:14px;font-weight:bold'>收件人: </span><span style='font-size:14px'>"+toUsers.toString()+"</span><br/>");
                        }
                        if(ccUsers.toString().length()>0){
                            content.append("<span style='font-size:14px;font-weight:bold'>抄送: </span><span style='font-size:14px'>"+ccUsers.toString()+"</span><br/>");
                        }
                    }
                    //如下情况添加：我要参加、我不参加按钮，通知对象为学员、且邮件通知类别为需报名
                    if((msgMessageUser.getRole()==0 || msgMessageUser.getRole()==3) && msgInfo.getIsPublic()!=1157){
                        content.append("<br/><br/>");
                        //以下写法兼容outlook和foxmail等客户端，图片在outlook中无法显示

                        content.append("<a  href='").append(tenantService.findById(Constants.tenantId)==null?"":tenantService.findById(Constants.tenantId).getEnterUrl())
                                .append("/msgMessageArrangeUserAction.do?method=msgReceiverBack")
                                .append("&attendable=1").append("&msgId=").append(msgId).append("&trainId=").append(trainId).append("&userId=").append(userId).append("&id=").append(msgMessageUser.getID());
                        content.append("'><span style='cursor:pointer;width:200px;height:58px;line-height:58px;text-decoration: NONE;background-color:#0073C6;text-align:center;font-family:微软雅黑;font-size:28px;color:#F1F3F5;display:inline-block;'>&nbsp;&nbsp;我要参加&nbsp;&nbsp;</span></a>");
                        content.append("&nbsp;&nbsp;");
                        content.append("<a  href='").append(tenantService.findById(Constants.tenantId)==null?"":tenantService.findById(Constants.tenantId).getEnterUrl())
                                .append("/msgMessageArrangeUserAction.do?method=msgReceiverBack")
                                .append("&attendable=0").append("&msgId=").append(msgId).append("&trainId=").append(trainId).append("&userId=").append(userId).append("&id=").append(msgMessageUser.getID());
                        content.append("'><span style='cursor:pointer;width:200px;height:58px;line-height:58px;text-decoration: NONE;background-color:#FF6B1A;text-align:center;font-family:微软雅黑;font-size:28px;color:#F1F3F5;display:inline-block;'>&nbsp;&nbsp;我不参加&nbsp;&nbsp;</span></a>");
                        content.append("<br/><br/>");
                    }
                    content.append(msgInfo.getContent());
                    if((msgMessageUser.getRole()==0||msgMessageUser.getRole()==3)&&msgInfo.getIsPublic()!=1157){
                        content.append("<br/><br/>");
                        mailMessage.setSendType(0);
                    }else if(msgMessageUser.getRole()==1||msgMessageUser.getRole()==2){
                        mailMessage.setSendType(0);//由于设置为抄送形式，邮件上收件人为空，所以还是设置为直接发送方式，邮件正文有抄送人
                    }
                    mailMessage.setContent(content.toString());

                    mailMessage.setTitle(msgInfo.getTitle());
                    String tempPath = msgInfo.getAttachmentPath();
                    if(tempPath!=null){
                        String lastpath = tempPath.replace(Constants.elearningProperties.getProperty("uploadFile.address"), Constants.elearningProperties.getProperty("uploadFile.fulladdress"));
                        mailMessage.setAttachment(lastpath);
                    }

                    //下面这个方法未走通
                    //=================未走通方法====================
                    this.sendMailByMom4j(mailMessage, senderEmployee, msgInfo.getTitle(), tenant.getEnterUrl());

                    if((null!=msgInfo.getBackflag() && msgInfo.getBackflag().intValue()==1) && (msgMessageUser.getRole()==0 || msgMessageUser.getRole()==3) && msgInfo.getIsPublic()!=1157){

                        MtMixTrainUserTrainInfo mtMixTrainUserTrainInfo = new MtMixTrainUserTrainInfo();
                        mtMixTrainUserTrainInfo.setAttendType(2502);
                        mtMixTrainUserTrainInfo.setUserID(userId);
                        mtMixTrainUserTrainInfo.setTrainID(trainId);

                        Map<String,Object> parmM = new HashMap<>();
                        parmM.put("attendType",2502);
                        parmM.put("userID",userId);
                        parmM.put("trainID",trainId);

                        if(this.mtMixTrainUserTrainInfoService.getListByMap(parmM)==null || this.mtMixTrainUserTrainInfoService.getListByMap(parmM).size()==0){
                            mtMixTrainUserTrainInfo.setUserName(operator.getOperatorName());
                            mtMixTrainUserTrainInfo.setAttendType(2502);
                            if(train.getIsNeedCheck()!=null && train.getIsNeedCheck()){
                                mtMixTrainUserTrainInfo.setStatus(1051);
                            } else{
                                mtMixTrainUserTrainInfo.setStatus(1052);
                            }
                            mtMixTrainUserTrainInfo.setAttendable(-1);

                            String parentOrgName = "";
                            String orgName = "";

                            EosorgTOrganization organization = null;
                            EosorgTOrganization parentOrg = null;
                            if(tenant != null && tenant.getIsVirOrg()!=null && tenant.getIsVirOrg().intValue()==1){
                                if(orgNameMap.containsKey(employee.getOrgID())){
                                    orgName=orgNameMap.get(employee.getOrgID());
                                    parentOrgName=tenantNameMap.get(employee.getOrgID());
                                } else{
                                    organization = this.eosorgTOrganizationService.findById(this.eosorgTOrganizationService.findOrgId(employee.getOrgID()));
                                    orgName=organization.getOrgName();
                                    orgNameMap.put(employee.getOrgID(), orgName);
                                    Tenant _tenant = this.eosorgTOrganizationService.findTenant(employee.getOrgID());
                                    parentOrgName=_tenant.getTenantName();
                                    tenantNameMap.put(employee.getOrgID(), _tenant.getTenantName());
                                }
                            }else{
                                organization = this.eosorgTOrganizationService.findById(employee.getOrgID());
                                orgName = organization.getOrgName();
                                parentOrg = this.eosorgTOrganizationService.findById(this.eosorgTOrganizationService.findOrgId(employee.getOrgID()));
                                if(parentOrg!=null && parentOrg.getOrgName()!=null){
                                    parentOrgName = parentOrg.getOrgName();
                                }
                            }

                            mtMixTrainUserTrainInfo.setOrgName(orgName);
                            mtMixTrainUserTrainInfo.setParentOrgName(parentOrgName);
                            this.mtMixTrainUserTrainInfoService.save(mtMixTrainUserTrainInfo);
                        }
                    }else{
                        //假如是必须参加的方式，则学员直接在我的培训中能够看到本人需要参加的培训，部门联络员和领导默认不参加培训
                        if((msgMessageUser.getRole()==0||msgMessageUser.getRole()==3) && msgInfo.getIsPublic()!=1157){
                            MtMixTrainUserTrainInfo mtMixTrainUserTrainInfo = new MtMixTrainUserTrainInfo();
                            mtMixTrainUserTrainInfo.setStatus(1052);
                            mtMixTrainUserTrainInfo.setAttendable(-1);//必须参加方式默认为未回执
                            mtMixTrainUserTrainInfo.setAttendType(2504);
                            mtMixTrainUserTrainInfo.setTrainID(msgInfo.getTrainID());
                            mtMixTrainUserTrainInfo.setUserID(msgMessageUser.getUserID());
                            mtMixTrainUserTrainInfo.setUserName(this.eosOperatorService.findById(msgMessageUser.getUserID()).getOperatorName());

                            Map<String,Object> parmM2 = new HashMap<>();
                            parmM2.put("status",1052);
                            parmM2.put("attendable",-1);
                            parmM2.put("attendType",2504);
                            parmM2.put("trainID",msgInfo.getTrainID());
                            parmM2.put("userID",msgMessageUser.getUserID());
                            parmM2.put("userName",this.eosOperatorService.findById(msgMessageUser.getUserID()).getOperatorName());

                            String parentOrgName = "";
                            String orgName = "";
                            EosorgTOrganization organization = null;
                            EosorgTOrganization parentOrg = null;
                            if(tenant.getIsVirOrg()!=null && tenant.getIsVirOrg().intValue()==1){
                                if(orgNameMap.containsKey(employee.getOrgID())){
                                    orgName=orgNameMap.get(employee.getOrgID());
                                    parentOrgName=tenantNameMap.get(employee.getOrgID());
                                } else{
                                    organization = this.eosorgTOrganizationService.findById(this.eosorgTOrganizationService.findOrgId(employee.getOrgID()));
                                    orgName=organization.getOrgName();
                                    orgNameMap.put(employee.getOrgID(), orgName);
                                    Integer tenantId = this.eosorgTOrganizationService.findTenantId(employee.getOrgID());
                                    Tenant _tenant = this.tenantService.findById(tenantId);
                                    parentOrgName=_tenant.getTenantName();
                                    tenantNameMap.put(employee.getOrgID(), _tenant.getTenantName());
                                }
                            }else{
                                organization = this.eosorgTOrganizationService.findById(employee.getOrgID());
                                orgName = organization.getOrgName();
                                parentOrg = this.eosorgTOrganizationService.findById(this.eosorgTOrganizationService.findOrgId(employee.getOrgID()));
                                if(parentOrg!=null && parentOrg.getOrgName()!=null){
                                    parentOrgName = parentOrg.getOrgName();
                                }
                            }
                            mtMixTrainUserTrainInfo.setOrgName(orgName);
                            mtMixTrainUserTrainInfo.setParentOrgName(parentOrgName);
                            parmM2.put("orgName",orgName);
                            parmM2.put("parentOrgName",parentOrgName);

                            if(this.mtMixTrainUserTrainInfoService.getListByMap(parmM2).size()==0){
                                this.mtMixTrainUserTrainInfoService.save(mtMixTrainUserTrainInfo);
                            }
                            String trainName = train.getTrainName();
                            Integer trainWay = train.getTrainWay();
                            String location = "";
                            if(train.getLocation()!=null)
                                location = train.getLocation();
                            Integer attendantCount = train.getAttendantCount();
                            Date startTime = train.getStartTime();
                            Date endTime = train.getEndTime();
                            Byte isStationTrain = Byte.valueOf(train.getIsStationTrain()==true?"1":"0");
                            String sponsorName = train.getSponsorName();
                            UserTrainKey userTrainKey = new UserTrainKey(msgMessageUser.getUserID(),msgInfo.getTrainID(),train.getYear());
                            if(this.userTrainService.selectByPrimaryKey(userTrainKey)==null){
                                UserTrain usertrain = new UserTrain(msgMessageUser.getUserID(),msgInfo.getTrainID(),train.getYear(),trainName,trainWay,
                                        location,attendantCount,startTime,endTime,isStationTrain,sponsorName);
                                this.userTrainService.save(usertrain);
                            }
                        }
                    }
                }
            }
        }
        msgInfo.setStatus(1092);
        this.msgMessageInfoMapper.updateByPrimaryKeySelective(msgInfo);
    }

    public void sendMailByMom4j(MailMessage mailMessage, EosorgTEmployee employee,String title,String enterUrl){
        if(employee.getEmailSetEnable()!=null && employee.getEmailSetEnable()==1){//表示启用个人邮箱发送邮件
            mailMessage.setHost(employee.getEmailHost());
            mailMessage.setAdminUserName(employee.getOEmail());
            mailMessage.setAdminPassword(employee.getEmailPwd());
        }
        MailSender sender = this.sender;
        String mailContent = mailMessage.getContent();
        mailContent = Tools.formatHtmlLink(mailContent,enterUrl);
        mailMessage.setContent(mailContent);
        mailMessage.setMailstamp(title);
        sender.send(mailMessage);
    }

    /*
     * 培训班通知管理发送手机短信
     */
    public void sendMobileMessage(Integer id) {
        List<String> phoneList = new ArrayList();
        MsgMessageInfo msgInfo = this.msgMessageInfoMapper.selectByPrimaryKey(id);
        List<MsgMessageUser> userList = this.msgMessageUserService.findByMsgId(id);
        /*MTMessage mtMessage = new MTMessage();

        for (MsgMessageUser msgMessageUser : userList) {
            Integer operatorId = msgMessageUser.getUserID();
            EosorgTEmployee eosorgTEmployee = this.eosorgTEmployeeService.findById(operatorId);
            String mobileNO = eosorgTEmployee.getMobileNO();
            if (mobileNO != null) {
                phoneList.add(mobileNO);
            }
        }

        //获得连接
        int i = 0;
        String[] toE = new String[phoneList.size()];
        for (String mobileNO : phoneList) {
            toE[i] = mobileNO;
            i++;
        }

        String msgContent = msgInfo.getSmsContent();

        SmsConnection smsConnection = SmsConnection.getSmsConnection(Constants.elearningProperties.getProperty("sms.connectionUrl"));
        String userName = Constants.elearningProperties.getProperty("sms.userName");
        String password = Constants.elearningProperties.getProperty("sms.password");

        //群发短信
        for(int k=0;k<toE.length;k++){
            mtMessage.setContent(msgContent);
            mtMessage.setChannelId(1);
            //mtMessage.setServiceId(143);
            mtMessage.setServiceId(142);
            mtMessage.setDeliveryReport(0);
            mtMessage.setPhone(toE[k]);
            mtMessage.setPriority(1);
            try {
                MTResponse[] mtResponse = smsConnection.smsOperator
                        .sendSms(userName,
                                cn.kepu.smswebservice.client.MD5
                                        .MD5Encode(password),
                                mtMessage);

            } catch (Exception ex) {
                System.out.println("发送短信失败(" + toE[k] + ")" + ex.getMessage());
            }
        }

        msgInfo.setStatus(1092);
        this.msgMessageInfoDAO.update(msgInfo);*/

    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public void messagedetail(HttpServletRequest request){

        int trainId = Integer.parseInt(request.getSession().getAttribute("trainId").toString());
        Integer msgId = 0;
        if(request.getParameter("id")!=null){
            msgId = Integer.valueOf(request.getParameter("id").toString());
        } else{
            msgId = Integer.valueOf(request.getAttribute("id").toString());
        }
        MsgMessageInfoWithBLOBs msgMessageInfo = this.msgMessageInfoMapper.selectByPrimaryKey(msgId);
        MsgMessageInfoForm msgMessageInfoForm= this.getMsgMessageInfoForm(msgMessageInfo);
        request.setAttribute("msgInfo", msgMessageInfoForm);
        Integer sendable = 1; //标识页面是否出现发送通知邮件和网站发布的图标 1：能够出现
        if(msgMessageInfoForm.getTrainId()!=null&&msgMessageInfoForm.getTrainId().intValue()!=trainId){
            sendable=0;
        } else if(msgMessageInfoForm.getStatus().intValue()==1092){
            sendable=0;
        }
        request.setAttribute("sendable", sendable);

        //获取本次培训的通知列表
        List<MsgMessageInfoWithBLOBs> thisTrainMsgInfoList = this.msgMessageInfoMapper.findByTrainId(-1);
        List<MsgMessageInfoForm> thisTrainMsgInfoFormList = this.getMessageFormList(thisTrainMsgInfoList);
        request.setAttribute("thisTrainMsgInfoFormList", thisTrainMsgInfoFormList);

        Integer editorId = 0;
        String title = "";

        EosOperator operator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        editorId = operator.getOperatorId();
        Map<String,Object> map = new HashMap<>();
        map.put("editorId", editorId);

        List<MsgMessageInfoWithBLOBs> msgMessageInfoList = this.msgMessageInfoMapper.getListByEditorId(map);
        List<MsgMessageInfoForm> msgMessageInfoFormList = this.getMessageFormList(msgMessageInfoList);
        request.setAttribute("msgMessageInfoFormList", msgMessageInfoFormList);

    }


}
