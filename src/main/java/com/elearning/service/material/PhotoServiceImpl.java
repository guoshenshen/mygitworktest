package com.elearning.service.material;

import com.elearning.dao.material.PhotoMapper;
import com.elearning.pojo.material.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service("photoService")
public class PhotoServiceImpl implements IPhotoService{

    @Autowired
    private PhotoMapper photoMapper;

    public int insertSelective(Photo record){

        return photoMapper.insertSelective(record);
    }

    public int deleteByAlbumId(Long albumId){

        return photoMapper.deleteByAlbumId(albumId);
    }

    public int deleteByPhotoId(Long photoId){

        return photoMapper.deleteByPhotoId(photoId);
    }

    public List<Photo> findByBasicCondition(Map<String,Object> condition){

        return this.photoMapper.findByBasicCondition(condition);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public Photo findById(Long photoId){

        Photo photo = null;
        List<Photo> photoList = this.photoMapper.findById(photoId);
        if(photoList.size()>0){
            photo = photoList.get(0);
        }
        return photo;

    }


}
