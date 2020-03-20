package com.elearning.service.integralTask;

import com.elearning.dao.integralTask.IntegralOfflineTrainRecordMapper;
import com.elearning.dao.integralTask.IntegralRuleMapper;
import com.elearning.pojo.integralTask.IntegralOfflineTrainRecord;
import com.elearning.pojo.integralTask.IntegralRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("integralOfflineTrainRecordService")
public class IntegralOfflineTrainRecordService implements IIntegralOfflineTrainRecordService{

    @Autowired
    private IntegralOfflineTrainRecordMapper integralOfflineTrainRecordMapper;

    @Override
    public IntegralOfflineTrainRecord selectByPrimaryKey(Long id){
        return this.integralOfflineTrainRecordMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<IntegralOfflineTrainRecord> getListByUserIdAndTrainIdAndStatus(Map<String,Object> map){
        return this.integralOfflineTrainRecordMapper.getListByUserIdAndTrainIdAndStatus(map);
    }

    @Override
    public int saveAndGetIntegralOfflineTrainRecord(IntegralOfflineTrainRecord record){
        return this.integralOfflineTrainRecordMapper.insertSelective(record);
    }

    @Override
    public int updateIntegralOfflineTrainRecord(IntegralOfflineTrainRecord record){
        return this.integralOfflineTrainRecordMapper.updateByPrimaryKeySelective(record);
    }







}
