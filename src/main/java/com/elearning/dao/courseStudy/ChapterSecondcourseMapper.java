package com.elearning.dao.courseStudy;

import com.elearning.pojo.courseStudy.ChapterSecondcourse;

import java.util.List;
import java.util.Map;

public interface ChapterSecondcourseMapper {
    int insert(ChapterSecondcourse record);

    int insertSelective(ChapterSecondcourse record);

    List<ChapterSecondcourse> getChapterSecondcourseByIdReturnList(Long id);

    List<ChapterSecondcourse> findChapterSecondcourseByMap(Map<String, Object> map);

    ChapterSecondcourse findChapterByChapterIdAndType(Map<String, Object> map);

    int updateByPrimaryKeySelective(ChapterSecondcourse record);

    int delete(Long id);
}