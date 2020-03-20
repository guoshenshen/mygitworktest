package com.elearning.service.systemManage;

import com.elearning.dao.systemManage.PictureBaseMapper;
import com.elearning.pojo.systemManage.PictureBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("pictureBaseService")
public class PictureBaseServiceImpl implements IPictureBaseService{

    @Autowired
    private PictureBaseMapper pictureBaseMapper;


    @Override
    public PictureBase findById(Integer id) {

        return this.pictureBaseMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<PictureBase> getListByParmMap(Map<String,Object> parmMap){

        return this.pictureBaseMapper.getListByParmMap(parmMap);
    }

    @Override
    public int deleteByPrimaryKey(Integer id){

        return this.pictureBaseMapper.deleteByPrimaryKey(id);
    }


}
