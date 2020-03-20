package com.elearning.dao.mixtraining;

import com.elearning.pojo.mixtraining.MtMixTrainUserTrainInfo;
import com.elearning.vo.BasicUserForm;

import java.util.List;
import java.util.Map;

public interface MtMixTrainUserTrainInfoMapper {
    int deleteByPrimaryKey(Integer ID);

    int insert(MtMixTrainUserTrainInfo record);

    int insertSelective(MtMixTrainUserTrainInfo record);

    MtMixTrainUserTrainInfo selectByPrimaryKey(Integer ID);

    int updateByPrimaryKeySelective(MtMixTrainUserTrainInfo record);

    int updateByPrimaryKey(MtMixTrainUserTrainInfo record);

    List<MtMixTrainUserTrainInfo> findByUserId(Integer operatorId);

    List<MtMixTrainUserTrainInfo> findByTrainId(Integer trainID);

    List<MtMixTrainUserTrainInfo> getListByUserIDAndTrainID(Map<String,Object> parm);

    List<MtMixTrainUserTrainInfo> getListByUserIDAndTrainIDAndStatus(Map<String,Object> parm);

    List<MtMixTrainUserTrainInfo> searchMtMixTrainUserTrainInfoList(Map<String,Object> parm);

    List<MtMixTrainUserTrainInfo> getListByMap(Map<String,Object> parm);

    int findCountByTrainId(Integer trainId);

    List<BasicUserForm> getAttendedListForParmMap(Map<String, Object> condition);


}