package com.elearning.dao.coursemanage;

import com.elearning.common.ServiceResponse;
import com.elearning.pojo.coursemanage.UscLectureFile;

import java.util.List;

public interface UscLectureFileMapper {
    int deleteByPrimaryKey(Integer lectureId);

    int insert(UscLectureFile record);

    int insertSelective(UscLectureFile record);

    UscLectureFile selectByPrimaryKey(Integer lectureId);

    int updateByPrimaryKeySelective(UscLectureFile record);

    int updateByPrimaryKey(UscLectureFile record);


    /**
     * 根据courseId查询相关资料
     * @param courseId
     * @return
     */
    List<UscLectureFile> getUscLectureFileByCourseId(Long courseId);
}