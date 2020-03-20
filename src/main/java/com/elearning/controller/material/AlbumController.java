package com.elearning.controller.material;

import com.elearning.common.Constants;
import com.elearning.common.ResponseCode;
import com.elearning.common.ServiceResponse;
import com.elearning.interceptor.loginInterceptor.IsCheckUserLogin;
import com.elearning.pojo.material.Album;
import com.elearning.pojo.pub.EosOperator;
import com.elearning.service.material.IAlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 *
 */
@Controller
@RequestMapping("/album/")
public class AlbumController {

    @Autowired
    private IAlbumService albumService;

    @IsCheckUserLogin(check = true)
    @RequestMapping("getAlbumByIdReturnList.do")
    @ResponseBody
    public ServiceResponse getAlbumByIdReturnList(Long id, Model model){
        List<Album> albumList = albumService.getAlbumByIdReturnList(id);

        return ServiceResponse.createBySuccess(albumList);
    }

    /**
     * 加载某培训项目(...)相册
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("loadAlbumList.do")
    @ResponseBody
    public ServiceResponse loadAlbumList(HttpServletRequest request){

        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        return this.albumService.loadAlbumList(request);
    }

    /**
     * 保存相册
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("saveAlbum.do")
    @ResponseBody
    public ServiceResponse saveAlbum(HttpServletRequest request){

        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        return this.albumService.saveAlbum(request);
    }


    /**
     * 加载某相册照片信息
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("loadPhotoList.do")
    @ResponseBody
    public ServiceResponse loadPhotoList(HttpServletRequest request){

        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        return this.albumService.loadPhotoList(request);
    }

    /**
     * 删除某个相册
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("deleteAlbum.do")
    @ResponseBody
    public ServiceResponse deleteAlbum(HttpServletRequest request){

        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        return this.albumService.deleteAlbum(request);
    }

    /**
     * 删除相片
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("deletePhoto.do")
    @ResponseBody
    public ServiceResponse deletePhoto(HttpServletRequest request){

        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        return this.albumService.deletePhoto(request);
    }



}
