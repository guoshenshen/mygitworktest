package com.elearning.service.material;

import com.elearning.dao.material.AssignTopicMapper;
import com.elearning.pojo.material.AssignTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("assignTopicService")
public class AssignTopicServiceImpl implements IAssignTopicService {

    @Autowired
    private AssignTopicMapper assignTopicMapper;

    @Override
    public AssignTopic selectByPrimaryKey(Long id){
        return assignTopicMapper.selectByPrimaryKey(id);
    }

    @Override
    public int insert(AssignTopic record){
        return assignTopicMapper.insert(record);
    }

    @Override
    public int insertSelective(AssignTopic record){
        return assignTopicMapper.insertSelective(record);
    }

    @Override
    public int updateByPrimaryKeyWithBLOBs(AssignTopic record){
        return assignTopicMapper.updateByPrimaryKeyWithBLOBs(record);
    }

    @Override
    public List<AssignTopic> getListByAssignId(Long assignId){
        return assignTopicMapper.getListByAssignId(assignId);
    }

    @Override
    public int deleteByPrimaryKey(Long id){
        return assignTopicMapper.deleteByPrimaryKey(id);
    }


}
