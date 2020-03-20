package com.elearning.service.mixtraining;

import com.elearning.common.ServiceResponse;
import com.elearning.pojo.mixtraining.MtMixTrainPhoto;
import com.elearning.pojo.mixtraining.MtTrainNewsWithBLOBs;
import com.elearning.vo.mixtraining.MtTrainNewsForm;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface IMtMixTrainPhotoService {

    MtMixTrainPhoto selectByPrimaryKey(Long photoId);


}
