package com.elearning.service.mixtraining;

import com.elearning.dao.mixtraining.MtMixTrainAndAddrBookMapper;
import com.elearning.dao.mixtraining.MtTrainNewsMapper;
import com.elearning.pojo.mixtraining.MtMixTrainAndAddrBook;
import com.elearning.pojo.mixtraining.MtTrainNewsWithBLOBs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("mtMixTrainAndAddrBookService")
public class MtMixTrainAndAddrBookService implements IMtMixTrainAndAddrBookService{


    @Autowired
    private MtMixTrainAndAddrBookMapper mtMixTrainAndAddrBookMapper;

    @Override
    public MtMixTrainAndAddrBook selectByPrimaryKey(Integer ID){

        return this.mtMixTrainAndAddrBookMapper.selectByPrimaryKey(ID);
    }

    @Override
    public int insert(MtMixTrainAndAddrBook record){

        return this.mtMixTrainAndAddrBookMapper.insert(record);
    }

    @Override
    public int insertSelective(MtMixTrainAndAddrBook record){

        return this.mtMixTrainAndAddrBookMapper.insertSelective(record);
    }

    @Override
    public int deleteByPrimaryKey(Integer ID){
        return this.mtMixTrainAndAddrBookMapper.deleteByPrimaryKey(ID);
    }

    @Override
    public List<MtMixTrainAndAddrBook> findByTrainId(Integer trainId){
        List<MtMixTrainAndAddrBook> list = new ArrayList<>();

        if (trainId != null){
            list = this.mtMixTrainAndAddrBookMapper.findByTrainId(trainId);
        }
        return list;
    }

    @Override
    public MtMixTrainAndAddrBook getMtMixTrainAndAddrBookByAddrBookId(Integer addrbookId){
        List<MtMixTrainAndAddrBook> mtMixTrainAndAddrBookList = this.mtMixTrainAndAddrBookMapper.getMtMixTrainAndAddrBookByAddrBookId(addrbookId);
        if(mtMixTrainAndAddrBookList.size() > 0 ){
            return mtMixTrainAndAddrBookList.get(0);
        }else{
            return null;
        }
    }



}
