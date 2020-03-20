package com.elearning.service.material;

import com.elearning.common.Constants;
import com.elearning.common.ServiceResponse;
import com.elearning.common.SnowflakeIdWorker;
import com.elearning.dao.material.AlbumMapper;
import com.elearning.pojo.material.Album;
import com.elearning.pojo.material.Photo;
import com.elearning.pojo.pub.EosOperator;
import com.elearning.pojo.pub.EosorgTEmployee;
import com.elearning.pojo.pub.EosorgTOrganization;
import com.elearning.service.material.IAlbumService;
import com.elearning.service.pub.IEosorgTEmployeeService;
import com.elearning.service.pub.IEosorgTOrganizationService;
import com.elearning.util.ToolsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service("albumService")
public class AlbumServiceImpl implements IAlbumService{

    @Autowired
    private AlbumMapper albumMapper;

    @Autowired
    private IEosorgTEmployeeService eosorgTEmployeeService;

    @Autowired
    private IEosorgTOrganizationService eosorgTOrganizationService;

    @Autowired
    private IPhotoService photoService;



    public List<Album> getAlbumByIdReturnList(Long id){

        return albumMapper.getAlbumByIdReturnList(id);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public ServiceResponse loadAlbumList(HttpServletRequest request){

        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        List<Album> albumList = new ArrayList<>();
        Long resourceId = Long.parseLong(request.getParameter("resourceId"));
        Integer type = Integer.parseInt(request.getParameter("type"));
        Integer admin = 0;
        if(request.getParameter("admin")!=null){
            admin=Integer.parseInt(request.getParameter("admin"));
        }

        Map<String,Object> condition = new HashMap<>();
        condition.put("type", type);
        condition.put("resourceId", resourceId);

        if(admin==1){
            //进入相册管理后台
        } else{
            //学员端相册展示
            EosorgTEmployee eosorgTEmployee = this.eosorgTEmployeeService.findById(eosoperator.getOperatorId());
            Integer orgId = eosorgTEmployee.getOrgID();
            if(eosorgTEmployee.getOrgID() != null){
                EosorgTOrganization org = this.eosorgTOrganizationService.findById(eosorgTEmployee.getOrgID());
                if(org.getOrgSEQ() != null){
                    List<String> orgSeqList = ToolsUtil.orgSEQList(org.getOrgSEQ());
                    condition.put("openOrgSeq", orgSeqList);
                }
            }
        }
        albumList = this.albumMapper.findByBasicCondition(condition);

        return ServiceResponse.createBySuccess(albumList);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public ServiceResponse saveAlbum(HttpServletRequest request){

        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);

        int albumType = Integer.parseInt(request.getParameter("albumType"));
        String albumDescription = request.getParameter("albumDescription");
        String albumName = request.getParameter("albumName");
        String albumIdStr = request.getParameter("albumId");
        String resourceId = request.getParameter("resourceId");
        String cover = request.getParameter("cover");

        Album album = null;
        if(albumIdStr==null || albumIdStr.trim().length()==0){
            //insert
            album = new Album();
            album.setCreatorName(eosoperator.getOperatorName());
            album.setCreateDate(new Date());
        } else{
            //update
            album = this.findById(Long.parseLong(albumIdStr));
        }
        album.setOpenOrgSeq("1.");
        album.setResourceId(Long.parseLong(resourceId));
        album.setType(albumType == 1?true:false);
        album.setAlbumName(albumName);
        album.setDescription(albumDescription);
        if(cover!=null && cover.trim().length()!=0){
            album.setCover(cover);
        }
        try {
            if(albumIdStr==null || albumIdStr.trim().length()==0){
                //insert
                SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
                Long id = idWorker.nextId();
                album.setId(id);
                this.albumMapper.insert(album);
            } else{
                this.albumMapper.updateByPrimaryKeySelective(album);
            }
            return ServiceResponse.createBySuccess(album);
        } catch (Exception e) {
            return ServiceResponse.createByError();
        }
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public ServiceResponse loadPhotoList(HttpServletRequest request){

        Long albumId = Long.parseLong(request.getParameter("albumId"));
        Map<String,Object> condition = new HashMap<>();
        condition.put("albumId", albumId);
        List<Photo> photoList=this.photoService.findByBasicCondition(condition);

        return ServiceResponse.createBySuccess(photoList);

    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public ServiceResponse deleteAlbum(HttpServletRequest request){

        Long albumId=Long.parseLong(request.getParameter("albumId"));

        this.photoService.deleteByAlbumId(albumId);
        this.albumMapper.deleteById(albumId);

        return ServiceResponse.createBySuccess();

    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public Album findById(Long id){

        Album album = null;
        List<Album> albumList = this.albumMapper.findById(id);
        if(albumList.size()>0){
            album = albumList.get(0);
        }
        return album;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public ServiceResponse deletePhoto(HttpServletRequest request){

        Long photoId = Long.parseLong(request.getParameter("photoId"));
        this.photoService.deleteByPhotoId(photoId);

        return ServiceResponse.createBySuccess();

    }


}
