package com.elearning.dao.mixtraining;

import com.elearning.pojo.mixtraining.MtMixTrainAndAddrBook;

import java.util.List;

public interface MtMixTrainAndAddrBookMapper {

    int deleteByPrimaryKey(Integer ID);

    int insert(MtMixTrainAndAddrBook record);

    int insertSelective(MtMixTrainAndAddrBook record);

    MtMixTrainAndAddrBook selectByPrimaryKey(Integer ID);

    int updateByPrimaryKeySelective(MtMixTrainAndAddrBook record);

    int updateByPrimaryKey(MtMixTrainAndAddrBook record);

    List<MtMixTrainAndAddrBook> findByTrainId(Integer trainId);

    List<MtMixTrainAndAddrBook> getMtMixTrainAndAddrBookByAddrBookId(Integer addrbookId);

}