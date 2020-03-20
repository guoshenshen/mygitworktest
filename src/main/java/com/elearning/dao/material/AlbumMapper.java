package com.elearning.dao.material;

import com.elearning.pojo.material.Album;

import java.util.List;
import java.util.Map;

public interface AlbumMapper {
    int insert(Album record);

    int insertSelective(Album record);

    int deleteById(Long albumId);

    int updateByPrimaryKeySelective(Album record);

    List<Album> findById(Long id);

    List<Album> getAlbumByIdReturnList(Long id);

    List<Album> findByBasicCondition(Map<String,Object> condition);


}