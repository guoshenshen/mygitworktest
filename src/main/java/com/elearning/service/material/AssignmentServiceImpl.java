package com.elearning.service.material;

import com.elearning.common.*;
import com.elearning.dao.material.AssignmentMapper;
import com.elearning.pojo.material.AssignTopic;
import com.elearning.pojo.material.AssignTopicUser;
import com.elearning.pojo.material.AssignUser;
import com.elearning.pojo.material.Assignment;
import com.elearning.pojo.pub.EosOperator;
import com.elearning.pojo.pub.EosorgTEmployee;
import com.elearning.pojo.pub.TrainWithBLOBs;
import com.elearning.pojo.systemManage.Tenant;
import com.elearning.service.onlinetraining.IOnlineTrainingService;
import com.elearning.service.pub.IEosorgTEmployeeService;
import com.elearning.util.DateTimeUtil;
import com.elearning.util.PropertiesUtil;
import com.elearning.vo.BasicUserForm;
import com.elearning.vo.material.AssignTopicUserForm;
import com.elearning.vo.material.AssignUserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service("assignmentService")
public class AssignmentServiceImpl implements IAssignmentService {

    @Autowired
    private AssignmentMapper assignmentMapper;

    @Autowired
    private IAssignUserService assignUserService;

    @Autowired
    private IAssignTopicService assignTopicService;

    @Autowired
    private IEosorgTEmployeeService eosorgTEmployeeService;

    @Autowired
    private IOnlineTrainingService onlineTrainingService;

    @Autowired
    private IAssignTopicUserService assignTopicUserService;

    private MailSender mailSender;

    public List<Assignment> getAssignmentByIdReturnList(Long id){

        return assignmentMapper.getAssignmentByIdReturnList(id);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public List<Assignment> findByResourceId(Integer type,Long resourceId){

        Map<String,Object> condition=new HashMap<String,Object>();
        condition.put("type", type);
        condition.put("resourceId", resourceId);

        List<Assignment> assignmentList = this.assignmentMapper.getListByTypeAndResourceId(condition);

        return assignmentList;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public ServiceResponse updateAssignInfo(HttpServletRequest request){

        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);

        Long resourceId = Long.parseLong(request.getParameter("resourceId"));
        int type = Integer.parseInt(request.getParameter("type"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String idStr = request.getParameter("id");
        String comment = request.getParameter("comment");

        Assignment example = new Assignment();
        example.setCreatorName(eosoperator.getOperatorName());
        example.setCreateDate(new Date());
        example.setResourceId(resourceId);
        example.setType(type==1?true:false);
        example.setName(name);
        example.setDescription(description);
        example.setComment(comment);

        if(idStr==null || idStr.trim().length()==0){
            SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
            Long id = idWorker.nextId();
            example.setId(id);
            this.assignmentMapper.insert(example);
        } else{
            example.setId(Long.parseLong(idStr));
            this.assignmentMapper.updateByPrimaryKeySelective(example);
        }

        example.setCreateDateStr(DateTimeUtil.dateToStr(example.getCreateDate()));

        return ServiceResponse.createBySuccess(example);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public ServiceResponse deleteAssignment(HttpServletRequest request){
        String msg = "删除成功！";
        String assignIdStr = request.getParameter("assignId");
        Long assignId = Long.parseLong(assignIdStr);

        List<AssignUser> assignUserList = this.assignUserService.getListByAssignId(assignId);

        if(assignUserList!=null && assignUserList.size()>0){
            msg = "有学员已经提交了作业，请删除学员提交的作业后再执行此操作！";
            return ServiceResponse.createByErrorMessage(msg);
        } else{
            List<AssignTopic> assignTopicList = this.assignTopicService.getListByAssignId(assignId);
            for(AssignTopic topic : assignTopicList){
                this.assignTopicService.deleteByPrimaryKey(topic.getId());
            }
            this.assignmentMapper.deleteByPrimaryKey(assignId);
        }
        return ServiceResponse.createBySuccessMessage(msg);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public ServiceResponse showTopicsOfAssign(HttpServletRequest request){

        Map<String,Object> returnMap = new HashMap<>();
        try {
            String assignIdStr = request.getParameter("assignId");
            Long assignId = Long.parseLong(assignIdStr);
            List<AssignTopic> assignTopicList = this.assignTopicService.getListByAssignId(assignId);
            Boolean canChange = !this.hasReceivedReply(assignId);
            returnMap.put("topicList",assignTopicList);
            returnMap.put("canChange",canChange);

            return ServiceResponse.createBySuccess(returnMap);
        } catch (Exception e) {
            e.printStackTrace();
            return ServiceResponse.createByErrorMessage("false");
        }
    }

    /**
     * 判断某作业是否已经有用户提交答题情况
     * @param assignId
     * @return
     */
    private Boolean hasReceivedReply(Long assignId){
        List<AssignUser> assignUserList=this.assignUserService.getListByAssignId(assignId);
        if(assignUserList==null || assignUserList.size()==0){
            return false;
        } else{
            return true;
        }
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public ServiceResponse updateTopic(HttpServletRequest request){
        String msg = "";

        String assignIdStr = request.getParameter("assignId");
        Long assignId = Long.parseLong(assignIdStr);
        if(this.hasReceivedReply(assignId)){
            msg = "有学员已经提交了作业，请删除学员提交的作业后再执行此操作！";
            return ServiceResponse.createByErrorMessage(msg);
        } else{
            String[] idList = request.getParameterValues("id");
            String[] requiredList = request.getParameterValues("required");
            String[] formList = request.getParameterValues("form");
            String[] contentList = request.getParameterValues("content");
            String[] deleteIdList = request.getParameterValues("deleteId");

            Integer index = 0;
            List<Integer> failedIndex = new ArrayList<>();
            for(String id : idList){
                AssignTopic topic = null;
                if(id==null || id.trim().length()==0){
                    topic = new AssignTopic();
                } else{
                    topic = this.assignTopicService.selectByPrimaryKey(Long.parseLong(id));
                }
                topic.setIndex1(index+1);
                topic.setAssignId(assignId);
                if(requiredList[index].trim().equals("0")){
                    topic.setRequired(false);
                } else{
                    topic.setRequired(true);
                }
                topic.setForm(Integer.parseInt(formList[index])==1?true:false);
                topic.setContent(contentList[index]);

                try {
                    if(id==null || id.trim().length()==0){
                        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
                        Long id2 = idWorker.nextId();
                        topic.setId(id2);
                        this.assignTopicService.insertSelective(topic);
                    } else{
                        this.assignTopicService.updateByPrimaryKeyWithBLOBs(topic);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    failedIndex.add(index);
                    continue;
                } finally{
                    index++;
                }
            }
            return ServiceResponse.createBySuccess(failedIndex);
        }
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public ServiceResponse deleteTopicByBatch(HttpServletRequest request){

        String msg = "";
        String assignIdStr=request.getParameter("assignId");
        String[] topicIdArray=request.getParameterValues("id");
        List<String> deleteFailTopicId = new ArrayList<>();
        Long assignId=Long.parseLong(assignIdStr);
        if(this.hasReceivedReply(assignId)){
            msg = "有学员已经提交了作业，请删除学员提交的作业后再执行此操作！";
            return ServiceResponse.createByErrorMessage(msg);
        } else{
            for(String topicId : topicIdArray){
                AssignTopic assignTopic = this.assignTopicService.selectByPrimaryKey(Long.parseLong(topicId));
                try {
                    this.assignTopicService.deleteByPrimaryKey(assignTopic.getId());
                } catch (Exception e) {
                    e.printStackTrace();
                    deleteFailTopicId.add(topicId+"");
                    continue;
                }
            }

            return ServiceResponse.createBySuccess(deleteFailTopicId);
        }
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public ServiceResponse loadAssignUsers(HttpServletRequest request){

        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);

        Long assignId = Long.parseLong(request.getParameter("assignId"));

        Map<String,Object> condition = new HashMap<>();
        condition.put("assignId", assignId);
        condition.put("valid",1);

        String orderPageString = " order by org.orgid asc";
        try {
            StringBuffer sqlQuery0 = new StringBuffer(" asu.assignId=:assignId and asu.status=2 and asu.valid=:valid ");
            StringBuffer sqlQuery1 = new StringBuffer(" asu.assignId=:assignId and asu.status=1 and asu.valid=:valid ");
            StringBuffer sqlQuery3 = new StringBuffer(" asu.assignId=:assignId and asu.status=-1 and asu.valid=:valid ");

            List<BasicUserForm> passList = this.assignUserService.findUserListByConditionSQL(sqlQuery0.toString(),eosoperator.getTenantId(), condition ,orderPageString);
            List<BasicUserForm> submitList=this.assignUserService.findUserListByConditionSQL(sqlQuery1.toString(), eosoperator.getTenantId(), condition, orderPageString);
            List<BasicUserForm> resetList=this.assignUserService.findUserListByConditionSQL(sqlQuery3.toString(), eosoperator.getTenantId(), condition, orderPageString);

            Map<String,Object> returnMap = new HashMap<>();
            returnMap.put("passList",passList);
            returnMap.put("submitList",submitList);
            returnMap.put("resetList",resetList);

            return ServiceResponse.createBySuccess(returnMap);
        } catch (Exception e) {
            e.printStackTrace();
            return ServiceResponse.createByError();
        }
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public ServiceResponse freezeUserDistribute(HttpServletRequest request){

        String[] selectedUserList = request.getParameterValues("operatorId");
        String assignIdStr = request.getParameter("assignId");
        Long assignId = Long.parseLong(assignIdStr);
        List<Integer> operatorIdList = new ArrayList<>();
        for(String operatorIdStr : selectedUserList){
            operatorIdList.add(Integer.parseInt(operatorIdStr));
        }
        this.assignUserService.freezeAssignUser(assignId, operatorIdList);

        return ServiceResponse.createBySuccess();
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public ServiceResponse getAssignInfo(HttpServletRequest request){

        Long assignId = Long.parseLong(request.getParameter("id"));
        Assignment assignment = this.assignmentMapper.findById(assignId);

        return ServiceResponse.createBySuccess(assignment);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public ServiceResponse distributeAssignByMail(HttpServletRequest request){

        String[] operatorIdStrList = request.getParameterValues("operatorId");
        String mailTitle = request.getParameter("mailTitle");
        String mailContent = request.getParameter("mailContent");
        String assignIdStr = request.getParameter("assignId");
        Long assignId = Long.parseLong(assignIdStr);

        Tenant currentTenant = CacheUtils.getTenant(Constants.tenantId);
        String rootUrl = currentTenant.getEnterUrl()+"/";
        List<Integer> operatorIdList = new ArrayList<>();
        for(String operatorIdStr : operatorIdStrList){
            operatorIdList.add(Integer.parseInt(operatorIdStr));
        }
        this.distributeAssign(operatorIdList, assignId);
        for(Integer operatorId:operatorIdList){
            try {
                EosorgTEmployee employee = this.eosorgTEmployeeService.findById(operatorId);
                if(employee.getOEmail()!=null && employee.getOEmail().trim().length()>0){
                    MailMessage mailMessage = new MailMessage();
                    mailMessage.setToEmail(new String[]{employee.getOEmail()});
                    mailMessage.setReceiverIDs(new Integer[]{employee.getOperatorID()});
                    mailMessage.setTitle(mailTitle);
                    mailMessage.setSendType(0);
                    StringBuffer contentForUser = new StringBuffer("<p style='margin-bottom:30px;'>").append(mailContent).append("</p>");
                    String actionUrl=rootUrl+"assignAction.do?method=intoAssignmentForUser&operatorId="+operatorId+"&assignId="+assignId;
                    contentForUser.append("<br/><a href='");
                    contentForUser.append(actionUrl);
                    contentForUser.append("'><span style='color: #333333; line-height: 1em; cursor: pointer;margin-right: 6px;float: none;display: inline-block; padding: 10px 16px 10px 16px;font-size: 16px;background-color: #f49d00;font-weight: bold;background-color: #3c8bea;border-radius: 5px;'>作业查看</span></a>");
                    mailMessage.setContent(contentForUser.toString());
                    mailMessage.setMailstamp(mailTitle);
                    this.mailSender.send(mailMessage);
                }
            } catch (Exception e) {
                e.printStackTrace();
                return ServiceResponse.createByError();
            }
        }
        return ServiceResponse.createBySuccess();
    }

    /**
     * 站内派送作业
     * @param operatorIdList
     * @param assignId
     */
    public void distributeAssign(List<Integer> operatorIdList,Long assignId){
        this.assignUserService.batchSave(assignId, operatorIdList);
        this.assignUserService.unfreezeAssignUser(assignId,operatorIdList);
    }


    @Override
    @Transactional(rollbackFor = {Exception.class })
    public void showAssignmentListForStudent(HttpServletRequest request){

        EosOperator operator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);

        Integer currentTrainId = Integer.valueOf(request.getParameter("trainId").toString());
        TrainWithBLOBs train = this.onlineTrainingService.findById(currentTrainId);

        List<AssignUserForm> assignmentList = null;

        if(operator != null && operator.getOperatorId() != null && currentTrainId != null){
            assignmentList = this.assignUserService.getListByTypeAndResourceIdAndOperatorId(ResourceType.TRAIN.getTypeCode(),Long.parseLong(currentTrainId+""),operator.getOperatorId());
            for(AssignUserForm assignForm:assignmentList){
                if(assignForm.getOperatorId()==null){
                    assignForm.setOperatorId(operator.getOperatorId());
                    assignForm.setOperatorName(operator.getOperatorName());
                    assignForm.setStatus((short)0);
                    //未向该学员指派该作业
                    List<Integer> toAddUser=new ArrayList<Integer>();
                    toAddUser.add(operator.getOperatorId());
                    this.assignUserService.batchSave(assignForm.getAssignId(), toAddUser);
                }
            }
            request.setAttribute("operatorId", operator.getOperatorId());
        }

        request.setAttribute("type", ResourceType.TRAIN.getTypeCode());
        request.setAttribute("train", train);
        request.setAttribute("assignmentItemList", assignmentList);

        Integer tenantId= Integer.parseInt(PropertiesUtil.getProperty("tenantId"));
        Tenant currentTenant = CacheUtils.getTenant(tenantId);
        String rootUrl = currentTenant.getEnterUrl()+"/";
        request.setAttribute("rootUrl",rootUrl);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public void intoAssignmentForUser(HttpServletRequest request){

        Integer operatorId = Integer.parseInt(request.getParameter("operatorId"));
        Long assignId = Long.parseLong(request.getParameter("assignId"));
        Assignment assignment = this.assignmentMapper.findById(assignId);
        if(assignment != null){
            request.setAttribute("assignment", assignment);
            AssignUser assignUser = this.assignUserService.findByAssignIdAndOperatorId(assignId, operatorId);
            List<AssignTopicUserForm> astuList = null;
            if(assignUser != null){
                //已经向该学员分配作业
                request.setAttribute("assignUser", assignUser);
                astuList = this.assignTopicUserService.findTopicFormByAssignIdAndOperatorId(assignId, operatorId);
                if(astuList==null || astuList.size()==0){
                    //学员尚未提交作业
                    List<AssignTopic> asList = this.assignTopicService.getListByAssignId(assignId);
                    if(asList!=null && asList.size()>0){
                        for(AssignTopic as : asList){
                            astuList.add(new AssignTopicUserForm(as));
                        }
                    }
                } else{
                    //学员已经提交作业
                }
            } else{
                //未向该学员分配作业
                astuList = this.assignTopicUserService.findTopicFormByAssignIdAndOperatorId(assignId, operatorId);
                //将该学员添加到作业指派对象中
                List<Integer> usersToAdd = new ArrayList<>();
                usersToAdd.add(operatorId);
                this.assignUserService.batchSave(assignId,usersToAdd);
                if(astuList==null || astuList.size()==0){
                    List<AssignTopic> asList = this.assignTopicService.getListByAssignId(assignId);
                    if(asList!=null&&asList.size()>0){
                        for(AssignTopic as:asList){
                            astuList.add(new AssignTopicUserForm(as));
                        }
                    }
                }
            }
            request.setAttribute("assignTopicFormList", astuList);
        }
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public ServiceResponse submitAssignment(HttpServletRequest request){

        String massge = "";
        String assignIdStr = request.getParameter("assignId");
        Long assignId = Long.parseLong(assignIdStr);
        String operatorIdStr = request.getParameter("operatorId");
        Integer operatorId = Integer.parseInt(operatorIdStr);
        AssignUser au = this.assignUserService.findByAssignIdAndOperatorId(assignId,operatorId);
        if(au == null){
            massge = "管理员并未向您派送此作业,提交无效";
            return ServiceResponse.createByErrorMessage(massge);
        } else{
            //题目编号
            String[] topicIdArray = request.getParameterValues("topicIds");
            //作业内容
            String[] answers = request.getParameterValues("answers");
            Integer index = 0;
            try {
                //如果学员已经提交过作业,则先删除该学员提交的作业然后再提交
                this.assignTopicUserService.deleteByAssignIdAndOperatorId(assignId, operatorId);
                for(String topicId : topicIdArray){
                    AssignTopicUser astu = new AssignTopicUser();
                    astu.setOperatorId(operatorId);
                    astu.setTopicId(Long.parseLong(topicId));
                    astu.setContent(answers[index]);

                    SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
                    Long id=idWorker.nextId();
                    astu.setId(id);

                    this.assignTopicUserService.save(astu);
                    index++;
                }
                au.setStatus(1);
                au.setSubmitTime(new Date());
                this.assignUserService.update(au);
                return ServiceResponse.createBySuccess();
            } catch (Exception e) {
                e.printStackTrace();
                return ServiceResponse.createByError();
            }
        }
    }

}
