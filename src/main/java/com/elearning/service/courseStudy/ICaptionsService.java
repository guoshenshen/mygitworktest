package com.elearning.service.courseStudy;


import com.elearning.pojo.courseStudy.Captions;

import java.util.List;

public interface ICaptionsService {

    List<Captions> getCaptionsByCaptionsIdReturnList(Long captionsId);

    Captions getCaptionsByCourseId(Long courseId);
}
