package com.elearning.service.pub;

import com.elearning.common.ServiceResponse;
import com.elearning.pojo.pub.Course;
import com.elearning.vo.CourseForm;
import com.elearning.vo.CourseFormAll;
import com.elearning.vo.CourseVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public interface ICourseService {

    /**
     * 通过课程id获取课程
     * @param courseId
     * @return
     */
    Course getCourse(Long courseId);

    /**
     * 将课程装换为VO类
     * @param course
     * @return
     */
    CourseVo getCourseVo(Course course);

    /**
     * 查询热门课件
     * @param orgSEQ
     * @return
     */
    ServiceResponse listPopularCourse(String orgSEQ);

    List<CourseVo> listPopularCourseReturnCourseVoList(String orgSEQ);

    /**
     * 选学成员
     * @param map
     * @return
     */
    ServiceResponse listLimitedUserInfoByCourseId(Map<String, Object> map);

    /**
     * 课程管理
     * @param map
     * @return
     */
    ServiceResponse queryAllOffLineCourseList(Map<String, Object> map);

    /**
     * 共享课程
     * @param map
     * @return
     */
    ServiceResponse queryAllShareCourseList(Map<String, Object> map);

    /**
     * 删除课程
     * @param selectbox
     * @return
     */
    ServiceResponse deleteCourse(String[] selectbox);

    /**
     * 修改课程发布状态
     * @param courseId
     * @param pubStatusId
     * @return
     */
    ServiceResponse updateCoursePubStatus(Long courseId, Integer pubStatusId);

    /**
     * 修改课程的公开范围
     * @param courseId
     * @param openScope
     * @param orgId
     * @return
     */
    ServiceResponse updateCourseOpenScope(Long courseId, Integer openScope,Integer orgId);


    CourseVo getCourseVo(Course _course,Integer orgId,Integer operatorId);

    int insertCourse(CourseForm courseForm, HttpServletRequest request);

    ServiceResponse updateCoursePic(Long courseId, String pictureUrl);

    String uploadSingleVideo(HttpServletRequest request, HttpServletResponse response);

    Integer updateCourse(Course course);

    String uploadSingleCourse(HttpServletRequest request);

    boolean isAllreadyLearned(Long courseId);

    boolean isAllreadyArranged(Long courseId);

    int insert(Course course);


    Integer delete(Long courseId);

    ServiceResponse findVisibleCourseByCondition(Integer startIndex, Integer count, HttpServletRequest request);

    /**
     * 根据一级课程的id查询改课程下所有的子集课程
     * @param courseId
     * @return
     */
    List<Course> listSecondCourseByCourseId(Long courseId);

    ServiceResponse getCourseRelevantCourse(Long originalCourseId, HttpServletRequest request);

    int deleteByPrimaryKey(Long courseId);

    List<Course> listCourseByMap(Map<String,Object> map);

    Course findCourseById(Long course_id);

    List<Course> getListByCourseNameAndOrgId(Map<String,Object> map);

    List<CourseFormAll> getCourseFormList(List<Course> courseList);

    CourseFormAll getCourseForm(Course _course);

    List<Course> listCoursesByOperatorId(Map<String, Object> map);

}
