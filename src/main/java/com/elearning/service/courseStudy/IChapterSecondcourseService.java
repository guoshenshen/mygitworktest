package com.elearning.service.courseStudy;


import com.elearning.common.ServiceResponse;
import com.elearning.pojo.courseStudy.ChapterSecondcourse;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface IChapterSecondcourseService {

    List<ChapterSecondcourse> getChapterSecondcourseByIdReturnList(Long id);

    List<ChapterSecondcourse> findChapterSecondcourseByMap(Map<String,Object> map);

    int insertSelective(ChapterSecondcourse chapterSecondcourse);

    int orderChapter(Long courseId, String[] chapters);

    ChapterSecondcourse findChapterByChapterIdAndType(Map<String, Object> map);

    int delete(Long id);

    ServiceResponse orderSecondCourseId(String[] courses, Long chapterId);

    ServiceResponse deleteSecond(Long chapterId, Long courseId, HttpServletRequest request);
}
