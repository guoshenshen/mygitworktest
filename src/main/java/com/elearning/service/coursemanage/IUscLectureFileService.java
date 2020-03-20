package com.elearning.service.coursemanage;

import com.elearning.common.ServiceResponse;
import com.elearning.pojo.coursemanage.UscLectureFile;

import java.util.List;

/**
 * 〈<br>
 * 〈〉
 *
 * @author lxx
 * @create 2019/7/17 14:05
 */
public interface IUscLectureFileService {

    /**
     * 相关资料
     * @param courseId
     * @return
     */
    ServiceResponse<List<UscLectureFile>> findAllLectureFile(Long courseId);

    int insertLectureFile(UscLectureFile uscLectureFile);

    UscLectureFile findById(Integer lectureId);

    int update(UscLectureFile uscLectureFile);

    int deleteUscLectureFile(String[] releCourseIds);
}
