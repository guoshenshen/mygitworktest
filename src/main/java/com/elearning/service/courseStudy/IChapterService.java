package com.elearning.service.courseStudy;


import com.elearning.common.ServiceResponse;
import com.elearning.pojo.courseStudy.Chapter;
import com.elearning.pojo.pub.Course;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface IChapterService {


    /**
     * 课程目录
     * @param courseId
     * @param userId
     * @return
     */
    ServiceResponse getChaptersByCourseId(Long courseId, int userId);

    List<Chapter> findByMap(Map<String, Object> map);

    int insertSelective(Chapter chapter);

    Chapter getChapterById(Long chapterId);

    int updateByPrimaryKeySelective(Chapter chapter);

    ServiceResponse inertChapter(Long courseId, String chapterName, Long chapterId);

    List<Course> listCourseByChapterId(Long chapterId);

    ServiceResponse deleteChapter(Long chapterId, Long courseId);

    ServiceResponse insertChapterSecond(Long courseId, Long chapterId, Double classHour, Integer kindId, String courseName,
                                        HttpServletRequest request);

    ServiceResponse getsecondCourse(String courseName, Long chapterId);

    Course findCourseBySecondcourse(Long courseId);


}
