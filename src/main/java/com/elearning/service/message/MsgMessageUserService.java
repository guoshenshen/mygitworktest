package com.elearning.service.message;

import com.elearning.dao.message.MsgMessageInfoMapper;
import com.elearning.dao.message.MsgMessageUserMapper;
import com.elearning.pojo.message.MsgMessageInfoWithBLOBs;
import com.elearning.pojo.message.MsgMessageUser;
import com.elearning.pojo.pub.DDictionary;
import com.elearning.pojo.pub.EosorgTOrganization;
import com.elearning.pojo.pub.TrainWithBLOBs;
import com.elearning.service.onlinetraining.IOnlineTrainingService;
import com.elearning.service.pub.IDDictionaryService;
import com.elearning.service.pub.IEosorgTEmployeeService;
import com.elearning.service.pub.IEosorgTOrganizationService;
import com.elearning.util.DateTimeUtil;
import com.elearning.vo.BasicUserForm;
import com.elearning.vo.message.MsgMessageInfoForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service("msgMessageUserService")
public class MsgMessageUserService implements IMsgMessageUserService{

    @Autowired
    private MsgMessageUserMapper msgMessageUserMapper;

    @Override
    public MsgMessageUser selectByPrimaryKey(Integer ID){
        return this.msgMessageUserMapper.selectByPrimaryKey(ID);
    }

    @Override
    public List<MsgMessageUser> queryMessageUserListYesOperatorName(Map<String,Object> map){
        return this.msgMessageUserMapper.queryMessageUserListYesOperatorName(map);
    }

    @Override
    public List<MsgMessageUser> queryMessageUserListNoOperatorName(Map<String,Object> map){
        return this.msgMessageUserMapper.queryMessageUserListNoOperatorName(map);
    }

    @Override
    public int insertSelective(MsgMessageUser record){
        return this.msgMessageUserMapper.insertSelective(record);
    }

    @Override
    public List<MsgMessageUser> findByExample(Map<String,Object> parmMap){
        return this.msgMessageUserMapper.findByExample(parmMap);
    }

    @Override
    public List<MsgMessageUser> findByMsgId(Integer msgId){
        return this.msgMessageUserMapper.findByMsgId(msgId);
    }



}
