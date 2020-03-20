package com.elearning.controller.pub;

import com.elearning.common.ServiceResponse;
import com.elearning.pojo.pub.DDictionary;
import com.elearning.service.pub.IDDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 */
@Controller
@RequestMapping("/dDictionary/")
public class DDictionaryController {

    @Autowired
    private IDDictionaryService dDictionaryService;

    @RequestMapping("getDDictionaryByID.do")
    @ResponseBody
    public ServiceResponse getDDictionaryByID(Integer ID, Model model){
        DDictionary dDictionary = dDictionaryService.selectByPrimaryKey(ID);

        return ServiceResponse.createBySuccess(dDictionary);
    }















}
