package com.elearning.service.mixtraining;

import com.elearning.dao.mixtraining.MtMixTrainScheduleTrainMapper;
import com.elearning.pojo.mixtraining.MtMixTrainSchedule;
import com.elearning.pojo.mixtraining.MtMixTrainScheduleTrain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("mtMixTrainScheduleTrainService")
public class MtMixTrainScheduleTrainService implements IMtMixTrainScheduleTrainService{


    @Autowired
    private MtMixTrainScheduleTrainMapper mtMixTrainScheduleTrainMapper;

    @Override
    public MtMixTrainScheduleTrain selectByPrimaryKey(Integer id){

        return this.mtMixTrainScheduleTrainMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<MtMixTrainScheduleTrain> findScheduleTrainList(Integer trainId){

        return this.mtMixTrainScheduleTrainMapper.findScheduleTrainList(trainId);
    }

    @Override
    public int save(MtMixTrainScheduleTrain record){

        return this.mtMixTrainScheduleTrainMapper.insertSelective(record);
    }


}
