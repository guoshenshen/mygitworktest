package com.elearning.dao.courseStudy;

import com.elearning.pojo.courseStudy.Chapter;
import com.elearning.pojo.pub.Course;

import java.util.List;
import java.util.Map;

public interface ChapterMapper {
    int insert(Chapter record);

    int insertSelective(Chapter record);

    int updateByPrimaryKeySelective(Chapter chapter);


    Chapter getChapterByChapterIdReturnList(Long chapterId);


    /**
     * 根据一级课程ID查询目录
     * @param
     * @return
     */
    List<Chapter> findByCourseId(Map<String, Object> map);


    /**
     * 根据目录ID查询该目录下的二级课程
     * @param
     * @return
     */
    List<Course> findByChapterId(Map<String, Object> map);


    List<Course> listCourseByChapterId(Long chapterId);

    /**
     * 根据二级课程查询一级课程的信息
     * @param courseId
     * @return
     */
    Course findCourseBySecondcourse(Long courseId);

    int delete(Long chapterId);
}