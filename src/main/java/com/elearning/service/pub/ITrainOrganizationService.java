package com.elearning.service.pub;


import com.elearning.pojo.pub.TrainOrganization;

import java.util.List;

public interface ITrainOrganizationService {

    TrainOrganization selectByPrimaryKey(Integer id);

    List<TrainOrganization> findTrainOrganizationByTrainID(Integer trainId);

    int insertSelective(TrainOrganization record);

    int deleteByPrimaryKey(Integer id);



}
