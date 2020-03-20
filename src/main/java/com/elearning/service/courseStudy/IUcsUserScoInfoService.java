package com.elearning.service.courseStudy;

import com.elearning.pojo.courseStudy.UcsUserScoInfo;

import java.util.List;
import java.util.Map; /**
 * 〈<br>
 * 〈〉
 *
 * @author lxx
 * @create 2019/10/14 16:24
 */
public interface IUcsUserScoInfoService {
    List<UcsUserScoInfo> findByMap(Map<String, Object> map);
}
