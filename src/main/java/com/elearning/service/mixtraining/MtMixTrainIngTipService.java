package com.elearning.service.mixtraining;

import com.elearning.dao.mixtraining.MtMixTrainIngTipMapper;
import com.elearning.pojo.mixtraining.MtMixTrainIngTip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("mtMixTrainIngTipService")
public class MtMixTrainIngTipService implements IMtMixTrainIngTipService{


    @Autowired
    private MtMixTrainIngTipMapper mtMixTrainIngTipMapper;

    @Override
    public MtMixTrainIngTip findById(Integer tipId){

        return this.mtMixTrainIngTipMapper.selectByPrimaryKey(tipId);
    }



}
