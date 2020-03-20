package com.elearning.service.integralTask;

import com.elearning.dao.integralTask.IntegralRecordMapper;
import com.elearning.pojo.integralTask.IntegralRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("integralRecordService")
public class IntegralRecordService implements IIntegralRecordService{

    @Autowired
    private IntegralRecordMapper integralRecordMapper;

    @Override
    public IntegralRecord selectByPrimaryKey(Long id){
        return this.integralRecordMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<IntegralRecord> findNewestRecord(){
        return this.integralRecordMapper.findNewestRecord();
    }

    @Override
    public int saveIntegralRecord(IntegralRecord record){
        return this.integralRecordMapper.insert(record);
    }

    @Override
    public List<IntegralRecord> getListByStudentIdAndHistoryNameAndHistoryId(Map<String,Object> parmMap){
        return this.integralRecordMapper.getListByStudentIdAndHistoryNameAndHistoryId(parmMap);
    }

    @Override
    public int updateIntegralRecord(IntegralRecord record){
        return this.integralRecordMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<IntegralRecord> getListByMap(Map<String,Object> parmMap){
        return this.integralRecordMapper.getListByMap(parmMap);
    }









}
