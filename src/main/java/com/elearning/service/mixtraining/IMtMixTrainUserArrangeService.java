package com.elearning.service.mixtraining;

import com.elearning.pojo.mixtraining.MtMixTrainUserArrange;
import com.elearning.vo.BasicUserForm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IMtMixTrainUserArrangeService {

    MtMixTrainUserArrange selectByPrimaryKey(Integer ID);

    List<BasicUserForm> findMtMixTrainUserArrangeListByAddrBookId(Map<String,Object> map);

    List<MtMixTrainUserArrange> getListByAddrbookId(int addrbookId);

    int insert(MtMixTrainUserArrange record);

    int deleteMtMixTrainUsers(Integer addrbookId,List<Integer> operatorIds);

}
