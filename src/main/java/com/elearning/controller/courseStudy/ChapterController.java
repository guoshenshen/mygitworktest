package com.elearning.controller.courseStudy;

import com.elearning.common.ServiceResponse;
import com.elearning.pojo.courseStudy.Chapter;
import com.elearning.service.courseStudy.IChapterService;
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
@RequestMapping("/chapter/")
public class ChapterController {

    @Autowired
    private IChapterService chapterService;
















}
