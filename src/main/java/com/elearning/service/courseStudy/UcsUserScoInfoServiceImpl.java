package com.elearning.service.courseStudy;

import com.elearning.dao.courseStudy.UcsUserScoInfoMapper;
import com.elearning.pojo.courseStudy.UcsUserScoInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 〈<br>
 * 〈〉
 *
 * @author lxx
 * @create 2019/10/14 16:24
 */
@Service("ucsUserScoInfoService")
public class UcsUserScoInfoServiceImpl implements  IUcsUserScoInfoService {

    @Autowired
    private UcsUserScoInfoMapper ucsUserScoInfoMapper;


    @Override
    public List<UcsUserScoInfo> findByMap(Map<String, Object> map) {
        return ucsUserScoInfoMapper.findByMap(map);
    }
}
