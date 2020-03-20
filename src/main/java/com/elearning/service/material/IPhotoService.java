package com.elearning.service.material;


import com.elearning.pojo.material.Photo;

import java.util.List;
import java.util.Map;

public interface IPhotoService {

    int insertSelective(Photo record);

    int deleteByAlbumId(Long albumId);

    int deleteByPhotoId(Long photoId);

    List<Photo> findByBasicCondition(Map<String,Object> condition);

    Photo findById(Long photoId);


}
