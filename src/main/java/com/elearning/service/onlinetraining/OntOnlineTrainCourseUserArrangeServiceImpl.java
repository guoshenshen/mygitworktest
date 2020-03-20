package com.elearning.service.onlinetraining;

import com.elearning.dao.onlinetraining.OntOnlineTrainCourseUserArrangeMapper;
import com.elearning.pojo.onlinetraining.OntOnlineTrainCourseUserArrange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 〈<br>
 * 〈〉
 *
 * @author lxx
 * @create 2019/9/25 9:29
 */
@Service("ontOnlineTrainCourseUserArrangeService")
public class OntOnlineTrainCourseUserArrangeServiceImpl implements IOntOnlineTrainCourseUserArrangeService{

    @Autowired
    private OntOnlineTrainCourseUserArrangeMapper ontOnlineTrainCourseUserArrangeMapper;


    @Override
    public String getMaxID() {
        return ontOnlineTrainCourseUserArrangeMapper.getMaxID();
    }

    @Override
    public int insert(OntOnlineTrainCourseUserArrange ontOnlineTrainCourseUserArrange) {
        return ontOnlineTrainCourseUserArrangeMapper.insertSelective(ontOnlineTrainCourseUserArrange);
    }
}
