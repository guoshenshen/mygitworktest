package com.elearning.dao.pub;

import com.elearning.pojo.pub.Course;
import com.elearning.vo.CourseVo;

import java.util.List;
import java.util.Map;

public interface CourseMapper {

    int deleteByPrimaryKey(Long courseId);

    int insert(Course record);

    int insertSelective(Course record);

    Course selectByPrimaryKey(Long courseId);

    int updateByPrimaryKeySelective(Course record);

    int updateByPrimaryKey(Course record);

    /**
     * 查询所有的课程
     * @return
     */
    List<Course> listCourse();

    List<Course> listCourseByMap(Map<String,Object> map);

    /**
     * 根据oegSEQ查询course
     * @param map
     * @return
     */
    List<CourseVo> listCourseCreateDateAndEnrollNums(Map<String,Object> map);

    /**
     * 课程管理
     * @param map
     * @return
     */
    List<Course> findAllCourseListByOrgInner(Map<String, Object> map);

    /**
     * 根据一级课程的id查询改课程下所有的子集课程
     * @param courseId
     * @return
     */
    List<Course> listSecondCourseByCourseId(Long courseId);

    /**
     *
     * 共享课程池
     * @param map
     */
    List<Course> findAllCourseListByOrgOuter(Map<String, Object> map);

    /**
     * 判断某门课程是否已有学员学习
     * @param courseId
     * @return
     */
    int isAllreadyLearned(long courseId);

    /**
     * 判断某课程是否已安排人员学习
     * @param courseId
     * @return
     */
    int isAllreadyArranged(long courseId);

    /**
     * 收藏夹
     * @param map
     * @return
     */
    List<Course> findCollectCourseListByOrg(Map<String, Object> map);

    List<Course> findVisibleCourseByCondition(Map<String, Object> map);

    List<Course> getCourseRelevantCourse(Map<String, Object> map);

    List<Course> getListByCourseNameAndOrgId(Map<String,Object> map);

    List<Course> listCoursesByOperatorId(Map<String, Object> map);


}