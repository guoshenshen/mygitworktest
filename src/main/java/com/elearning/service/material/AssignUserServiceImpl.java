package com.elearning.service.material;

import com.elearning.common.SnowflakeIdWorker;
import com.elearning.dao.material.AssignUserMapper;
import com.elearning.pojo.material.AssignUser;
import com.elearning.service.pub.IEosorgTEmployeeService;
import com.elearning.vo.BasicUserForm;
import com.elearning.vo.material.AssignUserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("assignUserService")
public class AssignUserServiceImpl implements IAssignUserService {

    @Autowired
    private AssignUserMapper assignUserMapper;

    @Autowired
    private IEosorgTEmployeeService eosorgTEmployeeService;

    private SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);

    public AssignUser selectByPrimaryKey(Long id){

        return assignUserMapper.selectByPrimaryKey(id);
    }

    public int update(AssignUser record){

        return assignUserMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public void freezeAssignUser(Long assignId,List<Integer> operatorList){
        Map<String,Object> condition=new HashMap<>();
        condition.put("assignId", assignId);
        condition.put("operatorIdList", operatorList);

        this.assignUserMapper.updateByAssignIdAndOperatorList(condition);

    }

    @Override
    public void unfreezeAssignUser(Long assignId,List<Integer> operatorList){
        Map<String,Object> condition=new HashMap<>();
        condition.put("assignId", assignId);
        condition.put("operatorIdList", operatorList);

        this.assignUserMapper.updateByAssignIdAndOperatorList2(condition);

    }

    @Override
    public List<AssignUser> getListByAssignId(Long assignId){
        return this.assignUserMapper.getListByAssignId(assignId);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public List<BasicUserForm> findUserListByConditionSQL(String sqlQuery, Integer tenantId, Map<String, Object> condition, String orderPageString) {
        StringBuffer queryStr = new StringBuffer(", assignuser asu where employee.operatorId=asu.operatorId and ");
        queryStr.append(sqlQuery);
        List<BasicUserForm>  userList = this.eosorgTEmployeeService.findBasicUserInfoByCondition(queryStr.toString(), tenantId, condition,orderPageString);
        return userList;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public void batchSave(Long assignId,List<Integer> selectedList) {
        List<AssignUser> userList = this.getListByAssignId(assignId);
        List<Integer> dealedList = new ArrayList<>();
        List<Integer> toAddList = new ArrayList<>();

        if(userList != null){
            for(AssignUser user : userList){
                dealedList.add(user.getOperatorid());
            }
        }
        for(Integer operatorId:selectedList){
            if(!dealedList.contains(operatorId)){
                toAddList.add(operatorId);
                dealedList.add(operatorId);

                AssignUser assignUser = new AssignUser();
                Long id = this.idWorker.nextId();
                assignUser.setId(id);
                assignUser.setAssignId(assignId);
                assignUser.setOperatorid(operatorId);
                assignUser.setStatus(0);
                assignUser.setValid(true);

                this.assignUserMapper.insert(assignUser);
            }
        }
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public List<AssignUserForm> getListByTypeAndResourceIdAndOperatorId(Integer type, Long resourceId, Integer assignUserId) {
        Map<String,Object> condition = new HashMap<>();
        condition.put("type", type);
        condition.put("resourceId", resourceId);
        condition.put("operatorId", assignUserId);
        List<AssignUserForm> assignmentList = this.assignUserMapper.getListByTypeAndResourceIdAndOperatorId(condition);

        return assignmentList;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public AssignUser findByAssignIdAndOperatorId(Long assignId, Integer operatorId){
        AssignUser result = null;
        Map<String,Object> condition = new HashMap<>();
        condition.put("assignId", assignId);
        condition.put("operatorId", operatorId);
        List<AssignUser> userList = this.assignUserMapper.getListByAssignIdAndOperatorId(condition);
        if(userList.size()>0){
            result = userList.get(0);
        }
        return result;
    }


}
