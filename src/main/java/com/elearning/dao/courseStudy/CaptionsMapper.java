package com.elearning.dao.courseStudy;

import com.elearning.pojo.courseStudy.Captions;

import java.util.List;

public interface CaptionsMapper {
    int insert(Captions record);

    int insertSelective(Captions record);

    List<Captions> getCaptionsByCaptionsIdReturnList(Long captionsId);

    Captions getCaptionsByCourseId(Long courseId);
}