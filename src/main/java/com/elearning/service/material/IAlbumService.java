package com.elearning.service.material;


import com.elearning.common.ServiceResponse;
import com.elearning.pojo.material.Album;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IAlbumService {

    List<Album> getAlbumByIdReturnList(Long id);

    ServiceResponse loadAlbumList(HttpServletRequest request);

    ServiceResponse saveAlbum(HttpServletRequest request);

    ServiceResponse loadPhotoList(HttpServletRequest request);

    ServiceResponse deleteAlbum(HttpServletRequest request);

    Album findById(Long id);

    ServiceResponse deletePhoto(HttpServletRequest request);


}
