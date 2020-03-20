package com.elearning.controller.recommend;

import com.elearning.common.ServiceResponse;
import com.elearning.pojo.recommend.SeriesBanner;
import com.elearning.service.recommend.ISeriesBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 */
@Controller
@RequestMapping("/seriesBanner/")
public class SeriesBannerController {

    @Autowired
    private ISeriesBannerService seriesBannerService;

    @RequestMapping("getSeriesBannerByID.do")
    @ResponseBody
    public ServiceResponse getSeriesBannerByID(Integer id, Model model){
        SeriesBanner seriesBanner = seriesBannerService.selectByPrimaryKey(id);

        return ServiceResponse.createBySuccess(seriesBanner);
    }















}
