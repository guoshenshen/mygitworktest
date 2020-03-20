package com.elearning.service.mixtraining;

import com.elearning.common.ServiceResponse;
import com.elearning.pojo.mixtraining.MtMixTrainUserTrainInfo;
import com.elearning.vo.mixtraining.MtMixTrainUserTrainInfoForm;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface IMtMixTrainUserTrainInfoService {

    int save(MtMixTrainUserTrainInfo record);

    int delete(HttpServletRequest request);

    int updateByPrimaryKeySelective(MtMixTrainUserTrainInfo record);

    int getUserApplyTrainStatus(Integer trainId,Integer operatorId);

    List<MtMixTrainUserTrainInfo> getListByUserIDAndTrainID(Map<String,Object> parm);

    List<MtMixTrainUserTrainInfo> getListByUserIDAndTrainIDAndStatus(Map<String,Object> parm);

    List<MtMixTrainUserTrainInfo> getListByMap(Map<String,Object> parm);

    MtMixTrainUserTrainInfo findById(Integer id);

    Integer update(MtMixTrainUserTrainInfoForm mtMixTrainUserTrainInfoForm,HttpServletRequest request);

    ServiceResponse searchMtMixTrainUserTrainInfoList(Map<String,Object> queryConditionMap,HttpServletRequest request);

    void updateattended(HttpServletRequest request);

    void addAttendedUsers(HttpServletRequest request);

    void updatestatus(HttpServletRequest request);

    void hurrySummary(HttpServletRequest request);

    Integer findCountByTrainId(Integer trainId);

    ServiceResponse getAttendedList(HttpServletRequest request);

    ServiceResponse updateTrainUserStatic(HttpServletRequest request);

    ServiceResponse getAttendedListForPaginationTool(Map<String,Object> queryConditionMap,HttpServletRequest request);

}
