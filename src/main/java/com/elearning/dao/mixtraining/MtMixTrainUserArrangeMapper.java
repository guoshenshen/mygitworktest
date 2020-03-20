package com.elearning.dao.mixtraining;

import com.elearning.pojo.mixtraining.MtMixTrainUserArrange;
import com.elearning.vo.BasicUserForm;

import java.util.List;
import java.util.Map;

public interface MtMixTrainUserArrangeMapper {

    int deleteByPrimaryKey(Integer ID);

    int insert(MtMixTrainUserArrange record);

    int insertSelective(MtMixTrainUserArrange record);

    MtMixTrainUserArrange selectByPrimaryKey(Integer ID);

    int updateByPrimaryKeySelective(MtMixTrainUserArrange record);

    int updateByPrimaryKey(MtMixTrainUserArrange record);

    List<BasicUserForm> findMtMixTrainUserArrangeListByAddrBookId(Map<String,Object> map);

    List<MtMixTrainUserArrange> getListByAddrbookId(int addrbookId);

    List<MtMixTrainUserArrange> getListByAddrbookIdAndIperatorIdsInMap(Map<String,Object> map);

}