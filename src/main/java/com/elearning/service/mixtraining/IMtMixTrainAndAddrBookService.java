package com.elearning.service.mixtraining;

import com.elearning.pojo.mixtraining.MtMixTrainAndAddrBook;

import java.util.List;
import java.util.Map;

public interface IMtMixTrainAndAddrBookService {

    MtMixTrainAndAddrBook selectByPrimaryKey(Integer ID);

    MtMixTrainAndAddrBook getMtMixTrainAndAddrBookByAddrBookId(Integer addrbookId);

    List<MtMixTrainAndAddrBook> findByTrainId(Integer trainId);

    //List<MtMixTrainAndAddrBook> listAddrBook(Map<String,Object> map);

    int insert(MtMixTrainAndAddrBook record);

    int insertSelective(MtMixTrainAndAddrBook record);

    int deleteByPrimaryKey(Integer ID);

}
