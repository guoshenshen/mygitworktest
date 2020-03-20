package com.elearning.service.message;

import com.elearning.dao.message.MsgMessageDeptMapper;
import com.elearning.pojo.message.MsgMessageDept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("msgMessageDeptService")
public class MsgMessageDeptService implements IMsgMessageDeptService{

    @Autowired
    private MsgMessageDeptMapper msgMessageDeptMapper;

    @Override
    public MsgMessageDept selectByPrimaryKey(Integer ID){

        return this.msgMessageDeptMapper.selectByPrimaryKey(ID);
    }

    @Override
    public int insertSelective(MsgMessageDept record){

        return this.msgMessageDeptMapper.insertSelective(record);
    }

    @Override
    public List<MsgMessageDept> findByExm(Map<String,Object> parmMap){

        return this.msgMessageDeptMapper.findByExample(parmMap);
    }

    @Override
    public List<MsgMessageDept> findByMsgId(Integer msgId){

        return this.msgMessageDeptMapper.findByMsgId(msgId);
    }



}
