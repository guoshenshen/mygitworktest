package com.elearning.service.coursemanage;

import com.elearning.pojo.coursemanage.ItemInfo;

import java.util.List;

/**
 * 〈<br>
 * 〈〉
 *
 * @author lxx
 * @create 2019/10/14 16:15
 */
public interface IItemInfoService {
    List<ItemInfo> findByCourseId(Long courseId);
}
