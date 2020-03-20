package com.elearning.dao.material;

import com.elearning.pojo.material.Photo;

import java.util.List;
import java.util.Map;

public interface PhotoMapper {

    int insert(Photo record);

    int insertSelective(Photo record);

    int deleteByAlbumId(Long albumId);

    int deleteByPhotoId(Long photoId);

    List<Photo> findByBasicCondition(Map<String,Object> condition);

    List<Photo> findById(Long photoId);


}