package com.elearning.service.onlinetraining;

import com.elearning.pojo.onlinetraining.OntOnlineTrainCourseUserArrange; /**
 * 〈<br>
 * 〈〉
 *
 * @author lxx
 * @create 2019/9/25 9:28
 */
public interface IOntOnlineTrainCourseUserArrangeService {

    String getMaxID();

    int insert(OntOnlineTrainCourseUserArrange ontOnlineTrainCourseUserArrange);
}
