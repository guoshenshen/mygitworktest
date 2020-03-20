package com.elearning.controller.recommend;

import com.elearning.common.ServiceResponse;
import com.elearning.pojo.recommend.SeriesItem;
import com.elearning.service.recommend.ISeriesItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 */
@Controller
@RequestMapping("/seriesItem/")
public class SeriesItemController {

    @Autowired
    private ISeriesItemService seriesItemService;

    @RequestMapping("getSeriesItemByID.do")
    @ResponseBody
    public ServiceResponse getSeriesItemByID(Long id, Model model){
        SeriesItem seriesItem = seriesItemService.selectByPrimaryKey(id);

        return ServiceResponse.createBySuccess(seriesItem);
    }















}
