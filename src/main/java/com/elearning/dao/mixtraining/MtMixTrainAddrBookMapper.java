package com.elearning.dao.mixtraining;

import com.elearning.pojo.mixtraining.MtMixTrainAddrBook;

import java.util.List;
import java.util.Map;

public interface MtMixTrainAddrBookMapper {

    int deleteByPrimaryKey(Integer addrbookId);

    int insert(MtMixTrainAddrBook record);

    int insertSelective(MtMixTrainAddrBook record);

    MtMixTrainAddrBook selectByPrimaryKey(Integer addrbookId);

    int updateByPrimaryKeySelective(MtMixTrainAddrBook record);

    int updateByPrimaryKey(MtMixTrainAddrBook record);

    List<MtMixTrainAddrBook> listAddrBook(Map<String,Object> condition);

    List<MtMixTrainAddrBook> getListByOperatorIdAndKeyWords(Map<String,Object> condition);

    List<MtMixTrainAddrBook> findListByMap(Map<String,Object> map);


}