package com.elearning.service.mixtraining;

import com.elearning.dao.mixtraining.MtMixTrainPhotoMapper;
import com.elearning.pojo.mixtraining.MtMixTrainPhoto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("mtMixTrainPhotoService")
public class MtMixTrainPhotoService implements IMtMixTrainPhotoService {

    @Autowired
    private MtMixTrainPhotoMapper mtMixTrainPhotoMapper;

    @Override
    public MtMixTrainPhoto selectByPrimaryKey(Long photoId) {

        return this.mtMixTrainPhotoMapper.selectByPrimaryKey(photoId);
    }


}
