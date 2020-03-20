package com.elearning.service.mixtraining;

import com.elearning.dao.mixtraining.MtMixTrainUserArrangeMapper;
import com.elearning.pojo.mixtraining.MtMixTrainUserArrange;
import com.elearning.vo.BasicUserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("mtMixTrainUserArrangeService")
public class MtMixTrainUserArrangeService implements IMtMixTrainUserArrangeService{

    @Autowired
    private MtMixTrainUserArrangeMapper mtMixTrainUserArrangeMapper;
    //@Transactional(rollbackFor = {Exception.class })

    @Override
    public MtMixTrainUserArrange selectByPrimaryKey(Integer ID){
        return mtMixTrainUserArrangeMapper.selectByPrimaryKey(ID);
    }

    @Override
    public int insert(MtMixTrainUserArrange record){
        return mtMixTrainUserArrangeMapper.insert(record);
    }

    @Override
    public List<MtMixTrainUserArrange> getListByAddrbookId(int addrbookId){
        return mtMixTrainUserArrangeMapper.getListByAddrbookId(addrbookId);
    }

    @Override
    public List<BasicUserForm> findMtMixTrainUserArrangeListByAddrBookId(Map<String,Object> map){
        return this.mtMixTrainUserArrangeMapper.findMtMixTrainUserArrangeListByAddrBookId(map);

    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public int deleteMtMixTrainUsers(Integer addrbookId,List<Integer> operatorIds){
        int i = 0;
        if(operatorIds.size() > 0){
            //通过参数查询出集合
            Map<String,Object> parmMap = new HashMap<>();
            parmMap.put("addrbookId",addrbookId);
            parmMap.put("operatorIds",operatorIds);

            List<MtMixTrainUserArrange> mtMixTrainUserArranges = this.mtMixTrainUserArrangeMapper.getListByAddrbookIdAndIperatorIdsInMap(parmMap);
            for(MtMixTrainUserArrange mtMixTrainUserArrange : mtMixTrainUserArranges){
                i = this.mtMixTrainUserArrangeMapper.deleteByPrimaryKey(mtMixTrainUserArrange.getID());
            }
        }
        return i;
    }




}
