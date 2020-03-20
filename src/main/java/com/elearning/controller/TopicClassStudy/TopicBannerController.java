package com.elearning.controller.TopicClassStudy;

import com.elearning.common.ServiceResponse;
import com.elearning.pojo.TopicClassStudy.TopicBanner;
import com.elearning.service.TopicClassStudy.ITopicBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 */
@Controller
@RequestMapping("/topicBanner/")
public class TopicBannerController {

    @Autowired
    private ITopicBannerService topicBannerService;

    @RequestMapping("getTopicBannerByID.do")
    @ResponseBody
    public ServiceResponse getTopicBannerByID(Integer id, Model model){
        TopicBanner topicBanner = topicBannerService.selectByPrimaryKey(id);

        return ServiceResponse.createBySuccess(topicBanner);
    }















}
