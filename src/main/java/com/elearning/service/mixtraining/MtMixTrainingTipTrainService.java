package com.elearning.service.mixtraining;

import com.elearning.dao.mixtraining.MtMixTrainingTipTrainMapper;
import com.elearning.dao.mixtraining.MtTrainNewsMapper;
import com.elearning.pojo.mixtraining.MtMixTrainingTipTrain;
import com.elearning.pojo.mixtraining.MtMixTrainingTipTrainKey;
import com.elearning.pojo.mixtraining.MtTrainNewsWithBLOBs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("mtMixTrainingTipTrainService")
public class MtMixTrainingTipTrainService implements IMtMixTrainingTipTrainService{


    @Autowired
    private MtMixTrainingTipTrainMapper MtMixTrainingTipTrainMapper;

    @Override
    public MtMixTrainingTipTrain selectByPrimaryKey(MtMixTrainingTipTrainKey key){

        return this.MtMixTrainingTipTrainMapper.selectByPrimaryKey(key);
    }

    @Override
    public List<MtMixTrainingTipTrain> findAll(){

        return this.MtMixTrainingTipTrainMapper.findAll();
    }



}
