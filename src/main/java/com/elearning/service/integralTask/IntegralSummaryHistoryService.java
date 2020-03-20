package com.elearning.service.integralTask;

import com.elearning.dao.integralTask.IntegralSummaryHistoryMapper;
import com.elearning.pojo.integralTask.IntegralSummaryHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("integralSummaryHistoryService")
public class IntegralSummaryHistoryService implements IIntegralSummaryHistoryService{

    @Autowired
    private IntegralSummaryHistoryMapper integralSummaryHistoryMapper;

    @Override
    public IntegralSummaryHistory selectByPrimaryKey(Long id){
        return this.integralSummaryHistoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<IntegralSummaryHistory> getListByTrainIdAndUserIdAndStatus(Map<String,Object> map){
        return this.integralSummaryHistoryMapper.getListByTrainIdAndUserIdAndStatus(map);
    }

    @Override
    public int saveIntegralSummaryHistory(IntegralSummaryHistory record){
        return this.integralSummaryHistoryMapper.insert(record);
    }







}
