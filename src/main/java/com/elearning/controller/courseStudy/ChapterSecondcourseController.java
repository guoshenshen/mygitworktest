package com.elearning.controller.courseStudy;

import com.elearning.common.ServiceResponse;
import com.elearning.pojo.courseStudy.ChapterSecondcourse;
import com.elearning.service.courseStudy.IChapterSecondcourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 *
 */
@Controller
@RequestMapping("/chapterSecondcourse/")
public class ChapterSecondcourseController {

    @Autowired
    private IChapterSecondcourseService chapterSecondcourseService;

    @RequestMapping("getChapterSecondcourseByIdReturnList.do")
    @ResponseBody
    public ServiceResponse getChapterSecondcourseByIdReturnList(Long id, Model model){
        List<ChapterSecondcourse> chapterSecondcourseList = chapterSecondcourseService.getChapterSecondcourseByIdReturnList(id);

        return ServiceResponse.createBySuccess(chapterSecondcourseList);
    }















}
