package com.elearning.dao.pub;

import com.elearning.pojo.pub.TrainOrganization;

import java.util.List;

public interface TrainOrganizationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TrainOrganization record);

    int insertSelective(TrainOrganization record);

    TrainOrganization selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TrainOrganization record);

    int updateByPrimaryKey(TrainOrganization record);

    List<TrainOrganization> findTrainOrganizationByTrainID(Integer trainId);

}