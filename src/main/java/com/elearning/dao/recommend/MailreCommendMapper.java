package com.elearning.dao.recommend;

import com.elearning.pojo.recommend.MailreCommend;

public interface MailreCommendMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MailreCommend record);

    int insertSelective(MailreCommend record);

    MailreCommend selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MailreCommend record);

    int updateByPrimaryKey(MailreCommend record);
}