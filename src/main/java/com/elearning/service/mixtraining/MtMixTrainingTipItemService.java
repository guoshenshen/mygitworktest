package com.elearning.service.mixtraining;

import com.elearning.dao.mixtraining.MtMixTrainingTipItemMapper;
import com.elearning.dao.mixtraining.MtTrainNewsMapper;
import com.elearning.pojo.mixtraining.MtMixTrainingTipItem;
import com.elearning.pojo.mixtraining.MtTrainNewsWithBLOBs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


@Service("mtMixTrainingTipItemService")
public class MtMixTrainingTipItemService implements IMtMixTrainingTipItemService{


    @Autowired
    private MtMixTrainingTipItemMapper mtMixTrainingTipItemMapper;

    @Override
    public MtMixTrainingTipItem selectByPrimaryKey(Integer id){

        return this.mtMixTrainingTipItemMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<MtMixTrainingTipItem> findListByTipId(Integer tipId){

        return this.mtMixTrainingTipItemMapper.findListByTipId(tipId);
    }

    @Override
    public List<MtMixTrainingTipItem> getMtBeforeTipIteminfoList(List<MtMixTrainingTipItem> tempList){
        List<MtMixTrainingTipItem> mtTipItemList=new ArrayList();
        for(MtMixTrainingTipItem tipItem:tempList){
            if(tipItem.getIsBeforeOrAfter()==1201)mtTipItemList.add(tipItem);
        }
        Comparator comp = new Comparator(){
            public int compare(Object o1,Object o2){
                MtMixTrainingTipItem p1=(MtMixTrainingTipItem)o1;
                MtMixTrainingTipItem p2=(MtMixTrainingTipItem)o2;
                if(p1.getTipDays()< p2.getTipDays()){
                    return 1;
                } else if(p1.getSeqNum()>p2.getSeqNum()){
                    return 1;
                } else{
                    return 0;
                }
            }
        };
        Collections.sort(mtTipItemList,comp);
        return mtTipItemList;

    }

    @Override
    public List<MtMixTrainingTipItem> getMtAfterTipIteminfoList(List<MtMixTrainingTipItem> tempList){
        List<MtMixTrainingTipItem> mtTipItemList=new ArrayList();
        for(MtMixTrainingTipItem tipItem:tempList){
            if(tipItem.getIsBeforeOrAfter()==1202)mtTipItemList.add(tipItem);
        }
        Comparator comp = new Comparator(){
            public int compare(Object o1,Object o2) {
                MtMixTrainingTipItem p1=(MtMixTrainingTipItem)o1;
                MtMixTrainingTipItem p2=(MtMixTrainingTipItem)o2;
                if(p1.getTipDays()> p2.getTipDays()){
                    return 1;
                } else if(p1.getSeqNum()>p2.getSeqNum()){
                    return 1;
                } else{
                    return 0;
                }
            }
        };
        Collections.sort(mtTipItemList,comp);
        return mtTipItemList;

    }



}
