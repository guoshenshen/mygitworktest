package com.elearning.dao.coursemanage;

import com.elearning.pojo.coursemanage.ItemInfo;

import java.util.List;

public interface ItemInfoMapper {
    int insert(ItemInfo record);

    int insertSelective(ItemInfo record);


    /**
     * 根据courseId查询
     * @param courseId
     * @return
     */
    List<ItemInfo> findItemInfo(Long courseId);
}