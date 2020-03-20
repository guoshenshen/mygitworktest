package com.elearning.service.material;

import com.elearning.dao.material.AssignTopicUserMapper;
import com.elearning.pojo.material.AssignTopic;
import com.elearning.pojo.material.AssignTopicUser;
import com.elearning.vo.material.AssignTopicUserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("assignTopicUserService")
public class AssignTopicUserServiceImpl implements IAssignTopicUserService {

    @Autowired
    private AssignTopicUserMapper assignTopicUserMapper;

    @Autowired
    private IAssignTopicService assignTopicService;

    @Override
    public AssignTopicUser selectByPrimaryKey(Long id){
        return assignTopicUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public int save(AssignTopicUser record){
        return assignTopicUserMapper.insert(record);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public List<AssignTopicUserForm> findTopicFormByAssignIdAndOperatorId(Long assignId, Integer operatorId){

        Map<String,Object> condition = new HashMap<>();
        condition.put("assignId", assignId);
        condition.put("operatorId", operatorId);

        return this.assignTopicUserMapper.findTopicFormByAssignIdAndOperatorId(condition);

    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public void deleteByAssignIdAndOperatorId(Long assignId, Integer operatorId){

        //查询
        List<AssignTopic> topicList = this.assignTopicService.getListByAssignId(assignId);
        List<Long> topicIdList = new ArrayList<>();
        for(AssignTopic topic : topicList){
            topicIdList.add(topic.getId());
        }

        Map<String,Object> parmMap = new HashMap<>();
        parmMap.put("operatorId",operatorId);
        parmMap.put("topicIdList",topicIdList);

        //assignTopicUser
        List<AssignTopicUser> assignTopicUserList = this.assignTopicUserMapper.getListByOperatorIdAndTopicIdList(parmMap);

        //删除
        if(assignTopicUserList.size()>0){
            for (AssignTopicUser assignTopicUser: assignTopicUserList) {
                this.assignTopicUserMapper.deleteByPrimaryKey(assignTopicUser.getId());
            }
        }
    }


}
