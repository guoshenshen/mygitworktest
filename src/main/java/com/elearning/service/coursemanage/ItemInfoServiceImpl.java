package com.elearning.service.coursemanage;

import com.elearning.dao.coursemanage.ItemInfoMapper;
import com.elearning.pojo.coursemanage.ItemInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 〈<br>
 * 〈〉
 *
 * @author lxx
 * @create 2019/10/14 16:15
 */
@Service("itemInfoService")
public class ItemInfoServiceImpl implements  IItemInfoService {

    @Autowired
    private ItemInfoMapper itemInfoMapper;

    @Override
    public List<ItemInfo> findByCourseId(Long courseId) {
        return itemInfoMapper.findItemInfo(courseId);
    }
}
