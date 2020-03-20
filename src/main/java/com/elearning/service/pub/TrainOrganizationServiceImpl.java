package com.elearning.service.pub;

import com.elearning.dao.pub.TrainOrganizationMapper;
import com.elearning.pojo.pub.TrainOrganization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("trainOrganizationService")
public class TrainOrganizationServiceImpl implements ITrainOrganizationService {

    @Autowired
    private TrainOrganizationMapper trainOrganizationMapper;

    @Override
    public TrainOrganization selectByPrimaryKey(Integer id){
        return this.trainOrganizationMapper.selectByPrimaryKey(id);
    }

    public List<TrainOrganization> findTrainOrganizationByTrainID(Integer trainId){
        return this.trainOrganizationMapper.findTrainOrganizationByTrainID(trainId);
    }

    public int insertSelective(TrainOrganization record){
        return this.trainOrganizationMapper.insertSelective(record);
    }

    public int deleteByPrimaryKey(Integer id){
        return this.trainOrganizationMapper.deleteByPrimaryKey(id);
    }

}
