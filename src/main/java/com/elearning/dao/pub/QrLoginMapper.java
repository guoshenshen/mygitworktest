package com.elearning.dao.pub;

import com.elearning.pojo.pub.QRLogin;

public interface QrLoginMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(QRLogin record);

    int insertSelective(QRLogin record);

    QRLogin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(QRLogin record);

    int updateByPrimaryKey(QRLogin record);
}