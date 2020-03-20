package com.elearning.service.courseStudy;

import com.elearning.dao.courseStudy.CaptionsMapper;
import com.elearning.pojo.courseStudy.Captions;
import com.elearning.service.courseStudy.ICaptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("captionsService")
public class CaptionsServiceImpl implements ICaptionsService {

    @Autowired
    private CaptionsMapper captionsMapper;

    public List<Captions> getCaptionsByCaptionsIdReturnList(Long captionsId){

        return captionsMapper.getCaptionsByCaptionsIdReturnList(captionsId);
    }

    @Override
    public Captions getCaptionsByCourseId(Long courseId) {
        return captionsMapper.getCaptionsByCourseId(courseId);
    }


}
