package com.elearning.service.mixtraining;

import com.elearning.dao.mixtraining.MtMixTrainSectionFileMapper;
import com.elearning.pojo.mixtraining.MtMixTrainSectionFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("mtMixTrainSectionFileService")
public class MtMixTrainSectionFileService implements IMtMixTrainSectionFileService{

    @Autowired
    private MtMixTrainSectionFileMapper mtMixTrainSectionFileMapper;

    @Override
    public MtMixTrainSectionFile selectByPrimaryKey(Integer ID){

        return this.mtMixTrainSectionFileMapper.selectByPrimaryKey(ID);
    }



}
